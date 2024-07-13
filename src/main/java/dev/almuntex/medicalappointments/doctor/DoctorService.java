package dev.almuntex.medicalappointments.doctor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class DoctorService {

    private final List<Doctor> doctors = new CopyOnWriteArrayList<>();
    private Long id = 0L;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<DoctorDto> getAllDoctors() {
        return doctors.stream().map(doctor -> modelMapper.map(doctor, DoctorDto.class)).toList();
    }

    public DoctorDto getDoctorById(Long id) {
        Doctor doctor = getById(id);
        return modelMapper.map(doctor, DoctorDto.class);
    }

    public Long registerDoctor(DoctorDto doctorDto) {
        Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
        doctor.setId(id++);
        doctors.add(doctor);
        return id;
    }

    public void deleteDoctor(Long id) {
        Doctor doctor = getById(id);
        doctors.remove(doctor);
    }

    public Doctor getById(Long id) {
        return doctors.stream()
                .filter(doctor -> doctor.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Entity not found."));
    }
}
