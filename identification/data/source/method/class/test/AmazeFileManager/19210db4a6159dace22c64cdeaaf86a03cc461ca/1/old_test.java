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
        file = new File("/test/test.rar");//.rar used by RarExtractor
        result = CompressedHelper.getExtractorInstance(context, file,"/test2",emptyUpdateListener);
        assertEquals(result.getClass(),RarExtractor.class);

        //null test
        file = new File("/test/test.7z");//Can't use 7zip
        result = CompressedHelper.getExtractorInstance(context, file,"/test2",emptyUpdateListener);
        assertNull(result);
    }