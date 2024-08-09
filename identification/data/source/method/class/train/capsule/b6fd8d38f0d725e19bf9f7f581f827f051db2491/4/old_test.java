    private Capsule setTarget(Capsule capsule, String artifact) {
        return Reflect.on(capsule).call("setTarget", artifact).get();
    }