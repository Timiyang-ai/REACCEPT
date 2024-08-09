public Collection<ConceptName> getNames(boolean includeVoided) {
		Collection<ConceptName> ret = new HashSet<ConceptName>();
		if (includeVoided){
			if (names != null)
				return names;
			else
				return ret;
		} else {
			if (names != null){
				for (ConceptName cn : names){
					if (!cn.isVoided())
						ret.add(cn);
				}
			}	
			return ret;
		}
	}