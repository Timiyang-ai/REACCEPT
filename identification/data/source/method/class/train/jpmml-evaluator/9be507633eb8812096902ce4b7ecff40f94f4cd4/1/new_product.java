static
	public Boolean evaluate(Predicate predicate, EvaluationContext context){

		if(predicate instanceof SimplePredicate){
			return evaluateSimplePredicate((SimplePredicate)predicate, context);
		} else

		if(predicate instanceof CompoundPredicate){
			return evaluateCompoundPredicate((CompoundPredicate)predicate, context);
		} else

		if(predicate instanceof SimpleSetPredicate){
			return evaluateSimpleSetPredicate((SimpleSetPredicate)predicate, context);
		} else

		if(predicate instanceof True){
			return evaluateTrue((True)predicate);
		} else

		if(predicate instanceof False){
			return evaluateFalse((False)predicate);
		}

		throw new UnsupportedFeatureException(predicate);
	}