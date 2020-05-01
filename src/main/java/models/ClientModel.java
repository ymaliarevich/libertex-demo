package models;

import utils.Generator;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class ClientModel extends RestModel {
    private String fullName;
    private String username;

    public String getFullName() {
        return fullName;
    }

    public ClientModel() {
        this.fullName = Generator.getRandomString();
        this.username = Generator.getRandomString();
    }

    public ClientModel(String fullName, String username) {
        this.fullName = fullName;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientModel that = (ClientModel) o;
        return Objects.equals(fullName, that.fullName) &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, username);
    }

    @Override
    public String toString() {
        return "ClientModel{" +
                "fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}