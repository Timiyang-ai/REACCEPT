    @After
    public void destroy() {
        if (kernelImpl != null) {
            kernelImpl.destroy();
        }
        kernelImpl = null;
    }