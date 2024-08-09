    @Test
    public void toBufferedString() {
        final byte[] bytes = "abcd".getBytes();
        charSkippingBufferedString.set(bytes,0, bytes.length - 1);
        charSkippingBufferedString.skipIndex(bytes.length - 1);

        assertNotNull(charSkippingBufferedString.getBuffer());

        final BufferedString bufferedString = charSkippingBufferedString.toBufferedString();
        assertNotNull(bufferedString.getBuffer());
        assertEquals(3, bufferedString.length());
        assertEquals(0, bufferedString.getOffset());

        assertEquals("abc", bufferedString.toString());
    }