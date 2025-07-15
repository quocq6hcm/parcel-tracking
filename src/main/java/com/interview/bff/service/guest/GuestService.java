package com.interview.bff.service.guest;

import com.interview.bff.dto.guest.GuestDto;

import java.util.List;
import java.util.UUID;

public interface GuestService {
    List<GuestDto> search(String query);
    GuestDto checkIn(UUID guestId);
    GuestDto checkOut(UUID guestId);
}
