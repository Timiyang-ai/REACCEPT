@Test
    public void testAdd()
    {
        assertFalse(set.add(key));
        assertTrue(set.add(key));
        assertEquals(1, set.size());
    }