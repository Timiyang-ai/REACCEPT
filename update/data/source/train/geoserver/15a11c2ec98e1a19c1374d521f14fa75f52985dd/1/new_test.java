@Test
    public void testEncode() {
        String result = NCNameResourceCodec.encode("ws", "name");
        assertEquals("ws__name", result);
    }