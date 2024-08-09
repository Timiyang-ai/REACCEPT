public boolean register(Invocation invocation) {
        final long callId;
        boolean force = invocation.op.isUrgent() || invocation.isRetryCandidate();
        try {
            callId = force ? callIdSequence.forceNext() : callIdSequence.next();
        } catch (HazelcastOverloadException e) {
            throw new HazelcastOverloadException("Failed to start invocation due to overload: " + invocation, e);
        }
        try {
            // fails with IllegalStateException if the operation is already active
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