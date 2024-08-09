public static void cleanup()
    {
        MessagingService.instance().outboundSink.clear();
        MessagingService.instance().inboundSink.clear();
    }