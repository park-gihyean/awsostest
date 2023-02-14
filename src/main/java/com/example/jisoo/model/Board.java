package com.example.jisoo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import lombok.Data;

@Data       
public class Board {
    private int boardNo;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;


}
