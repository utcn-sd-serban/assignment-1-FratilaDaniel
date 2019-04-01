package ro.utcn.daneeee.assignment1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.utcn.daneeee.assignment1.model.Question;
import ro.utcn.daneeee.assignment1.model.User;
import ro.utcn.daneeee.assignment1.persistance.RepositoryFactory;
import ro.utcn.daneeee.assignment1.persistance.memory.InMemoryRepositoryFactory;
import ro.utcn.daneeee.assignment1.service.QuestionManagementService;
import ro.utcn.daneeee.assignment1.service.UserManagementService;

public class Assignment1ApplicationTests {


	private RepositoryFactory createUserTestData(){
		RepositoryFactory factory = new InMemoryRepositoryFactory();
		factory.createUserRepository().save(new User(1,"dummyuser1", "password1"));
		factory.createUserRepository().save(new User(2, "dummyuser2", "password2"));
		factory.createUserRepository().save(new User(3, "dummyuser3", "password3"));
		return factory;
	}

	@Test
	public void testRemoveUser(){

		RepositoryFactory factory  = createUserTestData();
		UserManagementService s = new UserManagementService(factory);

		s.removeUser(2);
		Assert.assertEquals(2, factory.createUserRepository().findAll().size());
		Assert.assertTrue(factory.createUserRepository().findById(1).isPresent());
		Assert.assertTrue(factory.createUserRepository().findById(3).isPresent());


	}


	private RepositoryFactory createQuestionTestData(){
		RepositoryFactory factory = new InMemoryRepositoryFactory();
		factory.createQuestionRepository().save(new Question(1,"dummytitle1", "dummytext1",2008,10,4));
		factory.createQuestionRepository().save(new Question(2,"dummytitle2", "dummytext2",2015,9,22));
		factory.createQuestionRepository().save(new Question(2,"dummytitle3", "dummytext3",2006,3,13));
		return factory;
	}

	@Test
	public void testAddQuestion(){

		RepositoryFactory factory  = createQuestionTestData();
		QuestionManagementService s = new QuestionManagementService(factory);

		s.addQuestion(4,"dummytitle4","dummytext5",2003,10,10);
		Assert.assertEquals(4, factory.createQuestionRepository().findAll().size());
	}

}
