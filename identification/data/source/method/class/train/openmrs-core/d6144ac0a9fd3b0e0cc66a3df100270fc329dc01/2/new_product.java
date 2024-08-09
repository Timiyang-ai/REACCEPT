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
				return getValueNumeric() == null ? "" : getValueNumeric().toString();
			else if (abbrev.equals("DT"))
				return (getValueDatetime() == null ? "" : Format.format(getValueDatetime(), locale, FORMAT_TYPE.DATE));
			else if (abbrev.equals("TM") )
				return (getValueDatetime() == null ? "" : Format.format(getValueDatetime(), locale, FORMAT_TYPE.TIME));
			else if (abbrev.equals("TS"))
				return (getValueDatetime() == null ? "" : Format.format(getValueDatetime(), locale, FORMAT_TYPE.TIMESTAMP));
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
			return Format.format(getValueDatetime(), locale, FORMAT_TYPE.DATE);
		else if (getValueText() != null)
			return getValueText();
		
		return "";
	}