public void deleteFeedbackQuestion(String feedbackQuestionId) {
        makeKeyFromWebSafeString(feedbackQuestionId).ifPresent(this::deleteEntity);
    }