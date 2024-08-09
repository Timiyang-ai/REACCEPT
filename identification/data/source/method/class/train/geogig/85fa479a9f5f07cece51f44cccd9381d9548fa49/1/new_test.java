@Test
    public void testIsDirectChild() {
        assertFalse(isDirectChild("", ""));
        assertTrue(isDirectChild("", "path"));
        assertFalse(isDirectChild("", "path/to"));

        assertFalse(isDirectChild("path", "path"));
        assertFalse(isDirectChild("path", ""));
        assertTrue(isDirectChild("path", "path/to"));
        assertFalse(isDirectChild("path", "path/to/node"));

        assertFalse(isDirectChild("path/to", ""));
        assertFalse(isDirectChild("path/to", "path"));
        assertFalse(isDirectChild("path/to", "path/to"));
        assertFalse(isDirectChild("path/to", "path2/to"));

        assertTrue(isDirectChild("path/to", "path/to/node"));

        assertTrue(isDirectChild("roads", "roads/highway"));
        assertFalse(isDirectChild("roads/highway", "roads"));
    }