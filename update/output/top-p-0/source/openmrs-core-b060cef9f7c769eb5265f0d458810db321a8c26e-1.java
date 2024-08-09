@Test
@Verifies(value = "should reject alert if object is null", method = "validate(Object,Errors)")
public void validate_shouldRejectAlertIfObjectIsNull() throws Exception {
    Alert alert = null;
    
    Errors errors = new BindException(alert, "alert");
    new AlertValidator().validate(alert, errors);
    
    assertTrue(errors.hasErrors());
}