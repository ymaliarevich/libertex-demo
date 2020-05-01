package models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@XmlRootElement
public class ClientsResultModel extends RestModel {
    private String resultCode;
    private List<String> clients;

    public ClientsResultModel() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientsResultModel that = (ClientsResultModel) o;
        return Objects.equals(resultCode, that.resultCode) &&
                Objects.equals(clients, that.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCode, clients);
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public List<String> getClients() {
        return clients;
    }

    public void setClients(List<String> clients) {
        this.clients = clients;
    }
}
