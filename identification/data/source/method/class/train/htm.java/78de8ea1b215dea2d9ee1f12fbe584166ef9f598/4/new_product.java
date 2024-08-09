public Map<String, Object> compute(int recordNum, Map<String, Object> classification, int[] patternNZ, boolean learn, boolean infer) {
		Map<String, Object> retVal = new LinkedHashMap<String, Object>();
		
		// Save the offset between recordNum and learnIteration if this is the first
	    // compute
		if(recordNumMinusLearnIteration == 0) {
			recordNumMinusLearnIteration = recordNum - learnIteration;
		}
		
		// Update the learn iteration
		learnIteration = recordNum - recordNumMinusLearnIteration;
		
		if(verbosity >= 1) {
			System.out.println(String.format("\n%s: compute ", g_debugPrefix));
			System.out.println(" recordNum: " + recordNum);
			System.out.println(" learnIteration: " + learnIteration);
			System.out.println(String.format(" patternNZ(%d): ", patternNZ.length, patternNZ));
			System.out.println(" classificationIn: " + classification);
		}
		
		patternNZHistory.append(new Tuple(2, learnIteration, patternNZ));
		
		//------------------------------------------------------------------------
	    // Inference:
	    // For each active bit in the activationPattern, get the classification
	    // votes
		//
		// Return value dict. For buckets which we don't have an actual value
	    // for yet, just plug in any valid actual value. It doesn't matter what
	    // we use because that bucket won't have non-zero likelihood anyways.
		if(infer) {
			// NOTE: If doing 0-step prediction, we shouldn't use any knowledge
		    //		 of the classification input during inference.
			Object defaultValue = null;
			if(steps.get(0) == 0) {
				defaultValue = 0;
			}else{
				defaultValue = classification.get("actValue");
			}
			
			Object[] actValues = new Object[this.actualValues.size()];
			for(int i = 0;i < actualValues.size();i++) {
				actValues[i] = actualValues.get(i) == null ? defaultValue : actualValues.get(i);
			}
			
			retVal.put("actualValues", actValues);
			
			// For each n-step prediction...
			for(int nSteps : steps.toArray()) {
				// Accumulate bucket index votes and actValues into these arrays
				double[] sumVotes = new double[maxBucketIdx + 1];
				double[] bitVotes = new double[maxBucketIdx + 1];
				
				for(int bit : patternNZ) {
					Tuple key = new Tuple(2, bit, nSteps);
					BitHistory history = activeBitHistory.get(key);
					if(history == null) continue;
					
					history.infer(learnIteration, bitVotes);
					
					sumVotes = ArrayUtils.d_add(sumVotes, bitVotes);
				}
				
				// Return the votes for each bucket, normalized
				double total = ArrayUtils.sum(sumVotes);
				if(total > 0) {
					sumVotes = ArrayUtils.divide(sumVotes, total);
				}else{
					// If all buckets have zero probability then simply make all of the
			        // buckets equally likely. There is no actual prediction for this
			        // timestep so any of the possible predictions are just as good.
					if(sumVotes.length > 0) {
						Arrays.fill(sumVotes, 1);
						sumVotes = ArrayUtils.divide(sumVotes, sumVotes.length);
					}
				}
				
				retVal.put("" + nSteps, sumVotes);
			}
		}
		
		// ------------------------------------------------------------------------
	    // Learning:
	    // For each active bit in the activationPattern, store the classification
	    // info. If the bucketIdx is None, we can't learn. This can happen when the
	    // field is missing in a specific record.
		if(learn && classification.get("bucketIdx") != null) {
			// Get classification info
			int bucketIdx = (int)classification.get("bucketIdx");
			Object actValue = classification.get("actValue");
			
			// Update maxBucketIndex
			maxBucketIdx = (int) Math.max(maxBucketIdx, bucketIdx);
			
			// Update rolling average of actual values if it's a scalar. If it's
		    // not, it must be a category, in which case each bucket only ever
		    // sees one category so we don't need a running average.
			while(maxBucketIdx > actualValues.size() - 1) {
				actualValues.add(null);
			}
			if(actualValues.get(bucketIdx) == null) {
				actualValues.set(bucketIdx, actValue);
			}else{
				if(Number.class.isAssignableFrom(actValue.getClass())) {
					actualValues.set(bucketIdx, (1.0 - actValueAlpha) * ((Number)actualValues.get(bucketIdx)).doubleValue() + 
						actValueAlpha * ((Number)actValue).doubleValue());
				}else{
					actualValues.set(bucketIdx, actValue);
				}
			}
			
			// Train each pattern that we have in our history that aligns with the
		    // steps we have in self.steps
			int nSteps = -1;
			int iteration = 0;
			int[] learnPatternNZ = null;
			for(int n : steps.toArray()) {
				nSteps = n;
				// Do we have the pattern that should be assigned to this classification
		        // in our pattern history? If not, skip it
				boolean found = false;
				for(Tuple t : patternNZHistory) {
					learnPatternNZ = (int[]) t.get(1);
					if(iteration == learnIteration - nSteps) {
						found = true;
						break;
					}
					iteration++;
				}
				if(!found) continue;
			}
			
			// Store classification info for each active bit from the pattern
	        // that we got nSteps time steps ago.
			for(int bit : learnPatternNZ) {
				// Get the history structure for this bit and step
				Tuple key = new Tuple(2, bit, nSteps);
				BitHistory history = activeBitHistory.get(key);
				if(history == null) {
					activeBitHistory.put(key, history = new BitHistory(this, bit, nSteps));
				}
				history.store(iteration, bucketIdx);
			}
		}
		
		if(infer && verbosity >= 1) {
			System.out.println(" inference: combined bucket likelihoods:");
			System.out.println("   actual bucket values: " + retVal.get("actualValues"));
			
			for(String key : retVal.keySet()) {
				if(key.equals("actualValues")) continue;
				
				System.out.println(String.format("  %d steps: ", key, pFormatArray((double[])retVal.get(key))));
				int bestBucketIdx = ArrayUtils.argmax((double[])retVal.get(key));
				System.out.println(String.format("   most likely bucket idx: %d, value: %s ", bestBucketIdx, 
					((Object[])retVal.get("actualValues"))[bestBucketIdx]));
				
			}
		}
		
		return retVal;
	}