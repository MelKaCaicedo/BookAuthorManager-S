package ec.edu.espe.bookauthormanager.services;

import ec.edu.espe.bookauthormanager.models.Book;
import ec.edu.espe.bookauthormanager.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    public Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.getOne(isbn);
    }

    public void createBook(Book book) {
        bookRepository.addBook(book);
    }

    public void updateBook(Book updatedBook) {
        String isbn = updatedBook.getIsbn();
        Optional<Book> existingBook = findBookByIsbn(isbn);
        existingBook.ifPresent(b -> bookRepository.updateBook(isbn, updatedBook));
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteBook(isbn);
    }
}
