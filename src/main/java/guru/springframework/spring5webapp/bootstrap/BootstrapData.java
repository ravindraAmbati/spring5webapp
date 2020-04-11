package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Starting "+this.getClass().getSimpleName());

        Publisher p1 = new Publisher();
        p1.setName("p1");
        p1.setAddressLine1("addressLine1");
        p1.setCity("city");
        p1.setState("state");

        publisherRepository.save(p1);


        Author a1 = new Author("A1","a1");
        Book b1 = new Book("b1","b123");
        a1.getBooks().add(b1);
        b1.getAuthors().add(a1);
        b1.setPublisher(p1);
        p1.getBooks().add(b1);

        authorRepository.save(a1);
        bookRepository.save(b1);


        Author a2 = new Author("A2","a2");
        Book b2 = new Book("b2","b456");
        a2.getBooks().add(b2);
        b2.getAuthors().add(a2);
        b2.setPublisher(p1);
        p1.getBooks().add(b2);

        authorRepository.save(a2);
        bookRepository.save(b2);

        System.out.println("Books count: "+bookRepository.count());
        System.out.println("Authors count: "+authorRepository.count());
        System.out.println("Publisher count: "+publisherRepository.count());
    }
}
