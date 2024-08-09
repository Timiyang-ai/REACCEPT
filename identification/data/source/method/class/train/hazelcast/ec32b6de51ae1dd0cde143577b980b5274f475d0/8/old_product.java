public void deregister(Invocation invocation) {
        long callId = invocation.op.getCallId();

        callIdSequence.complete(invocation);

        setCallId(invocation.op, 0);

        if (callId == 0 || callId == CALL_ID_LOCAL_SKIPPED) {
            return;
        }

        boolean deleted = invocations.remove(callId) != null;
        if (!deleted && logger.isFinestEnabled()) {
            logger.finest("failed to deregister callId: " + callId + " " + invocation);
        }
    }