private String typeAnsAnswerFilter(String buf, String userAnswer, String correctAnswer) {
        Matcher m = sTypeAnsPat.matcher(buf);
        DiffEngine diffEngine = new DiffEngine();
        StringBuilder sb = new StringBuilder();
        sb.append("<div");
        sb.append("><code id=typeans>");
        if (!TextUtils.isEmpty(userAnswer)) {
            // The user did type something.
            if (userAnswer.equals(correctAnswer)) {
                // and it was right.
                sb.append(DiffEngine.wrapGood(correctAnswer));
                sb.append("\u2714"); // Heavy check mark
            } else {
                // Answer not correct.
                // Only use the complex diff code when needed, that is when we have some typed text that is not
                // exactly the same as the correct text.
                String[] diffedStrings = diffEngine.diffedHtmlStrings(correctAnswer, userAnswer);
                // We know we get back two strings.
                sb.append(diffedStrings[0]);
                sb.append("<br>&darr;<br>");
                sb.append(diffedStrings[1]);
            }
        } else {
            if (!mUseInputTag) {
                sb.append(DiffEngine.wrapMissing(correctAnswer));
            } else {
                sb.append(correctAnswer);
            }
        }
        sb.append("</code></div>");
        return m.replaceAll(sb.toString());
    }