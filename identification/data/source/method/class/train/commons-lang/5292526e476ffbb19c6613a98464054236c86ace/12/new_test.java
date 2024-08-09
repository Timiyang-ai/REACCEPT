@Test
    public void testAppendSeparator_String() {
        final StrBuilder sb = new StrBuilder();
        sb.appendSeparator(",");  // no effect
        assertEquals("", sb.toString());
        sb.append("foo");
        assertEquals("foo", sb.toString());
        sb.appendSeparator(",");
        assertEquals("foo,", sb.toString());
    }