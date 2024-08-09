@Test
	public void selectMatchSet() {
		double modelVal = 50;

		List<Integer> dataSet = new ArrayList<Integer>();

		for (int i = 0; i < 200; i++) {
			dataSet.add(i);
		}

		DebugModelStuff stuff = new DebugModelStuff((int) modelVal);
		SimpleRansacCommon<Integer> ransac = new RandsacDebug(stuff, stuff);
		double param[] = new double[]{modelVal};

		ransac.selectMatchSet(dataSet, 4, 5, param);

		assertTrue(ransac.candidatePoints.size() == 7);
	}