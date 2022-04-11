package pl.ganda.recruitmenttask.teacher;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.ganda.recruitmenttask.exception.NoRecordException;
import pl.ganda.recruitmenttask.student.StudentRepository;
import pl.ganda.recruitmenttask.student.model.Student;
import pl.ganda.recruitmenttask.student.model.StudentResponseDTO;
import pl.ganda.recruitmenttask.teacher.model.Teacher;
import pl.ganda.recruitmenttask.teacher.model.TeacherRequestDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Transactional
    public List<Teacher> getTeachers(Pageable pageable) {
        return teacherRepository.findAll(pageable).getContent();
    }

    @Transactional
    public Optional<Teacher> getTeacherById(long teacherId) {
        return teacherRepository.findById(teacherId);
    }

    @Transactional
    public List<Teacher> getTeacherByNameOrSurname(String teacherName, String teacherSurname, Pageable pageable) {
        return teacherRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(teacherName,teacherSurname, pageable).getContent();
    }

    public Teacher addTeacher(TeacherRequestDTO teacherRequest) {
        return teacherRepository.save(new Teacher(teacherRequest));
    }

    public Teacher assignStudent(long studentId, long teacherId) throws IllegalArgumentException {
        Student student = studentRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(IllegalArgumentException::new);
        teacher.addStudent(student);
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(long teacherId) throws NoRecordException {
        try {
            teacherRepository.deleteById(teacherId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NoRecordException("No user with id: " + teacherId);
        }
    }

    public Teacher deleteStudent(long studentId, long teacherId) throws IllegalArgumentException {
        Student student = studentRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(IllegalArgumentException::new);
        teacher.removeStudent(student);
        return teacherRepository.save(teacher);
    }

    public Set<StudentResponseDTO> getStudents(long teachersId) throws IllegalArgumentException {
        Teacher teacher = teacherRepository.findById(teachersId).orElseThrow(IllegalArgumentException::new);
        return teacher.getStudents().stream().map(StudentResponseDTO::new).collect(Collectors.toSet());
    }

}
