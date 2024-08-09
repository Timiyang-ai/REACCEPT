public static Extractor getExtractorInstance(Context context, File file, String outputPath,
                                                 Extractor.OnUpdate listener) {
        Extractor extractor;
        String type = file.getPath().substring(file.getPath().lastIndexOf('.')+1, file.getPath().length()).toLowerCase();

        if (isZip(type)) {
            extractor = new ZipExtractor(context, file.getPath(), outputPath, listener);
        } else if (isRar(type)) {
            extractor = new RarExtractor(context, file.getPath(), outputPath, listener);
        } else if(isTar(type)) {
            extractor = new TarExtractor(context, file.getPath(), outputPath, listener);
        } else {
            return null;
        }

        return extractor;
    }