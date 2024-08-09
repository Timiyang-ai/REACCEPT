public static Evaluatee2 composeNotNulls(@NonNull final Evaluatee... evaluatees)
	{
		final ImmutableList<Evaluatee> evaluateesFiltered = Stream.of(evaluatees).filter(Predicates.notNull()).collect(ImmutableList.toImmutableList());
		Check.assumeNotEmpty(evaluateesFiltered, "At least one evaluatee shall be not null: {}", (Object)evaluatees);
		
		return new CompositeEvaluatee(evaluateesFiltered);
	}