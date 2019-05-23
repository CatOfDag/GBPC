package com.huse.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AjaxResult {
    private boolean res;
    private String info;
}
