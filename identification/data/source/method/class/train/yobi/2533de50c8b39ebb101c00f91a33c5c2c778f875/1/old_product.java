@Override
    public ObjectNode getMetaDataFromPath(String path) throws IOException, NoHeadException, GitAPIException, SVNException {
        return getMetaDataFromPath(null, path);
    }