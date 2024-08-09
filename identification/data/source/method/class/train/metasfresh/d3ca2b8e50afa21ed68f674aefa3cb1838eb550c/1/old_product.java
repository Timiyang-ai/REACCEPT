public static final Evaluatee2 composeNotNulls(final Evaluatee... evaluatees)
	{
		Check.assumeNotEmpty(evaluatees, "evaluatees not empty");

		CompositeEvaluatee composite = null;
		for (final Evaluatee evaluatee : evaluatees)
		{
			if (evaluatee == null)
			{
				continue;
			}

			if (composite == null)
			{
				composite = new CompositeEvaluatee(evaluatee);
			}
			else
			{
				composite.addEvaluatee(evaluatee);
			}
		}

		Check.assumeNotNull(composite, "At least one evaluatee shall be not null: {}", (Object)evaluatees);
		return composite;
	}