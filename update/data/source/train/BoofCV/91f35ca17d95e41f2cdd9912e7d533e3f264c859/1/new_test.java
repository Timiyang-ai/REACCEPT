@Test
	public void process_connected() {
		GeneratePairwiseImageGraph alg = new GeneratePairwiseImageGraph();

		MockLookupSimilarImages similar = new MockLookupSimilarImages(4,123123);
		alg.process(similar);

		PairwiseImageGraph2 graph = alg.getGraph();
		assertEquals(4,graph.nodes.size);
		assertEquals(4,graph.mapNodes.size());
		for (int i = 0; i < graph.nodes.size; i++) {
			PairwiseImageGraph2.View v = graph.nodes.get(i);
			assertTrue(v.totalFeatures>50);
			assertEquals(3,v.connections.size);
			assertNotNull(graph.mapNodes.get(v.id));
		}
		assertEquals(6,graph.edges.size);

		for (int i = 0; i < graph.edges.size; i++) {
			PairwiseImageGraph2.Motion a = alg.graph.edges.get(i);
			assertTrue(a.is3D);

			// each edge pair should be unique
			for (int j = i+1; j < graph.edges.size; j++) {
				PairwiseImageGraph2.Motion b = alg.graph.edges.get(j);

				if( a.src.id.equals(b.src.id) && a.dst.id.equals(b.dst.id))
					fail("duplicate1! "+a.src.id+" "+a.dst.id);
				if( a.dst.id.equals(b.src.id) && a.src.id.equals(b.dst.id))
					fail("duplicate2! "+a.src.id+" "+a.dst.id);
			}
		}
	}