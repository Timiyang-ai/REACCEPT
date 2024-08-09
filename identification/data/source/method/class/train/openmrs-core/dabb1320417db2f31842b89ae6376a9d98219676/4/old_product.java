public Obs saveObs(Obs obs) throws APIException {
		// Get the buffered file  from the ComplexData.
		ComplexData complexData = obs.getComplexData();
		if (complexData == null) {
			log.error("Cannot save complex data where obsId=" + obs.getObsId() + " because its ComplexData is null.");
			return obs;
		}
		
		FileOutputStream fout = null;
		try {
			File outfile = getOutputFileToWrite(obs);
			fout = new FileOutputStream(outfile);
			
			Object data = obs.getComplexData().getData();
			if (data instanceof byte[]) {
				fout.write((byte[]) data);
			} else if (InputStream.class.isAssignableFrom(data.getClass())) {
				try {
					OpenmrsUtil.copyFile((InputStream) data, fout);
				}
				catch (IOException e) {
					throw new APIException(
					        "Unable to convert complex data to a valid input stream and then read it into a buffered image");
				}
			}
			
			// Set the Title and URI for the valueComplex
			obs.setValueComplex(outfile.getName() + " file |" + outfile.getName());
			
			// Remove the ComplexData from the Obs
			obs.setComplexData(null);
			
		}
		catch (IOException ioe) {
			throw new APIException("Trying to write complex obs to the file system. ", ioe);
		}
		finally {
			try {
				fout.close();
			}
			catch (Throwable t) {
				// pass
			}
		}
		
		return obs;
	}