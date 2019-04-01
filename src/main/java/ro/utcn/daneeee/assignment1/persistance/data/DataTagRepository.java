package ro.utcn.daneeee.assignment1.persistance.data;

import org.springframework.data.repository.Repository;
import ro.utcn.daneeee.assignment1.model.Tag;
import ro.utcn.daneeee.assignment1.persistance.TagRepository;

public interface DataTagRepository extends Repository<Tag, Integer>, TagRepository {

    void delete(Tag tag);

    @Override
    default void remove(Tag tag){
        delete(tag);
    }
}
