package com.example.jisoo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.jisoo.model.User;

@Mapper
public interface UserMapper {
    public void join(User user);

    public String getPw(String id);

    public User selectUser(String id);

    public void userRemove(User user);
}
