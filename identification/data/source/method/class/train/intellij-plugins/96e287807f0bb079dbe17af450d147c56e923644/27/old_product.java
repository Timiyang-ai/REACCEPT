public IJavaAnnotation getAnnotation(String annotationQualifiedName) {
        for (IJavaAnnotation annotation : getAnnotations())
            if (annotation.getFullyQualifiedName().equals(annotationQualifiedName)) {
                return annotation;
            }

        return null;
    }