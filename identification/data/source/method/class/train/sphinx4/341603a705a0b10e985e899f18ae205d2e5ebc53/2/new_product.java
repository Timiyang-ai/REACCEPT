public List<WordResult> align(URL audioUrl, List<String> transcript)
            throws IOException {
        LongTextAligner aligner = new LongTextAligner(transcript, TUPLE_SIZE);
        Map<Integer, WordResult> alignedWords = newTreeMap();
        Queue<Range<Integer>> ranges = newArrayDeque();
        Queue<List<String>> texts = newArrayDeque();
        Queue<TimeFrame> timeFrames = newArrayDeque();

        ranges.offer(Range.closed(0, transcript.size()));
        texts.offer(transcript);
        TimeFrame totalTimeFrame = TimeFrame.INFINITE;
        timeFrames.offer(totalTimeFrame);
        long lastFrame = TimeFrame.INFINITE.getEnd();

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
                TimeFrame frame = timeFrames.poll();
                Range<Integer> range = ranges.poll();

                logger.info("Aligning frame " + frame + " to text " + text
                        + " range " + range);

                if (i < 3) {
                    languageModel.setText(text);
                }

                recognizer.allocate();

                if (i == 3) {
                    grammar.setWords(text);
                }

                context.setSpeechSource(audioUrl.openStream(), frame);

                List<WordResult> hypothesis = newArrayList();
                Result result;
                while (null != (result = recognizer.recognize())) {
                    addAll(hypothesis, result.getTimedBestResult(false, i > 2));
                }

                if (i == 0) {
                    if (hypothesis.size() > 0) {
                        lastFrame = hypothesis.get(hypothesis.size() - 1)
                                .getTimeFrame().getEnd();
                    }
                }

                List<String> words = transform(hypothesis, toSpelling());
                int[] alignment = aligner.align(words, range);

                List<WordResult> results = hypothesis;

                logger.info("Decoding result is " + results);

                dumpAlignment(transcript, alignment, results);

                for (int j = 0; j < alignment.length; j++) {
                    if (alignment[j] != -1) {
                        alignedWords.put(alignment[j], hypothesis.get(j));
                    }
                }

                recognizer.deallocate();
            }

            scheduleNextAlignment(transcript, alignedWords, ranges, texts,
                    timeFrames, lastFrame);
        }

        return newArrayList(alignedWords.values());
    }