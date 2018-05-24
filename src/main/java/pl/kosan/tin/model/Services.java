package pl.kosan.tin.model;

public class Services {

    String service;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "Services{" +
                "service='" + service + '\'' +
                '}';
    }
}
