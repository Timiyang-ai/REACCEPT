@Test
    public void testSetShortDescription() throws SQLException
    {
        String desc = "short";
        bf.setShortDescription(desc);

        assertThat("testSetShortDescription 0", bf.getShortDescription(),
                notNullValue());
        assertThat("testSetShortDescription 1", bf.getShortDescription(),
                not(equalTo("")));
        assertThat("testSetShortDescription 2", bf.getShortDescription(),
                equalTo(desc));
    }