@Test
public void saveOrderFrequency_shouldNotAllowEditingAnExistingOrderFrequencyThatIsInUse() throws Exception {
    expectedException.expect(APIException.class);
    expectedException.expectMessage("Order.frequency.cannot.edit");

    OrderFrequency orderFrequency = Context.getOrderService().getOrderFrequency(1);
    assertNotNull(orderFrequency);
    
    orderFrequency.setFrequencyPerDay(4d);
    Context.getOrderService().saveOrderFrequency(orderFrequency);
}
//