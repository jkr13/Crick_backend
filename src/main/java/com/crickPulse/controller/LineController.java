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
import com.crickPulse.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/line/v2/")
@Api(value = "Score Controller", description = "REST API for Score", tags = { "Score Controller" })
public class LineController {

	public static final Map<String, String> responses = new HashMap<String, String>();
	private static HashMap<String, Long> refreshTimes = new HashMap<String, Long>();

	@GetMapping(value = "/info")
	public ResponseEntity<String> getFixture(String mId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.M_INFO + mId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.M_INFO + mId), HttpStatus.OK);

		} else {

			String url = Constants.MAZZA_BASEURL + "/game_info_v5";

			JSONObject gameInfo = new JSONObject();
			JSONObject gameId = new JSONObject();
			gameId.put("GAMEID", mId);
			gameInfo.put("GAME_INFO", gameId);

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, gameInfo.toString(),
					Utils.getMazzaHeaders());

			responses.put(Constants.M_INFO + mId, response);
			refreshTimes.put(Constants.M_INFO + mId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.M_INFO + mId), HttpStatus.OK);

	}

	@GetMapping(value = "/squad")
	public ResponseEntity<String> getSquad(String mId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.M_SQUAD + mId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.M_SQUAD + mId), HttpStatus.OK);

		} else {

			String url = Constants.MAZZA_BASEURL + "/game_squads_v5";

			JSONObject gameInfo = new JSONObject();
			JSONObject gameId = new JSONObject();
			gameId.put("GAMEID", mId);
			gameInfo.put("GAME_SQUADS", gameId);

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, gameInfo.toString(),
					Utils.getMazzaHeaders());

			responses.put(Constants.M_SQUAD + mId, response);
			refreshTimes.put(Constants.M_SQUAD + mId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.M_SQUAD + mId), HttpStatus.OK);

	}

	@GetMapping(value = "/scorecard")
	public ResponseEntity<String> getScorecard(String mId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.M_SC + mId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.M_SC + mId), HttpStatus.OK);

		} else {

			String url = Constants.MAZZA_BASEURL + "/scorecardsv1_v5";

			JSONObject gameInfo = new JSONObject();
			JSONObject gameId = new JSONObject();
			gameId.put("GAMEID", mId);
			gameInfo.put("GAME_SCORE", gameId);

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, gameInfo.toString(),
					Utils.getMazzaHeaders());

			responses.put(Constants.M_SC + mId, response);
			refreshTimes.put(Constants.M_SC + mId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.M_SC + mId), HttpStatus.OK);

	}

	@GetMapping(value = "/mom")
	public ResponseEntity<String> getMom(String mId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.M_MOM + mId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.M_MOM + mId), HttpStatus.OK);

		} else {

			String url = Constants.MAZZA_BASEURL + "/game_mom_v5";

			JSONObject gameInfo = new JSONObject();
			JSONObject gameId = new JSONObject();
			gameId.put("GAMEID", mId);
			gameInfo.put("GAME_MOM", gameId);

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, gameInfo.toString(),
					Utils.getMazzaHeaders());

			responses.put(Constants.M_MOM + mId, response);
			refreshTimes.put(Constants.M_MOM + mId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.M_MOM + mId), HttpStatus.OK);

	}

	@GetMapping(value = "/oddhistory")
	public ResponseEntity<String> getOddsHistory(String mId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.M_HISTORY + mId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.M_HISTORY + mId), HttpStatus.OK);

		} else {

			String url = Constants.MAZZA_BASEURL + "/odds_history_view_v5";

			JSONObject gameId = new JSONObject();
			gameId.put("GAMEID", mId);

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, gameId.toString(), Utils.getMazzaHeaders());

			responses.put(Constants.M_HISTORY + mId, response);
			refreshTimes.put(Constants.M_HISTORY + mId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.M_HISTORY + mId), HttpStatus.OK);

	}

	@GetMapping(value = "/series")
	public ResponseEntity<String> getSeries(String sId, int index) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.M_SERIES + sId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.M_SERIES + sId), HttpStatus.OK);

		} else {

			String url = Constants.MAZZA_BASEURL + "/series_games_v5";

			JSONObject gameId = new JSONObject();
			gameId.put("seriesid", sId);
			gameId.put("page_index", index);

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, gameId.toString(), Utils.getMazzaHeaders());

			responses.put(Constants.M_SERIES + sId, response);
			refreshTimes.put(Constants.M_SERIES + sId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.M_SERIES + sId), HttpStatus.OK);

	}

	@GetMapping(value = "/home")
	public ResponseEntity<String> getHome() throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.M_HOME, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.M_HOME), HttpStatus.OK);

		} else {

			String url = Constants.MAZZA_BASEURL + "/newhome_screenv1_v5";

			JSONObject home = new JSONObject();
			JSONObject sub = new JSONObject();

			sub.put("DEVICE_PLATFORM", "ANDROID");
			sub.put("UDID", "12121212");
			sub.put("shown", "cm");

			home.put("HOME_SCREEN", sub);

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, home.toString(), Utils.getMazzaHeaders());

			responses.put(Constants.M_HOME, response);
			refreshTimes.put(Constants.M_HOME, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.M_HOME), HttpStatus.OK);

	}

	@GetMapping(value = "/ranking")
	public ResponseEntity<String> getRankings() throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.M_RANKINGS, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.M_RANKINGS), HttpStatus.OK);

		} else {

			String url = Constants.MAZZA_BASEURL + "/allranking";

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, null, Utils.getMazzaHeaders());

			responses.put(Constants.M_RANKINGS, response);
			refreshTimes.put(Constants.M_RANKINGS, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.M_RANKINGS), HttpStatus.OK);

	}

	@GetMapping(value = "/fixture")
	public ResponseEntity<String> getFixtures(String gameType, String upcoming, int pageIndex)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.M_FIXTURES + gameType + upcoming + pageIndex, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.M_FIXTURES + gameType + upcoming + pageIndex),
					HttpStatus.OK);

		} else {

			String url = Constants.MAZZA_BASEURL + "/game_schedulesv1_v5";

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("GAMEDATE", "0");
			jsonObject.put("GAME_TYPE", gameType);
			jsonObject.put("PAGEINDEX", pageIndex);
			jsonObject.put("UPCOMING", upcoming);

			JSONObject gameSchdule = new JSONObject();
			gameSchdule.put("GAME_SCHEDULE", jsonObject);

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, gameSchdule.toString(),
					Utils.getMazzaHeaders());

			responses.put(Constants.M_FIXTURES + gameType + upcoming + pageIndex, response);
			refreshTimes.put(Constants.M_FIXTURES + gameType + upcoming + pageIndex, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.M_FIXTURES + gameType + upcoming + pageIndex),
				HttpStatus.OK);

	}

	public static void clearResponsesMap() {

		responses.clear();
		refreshTimes.clear();
	}

}
