package ec.edu.espe.bookauthormanager.controllers;

import ec.edu.espe.bookauthormanager.models.Author;
import ec.edu.espe.bookauthormanager.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> index() {
        List<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Author> show(@PathVariable Long id) {
        Optional<Author> author = authorService.findAuthorById(id);
        return author.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> store(@Valid @RequestBody Author author) {
        authorService.createAuthor(author);
        return new ResponseEntity<>("Se ha creado un nuevo autor :)", HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody Author updatedAuthor) {
        updatedAuthor.setId(id); // Aseguramos que el autor actualizado tenga el mismo ID que el autor existente
        authorService.updateAuthor(updatedAuthor);
        return new ResponseEntity<>("Se ha actualizado el autor :)", HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<String> destroy(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>("Se ha eliminado el autor :)", HttpStatus.OK);
    }
}
