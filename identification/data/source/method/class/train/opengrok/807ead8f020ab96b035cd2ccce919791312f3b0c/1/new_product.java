public static String uid2url(String uid) {
        String url = uid.replace('\u0000', PATH_SEPARATOR);
        return url.substring(0, url.lastIndexOf(PATH_SEPARATOR)); // remove date from end
    }