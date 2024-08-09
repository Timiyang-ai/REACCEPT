@SuppressWarnings("unchecked")
    public LinkedHashMap<String,S> process() throws IOException {
        LinkedHashMap<String,S> sequences = new LinkedHashMap<String,S>();


        String line = "";
        String header = "";
        StringBuilder sb = new StringBuilder();
        int maxSequenceLength = -1;
        long fileIndex = 0;
        long sequenceIndex = 0;
        boolean keepGoing = true;
        do {
            line = line.trim(); // nice to have but probably not needed
            if (line.length() != 0) {
                if (line.startsWith(">")) {
                    if (sb.length() > 0) {
                    //    System.out.println("Sequence index=" + sequenceIndex);
                        S sequence = (S)sequenceCreator.getSequence(sb.toString(), sequenceIndex);
                        headerParser.parseHeader(header, sequence);
                        sequences.put(sequence.getAccession().getID(),sequence);
                        if (maxSequenceLength < sb.length()) {
                            maxSequenceLength = sb.length();
                        }
                        sb = new StringBuilder(maxSequenceLength);
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
            //    System.out.println("Sequence index=" + sequenceIndex + " " + fileIndex );
                S sequence = (S)sequenceCreator.getSequence(sb.toString(), sequenceIndex);
                headerParser.parseHeader(header, sequence);
                sequences.put(sequence.getAccession().getID(),sequence);
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