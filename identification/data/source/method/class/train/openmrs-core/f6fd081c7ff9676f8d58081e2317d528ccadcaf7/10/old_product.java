public List<Form> getForms() throws APIException {
		return context.getDAOContext().getFormDAO().getForms();
	}