static List<Annotation> getAnnotations(AnnotatedElement element, EnumSet<FindOption> findOptions,
                                           Predicate<Annotation> collectingFilter) {
        requireNonNull(element, "element");
        requireNonNull(collectingFilter, "collectingFilter");

        final Builder<Annotation> builder = new Builder<>();

        for (final AnnotatedElement e : resolveTargetElements(element, findOptions)) {
            for (final Annotation annotation : e.getDeclaredAnnotations()) {
                if (findOptions.contains(FindOption.LOOKUP_META_ANNOTATIONS)) {
                    final Annotation[] metaAnnotations = annotation.annotationType().getDeclaredAnnotations();
                    for (final Annotation metaAnnotation : metaAnnotations) {
                        if (collectingFilter.test(metaAnnotation)) {
                            builder.add(metaAnnotation);
                        }
                    }
                }
                if (collectingFilter.test(annotation)) {
                    builder.add(annotation);
                }
            }
        }
        return builder.build();
    }