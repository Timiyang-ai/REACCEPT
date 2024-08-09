public static void storeProperties(Properties properties, File file, String comment) {
		OutputStream outStream = null;
		try {
			outStream = new FileOutputStream(file, true);
			storeProperties(properties, outStream, comment);
		}
		catch (IOException ex) {
			log.error("Unable to create file " + file.getAbsolutePath() + " in storeProperties routine.");
		}
		finally {
			try {
				if (outStream != null)
					outStream.close();
			}
			catch (IOException ioe){
				//pass
			}
		}
	}