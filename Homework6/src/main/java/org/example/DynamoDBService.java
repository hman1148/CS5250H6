package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

public class DynamoDBService {

    private final DynamoDbClient dynamoDbClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DynamoDBService() {
        dynamoDbClient = DynamoDbClient.builder().build();
    }

    public void storeWidgetsInDynamoDB(String tableName, Widget widget) {
        try {
            // Convert widget object to a map
            Map<String, Object> widgetMap = objectMapper.convertValue(widget, Map.class);

            // Create a HashMap to store attrs in DynamoDB
            HashMap<String, AttributeValue> item = new HashMap<>();

            // Add each widget value to a key and value for dynamo
            for (Map.Entry<String, Object> entry: widgetMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if (value != null) {
                    item.put(key, AttributeValue.builder().s(value.toString()).build());
                }
            }

            PutItemRequest putItemRequest = PutItemRequest.builder()
                    .tableName(tableName)
                    .item(item)
                    .build();

            dynamoDbClient.putItem(putItemRequest);
            System.out.println("Widget: " + widget.getWidgetId() + " stored in DynamoDB");

        } catch (SdkException ex) {
            System.err.println("Failed to store widget: " + widget.getWidgetId() + " into DynamoDB " + ex.getMessage());
        }
    }

}
