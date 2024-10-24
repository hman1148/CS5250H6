package org.example;

import picocli.CommandLine;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;


public class Main {
    public static void main(String[] args) {
        CommandLine.run(new Options(), args);

        Region region = Region.US_EAST_1;

        System.out.println("Hello world!");
    }
}