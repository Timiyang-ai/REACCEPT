public void test_read() throws IOException {
		int result = 0;
		int buffer[] = new int[500];
		byte orgBuffer[] = { 1, 3, 4, 7, 8 };
		InputStream infile = Support_Resources
				.getStream("hyts_constru_OD.txt"); // android-changed
		Inflater inflate = new Inflater();
		InflaterInputStream inflatIP = new InflaterInputStream(infile,
				inflate);

		int i = 0;
		while ((result = inflatIP.read()) != -1) {
			buffer[i] = result;
			i++;
		}
		inflatIP.close();

		for (int j = 0; j < orgBuffer.length; j++) {
			assertTrue(
				"original compressed data did not equal decompressed data",
				buffer[j] == orgBuffer[j]);
		}
	}