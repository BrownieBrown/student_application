package mbraun.student_application.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class StudentNotFoundException(msg: String) : RuntimeException(msg)