public Response copy(String fromBucket, String fromFileKey, String toBucket, String toFileKey)
            throws QiniuException {
        Response res = copy(fromBucket, fromFileKey, toBucket, toFileKey, false);
        if (!res.isOK()) {
            throw new QiniuException(res);
        }
        res.close();
        return res;
    }