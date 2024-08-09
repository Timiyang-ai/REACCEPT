@Test
public void saveOrder_shouldFailIfAnActiveDrugOrderForTheSameOrderableAndOverlappingScheduleExists() throws Exception {
    final Patient patient = patientService.getPatient(2);
    final Concept cd4Count = conceptService.getConcept(5497);
    // Assume this setup creates a drug order, not just any order
    DrugOrder activeDrugOrder = new DrugOrder();
    activeDrugOrder.setPatient(patient);
    activeDrugOrder.setConcept(cd4Count);
    activeDrugOrder.setCareSetting(orderService.getCareSetting(2));
    activeDrugOrder.setEncounter(encounterService.getEncounter(6));
    activeDrugOrder.setOrderer(providerService.getProvider(1));
    activeDrugOrder.setDateActivated(new Date());
    // Assuming this sets the schedule to make it active and overlapping
    activeDrugOrder.setAutoExpireDate(DateUtils.addDays(new Date(), 10));
    orderService.saveOrder(activeDrugOrder, null);

    DrugOrder newDrugOrder = new DrugOrder();
    newDrugOrder.setPatient(patient);
    newDrugOrder.setConcept(cd4Count);
    newDrugOrder.setCareSetting(orderService.getCareSetting(2));
    newDrugOrder.setEncounter(encounterService.getEncounter(6));
    newDrugOrder.setOrderer(providerService.getProvider(1));
    newDrugOrder.setDateActivated(new Date());
    // Set this order to overlap with the activeDrugOrder
    newDrugOrder.setAutoExpireDate(DateUtils.addDays(new Date(), 5));

    expectedException.expect(APIException.class);
    expectedException.expectMessage("Order.cannot.have.more.than.one");
    orderService.saveOrder(newDrugOrder, null);
}