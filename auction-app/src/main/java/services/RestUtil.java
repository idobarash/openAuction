package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.AbstractEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public abstract class RestUtil {

    private static final String SERVER_URL = "http://localhost:8080/auction-server";

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T httpGet(String url, Class<T> type) {

        try {

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(SERVER_URL + url);
            HttpResponse response = client.execute(request);

            return objectMapper.readValue(response.getEntity().toString(), type);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
