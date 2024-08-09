EvalVisitor create(
        Environment env,
        @Nullable SoyCssRenamingMap cssRenamingMap,
        @Nullable SoyIdRenamingMap xidRenamingMap,
        @Nullable SoyMsgBundle msgBundle,
        boolean debugSoyTemplateInfo,
        ImmutableMap<String, Supplier<Object>> pluginInstances);