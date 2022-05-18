package com.vanthuandev.doanphanmem.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPageUtils {
    private int page;
    private int maxPage;
    private int offset;
    private int limit;
    private String name;
    private String cmnd;
    private List<Integer> id;
}
