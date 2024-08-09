public boolean isSameAllergen(Allergen allergen) {
		if (isCoded()) {
			if (allergen.getCodedAllergen() == null) {
				return false;
			}
			if (!codedAllergen.equals(allergen.getCodedAllergen())) {
				return false;
			}
		}
		else {
			if (nonCodedAllergen == null || allergen.getNonCodedAllergen() == null) {
				return false;
			}
			if (!nonCodedAllergen.equalsIgnoreCase(allergen.getNonCodedAllergen())) {
				return false;
			}
		}
		
		return true;
	}