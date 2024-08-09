public static String formatFileSize(
            @Nullable Context context, long sizeBytes, int suffix, long mult) {
        if (context == null) {
            return "";
        }
        final Formatter.BytesResult res =
                formatBytes(context.getResources(), sizeBytes, suffix, mult);
        return BidiFormatter.getInstance()
                .unicodeWrap(context.getString(getFileSizeSuffix(context), res.value, res.units));
    }