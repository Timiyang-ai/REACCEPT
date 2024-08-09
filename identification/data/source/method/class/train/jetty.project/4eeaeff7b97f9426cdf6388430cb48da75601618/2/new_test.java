@Test
    public void testContainsKey()
    {
        assertTrue(m5.containsKey("abc"));
        assertTrue(!m5.containsKey("aBc"));
        assertTrue(m5.containsKey("bbb"));
        assertTrue(!m5.containsKey("xyz"));
        
        assertTrue(m5i.containsKey("abc"));
        assertTrue(m5i.containsKey("aBc"));
        assertTrue(m5i.containsKey("ABC"));
    }