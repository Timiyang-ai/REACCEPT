@Override
    public void destroy() {
        super.destroy();
        try {
            if (cleanFuture != null) {
                cleanFuture.cancel(true);
            }
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