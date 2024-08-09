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
		return result;
	}