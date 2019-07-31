package USER;

public class User {
    private String name;
    private String content;

    public User(){
        //default constructor for spring mapping
        //不省略
    }
    public User(String name ,String content){
        this.name = name;
        this.content = content;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name  = name;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
}
