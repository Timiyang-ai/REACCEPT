private double getQuality(final String mimeType, final ValueMap transforms) {
        final String KEY = "quality";
        final int DEFAULT_QUALITY = 82;
        final int MAX_QUALITY = 100;
        final int MIN_QUALITY = 0;
        final String MIME_TYPE_GIF = "image/gif";
        final int IMAGE_GIF_MAX_QUALITY = 255;

        log.debug("Transforming with [ quality ]");

        double quality = transforms.get(KEY, DEFAULT_QUALITY);

        log.debug("quality: {}", quality);

        if (quality > MAX_QUALITY) {
            quality = DEFAULT_QUALITY;
        } else if (quality < MIN_QUALITY) {
            quality = MIN_QUALITY;
        }

        quality = quality / 100D;

        if (StringUtils.equals(MIME_TYPE_GIF, mimeType)) {
            quality = quality * IMAGE_GIF_MAX_QUALITY;
        }

        return quality;
    }