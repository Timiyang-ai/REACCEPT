@Test
    public void testGetFieldId()
    {
        MetadataValue instance = new MetadataValue();
        assertThat("testGetFieldId 0", instance.getValueId(), equalTo(0));

        assertThat("testGetFieldId 1", mv.getMetadataField().getFieldID(), equalTo(mf.getFieldID()));
    }