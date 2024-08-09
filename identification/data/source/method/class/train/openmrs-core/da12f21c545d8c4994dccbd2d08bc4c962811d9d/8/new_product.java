public Obs saveObs(Obs obs) throws APIException {
		
		try {
			// Write the File to the File System
			String fileName = obs.getComplexData().getTitle();
			File outfile = getOutputFileToWrite(obs);
			OutputStream out = new FileOutputStream(outfile, false);
			FileInputStream mediaStream = (FileInputStream) obs.getComplexData().getData();
			OpenmrsUtil.copyFile(mediaStream, out);
			
			// Store the filename in the Obs
			obs.setComplexData(null);
			obs.setValueComplex(fileName + "|" + outfile.getName());
			
			// close the stream
			out.close();
		}
		catch (IOException ioe) {
			throw new APIException("Obs.error.trying.write.complex", null, ioe);
		}
		
		return obs;
	}