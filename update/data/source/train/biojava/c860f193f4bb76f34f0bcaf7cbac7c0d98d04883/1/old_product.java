public LinkedHashMap<String,S> process(int max) throws IOException, CompoundNotFoundException {
        LinkedHashMap<String,S> sequences = new LinkedHashMap<String,S>();
        @SuppressWarnings("unchecked")
        S sequence = (S) sequenceCreator.getSequence(genbankParser.getSequence(new BufferedReader(new InputStreamReader(inputStream)), 0), 0);
        genbankParser.getSequenceHeaderParser().parseHeader(genbankParser.getHeader(), sequence);
        
        // add features to new sequence
        for (String k: genbankParser.getFeatures().keySet()){
            for (AbstractFeature f: genbankParser.getFeatures(k)){
                //f.getLocations().setSequence(sequence);  // can't set proper sequence source to features. It is actually needed? Don't think so...
                sequence.addFeature(f);
            }
        }
        
        // add taxonomy ID to new sequence
        ArrayList<DBReferenceInfo> dbQualifier = genbankParser.getDatabaseReferences().get("db_xref");
        if (dbQualifier != null){
            DBReferenceInfo q = dbQualifier.get(0);
            sequence.setTaxonomy(new TaxonomyID(q.getDatabase()+":"+q.getId(), DataSource.GENBANK));
        }
        
    	sequences.put(sequence.getAccession().getID(), sequence);
    	close();
        return sequences;
    }