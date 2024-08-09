@Test
	public void dropTrack_Recycle() {
		Helper dat = new Helper();

		dat.tracksAll.add(dat.getUnused());
		dat.tracksAll.add(dat.getUnused());
		dat.tracksAll.add(dat.getUnused());

		PointTrack a = dat.tracksAll.get(1);

		assertEquals(0,dat.unused.size());
		dat.dropTrack(a);
		assertEquals(1,dat.unused.size());

		assertEquals(2,dat.tracksAll.size());
	}