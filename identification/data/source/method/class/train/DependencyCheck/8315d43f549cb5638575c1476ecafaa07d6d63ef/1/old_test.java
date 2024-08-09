@Test
    public void testFetchFile_URL_String() throws Exception {
        System.out.println("fetchFile");

//        Settings.setString(Settings.KEYS.PROXY_URL, "test");
//        Settings.setString(Settings.KEYS.PROXY_PORT, "80");
//        Settings.setString(Settings.KEYS.CONNECTION_TIMEOUT, "1000");

//        Settings.setString(Settings.KEYS.PROXY_PORT, "8080");
//        Settings.setString(Settings.KEYS.PROXY_URL, "127.0.0.1");

        //URL url = new URL(Settings.getString(Settings.KEYS.CPE_URL));
        URL url = new URL("http://static.nvd.nist.gov/feeds/xml/cve/nvdcve-2.0-modified.xml");

        String outputPath = "target\\downloaded_cpe.xml";
        Downloader.fetchFile(url, outputPath);
    }