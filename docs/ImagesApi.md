# ImagesApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteImage**](ImagesApi.md#deleteImage) | **DELETE** /images/{id} | Delete an Image |
| [**getAll**](ImagesApi.md#getAll) | **GET** /images | Get all Images |
| [**getById**](ImagesApi.md#getById) | **GET** /images/{id} | Get an Image |
| [**updateImageById**](ImagesApi.md#updateImageById) | **PUT** /images/{id} | Update an Image |


<a name="deleteImage"></a>
# **deleteImage**
> deleteImage(id).execute();

Delete an Image

Deletes an Image. Only Images of type &#x60;snapshot&#x60; and &#x60;backup&#x60; can be deleted.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ImagesApi;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Example {
  public static void main(String[] args) {
    Configuration configuration = new Configuration();
    configuration.host = "https://api.hetzner.cloud/v1";
    
    // Configure HTTP bearer authorization: APIToken
    configuration.token = "BEARER TOKEN";
    Hetzner client = new Hetzner(configuration);
    Long id = 56L; // ID of the Image
    try {
      client
              .images
              .deleteImage(id)
              .execute();
    } catch (ApiException e) {
      System.err.println("Exception when calling ImagesApi#deleteImage");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      client
              .images
              .deleteImage(id)
              .executeWithHttpInfo();
    } catch (ApiException e) {
      System.err.println("Exception when calling ImagesApi#deleteImage");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}

```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**| ID of the Image | |

### Return type

null (empty response body)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Image deleted |  -  |

<a name="getAll"></a>
# **getAll**
> ImagesGetAllResponse getAll().sort(sort).type(type).status(status).boundTo(boundTo).includeDeprecated(includeDeprecated).name(name).labelSelector(labelSelector).architecture(architecture).page(page).perPage(perPage).execute();

Get all Images

Returns all Image objects. You can select specific Image types only and sort the results by using URI parameters.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ImagesApi;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Example {
  public static void main(String[] args) {
    Configuration configuration = new Configuration();
    configuration.host = "https://api.hetzner.cloud/v1";
    
    // Configure HTTP bearer authorization: APIToken
    configuration.token = "BEARER TOKEN";
    Hetzner client = new Hetzner(configuration);
    String sort = "id"; // Sort resources by field and direction. Can be used multiple times. For more information, see \"[Sorting](https://docs.hetzner.cloud)\". 
    String type = "system"; // Can be used multiple times.
    String status = "available"; // Can be used multiple times. The response will only contain Images matching the status.
    String boundTo = "boundTo_example"; // Can be used multiple times. Server ID linked to the Image. Only available for Images of type `backup`
    Boolean includeDeprecated = true; // Can be used multiple times.
    String name = "name_example"; // Filter resources by their name. The response will only contain the resources matching the specified name. 
    String labelSelector = "labelSelector_example"; // Filter resources by labels. The response will only contain resources matching the label selector. For more information, see \"[Label Selector](https://docs.hetzner.cloud)\". 
    String architecture = "architecture_example"; // Return only Images with the given architecture.
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      ImagesGetAllResponse result = client
              .images
              .getAll()
              .sort(sort)
              .type(type)
              .status(status)
              .boundTo(boundTo)
              .includeDeprecated(includeDeprecated)
              .name(name)
              .labelSelector(labelSelector)
              .architecture(architecture)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getImages());
      System.out.println(result.getMeta());
    } catch (ApiException e) {
      System.err.println("Exception when calling ImagesApi#getAll");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ImagesGetAllResponse> response = client
              .images
              .getAll()
              .sort(sort)
              .type(type)
              .status(status)
              .boundTo(boundTo)
              .includeDeprecated(includeDeprecated)
              .name(name)
              .labelSelector(labelSelector)
              .architecture(architecture)
              .page(page)
              .perPage(perPage)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ImagesApi#getAll");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}

```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **sort** | **String**| Sort resources by field and direction. Can be used multiple times. For more information, see \&quot;[Sorting](https://docs.hetzner.cloud)\&quot;.  | [optional] [enum: id, id:asc, id:desc, name, name:asc, name:desc, created, created:asc, created:desc] |
| **type** | **String**| Can be used multiple times. | [optional] [enum: system, snapshot, backup, app] |
| **status** | **String**| Can be used multiple times. The response will only contain Images matching the status. | [optional] [enum: available, creating] |
| **boundTo** | **String**| Can be used multiple times. Server ID linked to the Image. Only available for Images of type &#x60;backup&#x60; | [optional] |
| **includeDeprecated** | **Boolean**| Can be used multiple times. | [optional] |
| **name** | **String**| Filter resources by their name. The response will only contain the resources matching the specified name.  | [optional] |
| **labelSelector** | **String**| Filter resources by labels. The response will only contain resources matching the label selector. For more information, see \&quot;[Label Selector](https://docs.hetzner.cloud)\&quot;.  | [optional] |
| **architecture** | **String**| Return only Images with the given architecture. | [optional] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**ImagesGetAllResponse**](ImagesGetAllResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;images&#x60; key in the reply contains an array of Image objects with this structure |  -  |

<a name="getById"></a>
# **getById**
> ImagesGetByIdResponse getById(id).execute();

Get an Image

Returns a specific Image object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ImagesApi;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Example {
  public static void main(String[] args) {
    Configuration configuration = new Configuration();
    configuration.host = "https://api.hetzner.cloud/v1";
    
    // Configure HTTP bearer authorization: APIToken
    configuration.token = "BEARER TOKEN";
    Hetzner client = new Hetzner(configuration);
    Long id = 56L; // ID of the Image
    try {
      ImagesGetByIdResponse result = client
              .images
              .getById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getImage());
    } catch (ApiException e) {
      System.err.println("Exception when calling ImagesApi#getById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ImagesGetByIdResponse> response = client
              .images
              .getById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ImagesApi#getById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}

```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**| ID of the Image | |

### Return type

[**ImagesGetByIdResponse**](ImagesGetByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;image&#x60; key in the reply contains an Image object with this structure |  -  |

<a name="updateImageById"></a>
# **updateImageById**
> ImagesUpdateImageByIdResponse updateImageById(id).imagesUpdateImageByIdRequest(imagesUpdateImageByIdRequest).execute();

Update an Image

Updates the Image. You may change the description, convert a Backup Image to a Snapshot Image or change the Image labels. Only Images of type &#x60;snapshot&#x60; and &#x60;backup&#x60; can be updated.  Note that when updating labels, the current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ImagesApi;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Example {
  public static void main(String[] args) {
    Configuration configuration = new Configuration();
    configuration.host = "https://api.hetzner.cloud/v1";
    
    // Configure HTTP bearer authorization: APIToken
    configuration.token = "BEARER TOKEN";
    Hetzner client = new Hetzner(configuration);
    Long id = 56L; // ID of the Image
    String description = "description_example"; // New description of Image
    Object labels = null; // User-defined labels (key-value pairs)
    String type = "snapshot"; // Destination Image type to convert to
    try {
      ImagesUpdateImageByIdResponse result = client
              .images
              .updateImageById(id)
              .description(description)
              .labels(labels)
              .type(type)
              .execute();
      System.out.println(result);
      System.out.println(result.getImage());
    } catch (ApiException e) {
      System.err.println("Exception when calling ImagesApi#updateImageById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ImagesUpdateImageByIdResponse> response = client
              .images
              .updateImageById(id)
              .description(description)
              .labels(labels)
              .type(type)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ImagesApi#updateImageById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}

```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**| ID of the Image | |
| **imagesUpdateImageByIdRequest** | [**ImagesUpdateImageByIdRequest**](ImagesUpdateImageByIdRequest.md)|  | [optional] |

### Return type

[**ImagesUpdateImageByIdResponse**](ImagesUpdateImageByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The image key in the reply contains the modified Image object |  -  |

