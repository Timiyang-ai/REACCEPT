static
	public Boolean evaluate(Predicate predicate, EvaluationContext context){

		try {
			return evaluatePredicate(predicate, context);
		} catch(PMMLException pe){
			throw pe.ensureContext(predicate);
		}
	}