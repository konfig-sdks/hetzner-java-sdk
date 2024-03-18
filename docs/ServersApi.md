# ServersApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createServerAction**](ServersApi.md#createServerAction) | **POST** /servers | Create a Server |
| [**deleteServer**](ServersApi.md#deleteServer) | **DELETE** /servers/{id} | Delete a Server |
| [**getAll**](ServersApi.md#getAll) | **GET** /servers | Get all Servers |
| [**getMetricsForServer**](ServersApi.md#getMetricsForServer) | **GET** /servers/{id}/metrics | Get Metrics for a Server |
| [**getServer**](ServersApi.md#getServer) | **GET** /servers/{id} | Get a Server |
| [**updateServer**](ServersApi.md#updateServer) | **PUT** /servers/{id} | Update a Server |


<a name="createServerAction"></a>
# **createServerAction**
> ServersCreateServerActionResponse createServerAction().serversCreateServerActionRequest(serversCreateServerActionRequest).execute();

Create a Server

Creates a new Server. Returns preliminary information about the Server as well as an Action that covers progress of creation.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServersApi;
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
    String image = "image_example"; // ID or name of the Image the Server is created from
    String name = "name_example"; // Name of the Server to create (must be unique per Project and a valid hostname as per RFC 1123)
    String serverType = "serverType_example"; // ID or name of the Server type this Server should be created with
    Boolean automount = true; // Auto-mount Volumes after attach
    String datacenter = "datacenter_example"; // ID or name of Datacenter to create Server in (must not be used together with location)
    List<ServersCreateServerActionRequestFirewallsInner> firewalls = Arrays.asList(); // Firewalls which should be applied on the Server's public network interface at creation time
    Object labels = null; // User-defined labels (key-value pairs)
    String location = "location_example"; // ID or name of Location to create Server in (must not be used together with datacenter)
    List<Long> networks = Arrays.asList(); // Network IDs which should be attached to the Server private network interface at the creation time
    Long placementGroup = 56L; // ID of the Placement Group the server should be in
    ServersCreateServerActionRequestPublicNet publicNet = new ServersCreateServerActionRequestPublicNet();
    List<String> sshKeys = Arrays.asList(); // SSH key IDs (`integer`) or names (`string`) which should be injected into the Server at creation time
    Boolean startAfterCreate = true; // This automatically triggers a [Power on a Server-Server Action](https://docs.hetzner.cloud) after the creation is finished and is returned in the `next_actions` response object.
    String userData = "userData_example"; // Cloud-Init user data to use during Server creation. This field is limited to 32KiB.
    List<Long> volumes = Arrays.asList(); // Volume IDs which should be attached to the Server at the creation time. Volumes must be in the same Location.
    try {
      ServersCreateServerActionResponse result = client
              .servers
              .createServerAction(image, name, serverType)
              .automount(automount)
              .datacenter(datacenter)
              .firewalls(firewalls)
              .labels(labels)
              .location(location)
              .networks(networks)
              .placementGroup(placementGroup)
              .publicNet(publicNet)
              .sshKeys(sshKeys)
              .startAfterCreate(startAfterCreate)
              .userData(userData)
              .volumes(volumes)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
      System.out.println(result.getNextActions());
      System.out.println(result.getRootPassword());
      System.out.println(result.getServer());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServersApi#createServerAction");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServersCreateServerActionResponse> response = client
              .servers
              .createServerAction(image, name, serverType)
              .automount(automount)
              .datacenter(datacenter)
              .firewalls(firewalls)
              .labels(labels)
              .location(location)
              .networks(networks)
              .placementGroup(placementGroup)
              .publicNet(publicNet)
              .sshKeys(sshKeys)
              .startAfterCreate(startAfterCreate)
              .userData(userData)
              .volumes(volumes)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServersApi#createServerAction");
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
| **serversCreateServerActionRequest** | [**ServersCreateServerActionRequest**](ServersCreateServerActionRequest.md)| Please note that Server names must be unique per Project and valid hostnames as per RFC 1123 (i.e. may only contain letters, digits, periods, and dashes).  For &#x60;server_type&#x60; you can either use the ID as listed in &#x60;/server_types&#x60; or its name.  For &#x60;image&#x60; you can either use the ID as listed in &#x60;/images&#x60; or its name.  If you want to create the Server in a Location, you must set &#x60;location&#x60; to the ID or name as listed in &#x60;/locations&#x60;. This is the recommended way. You can be even more specific by setting &#x60;datacenter&#x60; to the ID or name as listed in &#x60;/datacenters&#x60;. However we only recommend this if you want to assign a specific Primary IP to the Server which is located in the specified Datacenter.  Some properties like &#x60;start_after_create&#x60; or &#x60;automount&#x60; will trigger Actions after the Server is created. Those Actions are listed in the &#x60;next_actions&#x60; field in the response.  For accessing your Server we strongly recommend to use SSH keys by passing the respective key IDs in &#x60;ssh_keys&#x60;. If you do not specify any &#x60;ssh_keys&#x60; we will generate a root password for you and return it in the response.  Please note that provided user-data is stored in our systems. While we take measures to protect it we highly recommend that you don’t use it to store passwords or other sensitive information.  #### Call specific error codes  | Code                             | Description                                                | |----------------------------------|------------------------------------------------------------| | &#x60;placement_error&#x60;                | An error during the placement occurred                     | | &#x60;primary_ip_assigned&#x60;            | The specified Primary IP is already assigned to a server   | | &#x60;primary_ip_datacenter_mismatch&#x60; | The specified Primary IP is in a different datacenter      | | &#x60;primary_ip_version_mismatch&#x60;    | The specified Primary IP has the wrong IP Version          |  | [optional] |

### Return type

[**ServersCreateServerActionResponse**](ServersCreateServerActionResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;server&#x60; key in the reply contains a Server object with this structure |  -  |

<a name="deleteServer"></a>
# **deleteServer**
> ServersDeleteServerResponse deleteServer(id).execute();

Delete a Server

Deletes a Server. This immediately removes the Server from your account, and it is no longer accessible. Any resources attached to the server (like Volumes, Primary IPs, Floating IPs, Firewalls, Placement Groups) are detached while the server is deleted. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServersApi;
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
    Long id = 56L; // ID of the Server
    try {
      ServersDeleteServerResponse result = client
              .servers
              .deleteServer(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServersApi#deleteServer");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServersDeleteServerResponse> response = client
              .servers
              .deleteServer(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServersApi#deleteServer");
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
| **id** | **Long**| ID of the Server | |

### Return type

[**ServersDeleteServerResponse**](ServersDeleteServerResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="getAll"></a>
# **getAll**
> ServersGetAllResponse getAll().name(name).labelSelector(labelSelector).sort(sort).status(status).page(page).perPage(perPage).execute();

Get all Servers

Returns all existing Server objects

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServersApi;
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
    String sort = "id"; // Sort resources by field and direction. Can be used multiple times. For more information, see \"[Sorting](https://docs.hetzner.cloud)\". 
    String status = "initializing"; // Can be used multiple times. The response will only contain Server matching the status
    Long page = 1L; // Page number to return. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    Long perPage = 25L; // Maximum number of entries returned per page. For more information, see \"[Pagination](https://docs.hetzner.cloud)\".
    try {
      ServersGetAllResponse result = client
              .servers
              .getAll()
              .name(name)
              .labelSelector(labelSelector)
              .sort(sort)
              .status(status)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getServers());
      System.out.println(result.getMeta());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServersApi#getAll");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServersGetAllResponse> response = client
              .servers
              .getAll()
              .name(name)
              .labelSelector(labelSelector)
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
      System.err.println("Exception when calling ServersApi#getAll");
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
| **sort** | **String**| Sort resources by field and direction. Can be used multiple times. For more information, see \&quot;[Sorting](https://docs.hetzner.cloud)\&quot;.  | [optional] [enum: id, id:asc, id:desc, name, name:asc, name:desc, created, created:asc, created:desc] |
| **status** | **String**| Can be used multiple times. The response will only contain Server matching the status | [optional] [enum: initializing, starting, running, stopping, false, deleting, rebuilding, migrating, unknown] |
| **page** | **Long**| Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 1] |
| **perPage** | **Long**| Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. | [optional] [default to 25] |

### Return type

[**ServersGetAllResponse**](ServersGetAllResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A paged array of servers |  * x-next - A link to the next page of responses <br>  |

<a name="getMetricsForServer"></a>
# **getMetricsForServer**
> ServersGetMetricsForServerResponse getMetricsForServer(id, type, start, end).step(step).execute();

Get Metrics for a Server

Get Metrics for specified Server.  You must specify the type of metric to get: cpu, disk or network. You can also specify more than one type by comma separation, e.g. cpu,disk.  Depending on the type you will get different time series data  | Type    | Timeseries              | Unit      | Description                                          | |---------|-------------------------|-----------|------------------------------------------------------| | cpu     | cpu                     | percent   | Percent CPU usage                                    | | disk    | disk.0.iops.read        | iop/s     | Number of read IO operations per second              | |         | disk.0.iops.write       | iop/s     | Number of write IO operations per second             | |         | disk.0.bandwidth.read   | bytes/s   | Bytes read per second                                | |         | disk.0.bandwidth.write  | bytes/s   | Bytes written per second                             | | network | network.0.pps.in        | packets/s | Public Network interface packets per second received | |         | network.0.pps.out       | packets/s | Public Network interface packets per second sent     | |         | network.0.bandwidth.in  | bytes/s   | Public Network interface bytes/s received            | |         | network.0.bandwidth.out | bytes/s   | Public Network interface bytes/s sent                |  Metrics are available for the last 30 days only.  If you do not provide the step argument we will automatically adjust it so that a maximum of 200 samples are returned.  We limit the number of samples returned to a maximum of 500 and will adjust the step parameter accordingly. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServersApi;
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
    Long id = 56L; // ID of the Server
    String type = "cpu"; // Type of metrics to get
    String start = "start_example"; // Start of period to get Metrics for (in ISO-8601 format)
    String end = "end_example"; // End of period to get Metrics for (in ISO-8601 format)
    String step = "step_example"; // Resolution of results in seconds
    try {
      ServersGetMetricsForServerResponse result = client
              .servers
              .getMetricsForServer(id, type, start, end)
              .step(step)
              .execute();
      System.out.println(result);
      System.out.println(result.getMetrics());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServersApi#getMetricsForServer");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServersGetMetricsForServerResponse> response = client
              .servers
              .getMetricsForServer(id, type, start, end)
              .step(step)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServersApi#getMetricsForServer");
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
| **id** | **Long**| ID of the Server | |
| **type** | **String**| Type of metrics to get | [enum: cpu, disk, network] |
| **start** | **String**| Start of period to get Metrics for (in ISO-8601 format) | |
| **end** | **String**| End of period to get Metrics for (in ISO-8601 format) | |
| **step** | **String**| Resolution of results in seconds | [optional] |

### Return type

[**ServersGetMetricsForServerResponse**](ServersGetMetricsForServerResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;metrics&#x60; key in the reply contains a metrics object with this structure |  -  |

<a name="getServer"></a>
# **getServer**
> ServersGetServerResponse getServer(id).execute();

Get a Server

Returns a specific Server object. The Server must exist inside the Project

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServersApi;
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
    Long id = 56L; // ID of the Server
    try {
      ServersGetServerResponse result = client
              .servers
              .getServer(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getServer());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServersApi#getServer");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServersGetServerResponse> response = client
              .servers
              .getServer(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServersApi#getServer");
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
| **id** | **Long**| ID of the Server | |

### Return type

[**ServersGetServerResponse**](ServersGetServerResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;server&#x60; key in the reply contains a Server object with this structure |  -  |

<a name="updateServer"></a>
# **updateServer**
> ServersUpdateServerResponse updateServer(id).serversUpdateServerRequest(serversUpdateServerRequest).execute();

Update a Server

Updates a Server. You can update a Server’s name and a Server’s labels. Please note that Server names must be unique per Project and valid hostnames as per RFC 1123 (i.e. may only contain letters, digits, periods, and dashes). Also note that when updating labels, the Server’s current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServersApi;
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
    Long id = 56L; // ID of the Server
    Object labels = null; // User-defined labels (key-value pairs)
    String name = "name_example"; // New name to set
    try {
      ServersUpdateServerResponse result = client
              .servers
              .updateServer(id)
              .labels(labels)
              .name(name)
              .execute();
      System.out.println(result);
      System.out.println(result.getServer());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServersApi#updateServer");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServersUpdateServerResponse> response = client
              .servers
              .updateServer(id)
              .labels(labels)
              .name(name)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServersApi#updateServer");
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
| **id** | **Long**| ID of the Server | |
| **serversUpdateServerRequest** | [**ServersUpdateServerRequest**](ServersUpdateServerRequest.md)|  | [optional] |

### Return type

[**ServersUpdateServerResponse**](ServersUpdateServerResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;server&#x60; key in the reply contains the updated Server |  -  |

