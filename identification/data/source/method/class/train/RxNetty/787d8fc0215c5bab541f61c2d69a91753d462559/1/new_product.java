void error(ZipObserver<R, ?> w, Exception e) {
            if (running.compareAndSet(true, false)) {
                // this thread succeeded in setting running=false so let's propagate the error
                observer.onError(e);
                /* since we receive an error we want to tell everyone to stop */
                stop();
            }
        }