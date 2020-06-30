package com.sigp.test.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserData {
    private Long id;
    private String username;
    private String about;
    private int submitted;
    private LocalDateTime updated_at;
    private int submission_count;
    private int comment_count;
    private long created_at;
}
