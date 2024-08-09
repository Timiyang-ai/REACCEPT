public PactModule asPactModule(final EvaluationContext context) {
		return PactModule.valueOf(this.getName(), this.assemblePact(context));
	}