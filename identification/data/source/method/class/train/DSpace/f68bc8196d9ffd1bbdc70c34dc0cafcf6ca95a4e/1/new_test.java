    @Test
    public void findAll() throws SQLException {
        List<Group> groups = groupService.findAll(context, null);
        assertThat("findAll 1", groups, notNullValue());
        System.out.println("TEST GROUP OUTPUT " + groups);
        assertTrue("findAll 2", 0 < groups.size());
    }