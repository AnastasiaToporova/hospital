package app.repository;


import app.utils.BookingRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import preprod.qa.soap.Booking;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BookingRepository implements IBookingRepository<Booking, Long> {

    private static final String SCHEMA_NAME = "hospital";
    private static final String BOOKING_TABLE_NAME = "booking";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public BookingRepository(JdbcTemplate jdbcTemplate,
                             NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void save(final Booking booking) {
        jdbcTemplate.update("INSERT INTO " + SCHEMA_NAME + "." + BOOKING_TABLE_NAME + " VALUES (?, ?, ?,?)",
                booking.getId(), booking.getDoctorName(), booking.getTimeSlot(),booking.getBookingStatus());
    }

    @Override
    public void saveWithSimpleJdbcInsert(final Booking booking) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("ID", booking.getId());
        parameters.put("DoctorName", booking.getDoctorName());
        parameters.put("TimeSlot", booking.getTimeSlot());
        parameters.put("Status", booking.getTimeSlot());

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withSchemaName(SCHEMA_NAME)
                .withTableName(BOOKING_TABLE_NAME)
                .execute(parameters);
    }

    @Override
    public Collection<Booking> findAll() {
        return jdbcTemplate.query("SELECT * FROM " + SCHEMA_NAME + "." + BOOKING_TABLE_NAME,
                new BookingRowMapper());
    }

    @Override
    public Booking findOne(final Long id) {
        final String query = "SELECT * FROM " + SCHEMA_NAME + "." + BOOKING_TABLE_NAME + " WHERE id = ?";

        return jdbcTemplate.queryForObject(query, new Object[]{id}, new BookingRowMapper());
    }

    @Override
    public void update(final Booking booking) {
        final String query = "UPDATE " + SCHEMA_NAME + "." + BOOKING_TABLE_NAME
                + " SET DoctorName = ?, TimeSlot = ?, Status = ? WHERE id = ?";

        jdbcTemplate.update(query, booking.getDoctorName(), booking.getTimeSlot(), booking.getTimeSlot(),booking.getId());
    }

    @Override
    public void batchUpdateUsingNamedParameterJDBCTemplate(final Collection<Booking> bookings) {
        final SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(bookings.toArray());
        namedParameterJdbcTemplate
                .batchUpdate("INSERT INTO " + SCHEMA_NAME + "." + BOOKING_TABLE_NAME +
                        " VALUES (:ID, :DoctorName, :TimeSlot, :Status)", batch);
    }

}
