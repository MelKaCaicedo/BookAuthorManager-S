package ec.edu.espe.bookauthormanager.repositories;

import ec.edu.espe.bookauthormanager.models.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {
    private List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
    }

    public List<Book> getAll() {
        return new ArrayList<>(books);
    }

    public Optional<Book> getOne(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(String isbn, Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(isbn)) {
                books.set(i, updatedBook);
                return;
            }
        }
    }

    public void deleteBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }
}
