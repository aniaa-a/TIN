package pl.kosan.tin.dao;

import pl.kosan.tin.model.Slider;

public interface SliderDao {

    void addSlider(Slider slider);
    void deleteSlider(Long id);
}
