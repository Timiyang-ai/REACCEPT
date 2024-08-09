public void deregister(Invocation invocation) {
        if (!deactivate(invocation.op)) {
            return;
        }
        invocations.remove(invocation.op.getCallId());
        callIdSequence.complete();
    }