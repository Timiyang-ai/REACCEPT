  @Test
  public void interceptCall_basic() {
    Context origContext = Context.current();
    final Object message = new Object();
    final List<Integer> methodCalls = new ArrayList<>();
    final ServerCall.Listener<Object> listener = new ServerCall.Listener<Object>() {
      @Override public void onMessage(Object messageIn) {
        assertSame(message, messageIn);
        assertSame(uniqueContext, Context.current());
        methodCalls.add(1);
      }

      @Override public void onHalfClose() {
        assertSame(uniqueContext, Context.current());
        methodCalls.add(2);
      }

      @Override public void onCancel() {
        assertSame(uniqueContext, Context.current());
        methodCalls.add(3);
      }

      @Override public void onComplete() {
        assertSame(uniqueContext, Context.current());
        methodCalls.add(4);
      }

      @Override public void onReady() {
        assertSame(uniqueContext, Context.current());
        methodCalls.add(5);
      }
    };
    ServerCall.Listener<Object> wrapped = interceptCall(uniqueContext, call, headers,
        new ServerCallHandler<Object, Object>() {
          @Override
          public ServerCall.Listener<Object> startCall(
              ServerCall<Object, Object> call, Metadata headers) {
            assertSame(ContextsTest.this.call, call);
            assertSame(ContextsTest.this.headers, headers);
            assertSame(uniqueContext, Context.current());
            return listener;
          }
        });
    assertSame(origContext, Context.current());

    wrapped.onMessage(message);
    wrapped.onHalfClose();
    wrapped.onCancel();
    wrapped.onComplete();
    wrapped.onReady();
    assertEquals(Arrays.asList(1, 2, 3, 4, 5), methodCalls);
    assertSame(origContext, Context.current());
  }