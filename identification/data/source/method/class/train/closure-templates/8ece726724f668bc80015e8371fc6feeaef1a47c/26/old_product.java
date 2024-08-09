EvalVisitor create(
        Environment env,
        @Nullable SoyRecord ijData,
        @Nullable SoyCssRenamingMap cssRenamingMap,
        @Nullable SoyIdRenamingMap xidRenamingMap,
        @Nullable SoyMsgBundle msgBundle,
        boolean debugSoyTemplateInfo,
        ImmutableMap<String, Supplier<Object>> pluginInstances);