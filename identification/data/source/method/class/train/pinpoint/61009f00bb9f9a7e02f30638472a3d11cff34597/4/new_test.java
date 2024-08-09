    @Test
    public void wasToBackendFilter() {
        final ServiceType tomcat = serviceTypeRegistryService.findServiceTypeByName(TOMCAT_TYPE_NAME);
        final ServiceType backend = serviceTypeRegistryService.findServiceTypeByName(BACKEND_TYPE_NAME);

        final String destinationA = "BACKEND_A";
        final String destinationB = "BACKEND_B";

        FilterDescriptor descriptor = new FilterDescriptor();
        descriptor.setFromApplicationName("APP_A");
        descriptor.setFromServiceType(tomcat.getName());
        descriptor.setToApplicationName(destinationA);
        descriptor.setToServiceType(backend.getName());

        FilterHint hint = new FilterHint(Collections.emptyList());

        LinkFilter linkFilter = newLinkFilter(descriptor, hint);
        logger.debug(linkFilter.toString());

        SpanBo matchingSpan = new SpanBo();
        matchingSpan.setApplicationId("APP_A");
        matchingSpan.setApplicationServiceType(tomcat.getCode());
        SpanEventBo spanEventDestinationA = new SpanEventBo();
        spanEventDestinationA.setDestinationId(destinationA);
        spanEventDestinationA.setServiceType(BACKEND_TYPE_CODE);
        matchingSpan.addSpanEvent(spanEventDestinationA);
        Assert.assertTrue(linkFilter.include(Collections.singletonList(matchingSpan)));

        SpanBo unmatchingSpan = new SpanBo();
        unmatchingSpan.setApplicationId("APP_A");
        unmatchingSpan.setApplicationServiceType(tomcat.getCode());
        SpanEventBo spanEventDestinationB = new SpanEventBo();
        spanEventDestinationB.setDestinationId(destinationB);
        spanEventDestinationB.setServiceType(BACKEND_TYPE_CODE);
        unmatchingSpan.addSpanEvent(spanEventDestinationB);
        Assert.assertFalse(linkFilter.include(Collections.singletonList(unmatchingSpan)));

        Assert.assertTrue(linkFilter.include(Arrays.asList(matchingSpan, unmatchingSpan)));

        SpanBo bothSpan = new SpanBo();
        bothSpan.setApplicationId("APP_A");
        bothSpan.setApplicationServiceType(tomcat.getCode());
        bothSpan.addSpanEventBoList(Arrays.asList(spanEventDestinationA, spanEventDestinationB));
        Assert.assertTrue(linkFilter.include(Collections.singletonList(bothSpan)));
    }