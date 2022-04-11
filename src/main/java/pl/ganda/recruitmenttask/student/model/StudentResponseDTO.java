package pl.ganda.recruitmenttask.student.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
public class StudentResponseDTO implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String mail;
    private String fieldOfStudy;

    public StudentResponseDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.surname = student.getSurname();
        this.age = student.getAge();
        this.mail = student.getMail();
        this.fieldOfStudy = student.getFieldOfStudy();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentResponseDTO that = (StudentResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
