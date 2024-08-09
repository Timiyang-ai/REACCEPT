public static File unzip(ZipInputStream zipStream, File outFile) throws UtilException {
		try {
			ZipEntry zipEntry;
			File outItemFile;
			while (null != (zipEntry = zipStream.getNextEntry())) {
				// FileUtil.file会检查slip漏洞，漏洞说明见http://blog.nsfocus.net/zip-slip-2/
				outItemFile = FileUtil.file(outFile, zipEntry.getName());
				if (zipEntry.isDirectory()) {
					// 目录
					outItemFile.mkdirs();
				} else {
					// 文件
					FileUtil.writeFromStream(zipStream, outItemFile);
				}
			}
		} catch (IOException e) {
			throw new UtilException(e);
		} finally {
			IoUtil.close(zipStream);
		}
		return outFile;
	}