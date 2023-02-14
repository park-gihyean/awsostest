package com.example.jisoo.model;

import lombok.Data;

@Data       
public class Secret {
    private int secNo;
    private String secTitle;
    private String secContent;
    private String secWriter;
    public String secIcon;
}
