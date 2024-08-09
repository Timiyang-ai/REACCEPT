@Test
@Verifies(value = "should fail if name is a duplicate", method = "validate(Object,Errors)")
public void validate_shouldFailIfNameIsADuplicate() throws Exception {
    OrderType orderType = new OrderType();
    orderType.setName("Drug order");
    Errors errors = new BindException(orderType, "orderType");
    new OrderTypeValidator().validate(orderType, errors);
    Assert.assertTrue(errors.hasFieldErrors("name"));
}