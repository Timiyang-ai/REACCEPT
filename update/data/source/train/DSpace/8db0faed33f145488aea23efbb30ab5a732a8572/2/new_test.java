@Test
    public void testGetFieldId()
    {
        MetadataValue instance = new MetadataValue();
        assertThat("testGetFieldId 0", instance.getID(), equalTo(0));

        assertThat("testGetFieldId 1", mv.getMetadataField().getID(), equalTo(mf.getID()));
    }