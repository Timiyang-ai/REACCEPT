    private String expand(Capsule c, String s) {
        return Reflect.on(c).call("expand", s).get();
    }