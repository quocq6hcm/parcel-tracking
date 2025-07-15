package com.interview.bff.service.guest;

import com.interview.bff.client.guest.GuestClient;
import com.interview.bff.dto.guest.GuestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {
    private final GuestClient guestClient;

    @Override
    public List<GuestDto> search(String query) {
        return guestClient.findGuests(query);
    }

    @Override
    public GuestDto checkIn(UUID guestId) {
        return guestClient.checkIn(guestId);
    }

    @Override
    public GuestDto checkOut(UUID guestId) {
        return guestClient.checkOut(guestId);
    }
}
