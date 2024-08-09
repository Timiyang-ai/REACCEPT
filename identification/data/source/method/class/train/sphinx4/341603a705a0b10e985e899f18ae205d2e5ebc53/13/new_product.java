public List<WordResult> align(URL audioUrl, List<String> transcript)
            throws IOException {
        LongTextAligner aligner = new LongTextAligner(transcript, TUPLE_SIZE);
        Map<Integer, WordResult> alignedWords = new TreeMap<Integer, WordResult>();
        Queue<Range> ranges = new LinkedList<Range>();
        Queue<List<String>> texts = new ArrayDeque<List<String>>();
        Queue<TimeFrame> timeFrames = new ArrayDeque<TimeFrame>();

        ranges.offer(new Range(0, transcript.size()));
        texts.offer(transcript);
        TimeFrame totalTimeFrame = TimeFrame.INFINITE;
        timeFrames.offer(totalTimeFrame);
        long lastFrame = TimeFrame.INFINITE.getEnd();

        for (int i = 0; i < 4; ++i) {
            if (i == 3) {
                context.setLocalProperty("decoder->searchManager",
                        "alignerSearchManager");
            }

            while (!texts.isEmpty()) {
                assert texts.size() == ranges.size();
                assert texts.size() == timeFrames.size();

                List<String> text = texts.poll();
                TimeFrame frame = timeFrames.poll();
                Range range = ranges.poll();

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

                List<WordResult> hypothesis = new ArrayList<WordResult>();
                Result result;
                while (null != (result = recognizer.recognize())) {
                    hypothesis.addAll(result.getTimedBestResult(false));
                }

                if (i == 0) {
                    if (hypothesis.size() > 0) {
                        lastFrame = hypothesis.get(hypothesis.size() - 1)
                                .getTimeFrame().getEnd();
                    }
                }

                List<String> words = new ArrayList<String>();
                for (WordResult wr : hypothesis) {
                    words.add(wr.getWord().getSpelling());
                }
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

        return new ArrayList<WordResult>(alignedWords.values());
    }