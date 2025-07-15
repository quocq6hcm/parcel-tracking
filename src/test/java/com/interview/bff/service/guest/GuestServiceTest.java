package com.interview.bff.service.guest;

import com.interview.bff.client.guest.GuestClient;
import com.interview.bff.dto.guest.GuestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class GuestServiceTest {

    private GuestClient guestClient;
    private GuestService guestService;

    @BeforeEach
    void setUp() {
        guestClient = mock(GuestClient.class);
        guestService = new GuestServiceImpl(guestClient);
    }

    @Test
    void should_return_guests_matching_query() {
        // Given
        String query = "John";
        List<GuestDto> expectedGuests = List.of(new GuestDto(), new GuestDto());
        given(guestClient.findGuests(query)).willReturn(expectedGuests);

        // When
        List<GuestDto> result = guestService.search(query);

        // Then
        assertThat(result).hasSize(2);
        then(guestClient).should().findGuests(query);
    }

    @Test
    void should_check_in_guest_by_id() {
        // Given
        UUID guestId = UUID.randomUUID();
        GuestDto expected = new GuestDto();
        expected.setId(guestId);

        given(guestClient.checkIn(guestId)).willReturn(expected);

        // When
        GuestDto result = guestService.checkIn(guestId);

        // Then
        assertThat(result.getId()).isEqualTo(guestId);
        then(guestClient).should().checkIn(guestId);
    }

    @Test
    void should_check_out_guest_by_id() {
        // Given
        UUID guestId = UUID.randomUUID();
        GuestDto expected = new GuestDto();
        expected.setId(guestId);

        given(guestClient.checkOut(guestId)).willReturn(expected);

        // When
        GuestDto result = guestService.checkOut(guestId);

        // Then
        assertThat(result.getId()).isEqualTo(guestId);
        then(guestClient).should().checkOut(guestId);
    }
}