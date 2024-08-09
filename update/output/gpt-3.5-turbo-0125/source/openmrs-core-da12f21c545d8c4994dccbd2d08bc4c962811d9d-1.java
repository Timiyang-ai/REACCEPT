@Test
public void saveOrder_shouldNotAllowChangingTheDrugOfThePreviousDrugOrderWhenRevisingAnOrder() throws Exception {
    DrugOrder order = (DrugOrder) orderService.getOrder(111).cloneForRevision();
    order.setDateActivated(new Date());
    order.setEncounter(encounterService.getEncounter(3));
    order.setOrderer(providerService.getProvider(1));
    Drug newDrug = conceptService.getDrug(2);
    DrugOrder previousOrder = (DrugOrder) order.getPreviousOrder();
    assertFalse(previousOrder.getDrug().equals(newDrug));
    previousOrder.setDrug(newDrug);

    expectedException.expect(APIException.class);
    expectedException.expectMessage("Cannot change the drug of a drug order");
    orderService.saveOrder(order, null);

    // Verify that stopOrder method is called with the correct parameters
    verify(orderService, times(1)).stopOrder(eq(previousOrder), any(Date.class));
}