@Test
	public void correctErrors_random() {
		GrowQueue_I8 ecc = new GrowQueue_I8();
		GrowQueue_I8 syndromes = new GrowQueue_I8();
		GrowQueue_I8 errorLocator = new GrowQueue_I8();
		GrowQueue_I32 locations = new GrowQueue_I32();
		int nsyn = 10;

		int locationsFailed = 0;
		for (int i = 0; i < 2000; i++) {
			GrowQueue_I8 message = randomMessage(100);
			GrowQueue_I8 corrupted = message.copy();

			// apply noise to the message
			int numErrors = rand.nextInt(6);

			for (int j = 0; j < numErrors; j++) {
				int selected = rand.nextInt(100);
				corrupted.data[selected] ^= (0x12+j); // make sure it changes even if the same number is selected twice
			}

			ReidSolomonCodes alg = new ReidSolomonCodes(8,primitive8);
			alg.generator(nsyn);
			alg.computeECC(message,ecc);

			// corrupt the ecc code
			if( numErrors != 5 && rand.nextInt(5) < 1 ) {
				ecc.data[rand.nextInt(ecc.size)] ^= 0x13;
			}

			int N = message.size+ecc.size;

			alg.computeSyndromes(corrupted,ecc,syndromes);
			alg.findErrorLocatorPolynomialBM(syndromes,errorLocator);
			if( !alg.findErrorLocations_BruteForce(errorLocator,N,locations)) {
				locationsFailed++;
				continue;
			}

			alg.correctErrors(corrupted,N,syndromes,errorLocator,locations);

			assertEquals(corrupted.size,message.size);
			for (int j = 0; j < corrupted.size; j++) {
				assertEquals(corrupted.get(j),message.get(j));
			}
		}
		// I'm not sure if it should be ever failing...
		if( locationsFailed > 10 )
			fail("Too many errors");
	}