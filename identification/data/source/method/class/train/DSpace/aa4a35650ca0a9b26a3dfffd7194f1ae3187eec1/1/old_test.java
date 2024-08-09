@Test
    public void testFindByField() throws Exception
    {
        mv.create(context);
        int fieldId = mv.getFieldId();
        Collection found = MetadataValue.findByField(context, fieldId);
        assertThat("testFind 0",found, notNullValue());
        assertTrue("testFind 1",found.size() >= 1);        
    }