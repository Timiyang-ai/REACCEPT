private double getQuality(final String mimeType, final ValueMap transforms) {
        final String key = "quality";
        final int defaultQuality = 82;
        final int maxQuality = 100;
        final int minQuality = 0;
        final int maxQualityGIF = 255;
        final double oneHundred = 100D;

        log.debug("Transforming with [ quality ]");

        double quality = transforms.get(key, defaultQuality);

        log.debug("quality: {}", quality);

        if (quality > maxQuality) {
            quality = defaultQuality;
        } else if (quality < minQuality) {
            quality = minQuality;
        }

        quality = quality / oneHundred;

        if (StringUtils.equals("image/gif", mimeType)) {
            quality = quality * maxQualityGIF;
        }

        return quality;
    }