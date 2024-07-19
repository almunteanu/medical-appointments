package dev.almuntex.medicalappointments.doctor;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDto.class))
                .toList();
    }

    public DoctorDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Doctor with id " + id + " not found")
        );
        return modelMapper.map(doctor, DoctorDto.class);
    }

    public Long registerDoctor(DoctorDto doctorDto) {
        Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
        return doctorRepository.save(doctor).getId();
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
