package org.example;

import picocli.CommandLine;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "Options", mixinStandardHelpOptions = true, description = "Options for the application")
public class Options implements Runnable{

    @Option(names = {"-s3", "--s3-strategy"}, description = "The storage strategy to use", required = true)
    private String StorageStrategy;

    @Option(names = {"-b", "--bucket3"}, description = "Name of the S3 Bucet to store widgets (Bucket 3)", required = false)
    private String Bucket3;
    @Option(names = {"-d", "--dynamodb-table"}, description = "Name of the DynamoDB table to store widgets", required = false)
    private String DynamoDBTable;

    @Option(names = {"-r", "--bucket2"}, description = "Name of the S3 bucket to read widget requests from (Bucket 2)")
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

    @Override
    public void run() {
        S3Service s3Service = new S3Service();
        DynamoDBService dynamoDBService = new DynamoDBService();

        Consumer consumer = new Consumer(s3Service, dynamoDBService, this);
        consumer.StartingReceving();
    }
}
