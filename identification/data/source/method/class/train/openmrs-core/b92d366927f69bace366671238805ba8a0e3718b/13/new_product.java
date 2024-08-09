public ConceptName getName() {
		if (getNames().isEmpty()) {
			if (log.isDebugEnabled()) {
				log.debug("there are no names defined for: " + conceptId);
			}
			return null;
		}
		
		for (Locale currentLocale : LocaleUtility.getLocalesInOrder()) {
			ConceptName preferredName = getPreferredName(currentLocale);
			if (preferredName != null) {
				return preferredName;
			}
			
			ConceptName fullySpecifiedName = getFullySpecifiedName(currentLocale);
			if (fullySpecifiedName != null) {
				return fullySpecifiedName;
			}
			
			//if the locale has an variants e.g en_GB, try names in the locale excluding the country code i.e en
			if (!StringUtils.isBlank(currentLocale.getCountry()) || !StringUtils.isBlank(currentLocale.getVariant())) {
				Locale broaderLocale = new Locale(currentLocale.getLanguage());
				ConceptName prefNameInBroaderLoc = getPreferredName(broaderLocale);
				if (prefNameInBroaderLoc != null) {
					return prefNameInBroaderLoc;
				}
				
				ConceptName fullySpecNameInBroaderLoc = getFullySpecifiedName(broaderLocale);
				if (fullySpecNameInBroaderLoc != null) {
					return fullySpecNameInBroaderLoc;
				}
			}
		}
		
		for (ConceptName cn : getNames()) {
			if (cn.isFullySpecifiedName()) {
				return cn;
			}
		}
		
		if (!getSynonyms().isEmpty()) {
			return getSynonyms().iterator().next();
		}
		
		//we don't expect to get here since every concept name must have atleast
		//one fully specified name, but just in case(probably inconsistent data)
		
		return null;
	}