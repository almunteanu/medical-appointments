package dev.almuntex.medicalappointments.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDto {

    @NotBlank(message = "IDNP is required")
    @Size(min = 13, max = 13, message = "IDNP must be exactly 13 digits long")
    @Pattern(regexp = "[0-9]+", message = "Only digits allowed")
    private String idnp;

    @JsonProperty("first_name")
    @NotBlank(message = "First name is required")
    @Size(max = 100)
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "Last name is required")
    @Size(max = 100)
    private String lastName;

    @JsonProperty("date_of_birth")
    @NotNull(message = "Date of birth is required")
    @Past
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is required")
    private Character gender;
}
