    private TestEvent publishEvent() throws InterruptedException, BrokenBarrierException
    {
        if (ringBuffer == null)
        {
            ringBuffer = disruptor.start();

            for (DelayedEventHandler eventHandler : delayedEventHandlers)
            {
                eventHandler.awaitStart();
            }
        }

        disruptor.publishEvent(
            new EventTranslator<TestEvent>()
            {
                @Override
                public void translateTo(final TestEvent event, final long sequence)
                {
                    lastPublishedEvent = event;
                }
            });

        return lastPublishedEvent;
    }