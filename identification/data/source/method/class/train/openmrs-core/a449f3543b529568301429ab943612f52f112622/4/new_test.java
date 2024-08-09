	@Test
	public void removeHandler_shouldNotFailWithInvalidKey() {
		Context.getObsService().removeHandler("SomeRandomHandler");
	}