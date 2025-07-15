package com.interview.bff.controller;

import com.interview.bff.dto.parcel.ParcelCreateRequest;
import com.interview.bff.dto.parcel.ParcelDto;
import com.interview.bff.service.parcel.ParcelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
//@PreAuthorize todo setup spring security to secure endpoint
public class ParcelController {
    private final ParcelService parcelService;

    // todo use wrapper class to make response consistent, predictable format
    @GetMapping("/parcels/{guestId}")
    public List<ParcelDto> parcelsByGuestId(@PathVariable UUID guestId) {
        return parcelService.parcelsByGuestId(guestId);
    }

    @PostMapping("/parcels")
    public ParcelDto create(@Valid @RequestBody ParcelCreateRequest input) {
        return parcelService.create(input);
    }

    @PostMapping("/parcels/{id}/claim")
    public ParcelDto claim(@PathVariable UUID id) {
        return parcelService.markClaimed(id);
    }
}
