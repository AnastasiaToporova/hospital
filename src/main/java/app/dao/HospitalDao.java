package app.dao;

import org.springframework.stereotype.Component;
import preprod.qa.soap.Booking;
import preprod.qa.soap.Doctor;
import preprod.qa.soap.Specialization;
import preprod.qa.soap.Status;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HospitalDao {

    private List<Booking> bookingStorage = new ArrayList<>();

    @PostConstruct
    public void initData() {
        Booking booking = new Booking();
        booking.setId(1);
        booking.setDoctorName(Specialization.SURGEON.value());
        booking.setTimeSlot(BigInteger.valueOf(6979898));
        booking.setBookingStatus(Status.ACTIVE);
        bookingStorage.add(booking);
    }
    private int currentId;
    {
        Booking booking = new Booking();
        booking.setId(1);
        booking.setDoctorName(Specialization.DENTIST.value());
        booking.setTimeSlot(BigInteger.valueOf(6979898));
        booking.setBookingStatus(Status.ACTIVE);
        bookingStorage.add(booking);

        Booking booking1 = new Booking();
        booking1.setId(2);
        booking1.setDoctorName(Specialization.NEUROSURGEONS.value());
        booking1.setTimeSlot(BigInteger.valueOf(6979898));
        booking1.setBookingStatus(Status.ACTIVE);
        bookingStorage.add(booking1);
    }
    public Booking addBooking(String name, BigInteger time) {
        Booking booking = new Booking();
        booking.setBookingStatus(Status.CLOSED);
        booking.setTimeSlot(time);
        booking.setDoctorName(name);
        bookingStorage.add(booking);
        return booking;
    }

    public Booking cancelBooking(String name, BigInteger time) {
        for (Booking record : bookingStorage) {
            if (record.getDoctorName().equals(name) && record.getTimeSlot() == time) {
                record.setBookingStatus(Status.ACTIVE);
                return record;
            }
        }
        return null;
    }

    public Booking addBooking(Booking booking)
    {
        while (get(currentId) != null){
            currentId++;
        }
        booking.setId(currentId++);
        bookingStorage.add(booking);
        return booking;
    }

    public List<Booking> GetBookingByTimeAndDoctor(String name, BigInteger maxTime, BigInteger minTime) {
        return bookingStorage.stream()
                .filter(booking -> name.equals(booking.getDoctorName()))
                .filter(booking -> booking.getTimeSlot().compareTo(minTime) > 0 && maxTime.compareTo(booking.getTimeSlot())>0)
                .collect(Collectors.toList());
    }

    public List<Booking> get() {
        return bookingStorage;
    }

    public Booking get(long id) {
        Optional<Booking> bookingOptional = bookingStorage.stream().filter(booking -> id == booking.getId()).findAny();
        if (bookingOptional.isPresent()) {
            return bookingOptional.get();
        } else {
            return null;
        }
    }

    public Booking update(long id, Booking booking) {
        Booking oldBooking = get(id);
        if (oldBooking == null) {
            throw new IllegalArgumentException("There is no tvShow with id");
        } else {
            oldBooking.setId(booking.getId());
            oldBooking.setDoctorName(booking.getDoctorName());
            oldBooking.setTimeSlot(booking.getTimeSlot());
            oldBooking.setBookingStatus(booking.getBookingStatus());
        }
        return oldBooking;
    }

    public boolean delete(long id) {
        return bookingStorage.remove(get(id));
    }
}
