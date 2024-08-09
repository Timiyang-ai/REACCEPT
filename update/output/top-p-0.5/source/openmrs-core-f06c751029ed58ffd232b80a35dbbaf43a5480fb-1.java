@Test
@Verifies(value = "should fail if name is a duplicate and handle new validation checks", method = "validate(Object,Errors)")
public void validate_shouldFailIfNameIsADuplicateAndHandleNewValidationChecks() throws Exception {
    OrderType orderType = new OrderType();
    orderType.setName("Drug order");
    orderType.setJavaClassName("org.openmrs.Order");
    Collection<ConceptClass> conceptClasses = new HashSet<>();
    ConceptClass cc = Context.getConceptService().getConceptClass(1); // Assuming this ID exists for the sake of example
    conceptClasses.add(cc);
    orderType.setConceptClasses(conceptClasses);

    Errors errors = new BindException(orderType, "orderType");
    new OrderTypeValidator().validate(orderType, errors);

    Assert.assertTrue("Should have errors for duplicate name", errors.hasFieldErrors("name"));
    Assert.assertTrue("Should have errors for duplicate javaClassName", errors.hasFieldErrors("javaClassName"));
    Assert.assertTrue("Should have errors for duplicate conceptClasses", errors.hasFieldErrors("conceptClasses[0]"));
}