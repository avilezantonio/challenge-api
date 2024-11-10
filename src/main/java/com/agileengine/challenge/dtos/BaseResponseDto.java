package com.agileengine.challenge.dtos;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public abstract class BaseResponseDto {

    protected Long id;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;
}
