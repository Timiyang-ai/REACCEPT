public boolean register(Invocation invocation) {
        assert invocation.op.getCallId() == 0 : "can't register twice: " + invocation;

        long callId = callIdSequence.next(invocation);
        setCallId(invocation.op, callId);

        invocations.put(callId, invocation);

        if (!alive) {
            invocation.notifyError(new HazelcastInstanceNotActiveException());
            return false;
        }
        return true;
    }