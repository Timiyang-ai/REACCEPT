@Test
	public void correctErrors_hand() {
		GrowQueue_I8 message = GrowQueue_I8.parseHex(
				"[ 0x40, 0xd2, 0x75, 0x47, 0x76, 0x17, 0x32, 0x06, 0x27, 0x26, 0x96, 0xc6, 0xc6, 0x96, 0x70, 0xec ]");
		GrowQueue_I8 ecc = new GrowQueue_I8();
		GrowQueue_I8 syndromes = new GrowQueue_I8();
		GrowQueue_I8 errorLocator = new GrowQueue_I8();
		int nsyn = 10;

		ReidSolomonCodes alg = new ReidSolomonCodes(8,primitive8);
		alg.generator(nsyn);
		alg.computeECC(message,ecc);

		GrowQueue_I8 corrupted = message.copy();
		corrupted.data[0] = 0;
		corrupted.data[4] = 8;
		corrupted.data[5] = 9;
		alg.computeSyndromes(corrupted,ecc,syndromes);
		alg.findErrorLocatorPolynomialBM(syndromes,errorLocator);

		GrowQueue_I32 errorLocations = new GrowQueue_I32(3);
		errorLocations.data[0] = 0;
		errorLocations.data[1] = 4;
		errorLocations.data[2] = 5;
		errorLocations.size = 3;

		alg.correctErrors(corrupted,message.size+ecc.size,syndromes,errorLocator,errorLocations);

		assertEquals(corrupted.size,message.size);
		for (int j = 0; j < corrupted.size; j++) {
			assertEquals(corrupted.get(j),message.get(j));
		}
	}