@Test
    public void testReleaseConnection() throws Exception {

        final PoolingHttpClientConnectionManager mgr = new PoolingHttpClientConnectionManager();
        mgr.setMaxTotal(1);

        final HttpHost target = getServerHttp();
        final HttpRoute route = new HttpRoute(target, null, false);
        final int      rsplen = 8;
        final String      uri = "/random/" + rsplen;

        final HttpRequest request = new BasicHttpRequest("GET", uri, HttpVersion.HTTP_1_1);
        final HttpContext context = new BasicHttpContext();

        HttpClientConnection conn = getConnection(mgr, route);
        mgr.connect(conn, route.getTargetHost(), route.getLocalAddress(), 0, context);

        context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
        context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, target);

        final HttpProcessor httpProcessor = new ImmutableHttpProcessor(
                new HttpRequestInterceptor[] { new RequestContent(), new RequestConnControl() });

        final HttpRequestExecutor exec = new HttpRequestExecutor();
        exec.preProcess(request, httpProcessor, context);
        HttpResponse response = exec.execute(request, conn, context);

        Assert.assertEquals("wrong status in first response",
                     HttpStatus.SC_OK,
                     response.getStatusLine().getStatusCode());
        byte[] data = EntityUtils.toByteArray(response.getEntity());
        Assert.assertEquals("wrong length of first response entity",
                     rsplen, data.length);
        // ignore data, but it must be read

        // check that there is no auto-release by default
        try {
            // this should fail quickly, connection has not been released
            getConnection(mgr, route, 10L, TimeUnit.MILLISECONDS);
            Assert.fail("ConnectionPoolTimeoutException should have been thrown");
        } catch (final ConnectionPoolTimeoutException e) {
            // expected
        }

        conn.close();
        mgr.releaseConnection(conn, null, -1, null);
        conn = getConnection(mgr, route);
        Assert.assertFalse("connection should have been closed", conn.isOpen());

        mgr.connect(conn, route.getTargetHost(), route.getLocalAddress(), 0, context);

        // repeat the communication, no need to prepare the request again
        context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
        response = exec.execute(request, conn, context);

        Assert.assertEquals("wrong status in second response",
                     HttpStatus.SC_OK,
                     response.getStatusLine().getStatusCode());
        data = EntityUtils.toByteArray(response.getEntity());
        Assert.assertEquals("wrong length of second response entity",
                     rsplen, data.length);
        // ignore data, but it must be read

        // release connection after marking it for re-use
        // expect the next connection obtained to be open
        mgr.releaseConnection(conn, null, -1, null);
        conn = getConnection(mgr, route);
        Assert.assertTrue("connection should have been open", conn.isOpen());

        // repeat the communication, no need to prepare the request again
        context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
        response = exec.execute(request, conn, context);

        Assert.assertEquals("wrong status in third response",
                     HttpStatus.SC_OK,
                     response.getStatusLine().getStatusCode());
        data = EntityUtils.toByteArray(response.getEntity());
        Assert.assertEquals("wrong length of third response entity",
                     rsplen, data.length);
        // ignore data, but it must be read

        mgr.releaseConnection(conn, null, -1, null);
        mgr.shutdown();
    }