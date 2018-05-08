package pl.kosan.tin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kosan.tin.model.Slider;
import pl.kosan.tin.model.Trip;
import pl.kosan.tin.services.SliderService;

import java.util.List;

@RestController
@RequestMapping(value = "/slider")
public class SliderController {

    @Autowired
    SliderService sliderService;


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public List<Slider> getAllSlider() {

        return sliderService.getSliders();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/getByTripId")
    public Slider getSliderByIdtrip( @RequestParam(defaultValue = "id") Long id) {

        return sliderService.getSliderByTrip(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void insertSlider(@RequestBody Slider slider){

        sliderService.addSlider(slider);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public void deleteSlider(@RequestBody Long id){

        sliderService.deleteSlider(id);
    }

}
