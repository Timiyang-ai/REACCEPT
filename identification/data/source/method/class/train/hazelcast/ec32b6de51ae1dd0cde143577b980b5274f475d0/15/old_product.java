public boolean register(Invocation invocation) {
        long callId = callIdSequence.next(invocation);
        // Fails if the operation is already active
        setCallId(invocation.op, callId);
        invocations.put(callId, invocation);
        if (!alive) {
            invocation.notifyError(new HazelcastInstanceNotActiveException());
            return false;
        }
        return true;
    }