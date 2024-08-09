public final <T extends ActorRef<M>, M> T getChild(Object id) throws SuspendExecution, InterruptedException {
        if (isInActor())
            return (T) SupervisorActor.currentSupervisor().getChild(id);

        return (T) call(this, new GetChildMessage(RequestReplyHelper.from(), null, id));
    }