package com.heartbeat.pin;

import com.heartbeat.common.board.BoardType;
import com.heartbeat.pin.command.PinCommand;
import com.heartbeat.pin.command.chip.ChipSystemPinCommand;

import java.util.concurrent.ConcurrentHashMap;

/**
 * A Manager class for pins. It aims to coordinate all pin creations & operations.
 * Manager holds an index for all created pins for preventing duplicate creations.
 */
public class PinManager {
    private static PinManager instance = new PinManager(BoardType.CHIP);
    private final ConcurrentHashMap<String, Pin> pins = new ConcurrentHashMap<>();
    private final BoardType boardType;
    private final PinCommand pinCommand;

    public static PinManager getInstance() {
        return instance;
    }

    private PinManager(BoardType boardType) {
        this.boardType = boardType;
        switch (boardType) {
            case CHIP:
                this.pinCommand = new ChipSystemPinCommand();
                break;
            default:
                this.pinCommand = new ChipSystemPinCommand();
                break;
        }
    }

    public Pin createPin(String code, Pin.Mode mode) throws PinException {
        if (pins.containsKey(code)) {
            Pin pin = pins.get(code);
            if (!pin.getMode().equals(mode))
                throw new PinException("Pin already created with mode " + pin.getMode().name());
            return pins.get(code);
        }
        Pin pin = new Pin(code, mode, getPinCommand());
        pins.put(code, pin);
        return pin;

    }

    public Pin getPin(String code) throws PinException {
        if (pins.containsKey(code))
            return pins.get(code);
        else
            throw new PinException("Pin is not created. Please call 'createPin' first.");
    }


    private PinCommand getPinCommand() {
        return pinCommand;
    }
}
