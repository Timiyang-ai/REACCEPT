    @Test
    public void isEmpty() throws SQLException, AuthorizeException, EPersonDeletionException, IOException {
        assertTrue(groupService.isEmpty(topGroup));
        assertTrue(groupService.isEmpty(level1Group));
        assertTrue(groupService.isEmpty(level2Group));

        EPerson person = createEPersonAndAddToGroup("isEmpty@dspace.org", level2Group);
        assertFalse(groupService.isEmpty(topGroup));
        assertFalse(groupService.isEmpty(level1Group));
        assertFalse(groupService.isEmpty(level2Group));
        context.turnOffAuthorisationSystem();
        ePersonService.delete(context, person);
        context.restoreAuthSystemState();
        assertTrue(groupService.isEmpty(level2Group));
    }