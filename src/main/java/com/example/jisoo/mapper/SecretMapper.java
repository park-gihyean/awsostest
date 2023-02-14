package com.example.jisoo.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.jisoo.model.Secret;


@Mapper
public interface SecretMapper {
    public ArrayList<Secret> boardList();

    public void Create(Secret secret);

    public void Remove(int secNo);

    public void Update(Secret secret);
    

    
}
