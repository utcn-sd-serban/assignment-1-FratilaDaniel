package ro.utcn.daneeee.assignment1.persistance.memory;

import ro.utcn.daneeee.assignment1.model.Tag;
import ro.utcn.daneeee.assignment1.persistance.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryTagRepository implements TagRepository {


    private final Map<Integer, Tag> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public Tag save(Tag tag) {
        if(tag.getId() == null){
            tag.setId(currentId.incrementAndGet());
        }
        data.put(tag.getId(), tag);
        return tag;
    }

    @Override
    public Optional<Tag> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Optional<Tag> findByContent(String searchedTag) {
        List<Tag> tags = findAll();
        for(Tag currentTag: tags){
            if(currentTag.getContent().equals(searchedTag)){
                return Optional.ofNullable(currentTag);
            }
        }
        return Optional.empty();
    }


    @Override
    public void remove(Tag tag) {
        data.remove(tag.getId());
    }

    @Override
    public List<Tag> findAll() {
        return new ArrayList<>(data.values());
    }
}
