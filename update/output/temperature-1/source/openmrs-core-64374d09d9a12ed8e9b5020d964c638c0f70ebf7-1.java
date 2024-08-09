@Test
public void saveVisitAttributeType_shouldCreateANewVisitAttributeType() throws Exception {
    executeDataSet(VISITS_ATTRIBUTES_XML);
    int originalSize = service.getAllVisitAttributeTypes().size();
    VisitAttributeType vat = new VisitAttributeType();
    vat.setName("Another one");
    vat.setDatatypeClassname(FreeText.class.getName());
    service.saveVisitAttributeType(vat);
    
    // Asserting not null ID to ensure the VisitAttributeType got persisted
    Assert.assertNotNull(vat.getId());
    
    // Should see an increase in count post save
    Assert.assertEquals(originalSize + 1, service.getAllVisitAttributeTypes().size());
}