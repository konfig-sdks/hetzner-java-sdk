# LocationsApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAllLocations**](LocationsApi.md#getAllLocations) | **GET** /locations | Get all Locations |
| [**getLocationById**](LocationsApi.md#getLocationById) | **GET** /locations/{id} | Get a Location |


<a name="getAllLocations"></a>
# **getAllLocations**
> LocationsGetAllLocationsResponse getAllLocations().name(name).sort(sort).page(page).perPage(perPage).execute();

Get all Locations

Returns all Location objects.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LocationsApi;
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
    String name = "name_example"; // Can be used to filter Locations by their name. The response will only contain the Location matching the specified name.
    String sort = "id"; // Can be used multiple times.
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      LocationsGetAllLocationsResponse result = client
              .locations
              .getAllLocations()
              .name(name)
              .sort(sort)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getLocations());
      System.out.println(result.getMeta());
    } catch (ApiException e) {
      System.err.println("Exception when calling LocationsApi#getAllLocations");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LocationsGetAllLocationsResponse> response = client
              .locations
              .getAllLocations()
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
      System.err.println("Exception when calling LocationsApi#getAllLocations");
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
| **name** | **String**| Can be used to filter Locations by their name. The response will only contain the Location matching the specified name. | [optional] |
| **sort** | **String**| Can be used multiple times. | [optional] [enum: id, id:asc, id:desc, name, name:asc, name:desc] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**LocationsGetAllLocationsResponse**](LocationsGetAllLocationsResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;locations&#x60; key in the reply contains an array of Location objects with this structure |  -  |

<a name="getLocationById"></a>
# **getLocationById**
> LocationsGetLocationByIdResponse getLocationById(id).execute();

Get a Location

Returns a specific Location object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LocationsApi;
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
    Long id = 56L; // ID of Location
    try {
      LocationsGetLocationByIdResponse result = client
              .locations
              .getLocationById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getLocation());
    } catch (ApiException e) {
      System.err.println("Exception when calling LocationsApi#getLocationById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LocationsGetLocationByIdResponse> response = client
              .locations
              .getLocationById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LocationsApi#getLocationById");
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
| **id** | **Long**| ID of Location | |

### Return type

[**LocationsGetLocationByIdResponse**](LocationsGetLocationByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;location&#x60; key in the reply contains a Location object with this structure |  -  |

