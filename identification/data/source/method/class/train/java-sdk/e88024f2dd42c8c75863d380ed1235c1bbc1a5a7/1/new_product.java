public Response putBucketAccessStyleMode(String bucket, AccessStyleMode mode) throws QiniuException {
        String url = String.format("%s/accessMode/%s/mode/%d", configHelper.ucHost(), bucket, mode.getType());
        Response res = post(url, null);
        if (!res.isOK()) {
            throw new QiniuException(res);
        }
        return res;
    }