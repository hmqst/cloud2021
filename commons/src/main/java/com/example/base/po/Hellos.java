package com.example.base.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author benben
 * @program cloud
 * @Description
 * @date 2021-04-27 14:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hellos {
    private Integer id;

    private List<String> names;
}
