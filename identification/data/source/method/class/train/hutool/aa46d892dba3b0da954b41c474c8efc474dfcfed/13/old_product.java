@SuppressWarnings("unchecked")
	public static void unzip(File zipFile,File outFile) throws IOException{
		final ZipFile zipFileObj = new ZipFile(zipFile);
		final Enumeration<ZipEntry> em = (Enumeration<ZipEntry>) zipFileObj.entries();
		ZipEntry zipEntry = null;
		File outItemFile = null;
		while(em.hasMoreElements()){
			zipEntry = em.nextElement();
			outItemFile = new File(outFile,zipEntry.getName());
			log.debug("UNZIP {}", outItemFile.getPath());
			if(zipEntry.isDirectory()){
				outItemFile.mkdirs();
			}else{
				FileUtil.touch(outItemFile);
				copy(zipFileObj, zipEntry, outItemFile);
			}
		}
		FileUtil.close(zipFileObj);
	}