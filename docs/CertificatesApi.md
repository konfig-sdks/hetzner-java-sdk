# CertificatesApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createNewCertificate**](CertificatesApi.md#createNewCertificate) | **POST** /certificates | Create a Certificate |
| [**deleteCertificate**](CertificatesApi.md#deleteCertificate) | **DELETE** /certificates/{id} | Delete a Certificate |
| [**getAll**](CertificatesApi.md#getAll) | **GET** /certificates | Get all Certificates |
| [**getById**](CertificatesApi.md#getById) | **GET** /certificates/{id} | Get a Certificate |
| [**updateById**](CertificatesApi.md#updateById) | **PUT** /certificates/{id} | Update a Certificate |


<a name="createNewCertificate"></a>
# **createNewCertificate**
> CertificatesCreateNewCertificateResponse createNewCertificate().certificatesCreateNewCertificateRequest(certificatesCreateNewCertificateRequest).execute();

Create a Certificate

Creates a new Certificate.  The default type **uploaded** allows for uploading your existing &#x60;certificate&#x60; and &#x60;private_key&#x60; in PEM format. You have to monitor its expiration date and handle renewal yourself.  In contrast, type **managed** requests a new Certificate from *Let&#39;s Encrypt* for the specified &#x60;domain_names&#x60;. Only domains managed by *Hetzner DNS* are supported. We handle renewal and timely alert the project owner via email if problems occur.  For type &#x60;managed&#x60; Certificates the &#x60;action&#x60; key of the response contains the Action that allows for tracking the issuance process. For type &#x60;uploaded&#x60; Certificates the &#x60;action&#x60; is always null. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.CertificatesApi;
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
    String name = "name_example"; // Name of the Certificate
    String certificate = "certificate_example"; // Certificate and chain in PEM format, in order so that each record directly certifies the one preceding. Required for type `uploaded` Certificates.
    List<String> domainNames = Arrays.asList(); // Domains and subdomains that should be contained in the Certificate issued by *Let's Encrypt*. Required for type `managed` Certificates.
    Object labels = null; // User-defined labels (key-value pairs)
    String privateKey = "privateKey_example"; // Certificate key in PEM format. Required for type `uploaded` Certificates.
    String type = "uploaded"; // Choose between uploading a Certificate in PEM format or requesting a managed *Let's Encrypt* Certificate.
    try {
      CertificatesCreateNewCertificateResponse result = client
              .certificates
              .createNewCertificate(name)
              .certificate(certificate)
              .domainNames(domainNames)
              .labels(labels)
              .privateKey(privateKey)
              .type(type)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
      System.out.println(result.getCertificate());
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificatesApi#createNewCertificate");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<CertificatesCreateNewCertificateResponse> response = client
              .certificates
              .createNewCertificate(name)
              .certificate(certificate)
              .domainNames(domainNames)
              .labels(labels)
              .privateKey(privateKey)
              .type(type)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificatesApi#createNewCertificate");
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
| **certificatesCreateNewCertificateRequest** | [**CertificatesCreateNewCertificateRequest**](CertificatesCreateNewCertificateRequest.md)|  | [optional] |

### Return type

[**CertificatesCreateNewCertificateResponse**](CertificatesCreateNewCertificateResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;certificate&#x60; key contains the Certificate that was just created. For type &#x60;managed&#x60; Certificates the &#x60;action&#x60; key contains the Action that allows for tracking the issuance process. For type &#x60;uploaded&#x60; Certificates the &#x60;action&#x60; is always null. |  -  |

<a name="deleteCertificate"></a>
# **deleteCertificate**
> deleteCertificate(id).execute();

Delete a Certificate

Deletes a Certificate.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.CertificatesApi;
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
              .certificates
              .deleteCertificate(id)
              .execute();
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificatesApi#deleteCertificate");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      client
              .certificates
              .deleteCertificate(id)
              .executeWithHttpInfo();
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificatesApi#deleteCertificate");
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
| **204** | Certificate deleted |  -  |

<a name="getAll"></a>
# **getAll**
> CertificatesGetAllResponse getAll().sort(sort).name(name).labelSelector(labelSelector).type(type).page(page).perPage(perPage).execute();

Get all Certificates

Returns all Certificate objects.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.CertificatesApi;
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
    String type = "uploaded"; // Can be used multiple times. The response will only contain Certificates matching the type.
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      CertificatesGetAllResponse result = client
              .certificates
              .getAll()
              .sort(sort)
              .name(name)
              .labelSelector(labelSelector)
              .type(type)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getCertificates());
      System.out.println(result.getMeta());
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificatesApi#getAll");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<CertificatesGetAllResponse> response = client
              .certificates
              .getAll()
              .sort(sort)
              .name(name)
              .labelSelector(labelSelector)
              .type(type)
              .page(page)
              .perPage(perPage)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificatesApi#getAll");
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
| **type** | **String**| Can be used multiple times. The response will only contain Certificates matching the type. | [optional] [enum: uploaded, managed] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**CertificatesGetAllResponse**](CertificatesGetAllResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;certificates&#x60; key contains an array of Certificate objects |  -  |

<a name="getById"></a>
# **getById**
> CertificatesGetByIdResponse getById(id).execute();

Get a Certificate

Gets a specific Certificate object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.CertificatesApi;
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
      CertificatesGetByIdResponse result = client
              .certificates
              .getById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getCertificate());
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificatesApi#getById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<CertificatesGetByIdResponse> response = client
              .certificates
              .getById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificatesApi#getById");
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

[**CertificatesGetByIdResponse**](CertificatesGetByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;certificate&#x60; key contains a Certificate object |  -  |

<a name="updateById"></a>
# **updateById**
> CertificatesUpdateByIdResponse updateById(id).certificatesUpdateByIdRequest(certificatesUpdateByIdRequest).execute();

Update a Certificate

Updates the Certificate properties.  Note that when updating labels, the Certificate’s current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body.  Note: if the Certificate object changes during the request, the response will be a “conflict” error. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.CertificatesApi;
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
    String name = "name_example"; // New Certificate name
    try {
      CertificatesUpdateByIdResponse result = client
              .certificates
              .updateById(id)
              .labels(labels)
              .name(name)
              .execute();
      System.out.println(result);
      System.out.println(result.getCertificate());
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificatesApi#updateById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<CertificatesUpdateByIdResponse> response = client
              .certificates
              .updateById(id)
              .labels(labels)
              .name(name)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling CertificatesApi#updateById");
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
| **certificatesUpdateByIdRequest** | [**CertificatesUpdateByIdRequest**](CertificatesUpdateByIdRequest.md)|  | [optional] |

### Return type

[**CertificatesUpdateByIdResponse**](CertificatesUpdateByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;certificate&#x60; key contains the Certificate that was just updated |  -  |

