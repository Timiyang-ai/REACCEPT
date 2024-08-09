@Test
    public void testGetSpecialGroups() throws SQLException, AuthorizeException
    {
        new NonStrictExpectations(authorizeService.getClass())
        {{
            // Allow Admin permissions - needed to create a new Group
            authorizeService.isAdmin((Context) any); result = true;
        }};
        
        // To test special groups we need a new Context object
        Context instance = new Context();
        
        // Create a new group & add it as a special group
        Group group = groupService.create(instance);
        UUID groupID = group.getID();
        instance.setSpecialGroup(groupID);
        
        // Also add Administrator group as a special group
        Group adminGroup = groupService.findByName(instance, Group.ADMIN);
        UUID adminGroupID = adminGroup.getID();
        instance.setSpecialGroup(adminGroupID);
        
        // Now get our special groups
        List<Group> specialGroups = instance.getSpecialGroups();
        assertThat("testGetSpecialGroup 0", specialGroups.size(), equalTo(2));
        assertThat("testGetSpecialGroup 1", specialGroups.get(0), equalTo(group));
        assertThat("testGetSpecialGroup 1", specialGroups.get(1), equalTo(adminGroup));

        // Cleanup our context
        cleanupContext(instance);
    }