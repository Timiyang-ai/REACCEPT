public boolean isSameAllergen(Allergen allergen) {
		if (isCoded()) {
			return allergen.getCodedAllergen() != null && codedAllergen.equals(allergen.getCodedAllergen());
		}  else {
			return nonCodedAllergen != null && allergen.getNonCodedAllergen() != null && nonCodedAllergen
					.equalsIgnoreCase(allergen.getNonCodedAllergen());
		}

	}