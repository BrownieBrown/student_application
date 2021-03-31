package mbraun.student_application.service

import mbraun.student_application.exception.BadRequestException
import mbraun.student_application.exception.StudentNotFoundException
import mbraun.student_application.model.Student
import mbraun.student_application.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentService(val studentRepository: StudentRepository) {

    fun getAllStudents(): MutableList<Student> {
        return studentRepository.findAll()
    }

    fun addStudent(student: Student) {
        val existsEmail = studentRepository.selectExistsEmail(student.email)

        if (existsEmail) {
            throw BadRequestException("The Email ${student.email} is already taken")
        }
        studentRepository.save(student)
    }

    fun deleteStudent(studentId: Long) {
        if (!studentRepository.existsById(studentId)) {
            throw StudentNotFoundException("The Student with id $studentId does not exist")
        }
        studentRepository.deleteById(studentId)
    }
}