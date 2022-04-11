# pl.ganda.recruitmenttask

## Prerequirements

1. Java 17
2. springVersion = 2.6.6
3. Gradle 7.4.1

## Build & run
To build use:   
./gradlew build     
./gradlew bootrun

## Api documentation
Aplication runs on port 8080.

### Endpoints for Student
**Request**  
Method: GET     
http://localhost:8080/school/students

**Response**
Show all students 

**Request**  
Method: GET     
http://localhost:8080/school/students/{studentId}

**Response**
Show a particular student

**Request**  
Method: GET     
http://localhost:8080/school/students?name=x&surname=y

**Response**
Show a particular student where x is a part of student's name and y is a part of student's surname

**Request**  
Method: POST       
http://localhost:8080/school/students

**Response**
Add new Student from json file

**Request**  
Method: POST    
http://localhost:8080/school/students/{studentId}/assignteacher/{teacherId}

**Response**
Add record to student's teachers list

**Request**  
Method: DELETE  
http://localhost:8080/school/students/{studentId}/deleteteacher/{teacherId}

**Response**
Delete record from student's teachers list

**Request**  
Method: DELETE  
http://localhost:8080/school/students/{studentId}

**Response**
Delete a particular student

**Request**  
Method: GET     
http://localhost:8080/school/students/{studentId}/teachers

**Response**
Show all record from student's teachers list

### Endpoints for Teacher

**Request**  
Method: GET     
http://localhost:8080/school/teachers

**Response**
Show all teachers

**Request**  
Method: GET     
http://localhost:8080/school/teachers/{teacherId}

**Response**
Show a particular teacher

**Request**  
Method: GET     
http://localhost:8080/school/teachers?name=x&surname=y

**Response**
Show a particular teachers where x is a part of teacher's name and y is a part of teacher's surname

**Request**  
Method: POST        
http://localhost:8080/school/teachers

**Response**
Add new Teacher from json file

**Request**         
Method: POST    
http://localhost:8080/school/teachers/{teacherId}/assignstudent/{studentId}

**Response**
Add record to teacher's students list

**Request**  
Method: DELETE  
http://localhost:8080/school/teachers/{teacherId}/deletestudent/{studentId}

**Response**
Delete record from teacher's students list

**Request**  
Method: DELETE  
http://localhost:8080/school/teachers/{teacherId}

**Response**
Delete a particular teacher

**Request**  
Method: GET     
http://localhost:8080/school/teachers/{teacherId}/students

**Response**
Show all record from teacher's students list