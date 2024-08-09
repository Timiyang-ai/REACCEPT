	@Test
	public void getOutputFileToWrite_shouldNeverOverwritePreviousFiles() throws IOException {
		String content1 = "A";
		String content2 = "B";
		
		File previousFile = null;
		File currentFile = null;
		
		for (int i = 0; i <= 101; i++) {
			String currentData = (i % 2 == 0) ? content1 : content2;
			
			ComplexData complexData = new ComplexData(FILENAME, currentData);
			
			Obs obs = new Obs();
			obs.setComplexData(complexData);
			
			currentFile = handler.getOutputFileToWrite(obs);
			
			try (BufferedWriter fout = new BufferedWriter(
			        new OutputStreamWriter(new FileOutputStream(currentFile), StandardCharsets.UTF_8))) {
				fout.write(currentData);
			}
			
			try (BufferedReader fin = new BufferedReader(
			        new InputStreamReader(new FileInputStream(currentFile), StandardCharsets.UTF_8))) {
				String readData = fin.readLine();
				assertEquals(readData, currentData);
			}
			
			if (i > 0) {
				assertFalse(FileUtils.contentEquals(previousFile, currentFile));
			}
			
			previousFile = currentFile;
		}
	}