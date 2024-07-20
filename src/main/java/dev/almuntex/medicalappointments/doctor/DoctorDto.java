package dev.almuntex.medicalappointments.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDto {

    @JsonProperty("first_name")
    @NotBlank(message = "First name is required")
    @Size(max = 100)
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "Last name is required")
    @Size(max = 100)
    private String lastName;

    @NotBlank(message = "Specialization is required")
    @Size(max = 100)
    private String specialization;

    @NotBlank(message = "Office is required")
    @Size(max = 10)
    @Pattern(regexp = "[0-9]+", message = "Only digits allowed")
    private String office;
}
