package org.example;

import software.amazon.awssdk.core.exception.SdkException;

public class Consumer {

    private final S3Service s3Service;
    private final DynamoDBService dynamoDBService;
    private final Options options;

    public Consumer(S3Service s3Service, DynamoDBService dynamoDBService, Options options) {
        this.s3Service = s3Service;
        this.dynamoDBService = dynamoDBService;
        this.options = options;
    }

    public void StartingReceving() {
        while (true) {
            try {
                String widgetRequest = this.s3Service.readWidgetRequestsFromBucket(options.getBucket2());

                // if (this.options.getStorageStrategy().equalsIgnoreCase("s3") && !(String.valueOf(this.options.getBucket3()) == null)) {
                //     this.s3Service.storeWidgetsInS3(this.options.getBucket3(), widgetRequest);
                // } else if (this.options.getStorageStrategy().equalsIgnoreCase("dynamodb") && !(String.valueOf(this.options.getDynamoDBTable()) == null)) {
                //     this.dynamoDBService.storeWidgetsInDynamoDB(this.options.getDynamoDBTable(), widgetRequest);
                // }

                Thread.sleep(100);
            } catch (SdkException e) {
                System.err.println("AWS SDK Error: " + e.getMessage());
            } catch (InterruptedException e) {
                System.err.println("Interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

//    private RequestType processRequest(String widgetRequest) {
//
//    }
//

}
