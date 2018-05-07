package pl.kosan.tin.model;

public class Slider {

    private Long id;
    private String description;
    private String photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
