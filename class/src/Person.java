
import java.io.Serializable;

public class Person implements Serializable {
    private String firstName;
    private String SurName;
    private int CreditCardNumber;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;

    }
    public String getSurName() {
        return SurName;
    }
    public void setSurName(String SurName) {
        this.SurName = SurName;
    }

    public int CreditCardNumber () {
        return CreditCardNumber;
    }
    public void setCreditCardNumber(int CreditCardNumber) {
        this.CreditCardNumber = CreditCardNumber;
    }

}
