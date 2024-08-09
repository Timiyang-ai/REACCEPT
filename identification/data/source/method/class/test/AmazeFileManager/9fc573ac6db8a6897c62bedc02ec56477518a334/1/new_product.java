public static Decompressor getCompressorInstance(Context context, File file) {
        Decompressor decompressor;
        String type = getExtension(file.getPath());

        if (isZip(type)) {
            decompressor = new ZipDecompressor(context);
        } else if (isRar(type)) {
            decompressor = new RarDecompressor(context);
        } else if(isTar(type)) {
            decompressor = new TarDecompressor(context);
        } else if(isGzippedTar(type)) {
            decompressor = new GzipDecompressor(context);
        } else if(isBzippedTar(type)) {
            decompressor = new Bzip2Decompressor(context);
        } else if(isXzippedTar(type)) {
            decompressor = new XzDecompressor(context);
        } else if(isLzippedTar(type)) {
            decompressor = new LzmaDecompressor(context);
        } else {
            return null;
        }

        decompressor.setFilePath(file.getPath());
        return decompressor;
    }