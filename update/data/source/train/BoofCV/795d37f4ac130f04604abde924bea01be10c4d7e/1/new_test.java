@Test
	public void copy() {
		List<QuadBlob> all = new ArrayList<QuadBlob>();
		all.add(createSquare(50,60,10,12));
		all.add(createSquare(51,61,11,13));
		all.add(createSquare(52, 62, 12, 14));
		all.add(createSquare(53, 63, 13, 15));
		connect(0,1,all);
		connect(0,2,all);
		connect(0,3,all);
		connect(1,3,all);
		connect(2,3,all);

		List<QuadBlob> sub = new ArrayList<QuadBlob>();
		sub.add( all.get(0));
		sub.add( all.get(1));
		sub.add( all.get(2));

		List<QuadBlob> found = ConnectGridSquares.copy(sub);

		checkSquare(found.get(0),50,60,10,12,2);
		checkSquare(found.get(1),51,61,11,13,1);
		checkSquare(found.get(2),52,62,12,14,1);
	}