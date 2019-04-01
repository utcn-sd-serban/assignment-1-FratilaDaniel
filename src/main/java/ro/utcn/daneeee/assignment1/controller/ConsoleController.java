package ro.utcn.daneeee.assignment1.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ro.utcn.daneeee.assignment1.exception.QuestionNotFoundException;
import ro.utcn.daneeee.assignment1.exception.TagNotFoundException;
import ro.utcn.daneeee.assignment1.exception.UserNotFoundException;
import ro.utcn.daneeee.assignment1.model.Question;
import ro.utcn.daneeee.assignment1.model.QuestionTagAssociation;
import ro.utcn.daneeee.assignment1.model.Tag;
import ro.utcn.daneeee.assignment1.model.User;
import ro.utcn.daneeee.assignment1.service.QuestionManagementService;
import ro.utcn.daneeee.assignment1.service.QuestionTagAssociationManagementService;
import ro.utcn.daneeee.assignment1.service.TagManagementService;
import ro.utcn.daneeee.assignment1.service.UserManagementService;

import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleController implements CommandLineRunner {

    private final Scanner scanner = new Scanner(System.in);
    private final UserManagementService userManagementService;
    private final QuestionManagementService questionManagementService;
    private final TagManagementService tagManagementService;
    private final QuestionTagAssociationManagementService questionTagAssociationManagementService;

    @Override
    public void run(String... args) {
        print("App like Stack Overflow but better, please log in");

        String pass = "", correctPass = "";
        do{
            print("Username: ");
            String user = scanner.next().trim();
            print("Password: ");
            pass = scanner.next().trim();

            try {
                correctPass = userManagementService.findByUsername(user).getPass();
            }catch(UserNotFoundException e){
                print("try again");
            }

        }while (!correctPass.equals(pass));


        boolean done = false;
        while (!done) {
            print("Enter a command: ");
            String command = scanner.next().trim();
            try {
                done = handleCommand(command);
            } catch (UserNotFoundException userNotFoundException) {
                print("The user with the given ID was not found!");
            }catch (QuestionNotFoundException quesstionNotFoundException) {
                print("The question with the given ID was not found!");
            }catch (TagNotFoundException tagNotFoundException) {
                //am cautat text si nu am gasit, deci fac un tag nou
                if(tagNotFoundException.id == -1){
                    Tag tag = tagManagementService.addTag(tagNotFoundException.text);
                    print("Created tag " + tag + ".");
                }
                else { // am cautat id si nu am gasit, doar spun ca nu exista tag
                    print("The tag with the ID " + tagNotFoundException.id + " was not found!");
                }
            }
        }
    }

    private boolean handleCommand(String command) {
        switch (command) {
            case "listusers":
                handleListUsers();
                return false;
            case "adduser":
                handleAddUser();
                return false;
            case "updateuser":
                handleUpdatePassword();
                return false;
            case "removeuser":
                handleRemoveUser();
                return false;

            case "listquestions":
                handleListQuestions();
                return false;
            case "filterquestionstext":
                handleFilterQuestionsText();
                return false;
            case "addquestion":
                handleAddQuestion();
                return false;
            case "removequestion":
                handleRemoveQuestion();
                return false;

            case "listtags":
                handleListTags();
                return false;
            case "addtag":
                handleAddTag();
                return false;

            case "listassociations":
                handleListAssoctiations();
                return false;
            case "filterassociations":
                handleAssociationsFilterByTag();
                return false;



            case "exit":
                return true;
            default:
                print("Unknown command. Try again.");
                return false;
        }
    }

    private void handleListUsers() {
        userManagementService.listUsers().forEach(s -> print(s.toString()));
    }

    private void handleListQuestions() {
        questionManagementService.listQuestions().forEach(s -> print(s.toString()));
    }

    private void handleListTags() {
        tagManagementService.listTags().forEach(s -> print(s.toString()));
    }

    private void handleListAssoctiations() {
        questionManagementService
                .listQuestions()
                .forEach(s -> {
                    print(questionManagementService.findById(s.getId()).toString());
                    print("tags associated: ");
                    questionTagAssociationManagementService.filteredAssoctiationsByQuestion(s.getId()).forEach(t ->
                            print(tagManagementService.findTagById(t).toString()));
                    print("---");
                });
    }

    private void handleAssociationsFilterByTag(){
        print("Filter by tag:");
        String text = scanner.next();
        Tag t = tagManagementService.findTagByText(text);

        questionTagAssociationManagementService.filteredAssoctiationsByTag(t.getId()).forEach(s ->
            print(questionManagementService.findById(s).toString())
        );
    }


    private void handleAddUser() {
        print("Username:");
        String username = scanner.next().trim();
        print("Password:");
        String pass = scanner.next().trim();
        User user = userManagementService.addUser(username, pass);
        print("Created user: " + user + ".");
    }

    private void handleAddQuestionTag() {
        print("Question Id:");
        int qid = scanner.nextInt();
        print("Tag Id:");
        int tid = scanner.nextInt();
        QuestionTagAssociation assoc = questionTagAssociationManagementService.addAssociation(qid, tid);
        print("Created association: " + assoc + ".");
    }

    private void handleAddQuestion() {
        print("Author id:");
        Integer authorId = scanner.nextInt();
        print("Title:");
        String title = scanner.next().trim();
        print("Text:");
        String text = scanner.next().trim();

        print("Year:");
        Integer year = scanner.nextInt();
        print("Month:");
        Integer month = scanner.nextInt();
        print("Day:");
        Integer day = scanner.nextInt();

        Question question = new Question(authorId, title, text, year, month, day);

        print("Created question: " + question + ".");
    }

    private void handleAddTag(){
        print("Tag description:");
        String description = scanner.next().trim();
        if(!tagManagementService.findTagByText(description).equals(null)){
            print("Tag already exists");
        }
    }

    private void handleUpdatePassword() {
        print("User ID:");
        int id = scanner.nextInt();
        print("New password:");
        String pass = scanner.next().trim();
        userManagementService.updatePassword(id, pass);
    }

    private void handleRemoveUser() {
        print("User ID:");
        int id = scanner.nextInt();
        userManagementService.removeUser(id);
    }

    private void handleRemoveQuestion() {
        print("Question ID:");
        int id = scanner.nextInt();
        questionManagementService.removeQuestion(id);
    }

    private void print(String value) {
        System.out.println(value);
    }




    private void handleFilterQuestionsText(){
        print("Filter by title:");
        scanner.nextLine();
        String text = scanner.nextLine();
        Question q = questionManagementService.findByTitle(text);
        print("Questions filtered by title: " + q + ".");
    }
}
