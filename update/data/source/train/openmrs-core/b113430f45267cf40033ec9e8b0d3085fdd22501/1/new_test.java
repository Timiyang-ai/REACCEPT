@Test
	public void validate_shouldRejectAProviderIfItHasFewerThanMinOccursOfAnAttribute() throws Exception {
		executeDataSet(PROVIDER_ATTRIBUTE_TYPES_XML);
		ProviderAttributeType attributeType = providerService.getProviderAttributeType(1);
		attributeType.setMinOccurs(2);
		attributeType.setMaxOccurs(3);
		providerService.saveProviderAttributeType(attributeType);
		
		provider.addAttribute(makeAttribute("one"));
		Errors errors = new BindException(provider, "provider");
		new ProviderValidator().validate(provider, errors);
		Assert.assertTrue(errors.hasFieldErrors("activeAttributes"));
	}