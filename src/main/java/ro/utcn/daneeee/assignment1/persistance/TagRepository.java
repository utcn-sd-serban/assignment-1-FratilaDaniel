package ro.utcn.daneeee.assignment1.persistance;

import ro.utcn.daneeee.assignment1.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

    Tag save(Tag tag);

    Optional<Tag> findById(int id);

    Optional<Tag> findByContent(String searchedTtag);

    void remove(Tag user);

    List<Tag> findAll();
}
