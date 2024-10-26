package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.core.exception.SdkException;

import java.util.Map;

public class Consumer {

    private final S3Service s3Service;
    private final DynamoDBService dynamoDBService;
    private final Options options;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Consumer(S3Service s3Service, DynamoDBService dynamoDBService, Options options) {
        this.s3Service = s3Service;
        this.dynamoDBService = dynamoDBService;
        this.options = options;
    }

    public void StartingReceving() {
        while (true) {
            try {
                String widgetRequest = this.s3Service.readWidgetRequestsFromBucket(options.getBucket2());

                if (!(String.valueOf(this.options.getBucket3()) == null)) {
                    this.s3Service.storeWidgetsInS3(this.options.getBucket3(), widgetRequest);
                } else if (!(String.valueOf(this.options.getDynamoDBTable()) == null)) {
                    this.dynamoDBService.storeWidgetsInDynamoDB(this.options.getDynamoDBTable(), widgetRequest);
                }

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

    private void processCreateRequest(Widget widget) {
        try {
            if (this.options.getBucket3() != null) {
                s3Service.storeWidgetsInS3(this.options.getBucket3(), widget);
            } else if (this.options.getDynamoDBTable() != null) {
                Map<String, Object> widgetAttributes = objectMapper.convertValue(widget, Map.class);

                dynamoDBService.storeWidgetsInDynamoDB(this.options.getDynamoDBTable(), widgetAttributes);
            }


        } catch (Exception ex) {

        }
    }

}
