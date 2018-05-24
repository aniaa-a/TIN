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
    @RequestMapping(method = RequestMethod.GET)
    public List<Slider> getAllSlider() {

        return sliderService.getSliders();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{tripId}")
    public Slider getSliderByIdtrip(@PathVariable("tripId") Long tripId) {

        return sliderService.getSliderByTrip(tripId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void insertSlider(@RequestBody Slider slider){

        sliderService.addSlider(slider);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteSlider(@PathVariable("id") Long id){

        sliderService.deleteSlider(id);
    }

}
