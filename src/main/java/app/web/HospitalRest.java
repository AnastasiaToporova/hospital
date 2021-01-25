package app.web;

import app.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import preprod.qa.soap.Booking;

import java.util.List;

@RestController
@RequestMapping(value = "/bookings")
public class HospitalRest {

        @Autowired
        private HospitalService hospitalService;

        public HospitalRest(HospitalService hospitalService)
        {
            this.hospitalService = hospitalService;
        }

        @RequestMapping(value = "/", method = RequestMethod.GET)
        @ResponseBody
        public List<Booking> get()
        {
            return hospitalService.get();
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        @ResponseBody
        public Booking get(@PathVariable long id)
        {
            return hospitalService.get(id);
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.POST)
        @ResponseBody
        public Booking update(@PathVariable long id, @RequestBody Booking booking)
        {
            return hospitalService.update(id, booking);
        }

        @RequestMapping(value = "/", method = RequestMethod.PUT)
        @ResponseBody
        public Booking create(@RequestBody Booking booking)
        {
            return hospitalService.create(booking);
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
        @ResponseBody
        public boolean delete(@PathVariable long id)
        {
            return hospitalService.delete(id);
        }

}
