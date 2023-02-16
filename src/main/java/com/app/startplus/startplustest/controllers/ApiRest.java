package com.app.startplus.startplustest.controllers;

import com.app.startplus.startplustest.dto.MarvelsCharactersDtoResponse;
import com.app.startplus.startplustest.dto.UserInformationResponse;
import com.app.startplus.startplustest.models.marvelResponse.MarvelsCharactersResponse;
import com.app.startplus.startplustest.services.MarvelService;
import com.app.startplus.startplustest.services.RandomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ApiRest {

    @Autowired
    private RandomUserService randomUserService;

    @Autowired
    private MarvelService marvelService;

    @GetMapping("/random-user")
    public ResponseEntity<UserInformationResponse> getInformationUser() {
        return ResponseEntity.ok(this.randomUserService.readResponse());
    }

    @GetMapping("/marvel-api")
    public ResponseEntity<MarvelsCharactersDtoResponse> getMarvelCharacters() {
        return ResponseEntity.ok(this.marvelService.getAllCharacters());
    }

    @GetMapping("/marvel-api/{characterId}")
    public ResponseEntity<MarvelsCharactersResponse> getMarvelCharacterById(
            @PathVariable(name = "characterId")
            Long characterId
    ) {
        if (characterId == null) {
            characterId = 1017300L;
        }
        return ResponseEntity.ok(this.marvelService.getById(characterId));
    }

}
