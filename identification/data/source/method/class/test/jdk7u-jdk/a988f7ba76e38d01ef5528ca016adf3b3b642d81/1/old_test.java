    private static void versionTest() throws Exception {
        UUID test = UUID.randomUUID();
        if (test.version() != 4)
            throw new Exception("randomUUID not type 4");
        Random byteSource = new Random();
        byte[] someBytes = new byte[12];
        byteSource.nextBytes(someBytes);
        test = UUID.nameUUIDFromBytes(someBytes);
        if (test.version() != 3)
            throw new Exception("nameUUIDFromBytes not type 3");
        test = UUID.fromString("9835451d-e2e0-1e41-8a5a-be785f17dcda");
        if (test.version() != 1)
            throw new Exception("wrong version fromString 1");
        test = UUID.fromString("9835451d-e2e0-2e41-8a5a-be785f17dcda");
        if (test.version() != 2)
            throw new Exception("wrong version fromString 2");
        test = UUID.fromString("9835451d-e2e0-3e41-8a5a-be785f17dcda");
        if (test.version() != 3)
            throw new Exception("wrong version fromString 3");
        test = UUID.fromString("9835451d-e2e0-4e41-8a5a-be785f17dcda");
        if (test.version() != 4)
            throw new Exception("wrong version fromString 4");
        test = new UUID(0x0000000000001000L, 55L);
        if (test.version() != 1)
            throw new Exception("wrong version from bit set to 1");
        test = new UUID(0x0000000000002000L, 55L);
        if (test.version() != 2)
            throw new Exception("wrong version from bit set to 2");
        test = new UUID(0x0000000000003000L, 55L);
        if (test.version() != 3)
            throw new Exception("wrong version from bit set to 3");
        test = new UUID(0x0000000000004000L, 55L);
        if (test.version() != 4)
            throw new Exception("wrong version from bit set to 4");
    }