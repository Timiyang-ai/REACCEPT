public static void storeProperties(Properties properties, OutputStream outStream, String comment) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(outStream), "UTF-8");
			Writer out = new BufferedWriter(osw);
			if (comment != null) {
				out.write("\n#" + comment + "\n");
			}
			out.write("#" + new Date() + "\n");
			for (Map.Entry<Object, Object> e : properties.entrySet()) {
				out.write(e.getKey() + "=" + e.getValue() + "\n");
			}
			out.write("\n");
			out.flush();
			out.close();
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