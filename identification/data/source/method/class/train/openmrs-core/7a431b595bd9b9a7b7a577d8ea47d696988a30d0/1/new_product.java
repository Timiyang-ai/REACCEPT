public Obs saveObs(Obs obs) throws APIException {
		// Get the buffered image from the ComplexData.
		BufferedImage img = null;
		
		Object data = obs.getComplexData().getData();
		if (data instanceof BufferedImage) {
			img = (BufferedImage) obs.getComplexData().getData();
		} else if (data instanceof InputStream) {
			try {
				img = ImageIO.read((InputStream) data);
				if (img == null) {
					throw new IllegalArgumentException("Invalid image file");
				}
			}
			catch (IOException e) {
				throw new APIException(
				        "Unable to convert complex data to a valid input stream and then read it into a buffered image", e);
			}
		}
		
		if (img == null) {
			throw new APIException("Cannot save complex obs where obsId=" + obs.getObsId()
			        + " because its ComplexData.getData() is null.");
		}
		
		File outfile = null;
		try {
			outfile = getOutputFileToWrite(obs);
			
			String extension = getExtension(obs.getComplexData().getTitle());
			
			// TODO: Check this extension against the registered extensions for validity
			
			// Write the file to the file system.
			ImageIO.write(img, extension, outfile);
			
			// Set the Title and URI for the valueComplex
			obs.setValueComplex(extension + " image |" + outfile.getName());
			
			// Remove the ComlexData from the Obs
			obs.setComplexData(null);
			
		}
		catch (IOException ioe) {
			if (outfile.length() == 0) { // OpenJDK 7 may leave a 0-byte file when ImageIO.write(..) fails.
				outfile.delete();
			}
			throw new APIException("Trying to write complex obs to the file system. ", ioe);
		}
		
		return obs;
	}