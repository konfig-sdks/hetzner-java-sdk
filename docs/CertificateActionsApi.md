# CertificateActionsApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAction**](CertificateActionsApi.md#getAction) | **GET** /certificates/actions/{id} | Get an Action |
| [**getActionById**](CertificateActionsApi.md#getActionById) | **GET** /certificates/{id}/actions/{action_id} | Get an Action for a Certificate |
| [**getAllActions**](CertificateActionsApi.md#getAllActions) | **GET** /certificates/actions | Get all Actions |
| [**getAllActions_0**](CertificateActionsApi.md#getAllActions_0) | **GET** /certificates/{id}/actions | Get all Actions for a Certificate |
| [**retryIssuanceOrRenewal**](CertificateActionsApi.md#retryIssuanceOrRenewal) | **POST** /certificates/{id}/actions/retry | Retry Issuance or Renewal |


<a name="getAction"></a>
# **getAction**
> CertificateActionsGetActionResponse getAction(id).execute();

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
import com.konfigthis.client.api.CertificateActionsApi;
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
      CertificateActionsGetActionResponse result = client
              .certificateActions
              .getAction(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificateActionsApi#getAction");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<CertificateActionsGetActionResponse> response = client
              .certificateActions
              .getAction(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificateActionsApi#getAction");
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

[**CertificateActionsGetActionResponse**](CertificateActionsGetActionResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;action&#x60; key in the reply has this structure |  -  |

<a name="getActionById"></a>
# **getActionById**
> CertificateActionsGetActionByIdResponse getActionById(id, actionId).execute();

Get an Action for a Certificate

Returns a specific Action for a Certificate. Only type &#x60;managed&#x60; Certificates have Actions.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.CertificateActionsApi;
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
    Long id = 56L; // ID of the Certificate
    Long actionId = 56L; // ID of the Action
    try {
      CertificateActionsGetActionByIdResponse result = client
              .certificateActions
              .getActionById(id, actionId)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificateActionsApi#getActionById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<CertificateActionsGetActionByIdResponse> response = client
              .certificateActions
              .getActionById(id, actionId)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificateActionsApi#getActionById");
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
| **id** | **Long**| ID of the Certificate | |
| **actionId** | **Long**| ID of the Action | |

### Return type

[**CertificateActionsGetActionByIdResponse**](CertificateActionsGetActionByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;action&#x60; key contains the Certificate Action |  -  |

<a name="getAllActions"></a>
# **getAllActions**
> CertificateActionsGetAllActionsResponse getAllActions().id(id).sort(sort).status(status).page(page).perPage(perPage).execute();

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
import com.konfigthis.client.api.CertificateActionsApi;
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
      CertificateActionsGetAllActionsResponse result = client
              .certificateActions
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
      System.err.println("Exception when calling CertificateActionsApi#getAllActions");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<CertificateActionsGetAllActionsResponse> response = client
              .certificateActions
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
      System.err.println("Exception when calling CertificateActionsApi#getAllActions");
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

[**CertificateActionsGetAllActionsResponse**](CertificateActionsGetAllActionsResponse.md)

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
> CertificateActionsGetAllActions200Response getAllActions_0(id).sort(sort).status(status).page(page).perPage(perPage).execute();

Get all Actions for a Certificate

Returns all Action objects for a Certificate. You can sort the results by using the &#x60;sort&#x60; URI parameter, and filter them with the &#x60;status&#x60; parameter.  Only type &#x60;managed&#x60; Certificates can have Actions. For type &#x60;uploaded&#x60; Certificates the &#x60;actions&#x60; key will always contain an empty array. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.CertificateActionsApi;
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
      CertificateActionsGetAllActions200Response result = client
              .certificateActions
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
      System.err.println("Exception when calling CertificateActionsApi#getAllActions_0");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<CertificateActionsGetAllActions200Response> response = client
              .certificateActions
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
      System.err.println("Exception when calling CertificateActionsApi#getAllActions_0");
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

[**CertificateActionsGetAllActions200Response**](CertificateActionsGetAllActions200Response.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;actions&#x60; key contains a list of Actions |  -  |

<a name="retryIssuanceOrRenewal"></a>
# **retryIssuanceOrRenewal**
> CertificateActionsRetryIssuanceOrRenewalResponse retryIssuanceOrRenewal(id).execute();

Retry Issuance or Renewal

Retry a failed Certificate issuance or renewal.  Only applicable if the type of the Certificate is &#x60;managed&#x60; and the issuance or renewal status is &#x60;failed&#x60;.  #### Call specific error codes  | Code                                                    | Description                                                               | |---------------------------------------------------------|---------------------------------------------------------------------------| | &#x60;caa_record_does_not_allow_ca&#x60;                          | CAA record does not allow certificate authority                           | | &#x60;ca_dns_validation_failed&#x60;                              | Certificate Authority: DNS validation failed                              | | &#x60;ca_too_many_authorizations_failed_recently&#x60;            | Certificate Authority: Too many authorizations failed recently            | | &#x60;ca_too_many_certificates_issued_for_registered_domain&#x60; | Certificate Authority: Too many certificates issued for registered domain | | &#x60;ca_too_many_duplicate_certificates&#x60;                    | Certificate Authority: Too many duplicate certificates                    | | &#x60;could_not_verify_domain_delegated_to_zone&#x60;             | Could not verify domain delegated to zone                                 | | &#x60;dns_zone_not_found&#x60;                                    | DNS zone not found                                                        | | &#x60;dns_zone_is_secondary_zone&#x60;                            | DNS zone is a secondary zone                                              | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.CertificateActionsApi;
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
    Long id = 56L; // ID of the Certificate
    try {
      CertificateActionsRetryIssuanceOrRenewalResponse result = client
              .certificateActions
              .retryIssuanceOrRenewal(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificateActionsApi#retryIssuanceOrRenewal");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<CertificateActionsRetryIssuanceOrRenewalResponse> response = client
              .certificateActions
              .retryIssuanceOrRenewal(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificateActionsApi#retryIssuanceOrRenewal");
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
| **id** | **Long**| ID of the Certificate | |

### Return type

[**CertificateActionsRetryIssuanceOrRenewalResponse**](CertificateActionsRetryIssuanceOrRenewalResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key contains the resulting Action |  -  |

