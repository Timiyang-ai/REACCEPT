@Test
    public void testSetDescription() throws SQLException
    {
        String description = "new description";
        bs.setDescription(context, description);
        assertThat("testSetDescription 0", bs.getDescription(), notNullValue());
        assertThat("testSetDescription 1", bs.getDescription(), not(equalTo("")));
        assertThat("testSetDescription 2", bs.getDescription(), equalTo(description));
    }