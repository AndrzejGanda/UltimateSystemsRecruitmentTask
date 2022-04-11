package pl.ganda.recruitmenttask.student.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class StudentRequestDTO {

    @NotBlank(message = "name property is mandatory")
    @Size(min = 3)
    private String name;

    @NotBlank(message = "surname property is mandatory")
    @Size(min = 3)
    private String surname;

    @NotNull(message = "age property is mandatory")
    @Min(value = 19, message = "age property must be min 18")
    private int age;

    @Email(message = "Email is not valid", regexp = "^(.+)@(\\S+)$")
    @NotEmpty(message = "Email cannot be empty")
    private String mail;

    private String fieldOfStudy;
}
