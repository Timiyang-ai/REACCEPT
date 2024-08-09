@Test(expected = APIException.class)
	public void validate_shouldRejectAProviderIfItHasFewerThanMinOccursOfAnAttribute() throws Exception {
		executeDataSet(PROVIDER_ATTRIBUTE_TYPES_XML);
		provider.addAttribute(makeAttribute("one"));
		ValidateUtil.validate(provider);
	}