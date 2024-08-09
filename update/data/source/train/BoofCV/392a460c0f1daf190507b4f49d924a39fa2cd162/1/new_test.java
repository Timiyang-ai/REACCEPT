@Test
	public void findErrorEvaluator() {

		// one error
		findErrorEvaluator(array(64, 192, 93, 231, 52, 92, 228, 49, 83, 2455),
				array(3,1),
				array(0,64));

		// two errors
		findErrorEvaluator(array(62, 101, 255, 19, 236, 196, 112, 227, 174, 215),
				array(159,118,1),
				array(0,62,142));

		// three errors
		findErrorEvaluator(array(32, 188, 7, 92, 8, 39, 184, 32, 101, 213),
				array(97, 138, 194, 1),
				array(0, 32, 217, 182));
	}