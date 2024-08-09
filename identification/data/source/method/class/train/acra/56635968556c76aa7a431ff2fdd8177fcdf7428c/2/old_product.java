public static String guessMimeType(Uri uri){
        String type = null;
        final String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                .toString());
        if (fileExtension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        if (type == null) {
            type = MIME_TYPE_OCTET_STREAM;
        }
        return type;
    }