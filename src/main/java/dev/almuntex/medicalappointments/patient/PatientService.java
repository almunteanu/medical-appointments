package dev.almuntex.medicalappointments.patient;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class PatientService {

    private final List<Patient> patients = new CopyOnWriteArrayList<>();
    private Long id = 0L;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<PatientDto> getAllPatients() {
        return patients.stream().map(patient -> modelMapper.map(patient, PatientDto.class)).toList();
    }

    public PatientDto getPatientById(Long id) {
        Patient patient = getById(id);
        return modelMapper.map(patient, PatientDto.class);
    }

    public Long registerPatient(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        patient.setId(id++);
        patients.add(patient);
        return id;
    }

    public void deletePatient(Long id) {
        Patient patient = getById(id);
        patients.remove(patient);
    }

    public Patient getById(Long id) {
        return patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Entity not found."));
    }
}
