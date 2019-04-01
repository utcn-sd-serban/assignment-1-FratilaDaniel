package ro.utcn.daneeee.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.daneeee.assignment1.exception.QuestionTagAssociationNotFoundException;
import ro.utcn.daneeee.assignment1.model.QuestionTagAssociation;
import ro.utcn.daneeee.assignment1.persistance.QuestionTagAssociationRepository;
import ro.utcn.daneeee.assignment1.persistance.RepositoryFactory;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionTagAssociationManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<QuestionTagAssociation> listAssociations(){
        return repositoryFactory.createQuestionTagAssociationRepository().findAll();
    }

    public List<Integer> filteredAssoctiationsByTag(int id){
        return repositoryFactory.createQuestionTagAssociationRepository().findByTagId(id);
    }

    public List<Integer> filteredAssoctiationsByQuestion(int id){
        return repositoryFactory.createQuestionTagAssociationRepository().findByQuestionId(id);
    }


    @Transactional
    public QuestionTagAssociation addAssociation(int questionId, int tagId){
        return repositoryFactory.createQuestionTagAssociationRepository().save(new QuestionTagAssociation(questionId, tagId));
    }

    @Transactional
    public void removeAssociation(int id){
        QuestionTagAssociationRepository repository = repositoryFactory.createQuestionTagAssociationRepository();
        QuestionTagAssociation association = repository.findById(id).orElseThrow(QuestionTagAssociationNotFoundException::new);
        repository.remove(association);
    }
}
