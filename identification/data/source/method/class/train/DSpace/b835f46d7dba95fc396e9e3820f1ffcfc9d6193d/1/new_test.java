    @Test
    public void allMemberGroups() throws SQLException, AuthorizeException, EPersonDeletionException, IOException {
        EPerson ePerson = createEPersonAndAddToGroup("allMemberGroups@dspace.org", level1Group);
        try {
            assertTrue(
                groupService.allMemberGroups(context, ePerson).containsAll(Arrays.asList(topGroup, level1Group)));
        } finally {
            context.turnOffAuthorisationSystem();
            ePersonService.delete(context, ePerson);
            context.restoreAuthSystemState();
        }

    }