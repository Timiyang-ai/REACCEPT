synchronized void error(CombineWatcher<R, ?> w, Exception e) {
            watcher.onError(e);
            /* tell ourselves to stop processing onNext events, event if the watchers don't obey the unsubscribe we're about to send */
            running.set(false);
            /* tell all watchers to unsubscribe since we had an error */
            stop();
        }