@SuppressWarnings("unchecked")
	public static File unzip(ZipFile zipFile, File outFile) throws IORuntimeException {
		try {
			final Enumeration<ZipEntry> em = (Enumeration<ZipEntry>) zipFile.entries();
			ZipEntry zipEntry;
			File outItemFile;
			while (em.hasMoreElements()) {
				zipEntry = em.nextElement();
				// FileUtil.file会检查slip漏洞，漏洞说明见http://blog.nsfocus.net/zip-slip-2/
				outItemFile = FileUtil.file(outFile, zipEntry.getName());
				if (zipEntry.isDirectory()) {
					// 创建对应目录
					outItemFile.mkdirs();
				} else {
					// 写出文件
					write(zipFile, zipEntry, outItemFile);
				}
			}
		} finally {
			IoUtil.close(zipFile);
		}
		return outFile;
	}