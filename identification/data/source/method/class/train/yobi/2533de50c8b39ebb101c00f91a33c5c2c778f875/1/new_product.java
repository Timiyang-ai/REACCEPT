@Override
    public ObjectNode getMetaDataFromPath(String path) throws IOException, GitAPIException, SVNException {
        return getMetaDataFromPath(null, path);
    }