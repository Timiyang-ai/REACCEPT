public boolean removePartition(String hcatURI) throws MetadataServiceException {
        HCatURI uri;
        try {
            uri = new HCatURI(hcatURI);
        }
        catch (URISyntaxException e) {
            throw new MetadataServiceException(ErrorCode.E1025, e.getMessage());
        }
        PartitionWrapper partition = new PartitionWrapper(uri.getServer(), uri.getDb(), uri.getTable(),
                uri.getPartitionMap());
        return removePartition(partition, true);
    }