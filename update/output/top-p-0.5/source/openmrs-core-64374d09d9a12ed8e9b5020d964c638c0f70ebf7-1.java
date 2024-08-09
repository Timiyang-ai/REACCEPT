@Test
public void saveVisitAttributeType_shouldCreateANewVisitAttributeType() throws Exception {
    executeDataSet(VISITS_ATTRIBUTES_XML);
    Assert.assertEquals(3, service.getAllVisitAttributeTypes().size());
    VisitAttributeType vat = new VisitAttributeType();
    vat.setName("Another one");
    vat.setDatatypeClassname(FreeText.class.getName());
    service.saveVisitAttributeType(vat);
    Assert.assertNotNull(vat.getId());
    
    // Explicitly fetch the saved entity to ensure it was committed
    VisitAttributeType fetchedVat = service.getVisitAttributeType(vat.getId());
    Assert.assertNotNull("The saved VisitAttributeType should be retrievable post-commit.", fetchedVat);
    Assert.assertEquals("Another one", fetchedVat.getName());
    Assert.assertEquals(FreeText.class.getName(), fetchedVat.getDatatypeClassname());
    
    Assert.assertEquals(4, service.getAllVisitAttributeTypes().size());
}