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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crickPulse.apicaller.WebCaller;
import com.crickPulse.utils.Constants;
import com.crickPulse.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/crick")
@Api(value = "Crick Controller", description = "REST API for Crick", tags = { "Crick Controller" })
public class CrickController {

	public static final Map<String, String> responses = new HashMap<String, String>();
	private static HashMap<String, Long> refreshTimes = new HashMap<String, Long>();

	@GetMapping(value = "/teams")
	public static ResponseEntity<String> getAllTeam() throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_TEAM, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.C_TEAM), HttpStatus.OK);

		} else {

			String url = Constants.CRICK_MAP + "/cemap/team.json";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, null);

			JSONArray teamArray = new JSONArray(response);
			JSONObject newTeamObject = new JSONObject();

			for (int i = 0; i < teamArray.length(); i++) {
				JSONObject obj = teamArray.getJSONObject(i);
				newTeamObject.put(obj.optString("f_key"), obj);
			}

			Constants.teamObject = newTeamObject;

			responses.put(Constants.C_TEAM, newTeamObject.toString());
			refreshTimes.put(Constants.C_TEAM, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.C_TEAM), HttpStatus.OK);

	}

	@GetMapping(value = "/umpire")
	public static ResponseEntity<String> getAllUmpire() throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_UMPIRE, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.C_UMPIRE), HttpStatus.OK);

		} else {

			String url = Constants.CRICK_MAP + "/cemap/umpire.json";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, null);

			JSONArray teamArray = new JSONArray(response);
			JSONObject newTeamObject = new JSONObject();

			for (int i = 0; i < teamArray.length(); i++) {
				JSONObject obj = teamArray.getJSONObject(i);
				newTeamObject.put(obj.optString("f_key"), obj);
			}

			Constants.umpireObject = newTeamObject;

			responses.put(Constants.C_UMPIRE, newTeamObject.toString());
			refreshTimes.put(Constants.C_UMPIRE, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.C_UMPIRE), HttpStatus.OK);

	}

	@GetMapping(value = "/venue")
	public static ResponseEntity<String> getAllVenue() throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_VENUE, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.C_VENUE), HttpStatus.OK);

		} else {

			String url = Constants.CRICK_MAP + "/cemap/venue.json";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, null);

			JSONArray teamArray = new JSONArray(response);
			JSONObject newTeamObject = new JSONObject();

			for (int i = 0; i < teamArray.length(); i++) {
				JSONObject obj = teamArray.getJSONObject(i);
				newTeamObject.put(obj.optString("f_key"), obj);
			}

			Constants.venueObject = newTeamObject;

			responses.put(Constants.C_VENUE, newTeamObject.toString());
			refreshTimes.put(Constants.C_VENUE, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.C_VENUE), HttpStatus.OK);

	}

	@GetMapping(value = "/series")
	public static ResponseEntity<String> getAllSeries() throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_SERIES, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.C_SERIES), HttpStatus.OK);

		} else {

			String url = Constants.CRICK_MAP + "/cemap/series.json";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, null);

			JSONArray seriesArray = new JSONArray(response);
			JSONObject newSeriesObject = new JSONObject();

			for (int i = 0; i < seriesArray.length(); i++) {
				JSONObject obj = seriesArray.getJSONObject(i);
				newSeriesObject.put(obj.optString("f_key"), obj);
			}

			Constants.seriesObject = newSeriesObject;

			responses.put(Constants.C_SERIES, newSeriesObject.toString());
			refreshTimes.put(Constants.C_SERIES, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.C_SERIES), HttpStatus.OK);

	}

	@GetMapping(value = "/seriesFormat")
	public static ResponseEntity<String> getAllSeriesFormat() throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_SERIES_FT, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.C_SERIES_FT), HttpStatus.OK);

		} else {

			String url = Constants.CRICK_BASEURL + "/venue/getSeriesFormat";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getCEHeaders());

			JSONArray formatArray = new JSONArray(response);
			JSONObject formatObject = new JSONObject();

			for (int i = 0; i < formatArray.length(); i++) {
				JSONObject obj = formatArray.getJSONObject(i);
				formatObject.put(obj.optString("stid"), obj);
			}

			Constants.SeriesFormat = formatObject;

			responses.put(Constants.C_SERIES_FT, formatObject.toString());
			refreshTimes.put(Constants.C_SERIES_FT, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.C_SERIES_FT), HttpStatus.OK);

	}

	@GetMapping(value = "/players")
	public static ResponseEntity<String> getAllPlayers() throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_PLAYER, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.HOUR) {

			return new ResponseEntity<String>(responses.get(Constants.C_PLAYER), HttpStatus.OK);

		} else {

			String url = Constants.CRICK_MAP + "/cemap/player.json";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, null);

			JSONArray pArray = new JSONArray(response);
			JSONObject newpObject = new JSONObject();

			for (int i = 0; i < pArray.length(); i++) {
				JSONObject obj = pArray.getJSONObject(i);
				newpObject.put(obj.optString("f_key"), obj);
			}

			Constants.playerObject = newpObject;

			responses.put(Constants.C_PLAYER, newpObject.toString());
			refreshTimes.put(Constants.C_PLAYER, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.C_PLAYER), HttpStatus.OK);

	}

	@RequestMapping(value = "/fixture", method = { RequestMethod.POST })
	public ResponseEntity<String> getAllSeason(int type, int wise, int page, String lang)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_FIXTURE + page + type + wise + lang, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.C_FIXTURE + page + type + wise + lang),
					HttpStatus.OK);

		} else {

			String url = Constants.CRICK_BASEURL + "/fixture/getFixture";

			JSONObject gameInfo = new JSONObject();
			gameInfo.put("type", type);
			gameInfo.put("wise", wise);
			gameInfo.put("page", page);
			gameInfo.put("lang", lang);

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, gameInfo.toString(), Utils.getCEHeaders());

			JSONArray matches = new JSONArray(response);
			JSONArray newResponse = new JSONArray();

			for (int i = 0; i < matches.length(); i++) {

				JSONObject oMatchDetails = matches.getJSONObject(i);
				JSONObject matchDetails = Utils.parseMatchDetails(oMatchDetails);
				newResponse.put(matchDetails);

			}

			responses.put(Constants.C_FIXTURE + page + type + wise + lang, newResponse.toString());
			refreshTimes.put(Constants.C_FIXTURE + page + type + wise + lang, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.C_FIXTURE + page + type + wise + lang),
				HttpStatus.OK);

	}

	@RequestMapping(value = "/info", method = { RequestMethod.POST })
	public ResponseEntity<String> getInfo(String key) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_INFO + key, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.C_INFO + key), HttpStatus.OK);

		} else {

			String url = Constants.CRICK_LIVEURL + "/iV4.php?key=" + key;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getCEHeaders());

			if (!response.equalsIgnoreCase("null")) {

				JSONObject info = new JSONObject(response);
				JSONObject newResponse = new JSONObject();

				JSONObject venueDetail = info.optJSONObject("vsp");
				JSONObject weatherDetails = info.optJSONObject("wU");
				JSONObject wkDt = info.optJSONObject("bs");

				JSONObject venue = new JSONObject();
				JSONObject weather = new JSONObject();

				String[] teams = info.optString("t").split("-");
				String[] h2h = info.optString("hth").split("-");

				JSONObject h2hObject = new JSONObject();
				h2hObject.put("team1", h2h[0].split(":")[1]);
				h2hObject.put("team2", h2h[1].split(":")[1]);

				newResponse.put("date", info.optString("dt"));
				newResponse.put("series", Utils.getSeriesObj(info.optString("s")));

				newResponse.put("h2h", h2hObject);
				newResponse.put("team1", Utils.getTeamObj(teams[0]));
				newResponse.put("team2", Utils.getTeamObj(teams[1]));
				newResponse.put("broadCasting", info.optString("b"));
				newResponse.put("ground", Utils.getVenueName(info.optString("v")));

				try {

					venue.put("highest", venueDetail.optString("h"));
					venue.put("highestChase", venueDetail.optString("hc"));
					venue.put("lowest", venueDetail.optString("l"));
					venue.put("lowestDefended", venueDetail.optString("ld"));
					venue.put("avg1", venueDetail.optString("a1"));
					venue.put("avg2", venueDetail.optString("a2"));
					venue.put("totalMatch", venueDetail.optString("tm"));
					venue.put("firstWin", venueDetail.optString("x"));
					venue.put("secondWin", venueDetail.optString("y"));

					weather.put("currentTemp", weatherDetails.optString("crT"));
					weather.put("humidity", weatherDetails.optString("hum"));
					weather.put("minTemp", weatherDetails.optString("mnT"));
					weather.put("maxTemp", weatherDetails.optString("mxT"));
					weather.put("windSpeed", weatherDetails.optString("wS"));
					weather.put("sky", weatherDetails.optString("wC"));
					weather.put("rainChance", weatherDetails.optString("rP"));

					newResponse.put("paceWkt", wkDt.optString("f"));
					newResponse.put("spinWkt", wkDt.optString("s"));

				} catch (Exception e) {

				}

				newResponse.put("venue", venue);
				newResponse.put("weather", weather);

				String team1Form = info.optString("t1f");
				String team2Form = info.optString("t2f");

				JSONArray team1FormArray = Utils.getFormObject(team1Form);
				JSONArray team2FormArray = Utils.getFormObject(team2Form);
				JSONObject formattedForm = Utils.getFormatedForm(info.optString("tf"));

				JSONArray teamPlaying = Utils.parsePlayingTeam(info.optString("tp"));
				JSONArray teamBanch = Utils.parsePlayingTeam(info.optString("tb"));
				JSONArray umpires = Utils.parseUmpire(info.optString("u"));

				newResponse.put("team1Form", team1FormArray);
				newResponse.put("team2Form", team2FormArray);
				newResponse.put("teamForm", formattedForm);
				newResponse.put("teamPlaying", teamPlaying);
				newResponse.put("teamBanch", teamBanch);
				newResponse.put("umpires", umpires);

				responses.put(Constants.C_INFO + key, newResponse.toString());
				refreshTimes.put(Constants.C_INFO + key, currentTime);

			}
		}

		return new ResponseEntity<String>(responses.get(Constants.C_INFO + key), HttpStatus.OK);

	}

	@RequestMapping(value = "/scorecard", method = { RequestMethod.POST })
	public ResponseEntity<String> getScorecard(String key) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_SCORECARD + key, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.C_SCORECARD + key), HttpStatus.OK);

		} else {

			String url = Constants.CRICK_LIVEURL + "/sC4.php?key=" + key;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getCEHeaders());

			JSONArray newResponse = new JSONArray();

			if (!response.isEmpty() && !response.equals("null")) {

				try {
					JSONArray scorecard = new JSONArray(response);

					for (int i = 0; i < scorecard.length(); i++) {
						newResponse.put(Utils.parseScore(scorecard.getJSONObject(i)));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			responses.put(Constants.C_SCORECARD + key, newResponse.toString());
			refreshTimes.put(Constants.C_SCORECARD + key, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.C_SCORECARD + key), HttpStatus.OK);

	}

	@GetMapping(value = "/getSeries")
	public ResponseEntity<String> getSeries() throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_HOME_SERIES, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < (Constants.MIN * 2)) {

			return new ResponseEntity<String>(responses.get(Constants.C_HOME_SERIES), HttpStatus.OK);

		} else {

			String url = Constants.CRICK_BASEURL + "/home/getHomeSeries";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getCEHeaders());

			JSONArray array = new JSONArray(response);
			JSONArray newResponse = new JSONArray();

			for (int i = 0; i < array.length(); i++) {

				JSONObject jsonObject = array.getJSONObject(i);
				JSONObject seriesObject = Utils.parseCESeries(jsonObject);
				newResponse.put(seriesObject);

			}

			responses.put(Constants.C_HOME_SERIES, newResponse.toString());
			refreshTimes.put(Constants.C_HOME_SERIES, currentTime);

		}
		return new ResponseEntity<String>(responses.get(Constants.C_HOME_SERIES), HttpStatus.OK);

	}

	@RequestMapping(value = "/getSerieOverview", method = { RequestMethod.POST })
	public ResponseEntity<String> getSerieOverview(String key,
			@RequestParam(required = false, defaultValue = "0") int pageNumber,
			@RequestParam(required = false, defaultValue = "10") int pageSize)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_SERIES_OVERVIEW + key + pageNumber + pageSize, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.SEC * 30) {

			return new ResponseEntity<String>(responses.get(Constants.C_SERIES_OVERVIEW + key + pageNumber + pageSize),
					HttpStatus.OK);

		} else {

			String url = Constants.CRICK_BASEURL + "/series/getSeriesOverview";

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("sf", key);
			jsonObject.put("lang", "en");

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, jsonObject.toString(),
					Utils.getCEHeaders());

			JSONObject seriesObject = new JSONObject(response);
			JSONObject newResponse = Utils.parseSeriesOverview(seriesObject);

			// ALL-NEWS
			String newsUrl = Constants.LIVE_SPORT_NEWS + "news/all?pageNumber=" + pageNumber + "&pageSize=" + pageSize
					+ "&apptype=CRICKET";
			String resultNews = WebCaller.getResult(newsUrl, WebCaller.HTTP_GET, null, null);

			// Cricket - Story
			String cricketStoryUrl = Constants.LIVE_SPORT_NEWS + "story?pageNumber=" + pageNumber + 1 + "&pageSize="
					+ pageSize;
			String resultCricketStory = WebCaller.getResult(cricketStoryUrl, WebCaller.HTTP_GET, null, null);

			newResponse.put("stories", new JSONObject(resultCricketStory));
			newResponse.put("news", new JSONObject(resultNews));

			responses.put(Constants.C_SERIES_OVERVIEW + key + pageNumber + pageSize, newResponse.toString());
			refreshTimes.put(Constants.C_SERIES_OVERVIEW + key + pageNumber + pageSize, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.C_SERIES_OVERVIEW + key + pageNumber + pageSize),
				HttpStatus.OK);

	}

	@GetMapping(value = "/getLiveMatches")
	public ResponseEntity<String> getLiveMatches() throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_LIVE_MATCHES, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.SEC * 30) {

			return new ResponseEntity<String>(responses.get(Constants.C_LIVE_MATCHES), HttpStatus.OK);

		} else {

			String url = Constants.CRICK_LIVEURL + "/liveMatches2.php";

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getCEHeaders());

			JSONObject jsonObject = new JSONObject(response);

			JSONArray newResponse = Utils.parseLiveMatches(jsonObject);

			responses.put(Constants.C_LIVE_MATCHES, newResponse.toString());
			refreshTimes.put(Constants.C_LIVE_MATCHES, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.C_LIVE_MATCHES), HttpStatus.OK);

	}

	@RequestMapping(value = "/getLiveLine", method = { RequestMethod.POST })
	public ResponseEntity<String> getLiveLine(String key) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_LIVE_Line + key, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.SEC * 15) {

			return new ResponseEntity<String>(responses.get(Constants.C_LIVE_Line + key), HttpStatus.OK);

		} else {

			String url = Constants.CRICK_LIVEURL + "/sV3.php?key=" + key;

			String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getCEHeaders());

			JSONObject newResponse = new JSONObject();

			if (response.contains("{")) {
				JSONObject jsonObject = new JSONObject(response);

				newResponse = Utils.parseLiveLine(jsonObject);

			}

			responses.put(Constants.C_LIVE_Line + key, newResponse.toString());
			refreshTimes.put(Constants.C_LIVE_Line + key, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.C_LIVE_Line + key), HttpStatus.OK);

	}

	@RequestMapping(value = "/getStats", method = { RequestMethod.POST })
	public ResponseEntity<String> getStats(String key, String formatId, String type)
			throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_LIVE_Line + key + formatId + type, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.C_LIVE_Line + key + formatId + type),
					HttpStatus.OK);

		} else {

			String url = Constants.CRICK_BASEURL + "/series/getPlayerStatsForSeriesID";

			JSONObject params = new JSONObject();
			params.put("type", type);
			params.put("page", "0");
			params.put("limit", "50");
			params.put("sf", key);
			params.put("ftid", formatId);
			params.put("fkey", key);
			params.put("format_type_id", formatId);
			params.put("record_type", 1);

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, params.toString(), Utils.getCEHeaders());

			JSONObject jsonObject = new JSONObject(response);

			JSONArray newResponse = Utils.parseDetailedStats(jsonObject);

			responses.put(Constants.C_LIVE_Line + key + formatId + type, newResponse.toString());
			refreshTimes.put(Constants.C_LIVE_Line + key + formatId + type, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.C_LIVE_Line + key + formatId + type), HttpStatus.OK);

	}

	@RequestMapping(value = "/getMatches", method = { RequestMethod.POST })
	public ResponseEntity<String> getMatches(String key) throws JsonProcessingException, IOException {

		Long lastRefreshTime = refreshTimes.getOrDefault(Constants.C_LIVE_Line + key, 0L);

		Long currentTime = new Date().getTime();

		if (currentTime - lastRefreshTime < Constants.MIN) {

			return new ResponseEntity<String>(responses.get(Constants.C_LIVE_Line + key), HttpStatus.OK);

		} else {

			String url = Constants.CRICK_BASEURL + "/series/getMatchesForSeriesID";

			JSONObject params = new JSONObject();
			params.put("sf", key);
			params.put("fkey", key);
			params.put("lang", "en");

			String response = WebCaller.getResult(url, WebCaller.HTTP_POST, params.toString(), Utils.getCEHeaders());

			JSONArray jsonObject = new JSONArray(response);

			JSONArray newResponse = Utils.parseSeriesMatches(jsonObject);

			responses.put(Constants.C_LIVE_Line + key, newResponse.toString());
			refreshTimes.put(Constants.C_LIVE_Line + key, currentTime);

		}

		return new ResponseEntity<String>(responses.get(Constants.C_LIVE_Line + key), HttpStatus.OK);

	}

	public static void clearResponsesMap() {

		responses.clear();
		refreshTimes.clear();
	}
}
