public static Resources loadResources(ZipInputStream zipInputStream, String defaultHtmlEncoding) throws IOException {
		Resources result = new Resources();
		ZipEntry zipEntry;
		do {
			// get next valid zipEntry
			zipEntry = getNextZipEntry(zipInputStream);
			if((zipEntry == null) || (zipEntry == ERROR_ZIP_ENTRY) || zipEntry.isDirectory()) {
				continue;
			}
			
			// store resource
			Resource resource = ResourceUtil.createResource(zipEntry, zipInputStream);
			if(resource.getMediaType() == MediatypeService.XHTML) {
				resource.setInputEncoding(defaultHtmlEncoding);
			}
			result.add(resource);
		} while(zipEntry != null);

		return result;
	}