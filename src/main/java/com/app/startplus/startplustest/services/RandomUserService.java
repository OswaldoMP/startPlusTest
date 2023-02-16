package com.app.startplus.startplustest.services;

import com.app.startplus.startplustest.dto.UserInformationResponse;
import com.app.startplus.startplustest.models.randomUserResponse.RandomUserResponse;
import com.app.startplus.startplustest.errors.ApiError;
import com.app.startplus.startplustest.interfaces.IRandomUser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class RandomUserService implements IRandomUser {
    private final Logger logger = LoggerFactory.getLogger(RandomUserService.class);
    private final Gson gson = new Gson();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${webServices.randomUser.url}")
    private String RANDOM_USER_API;
    @Override
    public UserInformationResponse readResponse() {
        try {
            logger.info("[STARTING]");
            String urlString = RANDOM_USER_API;
            logger.info("[URL] {}", gson.toJson(urlString));

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            connection.setRequestProperty("Accept", MediaType.ALL_VALUE);

            logger.info("[FOR READING RESPONSE]");

            if (connection.getResponseCode() == HttpStatus.OK.value()) {
                RandomUserResponse randomUserResponse = this.mapperResponse(connection.getInputStream());
                logger.info("[RESPONSE] {}", gson.toJson(randomUserResponse));

                UserInformationResponse userInformationResponse = objectMapper.convertValue(randomUserResponse,
                        UserInformationResponse.class);
                connection.disconnect();

                return userInformationResponse;
            } else {
                connection.disconnect();
                throw new ApiError(HttpStatus.CONFLICT.value(), "Problems with RANDOM USER API, Try again");
            }

        } catch (IOException e) {
            logger.info(e.getMessage());
            throw new ApiError(500, "[INTERNAL ERROR] " + e.getMessage());
        }
    }

    private RandomUserResponse mapperResponse(InputStream inputStream) {
        try {
            JsonParser parser = objectMapper.createParser(inputStream);
            return objectMapper.readValue(parser, RandomUserResponse.class);
        } catch (IOException e) {
            throw new ApiError(500, "Problems to try convert random user response to an POJO");
        }

    }
}
