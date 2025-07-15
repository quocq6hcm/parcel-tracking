package com.interview.bff.client.guest;

import com.interview.bff.dto.guest.GuestDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;
import java.util.UUID;

@Service
@HttpExchange(
    accept = MediaType.APPLICATION_JSON_VALUE)
// todo setup circuit breaker
public interface GuestClient {
    @GetExchange("/guests/search={query}")
    List<GuestDto> findGuests(@PathVariable String query);

    @GetExchange("/guests/{id}")
    GuestDto getGuest(@PathVariable UUID id);

    @PostExchange("/guests/{id}/check-in")
    GuestDto checkIn(@PathVariable UUID id);

    @PostExchange("/guests/{id}/check-out")
    GuestDto checkOut(@PathVariable UUID id);
}
