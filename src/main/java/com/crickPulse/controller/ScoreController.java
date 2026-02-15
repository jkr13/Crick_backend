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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crickPulse.apicaller.WebCaller;
import com.crickPulse.utils.Constants;
import com.crickPulse.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/line")
@Api(value = "Score Controller", description = "REST API for Score", tags = { "Score Controller" })
public class ScoreController {

	public static final Map<String, String> responses = new HashMap<String, String>();
	private static HashMap<String, Long> refreshTimes = new HashMap<String, Long>();

	@GetMapping(value = "/fixture")
	public ResponseEntity<String> getFixture(String tID, String startDate) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.FIXTURE + tID + startDate, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.FIXTURE + tID + startDate), HttpStatus.OK);

		} else {

			String url = Constants.ICC_BASEURL + "/fixtures?tournamentIds=" + tID + "&startDate=" + startDate
					+ "&matchStates=U&matchStates=L&sort=asc&page=0&pageSize=50&tournamentTypes=I&tournamentTypes=WI&altIds=true";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getHeaders());

			responses.put(Constants.FIXTURE + tID + startDate, response);
			refreshTimes.put(Constants.FIXTURE + tID + startDate, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.FIXTURE + tID + startDate), HttpStatus.OK);

	}

	@GetMapping(value = "/rankings/teams")
	public ResponseEntity<String> getTeamRankings(@RequestParam(required = false, defaultValue = "test") String type)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.RANKING + type, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {
			return new ResponseEntity<String>(responses.get(Constants.RANKING + type), HttpStatus.OK);
		} else {

			String url = Constants.ICC_BASEURL + "/icc-ratings/ranked/teams/" + type;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getHeaders());

			responses.put(Constants.RANKING + type, response);
			refreshTimes.put(Constants.RANKING + type, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.RANKING + type), HttpStatus.OK);
	}

	@GetMapping(value = "/rankings/players")
	public ResponseEntity<String> getPlayerRankings(@RequestParam(required = false, defaultValue = "test") String type,
			@RequestParam(required = false, defaultValue = "bat") String style)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.PLAYER_RANKING + type + style, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {
			return new ResponseEntity<String>(responses.get(Constants.PLAYER_RANKING + type + style), HttpStatus.OK);
		} else {

			String url = Constants.ICC_BASEURL + "/icc-ratings/ranked/players/" + type + "/" + style
					+ "?at=2023-09-28&page=0&pageSize=50&dmsPlayerIdRequired=true";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getHeaders());

			responses.put(Constants.PLAYER_RANKING + type + style, response);
			refreshTimes.put(Constants.PLAYER_RANKING + type + style, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.PLAYER_RANKING + type + style), HttpStatus.OK);
	}

	@GetMapping(value = "/standing")
	public ResponseEntity<String> getStanding(String tId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.STANDING + tId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {
			return new ResponseEntity<String>(responses.get(Constants.STANDING + tId), HttpStatus.OK);
		} else {

			String url = Constants.ICC_BASEURL + "/tournaments/" + tId + "/standings";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getHeaders());

			responses.put(Constants.STANDING + tId, response);
			refreshTimes.put(Constants.STANDING + tId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.STANDING + tId), HttpStatus.OK);
	}

	@GetMapping(value = "/commentry")
	public ResponseEntity<String> getCommentry(String mId, String page, String size)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.COMM + mId + page + size, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {
			return new ResponseEntity<String>(responses.get(Constants.COMM + mId + page + size), HttpStatus.OK);
		} else {

			String url = Constants.ICC_COMM_BASEURL + "/commentary/icc/" + mId + "/en?page=" + page + "&pageSize="
					+ size + "&direction=descending";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getHeaders());

			responses.put(Constants.COMM + mId + page + size, response);
			refreshTimes.put(Constants.COMM + mId + page + size, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.COMM + mId + page + size), HttpStatus.OK);
	}

	@GetMapping(value = "/scoring")
	public ResponseEntity<String> getScoring(String mId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.SC + mId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {
			return new ResponseEntity<String>(responses.get(Constants.SC + mId), HttpStatus.OK);
		} else {

			String url = Constants.ICC_COMM_BASEURL + "/fixtures/" + mId + "/scoring?altIds=false";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getHeaders());

			responses.put(Constants.SC + mId, response);
			refreshTimes.put(Constants.SC + mId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.SC + mId), HttpStatus.OK);
	}

	@GetMapping(value = "/squad")
	public ResponseEntity<String> getsquad(String mId, String tId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.SQ + mId + tId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {
			return new ResponseEntity<String>(responses.get(Constants.SQ + mId + tId), HttpStatus.OK);
		} else {

			String url = Constants.ICC_BASEURL + "/tournaments/" + mId + "/squads/" + tId;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getHeaders());

			responses.put(Constants.SQ + mId + tId, response);
			refreshTimes.put(Constants.SQ + mId + tId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.SQ + mId + tId), HttpStatus.OK);
	}

	@GetMapping(value = "/tStats")
	public ResponseEntity<String> getTstats(String tId, String statName) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.TSTATS + tId + statName, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {
			return new ResponseEntity<String>(responses.get(Constants.TSTATS + tId + statName), HttpStatus.OK);
		} else {

			String url = Constants.ICC_COMM_BASEURL + "/stats/ranked/teams/tournament/" + tId + "/" + statName;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getHeaders());

			responses.put(Constants.TSTATS + tId + statName, response);
			refreshTimes.put(Constants.TSTATS + tId + statName, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.TSTATS + tId + statName), HttpStatus.OK);
	}

	@GetMapping(value = "/tournamentStats")
	public ResponseEntity<String> getTournamentStats(String tId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.TSTATS + tId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {
			return new ResponseEntity<String>(responses.get(Constants.TSTATS + tId), HttpStatus.OK);
		} else {

			String url = Constants.ICC_COMM_BASEURL + "/stats/tournaments/" + tId;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getHeaders());

			responses.put(Constants.TSTATS + tId, response);
			refreshTimes.put(Constants.TSTATS + tId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.TSTATS + tId), HttpStatus.OK);
	}

	@GetMapping(value = "/stats")
	public ResponseEntity<String> getStats(String tId, String statName) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.STATS + tId + statName, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {
			return new ResponseEntity<String>(responses.get(Constants.STATS + tId + statName), HttpStatus.OK);
		} else {

			String url = Constants.ICC_BASEURL + "/stats/ranked/players/" + statName + "?tournamentIds=" + tId
					+ "&matchTypes=ODI&page=0&pageSize=50";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getHeaders());

			responses.put(Constants.STATS + tId + statName, response);
			refreshTimes.put(Constants.STATS + tId + statName, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.STATS + tId + statName), HttpStatus.OK);
	}

	@GetMapping(value = "/venue")
	public ResponseEntity<String> getVenue(String tId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.VENUE + tId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {
			return new ResponseEntity<String>(responses.get(Constants.VENUE + tId), HttpStatus.OK);
		} else {

			String url = Constants.ICC_VENUE_BASEURL + "/content/icc/news/en?page=0&pageSize=10&tagNames=cwc23-venue";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getHeaders());

			responses.put(Constants.VENUE + tId, response);
			refreshTimes.put(Constants.VENUE + tId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.VENUE + tId), HttpStatus.OK);
	}

	@GetMapping(value = "/all/fixture")
	public ResponseEntity<String> getAllFixture(String startDate, String endDate)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.ALL_FIXTURE + startDate + endDate, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {
			return new ResponseEntity<String>(responses.get(Constants.ALL_FIXTURE + startDate + endDate),
					HttpStatus.OK);
		} else {

			String url = Constants.ICC_BASEURL + "/fixtures?startDate=" + startDate + "&endDate=" + endDate
					+ "&matchStates=U&matchStates=C&matchStates=L&sort=asc&page=0&pageSize=120&tournamentTypes=I&altIds=true";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getHeaders());

			responses.put(Constants.ALL_FIXTURE + startDate + endDate, response);
			refreshTimes.put(Constants.ALL_FIXTURE + startDate + endDate, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.ALL_FIXTURE + startDate + endDate), HttpStatus.OK);
	}

	private String getStatReponse(String tId, String statName) {

		String url = Constants.ICC_BASEURL + "/stats/ranked/players/" + statName + "?tournamentIds=" + tId
				+ "&matchTypes=ODI&page=0&pageSize=50";
		String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getHeaders());
		return response;
	}

	@GetMapping(value = "/home")
	public ResponseEntity<String> getHomeDetail(String tId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.HOME + tId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.HOME + tId), HttpStatus.OK);

		} else {

			JSONObject response = new JSONObject();

			// Get mostruns, mostsixes and mostwickets details
			String mostRunResponse = getStatReponse(tId, Constants.MOST_RUNS);
			response.put(Constants.MOST_RUNS,
					!mostRunResponse.isEmpty() ? new JSONObject(mostRunResponse) : new JSONObject());

			String mostSixResponse = getStatReponse(tId, Constants.MOST_SIXES);
			response.put(Constants.MOST_SIXES,
					!mostSixResponse.isEmpty() ? new JSONObject(mostSixResponse) : new JSONObject());

			String mostWicketResponse = getStatReponse(tId, Constants.MOST_WICKETS);
			response.put(Constants.MOST_WICKETS,
					!mostWicketResponse.isEmpty() ? new JSONObject(mostWicketResponse) : new JSONObject());

			// Get standing response
			String standingUrl = Constants.ICC_BASEURL + "/tournaments/" + tId + "/standings";

			String standingResponse = WebCaller.getResult(standingUrl, WebCaller.HTTP_GET, null, Utils.getHeaders());
			response.put(Constants.STANDING,
					!standingResponse.isEmpty() ? new JSONObject(standingResponse) : new JSONObject());

			// Get tournamentStats response
			String tournamentStateUrl = Constants.ICC_COMM_BASEURL + "/stats/tournaments/" + tId;

			String tournamentStateResponse = WebCaller.getResult(tournamentStateUrl, WebCaller.HTTP_GET, null,
					Utils.getHeaders());

			response.put(Constants.TSTATS,
					!tournamentStateResponse.isEmpty() ? new JSONObject(tournamentStateResponse) : new JSONObject());
			responses.put(Constants.HOME + tId, response.toString());
			refreshTimes.put(Constants.HOME + tId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.HOME + tId), HttpStatus.OK);
	}

	public static void clearResponsesMap() {

		responses.clear();
		refreshTimes.clear();
	}

}
