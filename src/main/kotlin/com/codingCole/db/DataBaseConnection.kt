package com.codingCole.db

import org.ktorm.database.Database

object DataBaseConnection {

    val database = Database.connect(
        url = "jdbc:mysql://localhost:3306/nuesites",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "H#!5L0r4"
    )
}