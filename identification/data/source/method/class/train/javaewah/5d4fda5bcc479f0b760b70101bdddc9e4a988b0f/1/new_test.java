@Test
	public void testOr() {
		for (int N = 1; N < 10; ++N) {
			System.out.println("testOr N = " + N);
			Iterator<EWAHCompressedBitmap32[]> i = getCollections(N);
			while (i.hasNext()) {
				EWAHCompressedBitmap32[] x = i.next();
				EWAHCompressedBitmap32 tanswer = EWAHCompressedBitmap32.or(x);
				EWAHCompressedBitmap32 x1 = IteratorUtil32
						.materialize(IteratorAggregation32.bufferedor(IteratorUtil32
								.toIterators(x)));
				assertTrue(x1.equals(tanswer));
			}
			System.gc();
		}
	}