public void deleteFeedbackQuestion(String feedbackQuestionId) {
        Key<FeedbackQuestion> feedbackQuestionKey = makeKeyOrNullFromWebSafeString(feedbackQuestionId);
        if (feedbackQuestionKey != null) {
            deleteEntity(feedbackQuestionKey);
        }
    }