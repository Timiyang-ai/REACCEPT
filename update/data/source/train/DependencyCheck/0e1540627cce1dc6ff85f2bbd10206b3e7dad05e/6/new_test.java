@Test
    public void testCall() throws Exception {
        NvdCveInfo cve = new NvdCveInfo();
        cve.setId("modified");
        cve.setNeedsUpdate(true);
        cve.setUrl(getSettings().getString(Settings.KEYS.CVE_MODIFIED_JSON));
        ExecutorService processExecutor = null;
        CveDB cveDB = null;
        DownloadTask instance = new DownloadTask(cve, processExecutor, cveDB, getSettings());
        Future<ProcessTask> result = instance.call();
        assertNull(result);
    }