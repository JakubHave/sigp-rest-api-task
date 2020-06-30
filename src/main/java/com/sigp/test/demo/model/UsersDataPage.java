package com.sigp.test.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class UsersDataPage {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<UserData> data;
}
