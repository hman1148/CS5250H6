package org.example;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(name = "Options", mixinStandardHelpOptions = true, description = "Options for the application")
public class Options implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Options.class);

    @Option(names = {"-b", "--bucket3"}, description = "Name of the S3 Bucket to store widgets (Bucket 3)", required = false)
    private String Bucket3;

    @Option(names = {"-d", "--dynamodb-table"}, description = "Name of the DynamoDB table to store widgets", required = false)
    private String DynamoDBTable;

    @Option(names = {"-r", "--bucket2"}, description = "Name of the S3 bucket to read widget requests from (Bucket 2)", required = true)
    private String Bucket2;

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
        // Log the provided options
        logger.info("Starting application with the following options:");
        logger.info("Bucket 3: {}", Bucket3);
        logger.info("DynamoDB Table: {}", DynamoDBTable);
        logger.info("Bucket 2 (required): {}", Bucket2);

        try {
            S3Service s3Service = new S3Service();
            DynamoDBService dynamoDBService = new DynamoDBService();

            Consumer consumer = new Consumer(s3Service, dynamoDBService, this);
            consumer.StartingReceving();
            
            // Log the success of the service start
            logger.info("Services started successfully.");
        } catch (Exception e) {
            // Log the exception if there is an error during execution
            logger.error("An error occurred while running the services: ", e);
        }
    }
}
