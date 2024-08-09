@Test
    public void testEncode() {
        String result = NSNameResourceCodec.encode("ws", "name");
        assertEquals("ws__name", result);
    }