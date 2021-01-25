package app.utils;

import org.springframework.jdbc.core.RowMapper;
import preprod.qa.soap.Booking;
import preprod.qa.soap.Status;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The EmployeeRowMapper class
 * <p/>
 * Copyright (C) 2020 copyright.com
 * <p/>
 * Date: 02/05/2020
 *
 * @author Pavlo_Padalka
 */
public class BookingRowMapper implements RowMapper<Booking> {

    @Override
    public Booking mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Booking booking = new Booking();

        booking.setId(rs.getLong("ID"));
        booking.setDoctorName(rs.getString("DoctorName"));
        booking.setTimeSlot(BigInteger.valueOf(Long.parseLong(rs.getString("TimeSlot"))));
        booking.setBookingStatus(Status.valueOf(rs.getString("Status")));
        return booking;
    }
}
