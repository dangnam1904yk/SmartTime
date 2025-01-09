package com.vinschool.smarttime.service;

import java.util.List;

import com.vinschool.smarttime.entity.CategoryRoom;

public interface CategoryRoomSerive {
    CategoryRoom save(CategoryRoom room);

    CategoryRoom detail(String id);

    void delete(String id);

    List<CategoryRoom> getAll();
}
