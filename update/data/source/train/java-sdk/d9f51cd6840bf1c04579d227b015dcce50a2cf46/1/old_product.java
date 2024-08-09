public <T> T prefop(String persistentId, Class<T> retClass) throws QiniuException {
        StringMap params = new StringMap().put("id", persistentId);
        byte[] data = StringUtils.utf8Bytes(params.formString());
        String apiHost;

        if (this.configuration.region != null) {
            apiHost = this.configuration.region.getApiHost();
            if (this.configuration.useHttpsDomains) {
                apiHost = this.configuration.region.getApiHost();
            }
        } else {
            apiHost = "http://api.qiniu.com";
            if (this.configuration.useHttpsDomains) {
                apiHost = "https://api.qiniu.com";
            }
        }

        String url = String.format("%s/status/get/prefop", apiHost);
        Response response = this.client.post(url, data, null, Client.FormMime);
        return response.jsonToObject(retClass);
    }