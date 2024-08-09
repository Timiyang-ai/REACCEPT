@Override
	public Obs saveObs(Obs obs) throws APIException {
		ComplexData complexData = obs.getComplexData();
		if (complexData == null) {
			log.error("Cannot save complex data where obsId=" + obs.getObsId() + " because its ComplexData is null.");
			return obs;
		}
		BufferedWriter fout = null;
		try {
			File outfile = getOutputFileToWrite(obs);
			fout = new BufferedWriter(new FileWriter(outfile));
			Reader tempRd = null;
			Object data = obs.getComplexData().getData();
			if (data instanceof char[]) {
				fout.write((char[]) data);
			} else if (Reader.class.isAssignableFrom(data.getClass())) {
				try {
					tempRd = new BufferedReader((Reader) data);
					while (true) {
						int character = tempRd.read();
						if (character == -1) {
							break;
						}
						fout.write(character);
					}
				}
				catch (IOException e) {
					throw new APIException(
					        "Unable to convert complex data to a valid Reader and then read it into a buffered image");
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