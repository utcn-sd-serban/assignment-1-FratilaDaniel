package ro.utcn.daneeee.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.daneeee.assignment1.exception.TagNotFoundException;
import ro.utcn.daneeee.assignment1.model.Tag;
import ro.utcn.daneeee.assignment1.persistance.RepositoryFactory;
import ro.utcn.daneeee.assignment1.persistance.TagRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Tag> listTags(){
        return repositoryFactory.createTagRepository().findAll();
    }

    @Transactional
    public Tag addTag(String content){
        return repositoryFactory.createTagRepository().save(new Tag(content));
    }

    @Transactional
    public void removeTag(int id){
        TagRepository repository = repositoryFactory.createTagRepository();
        Tag tag = repository.findById(id).orElseThrow(() -> new TagNotFoundException(id));
        repository.remove(tag);
    }

    @Transactional
    public Tag findTagById(int id){
        TagRepository repository = repositoryFactory.createTagRepository();
        Tag tag = repository.findById(id).orElseThrow(() -> new TagNotFoundException(id));
        return tag;
    }

    @Transactional
    public Tag findTagByText(String text){
        TagRepository repository = repositoryFactory.createTagRepository();
        Tag tag = repository.findByContent(text).orElseThrow(() -> new TagNotFoundException(text));
        return tag;
    }
}
