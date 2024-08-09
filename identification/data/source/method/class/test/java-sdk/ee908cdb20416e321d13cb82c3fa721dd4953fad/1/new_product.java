public Response putBucketMaxAge(String bucket, long maxAge) throws QiniuException {
    	String url = String.format("%s/maxAge?bucket=%s&maxAge=%d", configuration.ucHost(), bucket, maxAge);
    	Response res = post(url, null);
        if (!res.isOK()) {
            throw new QiniuException(res);
        }
        return res;
    }