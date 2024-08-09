public void register(Invocation invocation) {
        if (skipRegistration(invocation)) {
            return;
        }

        long callId = callIdGen.incrementAndGet();

        Operation op = invocation.op;
        if (op.getCallId() != 0) {
            invocations.remove(op.getCallId());
        }

        invocations.put(callId, invocation);
        setCallId(invocation.op, callId);
    }