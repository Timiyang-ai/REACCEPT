    @Test
    public void findByName() throws SQLException {
        Group group = groupService.findByName(context, "topGroup");
        assertThat("findByName 1", group, notNullValue());
        assertThat("findByName 2", group.getName(), notNullValue());
        assertThat("findByName 2", group.getName(), equalTo("topGroup"));
    }