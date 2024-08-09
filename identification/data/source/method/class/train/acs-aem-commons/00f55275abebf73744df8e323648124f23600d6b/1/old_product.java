private boolean isProgressiveJpeg(String mimeType, ValueMap imageTransformersWithParams) {
        boolean enabled = imageTransformersWithParams.get(TYPE_PROGRESSIVE, EMPTY_PARAMS).get("enabled", false);
        if (enabled) {
            if ("image/jpeg".equals(mimeType) || "image/jpg".equals(mimeType)) {
                return true;
            } else {
                log.debug("Progressive encoding is only supported for JPEGs. Mime type: {}", mimeType);
                return false;
            }
        } else {
            return false;
        }
    }