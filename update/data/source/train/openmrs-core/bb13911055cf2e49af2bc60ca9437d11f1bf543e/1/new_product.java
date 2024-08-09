@Override
	public List<Orderable<?>> getOrderables(String query) throws APIException {
		
		if (query == null)
			throw new IllegalArgumentException("Orderable concept name is required");
		
		List<Orderable<?>> result = new ArrayList<Orderable<?>>();
		List<Concept> concepts = Context.getConceptService().getConceptsByName(query);
		if (concepts != null) {
			for (Concept concept : concepts) {
				if (concept.getConceptClass().getName().equals("Drug"))
					result.add(new GenericDrug(concept));
			}
		}
		
		// and next to try to find drugs by name
		List<Drug> drugs = Context.getConceptService().getDrugs(query);
		if (drugs != null) {
			for (Drug drug : drugs) {
				if (!drug.isRetired())
					result.add(drug);
			}
		}
		
		return result;
	}