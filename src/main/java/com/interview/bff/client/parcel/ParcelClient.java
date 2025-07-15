package com.interview.bff.client.parcel;

import com.interview.bff.dto.parcel.ParcelCreateRequest;
import com.interview.bff.dto.parcel.ParcelDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;
import java.util.UUID;

@Service
@HttpExchange(
        accept = MediaType.APPLICATION_JSON_VALUE)
// todo setup circuit breaker
public interface ParcelClient {
    @GetExchange("/parcels/guests/{guestId}")
    List<ParcelDto> findByGuestIds(@PathVariable UUID guestId);

    @PostExchange("/parcels")
    ParcelDto create(@RequestBody ParcelCreateRequest request);

    @PostExchange("/parcels/{id}/claim")
    ParcelDto markClaimed(@PathVariable UUID id);
}
