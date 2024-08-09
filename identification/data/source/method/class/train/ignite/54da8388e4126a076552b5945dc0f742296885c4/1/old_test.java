@Test
    public void testConvert() {
        // 8 bytes total: 1b, 1b, 3b, 3b.

        byte[] bytes = InlineIndexHelper.trimUTF8("00\u20ac\u20ac".getBytes(Charsets.UTF_8), 7);
        assertEquals(5, bytes.length);

        String s = new String(bytes);
        assertEquals(3, s.length());

        bytes = InlineIndexHelper.trimUTF8("aaaaaa".getBytes(Charsets.UTF_8), 4);
        assertEquals(4, bytes.length);
    }