public String getValueAsString(Locale locale) {
		// formatting for the return of numbers of type double
		NumberFormat nf = NumberFormat.getNumberInstance(locale);
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern("#0.0#####"); // formatting style up to 6 digits
		//branch on hl7 abbreviations
		if (getConcept() != null) {
			String abbrev = getConcept().getDatatype().getHl7Abbreviation();
			if ("BIT".equals(abbrev)) {
				return getValueAsBoolean() == null ? "" : getValueAsBoolean().toString();
			} else if ("CWE".equals(abbrev)) {
				if (getValueCoded() == null) {
					return "";
				}
				if (getValueDrug() != null) {
					return getValueDrug().getFullName(locale);
				} else {
					ConceptName valueCodedName = getValueCodedName();
					if (valueCodedName != null) {
						return getValueCoded().getName(locale, false).getName();
					} else {
						ConceptName fallbackName = getValueCoded().getName();
						if (fallbackName != null) {
							return fallbackName.getName();
						} else {
							return "";
						}
						
					}
				}
			} else if ("NM".equals(abbrev) || "SN".equals(abbrev)) {
				if (getValueNumeric() == null) {
					return "";
				} else {
					if (getConcept() instanceof ConceptNumeric) {
						ConceptNumeric cn = (ConceptNumeric) getConcept();
						if (!cn.getAllowDecimal()) {
							double d = getValueNumeric();
							int i = (int) d;
							return Integer.toString(i);
						} else {
							df.format(getValueNumeric());
						}
					}
				}
			} else if ("DT".equals(abbrev)) {
				DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
				return (getValueDatetime() == null ? "" : dateFormat.format(getValueDatetime()));
			} else if ("TM".equals(abbrev)) {
				return (getValueDatetime() == null ? "" : Format.format(getValueDatetime(), locale, FORMAT_TYPE.TIME));
			} else if ("TS".equals(abbrev)) {
				return (getValueDatetime() == null ? "" : Format.format(getValueDatetime(), locale, FORMAT_TYPE.TIMESTAMP));
			} else if ("ST".equals(abbrev)) {
				return getValueText();
			} else if ("ED".equals(abbrev) && getValueComplex() != null) {
				String[] valueComplex = getValueComplex().split("\\|");
				for (String aValueComplex : valueComplex) {
					if (StringUtils.isNotEmpty(aValueComplex)) {
						return aValueComplex.trim();
					}
				}
			}
		}
		
		// if the datatype is 'unknown', default to just returning what is not null
		if (getValueNumeric() != null) {
			return df.format(getValueNumeric());
		} else if (getValueCoded() != null) {
			if (getValueDrug() != null) {
				return getValueDrug().getFullName(locale);
			} else {
				ConceptName valudeCodedName = getValueCodedName();
				if (valudeCodedName != null) {
					return valudeCodedName.getName();
				} else {
					return "";
				}
			}
		} else if (getValueDatetime() != null) {
			return Format.format(getValueDatetime(), locale, FORMAT_TYPE.DATE);
		} else if (getValueText() != null) {
			return getValueText();
		} else if (hasGroupMembers()) {
			// all of the values are null and we're an obs group...so loop
			// over the members and just do a getValueAsString on those
			// this could potentially cause an infinite loop if an obs group
			// is a member of its own group at some point in the hierarchy
			StringBuilder sb = new StringBuilder();
			for (Obs groupMember : getGroupMembers()) {
				if (sb.length() > 0) {
					sb.append(", ");
				}
				sb.append(groupMember.getValueAsString(locale));
			}
			return sb.toString();
		}
		
		// returns the title portion of the valueComplex
		// which is everything before the first bar '|' character.
		if (getValueComplex() != null) {
			String[] valueComplex = getValueComplex().split("\\|");
			for (String aValueComplex : valueComplex) {
				if (StringUtils.isNotEmpty(aValueComplex)) {
					return aValueComplex.trim();
				}
			}
		}
		
		return "";
	}