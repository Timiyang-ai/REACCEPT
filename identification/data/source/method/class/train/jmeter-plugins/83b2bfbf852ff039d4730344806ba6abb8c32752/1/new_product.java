public static void main(String[] args) {
        Thread.currentThread().setContextClassLoader(loader);
        try {
            Class<?> initialClass;
            initialClass = loader.loadClass("kg.apc.jmeter.PluginsCMD");// $NON-NLS-1$
            Object instance = initialClass.newInstance();
            Method startup = initialClass.getMethod("processParams", new Class[]{(new String[0]).getClass()});// $NON-NLS-1$
            Object res=startup.invoke(instance, new Object[]{args});
            int rc=(Integer) res;
            if (rc!=0) System.exit(rc);
        } catch (Throwable e) {
            System.err.println("JMeter home directory was detected as: " + jmDir);
            throw new RuntimeException(e);
        }
    }