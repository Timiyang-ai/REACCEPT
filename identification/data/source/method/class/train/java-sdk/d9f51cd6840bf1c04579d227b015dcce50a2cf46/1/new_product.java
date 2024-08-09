public <T> T prefop(String persistentId, Class<T> retClass) throws QiniuException {
        StringMap params = new StringMap().put("id", persistentId);
        byte[] data = StringUtils.utf8Bytes(params.formString());
        String url = String.format("%s/status/get/prefop", configuration.apiHost());
        Response response = this.client.post(url, data, null, Client.FormMime);
        return response.jsonToObject(retClass);
    }