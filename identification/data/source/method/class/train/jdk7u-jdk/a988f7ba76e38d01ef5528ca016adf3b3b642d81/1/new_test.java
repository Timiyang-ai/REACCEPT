    private static void variantTest() throws Exception {
        UUID test = UUID.randomUUID();
        if (test.variant() != 2)
            throw new Exception("randomUUID not variant 2");
        Random byteSource = new Random();
        byte[] someBytes = new byte[12];
        byteSource.nextBytes(someBytes);
        test = UUID.nameUUIDFromBytes(someBytes);
        if (test.variant() != 2)
            throw new Exception("nameUUIDFromBytes not variant 2");
        test = new UUID(55L, 0x0000000000001000L);
        if (test.variant() != 0)
            throw new Exception("wrong variant from bit set to 0");
        test = new UUID(55L, 0x8000000000001000L);
        if (test.variant() != 2)
            throw new Exception("wrong variant from bit set to 2");
       test = new UUID(55L, 0xc000000000001000L);
        if (test.variant() != 6)
            throw new Exception("wrong variant from bit set to 6");
       test = new UUID(55L, 0xe000000000001000L);
        if (test.variant() != 7)
            throw new Exception("wrong variant from bit set to 7");
    }