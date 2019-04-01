package ro.utcn.daneeee.assignment1.exception;


public class TagNotFoundException extends RuntimeException {
    public Integer id;
    public String text;

    public TagNotFoundException(int id){
        this.id = id;
        this.text = "";
    }
    public TagNotFoundException(String text){
        this.id = -1;
        this.text = text;
    }
}
