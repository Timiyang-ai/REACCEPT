@Override
	public void validate(Object obj, Errors errors) throws APIException {
		log.debug("{}.validate...", this.getClass().getName());
		
		if (obj == null || !(obj instanceof Provider)) {
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type " + Provider.class);
		}
		
		Provider provider = (Provider) obj;
		
		if (provider.getPerson() == null && StringUtils.isBlank(provider.getName())) {
			errors.rejectValue("name", "Provider.error.personOrName.required");
			errors.rejectValue("person", "Provider.error.personOrName.required");
		}
		
		if (provider.getRetired() && StringUtils.isEmpty(provider.getRetireReason())) {
			errors.rejectValue("retireReason", "Provider.error.retireReason.required");
		}
		
		ValidateUtil.validateFieldLengths(errors, obj.getClass(), "name", "identifier", "retireReason");
		super.validateAttributes(provider, errors, Context.getProviderService().getAllProviderAttributeTypes());
	}