@Override
    public Integer getDeviceForCurrentThread() {
        long tid = Thread.currentThread().getId();
        return getDeviceForThread(tid);
    }