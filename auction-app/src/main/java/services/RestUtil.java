package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public abstract class RestUtil {

    private static final String SERVER_URL = "http://localhost:8080/auction-server";

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T httpGet(String url, Class<T> type) {

        try {

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(SERVER_URL + url);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return objectMapper.readValue(EntityUtils.toString(response.getEntity()), type);
            }

            return type.newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static <T> T httpPost(String url, Class<T> type, Object requestBody) {

        try {

            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(SERVER_URL + url);

            StringEntity entity = new StringEntity(objectMapper.writeValueAsString(requestBody));
            request.setEntity(entity);
            request.setHeader("Content-type", "application/json");

            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return objectMapper.readValue(EntityUtils.toString(response.getEntity()), type);
            } else {
                throw new RuntimeException(EntityUtils.toString(response.getEntity()));
            }
        } catch (Exception e) {
            throw new RuntimeJsonMappingException(e.getMessage());
        }
    }


    public static void httpPostNoResponse(String url, Object requestBody) {

        try {

            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(SERVER_URL + url);

            StringEntity entity = new StringEntity(objectMapper.writeValueAsString(requestBody));
            request.setEntity(entity);
            request.setHeader("Content-type", "application/json");

            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException(EntityUtils.toString(response.getEntity()));
            }
        } catch (Exception e) {
            throw new RuntimeJsonMappingException(e.getMessage());
        }
    }

    public static <T> T httpPut(String url, Class<T> type, Object requestBody) {

        try {

            HttpClient client = new DefaultHttpClient();
            HttpPut request = new HttpPut(SERVER_URL + url);

            StringEntity entity = new StringEntity(objectMapper.writeValueAsString(requestBody));
            request.setEntity(entity);
            request.setHeader("Content-type", "application/json");

            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return objectMapper.readValue(EntityUtils.toString(response.getEntity()), type);
            } else {
                throw new RuntimeException(EntityUtils.toString(response.getEntity()));
            }
        } catch (Exception e) {
            throw new RuntimeJsonMappingException(e.getMessage());
        }
    }
}
