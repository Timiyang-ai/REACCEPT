public final Task<Void> whenComplete(Scheduler scheduler, FinalizedCallback action) {
        return new Task<Void>() {
            {
                setSignificance(TaskSignificance.MODERATE);
            }

            @Override
            public Scheduler getScheduler() {
                return scheduler;
            }

            @Override
            public void execute() throws Exception {
                if (isDependentsSucceeded() != (Task.this.getException() == null))
                    throw new AssertionError("When dependents succeeded, Task.exception must be nonnull.");

                action.execute(Task.this.getException());

                if (!isDependentsSucceeded()) {
                    setSignificance(TaskSignificance.MINOR);
                    if (Task.this.getException() == null)
                        throw new CancellationException();
                    else
                        throw Task.this.getException();
                }
            }

            @Override
            public Collection<Task<?>> getDependents() {
                return Collections.singleton(Task.this);
            }

            @Override
            public boolean isRelyingOnDependents() {
                return false;
            }
        }.setName(getCaller());
    }