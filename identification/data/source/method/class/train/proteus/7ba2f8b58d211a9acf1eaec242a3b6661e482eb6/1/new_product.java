private Result resolve(Value data, int index) {
            // replace INDEX with index value
            if (tokens.length == 1 && INDEX.equals(tokens[0])) {
                return Result.success(new Primitive(String.valueOf(index)));
            } else {
                Value elementToReturn = data;
                Value tempElement;
                Array tempArray;

                for (int i = 0; i < tokens.length; i++) {
                    String segment = tokens[i];
                    if (elementToReturn == null) {
                        return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                    }
                    if (elementToReturn.isNull()) {
                        return Result.NULL_EXCEPTION;
                    }
                    if ("".equals(segment)) {
                        continue;
                    }
                    if (elementToReturn.isArray()) {
                        tempArray = elementToReturn.getAsArray();

                        if (INDEX.equals(segment)) {
                            if (index < tempArray.size()) {
                                elementToReturn = tempArray.get(index);
                            } else {
                                return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                            }
                        } else if (ARRAY_DATA_LENGTH_REFERENCE.equals(segment)) {
                            elementToReturn = new Primitive(tempArray.size());
                        } else if (ARRAY_DATA_LAST_INDEX_REFERENCE.equals(segment)) {
                            if (tempArray.size() == 0) {
                                return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                            }
                            elementToReturn = tempArray.get(tempArray.size() - 1);
                        } else {
                            try {
                                index = Integer.parseInt(segment);
                            } catch (NumberFormatException e) {
                                return Result.INVALID_DATA_PATH_EXCEPTION;
                            }
                            if (index < tempArray.size()) {
                                elementToReturn = tempArray.get(index);
                            } else {
                                return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                            }
                        }
                    } else if (elementToReturn.isObject()) {
                        tempElement = elementToReturn.getAsObject().get(segment);
                        if (tempElement != null) {
                            elementToReturn = tempElement;
                        } else {
                            return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                        }
                    } else if (elementToReturn.isPrimitive()) {
                        return Result.INVALID_DATA_PATH_EXCEPTION;
                    } else {
                        return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                    }
                }
                if (elementToReturn.isNull()) {
                    return Result.NULL_EXCEPTION;
                }
                return Result.success(elementToReturn);
            }
        }