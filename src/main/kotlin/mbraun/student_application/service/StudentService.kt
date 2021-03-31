package mbraun.student_application.service

import mbraun.student_application.model.Student
import mbraun.student_application.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentService(val studentRepository: StudentRepository) {

    fun getAllStudents(): MutableList<Student> {
        return studentRepository.findAll()
    }

    fun addStudent(student: Student) {
        studentRepository.save(student)
    }

    fun deleteStudent(studentId: Long) {
        studentRepository.deleteById(studentId)
    }
}