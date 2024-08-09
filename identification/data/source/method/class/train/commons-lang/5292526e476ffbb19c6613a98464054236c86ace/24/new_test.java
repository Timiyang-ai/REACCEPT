@Test
    public void testHashCode() {
        final StrBuilder sb = new StrBuilder();
        final int hc1a = sb.hashCode();
        final int hc1b = sb.hashCode();
        assertEquals(0, hc1a);
        assertEquals(hc1a, hc1b);
        
        sb.append("abc");
        final int hc2a = sb.hashCode();
        final int hc2b = sb.hashCode();
        assertTrue(hc2a != 0);
        assertEquals(hc2a, hc2b);
    }