public class Sinh_Vien {
    private String ID;
    private String Name;
    private String Gender;
    private int Age;

    public Sinh_Vien(String ID, String Name, String Gender, int Age) {
        this.ID = ID;
        this.Name = Name;
        this.Gender = Gender;
        this.Age = Age;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getGender() {
        return Gender;
    }

    public int getAge() {
        return Age;
    }
}