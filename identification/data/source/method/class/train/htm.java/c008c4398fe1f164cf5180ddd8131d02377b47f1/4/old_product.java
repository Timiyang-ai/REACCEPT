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
		//TODO: port lines 225-232 of python version here: !SHOULD BE DONE NOW!
		if(ArrayUtils.max(patternNZ) > maxInputIdx) {
			int newMaxInputIdx = Math.max(ArrayUtils.max(patternNZ), maxBucketIdx);
			for (int nSteps : steps.toArray()) {
				double[][] newMatrix = new double[maxBucketIdx+1][newMaxInputIdx+1];
				//Cycle through rows of matrix
				for(int j = 0; j < maxBucketIdx+1; j++) {
					//Cycle through columns of matrix
					for(int i = 0; i < maxInputIdx+1; i++) {
						newMatrix[j][i] = weightMatrix.get(nSteps)[j][i];
					}
				}
				weightMatrix.put(nSteps, newMatrix);
			}
			maxInputIdx = newMaxInputIdx;
		}

		//------------------------------------------------------------------------
		//Inference:
		//For each active bit in the activationPattern, get the classification votes
		if(infer) {
			//TODO: IMPLEMENT INFERENCE LOGIC HERE - LINE 239 IN PYTHON VERSION
		}

		//------------------------------------------------------------------------
		//Learning:
		if(learn && classification.get("bucketIdx") != null) {
			//TODO: IMPLEMENT LEARNING LOGIC HERE - LINES 249-280 IN PYTHON VERSION
			// Get classification info
			int bucketIdx = (int)classification.get("bucketIdx");
			Object actValue = classification.get("actValue");

			// Update maxBucketIndex and augment weight matrix with zero padding
			if(bucketIdx > maxBucketIdx) {
				for(int nSteps : steps.toArray()) {
					// Create larger weight matrix (additional row(s)) to accommodate the new bucket we're seeing
					// Then transfer values from old weight matrix into new one
					double[][] newMatrix = new double[bucketIdx+1][maxInputIdx+1];
					// Cycle through matrix rows
					for(int j = 0; j < bucketIdx; j++) {
						// Cycle through matrix columns
						for(int i = 0; i < maxInputIdx; i++) {
							// Put weights from old matrix into new one
							newMatrix[j][i] = weightMatrix.get(nSteps)[j][i];
						}
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

				//TODO: PORT THE FOLLOWING LINE - PYTHON VERSION LINE 275
				double error = calculateError(classification);
				
			}
		}

		for(Tuple t : patternNZHistory) {
			iteration = (int)t.get(0);
			learnPatternNZ = (int[]) t.get(1);
			if(iteration == learnIteration - nSteps) {
				found = true;
				break;
			}
			iteration++;
		}

		//------------------------------------------------------------------------
		//Verbose Print
		//TODO: IMPLEMENT THE VERBOSE PRINT HERE - LINES 285-295 IN PYTHON VERSION
		return retVal;
    }