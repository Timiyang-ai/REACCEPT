@Test
public void saveOrder_shouldFailIfAnActiveDrugOrderForTheSameOrderableAndOverlappingScheduleExists() throws Exception {
    final Patient patient = patientService.getPatient(2);
    final Concept cd4Count = conceptService.getConcept(5497);
    // Assuming a valid Concept exists for doseUnits and frequency, and these fulfill the system's requirements
    final Concept doseUnits = conceptService.getConcept(50); // Placeholder, replace with an actual concept
    final OrderFrequency frequency = orderService.getOrderFrequency(1); // Placeholder, replace with an actual frequency

    // Assuming valid Concept for the route
    final Concept routeConcept = conceptService.getConcept(22); // Placeholder, replace with an actual route concept

    // Create and save the first DrugOrder adhering to system's requirement
    DrugOrder activeDrugOrder = new DrugOrder();
    activeDrugOrder.setPatient(patient);
    activeDrugOrder.setOrderType(orderService.getOrderType(1)); // Assuming 1 is the ID for DrugOrder type
    activeDrugOrder.setConcept(cd4Count);
    activeDrugOrder.setCareSetting(orderService.getCareSetting(2));
    activeDrugOrder.setEncounter(encounterService.getEncounter(6));
    activeDrugOrder.setOrderer(providerService.getProvider(1));
    activeDrugOrder.setDateActivated(new Date());
    activeDrugOrder.setDose(1.0);
    activeDrugOrder.setDoseUnits(doseUnits);
    activeDrugOrder.setRoute(routeConcept); // Assuming assigning Concept directly is valid for route
    activeDrugOrder.setFrequency(frequency);
    activeDrugOrder.setAutoExpireDate(new Date(System.currentTimeMillis() + 864000000)); // Extending auto-expire 10 days into the future
    orderService.saveOrder(activeDrugOrder, null);

    // Attempt to save a second DrugOrder with overlapping schedule
    DrugOrder newDrugOrder = new DrugOrder();
    newDrugOrder.setPatient(patient);
    newDrugOrder.setOrderType(orderService.getOrderType(1)); // Ensure this matches the expected DrugOrder type
    newDrugOrder.setConcept(cd4Count);
    newDrugOrder.setCareSetting(orderService.getCareSetting(2));
    newDrugOrder.setEncounter(encounterService.getEncounter(6));
    newDrugOrder.setOrderer(providerService.getProvider(1));
    newDrugOrder.setDateActivated(new Date(System.currentTimeMillis() - 432000000)); // Overlap by starting 5 days ago
    newDrugOrder.setAutoExpireDate(new Date(System.currentTimeMillis() + 432000000)); // Extending 5 days into the future
    newDrugOrder.setDose(1.0);
    newDrugOrder.setDoseUnits(doseUnits);
    newDrugOrder.setRoute(routeConcept);
    newDrugOrder.setFrequency(frequency);

    expectedException.expect(APIException.class);
    expectedException.expectMessage("Order.cannot.have.more.than.one");
    orderService.saveOrder(newDrugOrder, null);
}