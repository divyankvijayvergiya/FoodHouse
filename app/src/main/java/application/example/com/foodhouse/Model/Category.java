package application.example.com.foodhouse.Model;

/**
 * Created by Dell on 16-10-2017.
 */

public class Category {


    private String Name;
    private String Image;

    private Category(){

    }
    private Category(String Name, String Image){
        this.Name=Name;
        this.Image=Image;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
