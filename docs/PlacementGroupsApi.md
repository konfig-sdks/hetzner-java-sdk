# PlacementGroupsApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createNewGroup**](PlacementGroupsApi.md#createNewGroup) | **POST** /placement_groups | Create a PlacementGroup |
| [**deleteGroup**](PlacementGroupsApi.md#deleteGroup) | **DELETE** /placement_groups/{id} | Delete a PlacementGroup |
| [**getAll**](PlacementGroupsApi.md#getAll) | **GET** /placement_groups | Get all PlacementGroups |
| [**getById**](PlacementGroupsApi.md#getById) | **GET** /placement_groups/{id} | Get a PlacementGroup |
| [**updateProperties**](PlacementGroupsApi.md#updateProperties) | **PUT** /placement_groups/{id} | Update a PlacementGroup |


<a name="createNewGroup"></a>
# **createNewGroup**
> PlacementGroupsCreateNewGroupResponse createNewGroup().placementGroupsCreateNewGroupRequest(placementGroupsCreateNewGroupRequest).execute();

Create a PlacementGroup

Creates a new PlacementGroup. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.PlacementGroupsApi;
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
    String name = "name_example"; // Name of the PlacementGroup
    String type = "spread"; // Define the Placement Group Type.
    Object labels = null; // User-defined labels (key-value pairs)
    try {
      PlacementGroupsCreateNewGroupResponse result = client
              .placementGroups
              .createNewGroup(name, type)
              .labels(labels)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
      System.out.println(result.getPlacementGroup());
    } catch (ApiException e) {
      System.err.println("Exception when calling PlacementGroupsApi#createNewGroup");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<PlacementGroupsCreateNewGroupResponse> response = client
              .placementGroups
              .createNewGroup(name, type)
              .labels(labels)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling PlacementGroupsApi#createNewGroup");
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
| **placementGroupsCreateNewGroupRequest** | [**PlacementGroupsCreateNewGroupRequest**](PlacementGroupsCreateNewGroupRequest.md)|  | [optional] |

### Return type

[**PlacementGroupsCreateNewGroupResponse**](PlacementGroupsCreateNewGroupResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;PlacementGroup&#x60; key contains the PlacementGroup that was just created. |  -  |

<a name="deleteGroup"></a>
# **deleteGroup**
> deleteGroup(id).execute();

Delete a PlacementGroup

Deletes a PlacementGroup.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.PlacementGroupsApi;
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
    Long id = 42L; // ID of the Resource.
    try {
      client
              .placementGroups
              .deleteGroup(id)
              .execute();
    } catch (ApiException e) {
      System.err.println("Exception when calling PlacementGroupsApi#deleteGroup");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      client
              .placementGroups
              .deleteGroup(id)
              .executeWithHttpInfo();
    } catch (ApiException e) {
      System.err.println("Exception when calling PlacementGroupsApi#deleteGroup");
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
| **id** | **Long**| ID of the Resource. | |

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
| **204** | PlacementGroup deleted |  -  |

<a name="getAll"></a>
# **getAll**
> PlacementGroupsGetAllResponse getAll().sort(sort).name(name).labelSelector(labelSelector).type(type).page(page).perPage(perPage).execute();

Get all PlacementGroups

Returns all PlacementGroup objects.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.PlacementGroupsApi;
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
    String name = "name_example"; // Filter resources by their name. The response will only contain the resources matching the specified name. 
    String labelSelector = "labelSelector_example"; // Filter resources by labels. The response will only contain resources matching the label selector. For more information, see \"[Label Selector](https://docs.hetzner.cloud)\". 
    String type = "spread"; // Can be used multiple times. The response will only contain PlacementGroups matching the type.
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      PlacementGroupsGetAllResponse result = client
              .placementGroups
              .getAll()
              .sort(sort)
              .name(name)
              .labelSelector(labelSelector)
              .type(type)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getMeta());
      System.out.println(result.getPlacementGroups());
    } catch (ApiException e) {
      System.err.println("Exception when calling PlacementGroupsApi#getAll");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<PlacementGroupsGetAllResponse> response = client
              .placementGroups
              .getAll()
              .sort(sort)
              .name(name)
              .labelSelector(labelSelector)
              .type(type)
              .page(page)
              .perPage(perPage)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling PlacementGroupsApi#getAll");
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
| **name** | **String**| Filter resources by their name. The response will only contain the resources matching the specified name.  | [optional] |
| **labelSelector** | **String**| Filter resources by labels. The response will only contain resources matching the label selector. For more information, see \&quot;[Label Selector](https://docs.hetzner.cloud)\&quot;.  | [optional] |
| **type** | **String**| Can be used multiple times. The response will only contain PlacementGroups matching the type. | [optional] [enum: spread] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**PlacementGroupsGetAllResponse**](PlacementGroupsGetAllResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;placement_groups&#x60; key contains an array of PlacementGroup objects |  -  |

<a name="getById"></a>
# **getById**
> PlacementGroupsGetByIdResponse getById(id).execute();

Get a PlacementGroup

Gets a specific PlacementGroup object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.PlacementGroupsApi;
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
    Long id = 42L; // ID of the Resource.
    try {
      PlacementGroupsGetByIdResponse result = client
              .placementGroups
              .getById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getPlacementGroup());
    } catch (ApiException e) {
      System.err.println("Exception when calling PlacementGroupsApi#getById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<PlacementGroupsGetByIdResponse> response = client
              .placementGroups
              .getById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling PlacementGroupsApi#getById");
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
| **id** | **Long**| ID of the Resource. | |

### Return type

[**PlacementGroupsGetByIdResponse**](PlacementGroupsGetByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;placement_group&#x60; key contains a PlacementGroup object |  -  |

<a name="updateProperties"></a>
# **updateProperties**
> PlacementGroupsUpdatePropertiesResponse updateProperties(id).placementGroupsUpdatePropertiesRequest(placementGroupsUpdatePropertiesRequest).execute();

Update a PlacementGroup

Updates the PlacementGroup properties.  Note that when updating labels, the PlacementGroup’s current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body.  Note: if the PlacementGroup object changes during the request, the response will be a “conflict” error. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.PlacementGroupsApi;
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
    Long id = 42L; // ID of the Resource.
    Object labels = null; // User-defined labels (key-value pairs)
    String name = "name_example"; // New PlacementGroup name
    try {
      PlacementGroupsUpdatePropertiesResponse result = client
              .placementGroups
              .updateProperties(id)
              .labels(labels)
              .name(name)
              .execute();
      System.out.println(result);
      System.out.println(result.getPlacementGroup());
    } catch (ApiException e) {
      System.err.println("Exception when calling PlacementGroupsApi#updateProperties");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<PlacementGroupsUpdatePropertiesResponse> response = client
              .placementGroups
              .updateProperties(id)
              .labels(labels)
              .name(name)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling PlacementGroupsApi#updateProperties");
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
| **id** | **Long**| ID of the Resource. | |
| **placementGroupsUpdatePropertiesRequest** | [**PlacementGroupsUpdatePropertiesRequest**](PlacementGroupsUpdatePropertiesRequest.md)|  | [optional] |

### Return type

[**PlacementGroupsUpdatePropertiesResponse**](PlacementGroupsUpdatePropertiesResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;certificate&#x60; key contains the PlacementGroup that was just updated |  -  |

