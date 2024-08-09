public PactModule asPactModule(EvaluationContext context) {
		return PactModule.valueOf(this.getName(), this.assemblePact(context));
	}