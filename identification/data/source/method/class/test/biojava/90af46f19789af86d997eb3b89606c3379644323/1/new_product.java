public LinkedHashMap<String,S> process(int max) throws IOException {


        String line = "";
        if(this.line != null && this.line.length() > 0){
            line=this.line;
        }
        String header = "";
        if(this.header != null && this.header.length() > 0){
            header=this.header;
        }

        StringBuilder sb = new StringBuilder();
        int processedSequences=0;
        boolean keepGoing = true;


        LinkedHashMap<String,S> sequences = new LinkedHashMap<String,S>();

        do {
            line = line.trim(); // nice to have but probably not needed
            if (line.length() != 0) {
                if (line.startsWith(">")) {//start of new fasta record

                    if (sb.length() > 0) {
                        //i.e. if there is already a sequence before
                        //logger.info("Sequence index=" + sequenceIndex);

                        try {
                            @SuppressWarnings("unchecked")
                            S sequence = (S)sequenceCreator.getSequence(sb.toString(), sequenceIndex);
                            headerParser.parseHeader(header, sequence);
                            sequences.put(sequence.getAccession().getID(),sequence);
                            processedSequences++;

                        } catch (CompoundNotFoundException e) {
                            logger.warn("Sequence with header '{}' has unrecognised compounds ({}), it will be ignored",
                                    header, e.getMessage());
                        }

                        sb.setLength(0); //this is faster than allocating new buffers, better memory utilization (same buffer)
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


                // Fix for #282
                if ( sequences.size() == 0 && max != -1) {
                    return null;
                }

                //i.e. EOF
                String seq = sb.toString();
                if ( seq.length() == 0) {
                    logger.warn("Can't parse sequence {}. Got sequence of length 0!", sequenceIndex);
                    logger.warn("header: {}", header);
                }
                //logger.info("Sequence index=" + sequenceIndex + " " + fileIndex );
                try {
                    @SuppressWarnings("unchecked")
                    S sequence = (S)sequenceCreator.getSequence(seq, sequenceIndex);
                    headerParser.parseHeader(header, sequence);
                    sequences.put(sequence.getAccession().getID(),sequence);
                    processedSequences++;
                } catch (CompoundNotFoundException e) {
                    logger.warn("Sequence with header '{}' has unrecognised compounds ({}), it will be ignored",
                            header, e.getMessage());
                }
                keepGoing = false;
            }
            if (max > -1 && processedSequences>=max) {
                keepGoing=false;
            }
            if ( this.line == null)
                keepGoing = false;
        } while (keepGoing);

        this.line  = line;
        this.header= header;

        return sequences;
    }