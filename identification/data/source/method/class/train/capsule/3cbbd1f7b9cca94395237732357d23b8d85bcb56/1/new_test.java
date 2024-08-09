    private static int main0(Class<?> clazz, String... args) {
        return Reflect.on(clazz).call("main0", (Object) args).get();
    }