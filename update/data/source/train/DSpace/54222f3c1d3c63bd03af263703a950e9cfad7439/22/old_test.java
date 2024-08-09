@Test
    public void testSetSource()
    {
        String source = "new source";
        bs.setSource(source);
        assertThat("testSetSource 0", bs.getSource(), notNullValue());
        assertThat("testSetSource 1", bs.getSource(), not(equalTo("")));
        assertThat("testSetSource 2", bs.getSource(), equalTo(source));
    }