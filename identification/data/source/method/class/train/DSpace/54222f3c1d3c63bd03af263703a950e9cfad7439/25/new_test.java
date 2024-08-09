@Test
    @Override
    public void testGetName()
    {
        //by default is empty
        assertThat("testGetName 0",collection.getName(), equalTo(""));
    }