package com.imedia.project.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Query {
    public double amount;
    public String from;
    public String to;
}
