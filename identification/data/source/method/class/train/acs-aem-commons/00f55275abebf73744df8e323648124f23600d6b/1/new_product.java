protected boolean isProgressiveJpeg(final String mimeType, final ValueMap transforms) {
        boolean enabled = transforms.get("enabled", false);
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