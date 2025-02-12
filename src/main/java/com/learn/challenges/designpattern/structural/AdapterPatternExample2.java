package com.learn.challenges.designpattern.structural;

public class AdapterPatternExample2 {

    // Only fetches the data in XML form
    // The adaptee class that needs to be adapted
    static class StockDataXMLRepo {
        public String getStockData() {
                return "<stockData><stock>ABC</stock><price>100</price></stockData>";
        }
    }

    static interface StockDataAdapter {
        String transformData();
    }

    static class JsonDataAdapter implements StockDataAdapter {

        StockDataXMLRepo stockDataXMLRepo;

        public JsonDataAdapter(StockDataXMLRepo stockDataXMLRepo) {
            this.stockDataXMLRepo = stockDataXMLRepo;
        }

        @Override
        public String transformData() {
            String data = stockDataXMLRepo.getStockData();
            // transform the data here;
            return data;
        }

    }

    static class AnalyticsLibrary {
        String analyseData(String data) {
            return data;
        }
    }


    public static void main(String[] args) {
        StockDataAdapter jsonDataAdapter = new JsonDataAdapter(new StockDataXMLRepo());
        String transformedData = jsonDataAdapter.transformData();

        AnalyticsLibrary library = new AnalyticsLibrary();
        library.analyseData(transformedData);
    }

}
