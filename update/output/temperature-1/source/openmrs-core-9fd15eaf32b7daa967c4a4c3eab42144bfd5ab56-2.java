@Test
public void saveOrder_shouldSaveARevisedOrderAndCheckOrderTypeCompatibility() throws Exception {
    Order originalOrder = orderService.getOrder(111);
    assertTrue("The original order should be current before revision", originalOrder.isCurrent());
    final Patient patient = originalOrder.getPatient();
    List<Order> originalActiveOrders = orderService.getActiveOrders(patient, null, null, null);
    final int originalOrderCount = originalActiveOrders.size();
    assertTrue("The original active orders should contain the original order", originalActiveOrders.contains(originalOrder));

    Order revisedOrder = originalOrder.cloneForRevision();
    revisedOrder.setEncounter(encounterService.getEncounter(5));
    revisedOrder.setInstructions("Take after a meal");
    revisedOrder.setDateActivated(new Date()); // Adjusted to accommodate the change from setStartDate to setDateActivated
    revisedOrder.setOrderer(providerService.getProvider(1));
    revisedOrder.setEncounter(encounterService.getEncounter(3));
    
    // Including exception handling to test the new production check added for order type compatibility
    try {
        orderService.saveOrder(revisedOrder, null);
    } catch (APIException ex) {
        fail("Saving a revised order with correct order type should not throw an APIException.");
    }
    
    // Control thread to ensure consistency in the timing of status update checks
    Thread.sleep(1); // To ensure the dateStopped and dateActivated are not equal to Now to the millisecond
    List<Order> activeOrders = orderService.getActiveOrders(patient, null, null, null);
    assertEquals("The count of active orders should remain the same after revision", originalOrderCount, activeOrders.size());
    assertFalse("The original order should no longer be current after revision", originalOrder.isCurrent());
}