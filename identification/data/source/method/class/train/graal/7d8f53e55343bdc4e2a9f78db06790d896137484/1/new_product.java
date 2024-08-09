public static <T> void compilationConstant(Object value) {
        if (!CompilerDirectives.isCompilationConstant(value)) {
            neverPartOfCompilation("Value is not compilation constant");
        }
    }