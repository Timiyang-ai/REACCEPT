@Test
    public void testSetName() throws SQLException
    {
        String name = "new name";
        bs.setName(context, name);
        assertThat("testGetName 0", bs.getName(), notNullValue());
        assertThat("testGetName 1", bs.getName(), not(equalTo("")));
        assertThat("testGetName 2", bs.getName(), equalTo(name));
    }