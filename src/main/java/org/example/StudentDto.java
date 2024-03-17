package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StudentDto {
    public int id;
    String first_name;
    String middle_name;
    String last_name;
    String date_of_birth;

}