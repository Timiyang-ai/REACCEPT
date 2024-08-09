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
			fout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile), StandardCharsets.UTF_8));
			Reader tempRd;
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
					tempRd.close();
				}
				catch (IOException e) {
					throw new APIException("Obs.error.unable.convert.complex.data", new Object[] { "Reader" }, e);
				}
			} else if (InputStream.class.isAssignableFrom(data.getClass())) {
				try {
					IOUtils.copy((InputStream) data, fout);
				}
				catch (IOException e) {
					throw new APIException("Obs.error.unable.convert.complex.data", new Object[] { "input stream" }, e);
				}
			}
			
			// Set the Title and URI for the valueComplex
			obs.setValueComplex(outfile.getName() + " file |" + outfile.getName());
			
			// Remove the ComplexData from the Obs
			obs.setComplexData(null);
			
		}
		catch (IOException ioe) {
			throw new APIException("Obs.error.trying.write.complex", null, ioe);
		}
		finally {
			try {
				fout.close();
			}
			catch (Exception e) {
				// pass
			}
		}
		
		return obs;
	}