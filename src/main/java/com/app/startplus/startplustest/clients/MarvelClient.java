package com.app.startplus.startplustest.clients;


import com.app.startplus.startplustest.models.marvelResponse.MarvelsCharactersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MarvelClient {

    @GET("public/characters")
    Call<MarvelsCharactersResponse> getAllCharacters(
            @Query("ts") int ts,
            @Query("apikey") String apikey,
            @Query("hash") String hash,
            @Query("offset") int offset,
            @Query("limit") int limit
    );

    @GET("public/characters/{characterId}")
    Call<MarvelsCharactersResponse> getCharacterById(
            @Path("characterId")
            long characterId,
            @Query("ts") int ts,
            @Query("apikey") String apikey,
            @Query("hash") String hash
    );
}
