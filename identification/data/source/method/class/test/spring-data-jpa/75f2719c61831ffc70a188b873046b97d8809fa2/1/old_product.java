@Override
	public List<T> findAllByExample(Example<T> example) {
		return findAll(new ExampleSpecification<T>(example));
	}