# ServerActionsApi

All URIs are relative to *https://api.hetzner.cloud/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addToPlacementGroup**](ServerActionsApi.md#addToPlacementGroup) | **POST** /servers/{id}/actions/add_to_placement_group | Add a Server to a Placement Group |
| [**attachIsoToServer**](ServerActionsApi.md#attachIsoToServer) | **POST** /servers/{id}/actions/attach_iso | Attach an ISO to a Server |
| [**attachToNetwork**](ServerActionsApi.md#attachToNetwork) | **POST** /servers/{id}/actions/attach_to_network | Attach a Server to a Network |
| [**changeAliasIps**](ServerActionsApi.md#changeAliasIps) | **POST** /servers/{id}/actions/change_alias_ips | Change alias IPs of a Network |
| [**changeDnsPtr**](ServerActionsApi.md#changeDnsPtr) | **POST** /servers/{id}/actions/change_dns_ptr | Change reverse DNS entry for this Server |
| [**changeProtection**](ServerActionsApi.md#changeProtection) | **POST** /servers/{id}/actions/change_protection | Change Server Protection |
| [**changeServerType**](ServerActionsApi.md#changeServerType) | **POST** /servers/{id}/actions/change_type | Change the Type of a Server |
| [**createImage**](ServerActionsApi.md#createImage) | **POST** /servers/{id}/actions/create_image | Create Image from a Server |
| [**detachFromNetwork**](ServerActionsApi.md#detachFromNetwork) | **POST** /servers/{id}/actions/detach_from_network | Detach a Server from a Network |
| [**detachIsoFromServer**](ServerActionsApi.md#detachIsoFromServer) | **POST** /servers/{id}/actions/detach_iso | Detach an ISO from a Server |
| [**disableBackup**](ServerActionsApi.md#disableBackup) | **POST** /servers/{id}/actions/disable_backup | Disable Backups for a Server |
| [**disableRescueMode**](ServerActionsApi.md#disableRescueMode) | **POST** /servers/{id}/actions/disable_rescue | Disable Rescue Mode for a Server |
| [**enableBackup**](ServerActionsApi.md#enableBackup) | **POST** /servers/{id}/actions/enable_backup | Enable and Configure Backups for a Server |
| [**enableRescueMode**](ServerActionsApi.md#enableRescueMode) | **POST** /servers/{id}/actions/enable_rescue | Enable Rescue Mode for a Server |
| [**getActionById**](ServerActionsApi.md#getActionById) | **GET** /servers/actions/{id} | Get an Action |
| [**getActionById_0**](ServerActionsApi.md#getActionById_0) | **GET** /servers/{id}/actions/{action_id} | Get an Action for a Server |
| [**getAll**](ServerActionsApi.md#getAll) | **GET** /servers/actions | Get all Actions |
| [**getAllActions**](ServerActionsApi.md#getAllActions) | **GET** /servers/{id}/actions | Get all Actions for a Server |
| [**gracefulShutdown**](ServerActionsApi.md#gracefulShutdown) | **POST** /servers/{id}/actions/shutdown | Shutdown a Server |
| [**powerOffServer**](ServerActionsApi.md#powerOffServer) | **POST** /servers/{id}/actions/poweroff | Power off a Server |
| [**powerOnServer**](ServerActionsApi.md#powerOnServer) | **POST** /servers/{id}/actions/poweron | Power on a Server |
| [**rebuildServerFromImage**](ServerActionsApi.md#rebuildServerFromImage) | **POST** /servers/{id}/actions/rebuild | Rebuild a Server from an Image |
| [**removeFromPlacementGroup**](ServerActionsApi.md#removeFromPlacementGroup) | **POST** /servers/{id}/actions/remove_from_placement_group | Remove from Placement Group |
| [**requestConsole**](ServerActionsApi.md#requestConsole) | **POST** /servers/{id}/actions/request_console | Request Console for a Server |
| [**resetServer**](ServerActionsApi.md#resetServer) | **POST** /servers/{id}/actions/reset | Reset a Server |
| [**resetServerPassword**](ServerActionsApi.md#resetServerPassword) | **POST** /servers/{id}/actions/reset_password | Reset root Password of a Server |
| [**softRebootServer**](ServerActionsApi.md#softRebootServer) | **POST** /servers/{id}/actions/reboot | Soft-reboot a Server |


<a name="addToPlacementGroup"></a>
# **addToPlacementGroup**
> ServerActionsAddToPlacementGroupResponse addToPlacementGroup(id).serverActionsAddToPlacementGroupRequest(serverActionsAddToPlacementGroupRequest).execute();

Add a Server to a Placement Group

Adds a Server to a Placement Group.  Server must be powered off for this command to succeed.  #### Call specific error codes  | Code                          | Description                                                          | |-------------------------------|----------------------------------------------------------------------| | &#x60;server_not_stopped&#x60;          | The action requires a stopped server                                 | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
    Long placementGroup = 56L; // ID of Placement Group the Server should be added to
    Long id = 56L; // ID of the Server
    try {
      ServerActionsAddToPlacementGroupResponse result = client
              .serverActions
              .addToPlacementGroup(placementGroup, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#addToPlacementGroup");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsAddToPlacementGroupResponse> response = client
              .serverActions
              .addToPlacementGroup(placementGroup, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#addToPlacementGroup");
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
| **serverActionsAddToPlacementGroupRequest** | [**ServerActionsAddToPlacementGroupRequest**](ServerActionsAddToPlacementGroupRequest.md)|  | [optional] |

### Return type

[**ServerActionsAddToPlacementGroupResponse**](ServerActionsAddToPlacementGroupResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="attachIsoToServer"></a>
# **attachIsoToServer**
> ServerActionsAttachIsoToServerResponse attachIsoToServer(id).serverActionsAttachIsoToServerRequest(serverActionsAttachIsoToServerRequest).execute();

Attach an ISO to a Server

Attaches an ISO to a Server. The Server will immediately see it as a new disk. An already attached ISO will automatically be detached before the new ISO is attached.  Servers with attached ISOs have a modified boot order: They will try to boot from the ISO first before falling back to hard disk. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
    String iso = "iso_example"; // ID or name of ISO to attach to the Server as listed in GET `/isos`
    Long id = 56L; // ID of the Server
    try {
      ServerActionsAttachIsoToServerResponse result = client
              .serverActions
              .attachIsoToServer(iso, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#attachIsoToServer");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsAttachIsoToServerResponse> response = client
              .serverActions
              .attachIsoToServer(iso, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#attachIsoToServer");
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
| **serverActionsAttachIsoToServerRequest** | [**ServerActionsAttachIsoToServerRequest**](ServerActionsAttachIsoToServerRequest.md)|  | [optional] |

### Return type

[**ServerActionsAttachIsoToServerResponse**](ServerActionsAttachIsoToServerResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="attachToNetwork"></a>
# **attachToNetwork**
> ServerActionsAttachToNetworkResponse attachToNetwork(id).serverActionsAttachToNetworkRequest(serverActionsAttachToNetworkRequest).execute();

Attach a Server to a Network

Attaches a Server to a network. This will complement the fixed public Server interface by adding an additional ethernet interface to the Server which is connected to the specified network.  The Server will get an IP auto assigned from a subnet of type &#x60;server&#x60; in the same &#x60;network_zone&#x60;.  Using the &#x60;alias_ips&#x60; attribute you can also define one or more additional IPs to the Servers. Please note that you will have to configure these IPs by hand on your Server since only the primary IP will be given out by DHCP.  **Call specific error codes**  | Code                             | Description                                                           | |----------------------------------|-----------------------------------------------------------------------| | &#x60;server_already_attached&#x60;        | The server is already attached to the network                         | | &#x60;ip_not_available&#x60;               | The provided Network IP is not available                              | | &#x60;no_subnet_available&#x60;            | No Subnet or IP is available for the Server within the network        | | &#x60;networks_overlap&#x60;               | The network IP range overlaps with one of the server networks         | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
    Long network = 56L; // ID of an existing network to attach the Server to
    Long id = 56L; // ID of the Server
    List<String> aliasIps = Arrays.asList(); // Additional IPs to be assigned to this Server
    String ip = "ip_example"; // IP to request to be assigned to this Server; if you do not provide this then you will be auto assigned an IP address
    try {
      ServerActionsAttachToNetworkResponse result = client
              .serverActions
              .attachToNetwork(network, id)
              .aliasIps(aliasIps)
              .ip(ip)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#attachToNetwork");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsAttachToNetworkResponse> response = client
              .serverActions
              .attachToNetwork(network, id)
              .aliasIps(aliasIps)
              .ip(ip)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#attachToNetwork");
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
| **serverActionsAttachToNetworkRequest** | [**ServerActionsAttachToNetworkRequest**](ServerActionsAttachToNetworkRequest.md)|  | [optional] |

### Return type

[**ServerActionsAttachToNetworkResponse**](ServerActionsAttachToNetworkResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="changeAliasIps"></a>
# **changeAliasIps**
> ServerActionsChangeAliasIpsResponse changeAliasIps(id).serverActionsChangeAliasIpsRequest(serverActionsChangeAliasIpsRequest).execute();

Change alias IPs of a Network

Changes the alias IPs of an already attached Network. Note that the existing aliases for the specified Network will be replaced with these provided in the request body. So if you want to add an alias IP, you have to provide the existing ones from the Network plus the new alias IP in the request body.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
    List<String> aliasIps = Arrays.asList(); // New alias IPs to set for this Server
    Long network = 56L; // ID of an existing Network already attached to the Server
    Long id = 56L; // ID of the Server
    try {
      ServerActionsChangeAliasIpsResponse result = client
              .serverActions
              .changeAliasIps(aliasIps, network, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#changeAliasIps");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsChangeAliasIpsResponse> response = client
              .serverActions
              .changeAliasIps(aliasIps, network, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#changeAliasIps");
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
| **serverActionsChangeAliasIpsRequest** | [**ServerActionsChangeAliasIpsRequest**](ServerActionsChangeAliasIpsRequest.md)|  | [optional] |

### Return type

[**ServerActionsChangeAliasIpsResponse**](ServerActionsChangeAliasIpsResponse.md)

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
> ServerActionsChangeDnsPtrResponse changeDnsPtr(id).serverActionsChangeDnsPtrRequest(serverActionsChangeDnsPtrRequest).execute();

Change reverse DNS entry for this Server

Changes the hostname that will appear when getting the hostname belonging to the primary IPs (IPv4 and IPv6) of this Server.  Floating IPs assigned to the Server are not affected by this. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
    String dnsPtr = "dnsPtr_example"; // Hostname to set as a reverse DNS PTR entry, reset to original value if `null`
    String ip = "ip_example"; // Primary IP address for which the reverse DNS entry should be set
    Long id = 56L; // ID of the Server
    try {
      ServerActionsChangeDnsPtrResponse result = client
              .serverActions
              .changeDnsPtr(dnsPtr, ip, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#changeDnsPtr");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsChangeDnsPtrResponse> response = client
              .serverActions
              .changeDnsPtr(dnsPtr, ip, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#changeDnsPtr");
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
| **serverActionsChangeDnsPtrRequest** | [**ServerActionsChangeDnsPtrRequest**](ServerActionsChangeDnsPtrRequest.md)| Select the IP address for which to change the DNS entry by passing &#x60;ip&#x60;. It can be either IPv4 or IPv6. The target hostname is set by passing &#x60;dns_ptr&#x60;. | [optional] |

### Return type

[**ServerActionsChangeDnsPtrResponse**](ServerActionsChangeDnsPtrResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="changeProtection"></a>
# **changeProtection**
> ServerActionsChangeProtectionResponse changeProtection(id).serverActionsChangeProtectionRequest(serverActionsChangeProtectionRequest).execute();

Change Server Protection

Changes the protection configuration of the Server.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
    Boolean delete = true; // If true, prevents the Server from being deleted (currently delete and rebuild attribute needs to have the same value)
    Boolean rebuild = true; // If true, prevents the Server from being rebuilt (currently delete and rebuild attribute needs to have the same value)
    try {
      ServerActionsChangeProtectionResponse result = client
              .serverActions
              .changeProtection(id)
              .delete(delete)
              .rebuild(rebuild)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#changeProtection");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsChangeProtectionResponse> response = client
              .serverActions
              .changeProtection(id)
              .delete(delete)
              .rebuild(rebuild)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#changeProtection");
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
| **serverActionsChangeProtectionRequest** | [**ServerActionsChangeProtectionRequest**](ServerActionsChangeProtectionRequest.md)|  | [optional] |

### Return type

[**ServerActionsChangeProtectionResponse**](ServerActionsChangeProtectionResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="changeServerType"></a>
# **changeServerType**
> ServerActionsChangeServerTypeResponse changeServerType(id).serverActionsChangeServerTypeRequest(serverActionsChangeServerTypeRequest).execute();

Change the Type of a Server

Changes the type (Cores, RAM and disk sizes) of a Server.  Server must be powered off for this command to succeed.  This copies the content of its disk, and starts it again.  You can only migrate to Server types with the same &#x60;storage_type&#x60; and equal or bigger disks. Shrinking disks is not possible as it might destroy data.  If the disk gets upgraded, the Server type can not be downgraded any more. If you plan to downgrade the Server type, set &#x60;upgrade_disk&#x60; to &#x60;false&#x60;.  #### Call specific error codes  | Code                          | Description                                                          | |-------------------------------|----------------------------------------------------------------------| | &#x60;invalid_server_type&#x60;         | The server type does not fit for the given server or is deprecated   | | &#x60;server_not_stopped&#x60;          | The action requires a stopped server                                 | 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
    String serverType = "serverType_example"; // ID or name of Server type the Server should migrate to
    Boolean upgradeDisk = true; // If false, do not upgrade the disk (this allows downgrading the Server type later)
    Long id = 56L; // ID of the Server
    try {
      ServerActionsChangeServerTypeResponse result = client
              .serverActions
              .changeServerType(serverType, upgradeDisk, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#changeServerType");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsChangeServerTypeResponse> response = client
              .serverActions
              .changeServerType(serverType, upgradeDisk, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#changeServerType");
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
| **serverActionsChangeServerTypeRequest** | [**ServerActionsChangeServerTypeRequest**](ServerActionsChangeServerTypeRequest.md)|  | [optional] |

### Return type

[**ServerActionsChangeServerTypeResponse**](ServerActionsChangeServerTypeResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="createImage"></a>
# **createImage**
> ServerActionsCreateImageResponse createImage(id).serverActionsCreateImageRequest(serverActionsCreateImageRequest).execute();

Create Image from a Server

Creates an Image (snapshot) from a Server by copying the contents of its disks. This creates a snapshot of the current state of the disk and copies it into an Image. If the Server is currently running you must make sure that its disk content is consistent. Otherwise, the created Image may not be readable.  To make sure disk content is consistent, we recommend to shut down the Server prior to creating an Image.  You can either create a &#x60;backup&#x60; Image that is bound to the Server and therefore will be deleted when the Server is deleted, or you can create a &#x60;snapshot&#x60; Image which is completely independent of the Server it was created from and will survive Server deletion. Backup Images are only available when the backup option is enabled for the Server. Snapshot Images are billed on a per GB basis. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
    String description = "description_example"; // Description of the Image, will be auto-generated if not set
    ServerActionsCreateImageRequestLabels labels = new ServerActionsCreateImageRequestLabels();
    String type = "snapshot"; // Type of Image to create.
    try {
      ServerActionsCreateImageResponse result = client
              .serverActions
              .createImage(id)
              .description(description)
              .labels(labels)
              .type(type)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
      System.out.println(result.getImage());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#createImage");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsCreateImageResponse> response = client
              .serverActions
              .createImage(id)
              .description(description)
              .labels(labels)
              .type(type)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#createImage");
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
| **serverActionsCreateImageRequest** | [**ServerActionsCreateImageRequest**](ServerActionsCreateImageRequest.md)|  | [optional] |

### Return type

[**ServerActionsCreateImageResponse**](ServerActionsCreateImageResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;image&#x60; key in the reply contains an the created Image, which is an object with this structure  The &#x60;action&#x60; key in the reply contains an Action object with this structure  |  -  |

<a name="detachFromNetwork"></a>
# **detachFromNetwork**
> ServerActionsDetachFromNetworkResponse detachFromNetwork(id).serverActionsDetachFromNetworkRequest(serverActionsDetachFromNetworkRequest).execute();

Detach a Server from a Network

Detaches a Server from a network. The interface for this network will vanish.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
    Long network = 56L; // ID of an existing network to detach the Server from
    Long id = 56L; // ID of the Server
    try {
      ServerActionsDetachFromNetworkResponse result = client
              .serverActions
              .detachFromNetwork(network, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#detachFromNetwork");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsDetachFromNetworkResponse> response = client
              .serverActions
              .detachFromNetwork(network, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#detachFromNetwork");
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
| **serverActionsDetachFromNetworkRequest** | [**ServerActionsDetachFromNetworkRequest**](ServerActionsDetachFromNetworkRequest.md)|  | [optional] |

### Return type

[**ServerActionsDetachFromNetworkResponse**](ServerActionsDetachFromNetworkResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="detachIsoFromServer"></a>
# **detachIsoFromServer**
> ServerActionsDetachIsoFromServerResponse detachIsoFromServer(id).execute();

Detach an ISO from a Server

Detaches an ISO from a Server. In case no ISO Image is attached to the Server, the status of the returned Action is immediately set to &#x60;success&#x60;

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsDetachIsoFromServerResponse result = client
              .serverActions
              .detachIsoFromServer(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#detachIsoFromServer");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsDetachIsoFromServerResponse> response = client
              .serverActions
              .detachIsoFromServer(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#detachIsoFromServer");
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

[**ServerActionsDetachIsoFromServerResponse**](ServerActionsDetachIsoFromServerResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="disableBackup"></a>
# **disableBackup**
> ServerActionsDisableBackupResponse disableBackup(id).execute();

Disable Backups for a Server

Disables the automatic backup option and deletes all existing Backups for a Server. No more additional charges for backups will be made.  Caution: This immediately removes all existing backups for the Server! 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsDisableBackupResponse result = client
              .serverActions
              .disableBackup(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#disableBackup");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsDisableBackupResponse> response = client
              .serverActions
              .disableBackup(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#disableBackup");
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

[**ServerActionsDisableBackupResponse**](ServerActionsDisableBackupResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="disableRescueMode"></a>
# **disableRescueMode**
> ServerActionsDisableRescueModeResponse disableRescueMode(id).execute();

Disable Rescue Mode for a Server

Disables the Hetzner Rescue System for a Server. This makes a Server start from its disks on next reboot.  Rescue Mode is automatically disabled when you first boot into it or if you do not use it for 60 minutes.  Disabling rescue mode will not reboot your Server — you will have to do this yourself. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsDisableRescueModeResponse result = client
              .serverActions
              .disableRescueMode(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#disableRescueMode");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsDisableRescueModeResponse> response = client
              .serverActions
              .disableRescueMode(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#disableRescueMode");
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

[**ServerActionsDisableRescueModeResponse**](ServerActionsDisableRescueModeResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="enableBackup"></a>
# **enableBackup**
> ServerActionsEnableBackupResponse enableBackup(id).execute();

Enable and Configure Backups for a Server

Enables and configures the automatic daily backup option for the Server. Enabling automatic backups will increase the price of the Server by 20%. In return, you will get seven slots where Images of type backup can be stored.  Backups are automatically created daily. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsEnableBackupResponse result = client
              .serverActions
              .enableBackup(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#enableBackup");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsEnableBackupResponse> response = client
              .serverActions
              .enableBackup(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#enableBackup");
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

[**ServerActionsEnableBackupResponse**](ServerActionsEnableBackupResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="enableRescueMode"></a>
# **enableRescueMode**
> ServerActionsEnableRescueModeResponse enableRescueMode(id).serverActionsEnableRescueModeRequest(serverActionsEnableRescueModeRequest).execute();

Enable Rescue Mode for a Server

Enable the Hetzner Rescue System for this Server. The next time a Server with enabled rescue mode boots it will start a special minimal Linux distribution designed for repair and reinstall.  In case a Server cannot boot on its own you can use this to access a Server’s disks.  Rescue Mode is automatically disabled when you first boot into it or if you do not use it for 60 minutes.  Enabling rescue mode will not [reboot](https://docs.hetzner.cloud/#server-actions-soft-reboot-a-server) your Server — you will have to do this yourself. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
    List<Long> sshKeys = Arrays.asList(); // Array of SSH key IDs which should be injected into the rescue system.
    String type = "linux64"; // Type of rescue system to boot.
    try {
      ServerActionsEnableRescueModeResponse result = client
              .serverActions
              .enableRescueMode(id)
              .sshKeys(sshKeys)
              .type(type)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
      System.out.println(result.getRootPassword());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#enableRescueMode");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsEnableRescueModeResponse> response = client
              .serverActions
              .enableRescueMode(id)
              .sshKeys(sshKeys)
              .type(type)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#enableRescueMode");
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
| **serverActionsEnableRescueModeRequest** | [**ServerActionsEnableRescueModeRequest**](ServerActionsEnableRescueModeRequest.md)|  | [optional] |

### Return type

[**ServerActionsEnableRescueModeResponse**](ServerActionsEnableRescueModeResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;root_password&#x60; key in the reply contains the root password that can be used to access the booted rescue system.  The &#x60;action&#x60; key in the reply contains an Action object with this structure  |  -  |

<a name="getActionById"></a>
# **getActionById**
> ServerActionsGetActionByIdResponse getActionById(id).execute();

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
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsGetActionByIdResponse result = client
              .serverActions
              .getActionById(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#getActionById");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsGetActionByIdResponse> response = client
              .serverActions
              .getActionById(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#getActionById");
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

[**ServerActionsGetActionByIdResponse**](ServerActionsGetActionByIdResponse.md)

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
> ServerActionsGetActionById200Response getActionById_0(id, actionId).execute();

Get an Action for a Server

Returns a specific Action object for a Server.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
    Long actionId = 56L; // ID of the Action
    try {
      ServerActionsGetActionById200Response result = client
              .serverActions
              .getActionById_0(id, actionId)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#getActionById_0");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsGetActionById200Response> response = client
              .serverActions
              .getActionById_0(id, actionId)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#getActionById_0");
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
| **actionId** | **Long**| ID of the Action | |

### Return type

[**ServerActionsGetActionById200Response**](ServerActionsGetActionById200Response.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;action&#x60; key in the reply has this structure |  -  |

<a name="getAll"></a>
# **getAll**
> ServerActionsGetAllResponse getAll().id(id).sort(sort).status(status).page(page).perPage(perPage).execute();

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
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsGetAllResponse result = client
              .serverActions
              .getAll()
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
      System.err.println("Exception when calling ServerActionsApi#getAll");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsGetAllResponse> response = client
              .serverActions
              .getAll()
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
      System.err.println("Exception when calling ServerActionsApi#getAll");
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

[**ServerActionsGetAllResponse**](ServerActionsGetAllResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;actions&#x60; key contains a list of Actions |  -  |

<a name="getAllActions"></a>
# **getAllActions**
> ServerActionsGetAllActionsResponse getAllActions(id).sort(sort).status(status).page(page).perPage(perPage).execute();

Get all Actions for a Server

Returns all Action objects for a Server. You can &#x60;sort&#x60; the results by using the sort URI parameter, and filter them with the &#x60;status&#x60; parameter.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsGetAllActionsResponse result = client
              .serverActions
              .getAllActions(id)
              .sort(sort)
              .status(status)
              .page(page)
              .perPage(perPage)
              .execute();
      System.out.println(result);
      System.out.println(result.getActions());
      System.out.println(result.getMeta());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#getAllActions");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsGetAllActionsResponse> response = client
              .serverActions
              .getAllActions(id)
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
      System.err.println("Exception when calling ServerActionsApi#getAllActions");
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

[**ServerActionsGetAllActionsResponse**](ServerActionsGetAllActionsResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The &#x60;actions&#x60; key contains a list of Actions |  -  |

<a name="gracefulShutdown"></a>
# **gracefulShutdown**
> ServerActionsGracefulShutdownResponse gracefulShutdown(id).execute();

Shutdown a Server

Shuts down a Server gracefully by sending an ACPI shutdown request. The Server operating system must support ACPI and react to the request, otherwise the Server will not shut down. Please note that the &#x60;action&#x60; status in this case only reflects whether the action was sent to the server. It does not mean that the server actually shut down successfully. If you need to ensure that the server is off, use the &#x60;poweroff&#x60; action 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsGracefulShutdownResponse result = client
              .serverActions
              .gracefulShutdown(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#gracefulShutdown");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsGracefulShutdownResponse> response = client
              .serverActions
              .gracefulShutdown(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#gracefulShutdown");
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

[**ServerActionsGracefulShutdownResponse**](ServerActionsGracefulShutdownResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="powerOffServer"></a>
# **powerOffServer**
> ServerActionsPowerOffServerResponse powerOffServer(id).execute();

Power off a Server

Cuts power to the Server. This forcefully stops it without giving the Server operating system time to gracefully stop. May lead to data loss, equivalent to pulling the power cord. Power off should only be used when shutdown does not work.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsPowerOffServerResponse result = client
              .serverActions
              .powerOffServer(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#powerOffServer");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsPowerOffServerResponse> response = client
              .serverActions
              .powerOffServer(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#powerOffServer");
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

[**ServerActionsPowerOffServerResponse**](ServerActionsPowerOffServerResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="powerOnServer"></a>
# **powerOnServer**
> ServerActionsPowerOnServerResponse powerOnServer(id).execute();

Power on a Server

Starts a Server by turning its power on.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsPowerOnServerResponse result = client
              .serverActions
              .powerOnServer(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#powerOnServer");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsPowerOnServerResponse> response = client
              .serverActions
              .powerOnServer(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#powerOnServer");
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

[**ServerActionsPowerOnServerResponse**](ServerActionsPowerOnServerResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="rebuildServerFromImage"></a>
# **rebuildServerFromImage**
> ServerActionsRebuildServerFromImageResponse rebuildServerFromImage(id).serverActionsRebuildServerFromImageRequest(serverActionsRebuildServerFromImageRequest).execute();

Rebuild a Server from an Image

Rebuilds a Server overwriting its disk with the content of an Image, thereby **destroying all data** on the target Server  The Image can either be one you have created earlier (&#x60;backup&#x60; or &#x60;snapshot&#x60; Image) or it can be a completely fresh &#x60;system&#x60; Image provided by us. You can get a list of all available Images with &#x60;GET /images&#x60;.  Your Server will automatically be powered off before the rebuild command executes. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
    String image = "image_example"; // ID or name of Image to rebuilt from.
    Long id = 56L; // ID of the Server
    try {
      ServerActionsRebuildServerFromImageResponse result = client
              .serverActions
              .rebuildServerFromImage(image, id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
      System.out.println(result.getRootPassword());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#rebuildServerFromImage");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsRebuildServerFromImageResponse> response = client
              .serverActions
              .rebuildServerFromImage(image, id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#rebuildServerFromImage");
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
| **serverActionsRebuildServerFromImageRequest** | [**ServerActionsRebuildServerFromImageRequest**](ServerActionsRebuildServerFromImageRequest.md)| To select which Image to rebuild from you can either pass an ID or a name as the &#x60;image&#x60; argument. Passing a name only works for &#x60;system&#x60; Images since the other Image types do not have a name set. | [optional] |

### Return type

[**ServerActionsRebuildServerFromImageResponse**](ServerActionsRebuildServerFromImageResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="removeFromPlacementGroup"></a>
# **removeFromPlacementGroup**
> ServerActionsRemoveFromPlacementGroupResponse removeFromPlacementGroup(id).execute();

Remove from Placement Group

Removes a Server from a Placement Group. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsRemoveFromPlacementGroupResponse result = client
              .serverActions
              .removeFromPlacementGroup(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#removeFromPlacementGroup");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsRemoveFromPlacementGroupResponse> response = client
              .serverActions
              .removeFromPlacementGroup(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#removeFromPlacementGroup");
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

[**ServerActionsRemoveFromPlacementGroupResponse**](ServerActionsRemoveFromPlacementGroupResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="requestConsole"></a>
# **requestConsole**
> ServerActionsRequestConsoleResponse requestConsole(id).execute();

Request Console for a Server

Requests credentials for remote access via VNC over websocket to keyboard, monitor, and mouse for a Server. The provided URL is valid for 1 minute, after this period a new url needs to be created to connect to the Server. How long the connection is open after the initial connect is not subject to this timeout.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsRequestConsoleResponse result = client
              .serverActions
              .requestConsole(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
      System.out.println(result.getPassword());
      System.out.println(result.getWssUrl());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#requestConsole");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsRequestConsoleResponse> response = client
              .serverActions
              .requestConsole(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#requestConsole");
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

[**ServerActionsRequestConsoleResponse**](ServerActionsRequestConsoleResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="resetServer"></a>
# **resetServer**
> ServerActionsResetServerResponse resetServer(id).execute();

Reset a Server

Cuts power to a Server and starts it again. This forcefully stops it without giving the Server operating system time to gracefully stop. This may lead to data loss, it’s equivalent to pulling the power cord and plugging it in again. Reset should only be used when reboot does not work.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsResetServerResponse result = client
              .serverActions
              .resetServer(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#resetServer");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsResetServerResponse> response = client
              .serverActions
              .resetServer(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#resetServer");
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

[**ServerActionsResetServerResponse**](ServerActionsResetServerResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

<a name="resetServerPassword"></a>
# **resetServerPassword**
> ServerActionsResetServerPasswordResponse resetServerPassword(id).execute();

Reset root Password of a Server

Resets the root password. Only works for Linux systems that are running the qemu guest agent. Server must be powered on (status &#x60;running&#x60;) in order for this operation to succeed.  This will generate a new password for this Server and return it.  If this does not succeed you can use the rescue system to netboot the Server and manually change your Server password by hand. 

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsResetServerPasswordResponse result = client
              .serverActions
              .resetServerPassword(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
      System.out.println(result.getRootPassword());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#resetServerPassword");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsResetServerPasswordResponse> response = client
              .serverActions
              .resetServerPassword(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#resetServerPassword");
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

[**ServerActionsResetServerPasswordResponse**](ServerActionsResetServerPasswordResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;root_password&#x60; key in the reply contains the new root password that will be active if the Action succeeds.  The &#x60;action&#x60; key in the reply contains an Action object with this structure:  |  -  |

<a name="softRebootServer"></a>
# **softRebootServer**
> ServerActionsSoftRebootServerResponse softRebootServer(id).execute();

Soft-reboot a Server

Reboots a Server gracefully by sending an ACPI request. The Server operating system must support ACPI and react to the request, otherwise the Server will not reboot.

### Example
```java
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Hetzner;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.auth.*;
import com.konfigthis.client.model.*;
import com.konfigthis.client.api.ServerActionsApi;
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
      ServerActionsSoftRebootServerResponse result = client
              .serverActions
              .softRebootServer(id)
              .execute();
      System.out.println(result);
      System.out.println(result.getAction());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#softRebootServer");
      System.err.println("Status code: " + e.getStatusCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }

    // Use .executeWithHttpInfo() to retrieve HTTP Status Code, Headers and Request
    try {
      ApiResponse<ServerActionsSoftRebootServerResponse> response = client
              .serverActions
              .softRebootServer(id)
              .executeWithHttpInfo();
      System.out.println(response.getResponseBody());
      System.out.println(response.getResponseHeaders());
      System.out.println(response.getStatusCode());
      System.out.println(response.getRoundTripTime());
      System.out.println(response.getRequest());
    } catch (ApiException e) {
      System.err.println("Exception when calling ServerActionsApi#softRebootServer");
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

[**ServerActionsSoftRebootServerResponse**](ServerActionsSoftRebootServerResponse.md)

### Authorization

[APIToken](../README.md#APIToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | The &#x60;action&#x60; key in the reply contains an Action object with this structure |  -  |

