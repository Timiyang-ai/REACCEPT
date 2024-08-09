@Test
    public void testSetSource() throws SQLException
    {
        String source = "new source";
        bs.setSource(context, source);
        assertThat("testSetSource 0", bs.getSource(), notNullValue());
        assertThat("testSetSource 1", bs.getSource(), not(equalTo("")));
        assertThat("testSetSource 2", bs.getSource(), equalTo(source));
    }