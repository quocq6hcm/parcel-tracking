package com.interview.bff.dto.guest;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 7142934183287656633L;

    private UUID id;
    private String socialId;
    private String firstName;
    private String lastName;
    private GuestStatusEnum status;
}