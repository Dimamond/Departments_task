package bean;
import java.util.Date;

public class Department {
    private String name;
    private Date dateOfCreation;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
        this.dateOfCreation = new Date();
    }

    public String getName() {
        return name;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
