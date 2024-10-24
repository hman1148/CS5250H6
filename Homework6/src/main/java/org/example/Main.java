package org.example;

import picocli.CommandLine;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;


public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new Options()).execute(args);
        System.exit(exitCode);
    }
}