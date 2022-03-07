package classes;

import com.google.gson.annotations.SerializedName;


public class HelthItem {

    @SerializedName("ID")
    private int ID;

    @SerializedName("Name")
    private String Name;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
