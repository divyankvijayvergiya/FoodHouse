package application.example.com.foodhouse;

/**
 * Created by Dell on 15-10-2017.
 */

public class User {

    private String Name;
    private String password;
    public User(){

    }
    public User(String name, String password){
        this.password=password;
        this.Name=name;

    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
