# LoadBalancerTypesApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAllTypes**](LoadBalancerTypesApi.md#getAllTypes) | **GET** /load_balancer_types | Get all Load Balancer Types |
| [**getById**](LoadBalancerTypesApi.md#getById) | **GET** /load_balancer_types/{id} | Get a Load Balancer Type |


<a name="getAllTypes"></a>
# **getAllTypes**
> LoadBalancerTypesGetAllTypesResponse getAllTypes().name(name).page(page).perPage(perPage).execute();

Get all Load Balancer Types

Gets all Load Balancer type objects.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerTypesApi;
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
    String name = "name_example"; // Can be used to filter Load Balancer types by their name. The response will only contain the Load Balancer type matching the specified name.
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      LoadBalancerTypesGetAllTypesResponse result = client
              .loadBalancerTypes
              .getAllTypes()
              .name(name)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getLoadBalancerTypes());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerTypesApi#getAllTypes");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerTypesGetAllTypesResponse> response = client
              .loadBalancerTypes
              .getAllTypes()
              .name(name)
              .page(page)
              .perPage(perPage)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerTypesApi#getAllTypes");
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
| **name** | **String**| Can be used to filter Load Balancer types by their name. The response will only contain the Load Balancer type matching the specified name. | [optional] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**LoadBalancerTypesGetAllTypesResponse**](LoadBalancerTypesGetAllTypesResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;load_balancer_types&#x60; key in the reply contains an array of Load Balancer type objects with this structure |  -  |

<a name="getById"></a>
# **getById**
> LoadBalancerTypesGetByIdResponse getById(id).execute();

Get a Load Balancer Type

Gets a specific Load Balancer type object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerTypesApi;
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
    Integer id = 56; // ID of Load Balancer type
    try {
      LoadBalancerTypesGetByIdResponse result = client
              .loadBalancerTypes
              .getById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getLoadBalancerType());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerTypesApi#getById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerTypesGetByIdResponse> response = client
              .loadBalancerTypes
              .getById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerTypesApi#getById");
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
| **id** | **Integer**| ID of Load Balancer type | |

### Return type

[**LoadBalancerTypesGetByIdResponse**](LoadBalancerTypesGetByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;load_balancer_type&#x60; key in the reply contains a Load Balancer type object with this structure |  -  |

