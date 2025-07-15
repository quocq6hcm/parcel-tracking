package com.interview.bff.dto.parcel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParcelCreateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 7142934183287656633L;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotNull(message = "senderName cannot be null")
    @NotBlank(message = "senderName cannot be empty")
    private String senderName;

    @NotNull(message = "guestId cannot be null")
    private UUID guestId;
}