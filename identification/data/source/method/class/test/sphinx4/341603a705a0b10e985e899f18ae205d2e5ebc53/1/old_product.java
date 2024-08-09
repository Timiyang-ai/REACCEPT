public List<WordResult> align(URL audioUrl, List<String> transcript)
            throws IOException {
        LongTextAligner aligner = new LongTextAligner(transcript, TUPLE_SIZE);
        Map<Integer, WordResult> alignedWords = newTreeMap();
        Queue<Range<Integer>> ranges = newArrayDeque();
        Queue<List<String>> texts = newArrayDeque();
        Queue<TimeFrame> timeFrames = newArrayDeque();

        ranges.offer(Range.atLeast(0));
        texts.offer(transcript);
        TimeFrame totalTimeFrame = TimeFrame.INFINITE;
        timeFrames.offer(totalTimeFrame);

        for (int i = 0; i < 4; ++i) {
            if (i == 3) {
                context.setLocalProperty("decoder->searchManager",
                        "alignerSearchManager");
                context.processBatch();
            }

            while (!texts.isEmpty()) {
                checkState(texts.size() == ranges.size());
                checkState(texts.size() == timeFrames.size());

                List<String> text = texts.poll();

                if (i < 3) {
                    languageModel.setText(text);
                }

                recognizer.allocate();

                if (i == 3) {
                    grammar.setWords(text);
                }

                context.setSpeechSource(audioUrl.openStream(),
                        timeFrames.poll());

                List<WordResult> hypothesis = newArrayList();
                Result result;
                while (null != (result = recognizer.recognize())) {
                    addAll(hypothesis, result.getTimedBestResult(false, i > 2));
                }

                List<String> words = transform(hypothesis, toSpelling());
                int[] alignment;
                if (text.size() < 3) {
                    alignment =
                            alignText(text, words, ranges.poll()
                                    .lowerEndpoint());
                } else {
                    alignment = aligner.align(words, ranges.poll());
                }

                for (int j = 0; j < alignment.length; j++) {
                    if (alignment[j] != -1) {
                        alignedWords.put(alignment[j], hypothesis.get(j));
                    }
                }

                recognizer.deallocate();
            }

            int prevKey = -1;
            long prevEnd = 0;
            for (Map.Entry<Integer, WordResult> e : alignedWords.entrySet()) {
                if (e.getKey() - prevKey > 1) {
                    texts.offer(transcript.subList(prevKey + 1, e.getKey()));
                    timeFrames.offer(new TimeFrame(prevEnd, e.getValue()
                            .getTimeFrame().getStart()));
                    // TODO: 'texts' are not required, should use only 'ranges'
                    ranges.offer(Range.closed(prevKey + 1, e.getKey() - 1));
                }
                prevKey = e.getKey();
                prevEnd = e.getValue().getTimeFrame().getEnd();
            }
            if (transcript.size() - prevKey > 1) {
                texts.offer(transcript.subList(prevKey + 1, transcript.size()));
                // TODO: do not use MAX_VALUE
                timeFrames.offer(new TimeFrame(prevEnd, totalTimeFrame
                        .getEnd()));
                ranges.offer(Range.closed(prevKey + 1, transcript.size() - 1));
            }
        }

        return newArrayList(alignedWords.values());
    }