package com.codingCole.data.entities

import org.ktorm.schema.Table
import org.ktorm.schema.enum
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object StudentEntity: Table<Nothing>("student") {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val matricNumber = int("matricNumber")
    val level = int("level")
    val cohort = varchar("cohort")
    val department = varchar("department")
    val email = varchar("email")
    val dob = varchar("dob")
    val modeOfEntry = varchar("modeOfEntry")
    val imageProfile = varchar("imageProfile")
}

//todo create table in mysql