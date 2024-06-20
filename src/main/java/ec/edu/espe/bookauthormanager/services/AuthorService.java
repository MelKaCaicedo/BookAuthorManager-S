package ec.edu.espe.bookauthormanager.services;

import ec.edu.espe.bookauthormanager.models.Author;
import ec.edu.espe.bookauthormanager.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.getAll();
    }

    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.getOne(id);
    }

    public void createAuthor(Author author) {
        authorRepository.addAuthor(author);
    }

    public void updateAuthor(Author author) {
        Long id = author.getId();
        Optional<Author> existingAuthor = findAuthorById(id);
        existingAuthor.ifPresent(a -> authorRepository.updateAuthor(id, author));
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteAuthor(id);
    }
}
