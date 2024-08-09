    @Test
    public void setPacketType() {
        Packet packet = new Packet();
        for (Packet.Type type : Packet.Type.values()) {
            packet.setPacketType(type);
            assertSame(type, packet.getPacketType());
        }
    }