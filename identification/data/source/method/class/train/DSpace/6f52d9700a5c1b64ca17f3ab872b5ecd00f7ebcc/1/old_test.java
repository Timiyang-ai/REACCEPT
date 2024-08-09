@Test
    public void testGetCommunities() throws Exception
    {
        assertThat("testGetCommunities 0",collection.getCommunities(), notNullValue());
        assertTrue("testGetCommunities 1",collection.getCommunities().size() == 1);
    }