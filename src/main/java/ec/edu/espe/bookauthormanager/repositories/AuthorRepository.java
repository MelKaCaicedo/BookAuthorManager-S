package ec.edu.espe.bookauthormanager.repositories;

import ec.edu.espe.bookauthormanager.models.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {
    private List<Author> authors;

    public AuthorRepository() {
        this.authors = new ArrayList<>();
    }

    public List<Author> getAll() {
        return new ArrayList<>(authors);
    }

    public Optional<Author> getOne(Long id) {
        return authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void updateAuthor(Long id, Author updatedAuthor) {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getId().equals(id)) {
                authors.set(i, updatedAuthor);
                return;
            }
        }
    }

    public void deleteAuthor(Long id) {
        authors.removeIf(author -> author.getId().equals(id));
    }
}
