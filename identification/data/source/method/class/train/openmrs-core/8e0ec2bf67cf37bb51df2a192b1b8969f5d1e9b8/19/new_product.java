public Collection<ConceptAnswer> getAnswers(boolean includeRetired) {
        if (includeRetired) {
            return getAnswers();
        } else {
            return getAnswers().stream()
                    .filter(a -> !a.getAnswerConcept().getRetired())
                    .collect(Collectors.toSet());
        }
    }