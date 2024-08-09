static List<Annotation> getAnnotations(AnnotatedElement element, EnumSet<FindOption> findOptions,
                                           Predicate<Annotation> collectingFilter) {
        requireNonNull(element, "element");
        requireNonNull(collectingFilter, "collectingFilter");

        final Builder<Annotation> builder = new Builder<>();

        for (final AnnotatedElement e : resolveTargetElements(element, findOptions)) {
            for (final Annotation annotation : e.getDeclaredAnnotations()) {
                if (findOptions.contains(FindOption.LOOKUP_META_ANNOTATIONS)) {
                    getMetaAnnotations(builder, annotation, collectingFilter);
                }
                if (collectingFilter.test(annotation)) {
                    builder.add(annotation);
                }
            }
        }
        return builder.build();
    }