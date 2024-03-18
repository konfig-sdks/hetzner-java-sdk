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


import com.konfigthis.client.model.NetworkActionsAddRouteRequest;
import com.konfigthis.client.model.NetworkActionsAddRouteResponse;
import com.konfigthis.client.model.NetworkActionsAddSubnetRequest;
import com.konfigthis.client.model.NetworkActionsAddSubnetResponse;
import com.konfigthis.client.model.NetworkActionsChangeIpRangeRequest;
import com.konfigthis.client.model.NetworkActionsChangeIpRangeResponse;
import com.konfigthis.client.model.NetworkActionsChangeProtectionRequest;
import com.konfigthis.client.model.NetworkActionsChangeProtectionResponse;
import com.konfigthis.client.model.NetworkActionsDeleteRouteRequest;
import com.konfigthis.client.model.NetworkActionsDeleteRouteResponse;
import com.konfigthis.client.model.NetworkActionsDeleteSubnetRequest;
import com.konfigthis.client.model.NetworkActionsDeleteSubnetResponse;
import com.konfigthis.client.model.NetworkActionsGetAction200Response;
import com.konfigthis.client.model.NetworkActionsGetActionResponse;
import com.konfigthis.client.model.NetworkActionsGetAllActions200Response;
import com.konfigthis.client.model.NetworkActionsGetAllActionsResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

public class NetworkActionsApiGenerated {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public NetworkActionsApiGenerated() throws IllegalArgumentException {
        this(Configuration.getDefaultApiClient());
    }

    public NetworkActionsApiGenerated(ApiClient apiClient) throws IllegalArgumentException {
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

    private okhttp3.Call addRouteCall(Long id, NetworkActionsAddRouteRequest networkActionsAddRouteRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = networkActionsAddRouteRequest;

        // create path and map variables
        String localVarPath = "/networks/{id}/actions/add_route"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call addRouteValidateBeforeCall(Long id, NetworkActionsAddRouteRequest networkActionsAddRouteRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling addRoute(Async)");
        }

        return addRouteCall(id, networkActionsAddRouteRequest, _callback);

    }


    private ApiResponse<NetworkActionsAddRouteResponse> addRouteWithHttpInfo(Long id, NetworkActionsAddRouteRequest networkActionsAddRouteRequest) throws ApiException {
        okhttp3.Call localVarCall = addRouteValidateBeforeCall(id, networkActionsAddRouteRequest, null);
        Type localVarReturnType = new TypeToken<NetworkActionsAddRouteResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call addRouteAsync(Long id, NetworkActionsAddRouteRequest networkActionsAddRouteRequest, final ApiCallback<NetworkActionsAddRouteResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = addRouteValidateBeforeCall(id, networkActionsAddRouteRequest, _callback);
        Type localVarReturnType = new TypeToken<NetworkActionsAddRouteResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class AddRouteRequestBuilder {
        private final String destination;
        private final String gateway;
        private final Long id;

        private AddRouteRequestBuilder(String destination, String gateway, Long id) {
            this.destination = destination;
            this.gateway = gateway;
            this.id = id;
        }

        /**
         * Build call for addRoute
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_route&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            NetworkActionsAddRouteRequest networkActionsAddRouteRequest = buildBodyParams();
            return addRouteCall(id, networkActionsAddRouteRequest, _callback);
        }

        private NetworkActionsAddRouteRequest buildBodyParams() {
            NetworkActionsAddRouteRequest networkActionsAddRouteRequest = new NetworkActionsAddRouteRequest();
            networkActionsAddRouteRequest.destination(this.destination);
            networkActionsAddRouteRequest.gateway(this.gateway);
            return networkActionsAddRouteRequest;
        }

        /**
         * Execute addRoute request
         * @return NetworkActionsAddRouteResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_route&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public NetworkActionsAddRouteResponse execute() throws ApiException {
            NetworkActionsAddRouteRequest networkActionsAddRouteRequest = buildBodyParams();
            ApiResponse<NetworkActionsAddRouteResponse> localVarResp = addRouteWithHttpInfo(id, networkActionsAddRouteRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute addRoute request with HTTP info returned
         * @return ApiResponse&lt;NetworkActionsAddRouteResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_route&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<NetworkActionsAddRouteResponse> executeWithHttpInfo() throws ApiException {
            NetworkActionsAddRouteRequest networkActionsAddRouteRequest = buildBodyParams();
            return addRouteWithHttpInfo(id, networkActionsAddRouteRequest);
        }

        /**
         * Execute addRoute request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_route&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<NetworkActionsAddRouteResponse> _callback) throws ApiException {
            NetworkActionsAddRouteRequest networkActionsAddRouteRequest = buildBodyParams();
            return addRouteAsync(id, networkActionsAddRouteRequest, _callback);
        }
    }

    /**
     * Add a route to a Network
     * Adds a route entry to a Network.  Note: if the Network object changes during the request, the response will be a “conflict” error. 
     * @param id ID of the Network (required)
     * @return AddRouteRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_route&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public AddRouteRequestBuilder addRoute(String destination, String gateway, Long id) throws IllegalArgumentException {
        if (destination == null) throw new IllegalArgumentException("\"destination\" is required but got null");
            

        if (gateway == null) throw new IllegalArgumentException("\"gateway\" is required but got null");
            

        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new AddRouteRequestBuilder(destination, gateway, id);
    }
    private okhttp3.Call addSubnetCall(Long id, NetworkActionsAddSubnetRequest networkActionsAddSubnetRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = networkActionsAddSubnetRequest;

        // create path and map variables
        String localVarPath = "/networks/{id}/actions/add_subnet"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call addSubnetValidateBeforeCall(Long id, NetworkActionsAddSubnetRequest networkActionsAddSubnetRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling addSubnet(Async)");
        }

        return addSubnetCall(id, networkActionsAddSubnetRequest, _callback);

    }


    private ApiResponse<NetworkActionsAddSubnetResponse> addSubnetWithHttpInfo(Long id, NetworkActionsAddSubnetRequest networkActionsAddSubnetRequest) throws ApiException {
        okhttp3.Call localVarCall = addSubnetValidateBeforeCall(id, networkActionsAddSubnetRequest, null);
        Type localVarReturnType = new TypeToken<NetworkActionsAddSubnetResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call addSubnetAsync(Long id, NetworkActionsAddSubnetRequest networkActionsAddSubnetRequest, final ApiCallback<NetworkActionsAddSubnetResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = addSubnetValidateBeforeCall(id, networkActionsAddSubnetRequest, _callback);
        Type localVarReturnType = new TypeToken<NetworkActionsAddSubnetResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class AddSubnetRequestBuilder {
        private final String networkZone;
        private final String type;
        private final Long id;
        private String ipRange;
        private Long vswitchId;

        private AddSubnetRequestBuilder(String networkZone, String type, Long id) {
            this.networkZone = networkZone;
            this.type = type;
            this.id = id;
        }

        /**
         * Set ipRange
         * @param ipRange Range to allocate IPs from. Must be a Subnet of the ip_range of the parent network object and must not overlap with any other subnets or with any destinations in routes. If the Subnet is of type vSwitch, it also can not overlap with any gateway in routes. Minimum Network size is /30. We suggest that you pick a bigger Network with a /24 netmask. (optional)
         * @return AddSubnetRequestBuilder
         */
        public AddSubnetRequestBuilder ipRange(String ipRange) {
            this.ipRange = ipRange;
            return this;
        }
        
        /**
         * Set vswitchId
         * @param vswitchId ID of the robot vSwitch. Must be supplied if the subnet is of type vswitch. (optional)
         * @return AddSubnetRequestBuilder
         */
        public AddSubnetRequestBuilder vswitchId(Long vswitchId) {
            this.vswitchId = vswitchId;
            return this;
        }
        
        /**
         * Build call for addSubnet
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_subnet&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            NetworkActionsAddSubnetRequest networkActionsAddSubnetRequest = buildBodyParams();
            return addSubnetCall(id, networkActionsAddSubnetRequest, _callback);
        }

        private NetworkActionsAddSubnetRequest buildBodyParams() {
            NetworkActionsAddSubnetRequest networkActionsAddSubnetRequest = new NetworkActionsAddSubnetRequest();
            networkActionsAddSubnetRequest.ipRange(this.ipRange);
            networkActionsAddSubnetRequest.networkZone(this.networkZone);
            if (this.type != null)
            networkActionsAddSubnetRequest.type(NetworkActionsAddSubnetRequest.TypeEnum.fromValue(this.type));
            networkActionsAddSubnetRequest.vswitchId(this.vswitchId);
            return networkActionsAddSubnetRequest;
        }

        /**
         * Execute addSubnet request
         * @return NetworkActionsAddSubnetResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_subnet&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public NetworkActionsAddSubnetResponse execute() throws ApiException {
            NetworkActionsAddSubnetRequest networkActionsAddSubnetRequest = buildBodyParams();
            ApiResponse<NetworkActionsAddSubnetResponse> localVarResp = addSubnetWithHttpInfo(id, networkActionsAddSubnetRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute addSubnet request with HTTP info returned
         * @return ApiResponse&lt;NetworkActionsAddSubnetResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_subnet&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<NetworkActionsAddSubnetResponse> executeWithHttpInfo() throws ApiException {
            NetworkActionsAddSubnetRequest networkActionsAddSubnetRequest = buildBodyParams();
            return addSubnetWithHttpInfo(id, networkActionsAddSubnetRequest);
        }

        /**
         * Execute addSubnet request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_subnet&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<NetworkActionsAddSubnetResponse> _callback) throws ApiException {
            NetworkActionsAddSubnetRequest networkActionsAddSubnetRequest = buildBodyParams();
            return addSubnetAsync(id, networkActionsAddSubnetRequest, _callback);
        }
    }

    /**
     * Add a subnet to a Network
     * Adds a new subnet object to the Network. If you do not specify an &#x60;ip_range&#x60; for the subnet we will automatically pick the first available /24 range for you if possible.  Note: if the parent Network object changes during the request, the response will be a “conflict” error. 
     * @param id ID of the Network (required)
     * @return AddSubnetRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_subnet&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public AddSubnetRequestBuilder addSubnet(String networkZone, String type, Long id) throws IllegalArgumentException {
        if (networkZone == null) throw new IllegalArgumentException("\"networkZone\" is required but got null");
            

        if (type == null) throw new IllegalArgumentException("\"type\" is required but got null");
            

        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new AddSubnetRequestBuilder(networkZone, type, id);
    }
    private okhttp3.Call changeIpRangeCall(Long id, NetworkActionsChangeIpRangeRequest networkActionsChangeIpRangeRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = networkActionsChangeIpRangeRequest;

        // create path and map variables
        String localVarPath = "/networks/{id}/actions/change_ip_range"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call changeIpRangeValidateBeforeCall(Long id, NetworkActionsChangeIpRangeRequest networkActionsChangeIpRangeRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling changeIpRange(Async)");
        }

        return changeIpRangeCall(id, networkActionsChangeIpRangeRequest, _callback);

    }


    private ApiResponse<NetworkActionsChangeIpRangeResponse> changeIpRangeWithHttpInfo(Long id, NetworkActionsChangeIpRangeRequest networkActionsChangeIpRangeRequest) throws ApiException {
        okhttp3.Call localVarCall = changeIpRangeValidateBeforeCall(id, networkActionsChangeIpRangeRequest, null);
        Type localVarReturnType = new TypeToken<NetworkActionsChangeIpRangeResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call changeIpRangeAsync(Long id, NetworkActionsChangeIpRangeRequest networkActionsChangeIpRangeRequest, final ApiCallback<NetworkActionsChangeIpRangeResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = changeIpRangeValidateBeforeCall(id, networkActionsChangeIpRangeRequest, _callback);
        Type localVarReturnType = new TypeToken<NetworkActionsChangeIpRangeResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class ChangeIpRangeRequestBuilder {
        private final String ipRange;
        private final Long id;

        private ChangeIpRangeRequestBuilder(String ipRange, Long id) {
            this.ipRange = ipRange;
            this.id = id;
        }

        /**
         * Build call for changeIpRange
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_ip_range&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            NetworkActionsChangeIpRangeRequest networkActionsChangeIpRangeRequest = buildBodyParams();
            return changeIpRangeCall(id, networkActionsChangeIpRangeRequest, _callback);
        }

        private NetworkActionsChangeIpRangeRequest buildBodyParams() {
            NetworkActionsChangeIpRangeRequest networkActionsChangeIpRangeRequest = new NetworkActionsChangeIpRangeRequest();
            networkActionsChangeIpRangeRequest.ipRange(this.ipRange);
            return networkActionsChangeIpRangeRequest;
        }

        /**
         * Execute changeIpRange request
         * @return NetworkActionsChangeIpRangeResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_ip_range&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public NetworkActionsChangeIpRangeResponse execute() throws ApiException {
            NetworkActionsChangeIpRangeRequest networkActionsChangeIpRangeRequest = buildBodyParams();
            ApiResponse<NetworkActionsChangeIpRangeResponse> localVarResp = changeIpRangeWithHttpInfo(id, networkActionsChangeIpRangeRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute changeIpRange request with HTTP info returned
         * @return ApiResponse&lt;NetworkActionsChangeIpRangeResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_ip_range&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<NetworkActionsChangeIpRangeResponse> executeWithHttpInfo() throws ApiException {
            NetworkActionsChangeIpRangeRequest networkActionsChangeIpRangeRequest = buildBodyParams();
            return changeIpRangeWithHttpInfo(id, networkActionsChangeIpRangeRequest);
        }

        /**
         * Execute changeIpRange request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_ip_range&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<NetworkActionsChangeIpRangeResponse> _callback) throws ApiException {
            NetworkActionsChangeIpRangeRequest networkActionsChangeIpRangeRequest = buildBodyParams();
            return changeIpRangeAsync(id, networkActionsChangeIpRangeRequest, _callback);
        }
    }

    /**
     * Change IP range of a Network
     * Changes the IP range of a Network. IP ranges can only be extended and never shrunk. You can only add IPs at the end of an existing IP range. This means that the IP part of your existing range must stay the same and you can only change its netmask.  For example if you have a range &#x60;10.0.0.0/16&#x60; you want to extend then your new range must also start with the IP &#x60;10.0.0.0&#x60;. Your CIDR netmask &#x60;/16&#x60; may change to a number that is smaller than &#x60;16&#x60; thereby increasing the IP range. So valid entries would be &#x60;10.0.0.0/15&#x60;, &#x60;10.0.0.0/14&#x60;, &#x60;10.0.0.0/13&#x60; and so on.  After changing the IP range you will have to adjust the routes on your connected Servers by either rebooting them or manually changing the routes to your private Network interface.  Note: if the Network object changes during the request, the response will be a “conflict” error. 
     * @param id ID of the Network (required)
     * @return ChangeIpRangeRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_ip_range&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public ChangeIpRangeRequestBuilder changeIpRange(String ipRange, Long id) throws IllegalArgumentException {
        if (ipRange == null) throw new IllegalArgumentException("\"ipRange\" is required but got null");
            

        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new ChangeIpRangeRequestBuilder(ipRange, id);
    }
    private okhttp3.Call changeProtectionCall(Long id, NetworkActionsChangeProtectionRequest networkActionsChangeProtectionRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = networkActionsChangeProtectionRequest;

        // create path and map variables
        String localVarPath = "/networks/{id}/actions/change_protection"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call changeProtectionValidateBeforeCall(Long id, NetworkActionsChangeProtectionRequest networkActionsChangeProtectionRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling changeProtection(Async)");
        }

        return changeProtectionCall(id, networkActionsChangeProtectionRequest, _callback);

    }


    private ApiResponse<NetworkActionsChangeProtectionResponse> changeProtectionWithHttpInfo(Long id, NetworkActionsChangeProtectionRequest networkActionsChangeProtectionRequest) throws ApiException {
        okhttp3.Call localVarCall = changeProtectionValidateBeforeCall(id, networkActionsChangeProtectionRequest, null);
        Type localVarReturnType = new TypeToken<NetworkActionsChangeProtectionResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call changeProtectionAsync(Long id, NetworkActionsChangeProtectionRequest networkActionsChangeProtectionRequest, final ApiCallback<NetworkActionsChangeProtectionResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = changeProtectionValidateBeforeCall(id, networkActionsChangeProtectionRequest, _callback);
        Type localVarReturnType = new TypeToken<NetworkActionsChangeProtectionResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class ChangeProtectionRequestBuilder {
        private final Long id;
        private Boolean delete;

        private ChangeProtectionRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Set delete
         * @param delete If true, prevents the Network from being deleted (optional)
         * @return ChangeProtectionRequestBuilder
         */
        public ChangeProtectionRequestBuilder delete(Boolean delete) {
            this.delete = delete;
            return this;
        }
        
        /**
         * Build call for changeProtection
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_protection&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            NetworkActionsChangeProtectionRequest networkActionsChangeProtectionRequest = buildBodyParams();
            return changeProtectionCall(id, networkActionsChangeProtectionRequest, _callback);
        }

        private NetworkActionsChangeProtectionRequest buildBodyParams() {
            NetworkActionsChangeProtectionRequest networkActionsChangeProtectionRequest = new NetworkActionsChangeProtectionRequest();
            networkActionsChangeProtectionRequest.delete(this.delete);
            return networkActionsChangeProtectionRequest;
        }

        /**
         * Execute changeProtection request
         * @return NetworkActionsChangeProtectionResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_protection&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public NetworkActionsChangeProtectionResponse execute() throws ApiException {
            NetworkActionsChangeProtectionRequest networkActionsChangeProtectionRequest = buildBodyParams();
            ApiResponse<NetworkActionsChangeProtectionResponse> localVarResp = changeProtectionWithHttpInfo(id, networkActionsChangeProtectionRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute changeProtection request with HTTP info returned
         * @return ApiResponse&lt;NetworkActionsChangeProtectionResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_protection&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<NetworkActionsChangeProtectionResponse> executeWithHttpInfo() throws ApiException {
            NetworkActionsChangeProtectionRequest networkActionsChangeProtectionRequest = buildBodyParams();
            return changeProtectionWithHttpInfo(id, networkActionsChangeProtectionRequest);
        }

        /**
         * Execute changeProtection request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_protection&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<NetworkActionsChangeProtectionResponse> _callback) throws ApiException {
            NetworkActionsChangeProtectionRequest networkActionsChangeProtectionRequest = buildBodyParams();
            return changeProtectionAsync(id, networkActionsChangeProtectionRequest, _callback);
        }
    }

    /**
     * Change Network Protection
     * Changes the protection configuration of a Network.  Note: if the Network object changes during the request, the response will be a “conflict” error. 
     * @param id ID of the Network (required)
     * @return ChangeProtectionRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_protection&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public ChangeProtectionRequestBuilder changeProtection(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new ChangeProtectionRequestBuilder(id);
    }
    private okhttp3.Call deleteRouteCall(Long id, NetworkActionsDeleteRouteRequest networkActionsDeleteRouteRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = networkActionsDeleteRouteRequest;

        // create path and map variables
        String localVarPath = "/networks/{id}/actions/delete_route"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteRouteValidateBeforeCall(Long id, NetworkActionsDeleteRouteRequest networkActionsDeleteRouteRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling deleteRoute(Async)");
        }

        return deleteRouteCall(id, networkActionsDeleteRouteRequest, _callback);

    }


    private ApiResponse<NetworkActionsDeleteRouteResponse> deleteRouteWithHttpInfo(Long id, NetworkActionsDeleteRouteRequest networkActionsDeleteRouteRequest) throws ApiException {
        okhttp3.Call localVarCall = deleteRouteValidateBeforeCall(id, networkActionsDeleteRouteRequest, null);
        Type localVarReturnType = new TypeToken<NetworkActionsDeleteRouteResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call deleteRouteAsync(Long id, NetworkActionsDeleteRouteRequest networkActionsDeleteRouteRequest, final ApiCallback<NetworkActionsDeleteRouteResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteRouteValidateBeforeCall(id, networkActionsDeleteRouteRequest, _callback);
        Type localVarReturnType = new TypeToken<NetworkActionsDeleteRouteResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class DeleteRouteRequestBuilder {
        private final String destination;
        private final String gateway;
        private final Long id;

        private DeleteRouteRequestBuilder(String destination, String gateway, Long id) {
            this.destination = destination;
            this.gateway = gateway;
            this.id = id;
        }

        /**
         * Build call for deleteRoute
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_route&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            NetworkActionsDeleteRouteRequest networkActionsDeleteRouteRequest = buildBodyParams();
            return deleteRouteCall(id, networkActionsDeleteRouteRequest, _callback);
        }

        private NetworkActionsDeleteRouteRequest buildBodyParams() {
            NetworkActionsDeleteRouteRequest networkActionsDeleteRouteRequest = new NetworkActionsDeleteRouteRequest();
            networkActionsDeleteRouteRequest.destination(this.destination);
            networkActionsDeleteRouteRequest.gateway(this.gateway);
            return networkActionsDeleteRouteRequest;
        }

        /**
         * Execute deleteRoute request
         * @return NetworkActionsDeleteRouteResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_route&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public NetworkActionsDeleteRouteResponse execute() throws ApiException {
            NetworkActionsDeleteRouteRequest networkActionsDeleteRouteRequest = buildBodyParams();
            ApiResponse<NetworkActionsDeleteRouteResponse> localVarResp = deleteRouteWithHttpInfo(id, networkActionsDeleteRouteRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute deleteRoute request with HTTP info returned
         * @return ApiResponse&lt;NetworkActionsDeleteRouteResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_route&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<NetworkActionsDeleteRouteResponse> executeWithHttpInfo() throws ApiException {
            NetworkActionsDeleteRouteRequest networkActionsDeleteRouteRequest = buildBodyParams();
            return deleteRouteWithHttpInfo(id, networkActionsDeleteRouteRequest);
        }

        /**
         * Execute deleteRoute request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_route&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<NetworkActionsDeleteRouteResponse> _callback) throws ApiException {
            NetworkActionsDeleteRouteRequest networkActionsDeleteRouteRequest = buildBodyParams();
            return deleteRouteAsync(id, networkActionsDeleteRouteRequest, _callback);
        }
    }

    /**
     * Delete a route from a Network
     * Delete a route entry from a Network.  Note: if the Network object changes during the request, the response will be a “conflict” error. 
     * @param id ID of the Network (required)
     * @return DeleteRouteRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_route&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public DeleteRouteRequestBuilder deleteRoute(String destination, String gateway, Long id) throws IllegalArgumentException {
        if (destination == null) throw new IllegalArgumentException("\"destination\" is required but got null");
            

        if (gateway == null) throw new IllegalArgumentException("\"gateway\" is required but got null");
            

        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new DeleteRouteRequestBuilder(destination, gateway, id);
    }
    private okhttp3.Call deleteSubnetCall(Long id, NetworkActionsDeleteSubnetRequest networkActionsDeleteSubnetRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = networkActionsDeleteSubnetRequest;

        // create path and map variables
        String localVarPath = "/networks/{id}/actions/delete_subnet"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteSubnetValidateBeforeCall(Long id, NetworkActionsDeleteSubnetRequest networkActionsDeleteSubnetRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling deleteSubnet(Async)");
        }

        return deleteSubnetCall(id, networkActionsDeleteSubnetRequest, _callback);

    }


    private ApiResponse<NetworkActionsDeleteSubnetResponse> deleteSubnetWithHttpInfo(Long id, NetworkActionsDeleteSubnetRequest networkActionsDeleteSubnetRequest) throws ApiException {
        okhttp3.Call localVarCall = deleteSubnetValidateBeforeCall(id, networkActionsDeleteSubnetRequest, null);
        Type localVarReturnType = new TypeToken<NetworkActionsDeleteSubnetResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call deleteSubnetAsync(Long id, NetworkActionsDeleteSubnetRequest networkActionsDeleteSubnetRequest, final ApiCallback<NetworkActionsDeleteSubnetResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteSubnetValidateBeforeCall(id, networkActionsDeleteSubnetRequest, _callback);
        Type localVarReturnType = new TypeToken<NetworkActionsDeleteSubnetResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class DeleteSubnetRequestBuilder {
        private final String ipRange;
        private final Long id;

        private DeleteSubnetRequestBuilder(String ipRange, Long id) {
            this.ipRange = ipRange;
            this.id = id;
        }

        /**
         * Build call for deleteSubnet
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_subnet&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            NetworkActionsDeleteSubnetRequest networkActionsDeleteSubnetRequest = buildBodyParams();
            return deleteSubnetCall(id, networkActionsDeleteSubnetRequest, _callback);
        }

        private NetworkActionsDeleteSubnetRequest buildBodyParams() {
            NetworkActionsDeleteSubnetRequest networkActionsDeleteSubnetRequest = new NetworkActionsDeleteSubnetRequest();
            networkActionsDeleteSubnetRequest.ipRange(this.ipRange);
            return networkActionsDeleteSubnetRequest;
        }

        /**
         * Execute deleteSubnet request
         * @return NetworkActionsDeleteSubnetResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_subnet&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public NetworkActionsDeleteSubnetResponse execute() throws ApiException {
            NetworkActionsDeleteSubnetRequest networkActionsDeleteSubnetRequest = buildBodyParams();
            ApiResponse<NetworkActionsDeleteSubnetResponse> localVarResp = deleteSubnetWithHttpInfo(id, networkActionsDeleteSubnetRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute deleteSubnet request with HTTP info returned
         * @return ApiResponse&lt;NetworkActionsDeleteSubnetResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_subnet&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<NetworkActionsDeleteSubnetResponse> executeWithHttpInfo() throws ApiException {
            NetworkActionsDeleteSubnetRequest networkActionsDeleteSubnetRequest = buildBodyParams();
            return deleteSubnetWithHttpInfo(id, networkActionsDeleteSubnetRequest);
        }

        /**
         * Execute deleteSubnet request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_subnet&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<NetworkActionsDeleteSubnetResponse> _callback) throws ApiException {
            NetworkActionsDeleteSubnetRequest networkActionsDeleteSubnetRequest = buildBodyParams();
            return deleteSubnetAsync(id, networkActionsDeleteSubnetRequest, _callback);
        }
    }

    /**
     * Delete a subnet from a Network
     * Deletes a single subnet entry from a Network. You cannot delete subnets which still have Servers attached. If you have Servers attached you first need to detach all Servers that use IPs from this subnet before you can delete the subnet.  Note: if the Network object changes during the request, the response will be a “conflict” error. 
     * @param id ID of the Network (required)
     * @return DeleteSubnetRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_subnet&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public DeleteSubnetRequestBuilder deleteSubnet(String ipRange, Long id) throws IllegalArgumentException {
        if (ipRange == null) throw new IllegalArgumentException("\"ipRange\" is required but got null");
            

        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new DeleteSubnetRequestBuilder(ipRange, id);
    }
    private okhttp3.Call getActionCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/networks/actions/{id}"
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
    private okhttp3.Call getActionValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getAction(Async)");
        }

        return getActionCall(id, _callback);

    }


    private ApiResponse<NetworkActionsGetActionResponse> getActionWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = getActionValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<NetworkActionsGetActionResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getActionAsync(Long id, final ApiCallback<NetworkActionsGetActionResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getActionValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<NetworkActionsGetActionResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetActionRequestBuilder {
        private final Long id;

        private GetActionRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for getAction
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getActionCall(id, _callback);
        }


        /**
         * Execute getAction request
         * @return NetworkActionsGetActionResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public NetworkActionsGetActionResponse execute() throws ApiException {
            ApiResponse<NetworkActionsGetActionResponse> localVarResp = getActionWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getAction request with HTTP info returned
         * @return ApiResponse&lt;NetworkActionsGetActionResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<NetworkActionsGetActionResponse> executeWithHttpInfo() throws ApiException {
            return getActionWithHttpInfo(id);
        }

        /**
         * Execute getAction request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<NetworkActionsGetActionResponse> _callback) throws ApiException {
            return getActionAsync(id, _callback);
        }
    }

    /**
     * Get an Action
     * Returns a specific Action object.
     * @param id ID of the Action. (required)
     * @return GetActionRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
     </table>
     */
    public GetActionRequestBuilder getAction(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new GetActionRequestBuilder(id);
    }
    private okhttp3.Call getAction_0Call(Long id, Long actionId, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/networks/{id}/actions/{action_id}"
            .replace("{" + "id" + "}", localVarApiClient.escapeString(id.toString()))
            .replace("{" + "action_id" + "}", localVarApiClient.escapeString(actionId.toString()));

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
    private okhttp3.Call getAction_0ValidateBeforeCall(Long id, Long actionId, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getAction_0(Async)");
        }

        // verify the required parameter 'actionId' is set
        if (actionId == null) {
            throw new ApiException("Missing the required parameter 'actionId' when calling getAction_0(Async)");
        }

        return getAction_0Call(id, actionId, _callback);

    }


    private ApiResponse<NetworkActionsGetAction200Response> getAction_0WithHttpInfo(Long id, Long actionId) throws ApiException {
        okhttp3.Call localVarCall = getAction_0ValidateBeforeCall(id, actionId, null);
        Type localVarReturnType = new TypeToken<NetworkActionsGetAction200Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getAction_0Async(Long id, Long actionId, final ApiCallback<NetworkActionsGetAction200Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAction_0ValidateBeforeCall(id, actionId, _callback);
        Type localVarReturnType = new TypeToken<NetworkActionsGetAction200Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetAction0RequestBuilder {
        private final Long id;
        private final Long actionId;

        private GetAction0RequestBuilder(Long id, Long actionId) {
            this.id = id;
            this.actionId = actionId;
        }

        /**
         * Build call for getAction_0
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key contains the Network Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getAction_0Call(id, actionId, _callback);
        }


        /**
         * Execute getAction_0 request
         * @return NetworkActionsGetAction200Response
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key contains the Network Action </td><td>  -  </td></tr>
         </table>
         */
        public NetworkActionsGetAction200Response execute() throws ApiException {
            ApiResponse<NetworkActionsGetAction200Response> localVarResp = getAction_0WithHttpInfo(id, actionId);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getAction_0 request with HTTP info returned
         * @return ApiResponse&lt;NetworkActionsGetAction200Response&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key contains the Network Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<NetworkActionsGetAction200Response> executeWithHttpInfo() throws ApiException {
            return getAction_0WithHttpInfo(id, actionId);
        }

        /**
         * Execute getAction_0 request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key contains the Network Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<NetworkActionsGetAction200Response> _callback) throws ApiException {
            return getAction_0Async(id, actionId, _callback);
        }
    }

    /**
     * Get an Action for a Network
     * Returns a specific Action for a Network.
     * @param id ID of the Network (required)
     * @param actionId ID of the Action (required)
     * @return GetAction0RequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;action&#x60; key contains the Network Action </td><td>  -  </td></tr>
     </table>
     */
    public GetAction0RequestBuilder getAction_0(Long id, Long actionId) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        if (actionId == null) throw new IllegalArgumentException("\"actionId\" is required but got null");
        return new GetAction0RequestBuilder(id, actionId);
    }
    private okhttp3.Call getAllActionsCall(Long id, String sort, String status, Long page, Long perPage, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/networks/actions";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (id != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("id", id));
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
    private okhttp3.Call getAllActionsValidateBeforeCall(Long id, String sort, String status, Long page, Long perPage, final ApiCallback _callback) throws ApiException {
        return getAllActionsCall(id, sort, status, page, perPage, _callback);

    }


    private ApiResponse<NetworkActionsGetAllActionsResponse> getAllActionsWithHttpInfo(Long id, String sort, String status, Long page, Long perPage) throws ApiException {
        okhttp3.Call localVarCall = getAllActionsValidateBeforeCall(id, sort, status, page, perPage, null);
        Type localVarReturnType = new TypeToken<NetworkActionsGetAllActionsResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getAllActionsAsync(Long id, String sort, String status, Long page, Long perPage, final ApiCallback<NetworkActionsGetAllActionsResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAllActionsValidateBeforeCall(id, sort, status, page, perPage, _callback);
        Type localVarReturnType = new TypeToken<NetworkActionsGetAllActionsResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetAllActionsRequestBuilder {
        private Long id;
        private String sort;
        private String status;
        private Long page;
        private Long perPage;

        private GetAllActionsRequestBuilder() {
        }

        /**
         * Set id
         * @param id Filter the actions by ID. Can be used multiple times. The response will only contain actions matching the specified IDs.  (optional)
         * @return GetAllActionsRequestBuilder
         */
        public GetAllActionsRequestBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        /**
         * Set sort
         * @param sort Sort actions by field and direction. Can be used multiple times. For more information, see \&quot;[Sorting](https://docs.hetzner.cloud)\&quot;.  (optional)
         * @return GetAllActionsRequestBuilder
         */
        public GetAllActionsRequestBuilder sort(String sort) {
            this.sort = sort;
            return this;
        }
        
        /**
         * Set status
         * @param status Filter the actions by status. Can be used multiple times. The response will only contain actions matching the specified statuses.  (optional)
         * @return GetAllActionsRequestBuilder
         */
        public GetAllActionsRequestBuilder status(String status) {
            this.status = status;
            return this;
        }
        
        /**
         * Set page
         * @param page Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. (optional, default to 1)
         * @return GetAllActionsRequestBuilder
         */
        public GetAllActionsRequestBuilder page(Long page) {
            this.page = page;
            return this;
        }
        
        /**
         * Set perPage
         * @param perPage Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. (optional, default to 25)
         * @return GetAllActionsRequestBuilder
         */
        public GetAllActionsRequestBuilder perPage(Long perPage) {
            this.perPage = perPage;
            return this;
        }
        
        /**
         * Build call for getAllActions
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getAllActionsCall(id, sort, status, page, perPage, _callback);
        }


        /**
         * Execute getAllActions request
         * @return NetworkActionsGetAllActionsResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public NetworkActionsGetAllActionsResponse execute() throws ApiException {
            ApiResponse<NetworkActionsGetAllActionsResponse> localVarResp = getAllActionsWithHttpInfo(id, sort, status, page, perPage);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getAllActions request with HTTP info returned
         * @return ApiResponse&lt;NetworkActionsGetAllActionsResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<NetworkActionsGetAllActionsResponse> executeWithHttpInfo() throws ApiException {
            return getAllActionsWithHttpInfo(id, sort, status, page, perPage);
        }

        /**
         * Execute getAllActions request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<NetworkActionsGetAllActionsResponse> _callback) throws ApiException {
            return getAllActionsAsync(id, sort, status, page, perPage, _callback);
        }
    }

    /**
     * Get all Actions
     * Returns all Action objects. You can &#x60;sort&#x60; the results by using the sort URI parameter, and filter them with the &#x60;status&#x60; and &#x60;id&#x60; parameter.
     * @return GetAllActionsRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
     </table>
     */
    public GetAllActionsRequestBuilder getAllActions() throws IllegalArgumentException {
        return new GetAllActionsRequestBuilder();
    }
    private okhttp3.Call getAllActions_0Call(Long id, String sort, String status, Long page, Long perPage, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/networks/{id}/actions"
            .replace("{" + "id" + "}", localVarApiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

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
    private okhttp3.Call getAllActions_0ValidateBeforeCall(Long id, String sort, String status, Long page, Long perPage, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getAllActions_0(Async)");
        }

        return getAllActions_0Call(id, sort, status, page, perPage, _callback);

    }


    private ApiResponse<NetworkActionsGetAllActions200Response> getAllActions_0WithHttpInfo(Long id, String sort, String status, Long page, Long perPage) throws ApiException {
        okhttp3.Call localVarCall = getAllActions_0ValidateBeforeCall(id, sort, status, page, perPage, null);
        Type localVarReturnType = new TypeToken<NetworkActionsGetAllActions200Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getAllActions_0Async(Long id, String sort, String status, Long page, Long perPage, final ApiCallback<NetworkActionsGetAllActions200Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAllActions_0ValidateBeforeCall(id, sort, status, page, perPage, _callback);
        Type localVarReturnType = new TypeToken<NetworkActionsGetAllActions200Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetAllActions0RequestBuilder {
        private final Long id;
        private String sort;
        private String status;
        private Long page;
        private Long perPage;

        private GetAllActions0RequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Set sort
         * @param sort Sort actions by field and direction. Can be used multiple times. For more information, see \&quot;[Sorting](https://docs.hetzner.cloud)\&quot;.  (optional)
         * @return GetAllActions0RequestBuilder
         */
        public GetAllActions0RequestBuilder sort(String sort) {
            this.sort = sort;
            return this;
        }
        
        /**
         * Set status
         * @param status Filter the actions by status. Can be used multiple times. The response will only contain actions matching the specified statuses.  (optional)
         * @return GetAllActions0RequestBuilder
         */
        public GetAllActions0RequestBuilder status(String status) {
            this.status = status;
            return this;
        }
        
        /**
         * Set page
         * @param page Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. (optional, default to 1)
         * @return GetAllActions0RequestBuilder
         */
        public GetAllActions0RequestBuilder page(Long page) {
            this.page = page;
            return this;
        }
        
        /**
         * Set perPage
         * @param perPage Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. (optional, default to 25)
         * @return GetAllActions0RequestBuilder
         */
        public GetAllActions0RequestBuilder perPage(Long perPage) {
            this.perPage = perPage;
            return this;
        }
        
        /**
         * Build call for getAllActions_0
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getAllActions_0Call(id, sort, status, page, perPage, _callback);
        }


        /**
         * Execute getAllActions_0 request
         * @return NetworkActionsGetAllActions200Response
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public NetworkActionsGetAllActions200Response execute() throws ApiException {
            ApiResponse<NetworkActionsGetAllActions200Response> localVarResp = getAllActions_0WithHttpInfo(id, sort, status, page, perPage);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getAllActions_0 request with HTTP info returned
         * @return ApiResponse&lt;NetworkActionsGetAllActions200Response&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<NetworkActionsGetAllActions200Response> executeWithHttpInfo() throws ApiException {
            return getAllActions_0WithHttpInfo(id, sort, status, page, perPage);
        }

        /**
         * Execute getAllActions_0 request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<NetworkActionsGetAllActions200Response> _callback) throws ApiException {
            return getAllActions_0Async(id, sort, status, page, perPage, _callback);
        }
    }

    /**
     * Get all Actions for a Network
     * Returns all Action objects for a Network. You can sort the results by using the &#x60;sort&#x60; URI parameter, and filter them with the &#x60;status&#x60; parameter.
     * @param id ID of the Network (required)
     * @return GetAllActions0RequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
     </table>
     */
    public GetAllActions0RequestBuilder getAllActions_0(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new GetAllActions0RequestBuilder(id);
    }
}
