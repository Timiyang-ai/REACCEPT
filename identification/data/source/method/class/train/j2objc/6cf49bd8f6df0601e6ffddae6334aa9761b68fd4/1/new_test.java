    public void test_nameUUIDFromBytes() throws Exception {
        byte[] name = { (byte) 0x6b, (byte) 0xa7, (byte) 0xb8, (byte) 0x11,
                (byte) 0x9d, (byte) 0xad, (byte) 0x11, (byte) 0xd1,
                (byte) 0x80, (byte) 0xb4, (byte) 0x00, (byte) 0xc0,
                (byte) 0x4f, (byte) 0xd4, (byte) 0x30, (byte) 0xc8 };

        UUID uuid = UUID.nameUUIDFromBytes(name);

        assertEquals(2, uuid.variant());
        assertEquals(3, uuid.version());

        assertEquals(0xaff565bc2f771745L, uuid.getLeastSignificantBits());
        assertEquals(0x14cdb9b4de013faaL, uuid.getMostSignificantBits());

        uuid = UUID.nameUUIDFromBytes(new byte[0]);
        assertEquals(2, uuid.variant());
        assertEquals(3, uuid.version());

        assertEquals(0xa9800998ecf8427eL, uuid.getLeastSignificantBits());
        assertEquals(0xd41d8cd98f003204L, uuid.getMostSignificantBits());

        try {
            UUID.nameUUIDFromBytes(null);
            fail("No NPE");
        } catch (NullPointerException e) {}
    }