public void destroy() {
        if (this.destroyed) {
            return;
        }
        synchronized (lock) {
            // stop the kernel
            try {
                stop();
            } catch (Exception e) {
                // oh well
            }
            // remove the mbean
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            try {
                ObjectName name = new ObjectName(mBeanName);
                if (mbs.isRegistered(name)) {
                    mbs.unregisterMBean(name);
                }
            } catch (Exception e) {
                // cannot use the logger here as it is already gone at this point
                log.error("INFO: Failed to unregister the MBean: " + mBeanName, e);
            }
            // trash the shutdown hook as we do not need it anymore
            if (this.shutdownHook != null) {
                try {
                    Runtime.getRuntime().removeShutdownHook(this.shutdownHook);
                    this.shutdownHook = null;
                } catch (Exception e) {
                    // ok, keep going
                }
            }
            this.destroyed = true;
        }
    }