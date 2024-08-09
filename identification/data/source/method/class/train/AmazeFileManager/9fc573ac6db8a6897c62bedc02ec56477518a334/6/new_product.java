public static Extractor getExtractorInstance(Context context, File file, String outputPath,
                                                 Extractor.OnUpdate listener) {
        Extractor extractor;
        String type = getExtension(file.getPath());

        if (isZip(type)) {
            extractor = new ZipExtractor(context, file.getPath(), outputPath, listener);
        } else if (isRar(type)) {
            extractor = new RarExtractor(context, file.getPath(), outputPath, listener);
        } else if(isTar(type)) {
            extractor = new TarExtractor(context, file.getPath(), outputPath, listener);
        } else if(isGzippedTar(type)) {
            extractor = new GzipExtractor(context, file.getPath(), outputPath, listener);
        } else if(isBzippedTar(type)) {
            extractor = new Bzip2Extractor(context, file.getPath(), outputPath, listener);
        } else if(isXzippedTar(type)) {
            extractor = new XzExtractor(context, file.getPath(), outputPath, listener);
        } else if(isLzippedTar(type)) {
            extractor = new LzmaExtractor(context, file.getPath(), outputPath, listener);
        } else if(is7zip(type)) {
            extractor = new SevenZipExtractor(context, file.getPath(), outputPath, listener);
        } else {
            return null;
        }

        return extractor;
    }