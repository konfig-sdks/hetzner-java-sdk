# FirewallsApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createFirewall**](FirewallsApi.md#createFirewall) | **POST** /firewalls | Create a Firewall |
| [**deleteFirewallById**](FirewallsApi.md#deleteFirewallById) | **DELETE** /firewalls/{id} | Delete a Firewall |
| [**getFirewallById**](FirewallsApi.md#getFirewallById) | **GET** /firewalls/{id} | Get a Firewall |
| [**listAll**](FirewallsApi.md#listAll) | **GET** /firewalls | Get all Firewalls |
| [**updateFirewallById**](FirewallsApi.md#updateFirewallById) | **PUT** /firewalls/{id} | Update a Firewall |


<a name="createFirewall"></a>
# **createFirewall**
> FirewallsCreateFirewallResponse createFirewall().firewallsCreateFirewallRequest(firewallsCreateFirewallRequest).execute();

Create a Firewall

Creates a new Firewall.  #### Call specific error codes  | Code                          | Description                                                   | |------------------------------ |-------------------------------------------------------------- | | &#x60;server_already_added&#x60;        | Server added more than one time to resource                   | | &#x60;incompatible_network_type&#x60;   | The Network type is incompatible for the given resource       | | &#x60;firewall_resource_not_found&#x60; | The resource the Firewall should be attached to was not found | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FirewallsApi;
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
    String name = "name_example"; // Name of the Firewall
    List<FirewallsCreateFirewallRequestApplyToInner> applyTo = Arrays.asList(); // Resources the Firewall should be applied to after creation
    Object labels = null; // User-defined labels (key-value pairs)
    List<Rule> rules = Arrays.asList(); // Array of rules
    try {
      FirewallsCreateFirewallResponse result = client
              .firewalls
              .createFirewall(name)
              .applyTo(applyTo)
              .labels(labels)
              .rules(rules)
              .execute();
      System.out.println(result);
      System.out.println(result.getActions());
      System.out.println(result.getFirewall());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallsApi#createFirewall");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FirewallsCreateFirewallResponse> response = client
              .firewalls
              .createFirewall(name)
              .applyTo(applyTo)
              .labels(labels)
              .rules(rules)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallsApi#createFirewall");
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
| **firewallsCreateFirewallRequest** | [**FirewallsCreateFirewallRequest**](FirewallsCreateFirewallRequest.md)|  | [optional] |

### Return type

[**FirewallsCreateFirewallResponse**](FirewallsCreateFirewallResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;firewall&#x60; key contains the Firewall that was just created |  -  |

<a name="deleteFirewallById"></a>
# **deleteFirewallById**
> deleteFirewallById(id).execute();

Delete a Firewall

Deletes a Firewall.  #### Call specific error codes  | Code                 | Description                               | |--------------------- |-------------------------------------------| | &#x60;resource_in_use&#x60;    | Firewall must not be in use to be deleted | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FirewallsApi;
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
              .firewalls
              .deleteFirewallById(id)
              .execute();
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallsApi#deleteFirewallById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      client
              .firewalls
              .deleteFirewallById(id)
              .executeWithHttpInfo();
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallsApi#deleteFirewallById");
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
| **204** | Firewall deleted |  -  |

<a name="getFirewallById"></a>
# **getFirewallById**
> FirewallsGetFirewallByIdResponse getFirewallById(id).execute();

Get a Firewall

Gets a specific Firewall object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FirewallsApi;
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
      FirewallsGetFirewallByIdResponse result = client
              .firewalls
              .getFirewallById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getFirewall());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallsApi#getFirewallById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FirewallsGetFirewallByIdResponse> response = client
              .firewalls
              .getFirewallById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallsApi#getFirewallById");
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

[**FirewallsGetFirewallByIdResponse**](FirewallsGetFirewallByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;firewall&#x60; key contains a Firewall object |  -  |

<a name="listAll"></a>
# **listAll**
> FirewallsListAllResponse listAll().sort(sort).name(name).labelSelector(labelSelector).page(page).perPage(perPage).execute();

Get all Firewalls

Returns all Firewall objects.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FirewallsApi;
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
      FirewallsListAllResponse result = client
              .firewalls
              .listAll()
              .sort(sort)
              .name(name)
              .labelSelector(labelSelector)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getFirewalls());
      System.out.println(result.getMeta());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallsApi#listAll");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FirewallsListAllResponse> response = client
              .firewalls
              .listAll()
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
      System.err.println("Exception when calling FirewallsApi#listAll");
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

[**FirewallsListAllResponse**](FirewallsListAllResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;firewalls&#x60; key contains an array of Firewall objects |  -  |

<a name="updateFirewallById"></a>
# **updateFirewallById**
> FirewallsUpdateFirewallByIdResponse updateFirewallById(id).firewallsUpdateFirewallByIdRequest(firewallsUpdateFirewallByIdRequest).execute();

Update a Firewall

Updates the Firewall.  Note that when updating labels, the Firewall&#39;s current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body.  Note: if the Firewall object changes during the request, the response will be a “conflict” error. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FirewallsApi;
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
    String name = "name_example"; // New Firewall name
    try {
      FirewallsUpdateFirewallByIdResponse result = client
              .firewalls
              .updateFirewallById(id)
              .labels(labels)
              .name(name)
              .execute();
      System.out.println(result);
      System.out.println(result.getFirewall());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallsApi#updateFirewallById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FirewallsUpdateFirewallByIdResponse> response = client
              .firewalls
              .updateFirewallById(id)
              .labels(labels)
              .name(name)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallsApi#updateFirewallById");
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
| **firewallsUpdateFirewallByIdRequest** | [**FirewallsUpdateFirewallByIdRequest**](FirewallsUpdateFirewallByIdRequest.md)|  | [optional] |

### Return type

[**FirewallsUpdateFirewallByIdResponse**](FirewallsUpdateFirewallByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;firewall&#x60; key contains the Firewall that was just updated |  -  |

