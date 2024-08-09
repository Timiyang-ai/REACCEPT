@Test
    public void testIsReadOnly() throws SQLException
    {
        // Our default context should NOT be read only
        assertThat("testIsReadOnly 0", context.isReadOnly(), equalTo(false));
        
        // Create a new read-only context
        Context instance = new Context(Context.Mode.READ_ONLY);
        assertThat("testIsReadOnly 1", instance.isReadOnly(), equalTo(true));

        //When in read-only, we only support abort().
        instance.abort();

        // Cleanup our context
        cleanupContext(instance);
    }