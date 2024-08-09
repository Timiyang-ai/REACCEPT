    @Test
    public void getExtractorInstance() {
        File file = new File("/test/test.zip");//.zip used by ZipExtractor
        Extractor result = CompressedHelper.getExtractorInstance(context, file,"/test2",emptyUpdateListener);
        assertEquals(result.getClass(),ZipExtractor.class);
        file = new File("/test/test.jar");//.jar used by ZipExtractor
        result = CompressedHelper.getExtractorInstance(context, file,"/test2",emptyUpdateListener);
        assertEquals(result.getClass(),ZipExtractor.class);
        file = new File("/test/test.apk");//.apk used by ZipExtractor
        result = CompressedHelper.getExtractorInstance(context, file,"/test2",emptyUpdateListener);
        assertEquals(result.getClass(),ZipExtractor.class);
        file = new File("/test/test.tar");//.tar used by TarExtractor
        result = CompressedHelper.getExtractorInstance(context, file,"/test2",emptyUpdateListener);
        assertEquals(result.getClass(),TarExtractor.class);
        file = new File("/test/test.tar.gz");//.tar.gz used by GzipExtractor
        result = CompressedHelper.getExtractorInstance(context, file,"/test2",emptyUpdateListener);
        assertEquals(result.getClass(), GzipExtractor.class);
        file = new File("/test/test.tgz");//.tgz used by GzipExtractor
        result = CompressedHelper.getExtractorInstance(context, file,"/test2",emptyUpdateListener);
        assertEquals(result.getClass(), GzipExtractor.class);
        file = new File("/test/test.rar");//.rar used by RarExtractor
        result = CompressedHelper.getExtractorInstance(context, file,"/test2",emptyUpdateListener);
        assertEquals(result.getClass(),RarExtractor.class);
        file = new File("/test/test.tar.bz2");//.rar used by RarExtractor
        result = CompressedHelper.getExtractorInstance(context, file,"/test2",emptyUpdateListener);
        assertEquals(result.getClass(), Bzip2Extractor.class);
        file = new File("/test/test.tbz");//.rar used by RarExtractor
        result = CompressedHelper.getExtractorInstance(context, file,"/test2",emptyUpdateListener);
        assertEquals(result.getClass(), Bzip2Extractor.class);
        file = new File("/test/test.7z");
        result = CompressedHelper.getExtractorInstance(context, file,"/test2",emptyUpdateListener);
        assertEquals(result.getClass(),SevenZipExtractor.class);
        file = new File("/test/test.tar.xz");
        result = CompressedHelper.getExtractorInstance(context, file,"/test2",emptyUpdateListener);
        assertEquals(result.getClass(), XzExtractor.class);
        file = new File("/test/test.tar.lzma");
        result = CompressedHelper.getExtractorInstance(context, file,"/test2",emptyUpdateListener);
        assertEquals(result.getClass(), LzmaExtractor.class);
    }