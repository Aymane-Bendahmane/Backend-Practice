package com.imedia.project.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor@AllArgsConstructor
public class ConversionDto{
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    public Date date;
    public Info info;
    public Query query;
    public double result;
    public boolean success;
}

