@Test
	public void dropTrack_Recycle() {
		Helper dat = new Helper();

		dat.tracksAll.add(dat.getUnused());
		dat.tracksAll.add(dat.getUnused());
		dat.tracksAll.add(dat.getUnused());
		for (int i = 0; i < dat.tracksAll.size(); i++) {
			dat.sets[0].tracks.add(dat.tracksAll.get(i));
		}

		PointTrack a = dat.tracksAll.get(1);

		assertEquals(0,dat.unused.size());
		assertTrue(dat.dropTrack(a));
		assertEquals(1,dat.unused.size());

		assertEquals(2,dat.tracksAll.size());
	}