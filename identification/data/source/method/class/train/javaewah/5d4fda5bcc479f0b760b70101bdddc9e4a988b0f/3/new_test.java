@Test
	public void testXor() {
		System.out.println("testXor ");
		Iterator<EWAHCompressedBitmap[]> i = getCollections(2);
		while (i.hasNext()) {
			EWAHCompressedBitmap[] x = i.next();
			EWAHCompressedBitmap tanswer = x[0].xor(x[1]);
			EWAHCompressedBitmap x1 = IteratorUtil
					.materialize(IteratorAggregation.bufferedxor(
							x[0].getIteratingRLW(), x[1].getIteratingRLW()));
			assertTrue(x1.equals(tanswer));
		}
		System.gc();
	}