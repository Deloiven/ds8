package org.example.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.model.Note;
import org.example.service.NoteService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    /**
     * Добавление(создание) новой заметки
     * @param note
     * @return
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note){
        note.setCreatedDate(LocalDateTime.now());
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.CREATED);
    }


    /**
     * Просмотр всех заметок
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAll(){
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    /**
     * Получение заметки по ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(@PathVariable("id") Long id) {
        Note noteById;
        try {
            noteById = noteService.getNoteById(id);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
        return new ResponseEntity<>(noteById, HttpStatus.OK);
    }

    /**
     * Редактирование заметки по id
     * @param note
     * @return
     */

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@RequestBody Note note, @PathVariable("id") Long id) {
        note.setId(id);
        return new ResponseEntity<>(noteService.updateNote(note), HttpStatus.OK);
    }


    /**
     * Удаление заметки по id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id){
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }


}