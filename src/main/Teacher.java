package application;
public class Teacher {
    private String name;
    private String major;

    public Teacher(String name, String major) {
        this.name = name;
        this.major = major;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

