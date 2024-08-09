@Test
    public void testCall() throws Exception {
        NvdCveInfo cve = new NvdCveInfo();
        cve.setId("modified");
        cve.setNeedsUpdate(true);
        cve.setUrl(getSettings().getString(Settings.KEYS.CVE_MODIFIED_20_URL));
        cve.setOldSchemaVersionUrl(getSettings().getString(Settings.KEYS.CVE_MODIFIED_12_URL));
        ExecutorService processExecutor = null;
        CveDB cveDB = null;
        DownloadTask instance = new DownloadTask(cve, processExecutor, cveDB, getSettings());
        Future<ProcessTask> result = instance.call();
        assertNull(result);
    }