public Concept getConceptByName(String name) {
		List<Concept> concepts =  getConcepts(name, Context.getLocale(), false, null, null);
		int size = concepts.size(); 
		if (size > 0){
			if (size > 1)
				log.warn("Multiple concepts found for '" + name + "'");
			return concepts.get(0);
		}	
		return null;
	}