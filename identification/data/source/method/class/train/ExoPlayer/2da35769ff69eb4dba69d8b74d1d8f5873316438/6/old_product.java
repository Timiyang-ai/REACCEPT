public static @Nullable Pair<Integer, Integer> getCodecProfileAndLevel(String codec) {
    if (codec == null) {
      return null;
    }
    String[] parts = codec.split("\\.");
    switch (parts[0]) {
      case CODEC_ID_HEV1:
      case CODEC_ID_HVC1:
        return getHevcProfileAndLevel(codec, parts);
      case CODEC_ID_DVHE:
      case CODEC_ID_DVH1:
        return getDolbyVisionProfileAndLevel(codec, parts);
      case CODEC_ID_AVC1:
      case CODEC_ID_AVC2:
        return getAvcProfileAndLevel(codec, parts);
      case CODEC_ID_MP4A:
        return getAacCodecProfileAndLevel(codec, parts);
      default:
        return null;
    }
  }