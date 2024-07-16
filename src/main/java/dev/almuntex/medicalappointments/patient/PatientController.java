package dev.almuntex.medicalappointments.patient;

import dev.almuntex.medicalappointments.appointment.AppointmentDto;
import dev.almuntex.medicalappointments.appointment.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    public PatientController(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PatientDto> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientDto getPatientById(@PathVariable(name = "id") Long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    public ResponseEntity<Void> registerPatient(@RequestBody PatientDto patientDto) {
        Long patientId = patientService.registerPatient(patientDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(patientId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

    @GetMapping("/{patient_id}/appointments")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDto> getAppointments(@PathVariable(name = "patient_id") Long patientId) {
        return appointmentService.getPatientAppointments(patientId);
    }
}
