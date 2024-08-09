public Packet setPacketType(Packet.Type type) {
        int nonTypeFlags = flags & (~FLAG_TYPE0 & ~FLAG_TYPE1 & ~FLAG_TYPE2);
        resetFlagsTo(type.headerEncoding | nonTypeFlags);
        return this;
    }