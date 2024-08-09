@Test
    public void testHashCode() {
        StrBuilder sb = new StrBuilder();
        int hc1a = sb.hashCode();
        int hc1b = sb.hashCode();
        assertEquals(0, hc1a);
        assertEquals(hc1a, hc1b);
        
        sb.append("abc");
        int hc2a = sb.hashCode();
        int hc2b = sb.hashCode();
        assertTrue(hc2a != 0);
        assertEquals(hc2a, hc2b);
    }