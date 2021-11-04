package com.codingCole.db

import org.ktorm.database.Database

object DataBaseConnection {

    val database = Database.connect(
        url = "jdbc:mysql://s465z7sj4pwhp7fn.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/lgizddihap2fts0v",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "h9zl8rzfk1ncdnzj",
        password = "s6dsdle44rfnehhp"
    )
}