public void deregister(Invocation invocation) {
        long oldCallId = resetCallId(invocation.op);
        if (oldCallId == 0) {
            return;
        }
        invocations.remove(oldCallId);
        callIdSequence.complete();
    }