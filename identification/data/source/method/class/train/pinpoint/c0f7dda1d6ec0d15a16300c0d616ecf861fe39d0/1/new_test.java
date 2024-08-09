    @Test
    public void queueToWasFilter() {
        final ServiceType tomcat = serviceTypeRegistryService.findServiceTypeByName(TOMCAT_TYPE_NAME);
        final ServiceType messageQueue = serviceTypeRegistryService.findServiceTypeByName(MESSAGE_QUEUE_TYPE_NAME);

        final String messageQueueA = "QUEUE_A";
        final String messageQueueB = "QUEUE_B";

        FilterDescriptor descriptor = new FilterDescriptor();
        descriptor.setFromApplicationName(messageQueueA);
        descriptor.setFromServiceType(messageQueue.getName());
        descriptor.setToApplicationName("APP_A");
        descriptor.setToServiceType(tomcat.getName());

        FilterHint hint = new FilterHint(Collections.emptyList());

        LinkFilter linkFilter = newLinkFilter(descriptor, hint);
        logger.debug(linkFilter.toString());

        SpanBo matchingSpan = new SpanBo();
        matchingSpan.setApplicationId("APP_A");
        matchingSpan.setApplicationServiceType(tomcat.getCode());
        matchingSpan.setAcceptorHost(messageQueueA);
        Assert.assertTrue(linkFilter.include(Collections.singletonList(matchingSpan)));

        SpanBo unmatchingSpan = new SpanBo();
        unmatchingSpan.setApplicationId("APP_A");
        unmatchingSpan.setApplicationServiceType(tomcat.getCode());
        unmatchingSpan.setAcceptorHost(messageQueueB);
        Assert.assertFalse(linkFilter.include(Collections.singletonList(unmatchingSpan)));
    }