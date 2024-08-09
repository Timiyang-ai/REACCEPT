public Result evaluate(JsonElement data, int index) {
            // replace INDEX with index value
            if (tokens.length == 1 && INDEX.equals(tokens[0])) {
                return Result.success(new JsonPrimitive(String.valueOf(index)));
            } else {
                JsonElement elementToReturn = data;
                JsonElement tempElement;
                JsonArray tempArray;

                for (int i = 0; i < tokens.length; i++) {
                    String segment = tokens[i];
                    if (elementToReturn == null) {
                        return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                    }
                    if (elementToReturn.isJsonNull()) {
                        return Result.JSON_NULL_EXCEPTION;
                    }
                    if ("".equals(segment)) {
                        continue;
                    }
                    if (elementToReturn.isJsonArray()) {
                        tempArray = elementToReturn.getAsJsonArray();

                        if (INDEX.equals(segment)) {
                            if (index < tempArray.size()) {
                                elementToReturn = tempArray.get(index);
                            } else {
                                return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                            }
                        } else if (ARRAY_DATA_LENGTH_REFERENCE.equals(segment)) {
                            elementToReturn = new JsonPrimitive(tempArray.size());
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
                    } else if (elementToReturn.isJsonObject()) {
                        tempElement = elementToReturn.getAsJsonObject().get(segment);
                        if (tempElement != null) {
                            elementToReturn = tempElement;
                        } else {
                            return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                        }
                    } else if (elementToReturn.isJsonPrimitive()) {
                        return Result.INVALID_DATA_PATH_EXCEPTION;
                    } else {
                        return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                    }
                }
                if (elementToReturn.isJsonNull()) {
                    return Result.JSON_NULL_EXCEPTION;
                }
                return Result.success(elementToReturn);
            }
        }