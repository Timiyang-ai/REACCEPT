@Test
    public void testAbort2() throws SQLException 
    {
        // To test abort() we need a new Context object
        Context instance = new Context();
        
        // Call abort twice. The second call should NOT throw an error 
        // and effectively does nothing
        instance.abort();
        instance.abort();

        // Cleanup our context
        cleanupContext(instance);
    }