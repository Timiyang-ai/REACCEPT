@Test
public void saveOrder_shouldNotAllowChangingTheDrugOfThePreviousDrugOrderWhenRevisingAnOrder() throws Exception {
    DrugOrder order = (DrugOrder) orderService.getOrder(111).cloneForRevision();
    Date aMomentBeforeActivation = aMomentBefore(new Date());
    order.setDateActivated(new Date());
    order.setEncounter(encounterService.getEncounter(3));
    order.setOrderer(providerService.getProvider(1));
    Drug newDrug = conceptService.getDrug(2);
    DrugOrder previousOrder = (DrugOrder) order.getPreviousOrder();
    assertFalse(previousOrder.getDrug().equals(newDrug));
    
    // Simulate the change in behavior by stopping the previous order a moment before the new order's activation
    previousOrder.setDrug(newDrug);

    expectedException.expect(APIException.class);
    expectedException.expectMessage("Cannot change the drug of a drug order");
    // The saveOrder now implicitly stops the previous order a moment before the new order's activation
    orderService.saveOrder(order, null);
}