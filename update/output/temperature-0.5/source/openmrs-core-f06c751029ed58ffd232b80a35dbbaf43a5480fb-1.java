@Test
public void validate_shouldFailWithDetailedErrorIfJavaClassNameIsDuplicated() throws Exception {
    // Set up an existing OrderType to simulate the edit scenario
    OrderType existingOrderType = new OrderType();
    existingOrderType.setName("Existing Drug Order");
    existingOrderType.setJavaClassName("org.openmrs.ExistingDrugOrder");
    Collection<ConceptClass> existingConceptClasses = new HashSet<>();
    ConceptClass existingConceptClass = Context.getConceptService().getConceptClass(2); // Assuming this exists for the test context
    existingConceptClasses.add(existingConceptClass);
    existingOrderType.setConceptClasses(existingConceptClasses);

    // Assume this is being "edited" to have a conflicting Java class name
    OrderType editedOrderType = new OrderType();
    editedOrderType.setName("Edited Drug Order");
    editedOrderType.setJavaClassName("org.openmrs.ExistingDrugOrder"); // Duplicate Java class name
    Collection<ConceptClass> editedConceptClasses = new HashSet<>();
    ConceptClass editedConceptClass = Context.getConceptService().getConceptClass(3); // Different concept class
    editedConceptClasses.add(editedConceptClass);
    editedOrderType.setConceptClasses(editedConceptClasses);

    Errors errors = new BindException(editedOrderType, "orderType");
    new OrderTypeValidator().validate(editedOrderType, errors);

    // Asserting the detailed error message for java class name duplication
    Assert.assertTrue("Expected validation error for javaClassName field", errors.hasFieldErrors("javaClassName"));
    List<ObjectError> javaClassNameErrors = errors.getFieldErrors("javaClassName");
    boolean detailedErrorFound = javaClassNameErrors.stream().anyMatch(error -> 
        error.getCode().equals("OrderType.duplicate") && 
        error.getArguments()[0].equals("org.openmrs.ExistingDrugOrder") && 
        error.getArguments()[1].equals("Edited Drug Order")
    );
    Assert.assertTrue("Expected detailed error message for duplicated Java class name", detailedErrorFound);
}