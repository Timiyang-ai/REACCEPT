public Response putReferAntiLeech(String bucket, BucketReferAntiLeech antiLeech) throws QiniuException {
    	String url = String.format("%s/referAntiLeech?bucket=%s&%s", configuration.ucHost(), bucket, antiLeech.asQueryString());
    	Response res = post(url, null);
        if (!res.isOK()) {
            throw new QiniuException(res);
        }
        return res;
    }