package com.codingCole.routes

import com.codingCole.data.entities.NoteEntity
import com.codingCole.data.models.dataClass.Note
import com.codingCole.data.models.dataClass.NoteRequest
import com.codingCole.data.models.responses.StatusResponse
import com.codingCole.db.DataBaseConnection
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.ktorm.dsl.*

private val db = DataBaseConnection.database

fun Route.noteRouter() {
    getNote()
    getNotes()
    insertNote()
    updateNote()
    deleteNote()
    home()
}

private fun Route.home() {
    get("/") {
        call.respondText("Well, Welcome", status = HttpStatusCode.NotFound)
    }
}


private fun Route.getNotes() {
    get("/notes") {

        val notes = db.from(NoteEntity).select()
            .map {
                val id = it[NoteEntity.id]
                val note = it[NoteEntity.note]
                Note(id ?: -1, note ?: "")
            }
        call.respond(notes)
    }
}

private fun Route.insertNote() {
    post("/notes") {
        val request = call.receive<NoteRequest>()
        val result = db.insert(NoteEntity) {
            set(it.note, request.note)
        }

        if (result == 1) {
            // Send successful response to the client
            call.respond(
                HttpStatusCode.OK, StatusResponse(
                    success = true,
                    data = "Values has been successfully inserted"
                )
            )
        } else {
            // Send failure response to the client
            call.respond(
                HttpStatusCode.BadRequest, StatusResponse(
                    success = false,
                    data = "Sorry, Something went wrong"
                )
            )
        }
    }
}

private fun Route.getNote() {
    get("/notes/{id}") {
        val id = call.parameters["id"]?.toInt() ?: -1
        val note = db.from(NoteEntity)
            .select()
            .where { NoteEntity.id eq id }
            .map {
                val id = it[NoteEntity.id]!!
                val note = it[NoteEntity.note]!!
                Note(id, note)
            }.firstOrNull()

        if (note == null) {
            call.respond(
                HttpStatusCode.NotFound,
                StatusResponse(
                    success = false,
                    data = "Could not find note with id = $id"
                )
            )
        } else {
            call.respond(
                HttpStatusCode.OK,
                StatusResponse(
                    success = true,
                    data = note
                )
            )

        }
    }
}

private fun Route.updateNote() {
    put("/notes/{id}") {
        val id = call.parameters["id"]?.toInt() ?: -1
        val updateNote = call.receive<NoteRequest>()

        val returned = db.update(NoteEntity) {
            set(it.note, updateNote.note)
            where { it.id eq id }
        }

        if (returned == 1) {
            call.respond(
                HttpStatusCode.OK,
                StatusResponse(
                    success = true,
                    data = "Note has been updated successfully"
                )
            )
        } else {
            call.respond(
                HttpStatusCode.BadRequest,
                StatusResponse(
                    success = false,
                    data = "Note failed to update"
                )
            )
        }
    }
}

private fun Route.deleteNote() {
    delete("/notes/{id}") {
        val id = call.parameters["id"]?.toInt() ?: -1
        val returned = db.delete(NoteEntity) {
            it.id eq id
        }
        if (returned == 1) {
            call.respond(
                HttpStatusCode.OK,
                StatusResponse(
                    success = true,
                    data = "Note has been deleted successfully"
                )
            )
        } else {
            call.respond(
                HttpStatusCode.BadRequest,
                StatusResponse(
                    success = false,
                    data = "Note failed to delete"
                )
            )
        }

    }
}