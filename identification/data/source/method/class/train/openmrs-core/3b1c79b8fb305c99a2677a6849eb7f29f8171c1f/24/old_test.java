	@Test
	public void getMessage_shouldReturnTheLastErrorCodeIfnoLocalizationIsFound() {
		MapBindingResult errors = new MapBindingResult(new HashMap<String, Object>(), "request");
		errors.rejectValue("myField", "myErrorCode");
		MessageSourceResolvable fieldError = errors.getFieldError("myField");
		Assert.assertEquals(3, fieldError.getCodes().length);
		Assert.assertEquals("myErrorCode.request.myField", fieldError.getCodes()[0]);
		Assert.assertEquals("myErrorCode.myField", fieldError.getCodes()[1]);
		Assert.assertEquals("myErrorCode", fieldError.getCodes()[2]);
		Assert.assertEquals("myErrorCode", Context.getMessageSourceService().getMessage(fieldError, Context.getLocale()));
	}