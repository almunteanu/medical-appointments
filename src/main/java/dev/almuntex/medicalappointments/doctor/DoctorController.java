package dev.almuntex.medicalappointments.doctor;

import dev.almuntex.medicalappointments.appointment.AppointmentDto;
import dev.almuntex.medicalappointments.appointment.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    public DoctorController(DoctorService doctorService, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorDto> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorDto getDoctorById(@PathVariable(name = "id") Long id) {
        return doctorService.getDoctorById(id);
    }

    @PostMapping
    public ResponseEntity<Void> registerDoctor(DoctorDto doctorDto) {
        Long doctorId = doctorService.registerDoctor(doctorDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(doctorId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }

    @GetMapping("/{doctor_id}/appointments")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDto> getAppointments(@PathVariable(name = "doctor_id") Long doctorId) {
        return appointmentService.getDoctorAppointments(doctorId);
    }
}
