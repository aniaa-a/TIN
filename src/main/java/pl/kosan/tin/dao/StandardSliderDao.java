package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kosan.tin.model.Slider;

public class StandardSliderDao implements SliderDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardSliderDao.class);

    private final static String INSERT_SLIDER = "INSERT INTO tin_slider(description, photo)" +
            "VALUES(:description, :photo)";

    private final static String DELETE_SLIDER = "DELETE FROM tin_slider WHERE id = :id";
    
    @Override
    public void addSlider(Slider slider) {

    }

    @Override
    public void deleteSlider(Slider slider) {

    }
}
