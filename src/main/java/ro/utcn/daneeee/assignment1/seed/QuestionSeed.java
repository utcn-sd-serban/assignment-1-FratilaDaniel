package ro.utcn.daneeee.assignment1.seed;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ro.utcn.daneeee.assignment1.model.Question;
import ro.utcn.daneeee.assignment1.model.User;
import ro.utcn.daneeee.assignment1.persistance.QuestionRepository;
import ro.utcn.daneeee.assignment1.persistance.RepositoryFactory;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class QuestionSeed implements CommandLineRunner {

    private final RepositoryFactory factory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        QuestionRepository repository = factory.createQuestionRepository();

        if(repository.findAll().isEmpty()) {

            Calendar c1 = Calendar.getInstance();
            c1.set(2015, 3, 7);

            Calendar c2 = Calendar.getInstance();
            c2.set(Calendar.YEAR, 2019);
            c2.set(Calendar.MONTH, 11);
            c2.set(Calendar.DATE, 3);

            Calendar c3 = Calendar.getInstance();
            c3.set(Calendar.YEAR, 2018);
            c3.set(Calendar.MONTH, 0);
            c3.set(Calendar.DATE, -1);

            Date date1 = c1.getTime();
            Date date2 = c2.getTime();
            Date date3 = c3.getTime();



            repository.save(new Question(1,
                                        "Do dogs eat cats?",
                                        "DummyText1",
                                        date1)
            );
            repository.save(new Question(1,
                                        "Do birds fly?",
                                        "DummyText2",
                                        date2)
            );
            repository.save(new Question(3,
                                        "Do cats fly?",
                                        "DummyText1",
                                        date3)
            );
        }
    }
}
