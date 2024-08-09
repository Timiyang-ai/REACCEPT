@Test
public void validate_shouldFailValidationIfDoseUnitsIsNullForSIMPLEDosingType() throws Exception {
    DrugOrder order = new DrugOrder();
    order.setDosingType(DrugOrder.DosingType.SIMPLE);
    order.setDoseUnits(null);
    Errors errors = new BindException(order, "order");
    new DrugOrderValidator().validate(order, errors);
    Assert.assertTrue(errors.hasFieldErrors("doseUnits"));
}