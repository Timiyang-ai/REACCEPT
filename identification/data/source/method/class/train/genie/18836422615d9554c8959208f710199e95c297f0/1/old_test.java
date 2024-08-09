@Test
    public void testGetS3SiteXmlsAsCsv() {
        cc.setS3CoreSiteXml("core");
        cc.setS3MapredSiteXml("mapred");
        cc.setS3HdfsSiteXml("hdfs");
        Assert.assertEquals(cc.getS3SiteXmlsAsCsv(), "core,mapred,hdfs");
    }