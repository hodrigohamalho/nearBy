package com.huge.nearby.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.huge.nearby.foursquare.Method;

public class IOHandler {

  public Response fetchData(String url, Method method) {
    int code = 200;

    try {
      URL aUrl = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) aUrl.openConnection();
      try {
        connection.setDoInput(true);
        if("POST".equals(method.name())) {
            connection.setDoOutput(true);
        }
        connection.setRequestMethod(method.name());
        connection.connect();

        code = connection.getResponseCode();
        if (code == 200) {
          InputStream inputStream = connection.getInputStream();
          return new Response(readStream(inputStream), code, connection.getResponseMessage());
        } else {
          return new Response("", code, getMessageByCode(code));
        }

      } finally {
        connection.disconnect();
      }
    } catch (MalformedURLException e) {
      return new Response("", 400, "Malformed URL: " + url);
    } catch (IOException e) {
      return new Response("", 500, e.getMessage());
    }
  }

  /**
   * Reads input stream and returns it's contents as String
   * 
   * @param inputStream input stream to be readed
   * @return Stream's content
   * @throws IOException 
   */
  private String readStream(InputStream inputStream) throws IOException {
    StringWriter responseWriter = new StringWriter();

    char[] buf = new char[1024];
    int l = 0;

    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
    while ((l = inputStreamReader.read(buf)) > 0) {
      responseWriter.write(buf, 0, l);
    }

    responseWriter.flush();
    responseWriter.close();
    return responseWriter.getBuffer().toString();
  }

  private String getMessageByCode(int code) {
    switch (code) {
      case 400:
        return "Bad Request";
      case 401:
        return "Unauthorized";
      case 403:
        return "Forbidden";
      case 404:
        return "Not Found";
      case 405:
        return "Method Not Allowed";
      case 500:
        return "Internal Server Error";
      default:
        return "Unknown";
    }
  }
  
}