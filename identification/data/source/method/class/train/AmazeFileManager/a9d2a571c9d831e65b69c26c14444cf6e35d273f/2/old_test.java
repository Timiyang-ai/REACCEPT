@Test
    public void getCompressorInstance() {
        File file = new File("/test/test.zip");//.zip used by ZipDecompressor
        Decompressor result = CompressedHelper.getCompressorInstance(context,file, null);
        assertEquals(result.getClass(),ZipDecompressor.class);
        file = new File("/test/test.jar");//.jar used by ZipDecompressor
        result = CompressedHelper.getCompressorInstance(context,file, null);
        assertEquals(result.getClass(),ZipDecompressor.class);
        file = new File("/test/test.apk");//.apk used by ZipDecompressor
        result = CompressedHelper.getCompressorInstance(context,file, null);
        assertEquals(result.getClass(),ZipDecompressor.class);
        file = new File("/test/test.tar");//.tar used by TarDecompressor
        result = CompressedHelper.getCompressorInstance(context,file, null);
        assertEquals(result.getClass(),TarDecompressor.class);
        file = new File("/test/test.tar.gz");//.tar.gz used by GzipDecompressor
        result = CompressedHelper.getCompressorInstance(context,file, null);
        assertEquals(result.getClass(),GzipDecompressor.class);
        file = new File("/test/test.rar");//.rar used by RarDecompressor
        result = CompressedHelper.getCompressorInstance(context,file, null);
        assertEquals(result.getClass(),RarDecompressor.class);
        file = new File("/test/test.7z");//Can't use 7zip
        result = CompressedHelper.getCompressorInstance(context,file, null);
        assertEquals(result.getClass(), SevenZipDecompressor.class);
    }