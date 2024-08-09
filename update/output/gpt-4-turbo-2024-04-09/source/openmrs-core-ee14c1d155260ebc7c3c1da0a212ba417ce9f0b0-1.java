@Test
public void validate_shouldFailValidationIfDoseUnitsIsNullForSIMPLEDosingType() throws Exception {
    DrugOrder order = new DrugOrder();
    order.setDosingType(DrugOrder.DosingType.SIMPLE);
    order.setDoseUnits(null);

    // Reflecting changes in the production code where DosingInstructions instance is used for validation
    DosingInstructions dosingInstructions = mock(DosingInstructions.class);
    when(order.getDosingInstructionsInstance()).thenReturn(dosingInstructions);
    Errors errors = new BindException(order, "order");

    new DrugOrderValidator().validate(order, errors);

    // Assuming that the DosingInstructions class has its own validation method which should be called
    verify(dosingInstructions).validate(order, errors);
    Assert.assertTrue(errors.hasFieldErrors("doseUnits"));
}