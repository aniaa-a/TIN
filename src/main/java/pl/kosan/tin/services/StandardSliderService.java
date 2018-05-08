package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosan.tin.dao.SliderDao;
import pl.kosan.tin.model.Car;
import pl.kosan.tin.model.Slider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StandardSliderService implements SliderService {

    @Autowired
    SliderDao sliderDao;


    @Override
    public void addSlider(Slider slider) {

        sliderDao.addSlider(slider);
    }

    @Override
    public void deleteSlider(Long idSlider) {
        sliderDao.deleteSlider(idSlider);
    }

    @Override
    public List<Slider> getSliders() {
        List<Slider> sliders = new ArrayList<Slider>();

        Optional<List<Slider>> optListSlider = sliderDao.getSliders();

        if (optListSlider.isPresent()) {
            sliders = optListSlider.get();
        }

        return sliders;
    }

    @Override
    public Slider getSliderByTrip(Long idTrip) {

        Slider slider = new Slider();
        Optional<Slider> optSlider = sliderDao.getSlider(idTrip);

        if (optSlider.isPresent()) {
            slider = optSlider.get();
        }
        return slider;
    }
}
