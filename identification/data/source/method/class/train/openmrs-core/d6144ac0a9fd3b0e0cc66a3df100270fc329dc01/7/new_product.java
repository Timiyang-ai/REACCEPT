public String getValueAsString(Locale locale) {
		//branch on hl7 abbreviations
		if (getConcept() != null) {
			String abbrev = getConcept().getDatatype().getHl7Abbreviation();
			if (abbrev.equals("BIT"))
				return getValueAsBoolean() == null ? "" : getValueAsBoolean().toString();
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
		else if (hasGroupMembers()) {
			// all of the values are null and we're an obs group...so loop
			// over the members and just do a getValueAsString on those
			// this could potentially cause an infinite loop if an obs group
			// is a member of its own group at some point in the hierarchy
			StringBuilder sb = new StringBuilder();
			for (Obs groupMember : getGroupMembers()) {
				if (sb.length() > 0)
					sb.append(", ");
				sb.append(groupMember.getValueAsString(locale));
			}
			return sb.toString();
		}
		
		return "";
	}