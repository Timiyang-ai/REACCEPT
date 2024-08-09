@Test
    public void testReleaseConnection() throws Exception {

        this.connManager.setMaxTotal(1);

        final HttpHost target = start();
        final HttpRoute route = new HttpRoute(target, null, false);
        final int      rsplen = 8;
        final String      uri = "/random/" + rsplen;

        final ClassicHttpRequest request = new BasicClassicHttpRequest("GET", uri);
        final HttpContext context = new BasicHttpContext();

        HttpClientConnection conn = getConnection(this.connManager, route);
        this.connManager.connect(conn, route, 0, context);
        this.connManager.routeComplete(conn, route, context);

        context.setAttribute(HttpCoreContext.HTTP_CONNECTION, conn);

        final HttpProcessor httpProcessor = new DefaultHttpProcessor(
                new RequestTargetHost(), new RequestContent(), new RequestConnControl());

        final HttpRequestExecutor exec = new HttpRequestExecutor();
        exec.preProcess(request, httpProcessor, context);
        ClassicHttpResponse response = exec.execute(request, conn, context);

        Assert.assertEquals("wrong status in first response",
                     HttpStatus.SC_OK,
                     response.getCode());
        byte[] data = EntityUtils.toByteArray(response.getEntity());
        Assert.assertEquals("wrong length of first response entity",
                     rsplen, data.length);
        // ignore data, but it must be read

        // check that there is no auto-release by default
        try {
            // this should fail quickly, connection has not been released
            getConnection(this.connManager, route, 10L, TimeUnit.MILLISECONDS);
            Assert.fail("ConnectionPoolTimeoutException should have been thrown");
        } catch (final ConnectionPoolTimeoutException e) {
            // expected
        }

        conn.close();
        this.connManager.releaseConnection(conn, null, -1, null);
        conn = getConnection(this.connManager, route);
        Assert.assertFalse("connection should have been closed", conn.isOpen());

        this.connManager.connect(conn, route, 0, context);
        this.connManager.routeComplete(conn, route, context);

        // repeat the communication, no need to prepare the request again
        context.setAttribute(HttpCoreContext.HTTP_CONNECTION, conn);
        response = exec.execute(request, conn, context);

        Assert.assertEquals("wrong status in second response",
                     HttpStatus.SC_OK,
                     response.getCode());
        data = EntityUtils.toByteArray(response.getEntity());
        Assert.assertEquals("wrong length of second response entity",
                     rsplen, data.length);
        // ignore data, but it must be read

        // release connection after marking it for re-use
        // expect the next connection obtained to be open
        this.connManager.releaseConnection(conn, null, -1, null);
        conn = getConnection(this.connManager, route);
        Assert.assertTrue("connection should have been open", conn.isOpen());

        // repeat the communication, no need to prepare the request again
        context.setAttribute(HttpCoreContext.HTTP_CONNECTION, conn);
        response = exec.execute(request, conn, context);

        Assert.assertEquals("wrong status in third response",
                     HttpStatus.SC_OK,
                     response.getCode());
        data = EntityUtils.toByteArray(response.getEntity());
        Assert.assertEquals("wrong length of third response entity",
                     rsplen, data.length);
        // ignore data, but it must be read

        this.connManager.releaseConnection(conn, null, -1, null);
        this.connManager.shutdown();
    }