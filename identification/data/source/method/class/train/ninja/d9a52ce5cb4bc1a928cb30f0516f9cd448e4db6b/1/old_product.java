static public Class<? extends Standalone> resolveStandaloneClass() {
        String standaloneClassName = System.getProperty(Standalone.KEY_NINJA_STANDALONE_CLASS, Standalone.DEFAULT_STANDALONE_CLASS);
        try {
            return (Class<Standalone>)Class.forName(standaloneClassName);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find standalone class '" + standaloneClassName + "' (class does not exist)");
        }
    }