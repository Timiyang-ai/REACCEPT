boolean annotate(TextAnnotation ta) throws AnnotatorException {
        boolean addedViews = false;
        for (String view : rm.getCommaSeparatedValues(PreprocessorConfigurator.VIEWS_TO_ADD)) {
            if (ta.hasView(view))
                continue;
            annotator.addView(ta, view);
            addedViews = true;
        }
        return addedViews;
    }