static int countTrailing(@NotNull CharSequence thizz, @NotNull CharPredicate chars, int startIndex, int fromIndex) {
        fromIndex = Math.min(fromIndex, thizz.length());
        startIndex = rangeLimit(startIndex, 0, fromIndex);

        int index = lastIndexOfAnyNot(thizz, chars, startIndex, fromIndex);
        return index == -1 ? fromIndex - startIndex : fromIndex <= index ? 0 : fromIndex - index - 1;
    }