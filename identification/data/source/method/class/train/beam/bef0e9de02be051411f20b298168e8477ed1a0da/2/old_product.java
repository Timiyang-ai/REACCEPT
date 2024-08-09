public void processTimestampedElement(TimestampedValue<InputT> element) throws Exception {
    checkNotNull(element, "Timestamped element cannot be null");
    if (state != State.BUNDLE_STARTED) {
      startBundle();
    }
    try {
      final TestProcessContext processContext = createProcessContext(element);
      fnInvoker.invokeProcessElement(new DoFnInvoker.FakeArgumentProvider<InputT, OutputT>() {
        @Override
        public DoFn<InputT, OutputT>.ProcessContext processContext(DoFn<InputT, OutputT> doFn) {
          return processContext;
        }
      });
    } catch (UserCodeException e) {
      unwrapUserCodeException(e);
    }
  }