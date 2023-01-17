package com.codeafrica.oracle.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse<T> {
    private boolean success;
    private String Message;
    private  T data;
}
