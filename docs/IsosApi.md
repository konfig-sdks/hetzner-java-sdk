# IsosApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**get**](IsosApi.md#get) | **GET** /isos/{id} | Get an ISO |
| [**getAll**](IsosApi.md#getAll) | **GET** /isos | Get all ISOs |


<a name="get"></a>
# **get**
> IsOsGetResponse get(id).execute();

Get an ISO

Returns a specific ISO object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.IsosApi;
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
    Long id = 56L; // ID of the ISO
    try {
      IsOsGetResponse result = client
              .isos
              .get(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getIso());
    } catch (ApiException e) {
      System.err.println("Exception when calling IsosApi#get");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<IsOsGetResponse> response = client
              .isos
              .get(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling IsosApi#get");
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
| **id** | **Long**| ID of the ISO | |

### Return type

[**IsOsGetResponse**](IsOsGetResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;iso&#x60; key in the reply contains an array of ISO objects with this structure |  -  |

<a name="getAll"></a>
# **getAll**
> IsOsGetAllResponse getAll().name(name).architecture(architecture).includeArchitectureWildcard(includeArchitectureWildcard).page(page).perPage(perPage).execute();

Get all ISOs

Returns all available ISO objects.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.IsosApi;
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
    String name = "name_example"; // Can be used to filter ISOs by their name. The response will only contain the ISO matching the specified name.
    String architecture = "architecture_example"; // Return only ISOs with the given architecture.
    Boolean includeArchitectureWildcard = true; // Include Images with wildcard architecture (architecture is null). Works only if architecture filter is specified.
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      IsOsGetAllResponse result = client
              .isos
              .getAll()
              .name(name)
              .architecture(architecture)
              .includeArchitectureWildcard(includeArchitectureWildcard)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getIsos());
      System.out.println(result.getMeta());
    } catch (ApiException e) {
      System.err.println("Exception when calling IsosApi#getAll");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<IsOsGetAllResponse> response = client
              .isos
              .getAll()
              .name(name)
              .architecture(architecture)
              .includeArchitectureWildcard(includeArchitectureWildcard)
              .page(page)
              .perPage(perPage)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling IsosApi#getAll");
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
| **name** | **String**| Can be used to filter ISOs by their name. The response will only contain the ISO matching the specified name. | [optional] |
| **architecture** | **String**| Return only ISOs with the given architecture. | [optional] |
| **includeArchitectureWildcard** | **Boolean**| Include Images with wildcard architecture (architecture is null). Works only if architecture filter is specified. | [optional] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**IsOsGetAllResponse**](IsOsGetAllResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;isos&#x60; key in the reply contains an array of iso objects with this structure |  -  |

