package com.codingCole.data.entities

import org.ktorm.schema.Table
import org.ktorm.schema.int

object DOBEntity: Table<Nothing>("dob") {
    val id = int("id").primaryKey()
    val day = int("day")
    val month = int("month")
    val year = int("year")
}

//todo create table in mysql