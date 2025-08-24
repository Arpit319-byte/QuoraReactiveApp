package com.example.QuoraReactiveApp.Model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User extends BaseModel {
    private String username;
    private String email;
    private String password;
}
