package pl.kosan.tin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.kosan.tin.model.Slider;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository

public class StandardSliderDao extends NamedParameterJdbcDaoSupport implements SliderDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardSliderDao.class);

    private final static String INSERT_SLIDER = "INSERT INTO tin_slider(id_trip, description, photo)" +
            "VALUES(:id_trip,:description, :photo)";
    private final static String DELETE_SLIDER = "DELETE FROM tin_slider WHERE id_trip = :id";
    private final static String GET_ALL_SLIDER = "SELECT id, id_trip, description, photo FROM tin_slider";
    private final static String GET_SLIDER_BY_IDTRIP = "SELECT id, id_trip, description, photo FROM tin_slider WHERE id_trip = :id_trip";

    @Autowired
    public void setDs(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public void addSlider(Slider slider) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id_trip", slider.getIdTrip())
                .addValue("description", slider.getDescription()).addValue("photo", slider.getPhoto());
        try {
            getNamedParameterJdbcTemplate().update(INSERT_SLIDER, mapSqlParameterSource, keyHolder);
            slider.setId((keyHolder.getKey().longValue()));
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteSlider(Long idTrip) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", idTrip);

        try {
            getNamedParameterJdbcTemplate().update(DELETE_SLIDER, mapSqlParameterSource);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Optional<List<Slider>> getSliders() {

        try {
            return Optional.ofNullable(getNamedParameterJdbcTemplate().query(GET_ALL_SLIDER,
                    new MapSqlParameterSource(), (rs, rowNum) -> {
                        Slider slider = new Slider();
                        slider.setId(rs.getLong("id"));
                        slider.setIdTrip(rs.getLong("id_trip"));
                        slider.setDescription(rs.getString("description"));
                        slider.setPhoto(rs.getString("photo"));
                        return slider;
                    }));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Optional<Slider> getSlider(Long idTrip) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id_trip", idTrip);
        try {
            return Optional.ofNullable(getNamedParameterJdbcTemplate().queryForObject(GET_SLIDER_BY_IDTRIP,
                    mapSqlParameterSource, (rs, rowNum) -> {
                        Slider slider = new Slider();
                        slider.setId(rs.getLong("id"));
                        slider.setIdTrip(rs.getLong("id_trip"));
                        slider.setDescription(rs.getString("description"));
                        slider.setPhoto(rs.getString("photo"));
                        return slider;
                    }));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();

        }
    }
}
