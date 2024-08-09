@Test
	public void findErrorLocatorPolynomialBM_compareToDirect() {

		GrowQueue_I8 found = new GrowQueue_I8();
		GrowQueue_I8 expected = new GrowQueue_I8();

		for (int i = 0; i < 30; i++) {
			int N = 50;
			GrowQueue_I8 message = randomMessage(N);

			GrowQueue_I8 ecc = new GrowQueue_I8();
			int nsyn = 10;
			int syndromes[] = new int[nsyn];

			ReidSolomonCodes alg = new ReidSolomonCodes(8,primitive8);
			alg.generator(nsyn);
			alg.computeECC(message,ecc);

			int where = rand.nextInt(N);
			message.data[where] ^= 0x12;
			alg.computeSyndromes(message,ecc,syndromes);

			GrowQueue_I32 whereList = new GrowQueue_I32(1);
			whereList.add(where);

			alg.findErrorLocatorPolynomialBM(syndromes,nsyn,found);
			alg.findErrorLocatorPolynomial(N+ecc.size,whereList,expected);

			assertEquals(found.size,expected.size);
			for (int j = 0; j < found.size; j++) {
				assertEquals(found.get(j),expected.get(j));
			}
		}
	}