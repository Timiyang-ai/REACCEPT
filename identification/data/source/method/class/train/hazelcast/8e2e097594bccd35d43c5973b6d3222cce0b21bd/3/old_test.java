    @Test
    public void isFlagSet() {
        Packet packet = new Packet();
        packet.setPacketType(Packet.Type.OPERATION);
        packet.raiseFlags(FLAG_URGENT);

        assertSame(Packet.Type.OPERATION, packet.getPacketType());
        assertTrue(packet.isFlagRaised(FLAG_URGENT));
        assertFalse(packet.isFlagRaised(FLAG_OP_CONTROL));
    }