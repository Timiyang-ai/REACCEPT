@Test
    public void testSetSpecialGroup() throws SQLException
    {
        // To test special groups we need a new Context object
        Context instance = new Context();
        
        // Pass in random integers (need not be valid group IDs)
        UUID groupID1 = UUID.randomUUID();
        UUID groupID2 = UUID.randomUUID();
        instance.setSpecialGroup(groupID1);
        instance.setSpecialGroup(groupID2);
        
        assertThat("testSetSpecialGroup 0", instance.inSpecialGroup(groupID1), equalTo(true));
        assertThat("testSetSpecialGroup 1", instance.inSpecialGroup(groupID2), equalTo(true));
        assertThat("testSetSpecialGroup 2", instance.inSpecialGroup(UUID.randomUUID()), equalTo(false));

        // Cleanup our context
        cleanupContext(instance);
    }