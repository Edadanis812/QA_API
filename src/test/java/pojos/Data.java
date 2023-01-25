package pojos;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
//If you find un know properties, ignore it.
public class Data {
     /*
      When
        I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employees
       Status code should be 200
       Use Gson and ObjectMapper
       make sure you have 24 records for data
  */
     //What we are seeing in data object
    //At first, run the Get11 and then get data on the console
    //Console:{
     //    "status": "success",
     //    "data": [
     //        {
     //            "id": 1,
     //            "employee_name": "Tiger Nixon",
     //            "employee_salary": 320800,
     //            "employee_age": 61,
     //            "profile_image": ""
     //        },
    //Whole variables have the same name
    private int id;
    private String employee_name;
    private int employee_salary;
    private int employee_age;
    //INTERVIEW QUESTION: If you are given limited test data, how would you test it with API?
    // We use "JsonIgnoreProperties(ignoreUnknown=true)"
    //For this one,If we did not use the profile image, we are going to get error. But we need to create the "JsonIgnoreProperties"
    //private String profile_image=""; If It is N/A, we can skip it.

    //FOR POJO:
    //Constructor:Parametrized
    public Data() {
    }
    //Constructor: Non Parametrized
    public Data(int id, String employee_name, int employee_salary, int employee_age) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
    }
    //GET AND SETTER METHODS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(int employee_salary) {
        this.employee_salary = employee_salary;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }
    //ToString METHOD
    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", employee_name='" + employee_name + '\'' +
                ", employee_salary=" + employee_salary +
                ", employee_age=" + employee_age +
                '}';
    }
}
