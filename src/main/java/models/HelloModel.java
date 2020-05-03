package models;

import java.util.Objects;

public class HelloModel extends RestModel {
    private String resultCode;
    private String message;

    public HelloModel() {

    }

    public HelloModel(String resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HelloModel that = (HelloModel) o;
        return Objects.equals(resultCode, that.resultCode) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCode, message);
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
