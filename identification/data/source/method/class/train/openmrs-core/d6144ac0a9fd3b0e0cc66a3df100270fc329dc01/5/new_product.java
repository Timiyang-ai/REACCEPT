public String getValueAsString(Locale locale) {
		//branch on hl7 abbreviations
		if (getConcept() != null) {
			String abbrev = getConcept().getDatatype().getHl7Abbreviation();
			if (abbrev.equals("BIT"))
				return getValueAsBoolean().toString();
			else if (abbrev.equals("CWE")) {
				if (getValueCoded() == null)
					return "";
				if (getValueDrug() != null)
					return getValueDrug().getFullName(locale);
				else
					return getValueCoded().getName(locale).getName();
			}
			else if (abbrev.equals("NM") || abbrev.equals("SN"))
				return getValueNumeric().toString();
			else if (abbrev.equals("DT") || abbrev.equals("TM") || abbrev.equals("TS"))
				return (getValueDatetime() == null ? "" : Format.format(getValueDatetime(), locale));
			else if (abbrev.equals("ST"))
				return getValueText();
		}
		
		// if the datatype is 'unknown', default to just returning what is not null
		if (getValueNumeric() != null)
			return getValueNumeric().toString();
		else if (getValueCoded() != null) {
			if (getValueDrug() != null)
				return getValueDrug().getFullName(locale);
			else
				return getValueCoded().getName(locale).getName();
		}
		else if (getValueDatetime() != null)
			return Format.format(getValueDatetime(), locale);
		else if (getValueText() != null)
			return getValueText();
		
		return "";
	}