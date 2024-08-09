@Test
    public void testComplete() throws SQLException 
    {
        // To test complete() we need a new Context object
        Context instance = new Context();
        
        // By default, we should have a new DB connection, so let's make sure it is there
        assertThat("testComplete 0", instance.getDBConnection(), notNullValue());
        assertThat("testComplete 1", instance.getDBConnection().isSessionAlive(), equalTo(true));
        assertThat("testComplete 2", instance.isValid(), equalTo(true));
        
        // Now, call complete(). This should set DB connection to null & invalidate context
        instance.complete();
        assertThat("testComplete 3", instance.getDBConnection(), nullValue());
        assertThat("testComplete 4", instance.isValid(), equalTo(false));

        // Cleanup our new context
        cleanupContext(instance);
        // TODO: May also want to test that complete() is calling commit()?
    }