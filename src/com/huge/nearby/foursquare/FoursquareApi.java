package com.huge.nearby.foursquare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.huge.nearby.io.IOHandler;
import com.huge.nearby.io.Response;

public class FoursquareApi {

	private IOHandler ioHandler;

	public FoursquareApi() {
		this.ioHandler = new IOHandler();
	}

	public ApiRequestResponse doApiRequest(Method method, String url) throws JSONException, FoursquareApiException {
		Response response = ioHandler.fetchData(url, method);

		return handleApiResponse(response);
	}

	private ApiRequestResponse handleApiResponse(Response response) throws JSONException {
		JSONObject responseJson = null;
		JSONArray notificationsJson = null;
		String errorDetail = null;

		if (response.getResponseCode() == 200) {
			JSONObject responseObject = new JSONObject(response.getResponseContent());
			responseJson = responseObject.getJSONObject("response");
			notificationsJson = responseObject.optJSONArray("notifications");
		} else {
			errorDetail = response.getMessage();
		}

		ResultMeta meta = new ResultMeta(response.getResponseCode(), "", errorDetail);
		return new ApiRequestResponse(meta, responseJson, notificationsJson);
	}

}
