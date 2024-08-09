public File getOutputFileToWrite(Obs obs) throws IOException {
		String title = obs.getComplexData().getTitle();
		String titleWithoutExtension = FilenameUtils.removeExtension(title);
		String extension = "." + StringUtils.defaultIfEmpty(FilenameUtils.getExtension(title), "dat");
		String uuid = obs.getUuid();
		String filename;
		
		if (StringUtils.isNotBlank(titleWithoutExtension)) {
			filename = titleWithoutExtension + "_" + uuid + extension;
		} else {
			filename = uuid + extension;
		}
		
		File dir = OpenmrsUtil.getDirectoryInApplicationDataDirectory(
		    Context.getAdministrationService().getGlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_COMPLEX_OBS_DIR));
		File outputfile = new File(dir, filename);
		
		return outputfile;
	}