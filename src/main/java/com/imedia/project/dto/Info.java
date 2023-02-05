package com.imedia.project.dto;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class Info {
    public double rate;
    public Date timestamp;
}
