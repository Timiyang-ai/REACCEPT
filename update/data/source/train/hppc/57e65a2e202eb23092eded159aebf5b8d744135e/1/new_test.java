@Test
    public void testAdd()
    {
        assertTrue(set.add(key));
        assertFalse(set.add(key));
        assertEquals(1, set.size());
    }