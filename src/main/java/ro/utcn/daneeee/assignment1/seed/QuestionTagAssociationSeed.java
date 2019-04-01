package ro.utcn.daneeee.assignment1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ro.utcn.daneeee.assignment1.model.QuestionTagAssociation;
import ro.utcn.daneeee.assignment1.persistance.QuestionRepository;
import ro.utcn.daneeee.assignment1.persistance.QuestionTagAssociationRepository;
import ro.utcn.daneeee.assignment1.persistance.RepositoryFactory;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class QuestionTagAssociationSeed implements CommandLineRunner {

    private final RepositoryFactory factory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        QuestionTagAssociationRepository repository = factory.createQuestionTagAssociationRepository();

        if(repository.findAll().isEmpty()) {
            repository.save(new QuestionTagAssociation(1, 1));
            repository.save(new QuestionTagAssociation(1, 2));
            repository.save(new QuestionTagAssociation(2, 3));
            repository.save(new QuestionTagAssociation(3, 1));
        }
    }

}
