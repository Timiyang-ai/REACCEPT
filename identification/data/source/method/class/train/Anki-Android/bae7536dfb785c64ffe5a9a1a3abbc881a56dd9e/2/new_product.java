private String typeAnsAnswerFilter(String buf, String userAnswer, String correctAnswer) {
        Matcher m = sTypeAnsPat.matcher(buf);
        DiffEngine diffEngine = new DiffEngine();
        StringBuilder sb = new StringBuilder();
        sb.append("<div");
        sb.append("><code id=typeans>");

        // We have to use Matcher.quoteReplacement because the inputs here might have $ or \.

        if (!TextUtils.isEmpty(userAnswer)) {
            // The user did type something.
            if (userAnswer.equals(correctAnswer)) {
                // and it was right.
                sb.append(Matcher.quoteReplacement(DiffEngine.wrapGood(correctAnswer)));
                sb.append("\u2714"); // Heavy check mark
            } else {
                // Answer not correct.
                // Only use the complex diff code when needed, that is when we have some typed text that is not
                // exactly the same as the correct text.
                String[] diffedStrings = diffEngine.diffedHtmlStrings(correctAnswer, userAnswer);
                // We know we get back two strings.
                sb.append(Matcher.quoteReplacement(diffedStrings[0]));
                sb.append("<br>&darr;<br>");
                sb.append(Matcher.quoteReplacement(diffedStrings[1]));
            }
        } else {
            if (!mUseInputTag) {
                sb.append(Matcher.quoteReplacement(DiffEngine.wrapMissing(correctAnswer)));
            } else {
                sb.append(Matcher.quoteReplacement(correctAnswer));
            }
        }
        sb.append("</code></div>");
        return m.replaceAll(sb.toString());
    }