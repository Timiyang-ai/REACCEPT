    @Test
    public void userToWasFilter() {
        final ServiceType user = serviceTypeRegistryService.findServiceTypeByName(USER_TYPE_NAME);
        final ServiceType tomcat = serviceTypeRegistryService.findServiceTypeByName(TOMCAT_TYPE_NAME);

        FilterDescriptor descriptor = new FilterDescriptor();
        descriptor.setFromApplicationName("USER");
        descriptor.setFromServiceType(user.getName());
        descriptor.setToApplicationName("APP_A");
        descriptor.setToServiceType(tomcat.getName());

        FilterHint hint = new FilterHint(Collections.emptyList());

        LinkFilter linkFilter = newLinkFilter(descriptor, hint);
        logger.debug(linkFilter.toString());

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
        SpanBo appB_appA = new SpanBo();
        appB_appA.setSpanId(3);
        appB_appA.setParentSpanId(2);
        appB_appA.setApplicationId("APP_A");
        appB_appA.setApplicationServiceType(tomcat.getCode());

        Assert.assertTrue(linkFilter.include(Collections.singletonList(user_appA)));
        Assert.assertFalse(linkFilter.include(Collections.singletonList(appA_appB)));
        Assert.assertFalse(linkFilter.include(Collections.singletonList(appB_appA)));
        Assert.assertTrue(linkFilter.include(Arrays.asList(user_appA, appA_appB, appB_appA)));
    }