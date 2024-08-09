public void add(String id, String url, long timestamp, boolean needsUpdate) {
        final NvdCveInfo item = new NvdCveInfo();
        item.setNeedsUpdate(needsUpdate); //the others default to true, to make life easier later this should default to false.
        item.setId(id);
        item.setUrl(url);
        item.setTimestamp(timestamp);
        collection.put(id, item);
    }