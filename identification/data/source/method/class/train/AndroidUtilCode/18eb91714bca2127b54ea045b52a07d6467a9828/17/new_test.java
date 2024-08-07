    @Test
    public void getImageType() {
        Assert.assertEquals(ImageUtils.ImageType.TYPE_JPG, ImageUtils.getImageType(TestConfig.PATH_IMAGE + "ic_launcher.jpg"));
        Assert.assertEquals(ImageUtils.ImageType.TYPE_PNG, ImageUtils.getImageType(TestConfig.PATH_IMAGE + "ic_launcher.png"));
        Assert.assertEquals(ImageUtils.ImageType.TYPE_GIF, ImageUtils.getImageType(TestConfig.PATH_IMAGE + "ic_launcher.gif"));
        Assert.assertEquals(ImageUtils.ImageType.TYPE_TIFF, ImageUtils.getImageType(TestConfig.PATH_IMAGE + "ic_launcher.tif"));
        Assert.assertEquals(ImageUtils.ImageType.TYPE_BMP, ImageUtils.getImageType(TestConfig.PATH_IMAGE + "ic_launcher.bmp"));
        Assert.assertEquals(ImageUtils.ImageType.TYPE_WEBP, ImageUtils.getImageType(TestConfig.PATH_IMAGE + "ic_launcher.webp"));
        Assert.assertEquals(ImageUtils.ImageType.TYPE_ICO, ImageUtils.getImageType(TestConfig.PATH_IMAGE + "ic_launcher.ico"));
    }