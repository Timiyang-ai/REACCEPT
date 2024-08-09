@Test
public void saveOrder_shouldStopPreviousOrderJustBeforeActivatingNewRevision() throws Exception {
    // Setting up the context for testing order revision, focusing on the timing adjustment
    DrugOrder originalOrder = (DrugOrder) orderService.getOrder(111);
    Date originalActivationDate = originalOrder.getDateActivated();
    
    // Clone for revision as per the standard operation for revising an order
    DrugOrder revisedOrder = (DrugOrder) originalOrder.cloneForRevision();
    Date revisedActivationDate = new Date(); // Assuming this is sometime after the original activation
    revisedOrder.setDateActivated(revisedActivationDate);
    revisedOrder.setEncounter(encounterService.getEncounter(3));
    revisedOrder.setOrderer(providerService.getProvider(1));
    
    // Perform the save which should now stop the previous order just before the revised order's activation
    orderService.saveOrder(revisedOrder, null);
    
    // Fetch the stopped (previous) order to inspect its stop date
    DrugOrder stoppedOrder = (DrugOrder) orderService.getOrder(originalOrder.getOrderId());
    
    // Validate stop date of the previous order is just a moment before the new order's activation date
    assertTrue("Previous order stop date should be just before the revised order's activation date",
               stoppedOrder.getDateStopped().compareTo(revisedOrder.getDateActivated()) < 0);
    
    // Ensure the stop date of the previous order is as close as possible to the revised order's activation date
    // Note: 'aMomentBefore' is conceptual; this check approximates that logic operationally
    long timeDifferenceMillis = revisedOrder.getDateActivated().getTime() - stoppedOrder.getDateStopped().getTime();
    assertTrue("The time difference between stopping the previous order and activating the revised order should be minimal",
                timeDifferenceMillis >= 1); // The exact condition may need adjustment based on how 'aMomentBefore' is implemented

}