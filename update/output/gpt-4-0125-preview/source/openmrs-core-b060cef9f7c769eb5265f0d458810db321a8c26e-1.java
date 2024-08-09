@Test
@Verifies(value = "should set retired to false if retireReason is null or empty", method = "validate(Object,Errors)")
public void validate_shouldSetRetiredToFalseIfRetireReasonIsNullOrEmpty() throws Exception {
    Location location = new Location();
    location.setName("County General");
    location.setRetired(true);
    
    Errors errors = new BindException(location, "location");
    new LocationValidator().validate(location, errors);
    
    Assert.assertFalse(location.isRetired());
}