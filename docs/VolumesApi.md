# VolumesApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createVolume**](VolumesApi.md#createVolume) | **POST** /volumes | Create a Volume |
| [**deleteVolume**](VolumesApi.md#deleteVolume) | **DELETE** /volumes/{id} | Delete a Volume |
| [**getAll**](VolumesApi.md#getAll) | **GET** /volumes | Get all Volumes |
| [**getById**](VolumesApi.md#getById) | **GET** /volumes/{id} | Get a Volume |
| [**updateVolumeProperties**](VolumesApi.md#updateVolumeProperties) | **PUT** /volumes/{id} | Update a Volume |


<a name="createVolume"></a>
# **createVolume**
> VolumesCreateVolumeResponse createVolume().volumesCreateVolumeRequest(volumesCreateVolumeRequest).execute();

Create a Volume

Creates a new Volume attached to a Server. If you want to create a Volume that is not attached to a Server, you need to provide the &#x60;location&#x60; key instead of &#x60;server&#x60;. This can be either the ID or the name of the Location this Volume will be created in. Note that a Volume can be attached to a Server only in the same Location as the Volume itself.  Specifying the Server during Volume creation will automatically attach the Volume to that Server after it has been initialized. In that case, the &#x60;next_actions&#x60; key in the response is an array which contains a single &#x60;attach_volume&#x60; action.  The minimum Volume size is 10GB and the maximum size is 10TB (10240GB).  A volume’s name can consist of alphanumeric characters, dashes, underscores, and dots, but has to start and end with an alphanumeric character. The total length is limited to 64 characters. Volume names must be unique per Project.  #### Call specific error codes  | Code                                | Description                                         | |-------------------------------------|-----------------------------------------------------| | &#x60;no_space_left_in_location&#x60;         | There is no volume space left in the given location | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.VolumesApi;
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
    String name = "name_example"; // Name of the volume
    Integer size = 56; // Size of the Volume in GB
    Boolean automount = true; // Auto-mount Volume after attach. `server` must be provided.
    String format = "format_example"; // Format Volume after creation. One of: `xfs`, `ext4`
    Object labels = null; // User-defined labels (key-value pairs)
    String location = "location_example"; // Location to create the Volume in (can be omitted if Server is specified)
    Long server = 56L; // Server to which to attach the Volume once it's created (Volume will be created in the same Location as the server)
    try {
      VolumesCreateVolumeResponse result = client
              .volumes
              .createVolume(name, size)
              .automount(automount)
              .format(format)
              .labels(labels)
              .location(location)
              .server(server)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
      System.out.println(result.getNextActions());
      System.out.println(result.getVolume());
    } catch (ApiException e) {
      System.err.println("Exception when calling VolumesApi#createVolume");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<VolumesCreateVolumeResponse> response = client
              .volumes
              .createVolume(name, size)
              .automount(automount)
              .format(format)
              .labels(labels)
              .location(location)
              .server(server)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling VolumesApi#createVolume");
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
| **volumesCreateVolumeRequest** | [**VolumesCreateVolumeRequest**](VolumesCreateVolumeRequest.md)|  | [optional] |

### Return type

[**VolumesCreateVolumeResponse**](VolumesCreateVolumeResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;volume&#x60; key contains the Volume that was just created  The &#x60;action&#x60; key contains the Action tracking Volume creation  |  -  |

<a name="deleteVolume"></a>
# **deleteVolume**
> deleteVolume(id).execute();

Delete a Volume

Deletes a volume. All Volume data is irreversibly destroyed. The Volume must not be attached to a Server and it must not have delete protection enabled.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.VolumesApi;
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
    Long id = 56L; // ID of the Volume
    try {
      client
              .volumes
              .deleteVolume(id)
              .execute();
    } catch (ApiException e) {
      System.err.println("Exception when calling VolumesApi#deleteVolume");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      client
              .volumes
              .deleteVolume(id)
              .executeWithHttpInfo();
    } catch (ApiException e) {
      System.err.println("Exception when calling VolumesApi#deleteVolume");
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
| **id** | **Long**| ID of the Volume | |

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
| **204** | Volume deleted |  -  |

<a name="getAll"></a>
# **getAll**
> VolumesGetAllResponse getAll().status(status).sort(sort).name(name).labelSelector(labelSelector).page(page).perPage(perPage).execute();

Get all Volumes

Gets all existing Volumes that you have available.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.VolumesApi;
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
    String status = "available"; // Can be used multiple times. The response will only contain Volumes matching the status.
    String sort = "id"; // Sort resources by field and direction. Can be used multiple times. For more information, see \"[Sorting](https://docs.hetzner.cloud)\". 
    String name = "name_example"; // Filter resources by their name. The response will only contain the resources matching the specified name. 
    String labelSelector = "labelSelector_example"; // Filter resources by labels. The response will only contain resources matching the label selector. For more information, see \"[Label Selector](https://docs.hetzner.cloud)\". 
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      VolumesGetAllResponse result = client
              .volumes
              .getAll()
              .status(status)
              .sort(sort)
              .name(name)
              .labelSelector(labelSelector)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getMeta());
      System.out.println(result.getVolumes());
    } catch (ApiException e) {
      System.err.println("Exception when calling VolumesApi#getAll");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<VolumesGetAllResponse> response = client
              .volumes
              .getAll()
              .status(status)
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
      System.err.println("Exception when calling VolumesApi#getAll");
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
| **status** | **String**| Can be used multiple times. The response will only contain Volumes matching the status. | [optional] [enum: available, creating] |
| **sort** | **String**| Sort resources by field and direction. Can be used multiple times. For more information, see \&quot;[Sorting](https://docs.hetzner.cloud)\&quot;.  | [optional] [enum: id, id:asc, id:desc, name, name:asc, name:desc, created, created:asc, created:desc] |
| **name** | **String**| Filter resources by their name. The response will only contain the resources matching the specified name.  | [optional] |
| **labelSelector** | **String**| Filter resources by labels. The response will only contain resources matching the label selector. For more information, see \&quot;[Label Selector](https://docs.hetzner.cloud)\&quot;.  | [optional] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**VolumesGetAllResponse**](VolumesGetAllResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;volumes&#x60; key contains a list of volumes |  -  |

<a name="getById"></a>
# **getById**
> VolumesGetByIdResponse getById(id).execute();

Get a Volume

Gets a specific Volume object.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.VolumesApi;
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
    Long id = 56L; // ID of the Volume
    try {
      VolumesGetByIdResponse result = client
              .volumes
              .getById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getVolume());
    } catch (ApiException e) {
      System.err.println("Exception when calling VolumesApi#getById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<VolumesGetByIdResponse> response = client
              .volumes
              .getById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling VolumesApi#getById");
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
| **id** | **Long**| ID of the Volume | |

### Return type

[**VolumesGetByIdResponse**](VolumesGetByIdResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;volume&#x60; key contains the volume |  -  |

<a name="updateVolumeProperties"></a>
# **updateVolumeProperties**
> VolumesUpdateVolumePropertiesResponse updateVolumeProperties(id).volumesUpdateVolumePropertiesRequest(volumesUpdateVolumePropertiesRequest).execute();

Update a Volume

Updates the Volume properties.  Note that when updating labels, the volume’s current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.VolumesApi;
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
    Long id = 56L; // ID of the Volume to update
    VolumesUpdateVolumePropertiesRequestLabels labels = new VolumesUpdateVolumePropertiesRequestLabels();
    String name = "name_example"; // New Volume name
    try {
      VolumesUpdateVolumePropertiesResponse result = client
              .volumes
              .updateVolumeProperties(id)
              .labels(labels)
              .name(name)
              .execute();
      System.out.println(result);
      System.out.println(result.getVolume());
    } catch (ApiException e) {
      System.err.println("Exception when calling VolumesApi#updateVolumeProperties");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<VolumesUpdateVolumePropertiesResponse> response = client
              .volumes
              .updateVolumeProperties(id)
              .labels(labels)
              .name(name)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling VolumesApi#updateVolumeProperties");
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
| **id** | **Long**| ID of the Volume to update | |
| **volumesUpdateVolumePropertiesRequest** | [**VolumesUpdateVolumePropertiesRequest**](VolumesUpdateVolumePropertiesRequest.md)|  | [optional] |

### Return type

[**VolumesUpdateVolumePropertiesResponse**](VolumesUpdateVolumePropertiesResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;volume&#x60; key contains the updated volume |  -  |

