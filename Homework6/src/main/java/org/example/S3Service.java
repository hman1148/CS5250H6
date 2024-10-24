package org.example;

import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class S3Service {

    private final S3Client s3Client;

    public S3Service() {
        s3Client = S3Client.builder().build();
    }

    public String readWidgetRequestsFromBucket(String bucket) {
        try {
            ListObjectsV2Request listRequest = ListObjectsV2Request.builder()
                    .bucket(bucket)
                    .maxKeys(1)
                    .build();

            ListObjectsV2Response listResponse = s3Client.listObjectsV2(listRequest);

            if (!listResponse.contents().isEmpty()) {
                S3Object object = listResponse.contents().get(0);

                GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                        .bucket(bucket)
                        .key(object.key())
                        .build();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                        s3Client.getObject(getObjectRequest)))) {

                    StringBuilder widgetRequest = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        widgetRequest.append(line);
                    }

                    DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                            .bucket(bucket)
                            .key(object.key())
                            .build();
                    s3Client.deleteObject(deleteObjectRequest);

                    return widgetRequest.toString();
                }

            }
        } catch (SdkException | IOException ex) {
            System.err.println("Failed to read from bucket: " + bucket + " - " + ex.getMessage());
        }
        return null;
    }

    public void storeWidgetsInS3(String bucket, String widgetInfo) {
        try {
            // Generate a random key for the new object

            String objectKey = UUID.randomUUID().toString();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(objectKey)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromString(widgetInfo));

            System.out.println("Widget " + widgetInfo + " stored in bucket " + bucket);
        } catch (SdkException ex) {
            System.err.println("Failed to store widget " + widgetInfo + " in s3 " + ex.getMessage());
        }
    }

}
