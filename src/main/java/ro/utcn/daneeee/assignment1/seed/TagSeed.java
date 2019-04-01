package ro.utcn.daneeee.assignment1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ro.utcn.daneeee.assignment1.model.Tag;
import ro.utcn.daneeee.assignment1.persistance.RepositoryFactory;
import ro.utcn.daneeee.assignment1.persistance.TagRepository;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TagSeed implements CommandLineRunner {

    private final RepositoryFactory factory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        TagRepository repository = factory.createTagRepository();
        if(repository.findAll().isEmpty()){
            repository.save(new Tag("cats"));
            repository.save(new Tag("dogs"));
            repository.save(new Tag("birds"));
            repository.save(new Tag("alcohol"));
        }
    }
}
