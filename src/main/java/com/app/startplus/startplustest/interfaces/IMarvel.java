package com.app.startplus.startplustest.interfaces;

import com.app.startplus.startplustest.dto.MarvelsCharactersDtoResponse;
import com.app.startplus.startplustest.models.marvelResponse.MarvelsCharactersResponse;

public interface IMarvel {
    MarvelsCharactersDtoResponse getAllCharacters();

    MarvelsCharactersResponse getById(long id);

}
