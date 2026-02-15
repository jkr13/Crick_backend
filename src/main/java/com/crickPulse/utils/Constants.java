package com.crickPulse.utils;

import org.json.JSONObject;

public class Constants {

	public static JSONObject teamObject = new JSONObject();
	public static JSONObject seriesObject = new JSONObject();
	public static JSONObject playerObject = new JSONObject();
	public static JSONObject SeriesFormat = new JSONObject();
	public static JSONObject venueObject = new JSONObject();
	public static JSONObject umpireObject = new JSONObject();

	public static final boolean FALSE = false;
	public static final boolean TRUE = true;

	public static final String UPLOAD_PATH = "/var/www/html/java-template-images/";

	public static final String BASE_URL = "http://localhost/java-template-images/";

	public static final String PROFILE_PREFIX = "user_";

	public static final String CLIENT_ID_ANDROID = "<android client Id>";

	public static final String CLIENT_ID_IOS = "<iOs client Id>";

	public static final String INVALID_DATA = "Invalid data";

	public static final String EMPTY = "";
	public static final int ZERO = 0;

	public static final int DAYS_IN_MONTH = 30;
	public static final int SEC = 1000;
	public static final int MIN = 60 * SEC;
	public static final int HOUR = 60 * MIN;
	public static final long DAY = 24 * HOUR;
	public static final long WEEK = 7 * DAY;
	public static final long MONTH = DAYS_IN_MONTH * DAY;

	public static final int TWEET_COUNT = 10;

//	public static final List<String> TWITTER_IDS = Arrays.asList("117407834", "989137039", "139876086", "121044171",
//			"358814014", "1288050427", "70179948", "121828529", "92945681", "171117515", "78941611", "196235064",
//			"730375072876105728", "92424604", "350224247", "45452226", "71201743", "127765218", "451215777",
//			"1476438846988427265", "4824087681", "176888549", "23592970", "30631766", "17082958", "70931004",
//			"106345557", "2708915372", "135421739", "185142711", "468471298", "356893311", "368186169", "1469065189",
//			"1614696542", "858720042938249216", "59808841", "447977892", "743735095308099585", "3133996860",
//			"959840104440385536", "984043546155069441", "756532891564158980", "807775550483763200", "321866313",
//			"1359277818", "2949561072", "759385549069164546", "120785508", "2855255702", "1098141150144356352",
//			"428234009", "1026332905", "15639696", "2816733930", "2526480081", "726044995933732866", "138002210");

//	public static final List<String> TWITTER_IDS = Arrays.asList("117407834", "989137039", "139876086", "121044171",
//			"358814014", "1288050427", "70179948", "121828529", "92945681", "171117515", "78941611", "196235064",
//			"730375072876105728", "92424604", "350224247", "45452226", "71201743", "127765218", "451215777",
//			"1476438846988427265", "4824087681", "176888549", "23592970", "30631766", "17082958", "70931004",
//			"106345557", "2708915372", "135421739", "185142711", "468471298", "356893311", "368186169", "1469065189",
//			"1614696542", "858720042938249216", "59808841", "447977892", "743735095308099585", "3133996860",
//			"959840104440385536", "984043546155069441", "756532891564158980", "807775550483763200", "321866313",
//			"1359277818", "2949561072", "759385549069164546", "120785508", "2855255702", "1098141150144356352",
//			"428234009", "1026332905", "15639696", "2816733930", "2526480081", "138002210", "3017241909", "273557471",
//			"45022448", "204002663", "187890010", "263800151", "248883264", "18673041", "4663241788", "343512172",
//			"271292659", "135180139", "168606635", "378460963", "179036286", "239411196", "350334760", "177838266",
//			"316577087", "4682691708", "3247896487", "560641368", "1900356463", "732245645898063872");

	// Constants for ICC World cup
	public static final String ICC_BASEURL = "https://cricketapi-icc.pulselive.com";
	public static final String ICC_COMM_BASEURL = "https://api.icc.cdp.pulselive.com";
	public static final String ICC_VENUE_BASEURL = "https://content-icc.pulselive.com";
	public static final String FIXTURE = "fixture";
	public static final String RANKING = "ranking";
	public static final String PLAYER_RANKING = "pranking";
	public static final String STANDING = "standing";
	public static final String COMM = "commentry";
	public static final String SC = "scorecard";
	public static final String SQ = "squad";
	public static final String TSTATS = "tournamentstats";
	public static final String VENUE = "venue";
	public static final String STATS = "stats";
	public static final String ALL_FIXTURE = "allfixture";
	public static final String HOME = "home";

	// Constants for Mazza
	public static final String MAZZA_BASEURL = "https://applive.cricketbox.in/games.svc";
	public static final String M_INFO = "Minfo";
	public static final String M_SQUAD = "Msquad";
	public static final String M_SC = "Mscorecard";
	public static final String M_MOM = "Mmom";
	public static final String M_HISTORY = "Mhistory";
	public static final String M_SERIES = "Mseries";
	public static final String M_RANKINGS = "Mrankings";
	public static final String M_FIXTURES = "Mfixtures";
	public static final String M_HOME = "Mhome";

	// Constants for Mazza
	public static final String GURU_BASEURL = "https://api.commentaryapi.com";
	public static final String G_INFO = "Ginfo";
	public static final String G_SQUAD = "Gsquad";
	public static final String G_SC = "Gscorecard";
	public static final String G_SERIES = "Gseries";
	public static final String G_SERIES_MATCHES = "GseriesMatches";
	public static final String G_SERIES_OVERVIEW = "GseriesOverView";
	public static final String G_SERIES_POINTTABLE = "GseriesTables";
	public static final String G_SERIES_TEAM = "GseriesTeam";
	public static final String G_STATS_OPTIONS = "GstatsOptions";
	public static final String G_STATS = "Gstats";
	public static final String G_Venue = "Gvenue";
	public static final String G_BALL = "GBALL";
	public static final String G_RANKINGS = "Grankings";
	public static final String G_FIXTURES = "Gfixtures";
	public static final String G_HOME = "GHome";
	public static final String G_PDETAIL = "GplayerDetail";
	public static final String G_PSEARCH = "GplayerSearch";
	public static final String G_TSEARCH = "GTeamSearch";
	public static final String G_TDETAIL = "GTeamDetails";

	// Constants for IPL
	public static final String IPL_BASEURL = "https://apiipl.iplt20.com/api/v1";
	public static final String IPL_FEEDURL = "https://url3.iplt20.com/ipl/feeds";
	public static final String IPL_POLLURL = "https://polls.iplt20.com";
	public static final String IPL_STATURL = "https://ipl-stats-sports-mechanic.s3.ap-south-1.amazonaws.com";
	public static final String I_Series = "Iseries";
	public static final String I_Teams = "Iteams";
	public static final String I_Standings = "Istandings";
	public static final String I_entity = "Ientity";
	public static final String I_Stats = "IStats";
	public static final String I_TStats = "ITStats";
	public static final String I_Squad = "ISquad";
	public static final String I_Schdule = "ISchdule";
	public static final String I_Summary = "ISummary";
	public static final String I_Innings = "IInnings";
	public static final String I_Player = "IPlayer";
	public static final String I_Master = "IMaster";
	public static final String I_MStats = "IMStats";
	public static final String I_MScore = "IMSscore";

	// Constants for stats
	public static final String MOST_RUNS = "mostRuns";
	public static final String MOST_WICKETS = "mostWickets";
	public static final String MOST_SIXES = "mostSixes";

	// Constants for Crick
	public static final String CRICK_MAP = "https://storage.googleapis.com";
	public static final String CRICK_BASEURL = "https://crickapi.com";
	public static final String CRICK_LIVEURL = "https://api-v1.com/v10";
	public static final String C_FIXTURE = "CFixture";
	public static final String C_INFO = "CInfo";
	public static final String C_SCORECARD = "CScorecard";
	public static final String C_HOME_SERIES = "CHomeSeries";
	public static final String C_SERIES_OVERVIEW = "CSerieOveview";
	public static final String C_LIVE_MATCHES = "CLiveMatches";
	public static final String C_LIVE_Line = "CLiveLine";
	public static final String C_TEAM = "CTEAM";
	public static final String C_UMPIRE = "CUmpire";
	public static final String C_VENUE = "CVenue";
	public static final String C_SERIES = "CSeries";
	public static final String C_SERIES_FT = "CSeriesFT";
	public static final String C_PLAYER = "CPlyaer";

	public static final int EXPIRE_MIN = 2;

	// Constants for Cricket Com
	public static final String CRICKET_SCH = "CricketSch";
	public static final String CRICKET_COM_BASEURL = "https://apiv2.cricket.com/cricket";
	public static final String CRICKET_COMMENTARY = "CricketCommentary";
	public static final String CRICKET_RUNS = "CricketRuns";
	public static final String CRICKET_M_STATS = "CricketMStats";
	public static final String CRICKET_FORM = "CricketForm";
	public static final String CRICKET_PHASE = "CricketPhase";
	public static final String CRICKET_HIGHLIGHTS = "CricketHighlights";

	public static final String NEWS = "News";
	public static final String LIVE_SPORT_NEWS = "https://api.livesportz24.com/";

}
