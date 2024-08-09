@Test
    public void testToString() {
        final StrBuilder sb = new StrBuilder("abc");
        assertEquals("abc", sb.toString());
    }