package com.interview.bff.service.parcel;

import com.interview.bff.client.guest.GuestClient;
import com.interview.bff.client.parcel.ParcelClient;
import com.interview.bff.dto.guest.GuestDto;
import com.interview.bff.dto.guest.GuestStatusEnum;
import com.interview.bff.dto.parcel.ParcelCreateRequest;
import com.interview.bff.dto.parcel.ParcelDto;
import com.interview.bff.exception.GuestNotAvailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {
    private final GuestClient guestClient;
    private final ParcelClient parcelClient;

    @Override
    public List<ParcelDto> parcelsByGuestId(UUID guestId) {
        return parcelClient.findByGuestIds(guestId);
    }

    @Override
    public ParcelDto create(ParcelCreateRequest input) throws GuestNotAvailableException {
        UUID guestId = input.getGuestId();
        GuestDto guest = guestClient.getGuest(guestId);
        boolean isGuestAvailable = guest != null && guest.getStatus() != null
                && (guest.getStatus().equals(GuestStatusEnum.CheckedIn)
                || guest.getStatus().equals(GuestStatusEnum.InHouse));
        if (!isGuestAvailable) {
            throw new GuestNotAvailableException(guestId);
        }

        return parcelClient.create(input);
    }

    @Override
    public ParcelDto markClaimed(UUID parcelId) {
        return parcelClient.markClaimed(parcelId);
    }
}
