package org.example;

public class Options {

    private String StorageStrategy;
    private String Bucket3;
    private String DynamoDBTable;
    private String Bucket2;

    public String getStorageStrategy() {
        return StorageStrategy;
    }

    public void setStorageStrategy(String storageStrategy) {
        StorageStrategy = storageStrategy;
    }

    public String getBucket3() {
        return Bucket3;
    }

    public void setBucket3(String bucket3) {
        Bucket3 = bucket3;
    }

    public String getDynamoDBTable() {
        return DynamoDBTable;
    }

    public void setDynamoDBTable(String dynamoDBTable) {
        DynamoDBTable = dynamoDBTable;
    }

    public String getBucket2() {
        return Bucket2;
    }

    public void setBucket2(String bucket2) {
        Bucket2 = bucket2;
    }
}
