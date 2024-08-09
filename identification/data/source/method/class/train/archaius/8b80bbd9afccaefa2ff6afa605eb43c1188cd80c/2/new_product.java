synchronized void setZkProperty(String key, String value) throws Exception {
        final String path = configRootPath + "/" + key; 

        byte[] data = value.getBytes(charset);
        
        try {
            // attempt to create (intentionally doing this instead of checkExists()) 
            client.create().creatingParentsIfNeeded().forPath(path, data);
        } catch (NodeExistsException exc) {
            // key already exists - update the data instead
            client.setData().forPath(path, data);
        }
    }