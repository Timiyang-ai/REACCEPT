public InternalRunner create(Class<?> klass, Supplier<MockitoTestListener> listenerSupplier) throws InvocationTargetException {
        try {
            return new RunnerProvider().newInstance("org.mockito.internal.runners.SilentJUnitRunner", klass, listenerSupplier);
        } catch (InvocationTargetException e) {
            if (!hasTestMethods(klass)) {
                throw new MockitoException(
                    "\n" +
                    "\n" +
                    "No tests found in " + klass.getSimpleName() + "\n" +
                    "Haven't you forgot @Test annotation?\n"
                    , e);
            }
            throw e;
        } catch (Throwable t) {
            throw new MockitoException(
                    "\n" +
                    "\n" +
                    "MockitoRunner can only be used with JUnit 4.5 or higher.\n" +
                    "You can upgrade your JUnit version or write your own Runner (please consider contributing your runner to the Mockito community).\n" +
                    "Bear in mind that you can still enjoy all features of the framework without using runners (they are completely optional).\n" +
                    "If you get this error despite using JUnit 4.5 or higher then please report this error to the mockito mailing list.\n"
                    , t);
        }
    }