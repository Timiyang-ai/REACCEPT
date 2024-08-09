@Test
    public void testGetEventClassesByFamilyIdVersionAndType() throws Exception {
        EventClassFamilyDto eventClassFamily = createEventClassFamily();
        client.addEventClassFamilySchema(eventClassFamily.getId(), TEST_EVENT_CLASS_FAMILY_SCHEMA);
        List<EventClassDto> eventClasses = client.getEventClassesByFamilyIdVersionAndType(eventClassFamily.getId(), 1, EventClassType.EVENT);
        Assert.assertNotNull(eventClasses);
        Assert.assertEquals(4, eventClasses.size());
        for (EventClassDto eventClass : eventClasses) {
            Assert.assertEquals(eventClassFamily.getId(), eventClass.getEcfId());
            Assert.assertEquals(1, eventClass.getVersion());
        }
    }