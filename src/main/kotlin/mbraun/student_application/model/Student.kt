package mbraun.student_application.model

data class Student (
    val id: Long,
    val name: String,
    val email: String,
    val gender: Gender) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        if (id != other.id) return false
        if (name != other.name) return false
        if (email != other.email) return false
        if (gender != other.gender) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + gender.hashCode()
        return result
    }

    override fun toString(): String {
        return "Student(id=$id, name='$name', email='$email', gender=$gender)"
    }


}
