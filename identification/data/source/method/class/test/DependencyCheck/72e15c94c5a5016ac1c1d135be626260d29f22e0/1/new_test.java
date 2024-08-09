@Test
    public void testFetchFile() throws Exception {

//        Settings.setString(Settings.KEYS.CONNECTION_TIMEOUT, "1000");

//        Settings.setString(Settings.KEYS.PROXY_PORT, "8080");
//        Settings.setString(Settings.KEYS.PROXY_URL, "127.0.0.1");

        URL url = new URL(Settings.getString(Settings.KEYS.CPE_URL));
        String outputPath = "target/downloaded_cpe.xml";
        Downloader.fetchFile(url, outputPath, true);

        url = new URL(Settings.getString(Settings.KEYS.CVE_MODIFIED_20_URL));
        outputPath = "target/downloaded_cve.xml";
        Downloader.fetchFile(url, outputPath, false);

    }