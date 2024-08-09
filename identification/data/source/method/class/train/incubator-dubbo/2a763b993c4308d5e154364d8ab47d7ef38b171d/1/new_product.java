@Override
    public void destroy() {
        super.destroy();
        try {
            ExecutorUtil.cancelScheduledFuture(cleanFuture);
        } catch (Throwable t) {
            logger.warn(t.getMessage(), t);
        }
        try {
            multicastSocket.leaveGroup(multicastAddress);
            multicastSocket.close();
        } catch (Throwable t) {
            logger.warn(t.getMessage(), t);
        }
        ExecutorUtil.gracefulShutdown(cleanExecutor, cleanPeriod);
    }