package com.interview.bff.service.parcel;

import com.interview.bff.dto.parcel.ParcelCreateRequest;
import com.interview.bff.dto.parcel.ParcelDto;

import java.util.List;
import java.util.UUID;

public interface ParcelService {
    List<ParcelDto> parcelsByGuestId(UUID guestId);
    ParcelDto create(ParcelCreateRequest input);
    ParcelDto markClaimed(UUID parcelId);
}
