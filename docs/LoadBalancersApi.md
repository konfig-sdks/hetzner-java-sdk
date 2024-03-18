# LoadBalancersApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createLoadBalancer**](LoadBalancersApi.md#createLoadBalancer) | **POST** /load_balancers | Create a Load Balancer |
| [**deleteLoadBalancer**](LoadBalancersApi.md#deleteLoadBalancer) | **DELETE** /load_balancers/{id} | Delete a Load Balancer |
| [**getAll**](LoadBalancersApi.md#getAll) | **GET** /load_balancers | Get all Load Balancers |
| [**getById**](LoadBalancersApi.md#getById) | **GET** /load_balancers/{id} | Get a Load Balancer |
| [**getMetrics**](LoadBalancersApi.md#getMetrics) | **GET** /load_balancers/{id}/metrics | Get Metrics for a LoadBalancer |
| [**updateBalancer**](LoadBalancersApi.md#updateBalancer) | **PUT** /load_balancers/{id} | Update a Load Balancer |


<a name="createLoadBalancer"></a>
# **createLoadBalancer**
> LoadBalancersCreateLoadBalancerResponse createLoadBalancer().loadBalancersCreateLoadBalancerRequest(loadBalancersCreateLoadBalancerRequest).execute();

Create a Load Balancer

Creates a Load Balancer.  #### Call specific error codes  | Code                                    | Description                                                                                           | |-----------------------------------------|-------------------------------------------------------------------------------------------------------| | &#x60;cloud_resource_ip_not_allowed&#x60;         | The IP you are trying to add as a target belongs to a Hetzner Cloud resource                          | | &#x60;ip_not_owned&#x60;                          | The IP is not owned by the owner of the project of the Load Balancer                                  | | &#x60;load_balancer_not_attached_to_network&#x60; | The Load Balancer is not attached to a network                                                        | | &#x60;robot_unavailable&#x60;                     | Robot was not available. The caller may retry the operation after a short delay.                      | | &#x60;server_not_attached_to_network&#x60;        | The server you are trying to add as a target is not attached to the same network as the Load Balancer | | &#x60;source_port_already_used&#x60;              | The source port you are trying to add is already in use                                               | | &#x60;target_already_defined&#x60;                | The Load Balancer target you are trying to define is already defined                                  | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancersApi;
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
    String loadBalancerType = "loadBalancerType_example"; // ID or name of the Load Balancer type this Load Balancer should be created with
    String name = "name_example"; // Name of the Load Balancer
    LoadBalancerAlgorithmProperty algorithm = new LoadBalancerAlgorithmProperty();
    LoadBalancersCreateLoadBalancerRequestLabels labels = new LoadBalancersCreateLoadBalancerRequestLabels();
    String location = "location_example"; // ID or name of Location to create Load Balancer in
    Long network = 56L; // ID of the network the Load Balancer should be attached to on creation
    String networkZone = "networkZone_example"; // Name of network zone
    Boolean publicInterface = true; // Enable or disable the public interface of the Load Balancer
    List<LoadBalancerService> services = Arrays.asList(); // Array of services
    List<LoadBalancerTarget> targets = Arrays.asList(); // Array of targets
    try {
      LoadBalancersCreateLoadBalancerResponse result = client
              .loadBalancers
              .createLoadBalancer(loadBalancerType, name)
              .algorithm(algorithm)
              .labels(labels)
              .location(location)
              .network(network)
              .networkZone(networkZone)
              .publicInterface(publicInterface)
              .services(services)
              .targets(targets)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
      System.out.println(result.getLoadBalancer());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancersApi#createLoadBalancer");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancersCreateLoadBalancerResponse> response = client
              .loadBalancers
              .createLoadBalancer(loadBalancerType, name)
              .algorithm(algorithm)
              .labels(labels)
              .location(location)
              .network(network)
              .networkZone(networkZone)
              .publicInterface(publicInterface)
              .services(services)
              .targets(targets)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancersApi#createLoadBalancer");
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
| **loadBalancersCreateLoadBalancerRequest** | [**LoadBalancersCreateLoadBalancerRequest**](LoadBalancersCreateLoadBalancerRequest.md)|  | [optional] |

### Return type

[**LoadBalancersCreateLoadBalancerResponse**](LoadBalancersCreateLoadBalancerResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;load_balancer&#x60; key contains the Load Balancer that was just created |  -  |

<a name="deleteLoadBalancer"></a>
# **deleteLoadBalancer**
> deleteLoadBalancer(id).execute();

Delete a Load Balancer

Deletes a Load Balancer.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancersApi;
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
    Long id = 56L; // ID of the Load Balancer
    try {
      client
              .loadBalancers
              .deleteLoadBalancer(id)
              .execute();
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancersApi#deleteLoadBalancer");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      client
              .loadBalancers
              .deleteLoadBalancer(id)
              .executeWithHttpInfo();
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancersApi#deleteLoadBalancer");
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
| **id** | **Long**| ID of the Load Balancer | |

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
| **204** | Load Balancer deleted |  -  |

<a name="getAll"></a>
# **getAll**
> LoadBalancersGetAllResponse getAll().sort(sort).name(name).labelSelector(labelSelector).page(page).perPage(perPage).execute();

Get all Load Balancers

Gets all existing Load Balancers that you have available.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancersApi;
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
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      LoadBalancersGetAllResponse result = client
              .loadBalancers
              .getAll()
              .sort(sort)
              .name(name)
              .labelSelector(labelSelector)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getLoadBalancers());
      System.out.println(result.getMeta());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancersApi#getAll");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancersGetAllResponse> response = client
              .loadBalancers
              .getAll()
              .sort(sort)
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
      System.err.println("Exception when calling LoadBalancersApi#getAll");
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
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**LoadBalancersGetAllResponse**](LoadBalancersGetAllResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;load_balancers&#x60; key contains a list of Load Balancers |  -  |

<a name="getById"></a>
# **getById**
> LoadBalancersGetByIdResponse getById(id).execute();

Get a Load Balancer

Gets a specific Load Balancer object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancersApi;
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
    Long id = 56L; // ID of the Load Balancer
    try {
      LoadBalancersGetByIdResponse result = client
              .loadBalancers
              .getById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getLoadBalancer());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancersApi#getById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancersGetByIdResponse> response = client
              .loadBalancers
              .getById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancersApi#getById");
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
| **id** | **Long**| ID of the Load Balancer | |

### Return type

[**LoadBalancersGetByIdResponse**](LoadBalancersGetByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;load_balancer&#x60; key contains the Load Balancer |  -  |

<a name="getMetrics"></a>
# **getMetrics**
> LoadBalancersGetMetricsResponse getMetrics(id, type, start, end).step(step).execute();

Get Metrics for a LoadBalancer

You must specify the type of metric to get: &#x60;open_connections&#x60;, &#x60;connections_per_second&#x60;, &#x60;requests_per_second&#x60; or &#x60;bandwidth&#x60;. You can also specify more than one type by comma separation, e.g. &#x60;requests_per_second,bandwidth&#x60;.  Depending on the type you will get different time series data:  |Type | Timeseries | Unit | Description | |---- |------------|------|-------------| | open_connections | open_connections | number | Open connections | | connections_per_second | connections_per_second | connections/s | Connections per second | | requests_per_second | requests_per_second | requests/s | Requests per second | | bandwidth | bandwidth.in | bytes/s | Ingress bandwidth | || bandwidth.out | bytes/s | Egress bandwidth |  Metrics are available for the last 30 days only.  If you do not provide the step argument we will automatically adjust it so that 200 samples are returned.  We limit the number of samples to a maximum of 500 and will adjust the step parameter accordingly. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancersApi;
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
    Long id = 56L; // ID of the Load Balancer
    String type = "open_connections"; // Type of metrics to get
    String start = "start_example"; // Start of period to get Metrics for (in ISO-8601 format)
    String end = "end_example"; // End of period to get Metrics for (in ISO-8601 format)
    String step = "step_example"; // Resolution of results in seconds
    try {
      LoadBalancersGetMetricsResponse result = client
              .loadBalancers
              .getMetrics(id, type, start, end)
              .step(step)
              .execute();
      System.out.println(result);
      System.out.println(result.getMetrics());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancersApi#getMetrics");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancersGetMetricsResponse> response = client
              .loadBalancers
              .getMetrics(id, type, start, end)
              .step(step)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancersApi#getMetrics");
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
| **id** | **Long**| ID of the Load Balancer | |
| **type** | **String**| Type of metrics to get | [enum: open_connections, connections_per_second, requests_per_second, bandwidth] |
| **start** | **String**| Start of period to get Metrics for (in ISO-8601 format) | |
| **end** | **String**| End of period to get Metrics for (in ISO-8601 format) | |
| **step** | **String**| Resolution of results in seconds | [optional] |

### Return type

[**LoadBalancersGetMetricsResponse**](LoadBalancersGetMetricsResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;metrics&#x60; key in the reply contains a metrics object with this structure |  -  |

<a name="updateBalancer"></a>
# **updateBalancer**
> LoadBalancersUpdateBalancerResponse updateBalancer(id).loadBalancersUpdateBalancerRequest(loadBalancersUpdateBalancerRequest).execute();

Update a Load Balancer

Updates a Load Balancer. You can update a Load Balancer’s name and a Load Balancer’s labels.  Note that when updating labels, the Load Balancer’s current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body.  Note: if the Load Balancer object changes during the request, the response will be a “conflict” error. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancersApi;
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
    Long id = 56L; // ID of the Load Balancer
    Object labels = null; // User-defined labels (key-value pairs)
    String name = "name_example"; // New Load Balancer name
    try {
      LoadBalancersUpdateBalancerResponse result = client
              .loadBalancers
              .updateBalancer(id)
              .labels(labels)
              .name(name)
              .execute();
      System.out.println(result);
      System.out.println(result.getLoadBalancer());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancersApi#updateBalancer");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancersUpdateBalancerResponse> response = client
              .loadBalancers
              .updateBalancer(id)
              .labels(labels)
              .name(name)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancersApi#updateBalancer");
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
| **id** | **Long**| ID of the Load Balancer | |
| **loadBalancersUpdateBalancerRequest** | [**LoadBalancersUpdateBalancerRequest**](LoadBalancersUpdateBalancerRequest.md)|  | [optional] |

### Return type

[**LoadBalancersUpdateBalancerResponse**](LoadBalancersUpdateBalancerResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;load_balancer&#x60; key contains the updated Load Balancer |  -  |

