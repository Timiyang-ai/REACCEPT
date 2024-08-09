@Test
    public void testSetUserFormatDescription() throws SQLException
    {
        String userdescription = "user format description";
        bs.setUserFormatDescription(userdescription);
        assertThat("testSetUserFormatDescription 0", bs.getUserFormatDescription()
                , notNullValue());
        assertThat("testSetUserFormatDescription 1", bs.getUserFormatDescription()
                , not(equalTo("")));
        assertThat("testSetUserFormatDescription 2", bs.getUserFormatDescription()
                , equalTo(userdescription));
    }