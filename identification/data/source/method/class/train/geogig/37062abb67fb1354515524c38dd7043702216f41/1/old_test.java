@SuppressWarnings("deprecation")
    @Test
    public void testToPath() {
        assertEquals("path", toPath(ImmutableList.of("path")));
        assertEquals("path", toPath(ImmutableList.of("path"), null));
        assertEquals("path/to", toPath(ImmutableList.of("path", "to"), null));
        assertEquals("path/to/node", toPath(ImmutableList.of("path", "to", "node"), null));
        assertEquals("path/to/node", toPath(ImmutableList.of("path", "to"), "node"));
        assertEquals("path/to/node", toPath(ImmutableList.of("path"), "to", "node"));
        try {
            toPath(ImmutableList.of("path", "to"), (String) null);
            fail("Expected precondition violation");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage(), e.getMessage().contains("cannot have null"));
        }
    }