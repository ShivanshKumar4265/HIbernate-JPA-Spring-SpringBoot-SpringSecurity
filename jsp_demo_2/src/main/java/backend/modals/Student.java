package backend.modals;

public class Student {
    private int id;
    private String name;
    private String mobile_number;
    private String email;
    private String address;


    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(int id, String name, String mobile_number, String email, String address) {
        this.id = id;
        this.name = name;
        this.mobile_number = mobile_number;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {
        return "ID : " + id + " Name :" + name + " Mobile :" + mobile_number + " Email :" + email + " Address " + address + "\n\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
