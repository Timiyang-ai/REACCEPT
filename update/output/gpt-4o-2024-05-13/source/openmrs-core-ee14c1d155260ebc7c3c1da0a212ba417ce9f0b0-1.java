@Test
public void validate_shouldFailValidationIfDoseUnitsIsNullForSIMPLEDosingType() throws Exception {
    DrugOrder order = new DrugOrder();
    order.setDosingType(DrugOrder.DosingType.SIMPLE);
    order.setDoseUnits(null);
    Errors errors = new BindException(order, "order");
    
    // Assuming DosingInstructions is an interface and SimpleDosingInstructions is an implementation
    DosingInstructions dosingInstructions = new SimpleDosingInstructions();
    order.setDosingInstructions(dosingInstructions);
    
    new DrugOrderValidator().validate(order, errors);
    Assert.assertTrue(errors.hasFieldErrors("doseUnits"));
}