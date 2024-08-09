@Override
	public void validate(Object target, Errors errors) {
		Visit visit = (Visit) target;
		for (VisitAttributeType vat : Context.getVisitService().getAllVisitAttributeTypes()) {
			if (vat.getMinOccurs() > 0 || vat.getMaxOccurs() != null) {
				int numFound = 0;
				for (VisitAttribute attr : visit.getActiveAttributes()) {
					if (attr.getAttributeType().equals(vat))
						++numFound;
				}
				if (vat.getMinOccurs() > 0) {
					if (numFound < vat.getMinOccurs()) {
						// report an error
						if (vat.getMinOccurs() == 1)
							errors.rejectValue("activeAttributes", "error.required", new Object[] { vat.getName() }, null);
						else
							errors.rejectValue("activeAttributes", "attribute.error.minOccurs", new Object[] {
							        vat.getName(), vat.getMinOccurs() }, null);
					}
				}
				if (vat.getMaxOccurs() != null) {
					if (numFound > vat.getMaxOccurs()) {
						errors.rejectValue("activeAttributes", "attribute.error.maxOccurs", new Object[] { vat.getName(),
						        vat.getMaxOccurs() }, null);
					}
				}
			}
		}
	}