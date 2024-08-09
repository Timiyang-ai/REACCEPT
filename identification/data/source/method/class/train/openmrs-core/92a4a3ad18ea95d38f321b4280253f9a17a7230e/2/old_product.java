public static void storeProperties(Properties properties, OutputStream outStream, String comment) {
		try {
			Charset utf8 = Charset.forName("UTF-8");
			properties.store(new OutputStreamWriter(outStream, utf8), comment);
		}
		catch (FileNotFoundException fnfe) {
			log.error("target file not found" + fnfe);
		}
		catch (UnsupportedEncodingException ex) { // pass
			log.error("unsupported encoding error hit" + ex);
		}
		catch (IOException ioex) {
			log.error("IO exception encountered trying to append to properties file" + ioex);
		}
		
	}