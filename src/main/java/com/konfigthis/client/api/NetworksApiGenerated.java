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


import com.konfigthis.client.model.NetworksCreateNetworkRequest;
import com.konfigthis.client.model.NetworksCreateNetworkRequestLabels;
import com.konfigthis.client.model.NetworksCreateNetworkRequestRoutesInner;
import com.konfigthis.client.model.NetworksCreateNetworkRequestSubnetsInner;
import com.konfigthis.client.model.NetworksCreateNetworkResponse;
import com.konfigthis.client.model.NetworksGetAllResponse;
import com.konfigthis.client.model.NetworksGetByIdResponse;
import com.konfigthis.client.model.NetworksUpdatePropertiesRequest;
import com.konfigthis.client.model.NetworksUpdatePropertiesRequestLabels;
import com.konfigthis.client.model.NetworksUpdatePropertiesResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

public class NetworksApiGenerated {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public NetworksApiGenerated() throws IllegalArgumentException {
        this(Configuration.getDefaultApiClient());
    }

    public NetworksApiGenerated(ApiClient apiClient) throws IllegalArgumentException {
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

    private okhttp3.Call createNetworkCall(NetworksCreateNetworkRequest networksCreateNetworkRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = networksCreateNetworkRequest;

        // create path and map variables
        String localVarPath = "/networks";

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
    private okhttp3.Call createNetworkValidateBeforeCall(NetworksCreateNetworkRequest networksCreateNetworkRequest, final ApiCallback _callback) throws ApiException {
        return createNetworkCall(networksCreateNetworkRequest, _callback);

    }


    private ApiResponse<NetworksCreateNetworkResponse> createNetworkWithHttpInfo(NetworksCreateNetworkRequest networksCreateNetworkRequest) throws ApiException {
        okhttp3.Call localVarCall = createNetworkValidateBeforeCall(networksCreateNetworkRequest, null);
        Type localVarReturnType = new TypeToken<NetworksCreateNetworkResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call createNetworkAsync(NetworksCreateNetworkRequest networksCreateNetworkRequest, final ApiCallback<NetworksCreateNetworkResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = createNetworkValidateBeforeCall(networksCreateNetworkRequest, _callback);
        Type localVarReturnType = new TypeToken<NetworksCreateNetworkResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class CreateNetworkRequestBuilder {
        private final String ipRange;
        private final String name;
        private Boolean exposeRoutesToVswitch;
        private NetworksCreateNetworkRequestLabels labels;
        private List<NetworksCreateNetworkRequestRoutesInner> routes;
        private List<NetworksCreateNetworkRequestSubnetsInner> subnets;

        private CreateNetworkRequestBuilder(String ipRange, String name) {
            this.ipRange = ipRange;
            this.name = name;
        }

        /**
         * Set exposeRoutesToVswitch
         * @param exposeRoutesToVswitch Indicates if the routes from this network should be exposed to the vSwitch connection. The exposing only takes effect if a vSwitch connection is active. (optional)
         * @return CreateNetworkRequestBuilder
         */
        public CreateNetworkRequestBuilder exposeRoutesToVswitch(Boolean exposeRoutesToVswitch) {
            this.exposeRoutesToVswitch = exposeRoutesToVswitch;
            return this;
        }
        
        /**
         * Set labels
         * @param labels  (optional)
         * @return CreateNetworkRequestBuilder
         */
        public CreateNetworkRequestBuilder labels(NetworksCreateNetworkRequestLabels labels) {
            this.labels = labels;
            return this;
        }
        
        /**
         * Set routes
         * @param routes Array of routes set in this network. The destination of the route must be one of the private IPv4 ranges of RFC1918. The gateway must be a subnet/IP of the ip_range of the network object. The destination must not overlap with an existing ip_range in any subnets or with any destinations in other routes or with the first IP of the networks ip_range or with 172.31.1.1. The gateway cannot be the first IP of the networks ip_range and also cannot be 172.31.1.1. (optional)
         * @return CreateNetworkRequestBuilder
         */
        public CreateNetworkRequestBuilder routes(List<NetworksCreateNetworkRequestRoutesInner> routes) {
            this.routes = routes;
            return this;
        }
        
        /**
         * Set subnets
         * @param subnets Array of subnets allocated. (optional)
         * @return CreateNetworkRequestBuilder
         */
        public CreateNetworkRequestBuilder subnets(List<NetworksCreateNetworkRequestSubnetsInner> subnets) {
            this.subnets = subnets;
            return this;
        }
        
        /**
         * Build call for createNetwork
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;network&#x60; key contains the network that was just created </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            NetworksCreateNetworkRequest networksCreateNetworkRequest = buildBodyParams();
            return createNetworkCall(networksCreateNetworkRequest, _callback);
        }

        private NetworksCreateNetworkRequest buildBodyParams() {
            NetworksCreateNetworkRequest networksCreateNetworkRequest = new NetworksCreateNetworkRequest();
            networksCreateNetworkRequest.exposeRoutesToVswitch(this.exposeRoutesToVswitch);
            networksCreateNetworkRequest.ipRange(this.ipRange);
            networksCreateNetworkRequest.labels(this.labels);
            networksCreateNetworkRequest.name(this.name);
            networksCreateNetworkRequest.routes(this.routes);
            networksCreateNetworkRequest.subnets(this.subnets);
            return networksCreateNetworkRequest;
        }

        /**
         * Execute createNetwork request
         * @return NetworksCreateNetworkResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;network&#x60; key contains the network that was just created </td><td>  -  </td></tr>
         </table>
         */
        public NetworksCreateNetworkResponse execute() throws ApiException {
            NetworksCreateNetworkRequest networksCreateNetworkRequest = buildBodyParams();
            ApiResponse<NetworksCreateNetworkResponse> localVarResp = createNetworkWithHttpInfo(networksCreateNetworkRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute createNetwork request with HTTP info returned
         * @return ApiResponse&lt;NetworksCreateNetworkResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;network&#x60; key contains the network that was just created </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<NetworksCreateNetworkResponse> executeWithHttpInfo() throws ApiException {
            NetworksCreateNetworkRequest networksCreateNetworkRequest = buildBodyParams();
            return createNetworkWithHttpInfo(networksCreateNetworkRequest);
        }

        /**
         * Execute createNetwork request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;network&#x60; key contains the network that was just created </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<NetworksCreateNetworkResponse> _callback) throws ApiException {
            NetworksCreateNetworkRequest networksCreateNetworkRequest = buildBodyParams();
            return createNetworkAsync(networksCreateNetworkRequest, _callback);
        }
    }

    /**
     * Create a Network
     * Creates a network with the specified &#x60;ip_range&#x60;.  You may specify one or more &#x60;subnets&#x60;. You can also add more Subnets later by using the [add subnet action](https://docs.hetzner.cloud/#network-actions-add-a-subnet-to-a-network). If you do not specify an &#x60;ip_range&#x60; in the subnet we will automatically pick the first available /24 range for you.  You may specify one or more routes in &#x60;routes&#x60;. You can also add more routes later by using the [add route action](https://docs.hetzner.cloud/#network-actions-add-a-route-to-a-network). 
     * @return CreateNetworkRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;network&#x60; key contains the network that was just created </td><td>  -  </td></tr>
     </table>
     */
    public CreateNetworkRequestBuilder createNetwork(String ipRange, String name) throws IllegalArgumentException {
        if (ipRange == null) throw new IllegalArgumentException("\"ipRange\" is required but got null");
            

        if (name == null) throw new IllegalArgumentException("\"name\" is required but got null");
            

        return new CreateNetworkRequestBuilder(ipRange, name);
    }
    private okhttp3.Call deleteNetworkCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/networks/{id}"
            .replace("{" + "id" + "}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
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
    private okhttp3.Call deleteNetworkValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling deleteNetwork(Async)");
        }

        return deleteNetworkCall(id, _callback);

    }


    private ApiResponse<Void> deleteNetworkWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = deleteNetworkValidateBeforeCall(id, null);
        return localVarApiClient.execute(localVarCall);
    }

    private okhttp3.Call deleteNetworkAsync(Long id, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteNetworkValidateBeforeCall(id, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }

    public class DeleteNetworkRequestBuilder {
        private final Long id;

        private DeleteNetworkRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for deleteNetwork
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 204 </td><td> Network deleted </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return deleteNetworkCall(id, _callback);
        }


        /**
         * Execute deleteNetwork request
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 204 </td><td> Network deleted </td><td>  -  </td></tr>
         </table>
         */
        public void execute() throws ApiException {
            deleteNetworkWithHttpInfo(id);
        }

        /**
         * Execute deleteNetwork request with HTTP info returned
         * @return ApiResponse&lt;Void&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 204 </td><td> Network deleted </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<Void> executeWithHttpInfo() throws ApiException {
            return deleteNetworkWithHttpInfo(id);
        }

        /**
         * Execute deleteNetwork request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 204 </td><td> Network deleted </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<Void> _callback) throws ApiException {
            return deleteNetworkAsync(id, _callback);
        }
    }

    /**
     * Delete a Network
     * Deletes a network. If there are Servers attached they will be detached in the background.  Note: if the network object changes during the request, the response will be a “conflict” error. 
     * @param id ID of the network (required)
     * @return DeleteNetworkRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Network deleted </td><td>  -  </td></tr>
     </table>
     */
    public DeleteNetworkRequestBuilder deleteNetwork(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new DeleteNetworkRequestBuilder(id);
    }
    private okhttp3.Call getAllCall(String name, String labelSelector, Long page, Long perPage, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/networks";

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
    private okhttp3.Call getAllValidateBeforeCall(String name, String labelSelector, Long page, Long perPage, final ApiCallback _callback) throws ApiException {
        return getAllCall(name, labelSelector, page, perPage, _callback);

    }


    private ApiResponse<NetworksGetAllResponse> getAllWithHttpInfo(String name, String labelSelector, Long page, Long perPage) throws ApiException {
        okhttp3.Call localVarCall = getAllValidateBeforeCall(name, labelSelector, page, perPage, null);
        Type localVarReturnType = new TypeToken<NetworksGetAllResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getAllAsync(String name, String labelSelector, Long page, Long perPage, final ApiCallback<NetworksGetAllResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAllValidateBeforeCall(name, labelSelector, page, perPage, _callback);
        Type localVarReturnType = new TypeToken<NetworksGetAllResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetAllRequestBuilder {
        private String name;
        private String labelSelector;
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
            <tr><td> 200 </td><td> The &#x60;networks&#x60; key contains a list of networks </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getAllCall(name, labelSelector, page, perPage, _callback);
        }


        /**
         * Execute getAll request
         * @return NetworksGetAllResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;networks&#x60; key contains a list of networks </td><td>  -  </td></tr>
         </table>
         */
        public NetworksGetAllResponse execute() throws ApiException {
            ApiResponse<NetworksGetAllResponse> localVarResp = getAllWithHttpInfo(name, labelSelector, page, perPage);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getAll request with HTTP info returned
         * @return ApiResponse&lt;NetworksGetAllResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;networks&#x60; key contains a list of networks </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<NetworksGetAllResponse> executeWithHttpInfo() throws ApiException {
            return getAllWithHttpInfo(name, labelSelector, page, perPage);
        }

        /**
         * Execute getAll request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;networks&#x60; key contains a list of networks </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<NetworksGetAllResponse> _callback) throws ApiException {
            return getAllAsync(name, labelSelector, page, perPage, _callback);
        }
    }

    /**
     * Get all Networks
     * Gets all existing networks that you have available.
     * @return GetAllRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;networks&#x60; key contains a list of networks </td><td>  -  </td></tr>
     </table>
     */
    public GetAllRequestBuilder getAll() throws IllegalArgumentException {
        return new GetAllRequestBuilder();
    }
    private okhttp3.Call getByIdCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/networks/{id}"
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
    private okhttp3.Call getByIdValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getById(Async)");
        }

        return getByIdCall(id, _callback);

    }


    private ApiResponse<NetworksGetByIdResponse> getByIdWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = getByIdValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<NetworksGetByIdResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getByIdAsync(Long id, final ApiCallback<NetworksGetByIdResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getByIdValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<NetworksGetByIdResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetByIdRequestBuilder {
        private final Long id;

        private GetByIdRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for getById
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;network&#x60; key contains the network </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getByIdCall(id, _callback);
        }


        /**
         * Execute getById request
         * @return NetworksGetByIdResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;network&#x60; key contains the network </td><td>  -  </td></tr>
         </table>
         */
        public NetworksGetByIdResponse execute() throws ApiException {
            ApiResponse<NetworksGetByIdResponse> localVarResp = getByIdWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getById request with HTTP info returned
         * @return ApiResponse&lt;NetworksGetByIdResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;network&#x60; key contains the network </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<NetworksGetByIdResponse> executeWithHttpInfo() throws ApiException {
            return getByIdWithHttpInfo(id);
        }

        /**
         * Execute getById request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;network&#x60; key contains the network </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<NetworksGetByIdResponse> _callback) throws ApiException {
            return getByIdAsync(id, _callback);
        }
    }

    /**
     * Get a Network
     * Gets a specific network object.
     * @param id ID of the network (required)
     * @return GetByIdRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;network&#x60; key contains the network </td><td>  -  </td></tr>
     </table>
     */
    public GetByIdRequestBuilder getById(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new GetByIdRequestBuilder(id);
    }
    private okhttp3.Call updatePropertiesCall(Long id, NetworksUpdatePropertiesRequest networksUpdatePropertiesRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = networksUpdatePropertiesRequest;

        // create path and map variables
        String localVarPath = "/networks/{id}"
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
    private okhttp3.Call updatePropertiesValidateBeforeCall(Long id, NetworksUpdatePropertiesRequest networksUpdatePropertiesRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling updateProperties(Async)");
        }

        return updatePropertiesCall(id, networksUpdatePropertiesRequest, _callback);

    }


    private ApiResponse<NetworksUpdatePropertiesResponse> updatePropertiesWithHttpInfo(Long id, NetworksUpdatePropertiesRequest networksUpdatePropertiesRequest) throws ApiException {
        okhttp3.Call localVarCall = updatePropertiesValidateBeforeCall(id, networksUpdatePropertiesRequest, null);
        Type localVarReturnType = new TypeToken<NetworksUpdatePropertiesResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call updatePropertiesAsync(Long id, NetworksUpdatePropertiesRequest networksUpdatePropertiesRequest, final ApiCallback<NetworksUpdatePropertiesResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = updatePropertiesValidateBeforeCall(id, networksUpdatePropertiesRequest, _callback);
        Type localVarReturnType = new TypeToken<NetworksUpdatePropertiesResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class UpdatePropertiesRequestBuilder {
        private final Long id;
        private Boolean exposeRoutesToVswitch;
        private NetworksUpdatePropertiesRequestLabels labels;
        private String name;

        private UpdatePropertiesRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Set exposeRoutesToVswitch
         * @param exposeRoutesToVswitch Indicates if the routes from this network should be exposed to the vSwitch connection. The exposing only takes effect if a vSwitch connection is active. (optional)
         * @return UpdatePropertiesRequestBuilder
         */
        public UpdatePropertiesRequestBuilder exposeRoutesToVswitch(Boolean exposeRoutesToVswitch) {
            this.exposeRoutesToVswitch = exposeRoutesToVswitch;
            return this;
        }
        
        /**
         * Set labels
         * @param labels  (optional)
         * @return UpdatePropertiesRequestBuilder
         */
        public UpdatePropertiesRequestBuilder labels(NetworksUpdatePropertiesRequestLabels labels) {
            this.labels = labels;
            return this;
        }
        
        /**
         * Set name
         * @param name New network name (optional)
         * @return UpdatePropertiesRequestBuilder
         */
        public UpdatePropertiesRequestBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        /**
         * Build call for updateProperties
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;network&#x60; key contains the updated network </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            NetworksUpdatePropertiesRequest networksUpdatePropertiesRequest = buildBodyParams();
            return updatePropertiesCall(id, networksUpdatePropertiesRequest, _callback);
        }

        private NetworksUpdatePropertiesRequest buildBodyParams() {
            NetworksUpdatePropertiesRequest networksUpdatePropertiesRequest = new NetworksUpdatePropertiesRequest();
            networksUpdatePropertiesRequest.exposeRoutesToVswitch(this.exposeRoutesToVswitch);
            networksUpdatePropertiesRequest.labels(this.labels);
            networksUpdatePropertiesRequest.name(this.name);
            return networksUpdatePropertiesRequest;
        }

        /**
         * Execute updateProperties request
         * @return NetworksUpdatePropertiesResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;network&#x60; key contains the updated network </td><td>  -  </td></tr>
         </table>
         */
        public NetworksUpdatePropertiesResponse execute() throws ApiException {
            NetworksUpdatePropertiesRequest networksUpdatePropertiesRequest = buildBodyParams();
            ApiResponse<NetworksUpdatePropertiesResponse> localVarResp = updatePropertiesWithHttpInfo(id, networksUpdatePropertiesRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute updateProperties request with HTTP info returned
         * @return ApiResponse&lt;NetworksUpdatePropertiesResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;network&#x60; key contains the updated network </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<NetworksUpdatePropertiesResponse> executeWithHttpInfo() throws ApiException {
            NetworksUpdatePropertiesRequest networksUpdatePropertiesRequest = buildBodyParams();
            return updatePropertiesWithHttpInfo(id, networksUpdatePropertiesRequest);
        }

        /**
         * Execute updateProperties request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;network&#x60; key contains the updated network </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<NetworksUpdatePropertiesResponse> _callback) throws ApiException {
            NetworksUpdatePropertiesRequest networksUpdatePropertiesRequest = buildBodyParams();
            return updatePropertiesAsync(id, networksUpdatePropertiesRequest, _callback);
        }
    }

    /**
     * Update a Network
     * Updates the network properties.  Note that when updating labels, the network’s current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body.  Note: if the network object changes during the request, the response will be a “conflict” error. 
     * @param id ID of the network (required)
     * @return UpdatePropertiesRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;network&#x60; key contains the updated network </td><td>  -  </td></tr>
     </table>
     */
    public UpdatePropertiesRequestBuilder updateProperties(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new UpdatePropertiesRequestBuilder(id);
    }
}
