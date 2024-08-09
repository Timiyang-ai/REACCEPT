public List<ConceptName> getCompatibleNames(Locale desiredLocale) {
		// lazy create the cache
		List<ConceptName> compatibleNames = null;
		if (compatibleCache == null) {
			compatibleCache = new HashMap<Locale, List<ConceptName>>();
		} else {
			compatibleNames = compatibleCache.get(desiredLocale);
		}
		
		if (compatibleNames == null) {
			compatibleNames = new Vector<ConceptName>();
			for (ConceptName possibleName : names) {
				if (!possibleName.isVoided() && LocaleUtility.areCompatible(possibleName.getLocale(), desiredLocale)) {
					compatibleNames.add(possibleName);
				}
			}
			compatibleCache.put(desiredLocale, compatibleNames);
		}
		return compatibleNames;
	}