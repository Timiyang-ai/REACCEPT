@Test
    public void testAddEventClassFamilySchema() throws Exception {
        EventClassFamilyDto eventClassFamily = createEventClassFamily();
        Schema expectedSchema = new Schema.Parser().parse(getResourceAsString(TEST_EVENT_CLASS_FAMILY_SCHEMA));

        client.addEventClassFamilySchema(eventClassFamily.getId(), TEST_EVENT_CLASS_FAMILY_SCHEMA);
        EventClassFamilyDto storedEventClassFamily = client.getEventClassFamilyById(eventClassFamily.getId());
        List<EventSchemaVersionDto> schemas = storedEventClassFamily.getSchemas();
        Assert.assertNotNull(schemas);
        Assert.assertEquals(1, schemas.size());
        EventSchemaVersionDto eventSchema = schemas.get(0);
        Assert.assertNotNull(eventSchema);
        Assert.assertEquals(1, eventSchema.getVersion());
        
        Assert.assertEquals(expectedSchema, new Schema.Parser().parse(eventSchema.getSchema()));
        
        client.addEventClassFamilySchema(eventClassFamily.getId(), TEST_EVENT_CLASS_FAMILY_SCHEMA);
        storedEventClassFamily = client.getEventClassFamilyById(eventClassFamily.getId());
        schemas = storedEventClassFamily.getSchemas();
        Assert.assertNotNull(schemas);
        Assert.assertEquals(2, schemas.size());
        eventSchema = schemas.get(1);
        Assert.assertNotNull(eventSchema);
        Assert.assertEquals(2, eventSchema.getVersion());
        Assert.assertEquals(expectedSchema, new Schema.Parser().parse(eventSchema.getSchema()));
    }