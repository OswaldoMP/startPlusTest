package com.app.startplus.startplustest.models.randomUserResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Info {
    private String seed;
    private int results;
    private int page;
    private String version;
}
