    public void test_replaceWith() throws Exception {
        Charset ascii = Charset.forName("US-ASCII");
        CharsetEncoder e = ascii.newEncoder();
        e.onMalformedInput(CodingErrorAction.REPLACE);
        e.onUnmappableCharacter(CodingErrorAction.REPLACE);
        e.replaceWith("=".getBytes("US-ASCII"));
        String input = "hello\u0666world";
        String output = ascii.decode(e.encode(CharBuffer.wrap(input))).toString();
        assertEquals("hello=world", output);
    }