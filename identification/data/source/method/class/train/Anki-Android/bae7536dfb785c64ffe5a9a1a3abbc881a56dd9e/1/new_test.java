    public String typeAnsAnswerFilter(String buf, String userAnswer, String correctAnswer) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        NonabstractFlashcardViewer nafv = new NonabstractFlashcardViewer();

        Class[] argClasses = {String.class, String.class, String.class};
        Method method = AbstractFlashcardViewer.class.getDeclaredMethod("typeAnsAnswerFilter", argClasses);
        method.setAccessible(true);
        return (String) method.invoke(nafv, buf, userAnswer, correctAnswer);
    }