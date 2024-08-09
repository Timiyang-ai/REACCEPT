    @Test(expected = IllegalArgumentException.class)
    public void cancelFirmwareUpdate_noFirmwareUpdateHandler() {
        firmwareUpdateService.cancelFirmwareUpdate(new ThingUID("dummy:thing:withoutHandler"));
    }