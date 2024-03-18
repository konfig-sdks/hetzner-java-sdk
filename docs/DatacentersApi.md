# DatacentersApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAll**](DatacentersApi.md#getAll) | **GET** /datacenters | Get all Datacenters |
| [**getById**](DatacentersApi.md#getById) | **GET** /datacenters/{id} | Get a Datacenter |


<a name="getAll"></a>
# **getAll**
> DatacentersGetAllResponse getAll().name(name).sort(sort).page(page).perPage(perPage).execute();

Get all Datacenters

Returns all Datacenter objects.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.DatacentersApi;
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
    String name = "name_example"; // Can be used to filter Datacenters by their name. The response will only contain the Datacenter matching the specified name. When the name does not match the Datacenter name format, an `invalid_input` error is returned.
    String sort = "id"; // Can be used multiple times.
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      DatacentersGetAllResponse result = client
              .datacenters
              .getAll()
              .name(name)
              .sort(sort)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getDatacenters());
      System.out.println(result.getMeta());
      System.out.println(result.getRecommendation());
    } catch (ApiException e) {
      System.err.println("Exception when calling DatacentersApi#getAll");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<DatacentersGetAllResponse> response = client
              .datacenters
              .getAll()
              .name(name)
              .sort(sort)
              .page(page)
              .perPage(perPage)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling DatacentersApi#getAll");
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
| **name** | **String**| Can be used to filter Datacenters by their name. The response will only contain the Datacenter matching the specified name. When the name does not match the Datacenter name format, an &#x60;invalid_input&#x60; error is returned. | [optional] |
| **sort** | **String**| Can be used multiple times. | [optional] [enum: id, id:asc, id:desc, name, name:asc, name:desc] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**DatacentersGetAllResponse**](DatacentersGetAllResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The reply contains the &#x60;datacenters&#x60; and &#x60;recommendation&#x60; keys |  -  |

<a name="getById"></a>
# **getById**
> DatacentersGetByIdResponse getById(id).execute();

Get a Datacenter

Returns a specific Datacenter object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.DatacentersApi;
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
    Long id = 56L; // ID of Datacenter
    try {
      DatacentersGetByIdResponse result = client
              .datacenters
              .getById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getDatacenter());
    } catch (ApiException e) {
      System.err.println("Exception when calling DatacentersApi#getById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<DatacentersGetByIdResponse> response = client
              .datacenters
              .getById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling DatacentersApi#getById");
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
| **id** | **Long**| ID of Datacenter | |

### Return type

[**DatacentersGetByIdResponse**](DatacentersGetByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;datacenter&#x60; key in the reply contains a Datacenter object with this structure |  -  |

