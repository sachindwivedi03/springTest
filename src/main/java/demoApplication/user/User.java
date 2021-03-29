package demoApplication.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "User details")
public class User {

    private Integer id;

    @ApiModelProperty(notes = "Name should have at least 2 characters")
    @Size(min=2, message = "Name should have at least 2 characters")
    private String name;

    @ApiModelProperty(notes = "Date of birth cannot in past")
    @Past
    private Date dateOfBirth;

    protected  User(){

    }

    public User(Integer id, String name, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
