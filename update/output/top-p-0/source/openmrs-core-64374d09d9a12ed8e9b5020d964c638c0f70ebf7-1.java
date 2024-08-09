@Test
public void saveVisitAttributeType_shouldCreateANewVisitAttributeTypeAndPersistIt() throws Exception {
    executeDataSet(VISITS_ATTRIBUTES_XML);
    Assert.assertEquals(3, service.getAllVisitAttributeTypes().size());
    VisitAttributeType vat = new VisitAttributeType();
    vat.setName("Another one");
    vat.setDatatypeClassname(FreeText.class.getName());
    service.saveVisitAttributeType(vat);
    Assert.assertNotNull(vat.getId());
    // Fetch the saved VisitAttributeType to ensure it's correctly persisted and can be retrieved
    VisitAttributeType fetchedVat = service.getVisitAttributeType(vat.getId());
    Assert.assertNotNull("The saved VisitAttributeType should be retrievable from the service.", fetchedVat);
    Assert.assertEquals("Another one", fetchedVat.getName());
    Assert.assertEquals(FreeText.class.getName(), fetchedVat.getDatatypeClassname());
    Assert.assertEquals(4, service.getAllVisitAttributeTypes().size());
}