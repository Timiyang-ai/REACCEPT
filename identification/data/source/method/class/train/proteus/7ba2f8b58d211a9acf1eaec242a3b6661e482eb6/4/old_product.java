public Result evaluate(Value data, int index) {
            Result result = resolve(data, index);
            if (null == this.formatter) {
                return result;
            } else {
                return Result.success(this.formatter.format(result.value, index, this.arguments));
            }
        }