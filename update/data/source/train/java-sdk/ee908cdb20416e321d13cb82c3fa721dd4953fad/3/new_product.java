public Response putBucketAccessMode(String bucket, AclType acl) throws QiniuException {
        String url = String.format("%s/private?bucket=%s&private=%s", configuration.ucHost(), bucket, acl.getType());
        Response res = post(url, null);
        if (!res.isOK()) {
            throw new QiniuException(res);
        }
        return res;
    }