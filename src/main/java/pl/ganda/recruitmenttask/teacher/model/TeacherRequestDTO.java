package pl.ganda.recruitmenttask.teacher.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class TeacherRequestDTO {

    @NotBlank(message = "name property is mandatory")
    @Size(min = 3)
    private String name;

    @NotBlank(message = "surname property is mandatory")
    @Size(min = 3)
    private String surname;

    @NotNull(message = "age property is mandatory")
    @Min(value = 18, message = "age property must be min 19")
    private int age;

    @Email(message = "Email is not valid", regexp = "^(.+)@(\\S+)$")
    @NotEmpty(message = "Email cannot be empty")
    private String mail;

    private String subject;
}
