@Test
    public void testTrim() {
        final StrBuilder sb = new StrBuilder();
        assertEquals("", sb.reverse().toString());
        
        sb.clear().append(" \u0000 ");
        assertEquals("", sb.trim().toString());
        
        sb.clear().append(" \u0000 a b c");
        assertEquals("a b c", sb.trim().toString());
        
        sb.clear().append("a b c \u0000 ");
        assertEquals("a b c", sb.trim().toString());
        
        sb.clear().append(" \u0000 a b c \u0000 ");
        assertEquals("a b c", sb.trim().toString());
        
        sb.clear().append("a b c");
        assertEquals("a b c", sb.trim().toString());
    }