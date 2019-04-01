package ro.utcn.daneeee.assignment1.persistance.memory;

import ro.utcn.daneeee.assignment1.model.QuestionTagAssociation;
import ro.utcn.daneeee.assignment1.persistance.QuestionTagAssociationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryQuestionTagAssociationRepository implements QuestionTagAssociationRepository {
    private final Map<Integer, QuestionTagAssociation> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public QuestionTagAssociation save(QuestionTagAssociation association) {
        if(association.getId() == null){
            association.setId(currentId.incrementAndGet());
        }
        data.put(association.getId(), association);
        return association;
    }

    @Override
    public Optional<QuestionTagAssociation> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void remove(QuestionTagAssociation association) {
        data.remove(association.getId());
    }

    @Override
    public List<QuestionTagAssociation> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public List<Integer> findByTagId (int id){
        List<Integer> l = new ArrayList<Integer>();
        findAll().forEach(s -> {
            if(s.getTagId() == id){
                l.add(s.getQuestionId());
            }
        });
        return l;
    }


    @Override
    public List<Integer> findByQuestionId (int id){
        List<Integer> l = new ArrayList<Integer>();
        findAll().forEach(s -> {
            if(s.getQuestionId() == id){
                l.add(s.getTagId());
            }
        });
        return l;
    }

}
