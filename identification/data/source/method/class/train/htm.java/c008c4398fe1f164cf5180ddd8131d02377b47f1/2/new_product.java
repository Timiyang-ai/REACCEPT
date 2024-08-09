@SuppressWarnings("unchecked")
    public <T> Classification<T> compute(int recordNum, Map<String, Object> classification, int[] patternNZ, boolean learn, boolean infer) {
        Classification<T> retVal = new Classification<T>();
        List<T> actualValues = (List<T>)this.actualValues;

		//Save the offset between recordNum and learnIteration if this is the first compute
		if(recordNumMinusLearnIteration == -1)
			recordNumMinusLearnIteration = recordNum - learnIteration;

		//Update the learn iteration
		learnIteration = recordNum - recordNumMinusLearnIteration;

		//Verbose print
		if(verbosity >= 1) {
			System.out.printf("recordNum: %d", recordNum);
			System.out.printf("learnIteration: %d", learnIteration);
			System.out.printf("patternNZ (%d): %d", patternNZ.length, patternNZ);
			System.out.println("classificationIn: " + classification);
		}

		//Store pattern in our history
		patternNZHistory.append(new Tuple(learnIteration, patternNZ));

		//Update maxInputIdx and augment weight matrix with zero padding
		if(ArrayUtils.max(patternNZ) > maxInputIdx) {
			int newMaxInputIdx = Math.max(ArrayUtils.max(patternNZ), maxBucketIdx);
			for (int nSteps : steps.toArray()) {
				for(int i = maxBucketIdx; i < ArrayUtils.max(patternNZ); i++) {
					weightMatrix.get(nSteps).addCol(new double[maxBucketIdx + 1]);
				}
			}
			maxInputIdx = newMaxInputIdx;
		}

		//------------------------------------------------------------------------
		//Inference:
		//For each active bit in the activationPattern, get the classification votes
		if(infer) {
			retVal = infer(patternNZ, classification);
		}

		//------------------------------------------------------------------------
		//Learning:
		if(learn && classification.get("bucketIdx") != null) {
			// Get classification info
			int bucketIdx = (int)classification.get("bucketIdx");
			Object actValue = classification.get("actValue");

			// Update maxBucketIndex and augment weight matrix with zero padding
			if(bucketIdx > maxBucketIdx) {
				for(int nSteps : steps.toArray()) {
					for(int i = maxBucketIdx; i < bucketIdx; i++) {
						weightMatrix.get(nSteps).addRow(new double[maxBucketIdx + 1]);
					}
				}
				maxBucketIdx = bucketIdx;
			}


			// Update rolling average of actual values if it's a scalar. If it's not, it
			// must be a category, in which case each bucket only ever sees on category so
			// we don't need a running average.
			while(maxBucketIdx > actualValues.size() - 1) {
				actualValues.add(null);
			}
			if(actualValues.get(bucketIdx) == null) {
				actualValues.set(bucketIdx, (T)actValue);
			}
			else {
				if(Number.class.isAssignableFrom(actValue.getClass())) {
					Double val = ((1.0 - actValueAlpha) * ((Number)actualValues.get(bucketIdx)).doubleValue() +
							actValueAlpha * ((Number)actValue).doubleValue());
					actualValues.set(bucketIdx, (T)val);
				}else{
					actualValues.set(bucketIdx, (T)actValue);
				}
			}

			int iteration = 0;
			int[] learnPatternNZ = null;
			for(Tuple t : patternNZHistory) {
				iteration = (int)t.get(0);
				learnPatternNZ = (int[])t.get(1);

				Map<Integer, double[]> error = calculateError(classification);

				int nSteps = learnIteration - iteration;
				if(steps.contains(nSteps)) {
					for(int row = 0; row <= maxBucketIdx; row++) {
						for (int bit : learnPatternNZ) {
							weightMatrix.get(nSteps).add(row, bit, alpha * error.get(nSteps)[row]);
						}
					}
				}
			}
		}

		//------------------------------------------------------------------------
		//Verbose Print
		if(infer && verbosity >= 1) {
			System.out.println(" inference: combined bucket likelihoods:");
			System.out.println("   actual bucket values: " + Arrays.toString((T[])retVal.getActualValues()));

			for(int key : retVal.stepSet()) {
				if(retVal.getActualValue(key) == null) continue;

				Object[] actual = new Object[] { (T)retVal.getActualValue(key) };
				System.out.println(String.format("  %d steps: ", key, pFormatArray(actual)));
				int bestBucketIdx = retVal.getMostProbableBucketIndex(key);
				System.out.println(String.format("   most likely bucket idx: %d, value: %s ", bestBucketIdx,
						retVal.getActualValue(bestBucketIdx)));

			}
		}

		return retVal;
    }