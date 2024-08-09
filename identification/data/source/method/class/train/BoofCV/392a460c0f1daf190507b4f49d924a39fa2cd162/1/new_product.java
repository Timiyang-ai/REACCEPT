void findErrorEvaluator( GrowQueue_I8 syndromes , GrowQueue_I8 errorLocator ,
							 GrowQueue_I8 evaluator )
	{
		math.polyMult_flipA(syndromes,errorLocator,evaluator);
		int N = errorLocator.size-1;
		int offset = evaluator.size-N;
		for (int i = 0; i < N; i++) {
			evaluator.data[i] = evaluator.data[i+offset];
		}
		evaluator.data[N]=0;
		evaluator.size = errorLocator.size;

		// flip evaluator around // TODO remove this flip and do it in place
		for (int i = 0; i < evaluator.size / 2; i++) {
			int j = evaluator.size-i-1;
			int tmp = evaluator.data[i];
			evaluator.data[i] = evaluator.data[j];
			evaluator.data[j] = (byte)tmp;
		}
	}