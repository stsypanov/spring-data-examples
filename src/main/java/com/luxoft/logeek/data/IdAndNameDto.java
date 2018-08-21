package com.luxoft.logeek.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IdAndNameDto {
    private final int id;
    private final String name;
}
