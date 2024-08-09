@Test
public void saveOrder_shouldFailIfAnActiveDrugOrderForTheSameConceptAndCareSettingWithOverlappingScheduleExists() throws Exception {
    final Patient patient = patientService.getPatient(2);
    final Concept cd4Count = conceptService.getConcept(5497);
    // Sanity check that we have an active drug order for the same concept with an overlapping schedule
    TestOrder activeOrder = (TestOrder) orderService.getOrder(7);
    assertTrue(activeOrder.isActive());
    assertEquals(cd4Count, activeOrder.getConcept());
    
    TestOrder newOrder = new TestOrder();
    newOrder.setPatient(patient);
    newOrder.setCareSetting(orderService.getCareSetting(2));
    newOrder.setConcept(cd4Count);
    newOrder.setEncounter(encounterService.getEncounter(6));
    newOrder.setOrderer(providerService.getProvider(1));
    newOrder.setCareSetting(activeOrder.getCareSetting());
    // Set dates to ensure overlap with the active order
    newOrder.setDateActivated(activeOrder.getDateActivated());
    newOrder.setAutoExpireDate(activeOrder.getAutoExpireDate());
    
    expectedException.expect(APIException.class);
    expectedException.expectMessage("Order.cannot.have.more.than.one");
    orderService.saveOrder(newOrder, null);
}