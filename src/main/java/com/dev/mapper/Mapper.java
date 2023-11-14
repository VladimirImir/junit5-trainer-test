package com.dev.mapper;

public interface Mapper<F, T> {

    T map(F object);
}
