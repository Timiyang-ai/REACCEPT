@Test
    public void testFetchFile() throws Exception {

//        Settings.setString(Settings.KEYS.CONNECTION_TIMEOUT, "1000");
//        Settings.setString(Settings.KEYS.PROXY_PORT, "8080");
//        Settings.setString(Settings.KEYS.PROXY_SERVER, "127.0.0.1");
        URL url = new URL(getSettings().getString(Settings.KEYS.CVE_MODIFIED_20_URL));
        File outputPath = new File("target/downloaded_cve.xml");
        Downloader downloader = new Downloader(getSettings());
        downloader.fetchFile(url, outputPath);
        assertTrue(outputPath.isFile());
    }