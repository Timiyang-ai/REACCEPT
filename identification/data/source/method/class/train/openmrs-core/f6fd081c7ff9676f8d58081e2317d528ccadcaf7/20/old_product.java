public Form getForm(Integer formId) throws APIException {
		return context.getDAOContext().getFormDAO().getForm(formId);
	}