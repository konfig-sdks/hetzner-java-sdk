# FloatingIpsApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createNewIp**](FloatingIpsApi.md#createNewIp) | **POST** /floating_ips | Create a Floating IP |
| [**deleteIp**](FloatingIpsApi.md#deleteIp) | **DELETE** /floating_ips/{id} | Delete a Floating IP |
| [**get**](FloatingIpsApi.md#get) | **GET** /floating_ips/{id} | Get a Floating IP |
| [**getAll**](FloatingIpsApi.md#getAll) | **GET** /floating_ips | Get all Floating IPs |
| [**updateDescriptionLabels**](FloatingIpsApi.md#updateDescriptionLabels) | **PUT** /floating_ips/{id} | Update a Floating IP |


<a name="createNewIp"></a>
# **createNewIp**
> FloatingIPsCreateNewIpResponse createNewIp().floatingIPsCreateNewIpRequest(floatingIPsCreateNewIpRequest).execute();

Create a Floating IP

Creates a new Floating IP assigned to a Server. If you want to create a Floating IP that is not bound to a Server, you need to provide the &#x60;home_location&#x60; key instead of &#x60;server&#x60;. This can be either the ID or the name of the Location this IP shall be created in. Note that a Floating IP can be assigned to a Server in any Location later on. For optimal routing it is advised to use the Floating IP in the same Location it was created in.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FloatingIpsApi;
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
    String type = "ipv4"; // Floating IP type
    String description = "description_example";
    String homeLocation = "homeLocation_example"; // Home Location (routing is optimized for that Location). Only optional if Server argument is passed.
    Object labels = null; // User-defined labels (key-value pairs)
    String name = "name_example";
    Long server = 56L; // ID of the Server to assign the Floating IP to
    try {
      FloatingIPsCreateNewIpResponse result = client
              .floatingIps
              .createNewIp(type)
              .description(description)
              .homeLocation(homeLocation)
              .labels(labels)
              .name(name)
              .server(server)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
      System.out.println(result.getFloatingIp());
    } catch (ApiException e) {
      System.err.println("Exception when calling FloatingIpsApi#createNewIp");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FloatingIPsCreateNewIpResponse> response = client
              .floatingIps
              .createNewIp(type)
              .description(description)
              .homeLocation(homeLocation)
              .labels(labels)
              .name(name)
              .server(server)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling FloatingIpsApi#createNewIp");
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
| **floatingIPsCreateNewIpRequest** | [**FloatingIPsCreateNewIpRequest**](FloatingIPsCreateNewIpRequest.md)| The &#x60;type&#x60; argument is required while &#x60;home_location&#x60; and &#x60;server&#x60; are mutually exclusive. | [optional] |

### Return type

[**FloatingIPsCreateNewIpResponse**](FloatingIPsCreateNewIpResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;floating_ip&#x60; key in the reply contains the object that was just created |  -  |

<a name="deleteIp"></a>
# **deleteIp**
> deleteIp(id).execute();

Delete a Floating IP

Deletes a Floating IP. If it is currently assigned to a Server it will automatically get unassigned.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FloatingIpsApi;
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
    Long id = 56L; // ID of the Floating IP
    try {
      client
              .floatingIps
              .deleteIp(id)
              .execute();
    } catch (ApiException e) {
      System.err.println("Exception when calling FloatingIpsApi#deleteIp");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      client
              .floatingIps
              .deleteIp(id)
              .executeWithHttpInfo();
    } catch (ApiException e) {
      System.err.println("Exception when calling FloatingIpsApi#deleteIp");
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
| **id** | **Long**| ID of the Floating IP | |

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
| **204** | Floating IP deleted |  -  |

<a name="get"></a>
# **get**
> FloatingIPsGetResponse get(id).execute();

Get a Floating IP

Returns a specific Floating IP object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FloatingIpsApi;
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
    Long id = 56L; // ID of the Floating IP
    try {
      FloatingIPsGetResponse result = client
              .floatingIps
              .get(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getFloatingIp());
    } catch (ApiException e) {
      System.err.println("Exception when calling FloatingIpsApi#get");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FloatingIPsGetResponse> response = client
              .floatingIps
              .get(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling FloatingIpsApi#get");
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
| **id** | **Long**| ID of the Floating IP | |

### Return type

[**FloatingIPsGetResponse**](FloatingIPsGetResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;floating_ip&#x60; key in the reply contains a Floating IP object with this structure |  -  |

<a name="getAll"></a>
# **getAll**
> FloatingIPsGetAllResponse getAll().name(name).labelSelector(labelSelector).sort(sort).page(page).perPage(perPage).execute();

Get all Floating IPs

Returns all Floating IP objects.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FloatingIpsApi;
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
    String sort = "id"; // Can be used multiple times. Choices id id:asc id:desc created created:asc created:desc
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      FloatingIPsGetAllResponse result = client
              .floatingIps
              .getAll()
              .name(name)
              .labelSelector(labelSelector)
              .sort(sort)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getFloatingIps());
      System.out.println(result.getMeta());
    } catch (ApiException e) {
      System.err.println("Exception when calling FloatingIpsApi#getAll");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FloatingIPsGetAllResponse> response = client
              .floatingIps
              .getAll()
              .name(name)
              .labelSelector(labelSelector)
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
      System.err.println("Exception when calling FloatingIpsApi#getAll");
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
| **sort** | **String**| Can be used multiple times. Choices id id:asc id:desc created created:asc created:desc | [optional] [enum: id, id:asc, id:desc, created, created:asc, created:desc] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**FloatingIPsGetAllResponse**](FloatingIPsGetAllResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;floating_ips&#x60; key in the reply contains an array of Floating IP objects with this structure |  -  |

<a name="updateDescriptionLabels"></a>
# **updateDescriptionLabels**
> FloatingIPsUpdateDescriptionLabelsResponse updateDescriptionLabels(id).floatingIPsUpdateDescriptionLabelsRequest(floatingIPsUpdateDescriptionLabelsRequest).execute();

Update a Floating IP

Updates the description or labels of a Floating IP. Also note that when updating labels, the Floating IP’s current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FloatingIpsApi;
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
    Long id = 56L; // ID of the Floating IP
    String description = "description_example"; // New Description to set
    Object labels = null; // User-defined labels (key-value pairs)
    String name = "name_example"; // New unique name to set
    try {
      FloatingIPsUpdateDescriptionLabelsResponse result = client
              .floatingIps
              .updateDescriptionLabels(id)
              .description(description)
              .labels(labels)
              .name(name)
              .execute();
      System.out.println(result);
      System.out.println(result.getFloatingIp());
    } catch (ApiException e) {
      System.err.println("Exception when calling FloatingIpsApi#updateDescriptionLabels");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FloatingIPsUpdateDescriptionLabelsResponse> response = client
              .floatingIps
              .updateDescriptionLabels(id)
              .description(description)
              .labels(labels)
              .name(name)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling FloatingIpsApi#updateDescriptionLabels");
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
| **id** | **Long**| ID of the Floating IP | |
| **floatingIPsUpdateDescriptionLabelsRequest** | [**FloatingIPsUpdateDescriptionLabelsRequest**](FloatingIPsUpdateDescriptionLabelsRequest.md)|  | [optional] |

### Return type

[**FloatingIPsUpdateDescriptionLabelsResponse**](FloatingIPsUpdateDescriptionLabelsResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;floating_ip&#x60; key in the reply contains the modified Floating IP object with the new description |  -  |

