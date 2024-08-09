@Test
	public void testLoadResources_ZipFile_partial_lazy() throws FileNotFoundException, IOException {
		// given
		ZipFile zipFile = new ZipFile(testBookFilename);
		
		// when
		Resources resources = ResourcesLoader.loadResources(zipFile, encoding, Arrays.asList(MediatypeService.CSS));
		
		// then
		verifyResources(resources);
		Assert.assertEquals(Resource.class, resources.getById("container").getClass());
		Assert.assertEquals(LazyResource.class, resources.getById("book1").getClass());
		Assert.assertEquals(Resource.class, resources.getById("chapter1").getClass());
	}