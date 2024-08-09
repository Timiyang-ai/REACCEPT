	@Test
	public void validate_shouldFailValidationForLocationClassIfFieldLengthsAreNotCorrect() {
		Location location = new Location();
		String longString = "too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text";
		
		String[] LocationFields = new String[] { "name", "description", "address1", "address2", "address3", "address4",
		        "address5", "address6", "address7", "address8", "address9", "address10", "address11", "address12",
		        "address13", "address14", "address15", "cityVillage", "stateProvince", "country", "postalCode", "latitude",
		        "longitude", "countyDistrict", "retireReason" };
		
		String errorCode = "error.exceededMaxLengthOfField";
		
		location.setName(longString);
		location.setDescription(longString);
		location.setAddress1(longString);
		location.setAddress2(longString);
		location.setAddress3(longString);
		location.setAddress4(longString);
		location.setAddress5(longString);
		location.setAddress6(longString);
		location.setAddress7(longString);
		location.setAddress8(longString);
		location.setAddress9(longString);
		location.setAddress10(longString);
		location.setAddress11(longString);
		location.setAddress12(longString);
		location.setAddress13(longString);
		location.setAddress14(longString);
		location.setAddress15(longString);
		location.setCityVillage(longString);
		location.setStateProvince(longString);
		location.setCountry(longString);
		location.setPostalCode(longString);
		location.setLatitude(longString);
		location.setLongitude(longString);
		location.setCountyDistrict(longString);
		location.setRetireReason(longString);
		
		Errors errors = new BindException(location, "location");
		dao.validate(location, errors);
		
		for (String feilds : LocationFields) {
			FieldError fielderror = errors.getFieldError(feilds);
			Assert.assertTrue(errorCode.equals(fielderror.getCode()));
		}
		
	}