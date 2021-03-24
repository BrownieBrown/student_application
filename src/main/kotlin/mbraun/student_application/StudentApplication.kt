package mbraun.student_application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StudentApplication

fun main(args: Array<String>) {
	runApplication<StudentApplication>(*args)
}
