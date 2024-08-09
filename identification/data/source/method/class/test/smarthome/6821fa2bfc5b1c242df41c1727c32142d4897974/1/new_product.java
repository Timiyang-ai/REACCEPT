public void cancelFirmwareUpdate(final ThingUID thingUID) {
        Preconditions.checkNotNull(thingUID, "Thing UID must not be null.");
        final FirmwareUpdateHandler firmwareUpdateHandler = getFirmwareUpdateHandler(thingUID);
        if (firmwareUpdateHandler == null) {
            throw new IllegalArgumentException(
                    String.format("There is no firmware update handler for thing with UID %s.", thingUID));
        }
        final ProgressCallbackImpl progressCallback = getProgressCallback(thingUID);
        getPool().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    SafeMethodCaller.call(new SafeMethodCaller.ActionWithException<Void>() {
                        @Override
                        public Void call() {
                            logger.debug("Canceling firmware update for thing with UID {}.", thingUID);
                            firmwareUpdateHandler.cancel();
                            return null;
                        }
                    });
                } catch (ExecutionException e) {
                    logger.error("Unexpected exception occurred while canceling firmware update of thing with UID {}.",
                            thingUID, e.getCause());
                    progressCallback.failedInternal("unexpected-handler-error-during-cancel");
                } catch (TimeoutException e) {
                    logger.error("Timeout occurred while canceling firmware update of thing with UID {}.", thingUID, e);
                    progressCallback.failedInternal("timeout-error-during-cancel");
                }
            }
        });
    }