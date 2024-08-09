@Test
public void saveOrder_shouldStopPreviousOrderAMomentBeforeActivatingTheNewOrder() throws Exception {
    // Setup: Clone an existing DrugOrder for revision
    DrugOrder order = (DrugOrder) orderService.getOrder(111).cloneForRevision();
    Date newActivationDate = new Date();
    order.setDateActivated(newActivationDate);
    order.setEncounter(encounterService.getEncounter(3));
    order.setOrderer(providerService.getProvider(1));

    // Mock or simulate the 'aMomentBefore' method to control its output for testing
    // Assuming 'aMomentBefore' is a method that subtracts a small amount of time (e.g., milliseconds) from the given date
    Date expectedStopDate = DateUtils.addMilliseconds(newActivationDate, -1); // Adjust based on the actual implementation of 'aMomentBefore'

    // Act: Save the revised order
    orderService.saveOrder(order, null);

    // Verify: The previous order should be stopped a moment before the activation date of the new order
    DrugOrder previousOrder = (DrugOrder) order.getPreviousOrder();
    assertNotNull("Previous order should not be null", previousOrder);
    assertEquals("Previous order should be stopped a moment before the new order's activation date",
                 expectedStopDate, previousOrder.getDateStopped());
}