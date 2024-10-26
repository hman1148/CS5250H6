package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;


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
                // Read the widget requests
                String widgetRequestJson = this.s3Service.readWidgetRequestsFromBucket(this.options.getBucket2());

                if (widgetRequestJson != null) {
                    // Create the widget object and determine the request type
                    Widget widget = objectMapper.readValue(widgetRequestJson, Widget.class);

                    RequestType requestType = RequestType.fromString(widget.getType());

                    switch (requestType) {
                        case CREATE:
                            processCreateRequest(widget);
                            break;
                        case UPDATE:
                            System.out.println("Haven't implemented Update");
                            break;
                        case DELETE:
                            System.out.println("Haven't implemented Delete");
                            break;
                        default:
                            System.err.println("Unknown request type");
                            break;
                    }

                } else {
                    Thread.sleep(100);
                }
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
                dynamoDBService.storeWidgetsInDynamoDB(this.options.getDynamoDBTable(), widget);
            }
        } catch (Exception ex) {
            System.err.println("Failed to process create request: " + widget.getWidgetId() + " Error: " + ex.getMessage());
        }
    }
}
