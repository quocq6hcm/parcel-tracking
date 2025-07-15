package com.interview.bff.service.parcel;

import com.interview.bff.client.guest.GuestClient;
import com.interview.bff.client.parcel.ParcelClient;
import com.interview.bff.dto.guest.GuestDto;
import com.interview.bff.dto.guest.GuestStatusEnum;
import com.interview.bff.dto.parcel.ParcelCreateRequest;
import com.interview.bff.dto.parcel.ParcelDto;
import com.interview.bff.exception.GuestNotAvailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class ParcelServiceTest {

    private ParcelServiceImpl parcelService;
    private GuestClient guestClient;
    private ParcelClient parcelClient;

    @BeforeEach
    void setUp() {
        guestClient = mock(GuestClient.class);
        parcelClient = mock(ParcelClient.class);
        parcelService = new ParcelServiceImpl(guestClient, parcelClient);
    }

    @Test
    void should_return_parcels_by_guest_id() {
        // Given
        UUID guestId = UUID.randomUUID();
        List<ParcelDto> expected = List.of(new ParcelDto());
        given(parcelClient.findByGuestIds(guestId)).willReturn(expected);

        // When
        List<ParcelDto> result = parcelService.parcelsByGuestId(guestId);

        // Then
        assertThat(result).hasSize(1);
        then(parcelClient).should().findByGuestIds(guestId);
    }

    @Test
    void should_create_parcel_when_guest_is_checked_in() {
        // Given
        UUID guestId = UUID.randomUUID();
        ParcelCreateRequest request = new ParcelCreateRequest();
        request.setGuestId(guestId);
        request.setSenderName("Sender");

        GuestDto guest = new GuestDto();
        guest.setId(guestId);
        guest.setStatus(GuestStatusEnum.CheckedIn);

        ParcelDto expectedParcel = new ParcelDto();
        expectedParcel.setGuestId(guestId);

        given(guestClient.getGuest(guestId)).willReturn(guest);
        given(parcelClient.create(request)).willReturn(expectedParcel);

        // When
        ParcelDto result = parcelService.create(request);

        // Then
        assertThat(result.getGuestId()).isEqualTo(guestId);
        then(guestClient).should().getGuest(guestId);
        then(parcelClient).should().create(request);
    }

    @Test
    void should_throw_when_guest_is_checked_out() {
        // Given
        UUID guestId = UUID.randomUUID();
        ParcelCreateRequest request = new ParcelCreateRequest();
        request.setGuestId(guestId);

        GuestDto guest = new GuestDto();
        guest.setId(guestId);
        guest.setStatus(GuestStatusEnum.CheckedOut);

        given(guestClient.getGuest(guestId)).willReturn(guest);

        // When / Then
        assertThrows(GuestNotAvailableException.class, () -> parcelService.create(request));
        then(parcelClient).shouldHaveNoInteractions();
    }

    @Test
    void should_markClaimed_parcel_by_id() {
        // Given
        UUID parcelId = UUID.randomUUID();
        ParcelDto expected = new ParcelDto();
        expected.setId(parcelId);

        given(parcelClient.markClaimed(parcelId)).willReturn(expected);

        // When
        ParcelDto result = parcelService.markClaimed(parcelId);

        // Then
        assertThat(result.getId()).isEqualTo(parcelId);
        then(parcelClient).should().markClaimed(parcelId);
    }
}