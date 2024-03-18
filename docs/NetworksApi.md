# NetworksApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createNetwork**](NetworksApi.md#createNetwork) | **POST** /networks | Create a Network |
| [**deleteNetwork**](NetworksApi.md#deleteNetwork) | **DELETE** /networks/{id} | Delete a Network |
| [**getAll**](NetworksApi.md#getAll) | **GET** /networks | Get all Networks |
| [**getById**](NetworksApi.md#getById) | **GET** /networks/{id} | Get a Network |
| [**updateProperties**](NetworksApi.md#updateProperties) | **PUT** /networks/{id} | Update a Network |


<a name="createNetwork"></a>
# **createNetwork**
> NetworksCreateNetworkResponse createNetwork().networksCreateNetworkRequest(networksCreateNetworkRequest).execute();

Create a Network

Creates a network with the specified &#x60;ip_range&#x60;.  You may specify one or more &#x60;subnets&#x60;. You can also add more Subnets later by using the [add subnet action](https://docs.hetzner.cloud/#network-actions-add-a-subnet-to-a-network). If you do not specify an &#x60;ip_range&#x60; in the subnet we will automatically pick the first available /24 range for you.  You may specify one or more routes in &#x60;routes&#x60;. You can also add more routes later by using the [add route action](https://docs.hetzner.cloud/#network-actions-add-a-route-to-a-network). 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.NetworksApi;
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
    String ipRange = "ipRange_example"; // IP range of the whole network which must span all included subnets. Must be one of the private IPv4 ranges of RFC1918. Minimum network size is /24. We highly recommend that you pick a larger network with a /16 netmask.
    String name = "name_example"; // Name of the network
    Boolean exposeRoutesToVswitch = true; // Indicates if the routes from this network should be exposed to the vSwitch connection. The exposing only takes effect if a vSwitch connection is active.
    NetworksCreateNetworkRequestLabels labels = new NetworksCreateNetworkRequestLabels();
    List<NetworksCreateNetworkRequestRoutesInner> routes = Arrays.asList(); // Array of routes set in this network. The destination of the route must be one of the private IPv4 ranges of RFC1918. The gateway must be a subnet/IP of the ip_range of the network object. The destination must not overlap with an existing ip_range in any subnets or with any destinations in other routes or with the first IP of the networks ip_range or with 172.31.1.1. The gateway cannot be the first IP of the networks ip_range and also cannot be 172.31.1.1.
    List<NetworksCreateNetworkRequestSubnetsInner> subnets = Arrays.asList(); // Array of subnets allocated.
    try {
      NetworksCreateNetworkResponse result = client
              .networks
              .createNetwork(ipRange, name)
              .exposeRoutesToVswitch(exposeRoutesToVswitch)
              .labels(labels)
              .routes(routes)
              .subnets(subnets)
              .execute();
      System.out.println(result);
      System.out.println(result.getNetwork());
    } catch (ApiException e) {
      System.err.println("Exception when calling NetworksApi#createNetwork");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<NetworksCreateNetworkResponse> response = client
              .networks
              .createNetwork(ipRange, name)
              .exposeRoutesToVswitch(exposeRoutesToVswitch)
              .labels(labels)
              .routes(routes)
              .subnets(subnets)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling NetworksApi#createNetwork");
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
| **networksCreateNetworkRequest** | [**NetworksCreateNetworkRequest**](NetworksCreateNetworkRequest.md)|  | [optional] |

### Return type

[**NetworksCreateNetworkResponse**](NetworksCreateNetworkResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;network&#x60; key contains the network that was just created |  -  |

<a name="deleteNetwork"></a>
# **deleteNetwork**
> deleteNetwork(id).execute();

Delete a Network

Deletes a network. If there are Servers attached they will be detached in the background.  Note: if the network object changes during the request, the response will be a “conflict” error. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.NetworksApi;
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
    Long id = 56L; // ID of the network
    try {
      client
              .networks
              .deleteNetwork(id)
              .execute();
    } catch (ApiException e) {
      System.err.println("Exception when calling NetworksApi#deleteNetwork");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      client
              .networks
              .deleteNetwork(id)
              .executeWithHttpInfo();
    } catch (ApiException e) {
      System.err.println("Exception when calling NetworksApi#deleteNetwork");
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
| **id** | **Long**| ID of the network | |

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
| **204** | Network deleted |  -  |

<a name="getAll"></a>
# **getAll**
> NetworksGetAllResponse getAll().name(name).labelSelector(labelSelector).page(page).perPage(perPage).execute();

Get all Networks

Gets all existing networks that you have available.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.NetworksApi;
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
    String name = "name_example"; // Filter resources by their name. The response will only contain the resources matching the specified name. 
    String labelSelector = "labelSelector_example"; // Filter resources by labels. The response will only contain resources matching the label selector. For more information, see \"[Label Selector](https://docs.hetzner.cloud)\". 
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      NetworksGetAllResponse result = client
              .networks
              .getAll()
              .name(name)
              .labelSelector(labelSelector)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getMeta());
      System.out.println(result.getNetworks());
    } catch (ApiException e) {
      System.err.println("Exception when calling NetworksApi#getAll");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<NetworksGetAllResponse> response = client
              .networks
              .getAll()
              .name(name)
              .labelSelector(labelSelector)
              .page(page)
              .perPage(perPage)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling NetworksApi#getAll");
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
| **name** | **String**| Filter resources by their name. The response will only contain the resources matching the specified name.  | [optional] |
| **labelSelector** | **String**| Filter resources by labels. The response will only contain resources matching the label selector. For more information, see \&quot;[Label Selector](https://docs.hetzner.cloud)\&quot;.  | [optional] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**NetworksGetAllResponse**](NetworksGetAllResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;networks&#x60; key contains a list of networks |  -  |

<a name="getById"></a>
# **getById**
> NetworksGetByIdResponse getById(id).execute();

Get a Network

Gets a specific network object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.NetworksApi;
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
    Long id = 56L; // ID of the network
    try {
      NetworksGetByIdResponse result = client
              .networks
              .getById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getNetwork());
    } catch (ApiException e) {
      System.err.println("Exception when calling NetworksApi#getById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<NetworksGetByIdResponse> response = client
              .networks
              .getById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling NetworksApi#getById");
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
| **id** | **Long**| ID of the network | |

### Return type

[**NetworksGetByIdResponse**](NetworksGetByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;network&#x60; key contains the network |  -  |

<a name="updateProperties"></a>
# **updateProperties**
> NetworksUpdatePropertiesResponse updateProperties(id).networksUpdatePropertiesRequest(networksUpdatePropertiesRequest).execute();

Update a Network

Updates the network properties.  Note that when updating labels, the network’s current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body.  Note: if the network object changes during the request, the response will be a “conflict” error. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.NetworksApi;
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
    Long id = 56L; // ID of the network
    Boolean exposeRoutesToVswitch = true; // Indicates if the routes from this network should be exposed to the vSwitch connection. The exposing only takes effect if a vSwitch connection is active.
    NetworksUpdatePropertiesRequestLabels labels = new NetworksUpdatePropertiesRequestLabels();
    String name = "name_example"; // New network name
    try {
      NetworksUpdatePropertiesResponse result = client
              .networks
              .updateProperties(id)
              .exposeRoutesToVswitch(exposeRoutesToVswitch)
              .labels(labels)
              .name(name)
              .execute();
      System.out.println(result);
      System.out.println(result.getNetwork());
    } catch (ApiException e) {
      System.err.println("Exception when calling NetworksApi#updateProperties");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<NetworksUpdatePropertiesResponse> response = client
              .networks
              .updateProperties(id)
              .exposeRoutesToVswitch(exposeRoutesToVswitch)
              .labels(labels)
              .name(name)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling NetworksApi#updateProperties");
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
| **id** | **Long**| ID of the network | |
| **networksUpdatePropertiesRequest** | [**NetworksUpdatePropertiesRequest**](NetworksUpdatePropertiesRequest.md)|  | [optional] |

### Return type

[**NetworksUpdatePropertiesResponse**](NetworksUpdatePropertiesResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;network&#x60; key contains the updated network |  -  |

