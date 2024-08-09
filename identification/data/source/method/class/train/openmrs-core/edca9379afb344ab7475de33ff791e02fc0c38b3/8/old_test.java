	@Test
	public void getComplexObs_shouldFillInComplexDataObjectForComplexObs() throws IOException {
		executeDataSet(COMPLEX_OBS_XML);
		// create gif file
		// make sure the file isn't there to begin with
		AdministrationService as = Context.getAdministrationService();
		File complexObsDir = OpenmrsUtil.getDirectoryInApplicationDataDirectory(as
		        .getGlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_COMPLEX_OBS_DIR));
		File createdFile = new File(complexObsDir, "openmrs_logo_small.gif");
		if (createdFile.exists())
			createdFile.delete();
		int width = 10;
		int height = 10;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = image.getRaster();
		int[] colorArray = new int[3];
		int h = 255;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (i == 0 || j == 0 || i == width - 1 || j == height - 1 || (i > width / 3 && i < 2 * width / 3)
				        && (j > height / 3 && j < 2 * height / 3)) {
					colorArray[0] = h;
					colorArray[1] = h;
					colorArray[2] = 0;
				} else {
					colorArray[0] = 0;
					colorArray[1] = 0;
					colorArray[2] = h;
				}
				raster.setPixel(i, j, colorArray);
			}
		}
		ImageIO.write(image, "gif", createdFile);
		// end create gif file
		ObsService os = Context.getObsService();

		Obs complexObs = os.getObs(44);
		
		Assert.assertNotNull(complexObs);
		Assert.assertTrue(complexObs.isComplex());
		Assert.assertNotNull(complexObs.getValueComplex());
		Assert.assertNotNull(complexObs.getComplexData());
		Assert.assertEquals(complexObs, os.getObsByUuid(complexObs.getUuid()));
		// delete gif file
		// we always have to delete this inside the same unit test because it is
		// outside the
		// database and hence can't be "rolled back" like everything else
		createdFile.delete();
	}