public List<S> process() throws Exception {
        ArrayList<S> sequences = new ArrayList<S>();


        String line = "";
        String header = "";
        StringBuffer sb = new StringBuffer();
        int maxSequenceLength = -1;
        long fileIndex = 0;
        long sequenceIndex = 0;
        boolean keepGoing = true;
        do {
            line = line.trim(); // nice to have but probably not needed
            if (line.length() != 0) {
                if (line.startsWith(">")) {
                    if (sb.length() > 0) {
                        S sequence = (S) sequenceCreator.getSequence(sb.toString(), sequenceIndex);
                        headerParser.parseHeader(header, sequence);
                        sequences.add(sequence);
                        if (maxSequenceLength < sb.length()) {
                            maxSequenceLength = sb.length();
                        }
                        sb = new StringBuffer(maxSequenceLength);
                    }
                    header = line.substring(1);
                } else if (line.startsWith(";")) {
                } else {
                    //mark the start of the sequence with the fileIndex before the line was read
                    if(sb.length() == 0){
                        sequenceIndex = fileIndex;
                    }
                    sb.append(line);
                }
            }
            fileIndex = br.getBytesRead();
            line = br.readLine();
            if (line == null) {
                S sequence = (S) sequenceCreator.getSequence(sb.toString(), fileIndex);
                headerParser.parseHeader(header, sequence);
                sequences.add(sequence);
                keepGoing = false;
            }
        } while (keepGoing);
        br.close();
        isr.close();
        //If stream was created from File object then we need to close it
        if (fi != null) {
            fi.close();
        }
        return sequences;
    }