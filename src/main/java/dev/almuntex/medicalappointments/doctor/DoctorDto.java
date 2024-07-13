package dev.almuntex.medicalappointments.doctor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDto {

    private String firstName;
    private String lastName;
    private String specialization;
    private String office;
}
