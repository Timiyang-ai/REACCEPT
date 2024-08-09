@Override
	public <S extends T> List<S> findAll(Example<S> example) {
		return getQuery(new ExampleSpecification<S>(example), getResultType(example), (Sort) null).getResultList();
	}