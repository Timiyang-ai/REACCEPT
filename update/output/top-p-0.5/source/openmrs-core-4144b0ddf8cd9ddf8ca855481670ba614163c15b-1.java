@Test
public void saveOrderFrequency_shouldNotAllowEditingAnExistingOrderFrequencyThatIsInUse() throws Exception {
    OrderFrequency orderFrequency = Context.getOrderService().getOrderFrequency(1);
    assertNotNull(orderFrequency);
    
    orderFrequency.setFrequencyPerDay(4d);
    
    // Adjust the test to use the expected exception rule or try-catch block to assert the exception message
    // Assuming the use of JUnit 4's expected exception mechanism
    thrown.expect(APIException.class);
    thrown.expectMessage("Order.frequency.cannot.edit");
    
    Context.getOrderService().saveOrderFrequency(orderFrequency);
}