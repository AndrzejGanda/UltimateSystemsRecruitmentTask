package pl.ganda.recruitmenttask.teacher.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class TeacherResponseDTO implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String mail;
    private String subject;

    public TeacherResponseDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.surname = teacher.getSurname();
        this.age = teacher.getAge();
        this.mail = teacher.getMail();
        this.subject = teacher.getSubject();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherResponseDTO that = (TeacherResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
