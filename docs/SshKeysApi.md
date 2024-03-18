# SshKeysApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createKey**](SshKeysApi.md#createKey) | **POST** /ssh_keys | Create an SSH key |
| [**deleteKey**](SshKeysApi.md#deleteKey) | **DELETE** /ssh_keys/{id} | Delete an SSH key |
| [**getAllSshKeys**](SshKeysApi.md#getAllSshKeys) | **GET** /ssh_keys | Get all SSH keys |
| [**getById**](SshKeysApi.md#getById) | **GET** /ssh_keys/{id} | Get a SSH key |
| [**updateKey**](SshKeysApi.md#updateKey) | **PUT** /ssh_keys/{id} | Update an SSH key |


<a name="createKey"></a>
# **createKey**
> SshKeysCreateKeyResponse createKey().sshKeysCreateKeyRequest(sshKeysCreateKeyRequest).execute();

Create an SSH key

Creates a new SSH key with the given &#x60;name&#x60; and &#x60;public_key&#x60;. Once an SSH key is created, it can be used in other calls such as creating Servers.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.SshKeysApi;
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
    String name = "name_example"; // Name of the SSH key
    String publicKey = "publicKey_example"; // Public key
    Object labels = null; // User-defined labels (key-value pairs)
    try {
      SshKeysCreateKeyResponse result = client
              .sshKeys
              .createKey(name, publicKey)
              .labels(labels)
              .execute();
      System.out.println(result);
      System.out.println(result.getSshKey());
    } catch (ApiException e) {
      System.err.println("Exception when calling SshKeysApi#createKey");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<SshKeysCreateKeyResponse> response = client
              .sshKeys
              .createKey(name, publicKey)
              .labels(labels)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling SshKeysApi#createKey");
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
| **sshKeysCreateKeyRequest** | [**SshKeysCreateKeyRequest**](SshKeysCreateKeyRequest.md)|  | [optional] |

### Return type

[**SshKeysCreateKeyResponse**](SshKeysCreateKeyResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;ssh_key&#x60; key in the reply contains the object that was just created |  -  |

<a name="deleteKey"></a>
# **deleteKey**
> deleteKey(id).execute();

Delete an SSH key

Deletes an SSH key. It cannot be used anymore.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.SshKeysApi;
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
    Long id = 56L; // ID of the SSH key
    try {
      client
              .sshKeys
              .deleteKey(id)
              .execute();
    } catch (ApiException e) {
      System.err.println("Exception when calling SshKeysApi#deleteKey");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      client
              .sshKeys
              .deleteKey(id)
              .executeWithHttpInfo();
    } catch (ApiException e) {
      System.err.println("Exception when calling SshKeysApi#deleteKey");
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
| **id** | **Long**| ID of the SSH key | |

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
| **204** | SSH key deleted |  -  |

<a name="getAllSshKeys"></a>
# **getAllSshKeys**
> SshKeysGetAllSshKeysResponse getAllSshKeys().sort(sort).name(name).fingerprint(fingerprint).labelSelector(labelSelector).page(page).perPage(perPage).execute();

Get all SSH keys

Returns all SSH key objects.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.SshKeysApi;
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
    String sort = "id"; // Can be used multiple times.
    String name = "name_example"; // Filter resources by their name. The response will only contain the resources matching the specified name. 
    String fingerprint = "fingerprint_example"; // Can be used to filter SSH keys by their fingerprint. The response will only contain the SSH key matching the specified fingerprint.
    String labelSelector = "labelSelector_example"; // Filter resources by labels. The response will only contain resources matching the label selector. For more information, see \"[Label Selector](https://docs.hetzner.cloud)\". 
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      SshKeysGetAllSshKeysResponse result = client
              .sshKeys
              .getAllSshKeys()
              .sort(sort)
              .name(name)
              .fingerprint(fingerprint)
              .labelSelector(labelSelector)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getMeta());
      System.out.println(result.getSshKeys());
    } catch (ApiException e) {
      System.err.println("Exception when calling SshKeysApi#getAllSshKeys");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<SshKeysGetAllSshKeysResponse> response = client
              .sshKeys
              .getAllSshKeys()
              .sort(sort)
              .name(name)
              .fingerprint(fingerprint)
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
      System.err.println("Exception when calling SshKeysApi#getAllSshKeys");
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
| **sort** | **String**| Can be used multiple times. | [optional] [enum: id, id:asc, id:desc, name, name:asc, name:desc] |
| **name** | **String**| Filter resources by their name. The response will only contain the resources matching the specified name.  | [optional] |
| **fingerprint** | **String**| Can be used to filter SSH keys by their fingerprint. The response will only contain the SSH key matching the specified fingerprint. | [optional] |
| **labelSelector** | **String**| Filter resources by labels. The response will only contain resources matching the label selector. For more information, see \&quot;[Label Selector](https://docs.hetzner.cloud)\&quot;.  | [optional] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**SshKeysGetAllSshKeysResponse**](SshKeysGetAllSshKeysResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;ssh_keys&#x60; key in the reply contains an array of SSH key objects with this structure |  -  |

<a name="getById"></a>
# **getById**
> SshKeysGetByIdResponse getById(id).execute();

Get a SSH key

Returns a specific SSH key object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.SshKeysApi;
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
    Long id = 56L; // ID of the SSH key
    try {
      SshKeysGetByIdResponse result = client
              .sshKeys
              .getById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getSshKey());
    } catch (ApiException e) {
      System.err.println("Exception when calling SshKeysApi#getById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<SshKeysGetByIdResponse> response = client
              .sshKeys
              .getById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling SshKeysApi#getById");
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
| **id** | **Long**| ID of the SSH key | |

### Return type

[**SshKeysGetByIdResponse**](SshKeysGetByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;ssh_key&#x60; key in the reply contains an SSH key object with this structure |  -  |

<a name="updateKey"></a>
# **updateKey**
> SshKeysUpdateKeyResponse updateKey(id).sshKeysUpdateKeyRequest(sshKeysUpdateKeyRequest).execute();

Update an SSH key

Updates an SSH key. You can update an SSH key name and an SSH key labels.  Please note that when updating labels, the SSH key current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.SshKeysApi;
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
    Long id = 56L; // ID of the SSH key
    Object labels = null; // User-defined labels (key-value pairs)
    String name = "name_example"; // New name Name to set
    try {
      SshKeysUpdateKeyResponse result = client
              .sshKeys
              .updateKey(id)
              .labels(labels)
              .name(name)
              .execute();
      System.out.println(result);
      System.out.println(result.getSshKey());
    } catch (ApiException e) {
      System.err.println("Exception when calling SshKeysApi#updateKey");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<SshKeysUpdateKeyResponse> response = client
              .sshKeys
              .updateKey(id)
              .labels(labels)
              .name(name)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling SshKeysApi#updateKey");
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
| **id** | **Long**| ID of the SSH key | |
| **sshKeysUpdateKeyRequest** | [**SshKeysUpdateKeyRequest**](SshKeysUpdateKeyRequest.md)|  | [optional] |

### Return type

[**SshKeysUpdateKeyResponse**](SshKeysUpdateKeyResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;ssh_key&#x60; key in the reply contains the modified SSH key object with the new description |  -  |

