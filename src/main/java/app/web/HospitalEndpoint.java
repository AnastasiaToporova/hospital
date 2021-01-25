package app.web;

import app.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import preprod.qa.soap.*;

@Endpoint
public class HospitalEndpoint {

    private static final String NAMESPACE_URI = "http://preprod/qa/soap";

    private HospitalService hospitalService;

    @Autowired
    public HospitalEndpoint(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBookingRequest")
    @ResponsePayload
    public AddBookingResponse addBooking(@RequestPayload AddBookingRequest request) {
        AddBookingResponse response = new AddBookingResponse();
        response.setBooking(hospitalService.addRecord(request.getDoctorName(),request.getTimeSlot()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "cancelRequest")
    @ResponsePayload
    public CancelBookingResponse cancelBooking(@RequestPayload CancelBookingRequest request) {
        CancelBookingResponse response = new CancelBookingResponse();
        response.setBooking(hospitalService.cancelBooking(request.getDoctorName(),request.getTimeSlot()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookingByTimeAndDoctorRequest")
    @ResponsePayload
    public GetBookingByTimeAndDoctorResponse getBookingByTimeAndDoctor(@RequestPayload GetBookingByTimeAndDoctorRequest request) {
        GetBookingByTimeAndDoctorResponse response = new GetBookingByTimeAndDoctorResponse();
        response.getBooking().addAll(hospitalService.GetBookingByTimeAndDoctor(request.getDoctorName(),request.getTimeSlotMax(),request.getTimeSlotMin()));
        return response;
    }
}

