package com.interview.bff.controller;

import com.interview.bff.dto.guest.GuestDto;
import com.interview.bff.service.guest.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
//@PreAuthorize todo setup spring security to secure endpoint
public class GuestController {
    private final GuestService guestService;

    @GetMapping("/guests/search")
    public List<GuestDto> findGuests(@RequestParam String query) {
        return guestService.search(query);
    }

    @PostMapping("/guests/{id}/check-in")
    public GuestDto checkIn(@PathVariable UUID id) {
        return guestService.checkIn(id);
    }

    @PostMapping("/guests/{id}/check-out")
    public GuestDto checkOut(@PathVariable UUID id) {
        return guestService.checkOut(id);
    }
}
