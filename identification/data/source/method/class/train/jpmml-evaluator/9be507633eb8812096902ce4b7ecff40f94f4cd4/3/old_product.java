static
	public Boolean evaluate(Predicate predicate, EvaluationContext context){

		try {
			return evaluatePredicate(predicate, context);
		} catch(PMMLException pe){
			pe.ensureContext(predicate);

			throw pe;
		}
	}