package pl.kosan.tin.dao;

import pl.kosan.tin.model.Slider;

import java.util.List;
import java.util.Optional;

public interface SliderDao {

    void addSlider(Slider slider);
    void deleteSlider(Long idTrip);
    Optional<List<Slider>> getSliders();
    Optional<Slider> getSlider(Long idTrip);

}
