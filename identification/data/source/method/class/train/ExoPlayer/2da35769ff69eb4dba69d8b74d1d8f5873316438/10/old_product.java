public static Pair<Integer, Integer> getCodecProfileAndLevel(Format format) {
    if (format.codecs == null) {
      return null;
    }
    String[] parts = format.codecs.split("\\.");
    switch (parts[0]) {
      case CODEC_ID_AVC1:
      case CODEC_ID_AVC2:
        return getAvcProfileAndLevel(format.codecs, parts);
      case CODEC_ID_VP09:
        return getVp9ProfileAndLevel(format.codecs, parts);
      case CODEC_ID_HEV1:
      case CODEC_ID_HVC1:
        return getHevcProfileAndLevel(format.codecs, parts);
      case CODEC_ID_DVHE:
      case CODEC_ID_DVH1:
        return getDolbyVisionProfileAndLevel(format.codecs, parts);
      case CODEC_ID_AV01:
        return getAv1ProfileAndLevel(format.codecs, parts);
      case CODEC_ID_MP4A:
        return getAacCodecProfileAndLevel(format.codecs, parts);
      default:
        return null;
    }
  }