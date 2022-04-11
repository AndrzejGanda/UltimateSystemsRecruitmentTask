package pl.ganda.recruitmenttask.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ganda.recruitmenttask.student.model.Student;
import pl.ganda.recruitmenttask.student.model.StudentRequestDTO;
import pl.ganda.recruitmenttask.teacher.model.TeacherResponseDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity<List<Student>> getStudents(Pageable pageable) {
        return ResponseEntity.ok(studentService.getStudents(pageable));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") long studentId) {
        Optional<Student> student = studentService.getStudentById(studentId);
        return student.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(student.get());
    }

    @GetMapping(params = { "name", "surname" })
    public ResponseEntity<List<Student>> getStudentByNameOrSurname(@RequestParam("name") String studentName,
                                                                   @RequestParam("surname") String studentSurname,
                                                                   Pageable pageable) {
        List<Student> students = studentService.getStudentByNameOrSurname(studentName, studentSurname, pageable);
        return students.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(students);
    }

    @PostMapping()
    public ResponseEntity<Student> addStudent(@RequestBody @Valid StudentRequestDTO student) {
        return ResponseEntity.ok(studentService.addStudent(student));
    }


    @PostMapping("/{studentId}/assignteacher/{teacherId}")
    public ResponseEntity<Student> assignTeacher(@PathVariable("studentId") long studentId, @PathVariable("teacherId") long teacherId) {
        return ResponseEntity.ok(studentService.assignTeacher(studentId, teacherId));
    }

    @DeleteMapping("/{studentId}/deleteteacher/{teacherId}")
    public ResponseEntity<Student> deleteTeacher(@PathVariable("studentId") long studentId, @PathVariable("teacherId") long teacherId) {
        return ResponseEntity.ok(studentService.deleteTeacher(studentId, teacherId));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{studentId}/teachers")
    public ResponseEntity<Set<TeacherResponseDTO>> getTeachersFromStudent(@PathVariable("studentId") long studentId) {
        Set<TeacherResponseDTO> teachers = studentService.getTeachers(studentId);
        return teachers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(teachers);
    }
}
