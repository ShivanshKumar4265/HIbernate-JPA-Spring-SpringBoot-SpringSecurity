package myjdbc;


public class StudentModel {
    private int id;
    private String name;
    private String email;
    private String grade;

    // Default constructor
    public StudentModel() {}

    // Constructor without id (for new StudentModels)
    public StudentModel(String name, String email, String grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "StudentModel [id=" + id + ", name=" + name + ", age=" + ", email=" + email + ", grade=" + grade + "]\n";
    }
}
