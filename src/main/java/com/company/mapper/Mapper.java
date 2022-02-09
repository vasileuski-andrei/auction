package com.company.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
