public static String uid2url(String uid) {
        String url = uid.replace('\u0000', '/');
        return url.substring(0, url.lastIndexOf('/')); // remove date from end
    }