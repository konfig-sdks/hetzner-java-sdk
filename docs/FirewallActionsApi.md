# FirewallActionsApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**applyToResources**](FirewallActionsApi.md#applyToResources) | **POST** /firewalls/{id}/actions/apply_to_resources | Apply to Resources |
| [**getActionById**](FirewallActionsApi.md#getActionById) | **GET** /firewalls/actions/{id} | Get an Action |
| [**getActionById_0**](FirewallActionsApi.md#getActionById_0) | **GET** /firewalls/{id}/actions/{action_id} | Get an Action for a Firewall |
| [**getAllActions**](FirewallActionsApi.md#getAllActions) | **GET** /firewalls/actions | Get all Actions |
| [**getAllActions_0**](FirewallActionsApi.md#getAllActions_0) | **GET** /firewalls/{id}/actions | Get all Actions for a Firewall |
| [**removeFromResources**](FirewallActionsApi.md#removeFromResources) | **POST** /firewalls/{id}/actions/remove_from_resources | Remove from Resources |
| [**setRules**](FirewallActionsApi.md#setRules) | **POST** /firewalls/{id}/actions/set_rules | Set Rules |


<a name="applyToResources"></a>
# **applyToResources**
> FirewallActionsApplyToResourcesResponse applyToResources(id).firewallActionsApplyToResourcesRequest(firewallActionsApplyToResourcesRequest).execute();

Apply to Resources

Applies one Firewall to multiple resources.  Currently servers (public network interface) and label selectors are supported.  #### Call specific error codes  | Code                          | Description                                                   | |-------------------------------|---------------------------------------------------------------| | &#x60;firewall_already_applied&#x60;    | Firewall was already applied on resource                      | | &#x60;incompatible_network_type&#x60;   | The Network type is incompatible for the given resource       | | &#x60;firewall_resource_not_found&#x60; | The resource the Firewall should be attached to was not found | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FirewallActionsApi;
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
    List<FirewallResource> applyTo = Arrays.asList(); // Resources the Firewall should be applied to
    Long id = 56L; // ID of the Firewall
    try {
      FirewallActionsApplyToResourcesResponse result = client
              .firewallActions
              .applyToResources(applyTo, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getActions());
      System.out.println(result.getMeta());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallActionsApi#applyToResources");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FirewallActionsApplyToResourcesResponse> response = client
              .firewallActions
              .applyToResources(applyTo, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallActionsApi#applyToResources");
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
| **id** | **Long**| ID of the Firewall | |
| **firewallActionsApplyToResourcesRequest** | [**FirewallActionsApplyToResourcesRequest**](FirewallActionsApplyToResourcesRequest.md)|  | [optional] |

### Return type

[**FirewallActionsApplyToResourcesResponse**](FirewallActionsApplyToResourcesResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;actions&#x60; key contains multiple &#x60;apply_firewall&#x60; Actions |  -  |

<a name="getActionById"></a>
# **getActionById**
> FirewallActionsGetActionByIdResponse getActionById(id).execute();

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
import com.konfigthis.client.api.FirewallActionsApi;
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
      FirewallActionsGetActionByIdResponse result = client
              .firewallActions
              .getActionById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallActionsApi#getActionById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FirewallActionsGetActionByIdResponse> response = client
              .firewallActions
              .getActionById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallActionsApi#getActionById");
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

[**FirewallActionsGetActionByIdResponse**](FirewallActionsGetActionByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;action&#x60; key in the reply has this structure |  -  |

<a name="getActionById_0"></a>
# **getActionById_0**
> FirewallActionsGetActionById200Response getActionById_0(id, actionId).execute();

Get an Action for a Firewall

Returns a specific Action for a Firewall.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FirewallActionsApi;
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
    Long id = 56L; // ID of the Firewall
    Long actionId = 56L; // ID of the Action
    try {
      FirewallActionsGetActionById200Response result = client
              .firewallActions
              .getActionById_0(id, actionId)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallActionsApi#getActionById_0");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FirewallActionsGetActionById200Response> response = client
              .firewallActions
              .getActionById_0(id, actionId)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallActionsApi#getActionById_0");
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
| **id** | **Long**| ID of the Firewall | |
| **actionId** | **Long**| ID of the Action | |

### Return type

[**FirewallActionsGetActionById200Response**](FirewallActionsGetActionById200Response.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;action&#x60; key contains the Firewall Action |  -  |

<a name="getAllActions"></a>
# **getAllActions**
> FirewallActionsGetAllActionsResponse getAllActions().id(id).sort(sort).status(status).page(page).perPage(perPage).execute();

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
import com.konfigthis.client.api.FirewallActionsApi;
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
      FirewallActionsGetAllActionsResponse result = client
              .firewallActions
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
      System.err.println("Exception when calling FirewallActionsApi#getAllActions");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FirewallActionsGetAllActionsResponse> response = client
              .firewallActions
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
      System.err.println("Exception when calling FirewallActionsApi#getAllActions");
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

[**FirewallActionsGetAllActionsResponse**](FirewallActionsGetAllActionsResponse.md)

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
> FirewallActionsGetAllActions200Response getAllActions_0(id).sort(sort).status(status).page(page).perPage(perPage).execute();

Get all Actions for a Firewall

Returns all Action objects for a Firewall. You can sort the results by using the &#x60;sort&#x60; URI parameter, and filter them with the &#x60;status&#x60; parameter.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FirewallActionsApi;
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
    String sort = "id"; // Sort actions by field and direction. Can be used multiple times. For more information, see \"[Sorting](https://docs.hetzner.cloud)\". 
    String status = "running"; // Filter the actions by status. Can be used multiple times. The response will only contain actions matching the specified statuses. 
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      FirewallActionsGetAllActions200Response result = client
              .firewallActions
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
      System.err.println("Exception when calling FirewallActionsApi#getAllActions_0");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FirewallActionsGetAllActions200Response> response = client
              .firewallActions
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
      System.err.println("Exception when calling FirewallActionsApi#getAllActions_0");
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
| **sort** | **String**| Sort actions by field and direction. Can be used multiple times. For more information, see \&quot;[Sorting](https://docs.hetzner.cloud)\&quot;.  | [optional] [enum: id, id:asc, id:desc, command, command:asc, command:desc, status, status:asc, status:desc, started, started:asc, started:desc, finished, finished:asc, finished:desc] |
| **status** | **String**| Filter the actions by status. Can be used multiple times. The response will only contain actions matching the specified statuses.  | [optional] [enum: running, success, error] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**FirewallActionsGetAllActions200Response**](FirewallActionsGetAllActions200Response.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;actions&#x60; key contains a list of Actions |  -  |

<a name="removeFromResources"></a>
# **removeFromResources**
> FirewallActionsRemoveFromResourcesResponse removeFromResources(id).firewallActionsRemoveFromResourcesRequest(firewallActionsRemoveFromResourcesRequest).execute();

Remove from Resources

Removes one Firewall from multiple resources.  Currently only Servers (and their public network interfaces) are supported.  #### Call specific error codes  | Code                                  | Description                                                            | |---------------------------------------|------------------------------------------------------------------------| | &#x60;firewall_already_removed&#x60;            | Firewall was already removed from the resource                         | | &#x60;firewall_resource_not_found&#x60;         | The resource the Firewall should be attached to was not found          | | &#x60;firewall_managed_by_label_selector&#x60;  | Firewall was applied via label selector and cannot be removed manually | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FirewallActionsApi;
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
    List<FirewallResource1> removeFrom = Arrays.asList(); // Resources the Firewall should be removed from
    Long id = 56L; // ID of the Firewall
    try {
      FirewallActionsRemoveFromResourcesResponse result = client
              .firewallActions
              .removeFromResources(removeFrom, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getActions());
      System.out.println(result.getMeta());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallActionsApi#removeFromResources");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FirewallActionsRemoveFromResourcesResponse> response = client
              .firewallActions
              .removeFromResources(removeFrom, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallActionsApi#removeFromResources");
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
| **id** | **Long**| ID of the Firewall | |
| **firewallActionsRemoveFromResourcesRequest** | [**FirewallActionsRemoveFromResourcesRequest**](FirewallActionsRemoveFromResourcesRequest.md)|  | [optional] |

### Return type

[**FirewallActionsRemoveFromResourcesResponse**](FirewallActionsRemoveFromResourcesResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;actions&#x60; key contains multiple &#x60;remove_firewall&#x60; Actions |  -  |

<a name="setRules"></a>
# **setRules**
> FirewallActionsSetRulesResponse setRules(id).firewallActionsSetRulesRequest(firewallActionsSetRulesRequest).execute();

Set Rules

Sets the rules of a Firewall.  All existing rules will be overwritten. Pass an empty &#x60;rules&#x60; array to remove all rules. The maximum amount of rules that can be defined is 50.  #### Call specific error codes  | Code                          | Description                                                   | |-------------------------------|---------------------------------------------------------------| | &#x60;firewall_resource_not_found&#x60; | The resource the Firewall should be attached to was not found | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.FirewallActionsApi;
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
    List<Rule1> rules = Arrays.asList(); // Array of rules
    Long id = 56L; // ID of the Firewall
    try {
      FirewallActionsSetRulesResponse result = client
              .firewallActions
              .setRules(rules, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getActions());
      System.out.println(result.getMeta());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallActionsApi#setRules");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<FirewallActionsSetRulesResponse> response = client
              .firewallActions
              .setRules(rules, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling FirewallActionsApi#setRules");
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
| **id** | **Long**| ID of the Firewall | |
| **firewallActionsSetRulesRequest** | [**FirewallActionsSetRulesRequest**](FirewallActionsSetRulesRequest.md)|  | [optional] |

### Return type

[**FirewallActionsSetRulesResponse**](FirewallActionsSetRulesResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains one &#x60;set_firewall_rules&#x60; Action plus one &#x60;apply_firewall&#x60; Action per resource where the Firewall is active |  -  |

