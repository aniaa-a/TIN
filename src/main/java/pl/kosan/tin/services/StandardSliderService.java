package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosan.tin.dao.SliderDao;
import pl.kosan.tin.dao.TripDao;
import pl.kosan.tin.model.Slider;
import pl.kosan.tin.model.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StandardSliderService implements SliderService {

    @Autowired
    SliderDao sliderDao;

    @Autowired
    TripDao tripDao;


    @Override
    public void addSlider(Long idTrip) {

        Slider slider = new Slider();
        Trip trip =tripDao.findTripById(idTrip);
        slider.setIdTrip(idTrip);
        slider.setDescription(trip.getTitle());
        slider.setPhoto(trip.getPhoto());
        sliderDao.addSlider(slider);
    }

    @Override
    public void deleteSlider(Long idTrip) {
        sliderDao.deleteSlider(idTrip);
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
