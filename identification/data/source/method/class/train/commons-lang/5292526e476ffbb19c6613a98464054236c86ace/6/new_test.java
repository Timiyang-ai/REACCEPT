@Test
    public void testEqualsIgnoreCase() {
        final StrBuilder sb1 = new StrBuilder();
        final StrBuilder sb2 = new StrBuilder();
        assertTrue(sb1.equalsIgnoreCase(sb1));
        assertTrue(sb1.equalsIgnoreCase(sb2));
        assertTrue(sb2.equalsIgnoreCase(sb2));
        
        sb1.append("abc");
        assertFalse(sb1.equalsIgnoreCase(sb2));
        
        sb2.append("ABC");
        assertTrue(sb1.equalsIgnoreCase(sb2));
        
        sb2.clear().append("abc");
        assertTrue(sb1.equalsIgnoreCase(sb2));
        assertTrue(sb1.equalsIgnoreCase(sb1));
        assertTrue(sb2.equalsIgnoreCase(sb2));
        
        sb2.clear().append("aBc");
        assertTrue(sb1.equalsIgnoreCase(sb2));
    }