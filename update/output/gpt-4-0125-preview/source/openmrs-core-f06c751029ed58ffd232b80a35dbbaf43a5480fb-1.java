@Test
@Verifies(value = "should fail if name, javaClassName, or conceptClasses are duplicates", method = "validate(Object,Errors)")
public void validate_shouldFailIfNameJavaClassNameOrConceptClassesAreDuplicates() throws Exception {
    OrderType orderType = new OrderType();
    orderType.setName("Drug order");
    orderType.setJavaClassName("org.openmrs.TestDrugOrder");
    Collection<ConceptClass> conceptClasses = new HashSet<>();
    conceptClasses.add(Context.getConceptService().getConceptClass(2)); // Assuming this ID is for a ConceptClass that would be a duplicate
    orderType.setConceptClasses(conceptClasses);
    
    Errors errors = new BindException(orderType, "orderType");
    new OrderTypeValidator().validate(orderType, errors);
    
    Assert.assertTrue(errors.hasFieldErrors("name"));
    Assert.assertTrue(errors.hasFieldErrors("javaClassName"));
    Assert.assertTrue(errors.hasFieldErrors("conceptClasses[0]")); // Assuming the index of the duplicate ConceptClass is 0
}