public Obs saveObs(Obs obs) throws APIException {
		try {
			// Write the File to the File System
			String fileName = obs.getComplexData().getTitle();
			InputStream in = (InputStream) obs.getComplexData().getData();
			File outfile = getOutputFileToWrite(obs);
			OutputStream out = new FileOutputStream(outfile, false);
			OpenmrsUtil.copyFile(in, out);
			
			// Store the filename in the Obs
			obs.setComplexData(null);
			obs.setValueComplex(fileName + "|" + outfile.getName());
			
			// close the stream
			out.close();
		}
		catch (Exception e) {
			throw new APIException("Error writing binary data complex obs to the file system. ", e);
		}
		
		return obs;
	}