package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import pl.kosan.tin.dao.SliderDao;
import pl.kosan.tin.model.Slider;

public class SliderService {

    @Autowired
    SliderDao sliderDao;

    public void addSlider(Slider slider) {

        sliderDao.addSlider(slider);
    }

    public void deleteSlider(Long id) {

        sliderDao.deleteSlider(id);
    }
}
