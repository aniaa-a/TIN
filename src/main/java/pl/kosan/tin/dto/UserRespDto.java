package pl.kosan.tin.dto;

public class UserRespDto {

    Boolean existing;

    public UserRespDto() {
    }

    public UserRespDto(Boolean existing) {
        this.existing = existing;
    }

    public Boolean getExisting() {
        return existing;
    }

    public void setExisting(Boolean existing) {
        this.existing = existing;
    }
}
