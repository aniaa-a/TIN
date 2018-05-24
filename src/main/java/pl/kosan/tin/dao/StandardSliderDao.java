package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.kosan.tin.model.Slider;
import pl.kosan.tin.model.Trip;

import javax.sql.DataSource;

public class StandardSliderDao extends NamedParameterJdbcDaoSupport implements SliderDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardSliderDao.class);

    private final static String INSERT_SLIDER = "INSERT INTO tin_slider(description, photo)" +
            "VALUES(:description, :photo)";

    private final static String DELETE_SLIDER = "DELETE FROM tin_slider WHERE id = :id";

    @Autowired
    public void setDs(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public void addSlider(Slider slider) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("description", slider.getDescription()).addValue("photo", slider.getPhoto());

        try {
            getNamedParameterJdbcTemplate().update(INSERT_SLIDER, mapSqlParameterSource, keyHolder);
            slider.setId((keyHolder.getKey().longValue()));
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteSlider(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        try {
            getNamedParameterJdbcTemplate().update(DELETE_SLIDER, mapSqlParameterSource);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }
}
