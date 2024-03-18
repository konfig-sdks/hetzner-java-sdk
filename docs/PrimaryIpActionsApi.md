# PrimaryIpActionsApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**assignPrimaryIpToResource**](PrimaryIpActionsApi.md#assignPrimaryIpToResource) | **POST** /primary_ips/{id}/actions/assign | Assign a Primary IP to a resource |
| [**changeDnsPtr**](PrimaryIpActionsApi.md#changeDnsPtr) | **POST** /primary_ips/{id}/actions/change_dns_ptr | Change reverse DNS entry for a Primary IP |
| [**changeProtectionPrimaryIp**](PrimaryIpActionsApi.md#changeProtectionPrimaryIp) | **POST** /primary_ips/{id}/actions/change_protection | Change Primary IP Protection |
| [**getActionById**](PrimaryIpActionsApi.md#getActionById) | **GET** /primary_ips/actions/{id} | Get an Action |
| [**getAllActions**](PrimaryIpActionsApi.md#getAllActions) | **GET** /primary_ips/actions | Get all Actions |
| [**unassignPrimaryIp**](PrimaryIpActionsApi.md#unassignPrimaryIp) | **POST** /primary_ips/{id}/actions/unassign | Unassign a Primary IP from a resource |


<a name="assignPrimaryIpToResource"></a>
# **assignPrimaryIpToResource**
> PrimaryIpActionsAssignPrimaryIpToResourceResponse assignPrimaryIpToResource(id).primaryIpActionsAssignPrimaryIpToResourceRequest(primaryIpActionsAssignPrimaryIpToResourceRequest).execute();

Assign a Primary IP to a resource

Assigns a Primary IP to a Server.  A Server can only have one Primary IP of type &#x60;ipv4&#x60; and one of type &#x60;ipv6&#x60; assigned. If you need more IPs use Floating IPs.  The Server must be powered off (status &#x60;off&#x60;) in order for this operation to succeed.  #### Call specific error codes  | Code                          | Description                                                   | |------------------------------ |-------------------------------------------------------------- | | &#x60;server_not_stopped&#x60;          | The server is running, but needs to be powered off            | | &#x60;primary_ip_already_assigned&#x60; | Primary ip is already assigned to a different server          | | &#x60;server_has_ipv4&#x60;             | The server already has an ipv4 address                        | | &#x60;server_has_ipv6&#x60;             | The server already has an ipv6 address                        | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.PrimaryIpActionsApi;
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
    Long assigneeId = 56L; // ID of a resource of type `assignee_type`
    String assigneeType = "server"; // Type of resource assigning the Primary IP to
    Long id = 56L; // ID of the Primary IP
    try {
      PrimaryIpActionsAssignPrimaryIpToResourceResponse result = client
              .primaryIpActions
              .assignPrimaryIpToResource(assigneeId, assigneeType, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpActionsApi#assignPrimaryIpToResource");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<PrimaryIpActionsAssignPrimaryIpToResourceResponse> response = client
              .primaryIpActions
              .assignPrimaryIpToResource(assigneeId, assigneeType, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpActionsApi#assignPrimaryIpToResource");
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
| **id** | **Long**| ID of the Primary IP | |
| **primaryIpActionsAssignPrimaryIpToResourceRequest** | [**PrimaryIpActionsAssignPrimaryIpToResourceRequest**](PrimaryIpActionsAssignPrimaryIpToResourceRequest.md)|  | [optional] |

### Return type

[**PrimaryIpActionsAssignPrimaryIpToResourceResponse**](PrimaryIpActionsAssignPrimaryIpToResourceResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="changeDnsPtr"></a>
# **changeDnsPtr**
> PrimaryIpActionsChangeDnsPtrResponse changeDnsPtr(id).primaryIpActionsChangeDnsPtrRequest(primaryIpActionsChangeDnsPtrRequest).execute();

Change reverse DNS entry for a Primary IP

Changes the hostname that will appear when getting the hostname belonging to this Primary IP.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.PrimaryIpActionsApi;
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
    String dnsPtr = "dnsPtr_example"; // Hostname to set as a reverse DNS PTR entry, will reset to original default value if `null`
    String ip = "ip_example"; // IP address for which to set the reverse DNS entry
    Long id = 56L; // ID of the Primary IP
    try {
      PrimaryIpActionsChangeDnsPtrResponse result = client
              .primaryIpActions
              .changeDnsPtr(dnsPtr, ip, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpActionsApi#changeDnsPtr");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<PrimaryIpActionsChangeDnsPtrResponse> response = client
              .primaryIpActions
              .changeDnsPtr(dnsPtr, ip, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpActionsApi#changeDnsPtr");
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
| **id** | **Long**| ID of the Primary IP | |
| **primaryIpActionsChangeDnsPtrRequest** | [**PrimaryIpActionsChangeDnsPtrRequest**](PrimaryIpActionsChangeDnsPtrRequest.md)| Select the IP address for which to change the DNS entry by passing &#x60;ip&#x60;. For a Primary IP of type &#x60;ipv4&#x60; this must exactly match the IP address of the Primary IP. For a Primary IP of type &#x60;ipv6&#x60; this must be a single IP within the IPv6 /64 range that belongs to this Primary IP. You can add up to 100 IPv6 reverse DNS entries.  The target hostname is set by passing &#x60;dns_ptr&#x60;.  | [optional] |

### Return type

[**PrimaryIpActionsChangeDnsPtrResponse**](PrimaryIpActionsChangeDnsPtrResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the &#x60;change_dns_ptr&#x60; Action |  -  |

<a name="changeProtectionPrimaryIp"></a>
# **changeProtectionPrimaryIp**
> PrimaryIpActionsChangeProtectionPrimaryIpResponse changeProtectionPrimaryIp(id).primaryIpActionsChangeProtectionPrimaryIpRequest(primaryIpActionsChangeProtectionPrimaryIpRequest).execute();

Change Primary IP Protection

Changes the protection configuration of a Primary IP.  A Primary IP can only be delete protected if its &#x60;auto_delete&#x60; property is set to &#x60;false&#x60;. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.PrimaryIpActionsApi;
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
    Long id = 56L; // ID of the Primary IP
    Boolean delete = true; // If true, prevents the Primary IP from being deleted
    try {
      PrimaryIpActionsChangeProtectionPrimaryIpResponse result = client
              .primaryIpActions
              .changeProtectionPrimaryIp(id)
              .delete(delete)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpActionsApi#changeProtectionPrimaryIp");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<PrimaryIpActionsChangeProtectionPrimaryIpResponse> response = client
              .primaryIpActions
              .changeProtectionPrimaryIp(id)
              .delete(delete)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpActionsApi#changeProtectionPrimaryIp");
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
| **id** | **Long**| ID of the Primary IP | |
| **primaryIpActionsChangeProtectionPrimaryIpRequest** | [**PrimaryIpActionsChangeProtectionPrimaryIpRequest**](PrimaryIpActionsChangeProtectionPrimaryIpRequest.md)|  | [optional] |

### Return type

[**PrimaryIpActionsChangeProtectionPrimaryIpResponse**](PrimaryIpActionsChangeProtectionPrimaryIpResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the &#x60;change_protection&#x60; Action |  -  |

<a name="getActionById"></a>
# **getActionById**
> PrimaryIpActionsGetActionByIdResponse getActionById(id).execute();

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
import com.konfigthis.client.api.PrimaryIpActionsApi;
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
      PrimaryIpActionsGetActionByIdResponse result = client
              .primaryIpActions
              .getActionById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpActionsApi#getActionById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<PrimaryIpActionsGetActionByIdResponse> response = client
              .primaryIpActions
              .getActionById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpActionsApi#getActionById");
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

[**PrimaryIpActionsGetActionByIdResponse**](PrimaryIpActionsGetActionByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;action&#x60; key in the reply has this structure |  -  |

<a name="getAllActions"></a>
# **getAllActions**
> PrimaryIpActionsGetAllActionsResponse getAllActions().id(id).sort(sort).status(status).page(page).perPage(perPage).execute();

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
import com.konfigthis.client.api.PrimaryIpActionsApi;
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
      PrimaryIpActionsGetAllActionsResponse result = client
              .primaryIpActions
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
      System.err.println("Exception when calling PrimaryIpActionsApi#getAllActions");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<PrimaryIpActionsGetAllActionsResponse> response = client
              .primaryIpActions
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
      System.err.println("Exception when calling PrimaryIpActionsApi#getAllActions");
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

[**PrimaryIpActionsGetAllActionsResponse**](PrimaryIpActionsGetAllActionsResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;actions&#x60; key contains a list of Actions |  -  |

<a name="unassignPrimaryIp"></a>
# **unassignPrimaryIp**
> PrimaryIpActionsUnassignPrimaryIpResponse unassignPrimaryIp(id).execute();

Unassign a Primary IP from a resource

Unassigns a Primary IP from a Server.  The Server must be powered off (status &#x60;off&#x60;) in order for this operation to succeed.  Note that only Servers that have at least one network interface (public or private) attached can be powered on.  #### Call specific error codes  | Code                              | Description                                                   | |---------------------------------- |-------------------------------------------------------------- | | &#x60;server_not_stopped&#x60;              | The server is running, but needs to be powered off            | | &#x60;server_is_load_balancer_target&#x60;  | The server ipv4 address is a loadbalancer target              | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.PrimaryIpActionsApi;
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
    Long id = 56L; // ID of the Primary IP
    try {
      PrimaryIpActionsUnassignPrimaryIpResponse result = client
              .primaryIpActions
              .unassignPrimaryIp(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpActionsApi#unassignPrimaryIp");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<PrimaryIpActionsUnassignPrimaryIpResponse> response = client
              .primaryIpActions
              .unassignPrimaryIp(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpActionsApi#unassignPrimaryIp");
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
| **id** | **Long**| ID of the Primary IP | |

### Return type

[**PrimaryIpActionsUnassignPrimaryIpResponse**](PrimaryIpActionsUnassignPrimaryIpResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

