public void validate(Object obj, Errors errors) throws APIException {
		if (log.isDebugEnabled())
			log.debug(this.getClass().getName() + ".validate...");
		
		if (obj == null || !(obj instanceof Provider))
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type " + Provider.class);
		
		Provider provider = (Provider) obj;
		
		if (StringUtils.isBlank(provider.getIdentifier())) {
			errors.rejectValue("identifier", "Provider.error.identifier.required");
		}
		
		if (provider.getPerson() == null && StringUtils.isBlank(provider.getName())) {
			errors.rejectValue("name", "Provider.error.personOrName.required");
		}
		
		//if this is a retired existing provider, skip this
		//check if this provider has a unique identifier
		boolean isUnique = Context.getProviderService().isProviderIdentifierUnique(provider);
		if (!isUnique) {
			errors.rejectValue("identifier", "Provider.error.duplicateIdentifier",
			    new Object[] { provider.getIdentifier() }, null);
		}
		
		if (provider.isRetired() && StringUtils.isEmpty(provider.getRetireReason())) {
			errors.rejectValue("retireReason", "Provider.error.retireReason.required");
		}
		
		super.validateAttributes(provider, errors, Context.getProviderService().getAllProviderAttributeTypes());
	}