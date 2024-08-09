public boolean register(Invocation invocation) {
        final long callId;
        try {
            callId = callIdSequence.next(invocation.op.isUrgent());
        } catch (TimeoutException e) {
            throw new HazelcastOverloadException("Failed to start invocation due to overload: " + invocation, e);
        }
        try {
            // Fails with IllegalStateException if the operation is already active
            setCallId(invocation.op, callId);
        } catch (IllegalStateException e) {
            callIdSequence.complete();
            throw e;
        }
        invocations.put(callId, invocation);
        if (!alive) {
            invocation.notifyError(new HazelcastInstanceNotActiveException());
            return false;
        }
        return true;
    }