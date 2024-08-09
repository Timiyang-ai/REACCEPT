@SuppressWarnings("unchecked")
	public static File unzip(File zipFile, File outFile) throws UtilException {
		ZipFile zipFileObj = null;
		try {
			zipFileObj = new ZipFile(zipFile);
			final Enumeration<ZipEntry> em = (Enumeration<ZipEntry>) zipFileObj.entries();
			ZipEntry zipEntry = null;
			File outItemFile = null;
			while (em.hasMoreElements()) {
				zipEntry = em.nextElement();
				outItemFile = new File(outFile, zipEntry.getName());
				if (zipEntry.isDirectory()) {
					outItemFile.mkdirs();
				} else {
					FileUtil.touch(outItemFile);
					copy(zipFileObj, zipEntry, outItemFile);
				}
			}
		} catch (IOException e) {
			throw new UtilException(e);
		} finally {
			IoUtil.close(zipFileObj);

		}
		return outFile;
	}