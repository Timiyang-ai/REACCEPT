	static
	private Boolean evaluate(Predicate predicate, Object... objects){
		Map<FieldName, ?> arguments = ModelEvaluatorTest.createArguments(objects);

		return evaluate(predicate, arguments);
	}