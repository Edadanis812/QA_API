package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import java.util.List;
//If we face missing data in OuterData, Json will handle it.That is why we need to use it.
@JsonIgnoreProperties(ignoreUnknown = true)
public class OuterData {
    //"status": "success",
    //"message": "Successfully! All records has been fetched."
    private String status="success";
    private List<Data> data;
    private String message="Successfully! All records has been fetched.";
    //For OuterData we need get/set/constructor:
    public OuterData() {
    }
    public OuterData(String status, List<Data> data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<Data> getData() {
        return data;
    }
    public void setData(List<Data> data) {
        this.data = data;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "OuterData{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
