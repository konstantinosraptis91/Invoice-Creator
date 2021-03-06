package invoice.builder.api.dao;

import invoice.builder.api.util.Constants;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import order.form.dto.Form.Transporter;
import order.form.dto.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 *
 * @author konstantinos
 */
@Repository
@Qualifier("MySQLTransporter")
public class MySQLTransporterDao implements TransporterDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLTransporterDao.class);
    
    @Autowired    
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public int addTransporter(Transporter transporter) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName(TABLE_TRANSPORTER).usingGeneratedKeyColumns(ID);
        Map<String, Object> params = new HashMap<>();
        params.put(NAME, transporter.getName());
        params.put(ADDRESS, transporter.getAddress());
        params.put(PHONE_NUMBER, transporter.getPhoneNumber());
        params.put(EMAIL, transporter.getEmail());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        LOGGER.info(transporter + " added successfully.", Constants.LOG_DATE_FORMAT.format(new Date()));
        return key.intValue();
    }

    @Override
    public Transporter getTransporterById(int id) {
        ObjectFactory objectFactory = new ObjectFactory();
        Transporter transporter = objectFactory.createFormTransporter();
        try {
            transporter = (Transporter) jdbcTemplate.queryForObject("SELECT * FROM " 
                    + TABLE_TRANSPORTER + " WHERE " 
                    + ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Transporter t = objectFactory.createFormTransporter();
                        // no need to retrieve id here, becaise we already know it
                        t.setId(BigInteger.valueOf(id));
                        t.setName(rs.getString(NAME));
                        t.setAddress(rs.getString(ADDRESS));
                        t.setPhoneNumber(rs.getString(PHONE_NUMBER));
                        t.setEmail(rs.getString(EMAIL));
                        return t;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return transporter;
    }

    @Override
    public List<Transporter> getTransporters() {
        ObjectFactory objectFactory = new ObjectFactory();
        List<Transporter> transporters = new ArrayList<>();
        try {
            transporters = jdbcTemplate.query("SELECT * FROM " + TABLE_TRANSPORTER, 
                    (rs, rowNum) -> {
                        Transporter t = objectFactory.createFormTransporter();
                        int id = rs.getInt(ID);
                        t.setId(BigInteger.valueOf(id));
                        t.setName(rs.getString(NAME));
                        t.setAddress(rs.getString(ADDRESS));
                        t.setPhoneNumber(rs.getString(PHONE_NUMBER));
                        t.setEmail(rs.getString(EMAIL));
                        return t;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return transporters;
    }
      
}
