@Test
	public void findErrorLocatorBM_known() {
		GrowQueue_I8 message = GrowQueue_I8.parseHex(
				"[ 0x40, 0xd2, 0x75, 0x47, 0x76, 0x17, 0x32, 0x06, 0x27, 0x26, 0x96, 0xc6, 0xc6, 0x96, 0x70, 0xec ]");
		GrowQueue_I8 ecc = new GrowQueue_I8();
		int nsyn = 10;
		int syndromes[] = new int[nsyn];

		ReidSolomonCodes alg = new ReidSolomonCodes(8,primitive8);
		alg.generator(nsyn);
		alg.computeECC(message,ecc);

		message.data[0] = 0;
		alg.computeSyndromes(message,ecc,syndromes);
		GrowQueue_I8 errorLocator = new GrowQueue_I8();
		alg.findErrorLocatorBM(syndromes,nsyn,errorLocator);
		assertEquals(2,errorLocator.size);
		assertEquals(3,errorLocator.get(0));
		assertEquals(1,errorLocator.get(1));

		message.data[6] = 10;
		alg.computeSyndromes(message,ecc,syndromes);
		alg.findErrorLocatorBM(syndromes,nsyn,errorLocator);
		assertEquals(3,errorLocator.size);
		assertEquals(238,errorLocator.get(0)&0xFF);
		assertEquals(89,errorLocator.get(1));
		assertEquals(1,errorLocator.get(2));
	}