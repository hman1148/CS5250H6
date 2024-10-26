# Dependencies 
 * Gradle 8.4
 * Java 11

# Unit Tests 
    * Run `gradle test`

# Build Process 
    * To build the code run `gradle build` 
    * Jar file will be located build/libs/Homework6-10.all.jar"

# Application Options

The application provides options to specify various configurations related to AWS S3 buckets and DynamoDB tables. The following options are available:

## Command-Line Arguments

| Argument | Short Form | Long Form          | Description                                                    | Required |
|----------|------------|--------------------|----------------------------------------------------------------|----------|
| `-b`     | `-b`       | `--bucket3`        | Name of the S3 bucket to store widgets (Bucket 3).             | No       |
| `-d`     | `-d`       | `--dynamodb-table` | Name of the DynamoDB table to store widgets.                   | No       |
| `-r`     | `-r`       | `--bucket2`        | Name of the S3 bucket to read widget requests from (Bucket 2). | Yes      |

## Examples

- **Specify the S3 bucket for storage (Bucket 3) and the DynamoDB table:**
  ```bash
  java -jar application.jar -b my-bucket-3 -d my-dynamodb-table


