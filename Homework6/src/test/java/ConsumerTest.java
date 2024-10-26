import org.example.Consumer;
import org.example.DynamoDBService;
import org.example.Options;
import org.example.S3Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.core.exception.SdkException;

import static org.mockito.Mockito.*;

public class ConsumerTest {

    @Mock
    private S3Service s3Service;

    @Mock
    private DynamoDBService dynamoDBService;

    @Mock
    private Options options;
    private Consumer consumerTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        consumerTest = new Consumer(s3Service, dynamoDBService, options);
    }

    @Test
    public void testStartReceiving() throws  Exception {
        when(options.getBucket2()).thenReturn("bucket2");
        when(options.getBucket3()).thenReturn("bucket3");
        when(s3Service.readWidgetRequestsFromBucket("bucket2")).thenReturn("widgetData");

        consumerTest.StartingReceving();
        verify(s3Service, atLeastOnce()).storeWidgetsInS3("bucket3", "widgetData");
    }

    @Test
    public void testStartReceivingDynamoDB() throws Exception {
        when(options.getBucket2()).thenReturn("bucket2");
        when(options.getDynamoDBTable()).thenReturn("widgetsTable");
        when(s3Service.readWidgetRequestsFromBucket("bucket2")).thenReturn("widgetData");

        consumerTest.StartingReceving();

        verify(dynamoDBService, atLeastOnce()).storeWidgetsInDynamoDB("widgetsTable", "widgetsData");
    }

    @Test
    public void testSTartReceivingSdkExcpetions() throws Exception {
        when(options.getBucket2()).thenReturn("bucket2");
        when(s3Service.readWidgetRequestsFromBucket("bucket2")).thenThrow(SdkException.builder().message("AWS error").build());

        consumerTest.StartingReceving();

        verify(s3Service, never()).storeWidgetsInS3(anyString(), anyString());
        verify(dynamoDBService, never()).storeWidgetsInDynamoDB(anyString(), anyString());
    }

}