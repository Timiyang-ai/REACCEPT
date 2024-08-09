static List<Annotation> getAnnotations(AnnotatedElement element, EnumSet<FindOption> findOptions) {
        return getAnnotations(element, findOptions, annotation -> true);
    }