synchronized void error(CombineObserver<R, ?> w, Exception e) {
            Observer.onError(e);
            /* tell ourselves to stop processing onNext events, event if the Observers don't obey the unsubscribe we're about to send */
            running.set(false);
            /* tell all Observers to unsubscribe since we had an error */
            stop();
        }