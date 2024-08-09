@Test
public void saveOrder_shouldFailIfAnActiveDrugOrderForTheSameOrderableAndOverlappingScheduleExists() throws Exception {
    final Patient patient = patientService.getPatient(2);
    final Concept cd4Count = conceptService.getConcept(5497);
    // Setup an active drug order for the same concept
    DrugOrder activeDrugOrder = new DrugOrder();
    activeDrugOrder.setPatient(patient);
    activeDrugOrder.setConcept(cd4Count);
    activeDrugOrder.setCareSetting(orderService.getCareSetting(2));
    activeDrugOrder.setEncounter(encounterService.getEncounter(6));
    activeDrugOrder.setOrderer(providerService.getProvider(1));
    activeDrugOrder.setDrug(drugService.getDrug(1)); // Assuming this gets a valid drug
    activeDrugOrder.setDateActivated(new Date());
    activeDrugOrder.setAutoExpireDate(DateUtils.addDays(new Date(), 10)); // Set auto-expire 10 days from now
    orderService.saveOrder(activeDrugOrder, null);

    // Attempt to save a new drug order with overlapping schedule
    DrugOrder newDrugOrder = new DrugOrder();
    newDrugOrder.setPatient(patient);
    newDrugOrder.setConcept(cd4Count);
    newDrugOrder.setCareSetting(activeDrugOrder.getCareSetting());
    newDrugOrder.setEncounter(encounterService.getEncounter(6));
    newDrugOrder.setOrderer(providerService.getProvider(1));
    newDrugOrder.setDrug(drugService.getDrug(1)); // Assuming this gets the same valid drug
    newDrugOrder.setDateActivated(new Date()); // Activated today
    newDrugOrder.setAutoExpireDate(DateUtils.addDays(new Date(), 5)); // Set auto-expire 5 days from now, overlapping with the active order

    expectedException.expect(APIException.class);
    expectedException.expectMessage("Order.cannot.have.more.than.one");
    orderService.saveOrder(newDrugOrder, null);
}