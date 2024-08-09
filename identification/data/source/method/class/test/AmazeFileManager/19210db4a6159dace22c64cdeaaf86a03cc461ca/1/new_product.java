public static Extractor getExtractorInstance(@NonNull Context context, @NonNull File file, @NonNull String outputPath,
                                                 @NonNull Extractor.OnUpdate listener, @Nullable String password) {
        Extractor extractor;
        String type = getExtension(file.getPath());

        if (isZip(type)) {
            extractor = new ZipExtractor(context, file.getPath(), outputPath, listener, null);
        } else if (isRar(type)) {
            extractor = new RarExtractor(context, file.getPath(), outputPath, listener, null);
        } else if(isTar(type)) {
            extractor = new TarExtractor(context, file.getPath(), outputPath, listener, null);
        } else if(isGzippedTar(type)) {
            extractor = new GzipExtractor(context, file.getPath(), outputPath, listener, null);
        } else if(isBzippedTar(type)) {
            extractor = new Bzip2Extractor(context, file.getPath(), outputPath, listener, null);
        } else if(isXzippedTar(type)) {
            extractor = new XzExtractor(context, file.getPath(), outputPath, listener, null);
        } else if(isLzippedTar(type)) {
            extractor = new LzmaExtractor(context, file.getPath(), outputPath, listener, null);
        } else if(is7zip(type)) {
            extractor = new SevenZipExtractor(context, file.getPath(), outputPath, listener, null);
        } else {
            return null;
        }

        return extractor;
    }