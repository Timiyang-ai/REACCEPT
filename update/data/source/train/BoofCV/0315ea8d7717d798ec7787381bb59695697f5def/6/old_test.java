@Test
	public void various() {

		Dummy<Integer> nn = new Dummy<Integer>();
		// src = assoc[i] where src is the index of the source feature and i is the index of the dst feature
		nn.assoc = new int[]{2,0,1,-1,4,-1,-1,2,2,1};

		AssociateNearestNeighbor<TupleDesc_F64> alg = new AssociateNearestNeighbor<TupleDesc_F64>(nn,10);

		FastQueue<TupleDesc_F64> src = new FastQueue<TupleDesc_F64>(10,TupleDesc_F64.class,false);
		FastQueue<TupleDesc_F64> dst = new FastQueue<TupleDesc_F64>(10,TupleDesc_F64.class,false);

		for( int i = 0; i < 5; i++ ) {
			src.add( new TupleDesc_F64(10));
		}

		for( int i = 0; i < 10; i++ ) {
			dst.add( new TupleDesc_F64(10));
		}

		alg.setSource(src);
		alg.setDestination(dst);

		alg.associate();

		FastQueue<AssociatedIndex> matches = alg.getMatches();
		assertTrue(nn.pointDimension == 10);

		assertEquals(7,matches.size);
		for( int i = 0, count = 0; i < nn.assoc.length; i++ ) {
			if( nn.assoc[i] != -1 ) {
				int source = nn.assoc[i];
				assertEquals(source,matches.get(count).src);
				assertEquals(i,matches.get(count).dst);
				count++;
			}
		}

		GrowQueue_I32 unassoc = alg.getUnassociatedSource();
		assertEquals(1, unassoc.size);
		assertEquals(3,unassoc.get(0));
		unassoc = alg.getUnassociatedDestination();
		assertEquals(3, unassoc.size);
		assertEquals(3,unassoc.get(0));
		assertEquals(5,unassoc.get(1));
		assertEquals(6,unassoc.get(2));
	}