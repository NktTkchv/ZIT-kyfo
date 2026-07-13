package zit.kyfo.backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/airlines")
public class AirlinesController {

    @GetMapping("/flights")
    public Object getFlights() {
        return null;
    }

    @GetMapping("/tickets")
    public Object getTickets() {
        return null;
    }

    @GetMapping("/flights/{id}")
    public Object getFlightById(@PathVariable("id") Integer id) {
        return null;
    }

    @GetMapping("/tickets/{ticketNumber}")
    public Object getTicketByNumber(@PathVariable("ticketNumber") String ticketNumber) {
        return null;
    }

    @GetMapping("/flights/{id}/boardingPasses")
    public Object getBoardingPasses(@PathVariable("id") Integer id) {
        return null;
    }

    @GetMapping("/reports")
    public Object getReports() {
        return null;
    }

    @PostMapping("/flights/{id}/payment/process")
    public Object processPayment(@PathVariable("id") Integer id, @RequestBody Object amount) {
        return null;
    }

    @PutMapping("/validatePoint")
    public Object validatePoint(@RequestParam("pointId") Integer pointId) {
        return null;
    }

    @DeleteMapping("/flights/{id}/payment/restore")
    public Object restoreFlightPayment(@PathVariable("id") Integer id) {
        return null;
    }

    @DeleteMapping("/ticket/{ticketNumber}/payment/restore")
    public Object restoreTicketPayment(@PathVariable("ticketNumber") String ticketNumber) {
        return null;
    }
}
