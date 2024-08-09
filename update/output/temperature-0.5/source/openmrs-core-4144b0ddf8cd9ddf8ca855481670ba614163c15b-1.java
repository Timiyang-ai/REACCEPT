@Test
public void saveOrderFrequency_shouldNotAllowEditingAnExistingOrderFrequencyThatIsInUse() throws Exception {
    OrderFrequency orderFrequency = Context.getOrderService().getOrderFrequency(1);
    assertNotNull("The order frequency should not be null", orderFrequency);
    
    orderFrequency.setFrequencyPerDay(4d);
    
    // Adjusting the test to check for the specific exception message as per the new requirements
    // Note: The change in the production code does not directly affect this part of the test,
    // but the sample diff suggests adjusting how we expect exceptions and their messages.
    expectedException.expect(APIException.class);
    expectedException.expectMessage("Order.frequency.cannot.edit");
    
    Context.getOrderService().saveOrderFrequency(orderFrequency);
}