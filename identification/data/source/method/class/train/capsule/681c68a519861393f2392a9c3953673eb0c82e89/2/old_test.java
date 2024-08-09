    private static boolean runActions(Object capsule, List<String> args) {
        return Reflect.on(capsule.getClass()).call("runActions", capsule, args).get();
    }