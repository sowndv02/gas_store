/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dto;


/**
 *
 * @author ADMIN
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleUserInfoFetcher {
    private static final String USER_INFO_ENDPOINT = "https://www.googleapis.com/oauth2/v1/userinfo";
    private static final String ACCESS_TOKEN_PARAM = "access_token";

    public String getUserInfo(String accessToken) throws IOException {
        String userInfoUrl = USER_INFO_ENDPOINT + "?" + ACCESS_TOKEN_PARAM + "=" + accessToken;

        URL url = new URL(userInfoUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            throw new IOException("Unable to fetch user information. Response Code: " + responseCode);
        }
    }
}

