@Test
    public void testProcess() throws MalformedURLException {
        System.out.println("process");
        AnchorModifier instance = new AnchorModifier();
        JMeterContext context = JMeterContextService.getContext();
        Sampler s = new HTTPSampler();
        HTTPSampleResult res = new HTTPSampleResult();
        res.setURL(new URL("http://test/"));
        String data = "<a href='http://test'/><a href='http://test/2'/><a href='http://test'/>";
        data += "<a href='testsub'/><a href='http://test/2'/><a href='http://test'/>";
        res.setResponseData(data.getBytes());
        context.setPreviousResult(res);
        context.setCurrentSampler(s);
        instance.process();
        instance.process();
        instance.process();
    }