package com.crickPulse.utils;

import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.crickPulse.enums.Format;
import com.crickPulse.enums.Status;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Utils {

	static DecimalFormat format = new DecimalFormat("0.00");

	private static final String ALGORITHM = "AES";
	private static final String MODE = "AES/ECB/PKCS5Padding";
	private static final String KEY = "thiskey11thiskey11thiskey11thiskey11thiskey11";

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * This method use to get salt for create hash password
	 * 
	 * @return salt
	 * @throws NoSuchAlgorithmException
	 */
//	public static String getSalt() throws NoSuchAlgorithmException {
//		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//		byte[] salt = new byte[16];
//		sr.nextBytes(salt);
//		return Arrays.toString(salt);
//	}

	/**
	 * Get password hash
	 * 
	 * @param password Plain text password
	 * @param secret   Secret key
	 * @param salt     Random salt
	 * @return Password hash.
	 */
//	public static String getPasswordHash(String password, String salt) {
//
//		try {
//
//			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
//			SecretKey tmp = factory.generateSecret(spec);
//			return Base64.getEncoder().encodeToString(tmp.getEncoded());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}

	/**
	 * This method is used to validate given password is correct or not.
	 * 
	 * @param newPassword     new password from user.
	 * @param oldHashPassword password stored in db.
	 * @param salt            String
	 * @return true or false.
	 */
//	public static boolean isPasswordCorrect(String newPassword, String oldHashPassword, String salt) {
//		return oldHashPassword.equals(getPasswordHash(newPassword, salt));
//	}

	/**
	 * This method use to generate a random AlphaNumeric String using Math.random()
	 * method
	 * 
	 * @param number input number(like 500, 123, 780)
	 * @return random String
	 */
//	public static String getRandomString(int number) {
//
//		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
//
//		StringBuilder sb = new StringBuilder(number);
//
//		for (int i = 0; i < number; i++) {
//
//			int index = (int) (AlphaNumericString.length() * Math.random());
//
//			sb.append(AlphaNumericString.charAt(index));
//		}
//
//		return sb.toString();
//	}

//	public static String saveFile(MultipartFile file, String title) throws IOException {
//
//		String path = Constants.UPLOAD_PATH + title + new Date().getTime() + "."
//				+ FilenameUtils.getExtension(file.getOriginalFilename());
//		;
//
//		String storedFilePath = Constants.BASE_URL;
//
//		File convFile = new File(path);
//
//		convFile.createNewFile();
//
//		FileOutputStream fos = new FileOutputStream(convFile);
//
//		fos.write(file.getBytes());
//
//		fos.close();
//
//		storedFilePath = storedFilePath + convFile.getName();
//
//		return storedFilePath;
//	}

//	public static JSONObject getTwitterResult(String url, String userId, int count, String encodedFeature,
//			Map<String, String> headers) {
//
//		JSONObject response = new JSONObject();
//
//		JSONObject variableJson = new JSONObject();
//		variableJson.put("userId", userId);
//		variableJson.put("count", count);
//		variableJson.put("includePromotedContent", true);
//		variableJson.put("withQuickPromoteEligibilityTweetFields", true);
//		variableJson.put("withVoice", true);
//		variableJson.put("withV2Timeline", true);
//
//		String encodedVariable = urlEncode(variableJson.toString());
//
//		url += "?variables=" + encodedVariable + "&features=" + encodedFeature;
//
//		String result = WebCaller.getResult(url, WebCaller.HTTP_GET, null, headers);
//
//		if (!result.contains("Rate limit exceeded") && result.contains("{")) {
//			response = new JSONObject(result);
//		}
//
//		return response;
//	}
//
//	public static String urlEncode(String s) {
//		try {
//			return URLEncoder.encode(s, StandardCharsets.UTF_8.name());
//		} catch (UnsupportedEncodingException e) {
//			// UTF-8 being unsuppored is unlikely
//			// Replace with a unchecked exception to tidy up exception handling
//			throw new RuntimeException(StandardCharsets.UTF_8.name() + " is unsupported", e);
//		}
//	}

	/**
	 * This method used to generate a nonce.
	 * 
	 * @param key secret key
	 * @param ts  time stamp.
	 * @return valid nonce.
	 */
//	public static String generateNonce(String key, String ts) {
//
//		return key + Base64.getEncoder().encodeToString((ts).getBytes());
//	}

	/**
	 * @param key encoded key
	 * @return {@link PublicKey}
	 * @throws Exception
	 */
//	public static ECPublicKey loadPublicKey(String key) throws Exception {
//		BigInteger[] points = getPoints(key);
//		BigInteger a = points[0];
//		BigInteger b = points[1];
//		return getPublicKeyFromXAndY(a, b);
//	}

	/**
	 * This method returns the a,b BigInteger from base64 string
	 * 
	 * @param key encoded string
	 * @return Array of {@link BigInteger}
	 */
//	public static BigInteger[] getPoints(String key) {
//
//		BigInteger[] points = new BigInteger[2];
//
//		byte[] decodedKeyBytes = Base64.getDecoder().decode(key);
//
//		byte aLength = decodedKeyBytes[1];
//		byte[] aArray = Arrays.copyOfRange(decodedKeyBytes, 2, 2 + aLength);
//		byte blength = decodedKeyBytes[2 + aLength];
//		byte[] bArray = Arrays.copyOfRange(decodedKeyBytes, 2 + aLength + 1, 2 + aLength + 1 + blength);
//
//		BigInteger a = new BigInteger(aArray);
//		BigInteger b = new BigInteger(bArray);
//
//		points[0] = a;
//		points[1] = b;
//
//		return points;
//	}

//	public static String decodeBase64(String encodedValue) {
//		byte[] decodedBytes = Base64.getDecoder().decode(encodedValue);
//		return new String(decodedBytes, StandardCharsets.UTF_8);
//	}

	/**
	 * Get the Public Key from x and y co-ordinates
	 * 
	 * @param x x co-ordinate
	 * @param y y co-ordinate
	 * @return {@link PublicKey}
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
//	public static ECPublicKey getPublicKeyFromXAndY(BigInteger x, BigInteger y)
//			throws InvalidKeySpecException, NoSuchAlgorithmException {
//
//		KeyFactory kf = KeyFactory.getInstance("EC", new BouncyCastleProvider());
//
//		X9ECParameters params = ECNamedCurveTable.getByName("secp256k1");
//		java.security.spec.ECPoint point = new java.security.spec.ECPoint(x, y);
//		java.security.spec.ECParameterSpec params2 = EC5Util.convertToSpec(params);
//		java.security.spec.ECPublicKeySpec keySpec = new java.security.spec.ECPublicKeySpec(point, params2);
//		return (ECPublicKey) kf.generatePublic(keySpec);
//
//	}

	/**
	 * @param nonce
	 * @param pubKey
	 * @param r
	 * @param s
	 * @return
	 * @throws Exception
	 */
//	public static boolean performValidSignature(String nonce, ECPublicKey pubKey, BigInteger r, BigInteger s)
//			throws Exception {
//
//		ASN1Integer asn1R = new ASN1Integer(r);
//		ASN1Integer asn1S = new ASN1Integer(s);
//
//		DERSequence seq = new DERSequence(new ASN1Integer[] { asn1R, asn1S });
//		byte[] signatureToVerify2 = seq.getEncoded();
//
//		Signature signature = Signature.getInstance("SHA256withECDSA");
//		signature.initVerify(pubKey);
//		signature.update(nonce.getBytes("UTF-8"));
//
//		return signature.verify(signatureToVerify2);
//
//	}

	public static Map<String, String> getHeaders() {

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("User-Agent", "ICC(Android) / 93906341");
		headers.put("Account", "icc");
		return headers;
	}

	public static Map<String, String> getMazzaHeaders() {

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("User-Agent", "CMm0B!LE");
		headers.put("Authorization", "Basic Q01hemFhOmNNYXphQQ==");
		headers.put("Content-Type", "application/json");
		return headers;
	}

	public static Map<String, String> getIPLHeaders() {

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Access-Auth-Token", "QWmMTXMuL3I5AX4eYhRrK5rUrxZnY4br");
		return headers;
	}

	public static Map<String, String> getCEHeaders() {

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", createJWT());
		headers.put("Content-Type", "application/json");
		return headers;
	}

	public static Map<String, String> getGuruHeaders() {

		String ts = Long.toString(new Date().getTime());

		StringBuilder sb2 = new StringBuilder();

		sb2.append("2f:e1:7a:60:71:b4:38:41:7e:ef:e5:43:5f:1b:e1:5a:02:57:3a:00");
		sb2.append(ts);

		String sb3 = sb2.toString();

		Map<String, String> headers = new HashMap<String, String>();

		StringBuffer stringBuffer;

		try {

			MessageDigest instance = MessageDigest.getInstance("MD5");
			byte[] bytes = sb3.getBytes(Charset.forName("utf-8"));
			instance.update(bytes);
			byte[] digest = instance.digest();

			stringBuffer = new StringBuffer();

			for (byte b10 : digest) {
				stringBuffer.append(String.format("%02x", b10));
			}

			String ks = stringBuffer.toString();

			headers.put("platform", "2");
			headers.put("Authorization", "Basic Y3JpYzM2MGRldmxpdmU6Y0g0YkhzZ3hubkNoODVKclVnOGo=");
			headers.put("version", "18.5");
			headers.put("vn", "185");
			headers.put("ts", ts);
			headers.put("ks", ks);
			headers.put("User-Agent", "PostmanRuntime/7.29.4");

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return headers;
	}

	/**
	 * Parsing team form
	 * 
	 * @param team1Form
	 * @return
	 */
	public static JSONArray getFormObject(String team1Form) {

		JSONArray array = new JSONArray();

		String[] matches = team1Form.split("\\|");

		for (String match : matches) {

			try {
				JSONObject matchObject = new JSONObject();

				String[] firstSplit = match.split("-");

				String[] team1Score = firstSplit[0].split("\\^");
				String[] team2Score = firstSplit[1].split("\\^");

				matchObject.put("t1score", team1Score[0]);
				matchObject.put("t1over", team1Score[1]);
				matchObject.put("t2score", team2Score[0]);
				matchObject.put("t2over", team2Score[1]);

				String extraDetails = firstSplit[2];

				String[] extraSplits = extraDetails.split("&");

				matchObject.put("team1", getTeamObj(extraSplits[4]));
				matchObject.put("team2", getTeamObj(extraSplits[5]));
				matchObject.put("matchNumber", extraSplits[0]);
				matchObject.put("series", getSeriesObj(extraSplits[3]));
				matchObject.put("matchKey", extraSplits[2]);

				array.put(matchObject);

			} catch (Exception e) {

			}

		}

		return array;
	}

	public static JSONObject getFormatedForm(String formString) {

		JSONObject formObject = new JSONObject();

		String[] teamForm = formString.split("-");

		String team1Form = teamForm.length > 0 ? teamForm[0] : "";
		String team2Form = teamForm.length > 1 ? teamForm[1] : "";

		JSONArray team1Array = parseForm(team1Form);
		JSONArray team2Array = parseForm(team2Form);

		formObject.put("team1Form", team1Array);
		formObject.put("team2Form", team2Array);

		return formObject;
	}

	public static JSONArray parseForm(String teamForm) {

		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < teamForm.length(); i++) {

			if (i % 2 == 0) {

				Character c = teamForm.charAt(i);

				int x = Integer.parseInt(String.valueOf(teamForm.charAt(i + 1)));

				for (int y = 0; y < x; y++) {
					jsonArray.put(String.valueOf(c));
				}
			}

		}

		return jsonArray;
	}

	/**
	 * Parsing the team score
	 * 
	 * @param firstInning
	 * @return
	 */
	public static JSONObject parseScore(JSONObject firstInning) {

		JSONObject jsonObject = new JSONObject();

		JSONArray batsman = firstInning.optJSONArray("b");
		JSONArray newBatsman = new JSONArray();

		JSONArray bowler = firstInning.optJSONArray("a");
		JSONArray newBowler = new JSONArray();

		JSONArray partnership = firstInning.optJSONArray("p");
		JSONArray newPartnerShip = new JSONArray();

		String extraString = firstInning.optString("e");

		try {
			for (int i = 0; i < batsman.length(); i++) {
				String battingscore = batsman.getString(i);
				JSONObject battingObject = parseBattingString(battingscore);
				newBatsman.put(battingObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			for (int i = 0; i < bowler.length(); i++) {
				String bowlingScore = bowler.getString(i);
				JSONObject bowlingObject = parseBowlingString(bowlingScore);
				newBowler.put(bowlingObject);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			for (int i = 0; i < partnership.length(); i++) {
				String parnerShip = partnership.getString(i);
				JSONObject parnerObject = parsePartnerString(parnerShip);
				newPartnerShip.put(parnerObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONObject extras = parseExtraString(extraString);

		jsonObject.put("better", newBatsman);
		jsonObject.put("bowler", newBowler);
		jsonObject.put("parnership", newPartnerShip);
		jsonObject.put("score", firstInning.optString("d"));
		jsonObject.put("team", getTeamObj(firstInning.optString("c")));
		jsonObject.put("extras", extras);

		return jsonObject;
	}

	private static JSONObject parseExtraString(String extraString) {

		String[] partnerShipStats = extraString.split("\\.");

		JSONObject extraJson = new JSONObject();

		extraJson.put("b", partnerShipStats[0]);
		extraJson.put("lb", partnerShipStats[1]);
		extraJson.put("wd", partnerShipStats[2]);
		extraJson.put("nb", partnerShipStats[3]);
		extraJson.put("p", partnerShipStats[4]);

		return extraJson;
	}

	private static JSONObject parsePartnerString(String partnership) {

		String[] partnerShipStats = partnership.split("\\.");
		JSONObject partnerObject = new JSONObject();

		try {
			partnerObject.put("p1Key", getPlayerName(partnerShipStats[0]));
			partnerObject.put("p1run", partnerShipStats[1]);
			partnerObject.put("p1ball", partnerShipStats[2]);
			partnerObject.put("p2Key", getPlayerName(partnerShipStats[3]));
			partnerObject.put("p2run", partnerShipStats[4]);
			partnerObject.put("p2ball", partnerShipStats[5]);
			partnerObject.put("trun", partnerShipStats[6]);
			partnerObject.put("tball", partnerShipStats[7]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return partnerObject;
	}

	private static JSONObject parseBowlingString(String bowlingScore) {

		String[] bowlingStats = bowlingScore.split("\\.");
		JSONObject bowlingObject = new JSONObject();

		bowlingObject.put("pKey", getPlayerName(bowlingStats[0]));
		bowlingObject.put("run", bowlingStats[1]);
		bowlingObject.put("ball", bowlingStats[2]);
		bowlingObject.put("maiden", bowlingStats[3]);
		bowlingObject.put("wicket", bowlingStats[4]);

		return bowlingObject;
	}

	private static JSONObject parseBattingString(String battingscore) {

		String[] scoreSplits = battingscore.split("\\/");
		String[] battingStats = scoreSplits[0].split("\\.");
		String[] infos = scoreSplits[1].split("-");

		JSONObject battingObject = new JSONObject();
		battingObject.put("pKey", getPlayerName(battingStats[0]));
		try {
			battingObject.put("avg", infos.length > 0 ? infos[0] : "0");
			battingObject.put("sr", infos.length > 0 ? infos[1] : "0");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (battingStats.length < 2) {
			return battingObject;
		}

		battingObject.put("run", battingStats[1]);
		battingObject.put("ball", battingStats[2]);
		battingObject.put("four", battingStats[3]);
		battingObject.put("six", battingStats[4]);

		if (battingStats.length > 8) {

			try {
				battingObject.put("typeDismissal", battingStats[7]);
				battingObject.put("bowler", getPlayerName(battingStats[8]));

				if (battingStats.length == 10) {
					battingObject.put("assists", getPlayerName(battingStats[9]));
				}

				battingObject.put("fow", battingStats[6]);
				battingObject.put("fowBall", battingStats[5]);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return battingObject;
	}

	public static JSONObject getTeamObj(String tKey) {

		JSONObject teamObj = Constants.teamObject.optJSONObject(tKey);
		if (teamObj == null) {
			return new JSONObject();
		}

		String id = teamObj.optString("f_key");

		teamObj.remove("cc");
		teamObj.remove("uc");
		teamObj.remove("dc");
		teamObj.put("id", id);
//		teamObj.remove("f_key");

		return teamObj;
	}

	public static JSONObject getSeriesObj(String tKey) {

		JSONObject seriesObj = Constants.seriesObject.optJSONObject(tKey);
		if (seriesObj == null) {
			return new JSONObject();
		}

		String id = seriesObj.optString("f_key");
		seriesObj.remove("tour");
		seriesObj.put("id", id);
//		seriesObj.remove("f_key");

		return seriesObj;
	}

	public static String getVenueName(String tKey) {

		String name = "";
		if (Constants.venueObject.optJSONObject(tKey) != null) {
			name = Constants.venueObject.optJSONObject(tKey).optString("n");
		}
		return name;
	}

	public static String getPlayerName(String tKey) {
		String name = "";
		if (Constants.playerObject.optJSONObject(tKey) != null) {
			name = Constants.playerObject.optJSONObject(tKey).optString("n");
		}

		return name;
	}

	public static String getUmpireName(String tKey) {
		String name = "";
		if (Constants.umpireObject.optJSONObject(tKey) != null) {
			name = Constants.umpireObject.optJSONObject(tKey).optString("n");
		}
		return name;
	}

	public static JSONObject getSeriesFormat(String stId) {
		JSONObject seriesObj = Constants.SeriesFormat.optJSONObject(stId);
		if (seriesObj == null) {
			return new JSONObject();
		}
		return seriesObj;
	}

	public static JSONObject parseSeriesOverview(JSONObject seriesObject) {

		JSONObject responseObject = new JSONObject();

		JSONObject infoObject = seriesObject.optJSONObject("i1");

		JSONObject info = parseInfo(infoObject);
		JSONArray squad = parseSquad(infoObject);
		JSONArray schdule = parseSchdule(seriesObject.optJSONArray("i2"));
		JSONArray table = parseTable(seriesObject.optJSONArray("i3"));
		JSONObject stats = parseStats(seriesObject.optJSONObject("i4"));

		responseObject.put("info", info);
		responseObject.put("squad", squad);
		responseObject.put("matches", schdule);
		responseObject.put("tables", table);
		responseObject.put("stats", stats);

		return responseObject;
	}

	private static JSONObject parseStats(JSONObject stats) {

		JSONObject statObject = new JSONObject();

		JSONObject info = stats.optJSONObject("i");

		try {

			statObject.put("odi", Utils.parseStatDetails(info.optJSONObject("1")));
			statObject.put("t20", Utils.parseStatDetails(info.optJSONObject("2")));
			statObject.put("test", Utils.parseStatDetails(info.optJSONObject("3")));

		} catch (Exception e) {

		}

		return statObject;
	}

	private static JSONObject parseStatDetails(JSONObject jsonObject) {

		JSONObject statDetails = new JSONObject();

		try {

			if (jsonObject == null) {
				return statDetails;
			}

			statDetails.put("mostWicket", formatDetails(jsonObject.optJSONArray("mw"), "mostWicket"));
			statDetails.put("mostRuns", formatDetails(jsonObject.optJSONArray("mr"), "mostRuns"));
			statDetails.put("highScore", formatDetails(jsonObject.optJSONArray("hs"), "highScore"));
			statDetails.put("bestSr", formatDetails(jsonObject.optJSONArray("bsr"), "bestSr"));
			statDetails.put("bowlingFigure", formatDetails(jsonObject.optJSONArray("bf"), "bowlingFigure"));
			statDetails.put("mostSixes", formatDetails(jsonObject.optJSONArray("ms"), "mostSixes"));
			statDetails.put("bestEco", formatDetails(jsonObject.optJSONArray("bec"), "bestEco"));
			statDetails.put("fantsy", formatDetails(jsonObject.optJSONArray("mfp"), "fantsy"));

		} catch (Exception e) {

		}

		return statDetails;
	}

	private static JSONArray formatDetails(JSONArray statArray, String name) {

		for (int i = 0; i < statArray.length(); i++) {

			try {

				JSONObject stat = statArray.optJSONObject(i);
				stat.put("name", name);
				stat.put("team", getTeamObj(stat.optString("tf")));
				stat.put("pname", getPlayerName(stat.optString("pf")));
				stat.put("key", stat.optString("pf"));

				stat.remove("tf");
				stat.remove("pf");

			} catch (Exception e) {

			}
		}

		return statArray;

	}

	private static JSONArray parseTable(JSONArray tableInfo) {

		JSONArray tableArray = new JSONArray();

		for (int i = 0; i < tableInfo.length(); i++) {

			JSONArray tables = new JSONArray();
			JSONObject tableMaster = tableInfo.optJSONObject(i);
			JSONArray iTables = tableMaster.optJSONArray("d");

			for (int y = 0; y < iTables.length(); y++) {

				JSONObject newTable = new JSONObject();
				JSONObject table = iTables.optJSONObject(y);

				JSONArray tableEntries = table.optJSONArray("pt_info");
				JSONArray newTableEntry = new JSONArray();

				for (int z = 0; z < tableEntries.length(); z++) {

					JSONObject oEntry = tableEntries.optJSONObject(z);

					oEntry.put("series", getSeriesObj(oEntry.optString("series_fkey")));
					oEntry.put("team", getTeamObj(oEntry.optString("team_fkey")));
					newTableEntry.put(oEntry);
				}

				newTable.put("name", table.optString("g_name"));
				newTable.put("table", newTableEntry);

				tables.put(newTable);
			}

			tableArray.put(tables);
		}

		return tableArray;
	}

	private static JSONArray parseSchdule(JSONArray matches) {

		JSONArray matchArray = new JSONArray();

		for (int i = 0; i < matches.length(); i++) {

			JSONObject matchdetails = matches.getJSONObject(i);
			matchArray.put(parseMatchDetails(matchdetails));
		}

		return matchArray;
	}

	private static JSONArray parseSquad(JSONObject infoObject) {

		JSONArray squad = infoObject.optJSONArray("s");
		JSONArray squadObject = new JSONArray();

		for (int i = 0; i < squad.length(); i++) {

			JSONObject teamSquad = squad.optJSONObject(i);
			JSONObject teamObject = new JSONObject();
			teamObject.put("team", getTeamObj(teamSquad.optString("tf")));
			teamObject.put("format", teamSquad.optString("ft"));

			JSONArray players = teamSquad.optJSONArray("pf");
			JSONArray captains = teamSquad.optJSONArray("c");
			JSONArray vcaptains = teamSquad.optJSONArray("vc");
			JSONArray keeper = teamSquad.optJSONArray("iw");

			teamObject.put("players", parsePlayerInfo(players));
			teamObject.put("captains", parsePlayerInfo(captains));
			teamObject.put("vcaptains", parsePlayerInfo(vcaptains));
			teamObject.put("keeper", parsePlayerInfo(keeper));

			squadObject.put(teamObject);
		}

		return squadObject;
	}

	private static JSONArray parsePlayerInfo(JSONArray players) {
		JSONArray playerInfoArray = new JSONArray();

		for (int y = 0; y < players.length(); y++) {

			String playerString = players.optString(y);
			String[] playerInfo = playerString.split("\\/");
			JSONObject playerObject = new JSONObject();

			if (!playerInfo[0].equals("null")) {

				try {

					playerObject.put("name", getPlayerName(playerInfo[0]));
					playerObject.put("key", playerInfo[0]);
					playerObject.put("role", playerInfo[1]);
					playerInfoArray.put(playerObject);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return playerInfoArray;
	}

	private static JSONObject parseInfo(JSONObject info) {

		JSONObject infoObject = info.optJSONObject("i");

		JSONObject newInfoObject = new JSONObject();

		String matches = infoObject.optString("f");
		String[] match = matches.split("\\/");

		String[] foramts = match[0].split("\\.");

		JSONArray formatArray = new JSONArray();

		for (String format : foramts) {
			String[] matchFormats = format.split("-");

			JSONObject formatObject = new JSONObject();
			formatObject.put("matcheCount", matchFormats[0]);
			formatObject.put("format", matchFormats.length > 1 ? matchFormats[1] : "");
			formatArray.put(formatObject);
		}

		newInfoObject.put("date", infoObject.optString("d"));
		newInfoObject.put("formats", formatArray);

		return newInfoObject;
	}

	public static JSONObject parseMatchDetails(JSONObject oMatchDetails) {

		JSONObject matchDetails = new JSONObject();

		JSONObject team1 = Utils.getTeamObj(oMatchDetails.optString("t1f"));
		JSONObject team2 = Utils.getTeamObj(oMatchDetails.optString("t2f"));

		matchDetails.put("team1", team1);
		matchDetails.put("team2", team2);
		matchDetails.put("series", Utils.getSeriesObj(oMatchDetails.optString("sf")));
		matchDetails.put("matchDate", oMatchDetails.optString("date"));
		matchDetails.put("timestamp", oMatchDetails.optLong("t"));
		matchDetails.put("mNumber", oMatchDetails.optString("mn"));
		matchDetails.put("mKey", oMatchDetails.optString("mf"));
		matchDetails.put("nKey", oMatchDetails.optString("nf"));
		matchDetails.put("mId", oMatchDetails.optInt("id"));
		matchDetails.put("type", oMatchDetails.optInt("ft"));
		matchDetails.put("day", oMatchDetails.optInt("d"));

		matchDetails.put("i1score", oMatchDetails.optString("s1"));
		matchDetails.put("i2score", oMatchDetails.optString("s2"));
		matchDetails.put("i3score", oMatchDetails.optString("s3"));
		matchDetails.put("i4score", oMatchDetails.optString("s4"));

		matchDetails.put("i1over", oMatchDetails.optString("o1"));
		matchDetails.put("i2over", oMatchDetails.optString("o2"));
		matchDetails.put("i3over", oMatchDetails.optString("o3"));
		matchDetails.put("i4over", oMatchDetails.optString("o4"));

		matchDetails.put("odds", oMatchDetails.optString("odds"));

		try {
			if (oMatchDetails.keySet().contains("vf")) {
				matchDetails.put("ground", getVenueName(oMatchDetails.optString("vf")));
			} else {
				matchDetails.put("ground", getVenueName(oMatchDetails.optString("v")));
			}
		} catch (Exception e) {

		}

		try {

			String a = oMatchDetails.optString("a");
			String infoMessage = getCaseMessage(a, team1.optString("n"), team2.optString("n"), oMatchDetails);
			matchDetails.put("result", infoMessage);

		} catch (Exception e) {

		}

		matchDetails.put("status", oMatchDetails.optInt("status"));
		matchDetails.put("result", oMatchDetails.optString("result"));

		return matchDetails;
	}

	public static JSONArray parseLiveMatches(JSONObject jsonObject) {

		JSONArray liveMaches = new JSONArray();

		Iterator<String> keys = jsonObject.keys();

		while (keys.hasNext()) {

			String key = (String) keys.next();
			JSONObject matchObject = jsonObject.optJSONObject(key);
			JSONObject livematch = parseMatchInfo(matchObject, key);

			liveMaches.put(livematch);

		}

		return liveMaches;
	}

	static JSONObject parseMatchInfo(JSONObject matchObject, String key) {

		JSONObject match = new JSONObject();

		if (matchObject.optString("b") == null || matchObject.optString("c") == null) {
			return match;
		}

		JSONObject series = getSeriesObj(matchObject.optString("q").replace("^", ""));
		JSONObject team1 = getTeamObj(matchObject.optString("b"));
		JSONObject team2 = getTeamObj(matchObject.optString("c"));

		match.put("key", key);
		match.put("team1", getTeamObj(matchObject.optString("b")));
		match.put("team2", getTeamObj(matchObject.optString("c")));
		match.put("time", matchObject.optString("ti"));
		match.put("1", matchObject.optString("j"));
		match.put("2", matchObject.optString("k"));
		match.put("3", matchObject.optString("l"));
		match.put("4", matchObject.optString("m"));
		match.put("low", matchObject.optString("g"));
		match.put("high", matchObject.optString("h"));
		match.put("series", series);
		match.put("matchNumber", matchObject.optString("e"));
		match.put("ground", getVenueName(matchObject.optString("v")));

		int n = Integer.parseInt(matchObject.optString("n"));
		match.put("format", n % 4);

		int c = (int) Math.floor(n / 12);

		String wp = matchObject.optString("wp");

		int fav = parseFavourite(wp);

		String a = matchObject.optString("a");
		String message = "";
		if (a.startsWith("$")) {
			message = getMessage(a);
		} else {
			message = getCaseMessage(a, team1.optString("sn"), team2.optString("sn"), matchObject);
		}

		match.put("day", c + 1);
		match.put("message", message);
		match.put("fav", fav);
		return match;
	}

	private static int parseFavourite(String wp) {

		int fav = 0;

		String[] details = wp.split("\\,");

		try {
			fav = Integer.parseInt(details[3]);
		} catch (Exception e) {

		}

		return fav;
	}

	private static String getCaseMessage(String a, String team1, String team2, JSONObject match) {

		String message = "";
		switch (a) {
		case "^0":
			message = team1 + " won the toss and chose to bat";
			break;
		case "^1":
			message = team1 + " won the toss and chose to bowl";
			break;
		case "^2":
			message = team2 + " won the toss and chose to bat";
			break;
		case "^3":
			message = team2 + " won the toss and chose to bowl";
			break;
		case "a":
			message = " Innings break";
			break;
		case "b":
			message = " Drinks  break";
			break;
		case "c":
			message = " Lunch Break";
			break;
		case "d":
			message = " Tea Break";
			break;
		case "e":
			message = " Break";
			break;
		case "f":
			message = " Match paused due to rain";
			break;
		case "g":
			message = " Match paused due to low light";
			break;
		case "h":
			message = " Match paused";
			break;
		case "i":
			message = " Match cancelled due to rai";
			break;
		case "j":
			message = " Match cancelled due to low light";
			break;
		case "k":
			message = " Match cancelled";
			break;
		case "l":
			message = " Stumps";
			break;
		case "m":
			message = " Timeout";
			break;
		case "n":
			message = " Match Drawn";
			break;
		case "o":
			message = " Super Over";
			break;
		case "p":
			message = " Match Tied";
			break;
		case "q":
			message = " Abandoned";
			break;
		case "r":
			message = " Rescheduled";
			break;
		case "s":
			message = " Toss delayed";
			break;
		case "t":
			message = " Toss delayed due to rain";
			break;
		case "u":
			message = " Toss delayed due to bad weather";
			break;
		case "v":
			message = " Toss delayed due to low light";
			break;
		case "w":
			message = " Toss delayed due to wet outfield";
			break;
		case "":
		default:
			int d = match.optInt("d");
			if (d == 2) {
				message = getDetailedMessage(d, team1, team2, match);
			}
			break;

		}
		return message;
	}

	public static String getDetailedMessage(int d, String team1, String team2, JSONObject match) {

		int n = Integer.parseInt(match.optString("n"));
		String message = "";

		int q = n % 4;

		int c = (int) Math.floor(n / 4);
		if (n > 12) {
			int t = (int) Math.floor(n % 12);
			c = (int) Math.floor(t / 4);
		}

		int run1 = 0;
		int run2 = 0;
		int run3 = 0;
		int run4 = 0;
		int wk2 = 0;
		int wk3 = 0;
		int wk4 = 0;
		int ball2 = 0;

		String score1 = match.optString("j");
		String score2 = match.optString("k");
		String score3 = match.optString("l");
		String score4 = match.optString("m");
		int dlsRun = match.optInt("o");
		int dlsBall = match.optInt("p");

		if (score1 != null && !score1.isEmpty()) {
			String[] scoring1 = score1.split("\\/");
			run1 = Integer.parseInt(scoring1[0]);
		}

		if (score2 != null && !score2.isEmpty()) {
			String[] scoring2 = score2.split("\\/");
			run2 = Integer.parseInt(scoring2[0]);
			wk2 = Integer.parseInt(scoring2[1].substring(0, scoring2[1].indexOf("(")));

			try {
				String ballString2 = scoring2[1].substring(scoring2[1].indexOf("(") + 1, scoring2[1].length());
				String[] parts2 = ballString2.split("\\.");
				ball2 = Integer.parseInt(parts2[0]) * 6 + Integer.parseInt(parts2[1]);
			} catch (Exception e) {
			}
		}

		if (score3 != null && !score3.isEmpty()) {
			String[] scoring3 = score3.split("\\/");
			run3 = Integer.parseInt(scoring3[0]);
			wk3 = Integer.parseInt(scoring3[1].substring(0, scoring3[1].indexOf("(")));
		}

		if (score4 != null && !score4.isEmpty()) {
			String[] scoring4 = score4.split("\\/");
			run4 = Integer.parseInt(scoring4[0]);
			wk4 = Integer.parseInt(scoring4[1].substring(0, scoring4[1].indexOf("(")));
		}

		if (d == 2) {

			if (q != 2) {

				if (dlsRun != 0) {
					run1 = dlsRun;
				}

				int diff = run2 - run1;
				int wk = 10 - wk2;
				int balldiff = 0;

				if (c == 1) {

					diff = run1 + 1 - run2;
					if (diff < 0) {
						message = team2 + " won by " + wk + " wickets";
					} else if (diff == 0) {
						message = "Scores Level";
					} else {
						if (q == 0) {
							balldiff = 120 - ball2;
						} else if (q == 1) {
							balldiff = 300 - ball2;
						} else if (dlsBall != 0) {
							balldiff = dlsBall - ball2;
						}

						message = team2 + " needs " + diff + " runs to win from " + balldiff + " balls";
					}

				} else if (c == 2) {

					if (diff == 0) {
						message = "Match Tied";
					} else if (diff < 0) {
						message = team1 + " won by " + (-diff - 1) + " runs";
					} else {
						message = team2 + " won by " + wk + " wickets";
					}

					if (dlsRun != 0) {
						message = message + " (DLS Method)";
					}

				}
			} else {

				int diff = run2 - run1;

				if (diff == 0) {
					message = "Scores Level";
				} else if (diff < 0) {
					message = team2 + " trial by " + (run1 - run2) + " runs ";
				} else {
					message = team2 + " lead by " + diff + " runs ";
				}
			}
		} else if (d == 3 || d == 4) {

			int team1Run = run1 + run3;
			int team2Run = run2 + run4;
			int runDiff = team2Run - team1Run;
			int wkDiff = 10 - wk3;

			if (wkDiff == 0) {
				message = team2 + " won by an inning and  " + runDiff + " runs ";
			} else {
				if (runDiff > 0) {
					message = team1 + " trail by " + runDiff + " runs";
				} else if (runDiff == 0) {
					message = "Scores Level";
				} else if (runDiff < 0) {
					message = team1 + " lead by " + (-runDiff) + " runs";
				} else if (c == 2) {
					message = "Match Drawn";
				} else if ((-runDiff) > 100 && wkDiff < 4) {
					message = team2 + " need  " + wkDiff + " Wicket to win ";
				}
			}

		} else if (d == 5 || d == 6) {

			int team1Run = run1 + run3;
			int team2Run = run2 + run4;
			int runDiff = team2Run - team1Run;

			int wkDiff = 10 - wk4;

			if (wkDiff == 0) {

				message = team1 + " won by " + (-runDiff) + " runs";
				if (runDiff == 0) {
					message = "Match Drawn";
				}

			} else if (runDiff > 0) {
				message = team2 + " won by " + wkDiff + " wickets";
			} else {

				if (wkDiff < 4 && (-runDiff) > 50) {
					message = team1 + " needs " + wkDiff + " wickets to win";
				} else {
					message = team2 + " needs " + ((-runDiff) + 1) + " runs to win";
				}

			}

		}

		return message;

	}

	private static String getMessage(String a) {

		Map<String, String> breakMap = new HashMap<>();
		breakMap.put("$a", "Innings Break");
		breakMap.put("$b", "Drinks Break");
		breakMap.put("$c", "Lunch Break");
		breakMap.put("$d", "Tea Break");
		breakMap.put("$e", "Break Title");
		breakMap.put("$f", "Rain Delay");
		breakMap.put("$g", "Low Light Delay");
		breakMap.put("$h", "Match Paused");
		breakMap.put("$i", "Cancelled due to rain");
		breakMap.put("$j", "Cancelled due to low lights");
		breakMap.put("$k", "Match Cancelled");
		breakMap.put("$l", "Stumps");
		breakMap.put("$m", "Timeout");
		breakMap.put("$n", "Match Drawn");
		breakMap.put("$o", "Super Over");
		breakMap.put("$p", "Match Tied");
		breakMap.put("$q", "Abandoned");
		breakMap.put("$r", "Rescheduled");
		breakMap.put("$s", "Toss delayed");
		breakMap.put("$t", "Toss delayed due to rain");
		breakMap.put("$u", "Toss delayed due to bad weather");
		breakMap.put("$v", "Toss delayed due to low light");
		breakMap.put("$w", "Toss delayed due to wet outfield");
		breakMap.put("$x", "No Result");

		return breakMap.get(a);

	}

	public static JSONArray parsePlayingTeam(String teamPlaying) {

		JSONArray teams = new JSONArray();

		String[] teamString = teamPlaying.split("\\/");

		for (String team : teamString) {

			String players[] = team.split("-");
			JSONArray playerArray = new JSONArray();

			for (String player : players) {
				JSONObject playerInfo = parsePlayerInfo(player);
				playerArray.put(playerInfo);
			}

			teams.put(playerArray);

		}

		return teams;
	}

	private static JSONObject parsePlayerInfo(String player) {

		String[] playerinfo = player.split("\\.");
		JSONObject playerObject = new JSONObject();

		try {
			playerObject.put("name", getPlayerName(playerinfo[0]));
			playerObject.put("key", playerinfo[0]);
			playerObject.put("role", playerinfo[2]);
		} catch (Exception e) {

		}

		return playerObject;
	}

	public static JSONArray parseUmpire(String umpiresString) {

		JSONArray umpireArray = new JSONArray();

		String[] umpires = umpiresString.split("\\/");

		for (String umpire : umpires) {
			umpireArray.put(getUmpireName(umpire));
		}

		return umpireArray;
	}

	public static JSONObject parseLiveLine(JSONObject jsonObject) {

		JSONObject liveLineObject = new JSONObject();
		int target = 0;
		int maxBalls = 300;
		String message = jsonObject.optString("B");
		String dlsTarget = jsonObject.optString("T");

		liveLineObject.put("message", message);
		liveLineObject.put("overArray", parseOvers(jsonObject));
		liveLineObject.put("teams", parseTeam(jsonObject.optString("a")));
		liveLineObject.put("better", parseBetter(jsonObject));
		liveLineObject.put("bowler", parseBowler(jsonObject));
		liveLineObject.put("session", jsonObject.optString("Z"));
		liveLineObject.put("rate", jsonObject.optString("R"));
		liveLineObject.put("compare", jsonObject.optString("ats"));
		liveLineObject.put("f", jsonObject.optString("F").replace("^", ""));
		liveLineObject.put("sessions", parseSession(jsonObject.optString("S")));
		liveLineObject.put("cs",
				parseCurrentSession(jsonObject.optString("M"), jsonObject.optInt("D"), jsonObject.optString("D")));

		JSONObject lw = parseLastWicket(jsonObject.optString("x"));
		JSONObject i1 = getScore(jsonObject.optString("j"));
		JSONObject i2 = getScore(jsonObject.optString("k"));

		liveLineObject.put("lw", lw);

		int pRuns = 0;
		int pBalls = 0;

		if (i1.optInt("r") != 0) {

			pRuns = i1.optInt("r");
			pBalls = i1.optInt("b");

			if (i2.optInt("r") != 0) {

				target = i1.optInt("r") + 1;
				int balls = i2.optInt("b");
				int runs = i2.optInt("r");

				pRuns = runs;
				pBalls = balls;

				if (dlsTarget != null && !dlsTarget.isEmpty()) {
					String[] dlsScore = dlsTarget.split("\\.");
					target = Integer.parseInt(dlsScore[1]);
					maxBalls = Integer.parseInt(dlsScore[0]);
				}

				int runDiff = target - runs;
				int ballDiff = maxBalls - balls;

				float rrr = ((float) runDiff / ballDiff) * 6;
				liveLineObject.put("rrr", format.format(rrr));
			}

		}

		if (lw.optInt("r") != 0) {
			pRuns = pRuns - lw.getInt("fr");
		}

		if (lw.optInt("b") != 0) {
			pBalls = pBalls - lw.getInt("fb");
		}

		liveLineObject.put("1", i1);
		liveLineObject.put("2", i2);

		liveLineObject.put("pr", pRuns);
		liveLineObject.put("pb", pBalls);
		liveLineObject.put("t", target);
		liveLineObject.put("mb", maxBalls);

		return liveLineObject;
	}

	private static JSONObject parseLastWicket(String lwString) {

		JSONObject jsonObject = new JSONObject();

		if (lwString != null && !lwString.isEmpty()) {
			String[] deatils = lwString.split("\\.");

			jsonObject.put("n", getPlayerName(deatils[0]));
			jsonObject.put("r", deatils[1]);
			jsonObject.put("b", deatils[2]);
			jsonObject.put("fr", deatils[3]);
			jsonObject.put("fb", deatils[4]);

		}
		return jsonObject;
	}

	private static JSONObject getScore(String scoreString) {

		JSONObject s = new JSONObject();

		if (scoreString != null && !scoreString.isEmpty()) {
			String[] scores = scoreString.split("\\/");
			try {

				int run = Integer.parseInt(scores[0]);
				s.put("r", run);
				s.put("w", Integer.parseInt(scores[1].substring(0, scores[1].indexOf("("))));

				String ballString2 = scores[1].substring(scores[1].indexOf("(") + 1, scores[1].length());
				String[] parts2 = ballString2.split("\\.");
				int ball = Integer.parseInt(parts2[0]) * 6 + Integer.parseInt(parts2[1]);
				s.put("b", ball);
				float rr = ((float) run / ball) * 6;
				s.put("rr", format.format(rr));
			} catch (Exception e) {

			}
		}

		return s;
	}

	private static JSONObject parseCurrentSession(String csString, int over, String overString) {

		JSONObject cs = new JSONObject();

		String[] overs = overString.split("\\,");

		String[] sessionInfo = csString.split("\\.");

		try {

			cs.put("ov", overs[0].isEmpty() ? over : overs[0]);
			cs.put("o", sessionInfo[0]);
			cs.put("l", sessionInfo[1]);
			cs.put("h", sessionInfo[2]);

		} catch (Exception e) {

		}

		return cs;
	}

	private static JSONArray parseSession(String sessionString) {
		JSONArray jsonArray = new JSONArray();

		String[] sessions = sessionString.split("\\|");

		for (String session : sessions) {

			String[] sessionDetils = session.split("\\,");

			JSONArray array = new JSONArray();
			for (String sDetais : sessionDetils) {

				String parts[] = sDetais.split("\\.");

				JSONObject sessionObject = new JSONObject();
				;
				try {
					sessionObject.put("ov", parts[0]);
					sessionObject.put("o", parts[1]);
					sessionObject.put("p", parts[2]);
					sessionObject.put("l", parts[3]);
					sessionObject.put("h", parts[4]);
				} catch (Exception e) {
				}

				array.put(sessionObject);

			}
			jsonArray.put(array);
		}

		return jsonArray;
	}

	private static JSONArray parseBowler(JSONObject jsonObject) {

		JSONArray playerArray = new JSONArray();

		String bowler1 = jsonObject.optString("y");
		String bowler2 = jsonObject.optString("z");
		String cb = jsonObject.optString("b");

		JSONObject bowlerObject1 = parseBowlingFigure(bowler1, cb);
		JSONObject bowlerObject2 = parseBowlingFigure(bowler2, cb);

		playerArray.put(bowlerObject1);
		playerArray.put(bowlerObject2);

		return playerArray;
	}

	private static JSONObject parseBowlingFigure(String bowlerString, String currentBowler) {

		JSONObject details = new JSONObject();

		if (bowlerString != null) {

			String[] detailParts = bowlerString.split("\\.");

			try {
				if (detailParts.length > 1) {
					details.put("n", getPlayerName(detailParts[0]));
					details.put("b", detailParts[1]);
					details.put("r", detailParts[2]);
					details.put("w", detailParts[3]);
					details.put("c", detailParts[0].equals(currentBowler));
				}
			} catch (JSONException e) {
			}
		}

		return details;
	}

	private static JSONArray parseBetter(JSONObject jsonObject) {

		JSONArray playerArray = new JSONArray();

		String playerString = jsonObject.optString("p");

		if (playerString != null) {

			String p1String = jsonObject.optString("q");
			String p1b = jsonObject.optString("r");
			String p2String = jsonObject.optString("s");
			String p2b = jsonObject.optString("t");

			String player[] = playerString.split("\\.");

			JSONObject details1 = parseRunDetails(player[0], p1String, p1b);
			JSONObject details2 = player.length > 1 ? parseRunDetails(player[1], p2String, p2b) : new JSONObject();

			playerArray.put(details1);
			playerArray.put(details2);

		}

		return playerArray;
	}

	private static JSONObject parseRunDetails(String pkey, String pString, String pb) {

		JSONObject details = new JSONObject();

		if (!pString.isEmpty()) {

			details.put("p", getPlayerName(pkey));

			if (pString != null) {

				String[] runBalls = pString.split("\\.");

				if (runBalls.length > 1) {

					details.put("r", runBalls[0]);
					details.put("b", runBalls[1].replace("*", ""));
					details.put("s", pString.contains("*"));

				}
			}

			if (pb != null) {
				String[] boundries = pb.split("\\.");

				if (boundries.length > 1) {

					details.put("4s", boundries[0]);
					details.put("6s", boundries[1]);
				}
			}
		}

		return details;
	}

	private static JSONArray parseTeam(String teamString) {

		JSONArray teamArray = new JSONArray();
		String[] teams = teamString.split("\\.");

		for (String team : teams) {
			teamArray.put(getTeamObj(team));
		}

		return teamArray;
	}

	private static JSONArray parseOvers(JSONObject jsonObject) {

		JSONArray overArray = new JSONArray();

		JSONObject lastOver1 = parseLiveOvers(jsonObject.optString("l"));
		JSONObject lastOver2 = parseLiveOvers(jsonObject.optString("m"));
		JSONObject lastOver3 = parseLiveOvers(jsonObject.optString("n"));
		JSONObject currentOver = parseCurrentOver(jsonObject.optString("A"));

		overArray.put(lastOver1);
		overArray.put(lastOver2);
		overArray.put(lastOver3);
		overArray.put(currentOver);

		return overArray;
	}

	private static JSONObject parseCurrentOver(String overString) {
		JSONObject jsonObject = new JSONObject();

		if (overString != null) {
			JSONArray jsonArray = new JSONArray();
			String[] balls = overString.split("\\.");

			for (String ball : balls) {
				jsonArray.put(ball);
			}
			jsonObject.put("b", jsonArray);
			jsonObject.put("o", "c");
		}

		return jsonObject;
	}

	private static JSONObject parseLiveOvers(String overString) {

		JSONObject over = new JSONObject();

		try {
			String[] overs = overString.split("\\:");
			if (overString != null && !overString.isEmpty()) {

				String[] balls = overs[1].split("\\.");

				JSONArray overArray = new JSONArray();

				for (int i = 0; i < balls.length; i++) {
					overArray.put(balls[i]);
				}

				over.put("b", overArray);
				over.put("o", overs[0]);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return over;
	}

	public static JSONArray parseDetailedStats(JSONObject jsonObject) {

		JSONArray data = jsonObject.optJSONArray("d");

		for (int i = 0; i < data.length(); i++) {

			JSONObject details = data.getJSONObject(i);
			details.put("name", getPlayerName(details.optString("pf")));
			details.put("team", getTeamObj(details.optString("tf")));
			details.put("me", details.optString("bi"));
			details.put("key", details.optString("pf"));
			details.remove("bi");
			details.remove("pf");
			details.remove("tf");

		}

		return data;
	}

	public static JSONArray parseSeriesMatches(JSONArray jsonObject) {

		JSONArray matches = new JSONArray();

		for (int i = 0; i < jsonObject.length(); i++) {

			JSONObject match = jsonObject.getJSONObject(i);
			JSONObject nMatch = parseMatchDetails(match);
			matches.put(nMatch);
		}

		return matches;
	}

	public static String createJWT() {
		long currentTimeMillis = System.currentTimeMillis();
		JwtBuilder signWith = Jwts.builder().signWith(SignatureAlgorithm.HS256, getSecretKey());
		signWith.setExpiration(new Date(currentTimeMillis + 200000));
		return signWith.compact();
	}

	private static Key getSecretKey() {
		return new SecretKeySpec(KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
	}

	public static JSONArray parseGuruSeries(String response) {

		JSONObject jsonObject = new JSONObject(response);
		JSONArray newResponse = new JSONArray();

		JSONObject res = jsonObject.optJSONObject("res");

		JSONArray seriesArray = res.optJSONArray("series");

		for (int i = 0; i < seriesArray.length(); i++) {

			JSONObject seriesJson = seriesArray.getJSONObject(i);

			JSONArray data = seriesJson.optJSONArray("data");

			for (int y = 0; y < data.length(); y++) {

				JSONObject series = data.getJSONObject(y);

				JSONObject seriesObject = new JSONObject();

				Date startDate = new Date(series.optLong("sDate") * 1000);
				Date endDate = new Date(series.optLong("eDate") * 1000);

				seriesObject.put("sName", getGuruSeriesObj(series.optString("name"), series.optString("key")));
				seriesObject.put("startDate", sdf.format(startDate));
				seriesObject.put("endDate", sdf.format(endDate));
				seriesObject.put("format", new JSONArray());
				seriesObject.put("tournament", series.optString("name"));
				seriesObject.put("tournamentFormat", new JSONObject());
				seriesObject.put("nm", series.optInt("noOfMatches"));

				newResponse.put(seriesObject);
			}

		}

		return newResponse;
	}

	public static JSONArray parseGutuSeriesMatches(String response) {

		JSONObject jsonObject = new JSONObject(response);
		JSONArray newResponse = new JSONArray();

		JSONObject res = jsonObject.optJSONObject("res");

		JSONObject seriesDetails = res.optJSONObject("series");
		String seriesName = seriesDetails.optString("name");

		JSONArray matches = seriesDetails.optJSONArray("matches");

		for (int i = 0; i < matches.length(); i++) {

			JSONObject guruObject = matches.getJSONObject(i);
			newResponse.put(parseGuruMatchDetails(guruObject, seriesName, i));
		}

		return newResponse;
	}

	public static JSONObject parseGuruMatchDetails(JSONObject oMatchDetails, String seriesName, int index) {

		JSONObject matchDetails = new JSONObject();

		JSONObject teams = oMatchDetails.optJSONObject("teams");

		JSONObject team1 = teams.optJSONObject("t1");
		JSONObject team2 = teams.optJSONObject("t2");

		long time = oMatchDetails.optLong("time") * 1000;

		matchDetails.put("team1", Utils.getGuruTeamObj(team1));
		matchDetails.put("team2", Utils.getGuruTeamObj(team2));
		matchDetails.put("series", Utils.getGuruSeriesObj(seriesName, ""));

		matchDetails.put("matchDate", sdf2.format(new Date(time)));
		matchDetails.put("timestamp", time);
		matchDetails.put("mNumber", index);
		matchDetails.put("mKey", oMatchDetails.optString("key"));
		matchDetails.put("nKey", oMatchDetails.optString("key"));
		matchDetails.put("mId", oMatchDetails.optString("key"));
		matchDetails.put("type", Format.valueOf(oMatchDetails.optString("format")).getValue());
		matchDetails.put("day", 0);

		if (team1 != null) {

			String score = team1.optString("score");
			String[] scores = parseGuruScore(score);
			matchDetails = addScore(1, matchDetails, scores.length > 0 ? scores[0] : null);
			matchDetails = addScore(3, matchDetails, scores.length > 1 ? scores[1] : null);

		}

		if (team2 != null) {

			String score = team2.optString("score");
			String[] scores = parseGuruScore(score);
			matchDetails = addScore(2, matchDetails, scores.length > 0 ? scores[0] : null);
			matchDetails = addScore(4, matchDetails, scores.length > 1 ? scores[1] : null);

		}

		matchDetails.put("odds", "");
		matchDetails.put("ground", oMatchDetails.optString("venue"));

		// TODO : Need to convert int to string
		matchDetails.put("status", oMatchDetails.optString("matchStatus"));
		matchDetails.put("status", Status.valueOf(oMatchDetails.optString("matchStatus")).getValue());

		JSONObject result = oMatchDetails.optJSONObject("result");
		String resultMessage = "";

		if (result != null) {
			resultMessage = result.optString("message");
		}

		matchDetails.put("result", resultMessage);

		return matchDetails;
	}

	private static String[] parseGuruScore(String score) {
		String[] scores = {};

		if (score != null) {
			scores = score.split("\\&");
		}

		return scores;

	}

	private static JSONObject addScore(int i, JSONObject matchDetails, String scores) {

		String runs = "";
		String overs = "";

		if (scores != null && scores.length() > 2) {

			runs = scores.substring(0, scores.indexOf("("));
			overs = scores.substring(scores.indexOf("(") + 1, scores.indexOf(")"));
		}

		matchDetails.put("i" + i + "score", runs);
		matchDetails.put("i" + i + "over", overs);

		return matchDetails;

	}

	private static JSONObject getGuruTeamObj(JSONObject team) {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("sn", team.opt("sName"));
		jsonObject.put("id", team.opt("key"));
		jsonObject.put("f_key", team.opt("key"));
		jsonObject.put("n", team.opt("name"));

		return jsonObject;
	}

	private static JSONObject getGuruSeriesObj(String seriesName, String id) {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("sn", seriesName);
		jsonObject.put("n", seriesName);
		jsonObject.put("id", id);
		jsonObject.put("f_key", id);

		return jsonObject;
	}

	public static JSONObject parseCESeries(JSONObject jsonObject) {

		JSONObject seriesObject = new JSONObject();
		seriesObject.put("sName", Utils.getSeriesObj(jsonObject.optString("sf")));
		seriesObject.put("startDate", jsonObject.optString("sd"));
		seriesObject.put("endDate", jsonObject.optString("ed"));
		seriesObject.put("format", jsonObject.optJSONArray("format"));
		seriesObject.put("tournament", jsonObject.optString("tn"));
		seriesObject.put("tournamentFormat", Utils.getSeriesFormat(jsonObject.optString("stid")));
		seriesObject.put("nm", 0);

		return seriesObject;
	}

	public static JSONArray parseGuruFixture(String response) {
		JSONObject jsonObject = new JSONObject(response);
		JSONArray newResponse = new JSONArray();

		JSONObject res = jsonObject.optJSONObject("res");

		JSONArray matches = res.optJSONArray("matches");

		for (int i = 0; i < matches.length(); i++) {

			JSONObject matchData = matches.getJSONObject(i);

			JSONArray matchesArray = matchData.getJSONArray("data");

			for (int y = 0; y < matchesArray.length(); y++) {

				JSONObject matchDetails = matchesArray.getJSONObject(y);
				String seriesName = matchDetails.optString("srs");
				newResponse.put(parseGuruMatchDetails(matchDetails, seriesName, i));
			}

		}

		return newResponse;
	}

	public static JSONArray parseGuruPointTable(String response) {

		JSONObject jsonObject = new JSONObject(response);
		JSONArray newResponse = new JSONArray();

		JSONObject res = jsonObject.optJSONObject("res");

		JSONObject series = res.optJSONObject("series");

		JSONArray points = series.optJSONArray("points");

		JSONArray tableArray = new JSONArray();
		JSONObject tableObject = new JSONObject();
		JSONArray tablesArray = new JSONArray();

		for (int i = 0; i < points.length(); i++) {

			JSONObject teamObject = points.optJSONObject(i);

			JSONArray teams = teamObject.optJSONArray("teams");

			for (int j = 0; j < teams.length(); j++) {

				JSONObject team = teams.optJSONObject(j);
				JSONObject newTeam = formatTeam(team);
				tableArray.put(newTeam);
			}

			tableObject.put("name", series.optString("name"));
			tableObject.put("table", tableArray);

			tablesArray.put(tableObject);
		}

		newResponse.put(tablesArray);

		return newResponse;
	}

	private static JSONObject formatTeam(JSONObject team) {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("L", String.valueOf(team.optInt("lost")));
		jsonObject.put("P", String.valueOf(team.optInt("matches")));
		jsonObject.put("W", String.valueOf(team.optInt("won")));
		jsonObject.put("NR", String.valueOf(team.optInt("tie")));
		jsonObject.put("Pts", String.valueOf(team.optInt("pts")));
		jsonObject.put("NRR", String.valueOf(team.optInt("nrr")));

		JSONObject teamObject = new JSONObject();

		teamObject.put("sn", team.opt("sName"));
		teamObject.put("id", team.opt("key"));
		teamObject.put("f_key", team.opt("key"));
		teamObject.put("n", team.opt("sName"));

		jsonObject.put("team", teamObject);

		return jsonObject;
	}

	public static JSONArray parseGuruTeams(String response) {

		JSONObject jsonObject = new JSONObject(response);

		JSONObject res = jsonObject.optJSONObject("res");

		JSONObject squads = res.optJSONObject("squads");
		JSONArray teams = squads.optJSONArray("teams");

		return teams;
	}

	public static JSONArray parseGuruOptions(String response) {

		JSONObject jsonObject = new JSONObject(response);

		JSONObject res = jsonObject.optJSONObject("res");

		JSONArray options = res.optJSONArray("options");

		return options;
	}

//	public static String decompress(byte[] compressed) throws IOException {
//		ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
//		GZIPInputStream gis = new GZIPInputStream(bis);
//		BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
//		StringBuilder sb = new StringBuilder();
//		String line;
//		while ((line = br.readLine()) != null) {
//			sb.append(line);
//		}
//		br.close();
//		gis.close();
//		bis.close();
//		return sb.toString();
//	}

//	public static boolean validateApicallTime(long ts, long currentDateTime) {
//		return ts + (Constants.EXPIRE_MIN * Constants.MIN) >= currentDateTime;
//	}

	/**
	 * This method is used to decrypt encrypted data.
	 * 
	 * @param encrypted {@link Byte} encrypted.
	 * @param key       key of decryption.
	 * @return decrypted data.
	 * @throws Exception
	 */
//	public static String getDecryptedText(final byte[] encrypted, final String key) throws Exception {
//		final Cipher cipher = Utils.getMutual();
//		cipher.init(Cipher.DECRYPT_MODE, Utils.getAesKey(key));
//		final String realPass = new String(cipher.doFinal(encrypted));
//		return realPass;
//	}

	/**
	 * This method is used to get cipher object.
	 * 
	 * @return object
	 * @throws Exception
	 */
	private static Cipher getMutual() throws Exception {
		final Cipher cipher = Cipher.getInstance("AES");
		return cipher;// cipher.doFinal(pass.getBytes());
	}

	/**
	 * This method used to get Advance Encryption Standard key.
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
//	private static Key getAesKey(final String date) throws Exception {
//		return new SecretKeySpec(Arrays.copyOf(date.getBytes("UTF-8"), 16), "AES");
//	}

}
