@Test
	public void correctErrors_random() {
		GrowQueue_I8 ecc = new GrowQueue_I8();
		GrowQueue_I8 syndromes = new GrowQueue_I8();
		GrowQueue_I8 errorLocator = new GrowQueue_I8();
		GrowQueue_I32 locations = new GrowQueue_I32();
		int nsyn = 10; // should be able to recover from 4 errors

		ReidSolomonCodes alg = new ReidSolomonCodes(8,primitive8);
		alg.generator(nsyn);

		for (int i = 0; i < 20000; i++) {
			GrowQueue_I8 message = randomMessage(10);
			GrowQueue_I8 corrupted = message.copy();

			alg.computeECC(message,ecc);

			// apply noise to the message
			int numErrors = rand.nextInt(6);
			int eccErrors = 0;

			for (int j = 0; j < numErrors; j++) {
				int selected = rand.nextInt(message.size);
				corrupted.data[selected] ^= (0x12+j); // make sure it changes even if the same number is selected twice
			}

			// corrupt the ecc code
			if( numErrors < 5 && rand.nextInt(5) < 1 ) {
				numErrors++;
				ecc.data[rand.nextInt(ecc.size)] ^= 0x13;
			}

			int N = message.size+ecc.size;

			alg.computeSyndromes(corrupted,ecc,syndromes);
			alg.findErrorLocatorPolynomialBM(syndromes,errorLocator);
			if( !alg.findErrorLocations_BruteForce(errorLocator,N,locations)) {
				message.printHex();
				System.out.println();
				corrupted.printHex();
				fail("can't determine error locations. "+numErrors+" "+eccErrors);
			}

			alg.correctErrors(corrupted,N,syndromes,errorLocator,locations);

			assertEquals(corrupted.size,message.size);
			for (int j = 0; j < corrupted.size; j++) {
				assertEquals(corrupted.get(j),message.get(j));
			}
		}
	}