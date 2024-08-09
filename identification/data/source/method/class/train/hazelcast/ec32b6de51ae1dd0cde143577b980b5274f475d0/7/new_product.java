public boolean deregister(Invocation invocation) {
        if (!deactivate(invocation.op)) {
            return false;
        }
        invocations.remove(invocation.op.getCallId());
        callIdSequence.complete();
        return true;
    }