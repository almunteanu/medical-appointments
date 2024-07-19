package dev.almuntex.medicalappointments.patient;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patient -> modelMapper.map(patient, PatientDto.class))
                .toList();
    }

    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Patient with id " + id + " not found")
        );
        return modelMapper.map(patient, PatientDto.class);
    }

    public Long registerPatient(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        return patientRepository.save(patient).getId();
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
