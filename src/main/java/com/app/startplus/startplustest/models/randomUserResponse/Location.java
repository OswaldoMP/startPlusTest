package com.app.startplus.startplustest.models.randomUserResponse;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private Street street;
    private String city;
    private String state;
    private String country;
    private int postcode;
    private Coordinates coordinates;
    private Timezone timezone;
}
