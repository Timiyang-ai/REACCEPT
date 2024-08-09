public int analyzeAll(final InputStream input, final String location)
			throws IOException {
		final ContentTypeDetector detector = new ContentTypeDetector(input);
		switch (detector.getType()) {
		case ContentTypeDetector.CLASSFILE:
			analyzeClass(detector.getInputStream(), location);
			return 1;
		case ContentTypeDetector.ZIPFILE:
			return analyzeZip(detector.getInputStream(), location);
		case ContentTypeDetector.GZFILE:
			return analyzeGzip(detector.getInputStream(), location);
		case ContentTypeDetector.PACK200FILE:
			return analyzePack200(detector.getInputStream(), location);
		default:
			return 0;
		}
	}