package com.springreactive.learning.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log

public class Person {
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String designation;

}
