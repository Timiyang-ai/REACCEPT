public static String formatFileSize(
            @Nullable Context context, long sizeBytes, MeasureUnit unit, long mult) {
        if (context == null) {
            return "";
        }
        final RoundedBytesResult res = formatBytes(sizeBytes, unit, mult);
        return bidiWrap(context, formatRoundedBytesResult(context, res));
    }