public void add(String id, String url, String oldUrl, boolean needsUpdate) throws MalformedURLException, DownloadFailedException {
        final NvdCveInfo item = new NvdCveInfo();
        item.setNeedsUpdate(needsUpdate); //the others default to true, to make life easier later this should default to false.
        item.setId(id);
        item.setUrl(url);
        item.setOldSchemaVersionUrl(oldUrl);
        LOGGER.debug("Checking for updates from: {}", url);
        item.setTimestamp(Downloader.getLastModified(new URL(url)));
        collection.put(id, item);
    }