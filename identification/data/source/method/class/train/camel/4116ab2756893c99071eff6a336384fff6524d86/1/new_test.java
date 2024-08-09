    @Test
    public void validateProperties() throws Exception {
        // valid
        EndpointValidationResult result = catalog.validateEndpointProperties("log:mylog");
        assertTrue(result.isSuccess());

        // unknown
        result = catalog.validateEndpointProperties("log:mylog?level=WARN&foo=bar");
        assertFalse(result.isSuccess());
        assertTrue(result.getUnknown().contains("foo"));
        assertEquals(1, result.getNumberOfErrors());

        // enum
        result = catalog.validateEndpointProperties("jms:unknown:myqueue");
        assertFalse(result.isSuccess());
        assertEquals("unknown", result.getInvalidEnum().get("destinationType"));
        assertEquals("queue", result.getDefaultValues().get("destinationType"));
        assertEquals(1, result.getNumberOfErrors());

        // reference okay
        result = catalog.validateEndpointProperties("jms:queue:myqueue?jmsKeyFormatStrategy=#key");
        assertTrue(result.isSuccess());
        assertEquals(0, result.getNumberOfErrors());

        // reference
        result = catalog.validateEndpointProperties("jms:queue:myqueue?jmsKeyFormatStrategy=foo");
        assertFalse(result.isSuccess());
        assertEquals("foo", result.getInvalidEnum().get("jmsKeyFormatStrategy"));
        assertEquals(1, result.getNumberOfErrors());

        // okay
        result = catalog.validateEndpointProperties("yammer:MESSAGES?accessToken=aaa&consumerKey=bbb&consumerSecret=ccc&useJson=true&initialDelay=500");
        assertTrue(result.isSuccess());

        // required / boolean / integer
        result = catalog.validateEndpointProperties("yammer:MESSAGES?accessToken=aaa&consumerKey=&useJson=no&initialDelay=five");
        assertFalse(result.isSuccess());
        assertEquals(4, result.getNumberOfErrors());
        assertTrue(result.getRequired().contains("consumerKey"));
        assertTrue(result.getRequired().contains("consumerSecret"));
        assertEquals("no", result.getInvalidBoolean().get("useJson"));
        assertEquals("five", result.getInvalidInteger().get("initialDelay"));

        // unknown component
        result = catalog.validateEndpointProperties("foo:bar?me=you");
        assertTrue(result.isSuccess());
        assertTrue(result.hasWarnings());
        assertTrue(result.getUnknownComponent().equals("foo"));
        assertEquals(0, result.getNumberOfErrors());
        assertEquals(1, result.getNumberOfWarnings());

        // invalid boolean but default value
        result = catalog.validateEndpointProperties("log:output?showAll=ggg");
        assertFalse(result.isSuccess());
        assertEquals("ggg", result.getInvalidBoolean().get("showAll"));
        assertEquals(1, result.getNumberOfErrors());

        // dataset
        result = catalog.validateEndpointProperties("dataset:foo?minRate=50");
        assertTrue(result.isSuccess());

        // time pattern
        result = catalog.validateEndpointProperties("timer://foo?fixedRate=true&delay=0&period=2s");
        assertTrue(result.isSuccess());

        // reference lookup
        result = catalog.validateEndpointProperties("timer://foo?fixedRate=#fixed&delay=#myDelay");
        assertTrue(result.isSuccess());

        // optional consumer. prefix
        result = catalog.validateEndpointProperties("file:inbox?consumer.delay=5000&consumer.greedy=true");
        assertTrue(result.isSuccess());

        // optional without consumer. prefix
        result = catalog.validateEndpointProperties("file:inbox?delay=5000&greedy=true");
        assertTrue(result.isSuccess());

        // mixed optional without consumer. prefix
        result = catalog.validateEndpointProperties("file:inbox?delay=5000&consumer.greedy=true");
        assertTrue(result.isSuccess());

        // prefix
        result = catalog.validateEndpointProperties("file:inbox?delay=5000&scheduler.foo=123&scheduler.bar=456");
        assertTrue(result.isSuccess());

        // stub
        result = catalog.validateEndpointProperties("stub:foo?me=123&you=456");
        assertTrue(result.isSuccess());

        // lenient on
        result = catalog.validateEndpointProperties("dataformat:string:marshal?foo=bar");
        assertTrue(result.isSuccess());

        // lenient off
        result = catalog.validateEndpointProperties("dataformat:string:marshal?foo=bar", true);
        assertFalse(result.isSuccess());
        assertTrue(result.getUnknown().contains("foo"));

        // lenient off consumer only
        result = catalog.validateEndpointProperties("netty-http:http://myserver?foo=bar", false, true, false);
        assertFalse(result.isSuccess());
        // consumer should still fail because we cannot use lenient option in consumer mode
        assertEquals("foo", result.getUnknown().iterator().next());
        assertNull(result.getLenient());
        // lenient off producer only
        result = catalog.validateEndpointProperties("netty-http:http://myserver?foo=bar", false, false, true);
        assertTrue(result.isSuccess());
        // foo is the lenient option
        assertEquals(1, result.getLenient().size());
        assertEquals("foo", result.getLenient().iterator().next());

        // lenient on consumer only
        result = catalog.validateEndpointProperties("netty-http:http://myserver?foo=bar", true, true, false);
        assertFalse(result.isSuccess());
        // consumer should still fail because we cannot use lenient option in consumer mode
        assertEquals("foo", result.getUnknown().iterator().next());
        assertNull(result.getLenient());
        // lenient on producer only
        result = catalog.validateEndpointProperties("netty-http:http://myserver?foo=bar", true, false, true);
        assertFalse(result.isSuccess());
        assertEquals("foo", result.getUnknown().iterator().next());
        assertNull(result.getLenient());

        // lenient on rss consumer only
        result = catalog.validateEndpointProperties("rss:file:src/test/data/rss20.xml?splitEntries=true&sortEntries=true&consumer.delay=50&foo=bar", false, true, false);
        assertTrue(result.isSuccess());
        assertEquals("foo", result.getLenient().iterator().next());

        // data format
        result = catalog.validateEndpointProperties("dataformat:zipdeflater:marshal?compressionLevel=2", true);
        assertTrue(result.isSuccess());

        // 2 slash after component name
        result = catalog.validateEndpointProperties("atmos://put?remotePath=/dummy.txt");
        assertTrue(result.isSuccess());

        // userinfo in authority with username and password
        result = catalog.validateEndpointProperties("ssh://karaf:karaf@localhost:8101");
        assertTrue(result.isSuccess());

        // userinfo in authority without password
        result = catalog.validateEndpointProperties("ssh://scott@localhost:8101?certResource=classpath:test_rsa&useFixedDelay=true&delay=5000&pollCommand=features:list%0A");
        assertTrue(result.isSuccess());

        // userinfo with both user and password and placeholder
        result = catalog.validateEndpointProperties("ssh://smx:smx@localhost:8181?timeout=3000");
        assertTrue(result.isSuccess());
        // and should also work when port is using a placeholder
        result = catalog.validateEndpointProperties("ssh://smx:smx@localhost:{{port}}?timeout=3000");
        assertTrue(result.isSuccess());

        // placeholder for a bunch of optional options
        result = catalog.validateEndpointProperties("aws-swf://activity?{{options}}");
        assertTrue(result.isSuccess());

        // incapable to parse
        result = catalog.validateEndpointProperties("{{getFtpUrl}}?recursive=true");
        assertTrue(result.isSuccess());
        assertTrue(result.hasWarnings());
        assertTrue(result.getIncapable() != null);
    }