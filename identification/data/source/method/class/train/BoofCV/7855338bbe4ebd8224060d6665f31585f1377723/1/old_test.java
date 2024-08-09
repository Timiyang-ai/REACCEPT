@Test
	public void correctErrors() {
		GrowQueue_I8 message = GrowQueue_I8.parseHex(
				"[ 0x40, 0xd2, 0x75, 0x47, 0x76, 0x17, 0x32, 0x06, 0x27, 0x26, 0x96, 0xc6, 0xc6, 0x96, 0x70, 0xec ]");
		GrowQueue_I8 ecc = new GrowQueue_I8();
		GrowQueue_I8 syndromes = new GrowQueue_I8();
		GrowQueue_I8 errorLocator = new GrowQueue_I8();
		int nsyn = 10;

		ReidSolomonCodes alg = new ReidSolomonCodes(8,primitive8);
		alg.generator(nsyn);
		alg.computeECC(message,ecc);

		message.data[0] = 0;
		message.data[4] = 8;
		message.data[5] = 9;
		alg.computeSyndromes(message,ecc,syndromes);
		alg.findErrorLocatorPolynomialBM(syndromes,errorLocator);

		GrowQueue_I32 errorLocations = new GrowQueue_I32(3);
		errorLocations.data[0] = 0;
		errorLocations.data[1] = 4;
		errorLocations.data[2] = 5;
		errorLocations.size = 3;

		GrowQueue_I8 corrected = new GrowQueue_I8();

		alg.correctErrors(message,message.size+ecc.size,syndromes,errorLocator,errorLocations,corrected);

		message.data[0] = 0x40;
		message.data[4] = 0x76;
		message.data[5] = 0x17;
		assertEquals(corrected.size,message.size);
		for (int j = 0; j < corrected.size; j++) {
			assertEquals(corrected.get(j),message.get(j));
		}
	}