public File getOutputFileToWrite(Obs obs) throws IOException {
		// Get the title and remove the extension.
		String title = obs.getComplexData().getTitle();
		String extension = "." + getExtension(title);
		
		// If getExtension returns the title, there was no extension
		if (getExtension(title).equals(title)) {
			extension = "";
		}
		
		File dir = OpenmrsUtil.getDirectoryInApplicationDataDirectory(Context.getAdministrationService().getGlobalProperty(
		    OpenmrsConstants.GLOBAL_PROPERTY_COMPLEX_OBS_DIR));
		File outputfile;
		
		// Get the output stream
		if (null == title) {
			String now = longfmt.format(new Date());
			outputfile = new File(dir, now);
		} else {
			title = title.replace(extension, "");
			
			outputfile = new File(dir, title + extension);
		}
		
		int i = 0;
		String tmp;
		
		// If the Obs does not exist, but the File does, append a two-digit
		// count number to the filename and save it.
		while (obs.getObsId() == null && outputfile.exists() && i < 100) {
			// Remove the extension from the filename.
			tmp = String.valueOf(outputfile.getAbsolutePath().replace(extension, ""));
			// Append two-digit count number to the filename.
			String filename = (i < 1) ? tmp + "_" + nf.format(Integer.valueOf(++i)) : tmp.replace(nf.format(Integer
			        .valueOf(i)), nf.format(Integer.valueOf(++i)));
			// Append the extension to the filename
			outputfile = new File(filename + extension);
		}
		
		return outputfile;
		
	}