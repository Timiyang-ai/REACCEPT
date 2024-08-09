@Test
    public void testSetSpecialGroup() throws SQLException
    {
        // To test special groups we need a new Context object
        Context instance = new Context();
        
        // Pass in random integers (need not be valid group IDs)
        instance.setSpecialGroup(10000);
        instance.setSpecialGroup(10001);
        
        assertThat("testSetSpecialGroup 0", instance.inSpecialGroup(10000), equalTo(true));
        assertThat("testSetSpecialGroup 1", instance.inSpecialGroup(10001), equalTo(true));
        assertThat("testSetSpecialGroup 2", instance.inSpecialGroup(20000), equalTo(false));

        // Cleanup our context
        cleanupContext(instance);
    }