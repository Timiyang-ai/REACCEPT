public void deregister(Invocation invocation) {
        long callId = invocation.op.getCallId();

        // if an invocation skipped registration (so call id is 0) we don't need to deregister it.
        if (callId == 0) {
            return;
        }

        invocations.remove(callId);
    }