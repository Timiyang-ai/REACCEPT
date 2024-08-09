@Test
	public void testLoadResources_ZipFile_partial_lazy() throws IOException {
		// given
		ZipFile zipFile = new ZipFile(testBookFilename);
		
		// when
		Resources resources = ResourcesLoader.loadResources(zipFile, encoding, Collections.singletonList(MediatypeService.CSS));
		
		// then
		verifyResources(resources);
		Assert.assertEquals(Resource.class, resources.getById("container").getClass());
		Assert.assertEquals(LazyResource.class, resources.getById("book1").getClass());
		Assert.assertEquals(Resource.class, resources.getById("chapter1").getClass());
	}