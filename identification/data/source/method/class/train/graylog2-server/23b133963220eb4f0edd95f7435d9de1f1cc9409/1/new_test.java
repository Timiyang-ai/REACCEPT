@Test
    public void testExtractData() throws Exception {
        // A GELF chunk header. Sequence 2 of 7.
        String header = "1e0fdf0fcb728fd5b73b0232ee2db47ca1e1e859725c7f0202631f8fcb6d4297c32a00020007";
        String foo = asHex("foo".getBytes());
        header = header + foo;
        byte[] headerHex = Hex.decodeHex(header.toCharArray());
        DatagramPacket msg = new DatagramPacket(headerHex, headerHex.length);

        assertEquals("foo", new String(GELF.extractData(msg)));
    }