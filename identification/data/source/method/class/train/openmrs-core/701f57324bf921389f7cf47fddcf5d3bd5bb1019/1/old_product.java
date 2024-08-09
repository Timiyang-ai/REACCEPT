public static void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {
		if (inputStream == null || outputStream == null) {
			if (outputStream != null) {
				try {
					outputStream.close();
				}
				catch (Exception e) { /* pass */
				}
			}
			
			return;
		}
		
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(inputStream);
			out = new BufferedOutputStream(outputStream);
			while (true) {
				int data = in.read();
				if (data == -1) {
					break;
				}
				out.write(data);
			}
		}
		finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
			try {
				outputStream.close();
			}
			catch (Exception e) { /* pass */
			}
		}
		
	}