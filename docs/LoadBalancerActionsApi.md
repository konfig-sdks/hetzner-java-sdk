# LoadBalancerActionsApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addService**](LoadBalancerActionsApi.md#addService) | **POST** /load_balancers/{id}/actions/add_service | Add Service |
| [**addTarget**](LoadBalancerActionsApi.md#addTarget) | **POST** /load_balancers/{id}/actions/add_target | Add Target |
| [**attachToNetwork**](LoadBalancerActionsApi.md#attachToNetwork) | **POST** /load_balancers/{id}/actions/attach_to_network | Attach a Load Balancer to a Network |
| [**changeAlgorithm**](LoadBalancerActionsApi.md#changeAlgorithm) | **POST** /load_balancers/{id}/actions/change_algorithm | Change Algorithm |
| [**changeDnsPtr**](LoadBalancerActionsApi.md#changeDnsPtr) | **POST** /load_balancers/{id}/actions/change_dns_ptr | Change reverse DNS entry for this Load Balancer |
| [**changeProtection**](LoadBalancerActionsApi.md#changeProtection) | **POST** /load_balancers/{id}/actions/change_protection | Change Load Balancer Protection |
| [**changeType**](LoadBalancerActionsApi.md#changeType) | **POST** /load_balancers/{id}/actions/change_type | Change the Type of a Load Balancer |
| [**deleteService**](LoadBalancerActionsApi.md#deleteService) | **POST** /load_balancers/{id}/actions/delete_service | Delete Service |
| [**detachFromNetwork**](LoadBalancerActionsApi.md#detachFromNetwork) | **POST** /load_balancers/{id}/actions/detach_from_network | Detach a Load Balancer from a Network |
| [**disablePublicInterface**](LoadBalancerActionsApi.md#disablePublicInterface) | **POST** /load_balancers/{id}/actions/disable_public_interface | Disable the public interface of a Load Balancer |
| [**enablePublicInterface**](LoadBalancerActionsApi.md#enablePublicInterface) | **POST** /load_balancers/{id}/actions/enable_public_interface | Enable the public interface of a Load Balancer |
| [**getAllActions**](LoadBalancerActionsApi.md#getAllActions) | **GET** /load_balancers/actions | Get all Actions |
| [**getAllActions_0**](LoadBalancerActionsApi.md#getAllActions_0) | **GET** /load_balancers/{id}/actions | Get all Actions for a Load Balancer |
| [**getSpecificAction**](LoadBalancerActionsApi.md#getSpecificAction) | **GET** /load_balancers/actions/{id} | Get an Action |
| [**getSpecificAction_0**](LoadBalancerActionsApi.md#getSpecificAction_0) | **GET** /load_balancers/{id}/actions/{action_id} | Get an Action for a Load Balancer |
| [**removeTarget**](LoadBalancerActionsApi.md#removeTarget) | **POST** /load_balancers/{id}/actions/remove_target | Remove Target |
| [**updateService**](LoadBalancerActionsApi.md#updateService) | **POST** /load_balancers/{id}/actions/update_service | Update Service |


<a name="addService"></a>
# **addService**
> LoadBalancerActionsAddServiceResponse addService(id).loadBalancerActionsAddServiceRequest(loadBalancerActionsAddServiceRequest).execute();

Add Service

Adds a service to a Load Balancer.  #### Call specific error codes  | Code                       | Description                                             | |----------------------------|---------------------------------------------------------| | &#x60;source_port_already_used&#x60; | The source port you are trying to add is already in use | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    Integer destinationPort = 56; // Port the Load Balancer will balance to
    LoadBalancerServiceHealthCheckProperty1 healthCheck = new LoadBalancerServiceHealthCheckProperty1();
    Integer listenPort = 56; // Port the Load Balancer listens on
    String protocol = "tcp"; // Protocol of the Load Balancer
    Boolean proxyprotocol = true; // Is Proxyprotocol enabled or not
    Long id = 56L; // ID of the Load Balancer
    LoadBalancerServiceHTTPProperty1 http = new LoadBalancerServiceHTTPProperty1();
    try {
      LoadBalancerActionsAddServiceResponse result = client
              .loadBalancerActions
              .addService(destinationPort, healthCheck, listenPort, protocol, proxyprotocol, id)
              .http(http)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#addService");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsAddServiceResponse> response = client
              .loadBalancerActions
              .addService(destinationPort, healthCheck, listenPort, protocol, proxyprotocol, id)
              .http(http)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#addService");
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
| **loadBalancerActionsAddServiceRequest** | [**LoadBalancerActionsAddServiceRequest**](LoadBalancerActionsAddServiceRequest.md)|  | [optional] |

### Return type

[**LoadBalancerActionsAddServiceResponse**](LoadBalancerActionsAddServiceResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the &#x60;add_service&#x60; Action |  -  |

<a name="addTarget"></a>
# **addTarget**
> LoadBalancerActionsAddTargetResponse addTarget(id).loadBalancerActionsAddTargetRequest(loadBalancerActionsAddTargetRequest).execute();

Add Target

Adds a target to a Load Balancer.  #### Call specific error codes  | Code                                    | Description                                                                                           | |-----------------------------------------|-------------------------------------------------------------------------------------------------------| | &#x60;cloud_resource_ip_not_allowed&#x60;         | The IP you are trying to add as a target belongs to a Hetzner Cloud resource                          | | &#x60;ip_not_owned&#x60;                          | The IP you are trying to add as a target is not owned by the Project owner                            | | &#x60;load_balancer_not_attached_to_network&#x60; | The Load Balancer is not attached to a network                                                        | | &#x60;robot_unavailable&#x60;                     | Robot was not available. The caller may retry the operation after a short delay.                      | | &#x60;server_not_attached_to_network&#x60;        | The server you are trying to add as a target is not attached to the same network as the Load Balancer | | &#x60;target_already_defined&#x60;                | The Load Balancer target you are trying to define is already defined                                  | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    String type = "server"; // Type of the resource
    Long id = 56L; // ID of the Load Balancer
    LoadBalancerTargetIPProperty1 ip = new LoadBalancerTargetIPProperty1();
    LoadBalancerActionsAddTargetRequestLabelSelector labelSelector = new LoadBalancerActionsAddTargetRequestLabelSelector();
    LoadBalancerActionsAddTargetRequestServer server = new LoadBalancerActionsAddTargetRequestServer();
    Boolean usePrivateIp = false; // Use the private network IP instead of the public IP of the Server, requires the Server and Load Balancer to be in the same network.
    try {
      LoadBalancerActionsAddTargetResponse result = client
              .loadBalancerActions
              .addTarget(type, id)
              .ip(ip)
              .labelSelector(labelSelector)
              .server(server)
              .usePrivateIp(usePrivateIp)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#addTarget");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsAddTargetResponse> response = client
              .loadBalancerActions
              .addTarget(type, id)
              .ip(ip)
              .labelSelector(labelSelector)
              .server(server)
              .usePrivateIp(usePrivateIp)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#addTarget");
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
| **loadBalancerActionsAddTargetRequest** | [**LoadBalancerActionsAddTargetRequest**](LoadBalancerActionsAddTargetRequest.md)|  | [optional] |

### Return type

[**LoadBalancerActionsAddTargetResponse**](LoadBalancerActionsAddTargetResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the &#x60;add_target&#x60; Action |  -  |

<a name="attachToNetwork"></a>
# **attachToNetwork**
> LoadBalancerActionsAttachToNetworkResponse attachToNetwork(id).loadBalancerActionsAttachToNetworkRequest(loadBalancerActionsAttachToNetworkRequest).execute();

Attach a Load Balancer to a Network

Attach a Load Balancer to a Network.  **Call specific error codes**  | Code                             | Description                                                           | |----------------------------------|-----------------------------------------------------------------------| | &#x60;load_balancer_already_attached&#x60; | The Load Balancer is already attached to a network                    | | &#x60;ip_not_available&#x60;               | The provided Network IP is not available                              | | &#x60;no_subnet_available&#x60;            | No Subnet or IP is available for the Load Balancer within the network | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    Long network = 56L; // ID of an existing network to attach the Load Balancer to
    Long id = 56L; // ID of the Load Balancer
    String ip = "ip_example"; // IP to request to be assigned to this Load Balancer; if you do not provide this then you will be auto assigned an IP address
    try {
      LoadBalancerActionsAttachToNetworkResponse result = client
              .loadBalancerActions
              .attachToNetwork(network, id)
              .ip(ip)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#attachToNetwork");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsAttachToNetworkResponse> response = client
              .loadBalancerActions
              .attachToNetwork(network, id)
              .ip(ip)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#attachToNetwork");
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
| **loadBalancerActionsAttachToNetworkRequest** | [**LoadBalancerActionsAttachToNetworkRequest**](LoadBalancerActionsAttachToNetworkRequest.md)|  | [optional] |

### Return type

[**LoadBalancerActionsAttachToNetworkResponse**](LoadBalancerActionsAttachToNetworkResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the &#x60;attach_to_network&#x60; Action |  -  |

<a name="changeAlgorithm"></a>
# **changeAlgorithm**
> LoadBalancerActionsChangeAlgorithmResponse changeAlgorithm(id).loadBalancerActionsChangeAlgorithmRequest(loadBalancerActionsChangeAlgorithmRequest).execute();

Change Algorithm

Change the algorithm that determines to which target new requests are sent.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    String type = "round_robin"; // Algorithm of the Load Balancer
    Long id = 56L; // ID of the Load Balancer
    try {
      LoadBalancerActionsChangeAlgorithmResponse result = client
              .loadBalancerActions
              .changeAlgorithm(type, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#changeAlgorithm");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsChangeAlgorithmResponse> response = client
              .loadBalancerActions
              .changeAlgorithm(type, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#changeAlgorithm");
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
| **loadBalancerActionsChangeAlgorithmRequest** | [**LoadBalancerActionsChangeAlgorithmRequest**](LoadBalancerActionsChangeAlgorithmRequest.md)|  | [optional] |

### Return type

[**LoadBalancerActionsChangeAlgorithmResponse**](LoadBalancerActionsChangeAlgorithmResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the &#x60;change_algorithm&#x60; Action |  -  |

<a name="changeDnsPtr"></a>
# **changeDnsPtr**
> LoadBalancerActionsChangeDnsPtrResponse changeDnsPtr(id).loadBalancerActionsChangeDnsPtrRequest(loadBalancerActionsChangeDnsPtrRequest).execute();

Change reverse DNS entry for this Load Balancer

Changes the hostname that will appear when getting the hostname belonging to the public IPs (IPv4 and IPv6) of this Load Balancer.  Floating IPs assigned to the Server are not affected by this. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    String dnsPtr = "dnsPtr_example"; // Hostname to set as a reverse DNS PTR entry
    String ip = "ip_example"; // Public IP address for which the reverse DNS entry should be set
    Long id = 56L; // ID of the Load Balancer
    try {
      LoadBalancerActionsChangeDnsPtrResponse result = client
              .loadBalancerActions
              .changeDnsPtr(dnsPtr, ip, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#changeDnsPtr");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsChangeDnsPtrResponse> response = client
              .loadBalancerActions
              .changeDnsPtr(dnsPtr, ip, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#changeDnsPtr");
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
| **loadBalancerActionsChangeDnsPtrRequest** | [**LoadBalancerActionsChangeDnsPtrRequest**](LoadBalancerActionsChangeDnsPtrRequest.md)| Select the IP address for which to change the DNS entry by passing &#x60;ip&#x60;. It can be either IPv4 or IPv6. The target hostname is set by passing &#x60;dns_ptr&#x60;. | [optional] |

### Return type

[**LoadBalancerActionsChangeDnsPtrResponse**](LoadBalancerActionsChangeDnsPtrResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="changeProtection"></a>
# **changeProtection**
> LoadBalancerActionsChangeProtectionResponse changeProtection(id).loadBalancerActionsChangeProtectionRequest(loadBalancerActionsChangeProtectionRequest).execute();

Change Load Balancer Protection

Changes the protection configuration of a Load Balancer.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    Boolean delete = true; // If true, prevents the Load Balancer from being deleted
    try {
      LoadBalancerActionsChangeProtectionResponse result = client
              .loadBalancerActions
              .changeProtection(id)
              .delete(delete)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#changeProtection");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsChangeProtectionResponse> response = client
              .loadBalancerActions
              .changeProtection(id)
              .delete(delete)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#changeProtection");
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
| **loadBalancerActionsChangeProtectionRequest** | [**LoadBalancerActionsChangeProtectionRequest**](LoadBalancerActionsChangeProtectionRequest.md)|  | [optional] |

### Return type

[**LoadBalancerActionsChangeProtectionResponse**](LoadBalancerActionsChangeProtectionResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the &#x60;change_protection&#x60; Action |  -  |

<a name="changeType"></a>
# **changeType**
> LoadBalancerActionsChangeTypeResponse changeType(id).loadBalancerActionsChangeTypeRequest(loadBalancerActionsChangeTypeRequest).execute();

Change the Type of a Load Balancer

Changes the type (Max Services, Max Targets and Max Connections) of a Load Balancer.  **Call specific error codes**  | Code                         | Description                                                     | |------------------------------|-----------------------------------------------------------------| | &#x60;invalid_load_balancer_type&#x60; | The Load Balancer type does not fit for the given Load Balancer | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    String loadBalancerType = "loadBalancerType_example"; // ID or name of Load Balancer type the Load Balancer should migrate to
    Long id = 56L; // ID of the Load Balancer
    try {
      LoadBalancerActionsChangeTypeResponse result = client
              .loadBalancerActions
              .changeType(loadBalancerType, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#changeType");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsChangeTypeResponse> response = client
              .loadBalancerActions
              .changeType(loadBalancerType, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#changeType");
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
| **loadBalancerActionsChangeTypeRequest** | [**LoadBalancerActionsChangeTypeRequest**](LoadBalancerActionsChangeTypeRequest.md)|  | [optional] |

### Return type

[**LoadBalancerActionsChangeTypeResponse**](LoadBalancerActionsChangeTypeResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the &#x60;change_load_balancer_type&#x60; Action |  -  |

<a name="deleteService"></a>
# **deleteService**
> LoadBalancerActionsDeleteServiceResponse deleteService(id).loadBalancerActionsDeleteServiceRequest(loadBalancerActionsDeleteServiceRequest).execute();

Delete Service

Delete a service of a Load Balancer.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    Integer listenPort = 56; // The listen port of the service you want to delete
    Long id = 56L; // ID of the Load Balancer
    try {
      LoadBalancerActionsDeleteServiceResponse result = client
              .loadBalancerActions
              .deleteService(listenPort, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#deleteService");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsDeleteServiceResponse> response = client
              .loadBalancerActions
              .deleteService(listenPort, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#deleteService");
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
| **loadBalancerActionsDeleteServiceRequest** | [**LoadBalancerActionsDeleteServiceRequest**](LoadBalancerActionsDeleteServiceRequest.md)|  | [optional] |

### Return type

[**LoadBalancerActionsDeleteServiceResponse**](LoadBalancerActionsDeleteServiceResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the &#x60;delete_service&#x60; Action |  -  |

<a name="detachFromNetwork"></a>
# **detachFromNetwork**
> LoadBalancerActionsDetachFromNetworkResponse detachFromNetwork(id).loadBalancerActionsDetachFromNetworkRequest(loadBalancerActionsDetachFromNetworkRequest).execute();

Detach a Load Balancer from a Network

Detaches a Load Balancer from a network.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    Long network = 56L; // ID of an existing network to detach the Load Balancer from
    Long id = 56L; // ID of the Load Balancer
    try {
      LoadBalancerActionsDetachFromNetworkResponse result = client
              .loadBalancerActions
              .detachFromNetwork(network, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#detachFromNetwork");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsDetachFromNetworkResponse> response = client
              .loadBalancerActions
              .detachFromNetwork(network, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#detachFromNetwork");
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
| **loadBalancerActionsDetachFromNetworkRequest** | [**LoadBalancerActionsDetachFromNetworkRequest**](LoadBalancerActionsDetachFromNetworkRequest.md)|  | [optional] |

### Return type

[**LoadBalancerActionsDetachFromNetworkResponse**](LoadBalancerActionsDetachFromNetworkResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the &#x60;detach_from_network&#x60; Action |  -  |

<a name="disablePublicInterface"></a>
# **disablePublicInterface**
> LoadBalancerActionsDisablePublicInterfaceResponse disablePublicInterface(id).execute();

Disable the public interface of a Load Balancer

Disable the public interface of a Load Balancer. The Load Balancer will be not accessible from the internet via its public IPs.  #### Call specific error codes  | Code                                      | Description                                                                    | |-------------------------------------------|--------------------------------------------------------------------------------| | &#x60;load_balancer_not_attached_to_network&#x60;   |  The Load Balancer is not attached to a network                                | | &#x60;targets_without_use_private_ip&#x60;          | The Load Balancer has targets that use the public IP instead of the private IP | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
      LoadBalancerActionsDisablePublicInterfaceResponse result = client
              .loadBalancerActions
              .disablePublicInterface(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#disablePublicInterface");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsDisablePublicInterfaceResponse> response = client
              .loadBalancerActions
              .disablePublicInterface(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#disablePublicInterface");
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

[**LoadBalancerActionsDisablePublicInterfaceResponse**](LoadBalancerActionsDisablePublicInterfaceResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the &#x60;disable_public_interface&#x60; Action |  -  |

<a name="enablePublicInterface"></a>
# **enablePublicInterface**
> LoadBalancerActionsEnablePublicInterfaceResponse enablePublicInterface(id).execute();

Enable the public interface of a Load Balancer

Enable the public interface of a Load Balancer. The Load Balancer will be accessible from the internet via its public IPs.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
      LoadBalancerActionsEnablePublicInterfaceResponse result = client
              .loadBalancerActions
              .enablePublicInterface(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#enablePublicInterface");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsEnablePublicInterfaceResponse> response = client
              .loadBalancerActions
              .enablePublicInterface(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#enablePublicInterface");
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

[**LoadBalancerActionsEnablePublicInterfaceResponse**](LoadBalancerActionsEnablePublicInterfaceResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the &#x60;enable_public_interface&#x60; Action |  -  |

<a name="getAllActions"></a>
# **getAllActions**
> LoadBalancerActionsGetAllActionsResponse getAllActions().id(id).sort(sort).status(status).page(page).perPage(perPage).execute();

Get all Actions

Returns all Action objects. You can &#x60;sort&#x60; the results by using the sort URI parameter, and filter them with the &#x60;status&#x60; and &#x60;id&#x60; parameter.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    Long id = 42L; // Filter the actions by ID. Can be used multiple times. The response will only contain actions matching the specified IDs. 
    String sort = "id"; // Sort actions by field and direction. Can be used multiple times. For more information, see \"[Sorting](https://docs.hetzner.cloud)\". 
    String status = "running"; // Filter the actions by status. Can be used multiple times. The response will only contain actions matching the specified statuses. 
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      LoadBalancerActionsGetAllActionsResponse result = client
              .loadBalancerActions
              .getAllActions()
              .id(id)
              .sort(sort)
              .status(status)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getActions());
      System.out.println(result.getMeta());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#getAllActions");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsGetAllActionsResponse> response = client
              .loadBalancerActions
              .getAllActions()
              .id(id)
              .sort(sort)
              .status(status)
              .page(page)
              .perPage(perPage)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#getAllActions");
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
| **id** | **Long**| Filter the actions by ID. Can be used multiple times. The response will only contain actions matching the specified IDs.  | [optional] |
| **sort** | **String**| Sort actions by field and direction. Can be used multiple times. For more information, see \&quot;[Sorting](https://docs.hetzner.cloud)\&quot;.  | [optional] [enum: id, id:asc, id:desc, command, command:asc, command:desc, status, status:asc, status:desc, started, started:asc, started:desc, finished, finished:asc, finished:desc] |
| **status** | **String**| Filter the actions by status. Can be used multiple times. The response will only contain actions matching the specified statuses.  | [optional] [enum: running, success, error] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**LoadBalancerActionsGetAllActionsResponse**](LoadBalancerActionsGetAllActionsResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;actions&#x60; key contains a list of Actions |  -  |

<a name="getAllActions_0"></a>
# **getAllActions_0**
> LoadBalancerActionsGetAllActions200Response getAllActions_0(id).sort(sort).status(status).page(page).perPage(perPage).execute();

Get all Actions for a Load Balancer

Returns all Action objects for a Load Balancer. You can sort the results by using the &#x60;sort&#x60; URI parameter, and filter them with the &#x60;status&#x60; parameter.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    String sort = "id"; // Sort actions by field and direction. Can be used multiple times. For more information, see \"[Sorting](https://docs.hetzner.cloud)\". 
    String status = "running"; // Filter the actions by status. Can be used multiple times. The response will only contain actions matching the specified statuses. 
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      LoadBalancerActionsGetAllActions200Response result = client
              .loadBalancerActions
              .getAllActions_0(id)
              .sort(sort)
              .status(status)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getActions());
      System.out.println(result.getMeta());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#getAllActions_0");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsGetAllActions200Response> response = client
              .loadBalancerActions
              .getAllActions_0(id)
              .sort(sort)
              .status(status)
              .page(page)
              .perPage(perPage)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#getAllActions_0");
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
| **sort** | **String**| Sort actions by field and direction. Can be used multiple times. For more information, see \&quot;[Sorting](https://docs.hetzner.cloud)\&quot;.  | [optional] [enum: id, id:asc, id:desc, command, command:asc, command:desc, status, status:asc, status:desc, started, started:asc, started:desc, finished, finished:asc, finished:desc] |
| **status** | **String**| Filter the actions by status. Can be used multiple times. The response will only contain actions matching the specified statuses.  | [optional] [enum: running, success, error] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**LoadBalancerActionsGetAllActions200Response**](LoadBalancerActionsGetAllActions200Response.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;actions&#x60; key contains a list of Actions |  -  |

<a name="getSpecificAction"></a>
# **getSpecificAction**
> LoadBalancerActionsGetSpecificActionResponse getSpecificAction(id).execute();

Get an Action

Returns a specific Action object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    Long id = 42L; // ID of the Action.
    try {
      LoadBalancerActionsGetSpecificActionResponse result = client
              .loadBalancerActions
              .getSpecificAction(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#getSpecificAction");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsGetSpecificActionResponse> response = client
              .loadBalancerActions
              .getSpecificAction(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#getSpecificAction");
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
| **id** | **Long**| ID of the Action. | |

### Return type

[**LoadBalancerActionsGetSpecificActionResponse**](LoadBalancerActionsGetSpecificActionResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;action&#x60; key in the reply has this structure |  -  |

<a name="getSpecificAction_0"></a>
# **getSpecificAction_0**
> LoadBalancerActionsGetSpecificAction200Response getSpecificAction_0(id, actionId).execute();

Get an Action for a Load Balancer

Returns a specific Action for a Load Balancer.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    Long actionId = 56L; // ID of the Action
    try {
      LoadBalancerActionsGetSpecificAction200Response result = client
              .loadBalancerActions
              .getSpecificAction_0(id, actionId)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#getSpecificAction_0");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsGetSpecificAction200Response> response = client
              .loadBalancerActions
              .getSpecificAction_0(id, actionId)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#getSpecificAction_0");
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
| **actionId** | **Long**| ID of the Action | |

### Return type

[**LoadBalancerActionsGetSpecificAction200Response**](LoadBalancerActionsGetSpecificAction200Response.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;action&#x60; key contains the Load Balancer Action |  -  |

<a name="removeTarget"></a>
# **removeTarget**
> LoadBalancerActionsRemoveTargetResponse removeTarget(id).loadBalancerActionsRemoveTargetRequest(loadBalancerActionsRemoveTargetRequest).execute();

Remove Target

Removes a target from a Load Balancer.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    String type = "server"; // Type of the resource
    Long id = 56L; // ID of the Load Balancer
    LoadBalancerTargetIPProperty2 ip = new LoadBalancerTargetIPProperty2();
    LoadBalancerActionsRemoveTargetRequestLabelSelector labelSelector = new LoadBalancerActionsRemoveTargetRequestLabelSelector();
    LoadBalancerActionsRemoveTargetRequestServer server = new LoadBalancerActionsRemoveTargetRequestServer();
    try {
      LoadBalancerActionsRemoveTargetResponse result = client
              .loadBalancerActions
              .removeTarget(type, id)
              .ip(ip)
              .labelSelector(labelSelector)
              .server(server)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#removeTarget");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsRemoveTargetResponse> response = client
              .loadBalancerActions
              .removeTarget(type, id)
              .ip(ip)
              .labelSelector(labelSelector)
              .server(server)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#removeTarget");
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
| **loadBalancerActionsRemoveTargetRequest** | [**LoadBalancerActionsRemoveTargetRequest**](LoadBalancerActionsRemoveTargetRequest.md)|  | [optional] |

### Return type

[**LoadBalancerActionsRemoveTargetResponse**](LoadBalancerActionsRemoveTargetResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the &#x60;remove_target&#x60; Action |  -  |

<a name="updateService"></a>
# **updateService**
> LoadBalancerActionsUpdateServiceResponse updateService(id).loadBalancerActionsUpdateServiceRequest(loadBalancerActionsUpdateServiceRequest).execute();

Update Service

Updates a Load Balancer Service.  #### Call specific error codes  | Code                       | Description                                             | |----------------------------|---------------------------------------------------------| | &#x60;source_port_already_used&#x60; | The source port you are trying to add is already in use | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.LoadBalancerActionsApi;
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
    Integer listenPort = 56; // Port the Load Balancer listens on
    Long id = 56L; // ID of the Load Balancer
    Integer destinationPort = 56; // Port the Load Balancer will balance to
    UpdateLoadBalancerServiceHealthCheckProperty healthCheck = new UpdateLoadBalancerServiceHealthCheckProperty();
    LoadBalancerServiceHTTPProperty2 http = new LoadBalancerServiceHTTPProperty2();
    String protocol = "tcp"; // Protocol of the Load Balancer
    Boolean proxyprotocol = true; // Is Proxyprotocol enabled or not
    try {
      LoadBalancerActionsUpdateServiceResponse result = client
              .loadBalancerActions
              .updateService(listenPort, id)
              .destinationPort(destinationPort)
              .healthCheck(healthCheck)
              .http(http)
              .protocol(protocol)
              .proxyprotocol(proxyprotocol)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#updateService");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<LoadBalancerActionsUpdateServiceResponse> response = client
              .loadBalancerActions
              .updateService(listenPort, id)
              .destinationPort(destinationPort)
              .healthCheck(healthCheck)
              .http(http)
              .protocol(protocol)
              .proxyprotocol(proxyprotocol)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling LoadBalancerActionsApi#updateService");
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
| **loadBalancerActionsUpdateServiceRequest** | [**LoadBalancerActionsUpdateServiceRequest**](LoadBalancerActionsUpdateServiceRequest.md)|  | [optional] |

### Return type

[**LoadBalancerActionsUpdateServiceResponse**](LoadBalancerActionsUpdateServiceResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the &#x60;update_service&#x60; Action |  -  |

