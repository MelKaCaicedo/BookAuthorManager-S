package ec.edu.espe.bookauthormanager.controllers;

import ec.edu.espe.bookauthormanager.models.Book;
import ec.edu.espe.bookauthormanager.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> index() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping(value = "/{isbn}")
    public ResponseEntity<Book> show(@PathVariable String isbn) {
        Optional<Book> book = bookService.findBookByIsbn(isbn);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> store(@Valid @RequestBody Book book) {
        bookService.createBook(book);
        return new ResponseEntity<>("Se ha creado un nuevo libro :)", HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{isbn}")
    public ResponseEntity<String> update(@PathVariable String isbn, @Valid @RequestBody Book updatedBook) {
        updatedBook.setIsbn(isbn); // Aseguramos que el libro actualizado tenga el mismo ISBN que el libro existente
        bookService.updateBook(updatedBook);
        return new ResponseEntity<>("Se ha actualizado el libro :)", HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{isbn}")
    public ResponseEntity<String> destroy(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return new ResponseEntity<>("Se ha eliminado el libro :)", HttpStatus.OK);
    }
}
