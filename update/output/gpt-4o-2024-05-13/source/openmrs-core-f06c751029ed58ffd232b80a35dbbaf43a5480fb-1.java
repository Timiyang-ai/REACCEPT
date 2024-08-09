@Test
@Verifies(value = "should fail if name is a duplicate", method = "validate(Object,Errors)")
public void validate_shouldFailIfNameIsADuplicate() throws Exception {
    OrderType orderType = new OrderType();
    orderType.setName("Drug order");
    orderType.setJavaClassName("org.openmrs.TestDrugOrder");
    Collection<ConceptClass> conceptClasses = new HashSet<>();
    conceptClasses.add(Context.getConceptService().getConceptClass(2));
    orderType.setConceptClasses(conceptClasses);

    Errors errors = new BindException(orderType, "orderType");
    new OrderTypeValidator().validate(orderType, errors);

    Assert.assertTrue(errors.hasFieldErrors("name"));
    Assert.assertTrue(errors.hasFieldErrors("javaClassName"));
    Assert.assertTrue(errors.hasFieldErrors("conceptClasses[0]"));
}