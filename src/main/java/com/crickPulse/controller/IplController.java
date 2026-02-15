package com.crickPulse.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crickPulse.apicaller.WebCaller;
import com.crickPulse.utils.Constants;
import com.crickPulse.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/ipl")
@Api(value = "IPL Controller", description = "REST API for IPL", tags = { "IPL Controller" })
public class IplController {

	public static final Map<String, String> responses = new HashMap<String, String>();
	private static HashMap<String, Long> refreshTimes = new HashMap<String, Long>();

	@GetMapping(value = "/season-list")
	public ResponseEntity<String> getAllSeason() throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.I_Series, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.I_Series), HttpStatus.OK);

		} else {

			String url = Constants.IPL_BASEURL + "/ipl_matches/app/franchise/season-list";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getIPLHeaders());

			responses.put(Constants.I_Series, response);
			refreshTimes.put(Constants.I_Series, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.I_Series), HttpStatus.OK);

	}

	@GetMapping(value = "/allTeam")
	public ResponseEntity<String> getAllTeam() throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.I_Teams, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.I_Teams), HttpStatus.OK);

		} else {

			String url = Constants.IPL_BASEURL + "/ipl_matches/app/franchise/teams-list";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getIPLHeaders());

			responses.put(Constants.I_Teams, response);
			refreshTimes.put(Constants.I_Teams, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.I_Teams), HttpStatus.OK);

	}

	@GetMapping(value = "/standings")
	public ResponseEntity<String> getPointTable(String type, String year) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.I_Standings + type + year, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.I_Standings + type + year), HttpStatus.OK);

		} else {

			String url = Constants.IPL_BASEURL + "/menu/app/standing?type=" + type + "&year=" + year;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getIPLHeaders());

			responses.put(Constants.I_Standings + type + year, response);
			refreshTimes.put(Constants.I_Standings + type + year, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.I_Standings + type + year), HttpStatus.OK);

	}

	@GetMapping(value = "/matchID")
	public ResponseEntity<String> getMatchId(String mId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.I_entity + mId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.I_entity + mId), HttpStatus.OK);

		} else {

			String url = Constants.IPL_BASEURL + "/ipl_matches/web/getEnitityId?smId=" + mId;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getIPLHeaders());

			responses.put(Constants.I_entity + mId, response);
			refreshTimes.put(Constants.I_entity + mId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.I_entity + mId), HttpStatus.OK);

	}

	@GetMapping(value = "/stats")
	public ResponseEntity<String> getStats(String type, String pType, String season, String teamId, String sort)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.I_Stats + type + pType + season + teamId + sort, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.I_Stats + type + pType + season + teamId + sort),
					HttpStatus.OK);

		} else {

			String url = Constants.IPL_BASEURL + "/ipl_matches/app/stats?sort=" + sort + "&stats_type=" + type
					+ "&player_type=" + pType + "&season=" + season + "&team_id=" + teamId;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getIPLHeaders());

			responses.put(Constants.I_Stats + type + pType + season + teamId + sort, response);
			refreshTimes.put(Constants.I_Stats + type + pType + season + teamId + sort, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.I_Stats + type + pType + season + teamId + sort),
				HttpStatus.OK);

	}

	@GetMapping(value = "/tournamentStats")
	public ResponseEntity<String> getTStats(String sId, String statName) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.I_TStats + sId + statName, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.I_TStats + sId + statName), HttpStatus.OK);

		} else {

			String url = Constants.IPL_STATURL + "/ipl/feeds/stats/" + sId + "-" + statName + ".js";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getIPLHeaders());

			responses.put(Constants.I_TStats + sId + statName, response);
			refreshTimes.put(Constants.I_TStats + sId + statName, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.I_TStats + sId + statName), HttpStatus.OK);

	}

	@GetMapping(value = "/squad")
	public ResponseEntity<String> getSquad(String mId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.I_Squad + mId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.I_Squad + mId), HttpStatus.OK);

		} else {

			String url = Constants.IPL_FEEDURL + "/" + mId + "-squad.js";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getIPLHeaders());

			responses.put(Constants.I_Squad + mId, response);
			refreshTimes.put(Constants.I_Squad + mId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.I_Squad + mId), HttpStatus.OK);

	}

	@GetMapping(value = "/schdule")
	public ResponseEntity<String> getSchdule(String sId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.I_Schdule + sId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.I_Schdule + sId), HttpStatus.OK);

		} else {

			String url = Constants.IPL_FEEDURL + "/" + sId + "-matchschedule.js";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getIPLHeaders());

			responses.put(Constants.I_Schdule + sId, response);
			refreshTimes.put(Constants.I_Schdule + sId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.I_Schdule + sId), HttpStatus.OK);

	}

	@GetMapping(value = "/summary")
	public ResponseEntity<String> getSummary(String mId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.I_Summary + mId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.I_Summary + mId), HttpStatus.OK);

		} else {

			String url = Constants.IPL_FEEDURL + "/" + mId + "-matchsummary.js";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getIPLHeaders());

			responses.put(Constants.I_Summary + mId, response);
			refreshTimes.put(Constants.I_Summary + mId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.I_Summary + mId), HttpStatus.OK);

	}

	@GetMapping(value = "/inning")
	public ResponseEntity<String> getInning(String mId, String inningId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.I_Innings + mId + inningId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.I_Innings + mId + inningId), HttpStatus.OK);

		} else {

			String url = Constants.IPL_FEEDURL + "/" + mId + "-Innings" + inningId + ".js";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getIPLHeaders());

			responses.put(Constants.I_Innings + mId + inningId, response);
			refreshTimes.put(Constants.I_Innings + mId + inningId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.I_Innings + mId + inningId), HttpStatus.OK);

	}

	@GetMapping(value = "/player")
	public ResponseEntity<String> getPlayer(String pId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.I_Player + pId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.I_Player + pId), HttpStatus.OK);

		} else {

			String url = Constants.IPL_BASEURL + "/ipl_matches/app/playersdetails?player=" + pId;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getIPLHeaders());

			responses.put(Constants.I_Player + pId, response);
			refreshTimes.put(Constants.I_Player + pId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.I_Player + pId), HttpStatus.OK);

	}

	@GetMapping(value = "/masterData")
	public ResponseEntity<String> getMasterData(String mId, String inningId)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.I_Master + mId + inningId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.I_Master + mId + inningId), HttpStatus.OK);

		} else {

			String url = "https://polls.iplt20.com/widget/welcome/get_data?path=matches/" + mId + "/innings/" + inningId
					+ "/commentary&token=66&actualball=1";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getIPLHeaders());

			responses.put(Constants.I_Master + mId + inningId, response);
			refreshTimes.put(Constants.I_Master + mId + inningId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.I_Master + mId + inningId), HttpStatus.OK);

	}

	@GetMapping(value = "/mstats")
	public ResponseEntity<String> getStats(String mId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.I_MStats + mId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.I_MStats + mId), HttpStatus.OK);

		} else {

			String url = "https://polls.entitysport.com/widget/entity_cricket/cricket_request?path=matches%2F" + mId
					+ "%2Fstatistics&host=polls.entitysport.com";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getIPLHeaders());

			responses.put(Constants.I_MStats + mId, response);
			refreshTimes.put(Constants.I_MStats + mId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.I_MStats + mId), HttpStatus.OK);

	}

	@GetMapping(value = "/score")
	public ResponseEntity<String> getMscore(String mId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.I_MScore + mId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.I_MScore + mId), HttpStatus.OK);

		} else {

			String url = "https://polls.iplt20.com/widget/welcome/get_data?path=https://rest.entitysport.com/v2/matches/"
					+ mId + "/innings/info?token=bcf592cdfb5507cc6d922d73f75bb5cc";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getIPLHeaders());

			responses.put(Constants.I_MScore + mId, response);
			refreshTimes.put(Constants.I_MScore + mId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.I_MScore + mId), HttpStatus.OK);

	}

	public static void clearResponsesMap() {

		responses.clear();
		refreshTimes.clear();
	}
}
