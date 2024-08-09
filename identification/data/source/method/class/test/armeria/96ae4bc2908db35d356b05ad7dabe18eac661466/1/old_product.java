@Override
    public final void close() {
        final CompletableFuture<Void> f;
        synchronized (this) {
            if (state == State.STOPPED) {
                return;
            }
            f = stop();
        }

        boolean interrupted = false;
        for (;;) {
            try {
                f.get();
                break;
            } catch (InterruptedException ignored) {
                interrupted = true;
            } catch (ExecutionException e) {
                closeFailed(Exceptions.peel(e));
                break;
            }
        }

        if (interrupted) {
            Thread.currentThread().interrupt();
        }
    }