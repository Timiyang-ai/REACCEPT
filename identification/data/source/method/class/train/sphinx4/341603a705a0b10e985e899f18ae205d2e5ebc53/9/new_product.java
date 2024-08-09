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
                Range range = ranges.poll();
                
                System.out.println("---------------------------");
                System.out.println("Aligning frame " + frame + " to text " + text + " range " + range);

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
                        lastFrame = hypothesis.get(hypothesis.size() - 1).getTimeFrame().getEnd();
                    }
                }

                List<String> words = transform(hypothesis, toSpelling());
                int[] alignment = aligner.align(words, range);

                if (i < 5) {
                    List<WordResult> results = hypothesis;

                    System.out.println("--Result--------------------");

                    for (WordResult result1 : results) {
                        System.out.println(result1.getWord());
                    }
                    System.out.println("-----------------------");

                    int[] aid = alignment;
                    int lastId = -1;
                    for (int ij  = 0; ij < aid.length; ++ij) {
                        if (aid[ij] == -1) {
                            System.out.format("+ %s\n", results.get(ij));
                        } else {
                            if (aid[ij] - lastId > 1) {
                                for (String result1 : transcript.subList(lastId + 1,
                                                                   aid[ij])) {
                                    System.out.format("- %-25s\n", result1);
                                }
                            } else {
                                System.out.format("  %-25s\n", transcript.get(aid[ij]));
                            }
                            lastId = aid[ij];
                        }
                    }

                    if (lastId >= 0 && transcript.size() - lastId > 1) {
                        for (String result1 : transcript.subList(lastId + 1, transcript.size())) {
                            System.out.format("- %-25s\n", result1);
                        }
                    }

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
                    checkedOffer(transcript, texts, timeFrames, ranges, prevKey + 1, e.getKey(), prevEnd, e.getValue()
                                 .getTimeFrame().getStart());
                }
                prevKey = e.getKey();
                prevEnd = e.getValue().getTimeFrame().getEnd();
            }
            if (transcript.size() - prevKey > 1) {
                checkedOffer(transcript, texts, timeFrames, ranges, prevKey + 1, transcript.size(), prevEnd, lastFrame);
            }
        }

        return newArrayList(alignedWords.values());
    }