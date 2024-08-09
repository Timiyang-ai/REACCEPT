public void cancelFirmwareUpdate(final ThingUID thingUID) {
        Preconditions.checkNotNull(thingUID, "Thing UID must not be null.");
        final FirmwareUpdateHandler firmwareUpdateHandler = getFirmwareUpdateHandler(thingUID);
        if (firmwareUpdateHandler == null) {
            throw new IllegalArgumentException(
                    String.format("There is no firmware update handler for thing with UID %s.", thingUID));
        }
        final ProgressCallbackImpl progressCallback = getProgressCallback(thingUID);

        logger.debug("Cancelling firmware update for thing with UID {}.", thingUID);
        safeCaller.create(firmwareUpdateHandler).withTimeout(timeout).withAsync().onTimeout(() -> {
            logger.error("Timeout occurred while cancelling firmware update of thing with UID {}.", thingUID);
            progressCallback.failedInternal("timeout-error-during-cancel");
        }).onException(e -> {
            logger.error("Unexpected exception occurred while cancelling firmware update of thing with UID {}.",
                    thingUID, e.getCause());
            progressCallback.failedInternal("unexpected-handler-error-during-cancel");
        }).build().cancel();
    }