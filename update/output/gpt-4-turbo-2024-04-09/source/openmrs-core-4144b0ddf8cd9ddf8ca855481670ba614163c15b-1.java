@Test
public void saveOrder_shouldSaveARevisedOrder() {
    // Create an original order with an ID of 1
    Order originalOrder = new Order();
    originalOrder.setOrderId(1);

    // Create a revised order that references the original order
    Order revisedOrder = new Order();
    revisedOrder.setPreviousOrder(originalOrder);

    // Simulate saving the revised order which should ideally maintain the original order's ID
    Context.getOrderService().saveOrder(revisedOrder);

    // Fetch the revised order to verify if it has the correct previous order ID
    Order fetchedRevisedOrder = Context.getOrderService().getOrder(revisedOrder.getOrderId());

    // Assert that the previous order ID of the revised order is 1, as expected
    assertEquals("Expected the revised order's previous order ID to match the original order's ID", 1, fetchedRevisedOrder.getPreviousOrder().getOrderId().intValue());
}