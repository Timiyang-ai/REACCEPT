public <A> void publishEvent(final EventTranslatorOneArg<T, A> eventTranslator, A arg)
    {
        ringBuffer.publishEvent(eventTranslator, arg);
    }