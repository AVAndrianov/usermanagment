package ru.company.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Добавление сервиса")
public class SubscriptionsRequest {

    @Schema(description = "Сервис", example = "YouTube")
    @Size(max = 255, message = "Длина пароля должна быть не более 255 символов")
    private String service;
}
