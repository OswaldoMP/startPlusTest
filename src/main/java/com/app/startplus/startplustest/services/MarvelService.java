package com.app.startplus.startplustest.services;

import com.app.startplus.startplustest.clients.MarvelClient;
import com.app.startplus.startplustest.dto.CharactersDto;
import com.app.startplus.startplustest.dto.MarvelsCharactersDtoResponse;
import com.app.startplus.startplustest.errors.ApiError;
import com.app.startplus.startplustest.interfaces.IMarvel;

import com.app.startplus.startplustest.models.marvelResponse.MarvelsCharactersResponse;
import com.app.startplus.startplustest.models.marvelResponse.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MarvelService implements IMarvel {

    @Autowired
    private MarvelClient marvelClient;

    @Value("${webServices.marvel.keys.public}")
    private String PUBLIC_KEY;

    @Value("${webServices.marvel.keys.hash}")
    private String HASH_KEY;

    @Value("${webServices.marvel.keys.ts}")
    private int TS;

    @Override
    public MarvelsCharactersDtoResponse getAllCharacters() {
        MarvelsCharactersResponse marvelsCharactersResponse = this.requestAllCharacters();
        log.info("[RESPONSE MARVEL API] SUCCESS");

        int charactersTotal = marvelsCharactersResponse.getData().getTotal();

        List<CharactersDto> charactersDtoList = marvelsCharactersResponse.getData().getResults()
                .stream().map(this::buildMarvelCharacters).collect(Collectors.toList());

        log.info("[CHARACTERS TOTAL] {}", charactersTotal);

        return this.buildDtoResponse(charactersTotal, charactersDtoList);

    }

    @Override
    public MarvelsCharactersResponse getById(long id) {
        return this.requestCharactersById(id);
    }

    private MarvelsCharactersResponse requestAllCharacters() {
        try {
            log.info("[INITIAL REQUEST TO MARVEL API]");
            Response<MarvelsCharactersResponse> response = marvelClient
                    .getAllCharacters(TS, PUBLIC_KEY, HASH_KEY, 0, 100)
                    .execute();

            if (response.isSuccessful()) {
                return response.body();
            }
            throw new ApiError(HttpStatus.CONFLICT.value(), "Problems with MARVEL API, Try again");
        } catch (IOException e) {
            log.info("[ERROR REQUEST TO MARVEL API] {}", e.getMessage());
            throw new ApiError(HttpStatus.CONFLICT.value(), "Problems with MARVEL API, Try again");
        }
    }

    private MarvelsCharactersResponse requestCharactersById(long id) {
        try {
            log.info("[INITIAL REQUEST TO MARVEL API]");
            Response<MarvelsCharactersResponse> response = marvelClient
                    .getCharacterById(id, TS, PUBLIC_KEY, HASH_KEY)
                    .execute();

            if (response.isSuccessful()) {
                return response.body();
            }
            throw new ApiError(HttpStatus.CONFLICT.value(), "Problems with MARVEL API, Try again");
        } catch (IOException e) {
            log.info("[ERROR REQUEST TO MARVEL API] {}", e.getMessage());
            throw new ApiError(HttpStatus.CONFLICT.value(), "Problems with MARVEL API, Try again");
        }
    }

    private CharactersDto buildMarvelCharacters(Result character) {
        return CharactersDto.builder()
                .id(character.getId())
                .name(character.getName())
                .build();
    }
    private MarvelsCharactersDtoResponse buildDtoResponse(int total, List<CharactersDto> characters) {
        return MarvelsCharactersDtoResponse.builder()
                .charactersTotal(total)
                .characters(characters)
                .build();
    }
}
