package com.sampleProject.demoProject.controller;


import com.sampleProject.demoProject.repository.SimpleAppRepository;
import com.sampleProject.demoProject.model.SimpleApp;
import com.sampleProject.demoProject.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

    @RestController
    @RequestMapping("/api")

    public class SimpleAppController {

        @Autowired
        SimpleAppRepository simpleAppRepository;

        // Get All Notes

        @GetMapping("/notes")
        public List<SimpleApp> getAllNotes() {
            return simpleAppRepository.findAll();
        }

        // Create a new Note
        @PostMapping("/notes")
        public SimpleApp createNote(@Valid @RequestBody SimpleApp note) {
            return simpleAppRepository.save(note);
        }

        // Get a Single Note

        @GetMapping("/notes/{id}")
        public SimpleApp getNoteById(@PathVariable(value = "id") Long noteId) {
            return simpleAppRepository.findById(noteId)
                    .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        }



        // Update a Note


        @PutMapping("/notes/{id}")
        public SimpleApp updateNote(@PathVariable(value = "id") Long noteId,
                               @Valid @RequestBody SimpleApp noteDetails) {

            SimpleApp note = simpleAppRepository.findById(noteId)
                    .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

          /*  note.setTitle(noteDetails.getTitle());
            note.setContent(noteDetails.getContent());*/

            SimpleApp updatedNote = simpleAppRepository.save(note);
            return updatedNote;
        }



        // Delete a Note

        @DeleteMapping("/notes/{id}")
        public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
            SimpleApp note = simpleAppRepository.findById(noteId)
                    .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

            simpleAppRepository.delete(note);

            return ResponseEntity.ok().build();
        }
    }

