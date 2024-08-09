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
    
    // Reflecting the change in the production method, the test now implicitly expects
    // the stopOrder to be called with a moment before the dateActivated of the new order.
    // This change does not directly impact the logic of this test but ensures the test is aligned
    // with the production code's expectations and behaviors.
    
    orderService.saveOrder(order, null);
}