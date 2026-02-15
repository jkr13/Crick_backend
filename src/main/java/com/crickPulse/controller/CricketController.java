package com.crickPulse.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crickPulse.apicaller.WebCaller;
import com.crickPulse.utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/cricket")
@Api(value = "Cricket com Controller", tags = { "Cricket Controller" })
public class CricketController {

	public static final Map<String, String> responses = new HashMap<String, String>();
	private static HashMap<String, Long> refreshTimes = new HashMap<String, Long>();

	@GetMapping(value = "/upcoming")
	public ResponseEntity<String> getMatches() throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.CRICKET_SCH, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.CRICKET_SCH), HttpStatus.OK);

		} else {

			String url = Constants.CRICKET_COM_BASEURL;

			JSONObject jsonObject = new JSONObject();
			JSONObject subObject = new JSONObject();

			subObject.put("type", "All");
			subObject.put("status", "upcoming");
			subObject.put("page", 0);

			jsonObject.put("operationName", "schedule");
			jsonObject.put("variables", subObject);
			jsonObject.put("query",
					"query schedule($type: String!, $status: String!, $page: Int!) {\n  newSchedule(type: $type, status: $status, page: $page) {\n    matches {\n      playerOfTheMatchdDetails {\n        playerID\n        playerTeamID\n        playerName\n        batsman {\n          batsmanBalls\n          batsmanRuns\n          isNotOut\n        }\n        bowler {\n          bowlerWickets\n          bowlerConceeded\n          bowlerOvers\n        }\n      }\n      winningTeamID\n      userNotification\n      matchenddate\n      seriesName\n      seriesID\n      currentinningsNo\n      currentInningteamID\n      currentInningsTeamName\n      toss\n      startEndDate\n      matchStatus\n      matchID\n      matchType\n      statusMessage\n      matchNumber\n      venue\n      currentDay\n      currentSession\n      matchResult\n      startDate\n      playerOfTheMatch\n      playerofTheMatchTeamShortName\n      playerID\n      isCricklyticsAvailable\n      isLiveCriclyticsAvailable\n      playing11Status\n      probable11Status\n      isAbandoned\n      teamsWinProbability {\n        homeTeamShortName\n        homeTeamPercentage\n        awayTeamShortName\n        awayTeamPercentage\n        tiePercentage\n      }\n      matchScore {\n        teamFullName\n        teamID\n        teamShortName\n        teamScore {\n          inning\n          inningNumber\n          battingTeam\n          runsScored\n          wickets\n          declared\n          folowOn\n          overs\n          runRate\n          battingSide\n          teamID\n          battingTeamShortName\n        }\n      }\n    }\n    seriesID\n    matchType\n    seriesName\n    seriesView\n    league\n    seriesAvailable\n  }\n}");

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, jsonObject.toString(), null);

			responses.put(Constants.CRICKET_SCH, response);
			refreshTimes.put(Constants.CRICKET_SCH, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.CRICKET_SCH), HttpStatus.OK);

	}

	@GetMapping(value = "/commentary")
	public ResponseEntity<String> getCommentary(String matchId, long timestamp)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.CRICKET_COMMENTARY + matchId + timestamp, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.CRICKET_COMMENTARY + matchId + timestamp),
					HttpStatus.OK);

		} else {

			String url = Constants.CRICKET_COM_BASEURL + "/commentary";

			JSONObject jsonObject = new JSONObject();
			JSONObject subObject = new JSONObject();

			subObject.put("matchId", matchId);
			subObject.put("timeStamp", timestamp);

			jsonObject.put("operationName", "getCommentary");
			jsonObject.put("variables", subObject);
			jsonObject.put("query",
					"query getCommentary($matchId: String!, $timeStamp: Float!) {\n  getCommentary(matchId: $matchId, timeStamp: $timeStamp) {\n    commentary\n    __typename\n  }\n}");

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, jsonObject.toString(), null);

			responses.put(Constants.CRICKET_COMMENTARY + matchId + timestamp, response);
			refreshTimes.put(Constants.CRICKET_COMMENTARY + matchId + timestamp, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.CRICKET_COMMENTARY + matchId + timestamp),
				HttpStatus.OK);

	}

	@GetMapping(value = "/chart")
	public ResponseEntity<String> getChart(String matchId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.CRICKET_RUNS + matchId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.CRICKET_RUNS + matchId), HttpStatus.OK);

		} else {

			String url = Constants.CRICKET_COM_BASEURL;

			JSONObject jsonObject = new JSONObject();
			JSONObject subObject = new JSONObject();

			subObject.put("matchID", matchId);

			jsonObject.put("operationName", "runsComparsion");
			jsonObject.put("variables", subObject);
			jsonObject.put("query",
					"query runsComparsion($matchID: String!) {\n  runsComparsion(matchID: $matchID) {\n    inningsPhase {\n      _id\n      teamID\n      teamShortName\n      totalRuns\n      totalWickets\n      data {\n        overNumber\n        runs\n        wickets\n        innings\n        teamID\n        teamShortName\n        score\n      }\n    }\n    format\n  }\n}");

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, jsonObject.toString(), null);

			responses.put(Constants.CRICKET_RUNS + matchId, response);
			refreshTimes.put(Constants.CRICKET_RUNS + matchId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.CRICKET_RUNS + matchId), HttpStatus.OK);

	}

	@GetMapping(value = "/mStats")
	public ResponseEntity<String> getMStats(String matchId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.CRICKET_M_STATS + matchId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.CRICKET_M_STATS + matchId), HttpStatus.OK);

		} else {

			String url = Constants.CRICKET_COM_BASEURL;

			JSONObject jsonObject = new JSONObject();
			JSONObject subObject = new JSONObject();

			subObject.put("matchID", matchId);

			jsonObject.put("operationName", "getmatchStats");
			jsonObject.put("variables", subObject);
			jsonObject.put("query",
					"query getmatchStats($matchID: String!) {\n  getmatchStats(matchID: $matchID) {\n    matchID\n    awayTeamID\n    homeTeamID\n    format\n    matchStatsArray {\n      teamId\n      shortName\n      fullName\n      highestBattingScore {\n        value\n        percent\n      }\n      totalFours {\n        value\n        percent\n      }\n      totalSix {\n        value\n        percent\n      }\n      runsScoredInBoundaries {\n        value\n        percent\n      }\n      highestPartnership {\n        value\n        percent\n      }\n      highestPartnership {\n        value\n        percent\n      }\n      totalDotBalls {\n        value\n        percent\n      }\n      runRate {\n        value\n        percent\n      }\n      runRateInPowerplay1_6 {\n        value\n        percent\n      }\n      runRateInPowerplay1_10 {\n        value\n        percent\n      }\n      runRateInPowerplay11_40 {\n        value\n        percent\n      }\n      runRateInPowerplay41_50 {\n        value\n        percent\n      }\n      runRateDeathOver {\n        value\n        percent\n      }\n      highestWickets {\n        value\n        percent\n      }\n      extras {\n        value\n        percent\n      }\n    }\n  }\n}");

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, jsonObject.toString(), null);

			responses.put(Constants.CRICKET_M_STATS + matchId, response);
			refreshTimes.put(Constants.CRICKET_M_STATS + matchId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.CRICKET_M_STATS + matchId), HttpStatus.OK);

	}

	@GetMapping(value = "/form")
	public ResponseEntity<String> getForm(String matchId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.CRICKET_FORM + matchId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.CRICKET_FORM + matchId), HttpStatus.OK);

		} else {

			String url = Constants.CRICKET_COM_BASEURL;

			JSONObject jsonObject = new JSONObject();
			JSONObject subObject = new JSONObject();

			subObject.put("matchID", matchId);

			jsonObject.put("operationName", "getFormIndex");
			jsonObject.put("variables", subObject);
			jsonObject.put("query",
					"query getFormIndex($matchID: String!) {\n  getFormIndex(matchID: $matchID) {\n    teamOne {\n      name\n      bowlers {\n        playerID\n        playerName\n        BowlER\n        OverallForm\n        BowlAvg\n        BowlSR\n      }\n      batsmen {\n        playerID\n        playerName\n        BatSR\n        BatAvg\n        OverallForm\n      }\n      keepers {\n        playerID\n        playerName\n        BatSR\n        BatAvg\n        OverallForm\n      }\n      allRounders {\n        playerID\n        playerName\n        BatSR\n        BatAvg\n        OverallForm\n        BowlAvg\n      }\n    }\n    teamTwo {\n      name\n      bowlers {\n        playerID\n        playerName\n        BowlER\n        OverallForm\n        BowlAvg\n        BowlSR\n      }\n      batsmen {\n        playerID\n        playerName\n        BatSR\n        BatAvg\n        OverallForm\n      }\n      keepers {\n        playerID\n        playerName\n        BatSR\n        BatAvg\n        OverallForm\n      }\n      allRounders {\n        playerID\n        playerName\n        BatSR\n        BatAvg\n        OverallForm\n        BowlAvg\n      }\n    }\n  }\n}");

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, jsonObject.toString(), null);

			responses.put(Constants.CRICKET_FORM + matchId, response);
			refreshTimes.put(Constants.CRICKET_FORM + matchId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.CRICKET_FORM + matchId), HttpStatus.OK);

	}

	@GetMapping(value = "/phase")
	public ResponseEntity<String> getPhase(String matchId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.CRICKET_PHASE + matchId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.CRICKET_PHASE + matchId), HttpStatus.OK);

		} else {

			String url = Constants.CRICKET_COM_BASEURL;

			JSONObject jsonObject = new JSONObject();
			JSONObject subObject = new JSONObject();

			subObject.put("matchID", matchId);

			jsonObject.put("operationName", "getSessionWinner");
			jsonObject.put("variables", subObject);
			jsonObject.put("query",
					"query getSessionWinner($matchID: String!) {\n  getSessionWinner(matchID: $matchID) {\n    teamA\n    teamB\n    teamAId\n    teamBId\n    teamAWon\n    teamBWon\n    shared\n    data {\n      sessions {\n        day\n        session\n        teamWon\n        teamId\n        scores {\n          startScore\n          endScore\n          teamId\n          teamName\n          inningNo\n        }\n      }\n    }\n  }\n}");

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, jsonObject.toString(), null);

			responses.put(Constants.CRICKET_PHASE + matchId, response);
			refreshTimes.put(Constants.CRICKET_PHASE + matchId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.CRICKET_PHASE + matchId), HttpStatus.OK);

	}

	@GetMapping(value = "/highlight")
	public ResponseEntity<String> getHighlight(String matchId, String inningId, String type, int page)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes
				.getOrDefault(Constants.CRICKET_HIGHLIGHTS + matchId + inningId + type + page, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(
					responses.get(Constants.CRICKET_HIGHLIGHTS + matchId + inningId + type + page), HttpStatus.OK);

		} else {

			String url = Constants.CRICKET_COM_BASEURL;

			JSONObject jsonObject = new JSONObject();
			JSONObject subObject = new JSONObject();

			subObject.put("matchID", matchId);
			subObject.put("innings", inningId);
			subObject.put("type", type);
			subObject.put("page", page);

			jsonObject.put("operationName", "getHighlights");
			jsonObject.put("variables", subObject);
			jsonObject.put("query",
					"query getHighlights($matchID: String!, $innings: String!, $type: String, $page: Int) {\n  getHighlights(matchID: $matchID, innings: $innings, type: $type, page: $page) {\n    HighlightBall {\n      matchID\n      ballID\n      ballNumber\n      innings\n      over\n      overNumber\n      commentary\n      teamID\n      teamName\n      runs\n      xCoordinate\n      yCoordinate\n      wicket\n      zad\n    }\n    totalCount {\n      totalSix\n      totalFours\n      totalWickets\n    }\n  }\n}");

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, jsonObject.toString(), null);

			responses.put(Constants.CRICKET_HIGHLIGHTS + matchId + inningId + type + page, response);
			refreshTimes.put(Constants.CRICKET_HIGHLIGHTS + matchId + inningId + type + page, currentTime);

		}

		return new ResponseEntity<String>(
				responses.get(Constants.CRICKET_HIGHLIGHTS + matchId + inningId + type + page), HttpStatus.OK);

	}

	public static void clearResponsesMap() {

		responses.clear();
		refreshTimes.clear();
	}
}
