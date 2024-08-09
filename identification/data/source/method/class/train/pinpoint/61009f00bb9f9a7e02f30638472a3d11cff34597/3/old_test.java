    @Test
    public void wasToWasFilter_perfectMatch() {
        final ServiceType tomcat = serviceTypeRegistryService.findServiceTypeByName(TOMCAT_TYPE_NAME);

        FilterDescriptor descriptor = new FilterDescriptor();
        descriptor.setFromApplicationName("APP_A");
        descriptor.setFromServiceType(tomcat.getName());
        descriptor.setToApplicationName("APP_B");
        descriptor.setToServiceType(tomcat.getName());

        FilterHint hint = new FilterHint(Collections.emptyList());

        LinkFilter linkFilter = newLinkFilter(descriptor, hint);
        logger.debug(linkFilter.toString());

        // Accept - perfect match
        SpanBo user_appA = new SpanBo();
        user_appA.setSpanId(1);
        user_appA.setParentSpanId(-1);
        user_appA.setApplicationId("APP_A");
        user_appA.setApplicationServiceType(tomcat.getCode());
        SpanBo appA_appB = new SpanBo();
        appA_appB.setSpanId(2);
        appA_appB.setParentSpanId(1);
        appA_appB.setApplicationId("APP_B");
        appA_appB.setApplicationServiceType(tomcat.getCode());
        Assert.assertTrue(linkFilter.include(Arrays.asList(user_appA, appA_appB)));
    }