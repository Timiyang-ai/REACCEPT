@Test
	public void spawnGrid() {
		TldRegionTracker alg = createAlg();

		alg.initialize(pyramid);
		alg.spawnGrid(new Rectangle2D_F64(10,20,80,100));

		TldRegionTracker.Track[] tracks = alg.getTracks();

		assertEquals(10 * 10, tracks.length);

		for( int i = 0; i < tracks.length; i++ ) {
			float x = tracks[i].klt.x;
			float y = tracks[i].klt.y;

			assertTrue(x >= 10 && x <= 80);
			assertTrue(y >= 20 && y <= 100);

			assertTrue(tracks[i].active);
		}
	}