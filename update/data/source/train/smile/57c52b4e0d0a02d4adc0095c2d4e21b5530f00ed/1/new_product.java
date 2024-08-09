public AttributeDataset f(Dataset<T> data) {
        AttributeDataset dataset = new AttributeDataset(data.getName(), attributes(), data.response()); 
        dataset.setDescription(data.getDescription());

        for (int i = 0; i < data.size(); i++) {
            Datum<T> datum = data.get(i);
            Datum<double[]> x = new Datum<>(f(datum.x), datum.y, datum.weight);
            x.name = datum.name;
            x.description = datum.description;
            x.timestamp = datum.timestamp;
            dataset.add(x);
        }

        return dataset;
    }