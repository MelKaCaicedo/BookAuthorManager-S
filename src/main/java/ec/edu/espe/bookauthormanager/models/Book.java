package ec.edu.espe.bookauthormanager.models;

import lombok.*;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private String isbn;
    private String title;
    private Long authorId;
    private String publisher;
    private int year;
    private double price;
    private int pages;

    //private Author author;
    private List<Author> author;
}
