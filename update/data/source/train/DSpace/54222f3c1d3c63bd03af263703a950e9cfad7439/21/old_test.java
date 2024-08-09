@Test
    public void testGetFieldId()
    {
        MetadataValue instance = new MetadataValue();
        assertThat("testGetFieldId 0", instance.getFieldId(), equalTo(0));

        assertThat("testGetFieldId 1", mv.getFieldId(), equalTo(mf.getFieldID()));
    }