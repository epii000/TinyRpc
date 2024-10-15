package org.example.protocol;

import lombok.Getter;

@Getter
public enum ProtocolMessageTypeEnum {

    REQUEST(0),
    RESPONSE(1),
    HEAT_BEAT(2),
    OTHERS(3);

    private final int key;

    ProtocolMessageTypeEnum(int key) {
        this.key = key;
    }

    public static ProtocolMessageTypeEnum getEnumByKey(int key) {
        for (ProtocolMessageTypeEnum type : ProtocolMessageTypeEnum.values()) {
            if (type.key == key) {
                return type;
            }
        }
        return null;
    }
}
