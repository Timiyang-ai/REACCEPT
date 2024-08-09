@SuppressWarnings("unchecked")
	public Crotch or(String fieldname) {
		Criteria node = new Criteria(fieldname);
		node.setPartIsOr(true);
		return or(node);
	}