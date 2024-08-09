@Test
	void prunePoints_neighbors() {
		createPerfectScene();
		int countPoints0 = structure.points.size;
		int countObservations0 = observations.getObservationCount();

		PruneStructureFromSceneMetric alg = new PruneStructureFromSceneMetric(structure,observations);

		// This should just prune the outliers far from the center
		alg.prunePoints(2,0.5);

		int countPoints1 = structure.points.size;
		int countObservations1 = observations.getObservationCount();
		assertTrue(countPoints0>countPoints1 && countPoints1>0.95*countPoints0);
		assertTrue(countObservations0>countObservations1 && countObservations1>0.95*countObservations0);

		// If run a second time it should have very similar results
		alg.prunePoints(2,0.5);
		assertEquals(countPoints1, structure.points.size,5);
		assertEquals(countObservations1, observations.getObservationCount(),countObservations1*0.005);

		// sanity check the modifications
		checkAllObservationsArePerfect();
	}