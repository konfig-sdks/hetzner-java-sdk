# PrimaryIpsApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createOrUpdate**](PrimaryIpsApi.md#createOrUpdate) | **POST** /primary_ips | Create a Primary IP |
| [**deletePrimaryIp**](PrimaryIpsApi.md#deletePrimaryIp) | **DELETE** /primary_ips/{id} | Delete a Primary IP |
| [**getAll**](PrimaryIpsApi.md#getAll) | **GET** /primary_ips | Get all Primary IPs |
| [**getById**](PrimaryIpsApi.md#getById) | **GET** /primary_ips/{id} | Get a Primary IP |
| [**updateIpLabels**](PrimaryIpsApi.md#updateIpLabels) | **PUT** /primary_ips/{id} | Update a Primary IP |


<a name="createOrUpdate"></a>
# **createOrUpdate**
> PrimaryIPsCreateOrUpdateResponse createOrUpdate().primaryIPsCreateOrUpdateRequest(primaryIPsCreateOrUpdateRequest).execute();

Create a Primary IP

Creates a new Primary IP, optionally assigned to a Server.  If you want to create a Primary IP that is not assigned to a Server, you need to provide the &#x60;datacenter&#x60; key instead of &#x60;assignee_id&#x60;. This can be either the ID or the name of the Datacenter this Primary IP shall be created in.  Note that a Primary IP can only be assigned to a Server in the same Datacenter later on.  #### Call specific error codes  | Code                          | Description                                                   | |------------------------------ |-------------------------------------------------------------- | | &#x60;server_not_stopped&#x60;          | The specified server is running, but needs to be powered off  | | &#x60;server_has_ipv4&#x60;             | The server already has an ipv4 address                        | | &#x60;server_has_ipv6&#x60;             | The server already has an ipv6 address                        | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.PrimaryIpsApi;
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
    String assigneeType = "server"; // Resource type the Primary IP can be assigned to
    String name = "name_example";
    String type = "ipv4"; // Primary IP type
    Long assigneeId = 56L; // ID of the resource the Primary IP should be assigned to. Omitted if it should not be assigned.
    Boolean autoDelete = false; // Delete the Primary IP when the Server it is assigned to is deleted.
    String datacenter = "datacenter_example"; // ID or name of Datacenter the Primary IP will be bound to. Needs to be omitted if `assignee_id` is passed.
    Object labels = null; // User-defined labels (key-value pairs)
    try {
      PrimaryIPsCreateOrUpdateResponse result = client
              .primaryIps
              .createOrUpdate(assigneeType, name, type)
              .assigneeId(assigneeId)
              .autoDelete(autoDelete)
              .datacenter(datacenter)
              .labels(labels)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
      System.out.println(result.getPrimaryIp());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpsApi#createOrUpdate");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<PrimaryIPsCreateOrUpdateResponse> response = client
              .primaryIps
              .createOrUpdate(assigneeType, name, type)
              .assigneeId(assigneeId)
              .autoDelete(autoDelete)
              .datacenter(datacenter)
              .labels(labels)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpsApi#createOrUpdate");
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
| **primaryIPsCreateOrUpdateRequest** | [**PrimaryIPsCreateOrUpdateRequest**](PrimaryIPsCreateOrUpdateRequest.md)| The &#x60;type&#x60; argument is required while &#x60;datacenter&#x60; and &#x60;assignee_id&#x60; are mutually exclusive. | [optional] |

### Return type

[**PrimaryIPsCreateOrUpdateResponse**](PrimaryIPsCreateOrUpdateResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;primary_ip&#x60; key contains the Primary IP that was just created |  -  |

<a name="deletePrimaryIp"></a>
# **deletePrimaryIp**
> deletePrimaryIp(id).execute();

Delete a Primary IP

Deletes a Primary IP.  The Primary IP may be assigned to a Server. In this case it is unassigned automatically. The Server must be powered off (status &#x60;off&#x60;) in order for this operation to succeed. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.PrimaryIpsApi;
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
              .primaryIps
              .deletePrimaryIp(id)
              .execute();
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpsApi#deletePrimaryIp");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      client
              .primaryIps
              .deletePrimaryIp(id)
              .executeWithHttpInfo();
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpsApi#deletePrimaryIp");
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
| **204** | Primary IP deleted |  -  |

<a name="getAll"></a>
# **getAll**
> PrimaryIPsGetAllResponse getAll().name(name).labelSelector(labelSelector).ip(ip).page(page).perPage(perPage).sort(sort).execute();

Get all Primary IPs

Returns all Primary IP objects.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.PrimaryIpsApi;
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
    String ip = "127.0.0.1"; // Can be used to filter resources by their ip. The response will only contain the resources matching the specified ip.
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    String sort = "id"; // Can be used multiple times. Choices id id:asc id:desc created created:asc created:desc
    try {
      PrimaryIPsGetAllResponse result = client
              .primaryIps
              .getAll()
              .name(name)
              .labelSelector(labelSelector)
              .ip(ip)
              .page(page)
              .perPage(perPage)
              .sort(sort)
              .execute();
      System.out.println(result);
      System.out.println(result.getMeta());
      System.out.println(result.getPrimaryIps());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpsApi#getAll");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<PrimaryIPsGetAllResponse> response = client
              .primaryIps
              .getAll()
              .name(name)
              .labelSelector(labelSelector)
              .ip(ip)
              .page(page)
              .perPage(perPage)
              .sort(sort)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpsApi#getAll");
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
| **ip** | **String**| Can be used to filter resources by their ip. The response will only contain the resources matching the specified ip. | [optional] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |
| **sort** | **String**| Can be used multiple times. Choices id id:asc id:desc created created:asc created:desc | [optional] [enum: id, id:asc, id:desc, created, created:asc, created:desc] |

### Return type

[**PrimaryIPsGetAllResponse**](PrimaryIPsGetAllResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;primary_ips&#x60; key contains an array of Primary IP objects |  -  |

<a name="getById"></a>
# **getById**
> PrimaryIPsGetByIdResponse getById(id).execute();

Get a Primary IP

Returns a specific Primary IP object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.PrimaryIpsApi;
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
      PrimaryIPsGetByIdResponse result = client
              .primaryIps
              .getById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getPrimaryIp());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpsApi#getById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<PrimaryIPsGetByIdResponse> response = client
              .primaryIps
              .getById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpsApi#getById");
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

[**PrimaryIPsGetByIdResponse**](PrimaryIPsGetByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;primary_ip&#x60; key contains a Primary IP object |  -  |

<a name="updateIpLabels"></a>
# **updateIpLabels**
> PrimaryIPsUpdateIpLabelsResponse updateIpLabels(id).primaryIPsUpdateIpLabelsRequest(primaryIPsUpdateIpLabelsRequest).execute();

Update a Primary IP

Updates the Primary IP.  Note that when updating labels, the Primary IP&#39;s current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body.  If the Primary IP object changes during the request, the response will be a “conflict” error. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.PrimaryIpsApi;
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
    Boolean autoDelete = true; // Delete this Primary IP when the resource it is assigned to is deleted
    Object labels = null; // User-defined labels (key-value pairs)
    String name = "name_example"; // New unique name to set
    try {
      PrimaryIPsUpdateIpLabelsResponse result = client
              .primaryIps
              .updateIpLabels(id)
              .autoDelete(autoDelete)
              .labels(labels)
              .name(name)
              .execute();
      System.out.println(result);
      System.out.println(result.getPrimaryIp());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpsApi#updateIpLabels");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<PrimaryIPsUpdateIpLabelsResponse> response = client
              .primaryIps
              .updateIpLabels(id)
              .autoDelete(autoDelete)
              .labels(labels)
              .name(name)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling PrimaryIpsApi#updateIpLabels");
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
| **primaryIPsUpdateIpLabelsRequest** | [**PrimaryIPsUpdateIpLabelsRequest**](PrimaryIPsUpdateIpLabelsRequest.md)|  | [optional] |

### Return type

[**PrimaryIPsUpdateIpLabelsResponse**](PrimaryIPsUpdateIpLabelsResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;primary_ip&#x60; key contains the Primary IP that was just updated |  -  |

