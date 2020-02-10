package com.marcoscouto.brazilianleague.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public abstract class AbstractClient {

    @Value("${api.url}")
    private String base_url;

    @Value("${api.host}")
    private String host;

    @Value("${api.hostValue}")
    private String hostValue;

    @Value("${api.key}")
    private String key;

    @Value("${api.keyValue}")
    private String keyValue;

    protected HttpURLConnection connection(String endpoint) {
        try {
            String url = base_url + endpoint;
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestProperty(host, hostValue);
            conn.setRequestProperty(key, keyValue);
            return conn;
        } catch (MalformedURLException e) {
            System.out.println("URL problem: " + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
