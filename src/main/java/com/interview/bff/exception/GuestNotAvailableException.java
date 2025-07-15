package com.interview.bff.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GuestNotAvailableException extends RuntimeException {
    private final UUID guestId;

    public GuestNotAvailableException(UUID guestId) {
        super("Guest with ID is not available " + guestId);
        this.guestId = guestId;
    }
}
