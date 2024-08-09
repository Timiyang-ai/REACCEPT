static List<Annotation> getAnnotations(AnnotatedElement element, EnumSet<FindOption> findOptions) {
        // A user may not be interested in the built-in meta annotations, so the default predicate filters out
        // the default Java meta-annotations.
        return getAnnotations(element, findOptions,
                              annotation -> !BUILT_IN_META_ANNOTATIONS.contains(annotation.annotationType()));
    }