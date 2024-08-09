	@Test
	public void getPackagesFromFile_shouldReturnEmptyStringSetIfNonJarFile() {
		File f = new File(this.getClass().getResource("/org/openmrs/module/include/test1-1.0-SNAPSHOT.omod").getFile());
		Collection<String> packageCollection = ModuleUtil.getPackagesFromFile(f);
		Assert.assertTrue(packageCollection.isEmpty());
	}