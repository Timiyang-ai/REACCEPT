void findErrorEvaluator( GrowQueue_I8 syndromes , GrowQueue_I8 errorLocator ,
							 GrowQueue_I8 evaluator )
	{
		math.polyMult_flipA(syndromes,errorLocator,evaluator);
		int offset = evaluator.size-errorLocator.size;
		for (int i = 0; i < errorLocator.size; i++) {
			evaluator.data[i] = evaluator.data[i+offset];
		}
		evaluator.size = errorLocator.size;
	}