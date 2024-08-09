@Test
public void validate_shouldFailIfJavaClassNameOrConceptClassIsADuplicate() throws Exception {
    OrderType orderType = new OrderType();
    orderType.setName("Drug order");
    orderType.setJavaClassName("org.openmrs.Order");
    ConceptClass conceptClass = Context.getConceptService().getConceptClass(1); // Assuming 1 is a valid ID for a ConceptClass
    Set<ConceptClass> conceptClasses = new HashSet<>();
    conceptClasses.add(conceptClass);
    orderType.setConceptClasses(conceptClasses);

    Errors errors = new BindException(orderType, "orderType");
    new OrderTypeValidator().validate(orderType, errors);

    // Check for errors related to duplicate java class name
    Assert.assertTrue("Expected validation error for duplicate java class name", errors.hasFieldErrors("javaClassName"));
    
    // Check for errors related to duplicate concept classes
    Assert.assertTrue("Expected validation error for duplicate concept class", errors.hasFieldErrors("conceptClasses[0]"));
}