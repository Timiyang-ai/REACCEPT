public void setValueBoolean(Boolean valueBoolean) {
		if (valueBoolean != null && getConcept() != null && getConcept().getDatatype().isBoolean())
			setValueCoded(valueBoolean.booleanValue() ? Context.getConceptService().getTrueConcept() : Context
			        .getConceptService().getFalseConcept());
		else if (valueBoolean == null)
			setValueCoded(null);
	}