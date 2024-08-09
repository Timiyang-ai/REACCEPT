public static Dataset parseCSVFile(Reader reader, String yVariable, Map<String, TypeInference.DataType> headerDataTypes, 
                                           char delimiter, char quote, String recordSeparator, DatabaseConfiguration dbConf) {
            Logger logger = LoggerFactory.getLogger(Dataset.Builder.class);
            
            logger.info("Parsing CSV file");
            
            if (!headerDataTypes.containsKey(yVariable)) {
                logger.warn("WARNING: The file is missing the response variable column {}.", yVariable);
            }
            
            TypeInference.DataType yDataType = headerDataTypes.get(yVariable);
            Map<String, TypeInference.DataType> xDataTypes = new HashMap<>(headerDataTypes); //copy header types
            xDataTypes.remove(yVariable); //remove the response variable from xDataTypes
            Dataset dataset = new Dataset(dbConf, yDataType, xDataTypes); //use the private constructor to pass DataTypes directly and avoid updating them on the fly
            
            
            CSVFormat format = CSVFormat
                                .RFC4180
                                .withHeader()
                                .withDelimiter(delimiter)
                                .withQuote(quote)
                                .withRecordSeparator(recordSeparator);
            
            try (final CSVParser parser = new CSVParser(reader, format)) {                    
                for (CSVRecord row : parser) {
                    
                    if (!row.isConsistent()) {
                        logger.warn("WARNING: Skipping row {} because its size does not match the header size.", row.getRecordNumber());
                        continue;
                    }
                    
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
                    dataset._add(new Record(xData, y)); //use the internal _add() to avoid the update of the Metas. The Metas are already set in the construction of the Dataset.
                }
            } 
            catch (IOException ex) {
                dataset.delete();
                throw new RuntimeException(ex);
            }
            return dataset;
        }