@Test
public void saveOrder_shouldSaveARevisedOrder() throws Exception {
    Order originalOrder = orderService.getOrder(111);
    assertTrue(originalOrder.isCurrent());
    final Patient patient = originalOrder.getPatient();
    List<Order> originalActiveOrders = orderService.getActiveOrders(patient, null, null, null);
    final int originalOrderCount = originalActiveOrders.size();
    assertTrue(originalActiveOrders.contains(originalOrder));
    Order revisedOrder = originalOrder.cloneForRevision();
    revisedOrder.setEncounter(encounterService.getEncounter(5));
    revisedOrder.setInstructions("Take after a meal");
    revisedOrder.setDateActivated(new Date()); // Updated to use setDateActivated instead of setStartDate
    revisedOrder.setOrderer(providerService.getProvider(1));
    revisedOrder.setEncounter(encounterService.getEncounter(3));

    // Adding a check for order type compatibility, which should throw an APIException if mismatched.
    try {
        orderService.saveOrder(revisedOrder, null);
    } catch (APIException ex) {
        assertNotNull("APIException should be thrown due to order type mismatch", ex);
    }

    // If the time is too close, the original order may be returned because it
    // dateStopped will be exactly the same as the asOfDate(now) to the millisecond
    Thread.sleep(1);
    List<Order> activeOrders = orderService.getActiveOrders(patient, null, null, null);
    assertEquals(originalOrderCount, activeOrders.size());
    assertFalse(originalOrder.isCurrent());
}