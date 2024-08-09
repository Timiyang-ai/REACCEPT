@Test
    public void testDownloadFileFromServer() throws Exception {

        final RestfulMgr restfulMgr = new RestfulMgrMock().getMockInstance();

        FetcherMgr fetcherMgr = new FetcherMgrImpl(restfulMgr, 3, 5, true, "", new ArrayList<String>());

        try {

            String valueString = fetcherMgr.downloadFileFromServer(requestUrl, RestfulMgrMock.defaultFileName);
            Assert.assertEquals(RestfulMgrMock.defaultFileName, valueString);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }