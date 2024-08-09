public static Decompressor getCompressorInstance(Context context, File file) {
        Decompressor decompressor;
        String type = file.getPath().substring(file.getPath().lastIndexOf('.')+1, file.getPath().length()).toLowerCase();

        if (isZip(type)) {
            decompressor = new ZipDecompressor(context);
        } else if (isRar(type)) {
            decompressor = new RarDecompressor(context);
        } else if(isTar(type)) {
            decompressor = new TarDecompressor(context);
        } else {
            return null;
        }

        decompressor.setFilePath(file.getPath());
        return decompressor;
    }