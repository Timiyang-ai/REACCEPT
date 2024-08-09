@SuppressWarnings("unchecked")
	@Override
	public Crotch and(Node node) {

		if (!(node instanceof Criteria)) {
			throw new IllegalArgumentException("Can only add instances of Criteria");
		}

		Crotch crotch = new Crotch();
		crotch.setParent(this.getParent());
		crotch.add(this);
		crotch.add((Criteria) node);
		return crotch;
	}