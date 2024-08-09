@Test
public void saveVisitAttributeType_shouldCreateANewVisitAttributeType() throws Exception {
    executeDataSet(VISITS_ATTRIBUTES_XML);
    Assert.assertEquals(3, service.getAllVisitAttributeTypes().size());

    VisitAttributeType vat = new VisitAttributeType();
    vat.setName("Another one");
    vat.setDatatypeClassname(FreeText.class.getName());

    // Assuming the transactional behavior might require us to explicitly flush the session
    // to ensure the persistence context is synchronized with the database.
    service.saveVisitAttributeType(vat);
    service.getSessionFactory().getCurrentSession().flush();

    Assert.assertNotNull(vat.getId());
    Assert.assertEquals(4, service.getAllVisitAttributeTypes().size());
}