public static Decompressor getCompressorInstance(Context context, File file) {
        Decompressor decompressor;

        String path = file.getPath().toLowerCase();
        boolean isZip = path.endsWith(".zip") || path.endsWith(".jar") || path.endsWith(".apk");
        boolean isTar = path.endsWith(".tar") || path.endsWith(".tar.gz");
        boolean isRar = path.endsWith(".rar");

        if (isZip) {
            decompressor = new ZipDecompressor(context);
        } else if (isRar) {
            decompressor = new RarDecompressor(context);
        } else if(isTar) {
            decompressor = new TarDecompressor(context);
        } else {
            return null;
        }

        decompressor.setFilePath(file.getPath());
        return decompressor;
    }