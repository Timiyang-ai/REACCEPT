    public void test_replaceWith() throws Exception {
        CharsetDecoder d = Charset.forName("UTF-16").newDecoder();
        d.replaceWith("x");
        d.onMalformedInput(CodingErrorAction.REPLACE);
        d.onUnmappableCharacter(CodingErrorAction.REPLACE);
        ByteBuffer in = ByteBuffer.wrap(new byte[] { 109, 97, 109 });
        assertEquals("\u6d61x", d.decode(in).toString());
    }