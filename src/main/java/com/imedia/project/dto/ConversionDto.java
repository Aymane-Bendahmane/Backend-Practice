package com.imedia.project.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor@AllArgsConstructor
public class ConversionDto{
    public LocalDate date;
    public Info info;
    public Query query;
    public double result;
    public boolean success;
}

