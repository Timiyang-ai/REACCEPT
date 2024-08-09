@Test(expected = IOException.class)
	public void testAnalyzeAll_BrokenZip() throws IOException {
		File file = new File(folder.getRoot(), "broken.zip");
		OutputStream out = new FileOutputStream(file);
		ZipOutputStream zip = new ZipOutputStream(out);
		zip.putNextEntry(new ZipEntry("brokenentry.txt"));
		out.write(0x23); // Unexpected data here
		zip.close();
		analyzer.analyzeAll(file);
	}