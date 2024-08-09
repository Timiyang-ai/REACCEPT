@Test
    public void testToString() {
        StrBuilder sb = new StrBuilder("abc");
        assertEquals("abc", sb.toString());
    }