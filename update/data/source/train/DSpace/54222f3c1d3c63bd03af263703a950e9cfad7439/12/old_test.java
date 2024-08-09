@Test
    public void testGetCommunities() throws Exception
    {
        assertThat("testGetCommunities 0",c.getCommunities(), notNullValue());
        assertTrue("testGetCommunities 1",c.getCommunities().length == 0);
    }