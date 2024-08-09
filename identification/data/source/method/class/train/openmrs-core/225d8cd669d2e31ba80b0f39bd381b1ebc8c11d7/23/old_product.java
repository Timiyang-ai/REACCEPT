public void validate(Object obj, Errors errors) {
		if (!ValidateUtil.isValidationOn()) {
			return;
		}
		
		Obs obs = (Obs) obj;
		List<Obs> ancestors = new ArrayList<Obs>();
		//ancestors.add(obs);
		validateHelper(obs, errors, ancestors, true);
	}