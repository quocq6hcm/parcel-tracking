package com.interview.bff.dto.parcel;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParcelDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 7142934183287656633L;

    private UUID id;
    private String senderName;
    private String name;
    private UUID guestId;
    private ParcelDeliveryStateEnum deliveryState;

}