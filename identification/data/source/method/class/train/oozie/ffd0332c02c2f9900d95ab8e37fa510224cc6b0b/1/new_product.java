public void addMissingPartition(String hcatURI, String actionId) throws MetadataServiceException {
        HCatURI uri;
        try {
            uri = new HCatURI(hcatURI);
        }
        catch (URISyntaxException e) {
            throw new MetadataServiceException(ErrorCode.E1503, e.getMessage());
        }
        PartitionWrapper partition = new PartitionWrapper(uri.getServerEndPoint(), uri.getDb(), uri.getTable(),
                uri.getPartitionMap());
        addMissingPartition(partition, actionId);
    }