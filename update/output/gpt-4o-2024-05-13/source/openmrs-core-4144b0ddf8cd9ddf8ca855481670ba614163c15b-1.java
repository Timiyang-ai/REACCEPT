@Test
@Verifies(value = "should not allow editing an existing order frequency that is in use", method = "saveOrderFrequency(OrderFrequency)")
public void saveOrderFrequency_shouldNotAllowEditingAnExistingOrderFrequencyThatIsInUse() throws Exception {
    OrderFrequency orderFrequency = Context.getOrderService().getOrderFrequency(1);
    assertNotNull(orderFrequency);
    
    orderFrequency.setFrequencyPerDay(4d);
    
    try {
        Context.getOrderService().saveOrderFrequency(orderFrequency);
        fail("Expected an APIException to be thrown");
    } catch (APIException e) {
        assertEquals("This order frequency cannot be edited because it is already in use", e.getMessage());
    }
}