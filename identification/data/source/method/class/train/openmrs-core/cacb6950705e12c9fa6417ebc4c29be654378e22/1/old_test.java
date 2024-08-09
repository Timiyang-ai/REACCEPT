@Test
	public void getExtensions_shouldNotExpandExtensionNamesIfExtensionNamesIsNull() throws Exception {
		ArrayList<Extension> extensions = new ArrayList<Extension>();

		Extension mockExtension = new MockExtension();
		extensions.add(mockExtension);

		mockModule.setExtensions(extensions);
		mockModule.setExtensionNames(null);
		ArrayList<Extension> ret = new ArrayList<Extension>(mockModule.getExtensions());

		verifyPrivate(mockModule, never()).invoke("expandExtensionNames");
	}