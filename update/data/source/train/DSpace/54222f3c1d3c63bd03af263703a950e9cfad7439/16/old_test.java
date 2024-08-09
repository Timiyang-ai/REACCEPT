@Test
    public void testGetSpecialGroups() throws SQLException, AuthorizeException
    {
        new NonStrictExpectations(AuthorizeManager.class)
        {{
            // Allow Admin permissions - needed to create a new Group
            AuthorizeManager.isAdmin((Context) any); result = true;
        }};
        
        // To test special groups we need a new Context object
        Context instance = new Context();
        
        // Create a new group & add it as a special group
        Group group = Group.create(instance);
        int groupID = group.getID();
        instance.setSpecialGroup(groupID);
        
        // Also add Administrator group as a special group
        Group adminGroup = Group.find(instance, Group.ADMIN_ID);
        int adminGroupID = adminGroup.getID();
        instance.setSpecialGroup(adminGroupID);
        
        // Now get our special groups
        Group[] specialGroups = instance.getSpecialGroups();
        assertThat("testGetSpecialGroup 0", specialGroups.length, equalTo(2));
        assertThat("testGetSpecialGroup 1", specialGroups[0], equalTo(group));
        assertThat("testGetSpecialGroup 1", specialGroups[1], equalTo(adminGroup));

        // Cleanup our context
        cleanupContext(instance);
    }