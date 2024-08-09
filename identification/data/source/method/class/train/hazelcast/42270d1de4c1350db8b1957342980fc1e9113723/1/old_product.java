public Packet setPacketType(Packet.Type type) {
        int nonTypeFlags = flags & ~FLAG_OP & ~FLAG_EVENT & ~FLAG_BIND;
        resetFlagsTo(type.headerEncoding | nonTypeFlags);
        return this;
    }