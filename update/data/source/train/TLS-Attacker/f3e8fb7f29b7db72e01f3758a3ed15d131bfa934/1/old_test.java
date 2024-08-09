@Test
    public void testGethighestProtocolVersion() {
        List<ProtocolVersion> versions = new LinkedList<>();
        versions.add(ProtocolVersion.TLS10);
        versions.add(ProtocolVersion.TLS11);
        versions.add(ProtocolVersion.TLS12);
        versions.add(ProtocolVersion.TLS13);
        ProtocolVersion highestProtocolVersion = ProtocolVersion.gethighestProtocolVersion(versions);
        assertArrayEquals(ProtocolVersion.TLS13.getValue(), highestProtocolVersion.getValue());
    }