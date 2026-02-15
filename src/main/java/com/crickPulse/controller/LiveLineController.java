package com.crickPulse.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
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
@RequestMapping(value = "/line/v3/")
@Api(value = "Live Line Controller", description = "REST API for Live Line", tags = { "Live Line Controller" })
public class LiveLineController {

	public static final Map<String, String> responses = new HashMap<String, String>();
	private static HashMap<String, Long> refreshTimes = new HashMap<String, Long>();

	@GetMapping(value = "/series")
	public ResponseEntity<String> getAllSeries(String pageNo) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_SERIES + pageNo, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_SERIES + pageNo), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v2/series?pageno=" + pageNo;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			JSONArray parsedResponse = Utils.parseGuruSeries(response);

			responses.put(Constants.G_SERIES + pageNo, parsedResponse.toString());
			refreshTimes.put(Constants.G_SERIES + pageNo, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_SERIES + pageNo), HttpStatus.OK);

	}

	@GetMapping(value = "/seriesInfo")
	public ResponseEntity<String> getSeriesInfo(String key) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_SERIES_MATCHES + key, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_SERIES_MATCHES + key), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/series/" + key + "/matches";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			JSONArray matchArray = Utils.parseGutuSeriesMatches(response);

			responses.put(Constants.G_SERIES_MATCHES + key, matchArray.toString());
			refreshTimes.put(Constants.G_SERIES_MATCHES + key, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_SERIES_MATCHES + key), HttpStatus.OK);

	}

	@GetMapping(value = "/seriesOverview")
	public ResponseEntity<String> seriesOverview(String key) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_SERIES_OVERVIEW + key, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_SERIES_OVERVIEW + key), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/series/" + key + "/matches";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());
			JSONArray matchArray = Utils.parseGutuSeriesMatches(response);

			url = Constants.GURU_BASEURL + "/api/v1/series/" + key + "/pointstable";
			String ptsresponse = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());
			JSONArray pointtables = Utils.parseGuruPointTable(ptsresponse);

			url = Constants.GURU_BASEURL + "/api/v1/series/" + key + "/teams";
			String teams = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());
			JSONArray teamsArray = Utils.parseGuruTeams(teams);

			url = Constants.GURU_BASEURL + "/api/v1/series/" + key + "/stats/options";
			String optionResponse = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());
			JSONArray optionsArray = Utils.parseGuruOptions(optionResponse);

			JSONObject seriesInfo = new JSONObject();
			seriesInfo.put("matches", matchArray);
			seriesInfo.put("tables", pointtables);
			seriesInfo.put("teams", teamsArray);
			seriesInfo.put("stats", optionsArray);

			responses.put(Constants.G_SERIES_OVERVIEW + key, seriesInfo.toString());
			refreshTimes.put(Constants.G_SERIES_OVERVIEW + key, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_SERIES_OVERVIEW + key), HttpStatus.OK);

	}

	@GetMapping(value = "/pointTable")
	public ResponseEntity<String> getPointTable(String sId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_SERIES_POINTTABLE + sId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_SERIES_POINTTABLE + sId), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/series/" + sId + "/pointstable";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			JSONArray pointtables = Utils.parseGuruPointTable(response);

			responses.put(Constants.G_SERIES_POINTTABLE + sId, pointtables.toString());
			refreshTimes.put(Constants.G_SERIES_POINTTABLE + sId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_SERIES_POINTTABLE + sId), HttpStatus.OK);

	}

	@GetMapping(value = "/teams")
	public ResponseEntity<String> getTeams(String sId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_SERIES_TEAM + sId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_SERIES_TEAM + sId), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/series/" + sId + "/teams";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_SERIES_TEAM + sId, response);
			refreshTimes.put(Constants.G_SERIES_TEAM + sId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_SERIES_TEAM + sId), HttpStatus.OK);

	}

	@GetMapping(value = "/team")
	public ResponseEntity<String> getTeam(String sId, String format, String tId)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_SERIES_TEAM + sId + tId + format, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_SERIES_TEAM + sId + tId + format),
					HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/series/" + sId + "/squad/team/" + tId + "/format/" + format;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_SERIES_TEAM + sId + tId + format, response);
			refreshTimes.put(Constants.G_SERIES_TEAM + sId + tId + format, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_SERIES_TEAM + sId + tId + format), HttpStatus.OK);

	}

	@GetMapping(value = "/stats/options")
	public ResponseEntity<String> getStas(String sId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_STATS_OPTIONS + sId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_STATS_OPTIONS + sId), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/series/" + sId + "/stats/options";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_STATS_OPTIONS + sId, response);
			refreshTimes.put(Constants.G_STATS_OPTIONS + sId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_STATS_OPTIONS + sId), HttpStatus.OK);

	}

	@GetMapping(value = "/stats")
	public ResponseEntity<String> getStats(String sId, String type, String format)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_STATS + sId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_STATS + sId), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/series/" + sId + "/stats/" + type + "/format/" + format;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_STATS + sId, response);
			refreshTimes.put(Constants.G_STATS + sId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_STATS + sId), HttpStatus.OK);

	}

	@GetMapping(value = "/venue")
	public ResponseEntity<String> getVenue(String sId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_Venue + sId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_Venue + sId), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/series/" + sId + "/venue";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_Venue + sId, response);
			refreshTimes.put(Constants.G_Venue + sId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_Venue + sId), HttpStatus.OK);

	}

	@GetMapping(value = "/ballbyball")
	public ResponseEntity<String> getBallByBall(String key, @RequestParam(required = false) String lt)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_BALL + key + lt, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.G_BALL + key + lt), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/match/" + key + "/ballByBall";

			if (lt != null && lt.length() > 1) {
				url = Constants.GURU_BASEURL + "/api/v1/match/" + key + "/ballByBall?lt=" + lt;
			}

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_BALL + key + lt, response);
			refreshTimes.put(Constants.G_BALL + key + lt, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_BALL + key + lt), HttpStatus.OK);

	}

	@GetMapping(value = "/info")
	public ResponseEntity<String> getInfo(String key) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_INFO + key, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_INFO + key), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/match/" + key + "/info";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_INFO + key, response);
			refreshTimes.put(Constants.G_INFO + key, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_INFO + key), HttpStatus.OK);

	}

	@GetMapping(value = "/scorecard")
	public ResponseEntity<String> getScorecard(String key) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_SC + key, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.G_SC + key), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/match/" + key + "/scorecard";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_SC + key, response);
			refreshTimes.put(Constants.G_SC + key, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_SC + key), HttpStatus.OK);

	}

	@GetMapping(value = "/squad")
	public ResponseEntity<String> getSquad(String mId) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_SQUAD + mId, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_SQUAD + mId), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/match/" + mId + "/squad";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_SQUAD + mId, response);
			refreshTimes.put(Constants.G_SQUAD + mId, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_SQUAD + mId), HttpStatus.OK);

	}

	@GetMapping(value = "/pdetail")
	public ResponseEntity<String> getPdetail(String pId, String type) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_PDETAIL + pId + type, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_PDETAIL + pId + type), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/player/" + pId + "/info?type=" + type;

			if (type != null && type.length() > 0) {

				url = Constants.GURU_BASEURL + "/api/v1/player/" + pId + "/info";

			}

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_PDETAIL + pId + type, response);
			refreshTimes.put(Constants.G_PDETAIL + pId + type, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_PDETAIL + pId + type), HttpStatus.OK);

	}

	@GetMapping(value = "/ranking")
	public ResponseEntity<String> getRanking(String format, String gender) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_RANKINGS + format + gender, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_RANKINGS + format + gender), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/rankings?format=" + format + "&gender=" + gender;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_RANKINGS + format + gender, response);
			refreshTimes.put(Constants.G_RANKINGS + format + gender, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_RANKINGS + format + gender), HttpStatus.OK);

	}

	@GetMapping(value = "/fixture")
	public ResponseEntity<String> getFixture(String format, String groupByDate, int pageNo)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_FIXTURES + format + pageNo + groupByDate, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.G_FIXTURES + format + pageNo + groupByDate),
					HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/match/list?groupByDate=true";

			if (format != null && !format.isEmpty()) {
				url = url + "&format=" + format;
			}

			if (pageNo != 0) {
				url = url + "&pageno=" + pageNo;
			}
			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			JSONArray fixtureMatches = Utils.parseGuruFixture(response);

			responses.put(Constants.G_FIXTURES + format + pageNo + groupByDate, fixtureMatches.toString());
			refreshTimes.put(Constants.G_FIXTURES + format + pageNo + groupByDate, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_FIXTURES + format + pageNo + groupByDate),
				HttpStatus.OK);

	}

	@GetMapping(value = "/getLiveMatches")
	public ResponseEntity<String> getLiveMatches() throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_FIXTURES, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.G_HOME), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/home";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_HOME, response.toString());
			refreshTimes.put(Constants.G_HOME, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_HOME), HttpStatus.OK);

	}

	@GetMapping(value = "/pSearch")
	public ResponseEntity<String> getPlayer(String search) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_PSEARCH + search, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_PSEARCH + search), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/player/trending/app?search=" + search;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_PSEARCH + search, response);
			refreshTimes.put(Constants.G_PSEARCH + search, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_PSEARCH + search), HttpStatus.OK);

	}

	@GetMapping(value = "/tSearch")
	public ResponseEntity<String> getTeam(String search) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_TSEARCH + search, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_TSEARCH + search), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/team/trending/app?search=" + search;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_TSEARCH + search, response);
			refreshTimes.put(Constants.G_TSEARCH + search, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_TSEARCH + search), HttpStatus.OK);

	}

	@GetMapping(value = "/tDetails")
	public ResponseEntity<String> getTeamDetails(String tId, String type) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.G_TDETAIL + tId + type, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.G_TDETAIL + tId + type), HttpStatus.OK);

		} else {

			String url = Constants.GURU_BASEURL + "/api/v1/team/" + tId + "/matches?matchTab=" + type;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getGuruHeaders());

			responses.put(Constants.G_TDETAIL + tId + type, response);
			refreshTimes.put(Constants.G_TDETAIL + tId + type, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.G_TDETAIL + tId + type), HttpStatus.OK);

	}

	public static void clearResponsesMap() {

		responses.clear();
		refreshTimes.clear();
	}
}
