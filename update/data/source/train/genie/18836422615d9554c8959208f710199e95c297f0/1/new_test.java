@Test
    public void testGetS3SiteXmlsAsCsv() {
        cc.setS3CoreSiteXml("core");
        cc.setS3MapredSiteXml("mapred");
        cc.setS3HdfsSiteXml("hdfs");
        cc.setS3YarnSiteXml("yarn");
        Assert.assertEquals(cc.getS3SiteXmlsAsCsv(), "core,mapred,hdfs,yarn");
    }