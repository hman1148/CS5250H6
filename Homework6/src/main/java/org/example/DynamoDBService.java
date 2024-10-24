package org.example;

import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.UUID;

public class DynamoDBService {

    private final DynamoDbClient dynamoDbClient;

    public DynamoDBService() {
        dynamoDbClient = DynamoDbClient.builder().build();
    }

    public void storeWidgetsInDynamoDB(String tableName, String widgetInfo) {
        try {

            HashMap<String, AttributeValue> item = new HashMap<>();
            item.put("WidgetId", AttributeValue.builder().s(UUID.randomUUID().toString()).build());
            item.put("Data", AttributeValue.builder().s(widgetInfo).build());

            PutItemRequest putItemRequest = PutItemRequest.builder()
                    .tableName(tableName)
                    .item(item)
                    .build();

            dynamoDbClient.putItem(putItemRequest);
            System.out.println("Widget: " + widgetInfo + " stored in DynamoDB");

        } catch (SdkException ex) {
            System.err.println("Failed to store widget: " + widgetInfo + " into DynamoDB " + ex.getMessage());
        }
    }

}
