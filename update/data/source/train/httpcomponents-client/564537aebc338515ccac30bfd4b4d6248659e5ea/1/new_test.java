@Test
    public void testReleaseConnection() throws Exception {

        this.connManager.setMaxTotal(1);

        final HttpHost target = start();
        final HttpRoute route = new HttpRoute(target, null, false);
        final int      rsplen = 8;
        final String      uri = "/random/" + rsplen;

        final ClassicHttpRequest request = new BasicClassicHttpRequest("GET", target, uri);
        final HttpContext context = new BasicHttpContext();

        final LeaseRequest leaseRequest1 = this.connManager.lease(route, null);
        final ConnectionEndpoint endpoint1 = leaseRequest1.get(0, TimeUnit.MILLISECONDS);

        this.connManager.connect(endpoint1, 0, TimeUnit.MILLISECONDS, context);

        final HttpProcessor httpProcessor = new DefaultHttpProcessor(
                new RequestTargetHost(), new RequestContent(), new RequestConnControl());

        final HttpRequestExecutor exec = new HttpRequestExecutor();
        exec.preProcess(request, httpProcessor, context);
        try (final ClassicHttpResponse response1 = endpoint1.execute(request, exec, context)) {
            Assert.assertEquals(HttpStatus.SC_OK, response1.getCode());
        }

        // check that there is no auto-release by default
        try {
            // this should fail quickly, connection has not been released
            final LeaseRequest leaseRequest2 = this.connManager.lease(route, null);
            leaseRequest2.get(10, TimeUnit.MILLISECONDS);
            Assert.fail("TimeoutException expected");
        } catch (final TimeoutException ex) {
            // expected
        }

        endpoint1.close();
        this.connManager.release(endpoint1, null, -1, null);
        final LeaseRequest leaseRequest2 = this.connManager.lease(route, null);
        final ConnectionEndpoint endpoint2 = leaseRequest2.get(0, TimeUnit.MILLISECONDS);
        Assert.assertFalse(endpoint2.isConnected());

        this.connManager.connect(endpoint2, 0, TimeUnit.MILLISECONDS, context);

        try (final ClassicHttpResponse response2 = endpoint2.execute(request, exec, context)) {
            Assert.assertEquals(HttpStatus.SC_OK, response2.getCode());
        }

        // release connection after marking it for re-use
        // expect the next connection obtained to be open
        this.connManager.release(endpoint2, null, -1, null);

        final LeaseRequest leaseRequest3 = this.connManager.lease(route, null);
        final ConnectionEndpoint endpoint3 = leaseRequest3.get(0, TimeUnit.MILLISECONDS);
        Assert.assertTrue(endpoint3.isConnected());

        // repeat the communication, no need to prepare the request again
        try (final ClassicHttpResponse response3 = endpoint3.execute(request, exec, context)) {
            Assert.assertEquals(HttpStatus.SC_OK, response3.getCode());
        }

        this.connManager.release(endpoint3, null, -1, null);
        this.connManager.close();
    }