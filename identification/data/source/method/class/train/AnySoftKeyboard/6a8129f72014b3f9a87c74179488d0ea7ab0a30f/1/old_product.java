public List<CharSequence> getOutputForTag(@NonNull CharSequence typedTagToSearch, KeyCodesProvider wordComposer) {
        mTagSuggestionsList.setTypedWord(typedTagToSearch);
        String tag = typedTagToSearch.toString().toLowerCase(Locale.US);

        if (mTagsForOutputs.containsKey(tag)) {
            mTagSuggestionsList.setTagsResults(mTagsForOutputs.get(tag));
        } else if (tag.length() == 0) {
            final List<QuickKeyHistoryRecords.HistoryKey> loadedHistory = QuickKeyHistoryRecords.load(mSharedPrefs);
            for (QuickKeyHistoryRecords.HistoryKey historyKey : loadedHistory) {
                //history is in reverse
                mPossibleQuickTextsFromDictionary.add(0, historyKey.value);
            }
            mTagSuggestionsList.setTagsResults(mPossibleQuickTextsFromDictionary);
        } else {
            mTempPossibleQuickTextsFromDictionary.clear();
            mPossibleQuickTextsFromDictionary.clear();
            mWordComposer.setTypedTag(wordComposer, typedTagToSearch);
            mTagsDictionary.getWords(mWordComposer, mSuggestionsListBuilder);
            mPossibleQuickTextsFromDictionary.addAll(mTempPossibleQuickTextsFromDictionary);
            mTagSuggestionsList.setTagsResults(mPossibleQuickTextsFromDictionary);
        }

        return mTagSuggestionsList;
    }