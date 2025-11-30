package com.orbis.cinema.mapping;

public interface BaseMapper<D, M>{
    D toDto (M model);
    M toModel(D dto);
}
