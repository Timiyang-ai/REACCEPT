public void setValueBoolean(Boolean valueBoolean) {
		if (getConcept() != null && getConcept().getDatatype() != null && getConcept().getDatatype().isBoolean()) {
			if (valueBoolean != null) {
				setValueCoded(valueBoolean ? Context.getConceptService().getTrueConcept() : Context
				        .getConceptService().getFalseConcept());
			} else {
				setValueCoded(null);
			}
		}
	}