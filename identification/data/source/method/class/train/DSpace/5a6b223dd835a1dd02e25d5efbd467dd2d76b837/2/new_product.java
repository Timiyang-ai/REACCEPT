@Override
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

            // If this was the default kernel, clear it
            if (DSpaceKernelManager.getDefaultKernel() == this) {
                DSpaceKernelManager.setDefaultKernel(null);
            }

            try {
                // remove the mbean
                DSpaceKernelManager.unregisterMBean(mBeanName);
            } finally {
                // trash the shutdown hook as we do not need it anymore
                if (this.shutdownHook != null) {
                    try {
                        Runtime.getRuntime().removeShutdownHook(this.shutdownHook);
                        this.shutdownHook = null;
                    } catch (Exception e) {
                        // ok, keep going
                    }
                }
            }

            this.destroyed = true;
        }
    }