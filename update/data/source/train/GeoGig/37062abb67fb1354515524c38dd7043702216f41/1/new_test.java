@Test
    public void testIsChild() {
        assertFalse(isChild("", ""));
        assertTrue(isChild("", "path"));
        assertTrue(isChild("", "path/to"));

        assertFalse(isChild("path", "path"));
        assertFalse(isChild("path", ""));
        assertTrue(isChild("path", "path/to"));
        assertTrue(isChild("path", "path/to/node"));

        assertFalse(isChild("path/to", ""));
        assertFalse(isChild("path/to", "path"));
        assertFalse(isChild("path/to", "path/to"));
        assertFalse(isChild("path/to", "path2/to"));

        assertTrue(isChild("path/to", "path/to/node"));
    }