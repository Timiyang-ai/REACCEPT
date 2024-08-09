public ConceptName getName(Locale locale, ConceptNameType ofType, ConceptNameTag havingTag) {
		Collection<ConceptName> namesInLocale = getNames(locale);
		if (!namesInLocale.isEmpty()) {
			List<ConceptName> matches = new ArrayList<>();
			
			for (ConceptName candidate : namesInLocale) {
				if ((ofType == null || ofType.equals(candidate.getConceptNameType()))
				        && (havingTag == null || candidate.hasTag(havingTag))) {
					matches.add(candidate);
				}
			}
			
			// if we have any matches, we'll return one of them
			if (matches.size() == 1) {
				return matches.get(0);
			} else if (matches.size() > 1) {
				for (ConceptName match : matches) {
					if (match.getLocalePreferred()) {
						return match;
					}
				}
				// none was explicitly marked as preferred
				return matches.get(0);
			}
		}
		
		// if we reach here, there were no matching names, so try to look in the parent locale
		Locale parent = new Locale(locale.getLanguage());
		if (!parent.equals(locale)) {
			return getName(parent, ofType, havingTag);
		} else {
			return null;
		}
	}