@Nullable
  public static Pair<Integer, Integer> getCodecProfileAndLevel(@Nullable String codec) {
    if (codec == null) {
      return null;
    }
    // TODO: Check codec profile/level for AV1 once targeting Android Q and [Internal: b/128552878]
    // has been fixed.
    String[] parts = codec.split("\\.");
    switch (parts[0]) {
      case CODEC_ID_AVC1:
      case CODEC_ID_AVC2:
        return getAvcProfileAndLevel(codec, parts);
      case CODEC_ID_VP09:
        return getVp9ProfileAndLevel(codec, parts);
      case CODEC_ID_HEV1:
      case CODEC_ID_HVC1:
        return getHevcProfileAndLevel(codec, parts);
      case CODEC_ID_DVHE:
      case CODEC_ID_DVH1:
        return getDolbyVisionProfileAndLevel(codec, parts);
      case CODEC_ID_MP4A:
        return getAacCodecProfileAndLevel(codec, parts);
      default:
        return null;
    }
  }