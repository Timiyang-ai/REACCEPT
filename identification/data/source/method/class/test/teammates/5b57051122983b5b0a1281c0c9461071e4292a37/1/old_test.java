    private void deleteFeedbackQuestion(FeedbackQuestionAttributes attributes) {
        FeedbackQuestionAttributes fq = fqDb.getFeedbackQuestion(
                attributes.getFeedbackSessionName(), attributes.getCourseId(), attributes.getQuestionNumber());
        if (fq != null) {
            fqDb.deleteFeedbackQuestion(fq.getId());
        }
    }