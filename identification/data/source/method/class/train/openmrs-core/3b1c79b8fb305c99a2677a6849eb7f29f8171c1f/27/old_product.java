@Transactional(readOnly = true)
	public List<Drug> getDrugsByIngredient(Concept ingredient);