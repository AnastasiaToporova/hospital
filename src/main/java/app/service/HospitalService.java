package app.service;

import app.dao.HospitalDao;
import org.springframework.stereotype.Component;
import preprod.qa.soap.Booking;

import java.math.BigInteger;
import java.util.List;

@Component
public class HospitalService {
    private HospitalDao hospitalDAO = new HospitalDao();

    public Booking addRecord(String name, BigInteger time) {
        if (name == null || time == null) {
            throw new IllegalArgumentException("Doctor`s name  or time of booking is null.");
        }
        return hospitalDAO.addBooking(name, time);
    }

    public Booking cancelBooking(String name, BigInteger time) {
        if (name == null || time == null) {
            throw new IllegalArgumentException("Doctor`s name  or time of booking is null.");
        }
        return hospitalDAO.cancelBooking(name, time);
    }

    public List<Booking> GetBookingByTimeAndDoctor(String name, BigInteger maxTime, BigInteger minTime) {
        if (name == null || maxTime == null || minTime == null) {
            throw new IllegalArgumentException("Doctor`s name  or time of booking is null.");
        }
        return hospitalDAO.GetBookingByTimeAndDoctor(name, maxTime, minTime);
    }

    public Booking get(long id) {
        return hospitalDAO.get(id);
    }
    public List<Booking> get()
    {
        return hospitalDAO.get();
    }
    public Booking update(long id, Booking tvShow)
    {
        return hospitalDAO.update(id, tvShow);
    }

    public Booking create(Booking booking)
    {
        return hospitalDAO.addBooking(booking);
    }

    public boolean delete(long id)
    {
        return hospitalDAO.delete(id);
    }
}
