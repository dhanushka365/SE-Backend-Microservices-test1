package com.service.teacher;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeacherAutheticationRequest {
    private String email;
    private String  password;
}
