@Test
    public void testSetDescription()
    {
        String description = "new description";
        bs.setDescription(description);
        assertThat("testSetDescription 0", bs.getDescription(), notNullValue());
        assertThat("testSetDescription 1", bs.getDescription(), not(equalTo("")));
        assertThat("testSetDescription 2", bs.getDescription(), equalTo(description));
    }