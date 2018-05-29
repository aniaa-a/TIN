package pl.kosan.tin.services;

import pl.kosan.tin.model.Slider;
import java.util.List;

public interface SliderService {

    void addSlider(Long idTrip);

    void deleteSlider(Long idSlider);

    List<Slider> getSliders();

    Slider getSliderByTrip(Long idTrip);

}
