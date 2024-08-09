public static Dataframe parseCSVFile(Reader reader, String yVariable, LinkedHashMap<String, TypeInference.DataType> headerDataTypes, 
                                           char delimiter, char quote, String recordSeparator, Long skip, Long limit, DatabaseConfiguration dbConf) {
            Logger logger = LoggerFactory.getLogger(Dataframe.Builder.class);
            
            if(skip == null) {
                skip = 0L;
            }
            
            if(limit == null) {
                limit = Long.MAX_VALUE;
            }
            
            logger.info("Parsing CSV file");
            
            if (!headerDataTypes.containsKey(yVariable)) {
                logger.warn("WARNING: The file is missing the response variable column {}.", yVariable);
            }
            
            TypeInference.DataType yDataType = headerDataTypes.get(yVariable);
            Map<String, TypeInference.DataType> xDataTypes = new HashMap<>(headerDataTypes); //copy header types
            xDataTypes.remove(yVariable); //remove the response variable from xDataTypes
            Dataframe dataset = new Dataframe(dbConf, yDataType, xDataTypes); //use the private constructor to pass DataTypes directly and avoid updating them on the fly
            
            
            CSVFormat format = CSVFormat
                                .RFC4180
                                .withHeader()
                                .withDelimiter(delimiter)
                                .withQuote(quote)
                                .withRecordSeparator(recordSeparator);
            
            try (final CSVParser parser = new CSVParser(reader, format)) { 
                ThreadMethods.throttledExecution(StreamMethods.enumerate(StreamMethods.stream(parser.spliterator(), false)).skip(skip).limit(limit), e -> { 
                    Integer rId = e.getKey();
                    CSVRecord row = e.getValue();
                
                    if (!row.isConsistent()) {
                        logger.warn("WARNING: Skipping row {} because its size does not match the header size.", row.getRecordNumber());
                    }
                    else {
                        Object y = null;
                        AssociativeArray xData = new AssociativeArray();
                        for (Map.Entry<String, TypeInference.DataType> entry : headerDataTypes.entrySet()) {
                            String column = entry.getKey();
                            TypeInference.DataType dataType = entry.getValue();

                            Object value = TypeInference.DataType.parse(row.get(column), dataType); //parse the string value according to the DataType
                            if (yVariable != null && yVariable.equals(column)) {
                                y = value;
                            } 
                            else {
                                xData.put(column, value);
                            }
                        }
                        
                        Record r = new Record(xData, y);
                        
                        //use the internal unsafe methods to avoid the update of the Metas. 
                        //The Metas are already set in the construction of the Dataframe.
                        if(SynchronizedBlocks.WITHOUT_SYNCHRONIZED.isActivated()) {
                            dataset._unsafe_set(rId, r); 
                        }
                        else {
                            synchronized(dataset) {
                                dataset._unsafe_set(rId, r);
                            }
                        }
                    }
                });
            } 
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return dataset;
        }