package com.crickPulse.apicaller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.crickPulse.utils.Constants;

public class WebCaller {

	public static final String HTTP_GET = "get";
	public static final String HTTP_POST = "post";
	public static final String HTTP_DELETE = "delete";
	public static final String HTTP_PUT = "put";
	public static final String HTTP_PATCH = "patch";

	public static String getResult(String url, String type) {

		String result = "";
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpResponse response;
			if (type.equalsIgnoreCase(HTTP_POST)) {
				HttpPost httpPost = new HttpPost(url);
				response = client.execute(httpPost);
			} else {
				HttpGet httpGet = new HttpGet(url);
				response = client.execute(httpGet);
			}
			result = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String getResult(String url, String type, String rawData, Map<String, String> headers) {

		String result = Constants.EMPTY;
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

			CloseableHttpResponse response = null;

			try {
				if (type.equalsIgnoreCase(HTTP_POST)) {
					HttpPost httpPost = new HttpPost(url);

					if (rawData != null) {
						httpPost.setEntity(new StringEntity(rawData));
					}

					if (headers != null && headers.size() > Constants.ZERO) {
						for (String header : headers.keySet()) {
							httpPost.setHeader(header, headers.get(header));
						}
					}

					response = client.execute(httpPost);
				} else {
					HttpGet httpGet = new HttpGet(url);
					if (headers != null && headers.size() > Constants.ZERO) {
						for (String header : headers.keySet()) {
							httpGet.setHeader(header, headers.get(header));
						}
					}

					response = client.execute(httpGet);
				}
			} finally {
				if (response != null) {
					result = EntityUtils.toString(response.getEntity());
					response.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String getPostResult(String url, String type, List<NameValuePair> params, Map<String, String> headers,
			Map<String, String> multipartData, JSONObject json) {

		String result = "";

		try {

			HttpClient client = HttpClientBuilder.create().build();
			MultipartEntityBuilder entitybuilder = MultipartEntityBuilder.create();

			HttpResponse response = null;
			if (type.equalsIgnoreCase(HTTP_POST)) {
				HttpPost httpPost = new HttpPost(url);

				if (params != null) {
					httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
				}

				if (headers != null) {
					for (String key : headers.keySet()) {
						httpPost.setHeader(key, headers.get(key));
					}
				}

				if (json != null) {
					httpPost.setEntity(new StringEntity(json.toString()));

				}

				if (multipartData != null) {
					for (String key : multipartData.keySet()) {
						entitybuilder.addTextBody(key, multipartData.get(key));
					}
					httpPost.setEntity(entitybuilder.build());
				}
//				else {
//					httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
//				}
				response = client.execute(httpPost);

			} else if (type.equalsIgnoreCase(HTTP_GET)) {
				HttpGet httpGet = new HttpGet(url);

				if (!params.isEmpty()) {

					URI uri = new URIBuilder(httpGet.getURI()).addParameters(params).build();
					((HttpRequestBase) httpGet).setURI(uri);
				}

				if (headers != null) {
					for (String key : headers.keySet()) {
						httpGet.setHeader(key, headers.get(key));
					}
				}
				response = client.execute(httpGet);
			} else if (type.equalsIgnoreCase(HTTP_DELETE)) {

				HttpDelete httpDelete = new HttpDelete(url);
				if (!params.isEmpty()) {

					URI uri = new URIBuilder(httpDelete.getURI()).addParameters(params).build();
					((HttpRequestBase) httpDelete).setURI(uri);
				}

				if (headers != null) {
					for (String key : headers.keySet()) {
						httpDelete.setHeader(key, headers.get(key));
					}
				}
				client.execute(httpDelete);
				return "Deleted";
			} else if (type.equalsIgnoreCase(HTTP_PUT)) {
				HttpPut httpPut = new HttpPut(url);
				if (!params.isEmpty()) {

					URI uri = new URIBuilder(httpPut.getURI()).addParameters(params).build();
					((HttpRequestBase) httpPut).setURI(uri);
				}

				if (headers != null) {
					for (String key : headers.keySet()) {
						httpPut.setHeader(key, headers.get(key));
					}
				}
				client.execute(httpPut);
				return "Locked";

			} else if (type.equalsIgnoreCase(HTTP_PATCH)) {

			}
			result = EntityUtils.toString(response.getEntity());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}