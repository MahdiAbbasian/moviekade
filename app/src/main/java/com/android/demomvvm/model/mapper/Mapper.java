package com.android.demomvvm.model.mapper;

interface Mapper<Local, Entity> {

    fun mapTo(value<Entity> entity): Local
    fun mapFrom(value<Local> local): Entity

}
