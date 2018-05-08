package pl.kosan.tin.model;

public class Slider {

    private Long id;
    private Long idTrip;
    private String description;
    private String photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(Long idTrip) {
        this.idTrip = idTrip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Slider{" +
                "id=" + id +
                "id_trip=" + idTrip +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
