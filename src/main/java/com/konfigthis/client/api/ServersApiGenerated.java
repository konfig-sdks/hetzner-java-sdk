/*
 * Hetzner Cloud API
 * This is the official documentation for the Hetzner Cloud API.  ## Introduction  The Hetzner Cloud API operates over HTTPS and uses JSON as its data format. The API is a RESTful API and utilizes HTTP methods and HTTP status codes to specify requests and responses.  As an alternative to working directly with our API you may also consider to use:  - Our CLI program [hcloud](https://github.com/hetznercloud/cli) - Our [library for Go](https://github.com/hetznercloud/hcloud-go) - Our [library for Python](https://github.com/hetznercloud/hcloud-python)  Also you can find a [list of libraries, tools, and integrations on GitHub](https://github.com/hetznercloud/awesome-hcloud).  If you are developing integrations based on our API and your product is Open Source you may be eligible for a free one time €50 (excl. VAT) credit on your account. Please contact us via the the support page on your Cloud Console and let us know the following:  - The type of integration you would like to develop - Link to the GitHub repo you will use for the Project - Link to some other Open Source work you have already done (if you have done so)  ## Getting Started  To get started using the API you first need an API token. Sign in into the [Hetzner Cloud Console](https://console.hetzner.cloud/) choose a Project, go to `Security` → `API Tokens`, and generate a new token. Make sure to copy the token because it won’t be shown to you again. A token is bound to a Project, to interact with the API of another Project you have to create a new token inside the Project. Let’s say your new token is `LRK9DAWQ1ZAEFSrCNEEzLCUwhYX1U3g7wMg4dTlkkDC96fyDuyJ39nVbVjCKSDfj`.  You’re now ready to do your first request against the API. To get a list of all Servers in your Project, issue the example request on the right side using [curl](https://curl.se/).  Make sure to replace the token in the example command with the token you have just created. Since your Project probably does not contain any Servers yet, the example response will look like the response on the right side. We will almost always provide a resource root like `servers` inside the example response. A response can also contain a `meta` object with information like [Pagination](https://docs.hetzner.cloud).  **Example Request**  ```bash curl -H \"Authorization: Bearer LRK9DAWQ1ZAEFSrCNEEzLCUwhYX1U3g7wMg4dTlkkDC96fyDuyJ39nVbVjCKSDfj\" \\   https://api.hetzner.cloud/v1/servers ```  **Example Response**  ```json {   \"servers\": [],   \"meta\": {     \"pagination\": {       \"page\": 1,       \"per_page\": 25,       \"previous_page\": null,       \"next_page\": null,       \"last_page\": 1,       \"total_entries\": 0     }   } } ```  ## Authentication  All requests to the Hetzner Cloud API must be authenticated via a API token. Include your secret API token in every request you send to the API with the `Authorization` HTTP header.  To create a new API token for your Project, switch into the [Hetzner Cloud Console](https://console.hetzner.cloud/) choose a Project, go to `Security` → `API Tokens`, and generate a new token.  **Example Authorization header**  ```http Authorization: Bearer LRK9DAWQ1ZAEFSrCNEEzLCUwhYX1U3g7wMg4dTlkkDC96fyDuyJ39nVbVjCKSDfj ```  ## Errors  Errors are indicated by HTTP status codes. Further, the response of the request which generated the error contains an error code, an error message, and, optionally, error details. The schema of the error details object depends on the error code.  The error response contains the following keys:  | Keys      | Meaning                                                               | | --------- | --------------------------------------------------------------------- | | `code`    | Short string indicating the type of error (machine-parsable)          | | `message` | Textual description on what has gone wrong                            | | `details` | An object providing for details on the error (schema depends on code) |  **Example response**  ```json {   \"error\": {     \"code\": \"invalid_input\",     \"message\": \"invalid input in field 'broken_field': is too long\",     \"details\": {       \"fields\": [         {           \"name\": \"broken_field\",           \"messages\": [\"is too long\"]         }       ]     }   } } ```  ### Error Codes  | Code                      | Description                                                                      | | ------------------------- | -------------------------------------------------------------------------------- | | `forbidden`               | Insufficient permissions for this request                                        | | `unauthorized`            | Request was made with an invalid or unknown token                                | | `invalid_input`           | Error while parsing or processing the input                                      | | `json_error`              | Invalid JSON input in your request                                               | | `locked`                  | The item you are trying to access is locked (there is already an Action running) | | `not_found`               | Entity not found                                                                 | | `rate_limit_exceeded`     | Error when sending too many requests                                             | | `resource_limit_exceeded` | Error when exceeding the maximum quantity of a resource for an account           | | `resource_unavailable`    | The requested resource is currently unavailable                                  | | `server_error`            | Error within the API backend                                                     | | `service_error`           | Error within a service                                                           | | `uniqueness_error`        | One or more of the objects fields must be unique                                 | | `protected`               | The Action you are trying to start is protected for this resource                | | `maintenance`             | Cannot perform operation due to maintenance                                      | | `conflict`                | The resource has changed during the request, please retry                        | | `unsupported_error`       | The corresponding resource does not support the Action                           | | `token_readonly`          | The token is only allowed to perform GET requests                                | | `unavailable`             | A service or product is currently not available                                  |  **invalid_input**  ```json {   \"error\": {     \"code\": \"invalid_input\",     \"message\": \"invalid input in field 'broken_field': is too long\",     \"details\": {       \"fields\": [         {           \"name\": \"broken_field\",           \"messages\": [\"is too long\"]         }       ]     }   } } ```  **uniqueness_error**  ```json {   \"error\": {     \"code\": \"uniqueness_error\",     \"message\": \"SSH key with the same fingerprint already exists\",     \"details\": {       \"fields\": [         {           \"name\": \"public_key\"         }       ]     }   } } ```  **resource_limit_exceeded**  ```json {   \"error\": {     \"code\": \"resource_limit_exceeded\",     \"message\": \"project limit exceeded\",     \"details\": {       \"limits\": [         {           \"name\": \"project_limit\"         }       ]     }   } } ```  ## Labels  Labels are `key/value` pairs that can be attached to all resources.  Valid label keys have two segments: an optional prefix and name, separated by a slash (`/`). The name segment is required and must be a string of 63 characters or less, beginning and ending with an alphanumeric character (`[a-z0-9A-Z]`) with dashes (`-`), underscores (`_`), dots (`.`), and alphanumerics between. The prefix is optional. If specified, the prefix must be a DNS subdomain: a series of DNS labels separated by dots (`.`), not longer than 253 characters in total, followed by a slash (`/`).  Valid label values must be a string of 63 characters or less and must be empty or begin and end with an alphanumeric character (`[a-z0-9A-Z]`) with dashes (`-`), underscores (`_`), dots (`.`), and alphanumerics between.  The `hetzner.cloud/` prefix is reserved and cannot be used.  **Example Labels**  ```json {   \"labels\": {     \"environment\": \"development\",     \"service\": \"backend\",     \"example.com/my\": \"label\",     \"just-a-key\": \"\"   } } ```  ## Label Selector  For resources with labels, you can filter resources by their labels using the label selector query language.  | Expression           | Meaning                                              | | -------------------- | ---------------------------------------------------- | | `k==v` / `k=v`       | Value of key `k` does equal value `v`                | | `k!=v`               | Value of key `k` does not equal value `v`            | | `k`                  | Key `k` is present                                   | | `!k`                 | Key `k` is not present                               | | `k in (v1,v2,v3)`    | Value of key `k` is `v1`, `v2`, or `v3`              | | `k notin (v1,v2,v3)` | Value of key `k` is neither `v1`, nor `v2`, nor `v3` | | `k1==v,!k2`          | Value of key `k1` is `v` and key `k2` is not present |  ### Examples  - Returns all resources that have a `env=production` label and that don't have a `type=database` label:    `env=production,type!=database`  - Returns all resources that have a `env=testing` or `env=staging` label:    `env in (testing,staging)`  - Returns all resources that don't have a `type` label:    `!type`  ## Pagination  Responses which return multiple items support pagination. If they do support pagination, it can be controlled with following query string parameters:  - A `page` parameter specifies the page to fetch. The number of the first page is 1. - A `per_page` parameter specifies the number of items returned per page. The default value is 25, the maximum value is 50 except otherwise specified in the documentation.  Responses contain a `Link` header with pagination information.  Additionally, if the response body is JSON and the root object is an object, that object has a `pagination` object inside the `meta` object with pagination information:  **Example Pagination**  ```json {     \"servers\": [...],     \"meta\": {         \"pagination\": {             \"page\": 2,             \"per_page\": 25,             \"previous_page\": 1,             \"next_page\": 3,             \"last_page\": 4,             \"total_entries\": 100         }     } } ```  The keys `previous_page`, `next_page`, `last_page`, and `total_entries` may be `null` when on the first page, last page, or when the total number of entries is unknown.  **Example Pagination Link header**  ```http Link: <https://api.hetzner.cloud/v1/actions?page=2&per_page=5>; rel=\"prev\",       <https://api.hetzner.cloud/v1/actions?page=4&per_page=5>; rel=\"next\",       <https://api.hetzner.cloud/v1/actions?page=6&per_page=5>; rel=\"last\" ```  Line breaks have been added for display purposes only and responses may only contain some of the above `rel` values.  ## Rate Limiting  All requests, whether they are authenticated or not, are subject to rate limiting. If you have reached your limit, your requests will be handled with a `429 Too Many Requests` error. Burst requests are allowed. Responses contain serveral headers which provide information about your current rate limit status.  - The `RateLimit-Limit` header contains the total number of requests you can perform per hour. - The `RateLimit-Remaining` header contains the number of requests remaining in the current rate limit time frame. - The `RateLimit-Reset` header contains a UNIX timestamp of the point in time when your rate limit will have recovered and you will have the full number of requests available again.  The default limit is 3600 requests per hour and per Project. The number of remaining requests increases gradually. For example, when your limit is 3600 requests per hour, the number of remaining requests will increase by 1 every second.  ## Server Metadata  Your Server can discover metadata about itself by doing a HTTP request to specific URLs. The following data is available:  | Data              | Format | Contents                                                     | | ----------------- | ------ | ------------------------------------------------------------ | | hostname          | text   | Name of the Server as set in the api                         | | instance-id       | number | ID of the server                                             | | public-ipv4       | text   | Primary public IPv4 address                                  | | private-networks  | yaml   | Details about the private networks the Server is attached to | | availability-zone | text   | Name of the availability zone that Server runs in            | | region            | text   | Network zone, e.g. eu-central                                |  **Example: Summary**  ```bash $ curl http://169.254.169.254/hetzner/v1/metadata ```  ```yaml availability-zone: hel1-dc2 hostname: my-server instance-id: 42 public-ipv4: 1.2.3.4 region: eu-central ```  **Example: Hostname**  ```bash $ curl http://169.254.169.254/hetzner/v1/metadata/hostname my-server ```  **Example: Instance ID**  ```bash $ curl http://169.254.169.254/hetzner/v1/metadata/instance-id 42 ```  **Example: Public IPv4**  ```bash $ curl http://169.254.169.254/hetzner/v1/metadata/public-ipv4 1.2.3.4 ```  **Example: Private Networks**  ```bash $ curl http://169.254.169.254/hetzner/v1/metadata/private-networks ```  ```yaml - ip: 10.0.0.2   alias_ips: [10.0.0.3, 10.0.0.4]   interface_num: 1   mac_address: 86:00:00:2a:7d:e0   network_id: 1234   network_name: nw-test1   network: 10.0.0.0/8   subnet: 10.0.0.0/24   gateway: 10.0.0.1 - ip: 192.168.0.2   alias_ips: []   interface_num: 2   mac_address: 86:00:00:2a:7d:e1   network_id: 4321   network_name: nw-test2   network: 192.168.0.0/16   subnet: 192.168.0.0/24   gateway: 192.168.0.1 ```  **Example: Availability Zone**  ```bash $ curl http://169.254.169.254/hetzner/v1/metadata/availability-zone hel1-dc2 ```  **Example: Region**  ```bash $ curl http://169.254.169.254/hetzner/v1/metadata/region eu-central ```  ## Sorting  Some responses which return multiple items support sorting. If they do support sorting the documentation states which fields can be used for sorting. You specify sorting with the `sort` query string parameter. You can sort by multiple fields. You can set the sort direction by appending `:asc` or `:desc` to the field name. By default, ascending sorting is used.  **Example: Sorting**  ``` https://api.hetzner.cloud/v1/actions?sort=status https://api.hetzner.cloud/v1/actions?sort=status:asc https://api.hetzner.cloud/v1/actions?sort=status:desc https://api.hetzner.cloud/v1/actions?sort=status:asc&sort=command:desc ```  ## Deprecation Notices  You can find all announced deprecations in our [Changelog](https://docs.hetzner.cloud). 
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by Konfig (https://konfigthis.com).
 * Do not edit the class manually.
 */


package com.konfigthis.client.api;

import com.konfigthis.client.ApiCallback;
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiResponse;
import com.konfigthis.client.Configuration;
import com.konfigthis.client.Pair;
import com.konfigthis.client.ProgressRequestBody;
import com.konfigthis.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import com.konfigthis.client.model.ServersCreateServerActionRequest;
import com.konfigthis.client.model.ServersCreateServerActionRequestFirewallsInner;
import com.konfigthis.client.model.ServersCreateServerActionRequestPublicNet;
import com.konfigthis.client.model.ServersCreateServerActionResponse;
import com.konfigthis.client.model.ServersDeleteServerResponse;
import com.konfigthis.client.model.ServersGetAllResponse;
import com.konfigthis.client.model.ServersGetMetricsForServerResponse;
import com.konfigthis.client.model.ServersGetServerResponse;
import com.konfigthis.client.model.ServersUpdateServerRequest;
import com.konfigthis.client.model.ServersUpdateServerResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

public class ServersApiGenerated {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public ServersApiGenerated() throws IllegalArgumentException {
        this(Configuration.getDefaultApiClient());
    }

    public ServersApiGenerated(ApiClient apiClient) throws IllegalArgumentException {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    private okhttp3.Call createServerActionCall(ServersCreateServerActionRequest serversCreateServerActionRequest, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = serversCreateServerActionRequest;

        // create path and map variables
        String localVarPath = "/servers";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "APIToken" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createServerActionValidateBeforeCall(ServersCreateServerActionRequest serversCreateServerActionRequest, final ApiCallback _callback) throws ApiException {
        return createServerActionCall(serversCreateServerActionRequest, _callback);

    }


    private ApiResponse<ServersCreateServerActionResponse> createServerActionWithHttpInfo(ServersCreateServerActionRequest serversCreateServerActionRequest) throws ApiException {
        okhttp3.Call localVarCall = createServerActionValidateBeforeCall(serversCreateServerActionRequest, null);
        Type localVarReturnType = new TypeToken<ServersCreateServerActionResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call createServerActionAsync(ServersCreateServerActionRequest serversCreateServerActionRequest, final ApiCallback<ServersCreateServerActionResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = createServerActionValidateBeforeCall(serversCreateServerActionRequest, _callback);
        Type localVarReturnType = new TypeToken<ServersCreateServerActionResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class CreateServerActionRequestBuilder {
        private final String image;
        private final String name;
        private final String serverType;
        private Boolean automount;
        private String datacenter;
        private List<ServersCreateServerActionRequestFirewallsInner> firewalls;
        private Object labels;
        private String location;
        private List<Long> networks;
        private Long placementGroup;
        private ServersCreateServerActionRequestPublicNet publicNet;
        private List<String> sshKeys;
        private Boolean startAfterCreate;
        private String userData;
        private List<Long> volumes;

        private CreateServerActionRequestBuilder(String image, String name, String serverType) {
            this.image = image;
            this.name = name;
            this.serverType = serverType;
        }

        /**
         * Set automount
         * @param automount Auto-mount Volumes after attach (optional)
         * @return CreateServerActionRequestBuilder
         */
        public CreateServerActionRequestBuilder automount(Boolean automount) {
            this.automount = automount;
            return this;
        }
        
        /**
         * Set datacenter
         * @param datacenter ID or name of Datacenter to create Server in (must not be used together with location) (optional)
         * @return CreateServerActionRequestBuilder
         */
        public CreateServerActionRequestBuilder datacenter(String datacenter) {
            this.datacenter = datacenter;
            return this;
        }
        
        /**
         * Set firewalls
         * @param firewalls Firewalls which should be applied on the Server&#39;s public network interface at creation time (optional)
         * @return CreateServerActionRequestBuilder
         */
        public CreateServerActionRequestBuilder firewalls(List<ServersCreateServerActionRequestFirewallsInner> firewalls) {
            this.firewalls = firewalls;
            return this;
        }
        
        /**
         * Set labels
         * @param labels User-defined labels (key-value pairs) (optional)
         * @return CreateServerActionRequestBuilder
         */
        public CreateServerActionRequestBuilder labels(Object labels) {
            this.labels = labels;
            return this;
        }
        
        /**
         * Set location
         * @param location ID or name of Location to create Server in (must not be used together with datacenter) (optional)
         * @return CreateServerActionRequestBuilder
         */
        public CreateServerActionRequestBuilder location(String location) {
            this.location = location;
            return this;
        }
        
        /**
         * Set networks
         * @param networks Network IDs which should be attached to the Server private network interface at the creation time (optional)
         * @return CreateServerActionRequestBuilder
         */
        public CreateServerActionRequestBuilder networks(List<Long> networks) {
            this.networks = networks;
            return this;
        }
        
        /**
         * Set placementGroup
         * @param placementGroup ID of the Placement Group the server should be in (optional)
         * @return CreateServerActionRequestBuilder
         */
        public CreateServerActionRequestBuilder placementGroup(Long placementGroup) {
            this.placementGroup = placementGroup;
            return this;
        }
        
        /**
         * Set publicNet
         * @param publicNet  (optional)
         * @return CreateServerActionRequestBuilder
         */
        public CreateServerActionRequestBuilder publicNet(ServersCreateServerActionRequestPublicNet publicNet) {
            this.publicNet = publicNet;
            return this;
        }
        
        /**
         * Set sshKeys
         * @param sshKeys SSH key IDs (&#x60;integer&#x60;) or names (&#x60;string&#x60;) which should be injected into the Server at creation time (optional)
         * @return CreateServerActionRequestBuilder
         */
        public CreateServerActionRequestBuilder sshKeys(List<String> sshKeys) {
            this.sshKeys = sshKeys;
            return this;
        }
        
        /**
         * Set startAfterCreate
         * @param startAfterCreate This automatically triggers a [Power on a Server-Server Action](https://docs.hetzner.cloud) after the creation is finished and is returned in the &#x60;next_actions&#x60; response object. (optional, default to true)
         * @return CreateServerActionRequestBuilder
         */
        public CreateServerActionRequestBuilder startAfterCreate(Boolean startAfterCreate) {
            this.startAfterCreate = startAfterCreate;
            return this;
        }
        
        /**
         * Set userData
         * @param userData Cloud-Init user data to use during Server creation. This field is limited to 32KiB. (optional)
         * @return CreateServerActionRequestBuilder
         */
        public CreateServerActionRequestBuilder userData(String userData) {
            this.userData = userData;
            return this;
        }
        
        /**
         * Set volumes
         * @param volumes Volume IDs which should be attached to the Server at the creation time. Volumes must be in the same Location. (optional)
         * @return CreateServerActionRequestBuilder
         */
        public CreateServerActionRequestBuilder volumes(List<Long> volumes) {
            this.volumes = volumes;
            return this;
        }
        
        /**
         * Build call for createServerAction
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;server&#x60; key in the reply contains a Server object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            ServersCreateServerActionRequest serversCreateServerActionRequest = buildBodyParams();
            return createServerActionCall(serversCreateServerActionRequest, _callback);
        }

        private ServersCreateServerActionRequest buildBodyParams() {
            ServersCreateServerActionRequest serversCreateServerActionRequest = new ServersCreateServerActionRequest();
            serversCreateServerActionRequest.automount(this.automount);
            serversCreateServerActionRequest.datacenter(this.datacenter);
            serversCreateServerActionRequest.firewalls(this.firewalls);
            serversCreateServerActionRequest.image(this.image);
            serversCreateServerActionRequest.labels(this.labels);
            serversCreateServerActionRequest.location(this.location);
            serversCreateServerActionRequest.name(this.name);
            serversCreateServerActionRequest.networks(this.networks);
            serversCreateServerActionRequest.placementGroup(this.placementGroup);
            serversCreateServerActionRequest.publicNet(this.publicNet);
            serversCreateServerActionRequest.serverType(this.serverType);
            serversCreateServerActionRequest.sshKeys(this.sshKeys);
            serversCreateServerActionRequest.startAfterCreate(this.startAfterCreate);
            serversCreateServerActionRequest.userData(this.userData);
            serversCreateServerActionRequest.volumes(this.volumes);
            return serversCreateServerActionRequest;
        }

        /**
         * Execute createServerAction request
         * @return ServersCreateServerActionResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;server&#x60; key in the reply contains a Server object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServersCreateServerActionResponse execute() throws ApiException {
            ServersCreateServerActionRequest serversCreateServerActionRequest = buildBodyParams();
            ApiResponse<ServersCreateServerActionResponse> localVarResp = createServerActionWithHttpInfo(serversCreateServerActionRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute createServerAction request with HTTP info returned
         * @return ApiResponse&lt;ServersCreateServerActionResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;server&#x60; key in the reply contains a Server object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServersCreateServerActionResponse> executeWithHttpInfo() throws ApiException {
            ServersCreateServerActionRequest serversCreateServerActionRequest = buildBodyParams();
            return createServerActionWithHttpInfo(serversCreateServerActionRequest);
        }

        /**
         * Execute createServerAction request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;server&#x60; key in the reply contains a Server object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServersCreateServerActionResponse> _callback) throws ApiException {
            ServersCreateServerActionRequest serversCreateServerActionRequest = buildBodyParams();
            return createServerActionAsync(serversCreateServerActionRequest, _callback);
        }
    }

    /**
     * Create a Server
     * Creates a new Server. Returns preliminary information about the Server as well as an Action that covers progress of creation.
     * @return CreateServerActionRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;server&#x60; key in the reply contains a Server object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public CreateServerActionRequestBuilder createServerAction(String image, String name, String serverType) throws IllegalArgumentException {
        if (image == null) throw new IllegalArgumentException("\"image\" is required but got null");
            

        if (name == null) throw new IllegalArgumentException("\"name\" is required but got null");
            

        if (serverType == null) throw new IllegalArgumentException("\"serverType\" is required but got null");
            

        return new CreateServerActionRequestBuilder(image, name, serverType);
    }
    private okhttp3.Call deleteServerCall(Long id, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/servers/{id}"
            .replace("{" + "id" + "}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "APIToken" };
        return localVarApiClient.buildCall(basePath, localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteServerValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling deleteServer(Async)");
        }

        return deleteServerCall(id, _callback);

    }


    private ApiResponse<ServersDeleteServerResponse> deleteServerWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = deleteServerValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServersDeleteServerResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call deleteServerAsync(Long id, final ApiCallback<ServersDeleteServerResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteServerValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServersDeleteServerResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class DeleteServerRequestBuilder {
        private final Long id;

        private DeleteServerRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for deleteServer
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return deleteServerCall(id, _callback);
        }


        /**
         * Execute deleteServer request
         * @return ServersDeleteServerResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServersDeleteServerResponse execute() throws ApiException {
            ApiResponse<ServersDeleteServerResponse> localVarResp = deleteServerWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute deleteServer request with HTTP info returned
         * @return ApiResponse&lt;ServersDeleteServerResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServersDeleteServerResponse> executeWithHttpInfo() throws ApiException {
            return deleteServerWithHttpInfo(id);
        }

        /**
         * Execute deleteServer request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServersDeleteServerResponse> _callback) throws ApiException {
            return deleteServerAsync(id, _callback);
        }
    }

    /**
     * Delete a Server
     * Deletes a Server. This immediately removes the Server from your account, and it is no longer accessible. Any resources attached to the server (like Volumes, Primary IPs, Floating IPs, Firewalls, Placement Groups) are detached while the server is deleted. 
     * @param id ID of the Server (required)
     * @return DeleteServerRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public DeleteServerRequestBuilder deleteServer(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new DeleteServerRequestBuilder(id);
    }
    private okhttp3.Call getAllCall(String name, String labelSelector, String sort, String status, Long page, Long perPage, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/servers";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (name != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("name", name));
        }

        if (labelSelector != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("label_selector", labelSelector));
        }

        if (sort != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("sort", sort));
        }

        if (status != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("status", status));
        }

        if (page != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("page", page));
        }

        if (perPage != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("per_page", perPage));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "APIToken" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getAllValidateBeforeCall(String name, String labelSelector, String sort, String status, Long page, Long perPage, final ApiCallback _callback) throws ApiException {
        return getAllCall(name, labelSelector, sort, status, page, perPage, _callback);

    }


    private ApiResponse<ServersGetAllResponse> getAllWithHttpInfo(String name, String labelSelector, String sort, String status, Long page, Long perPage) throws ApiException {
        okhttp3.Call localVarCall = getAllValidateBeforeCall(name, labelSelector, sort, status, page, perPage, null);
        Type localVarReturnType = new TypeToken<ServersGetAllResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getAllAsync(String name, String labelSelector, String sort, String status, Long page, Long perPage, final ApiCallback<ServersGetAllResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAllValidateBeforeCall(name, labelSelector, sort, status, page, perPage, _callback);
        Type localVarReturnType = new TypeToken<ServersGetAllResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetAllRequestBuilder {
        private String name;
        private String labelSelector;
        private String sort;
        private String status;
        private Long page;
        private Long perPage;

        private GetAllRequestBuilder() {
        }

        /**
         * Set name
         * @param name Filter resources by their name. The response will only contain the resources matching the specified name.  (optional)
         * @return GetAllRequestBuilder
         */
        public GetAllRequestBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        /**
         * Set labelSelector
         * @param labelSelector Filter resources by labels. The response will only contain resources matching the label selector. For more information, see \&quot;[Label Selector](https://docs.hetzner.cloud)\&quot;.  (optional)
         * @return GetAllRequestBuilder
         */
        public GetAllRequestBuilder labelSelector(String labelSelector) {
            this.labelSelector = labelSelector;
            return this;
        }
        
        /**
         * Set sort
         * @param sort Sort resources by field and direction. Can be used multiple times. For more information, see \&quot;[Sorting](https://docs.hetzner.cloud)\&quot;.  (optional)
         * @return GetAllRequestBuilder
         */
        public GetAllRequestBuilder sort(String sort) {
            this.sort = sort;
            return this;
        }
        
        /**
         * Set status
         * @param status Can be used multiple times. The response will only contain Server matching the status (optional)
         * @return GetAllRequestBuilder
         */
        public GetAllRequestBuilder status(String status) {
            this.status = status;
            return this;
        }
        
        /**
         * Set page
         * @param page Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. (optional, default to 1)
         * @return GetAllRequestBuilder
         */
        public GetAllRequestBuilder page(Long page) {
            this.page = page;
            return this;
        }
        
        /**
         * Set perPage
         * @param perPage Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. (optional, default to 25)
         * @return GetAllRequestBuilder
         */
        public GetAllRequestBuilder perPage(Long perPage) {
            this.perPage = perPage;
            return this;
        }
        
        /**
         * Build call for getAll
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> A paged array of servers </td><td>  * x-next - A link to the next page of responses <br>  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getAllCall(name, labelSelector, sort, status, page, perPage, _callback);
        }


        /**
         * Execute getAll request
         * @return ServersGetAllResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> A paged array of servers </td><td>  * x-next - A link to the next page of responses <br>  </td></tr>
         </table>
         */
        public ServersGetAllResponse execute() throws ApiException {
            ApiResponse<ServersGetAllResponse> localVarResp = getAllWithHttpInfo(name, labelSelector, sort, status, page, perPage);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getAll request with HTTP info returned
         * @return ApiResponse&lt;ServersGetAllResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> A paged array of servers </td><td>  * x-next - A link to the next page of responses <br>  </td></tr>
         </table>
         */
        public ApiResponse<ServersGetAllResponse> executeWithHttpInfo() throws ApiException {
            return getAllWithHttpInfo(name, labelSelector, sort, status, page, perPage);
        }

        /**
         * Execute getAll request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> A paged array of servers </td><td>  * x-next - A link to the next page of responses <br>  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServersGetAllResponse> _callback) throws ApiException {
            return getAllAsync(name, labelSelector, sort, status, page, perPage, _callback);
        }
    }

    /**
     * Get all Servers
     * Returns all existing Server objects
     * @return GetAllRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A paged array of servers </td><td>  * x-next - A link to the next page of responses <br>  </td></tr>
     </table>
     */
    public GetAllRequestBuilder getAll() throws IllegalArgumentException {
        return new GetAllRequestBuilder();
    }
    private okhttp3.Call getMetricsForServerCall(Long id, String type, String start, String end, String step, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/servers/{id}/metrics"
            .replace("{" + "id" + "}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (type != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type", type));
        }

        if (start != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("start", start));
        }

        if (end != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("end", end));
        }

        if (step != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("step", step));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "APIToken" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getMetricsForServerValidateBeforeCall(Long id, String type, String start, String end, String step, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getMetricsForServer(Async)");
        }

        // verify the required parameter 'type' is set
        if (type == null) {
            throw new ApiException("Missing the required parameter 'type' when calling getMetricsForServer(Async)");
        }

        // verify the required parameter 'start' is set
        if (start == null) {
            throw new ApiException("Missing the required parameter 'start' when calling getMetricsForServer(Async)");
        }

        // verify the required parameter 'end' is set
        if (end == null) {
            throw new ApiException("Missing the required parameter 'end' when calling getMetricsForServer(Async)");
        }

        return getMetricsForServerCall(id, type, start, end, step, _callback);

    }


    private ApiResponse<ServersGetMetricsForServerResponse> getMetricsForServerWithHttpInfo(Long id, String type, String start, String end, String step) throws ApiException {
        okhttp3.Call localVarCall = getMetricsForServerValidateBeforeCall(id, type, start, end, step, null);
        Type localVarReturnType = new TypeToken<ServersGetMetricsForServerResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getMetricsForServerAsync(Long id, String type, String start, String end, String step, final ApiCallback<ServersGetMetricsForServerResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMetricsForServerValidateBeforeCall(id, type, start, end, step, _callback);
        Type localVarReturnType = new TypeToken<ServersGetMetricsForServerResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetMetricsForServerRequestBuilder {
        private final Long id;
        private final String type;
        private final String start;
        private final String end;
        private String step;

        private GetMetricsForServerRequestBuilder(Long id, String type, String start, String end) {
            this.id = id;
            this.type = type;
            this.start = start;
            this.end = end;
        }

        /**
         * Set step
         * @param step Resolution of results in seconds (optional)
         * @return GetMetricsForServerRequestBuilder
         */
        public GetMetricsForServerRequestBuilder step(String step) {
            this.step = step;
            return this;
        }
        
        /**
         * Build call for getMetricsForServer
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;metrics&#x60; key in the reply contains a metrics object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getMetricsForServerCall(id, type, start, end, step, _callback);
        }


        /**
         * Execute getMetricsForServer request
         * @return ServersGetMetricsForServerResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;metrics&#x60; key in the reply contains a metrics object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServersGetMetricsForServerResponse execute() throws ApiException {
            ApiResponse<ServersGetMetricsForServerResponse> localVarResp = getMetricsForServerWithHttpInfo(id, type, start, end, step);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getMetricsForServer request with HTTP info returned
         * @return ApiResponse&lt;ServersGetMetricsForServerResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;metrics&#x60; key in the reply contains a metrics object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServersGetMetricsForServerResponse> executeWithHttpInfo() throws ApiException {
            return getMetricsForServerWithHttpInfo(id, type, start, end, step);
        }

        /**
         * Execute getMetricsForServer request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;metrics&#x60; key in the reply contains a metrics object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServersGetMetricsForServerResponse> _callback) throws ApiException {
            return getMetricsForServerAsync(id, type, start, end, step, _callback);
        }
    }

    /**
     * Get Metrics for a Server
     * Get Metrics for specified Server.  You must specify the type of metric to get: cpu, disk or network. You can also specify more than one type by comma separation, e.g. cpu,disk.  Depending on the type you will get different time series data  | Type    | Timeseries              | Unit      | Description                                          | |---------|-------------------------|-----------|------------------------------------------------------| | cpu     | cpu                     | percent   | Percent CPU usage                                    | | disk    | disk.0.iops.read        | iop/s     | Number of read IO operations per second              | |         | disk.0.iops.write       | iop/s     | Number of write IO operations per second             | |         | disk.0.bandwidth.read   | bytes/s   | Bytes read per second                                | |         | disk.0.bandwidth.write  | bytes/s   | Bytes written per second                             | | network | network.0.pps.in        | packets/s | Public Network interface packets per second received | |         | network.0.pps.out       | packets/s | Public Network interface packets per second sent     | |         | network.0.bandwidth.in  | bytes/s   | Public Network interface bytes/s received            | |         | network.0.bandwidth.out | bytes/s   | Public Network interface bytes/s sent                |  Metrics are available for the last 30 days only.  If you do not provide the step argument we will automatically adjust it so that a maximum of 200 samples are returned.  We limit the number of samples returned to a maximum of 500 and will adjust the step parameter accordingly. 
     * @param id ID of the Server (required)
     * @param type Type of metrics to get (required)
     * @param start Start of period to get Metrics for (in ISO-8601 format) (required)
     * @param end End of period to get Metrics for (in ISO-8601 format) (required)
     * @return GetMetricsForServerRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;metrics&#x60; key in the reply contains a metrics object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public GetMetricsForServerRequestBuilder getMetricsForServer(Long id, String type, String start, String end) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        if (type == null) throw new IllegalArgumentException("\"type\" is required but got null");
            

        if (start == null) throw new IllegalArgumentException("\"start\" is required but got null");
            

        if (end == null) throw new IllegalArgumentException("\"end\" is required but got null");
            

        return new GetMetricsForServerRequestBuilder(id, type, start, end);
    }
    private okhttp3.Call getServerCall(Long id, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/servers/{id}"
            .replace("{" + "id" + "}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "APIToken" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getServerValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getServer(Async)");
        }

        return getServerCall(id, _callback);

    }


    private ApiResponse<ServersGetServerResponse> getServerWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = getServerValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServersGetServerResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getServerAsync(Long id, final ApiCallback<ServersGetServerResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getServerValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServersGetServerResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetServerRequestBuilder {
        private final Long id;

        private GetServerRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for getServer
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;server&#x60; key in the reply contains a Server object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getServerCall(id, _callback);
        }


        /**
         * Execute getServer request
         * @return ServersGetServerResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;server&#x60; key in the reply contains a Server object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServersGetServerResponse execute() throws ApiException {
            ApiResponse<ServersGetServerResponse> localVarResp = getServerWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getServer request with HTTP info returned
         * @return ApiResponse&lt;ServersGetServerResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;server&#x60; key in the reply contains a Server object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServersGetServerResponse> executeWithHttpInfo() throws ApiException {
            return getServerWithHttpInfo(id);
        }

        /**
         * Execute getServer request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;server&#x60; key in the reply contains a Server object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServersGetServerResponse> _callback) throws ApiException {
            return getServerAsync(id, _callback);
        }
    }

    /**
     * Get a Server
     * Returns a specific Server object. The Server must exist inside the Project
     * @param id ID of the Server (required)
     * @return GetServerRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;server&#x60; key in the reply contains a Server object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public GetServerRequestBuilder getServer(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new GetServerRequestBuilder(id);
    }
    private okhttp3.Call updateServerCall(Long id, ServersUpdateServerRequest serversUpdateServerRequest, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = serversUpdateServerRequest;

        // create path and map variables
        String localVarPath = "/servers/{id}"
            .replace("{" + "id" + "}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "APIToken" };
        return localVarApiClient.buildCall(basePath, localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call updateServerValidateBeforeCall(Long id, ServersUpdateServerRequest serversUpdateServerRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling updateServer(Async)");
        }

        return updateServerCall(id, serversUpdateServerRequest, _callback);

    }


    private ApiResponse<ServersUpdateServerResponse> updateServerWithHttpInfo(Long id, ServersUpdateServerRequest serversUpdateServerRequest) throws ApiException {
        okhttp3.Call localVarCall = updateServerValidateBeforeCall(id, serversUpdateServerRequest, null);
        Type localVarReturnType = new TypeToken<ServersUpdateServerResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call updateServerAsync(Long id, ServersUpdateServerRequest serversUpdateServerRequest, final ApiCallback<ServersUpdateServerResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = updateServerValidateBeforeCall(id, serversUpdateServerRequest, _callback);
        Type localVarReturnType = new TypeToken<ServersUpdateServerResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class UpdateServerRequestBuilder {
        private final Long id;
        private Object labels;
        private String name;

        private UpdateServerRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Set labels
         * @param labels User-defined labels (key-value pairs) (optional)
         * @return UpdateServerRequestBuilder
         */
        public UpdateServerRequestBuilder labels(Object labels) {
            this.labels = labels;
            return this;
        }
        
        /**
         * Set name
         * @param name New name to set (optional)
         * @return UpdateServerRequestBuilder
         */
        public UpdateServerRequestBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        /**
         * Build call for updateServer
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;server&#x60; key in the reply contains the updated Server </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            ServersUpdateServerRequest serversUpdateServerRequest = buildBodyParams();
            return updateServerCall(id, serversUpdateServerRequest, _callback);
        }

        private ServersUpdateServerRequest buildBodyParams() {
            ServersUpdateServerRequest serversUpdateServerRequest = new ServersUpdateServerRequest();
            serversUpdateServerRequest.labels(this.labels);
            serversUpdateServerRequest.name(this.name);
            return serversUpdateServerRequest;
        }

        /**
         * Execute updateServer request
         * @return ServersUpdateServerResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;server&#x60; key in the reply contains the updated Server </td><td>  -  </td></tr>
         </table>
         */
        public ServersUpdateServerResponse execute() throws ApiException {
            ServersUpdateServerRequest serversUpdateServerRequest = buildBodyParams();
            ApiResponse<ServersUpdateServerResponse> localVarResp = updateServerWithHttpInfo(id, serversUpdateServerRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute updateServer request with HTTP info returned
         * @return ApiResponse&lt;ServersUpdateServerResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;server&#x60; key in the reply contains the updated Server </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServersUpdateServerResponse> executeWithHttpInfo() throws ApiException {
            ServersUpdateServerRequest serversUpdateServerRequest = buildBodyParams();
            return updateServerWithHttpInfo(id, serversUpdateServerRequest);
        }

        /**
         * Execute updateServer request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;server&#x60; key in the reply contains the updated Server </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServersUpdateServerResponse> _callback) throws ApiException {
            ServersUpdateServerRequest serversUpdateServerRequest = buildBodyParams();
            return updateServerAsync(id, serversUpdateServerRequest, _callback);
        }
    }

    /**
     * Update a Server
     * Updates a Server. You can update a Server’s name and a Server’s labels. Please note that Server names must be unique per Project and valid hostnames as per RFC 1123 (i.e. may only contain letters, digits, periods, and dashes). Also note that when updating labels, the Server’s current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body.
     * @param id ID of the Server (required)
     * @return UpdateServerRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;server&#x60; key in the reply contains the updated Server </td><td>  -  </td></tr>
     </table>
     */
    public UpdateServerRequestBuilder updateServer(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new UpdateServerRequestBuilder(id);
    }
}
