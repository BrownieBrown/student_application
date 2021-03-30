package mbraun.student_application.model

import javax.persistence.*


@Entity
@Table
data class Student (
    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(generator = "student_sequence", strategy = GenerationType.SEQUENCE)
    val id: Long = -1,
    var name: String = "",
    var email: String = "",
    @Enumerated(EnumType.STRING)
    var gender: Gender = Gender.OTHER) {

    constructor(name: String, email: String, gender: Gender) : this() {
        this.name = name
        this.email = email
        this.gender = gender
    }
}



