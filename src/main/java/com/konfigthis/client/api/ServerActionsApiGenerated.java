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


import com.konfigthis.client.model.ServerActionsAddToPlacementGroupRequest;
import com.konfigthis.client.model.ServerActionsAddToPlacementGroupResponse;
import com.konfigthis.client.model.ServerActionsAttachIsoToServerRequest;
import com.konfigthis.client.model.ServerActionsAttachIsoToServerResponse;
import com.konfigthis.client.model.ServerActionsAttachToNetworkRequest;
import com.konfigthis.client.model.ServerActionsAttachToNetworkResponse;
import com.konfigthis.client.model.ServerActionsChangeAliasIpsRequest;
import com.konfigthis.client.model.ServerActionsChangeAliasIpsResponse;
import com.konfigthis.client.model.ServerActionsChangeDnsPtrRequest;
import com.konfigthis.client.model.ServerActionsChangeDnsPtrResponse;
import com.konfigthis.client.model.ServerActionsChangeProtectionRequest;
import com.konfigthis.client.model.ServerActionsChangeProtectionResponse;
import com.konfigthis.client.model.ServerActionsChangeServerTypeRequest;
import com.konfigthis.client.model.ServerActionsChangeServerTypeResponse;
import com.konfigthis.client.model.ServerActionsCreateImageRequest;
import com.konfigthis.client.model.ServerActionsCreateImageRequestLabels;
import com.konfigthis.client.model.ServerActionsCreateImageResponse;
import com.konfigthis.client.model.ServerActionsDetachFromNetworkRequest;
import com.konfigthis.client.model.ServerActionsDetachFromNetworkResponse;
import com.konfigthis.client.model.ServerActionsDetachIsoFromServerResponse;
import com.konfigthis.client.model.ServerActionsDisableBackupResponse;
import com.konfigthis.client.model.ServerActionsDisableRescueModeResponse;
import com.konfigthis.client.model.ServerActionsEnableBackupResponse;
import com.konfigthis.client.model.ServerActionsEnableRescueModeRequest;
import com.konfigthis.client.model.ServerActionsEnableRescueModeResponse;
import com.konfigthis.client.model.ServerActionsGetActionById200Response;
import com.konfigthis.client.model.ServerActionsGetActionByIdResponse;
import com.konfigthis.client.model.ServerActionsGetAllActionsResponse;
import com.konfigthis.client.model.ServerActionsGetAllResponse;
import com.konfigthis.client.model.ServerActionsGracefulShutdownResponse;
import com.konfigthis.client.model.ServerActionsPowerOffServerResponse;
import com.konfigthis.client.model.ServerActionsPowerOnServerResponse;
import com.konfigthis.client.model.ServerActionsRebuildServerFromImageRequest;
import com.konfigthis.client.model.ServerActionsRebuildServerFromImageResponse;
import com.konfigthis.client.model.ServerActionsRemoveFromPlacementGroupResponse;
import com.konfigthis.client.model.ServerActionsRequestConsoleResponse;
import com.konfigthis.client.model.ServerActionsResetServerPasswordResponse;
import com.konfigthis.client.model.ServerActionsResetServerResponse;
import com.konfigthis.client.model.ServerActionsSoftRebootServerResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

public class ServerActionsApiGenerated {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public ServerActionsApiGenerated() throws IllegalArgumentException {
        this(Configuration.getDefaultApiClient());
    }

    public ServerActionsApiGenerated(ApiClient apiClient) throws IllegalArgumentException {
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

    private okhttp3.Call addToPlacementGroupCall(Long id, ServerActionsAddToPlacementGroupRequest serverActionsAddToPlacementGroupRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = serverActionsAddToPlacementGroupRequest;

        // create path and map variables
        String localVarPath = "/servers/{id}/actions/add_to_placement_group"
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
    private okhttp3.Call addToPlacementGroupValidateBeforeCall(Long id, ServerActionsAddToPlacementGroupRequest serverActionsAddToPlacementGroupRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling addToPlacementGroup(Async)");
        }

        return addToPlacementGroupCall(id, serverActionsAddToPlacementGroupRequest, _callback);

    }


    private ApiResponse<ServerActionsAddToPlacementGroupResponse> addToPlacementGroupWithHttpInfo(Long id, ServerActionsAddToPlacementGroupRequest serverActionsAddToPlacementGroupRequest) throws ApiException {
        okhttp3.Call localVarCall = addToPlacementGroupValidateBeforeCall(id, serverActionsAddToPlacementGroupRequest, null);
        Type localVarReturnType = new TypeToken<ServerActionsAddToPlacementGroupResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call addToPlacementGroupAsync(Long id, ServerActionsAddToPlacementGroupRequest serverActionsAddToPlacementGroupRequest, final ApiCallback<ServerActionsAddToPlacementGroupResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = addToPlacementGroupValidateBeforeCall(id, serverActionsAddToPlacementGroupRequest, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsAddToPlacementGroupResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class AddToPlacementGroupRequestBuilder {
        private final Long placementGroup;
        private final Long id;

        private AddToPlacementGroupRequestBuilder(Long placementGroup, Long id) {
            this.placementGroup = placementGroup;
            this.id = id;
        }

        /**
         * Build call for addToPlacementGroup
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            ServerActionsAddToPlacementGroupRequest serverActionsAddToPlacementGroupRequest = buildBodyParams();
            return addToPlacementGroupCall(id, serverActionsAddToPlacementGroupRequest, _callback);
        }

        private ServerActionsAddToPlacementGroupRequest buildBodyParams() {
            ServerActionsAddToPlacementGroupRequest serverActionsAddToPlacementGroupRequest = new ServerActionsAddToPlacementGroupRequest();
            serverActionsAddToPlacementGroupRequest.placementGroup(this.placementGroup);
            return serverActionsAddToPlacementGroupRequest;
        }

        /**
         * Execute addToPlacementGroup request
         * @return ServerActionsAddToPlacementGroupResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsAddToPlacementGroupResponse execute() throws ApiException {
            ServerActionsAddToPlacementGroupRequest serverActionsAddToPlacementGroupRequest = buildBodyParams();
            ApiResponse<ServerActionsAddToPlacementGroupResponse> localVarResp = addToPlacementGroupWithHttpInfo(id, serverActionsAddToPlacementGroupRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute addToPlacementGroup request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsAddToPlacementGroupResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsAddToPlacementGroupResponse> executeWithHttpInfo() throws ApiException {
            ServerActionsAddToPlacementGroupRequest serverActionsAddToPlacementGroupRequest = buildBodyParams();
            return addToPlacementGroupWithHttpInfo(id, serverActionsAddToPlacementGroupRequest);
        }

        /**
         * Execute addToPlacementGroup request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsAddToPlacementGroupResponse> _callback) throws ApiException {
            ServerActionsAddToPlacementGroupRequest serverActionsAddToPlacementGroupRequest = buildBodyParams();
            return addToPlacementGroupAsync(id, serverActionsAddToPlacementGroupRequest, _callback);
        }
    }

    /**
     * Add a Server to a Placement Group
     * Adds a Server to a Placement Group.  Server must be powered off for this command to succeed.  #### Call specific error codes  | Code                          | Description                                                          | |-------------------------------|----------------------------------------------------------------------| | &#x60;server_not_stopped&#x60;          | The action requires a stopped server                                 | 
     * @param id ID of the Server (required)
     * @return AddToPlacementGroupRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public AddToPlacementGroupRequestBuilder addToPlacementGroup(Long placementGroup, Long id) throws IllegalArgumentException {
        if (placementGroup == null) throw new IllegalArgumentException("\"placementGroup\" is required but got null");
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new AddToPlacementGroupRequestBuilder(placementGroup, id);
    }
    private okhttp3.Call attachIsoToServerCall(Long id, ServerActionsAttachIsoToServerRequest serverActionsAttachIsoToServerRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = serverActionsAttachIsoToServerRequest;

        // create path and map variables
        String localVarPath = "/servers/{id}/actions/attach_iso"
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
    private okhttp3.Call attachIsoToServerValidateBeforeCall(Long id, ServerActionsAttachIsoToServerRequest serverActionsAttachIsoToServerRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling attachIsoToServer(Async)");
        }

        return attachIsoToServerCall(id, serverActionsAttachIsoToServerRequest, _callback);

    }


    private ApiResponse<ServerActionsAttachIsoToServerResponse> attachIsoToServerWithHttpInfo(Long id, ServerActionsAttachIsoToServerRequest serverActionsAttachIsoToServerRequest) throws ApiException {
        okhttp3.Call localVarCall = attachIsoToServerValidateBeforeCall(id, serverActionsAttachIsoToServerRequest, null);
        Type localVarReturnType = new TypeToken<ServerActionsAttachIsoToServerResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call attachIsoToServerAsync(Long id, ServerActionsAttachIsoToServerRequest serverActionsAttachIsoToServerRequest, final ApiCallback<ServerActionsAttachIsoToServerResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = attachIsoToServerValidateBeforeCall(id, serverActionsAttachIsoToServerRequest, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsAttachIsoToServerResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class AttachIsoToServerRequestBuilder {
        private final String iso;
        private final Long id;

        private AttachIsoToServerRequestBuilder(String iso, Long id) {
            this.iso = iso;
            this.id = id;
        }

        /**
         * Build call for attachIsoToServer
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            ServerActionsAttachIsoToServerRequest serverActionsAttachIsoToServerRequest = buildBodyParams();
            return attachIsoToServerCall(id, serverActionsAttachIsoToServerRequest, _callback);
        }

        private ServerActionsAttachIsoToServerRequest buildBodyParams() {
            ServerActionsAttachIsoToServerRequest serverActionsAttachIsoToServerRequest = new ServerActionsAttachIsoToServerRequest();
            serverActionsAttachIsoToServerRequest.iso(this.iso);
            return serverActionsAttachIsoToServerRequest;
        }

        /**
         * Execute attachIsoToServer request
         * @return ServerActionsAttachIsoToServerResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsAttachIsoToServerResponse execute() throws ApiException {
            ServerActionsAttachIsoToServerRequest serverActionsAttachIsoToServerRequest = buildBodyParams();
            ApiResponse<ServerActionsAttachIsoToServerResponse> localVarResp = attachIsoToServerWithHttpInfo(id, serverActionsAttachIsoToServerRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute attachIsoToServer request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsAttachIsoToServerResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsAttachIsoToServerResponse> executeWithHttpInfo() throws ApiException {
            ServerActionsAttachIsoToServerRequest serverActionsAttachIsoToServerRequest = buildBodyParams();
            return attachIsoToServerWithHttpInfo(id, serverActionsAttachIsoToServerRequest);
        }

        /**
         * Execute attachIsoToServer request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsAttachIsoToServerResponse> _callback) throws ApiException {
            ServerActionsAttachIsoToServerRequest serverActionsAttachIsoToServerRequest = buildBodyParams();
            return attachIsoToServerAsync(id, serverActionsAttachIsoToServerRequest, _callback);
        }
    }

    /**
     * Attach an ISO to a Server
     * Attaches an ISO to a Server. The Server will immediately see it as a new disk. An already attached ISO will automatically be detached before the new ISO is attached.  Servers with attached ISOs have a modified boot order: They will try to boot from the ISO first before falling back to hard disk. 
     * @param id ID of the Server (required)
     * @return AttachIsoToServerRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public AttachIsoToServerRequestBuilder attachIsoToServer(String iso, Long id) throws IllegalArgumentException {
        if (iso == null) throw new IllegalArgumentException("\"iso\" is required but got null");
            

        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new AttachIsoToServerRequestBuilder(iso, id);
    }
    private okhttp3.Call attachToNetworkCall(Long id, ServerActionsAttachToNetworkRequest serverActionsAttachToNetworkRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = serverActionsAttachToNetworkRequest;

        // create path and map variables
        String localVarPath = "/servers/{id}/actions/attach_to_network"
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
    private okhttp3.Call attachToNetworkValidateBeforeCall(Long id, ServerActionsAttachToNetworkRequest serverActionsAttachToNetworkRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling attachToNetwork(Async)");
        }

        return attachToNetworkCall(id, serverActionsAttachToNetworkRequest, _callback);

    }


    private ApiResponse<ServerActionsAttachToNetworkResponse> attachToNetworkWithHttpInfo(Long id, ServerActionsAttachToNetworkRequest serverActionsAttachToNetworkRequest) throws ApiException {
        okhttp3.Call localVarCall = attachToNetworkValidateBeforeCall(id, serverActionsAttachToNetworkRequest, null);
        Type localVarReturnType = new TypeToken<ServerActionsAttachToNetworkResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call attachToNetworkAsync(Long id, ServerActionsAttachToNetworkRequest serverActionsAttachToNetworkRequest, final ApiCallback<ServerActionsAttachToNetworkResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = attachToNetworkValidateBeforeCall(id, serverActionsAttachToNetworkRequest, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsAttachToNetworkResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class AttachToNetworkRequestBuilder {
        private final Long network;
        private final Long id;
        private List<String> aliasIps;
        private String ip;

        private AttachToNetworkRequestBuilder(Long network, Long id) {
            this.network = network;
            this.id = id;
        }

        /**
         * Set aliasIps
         * @param aliasIps Additional IPs to be assigned to this Server (optional)
         * @return AttachToNetworkRequestBuilder
         */
        public AttachToNetworkRequestBuilder aliasIps(List<String> aliasIps) {
            this.aliasIps = aliasIps;
            return this;
        }
        
        /**
         * Set ip
         * @param ip IP to request to be assigned to this Server; if you do not provide this then you will be auto assigned an IP address (optional)
         * @return AttachToNetworkRequestBuilder
         */
        public AttachToNetworkRequestBuilder ip(String ip) {
            this.ip = ip;
            return this;
        }
        
        /**
         * Build call for attachToNetwork
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            ServerActionsAttachToNetworkRequest serverActionsAttachToNetworkRequest = buildBodyParams();
            return attachToNetworkCall(id, serverActionsAttachToNetworkRequest, _callback);
        }

        private ServerActionsAttachToNetworkRequest buildBodyParams() {
            ServerActionsAttachToNetworkRequest serverActionsAttachToNetworkRequest = new ServerActionsAttachToNetworkRequest();
            serverActionsAttachToNetworkRequest.aliasIps(this.aliasIps);
            serverActionsAttachToNetworkRequest.ip(this.ip);
            serverActionsAttachToNetworkRequest.network(this.network);
            return serverActionsAttachToNetworkRequest;
        }

        /**
         * Execute attachToNetwork request
         * @return ServerActionsAttachToNetworkResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsAttachToNetworkResponse execute() throws ApiException {
            ServerActionsAttachToNetworkRequest serverActionsAttachToNetworkRequest = buildBodyParams();
            ApiResponse<ServerActionsAttachToNetworkResponse> localVarResp = attachToNetworkWithHttpInfo(id, serverActionsAttachToNetworkRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute attachToNetwork request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsAttachToNetworkResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsAttachToNetworkResponse> executeWithHttpInfo() throws ApiException {
            ServerActionsAttachToNetworkRequest serverActionsAttachToNetworkRequest = buildBodyParams();
            return attachToNetworkWithHttpInfo(id, serverActionsAttachToNetworkRequest);
        }

        /**
         * Execute attachToNetwork request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsAttachToNetworkResponse> _callback) throws ApiException {
            ServerActionsAttachToNetworkRequest serverActionsAttachToNetworkRequest = buildBodyParams();
            return attachToNetworkAsync(id, serverActionsAttachToNetworkRequest, _callback);
        }
    }

    /**
     * Attach a Server to a Network
     * Attaches a Server to a network. This will complement the fixed public Server interface by adding an additional ethernet interface to the Server which is connected to the specified network.  The Server will get an IP auto assigned from a subnet of type &#x60;server&#x60; in the same &#x60;network_zone&#x60;.  Using the &#x60;alias_ips&#x60; attribute you can also define one or more additional IPs to the Servers. Please note that you will have to configure these IPs by hand on your Server since only the primary IP will be given out by DHCP.  **Call specific error codes**  | Code                             | Description                                                           | |----------------------------------|-----------------------------------------------------------------------| | &#x60;server_already_attached&#x60;        | The server is already attached to the network                         | | &#x60;ip_not_available&#x60;               | The provided Network IP is not available                              | | &#x60;no_subnet_available&#x60;            | No Subnet or IP is available for the Server within the network        | | &#x60;networks_overlap&#x60;               | The network IP range overlaps with one of the server networks         | 
     * @param id ID of the Server (required)
     * @return AttachToNetworkRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public AttachToNetworkRequestBuilder attachToNetwork(Long network, Long id) throws IllegalArgumentException {
        if (network == null) throw new IllegalArgumentException("\"network\" is required but got null");
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new AttachToNetworkRequestBuilder(network, id);
    }
    private okhttp3.Call changeAliasIpsCall(Long id, ServerActionsChangeAliasIpsRequest serverActionsChangeAliasIpsRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = serverActionsChangeAliasIpsRequest;

        // create path and map variables
        String localVarPath = "/servers/{id}/actions/change_alias_ips"
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
    private okhttp3.Call changeAliasIpsValidateBeforeCall(Long id, ServerActionsChangeAliasIpsRequest serverActionsChangeAliasIpsRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling changeAliasIps(Async)");
        }

        return changeAliasIpsCall(id, serverActionsChangeAliasIpsRequest, _callback);

    }


    private ApiResponse<ServerActionsChangeAliasIpsResponse> changeAliasIpsWithHttpInfo(Long id, ServerActionsChangeAliasIpsRequest serverActionsChangeAliasIpsRequest) throws ApiException {
        okhttp3.Call localVarCall = changeAliasIpsValidateBeforeCall(id, serverActionsChangeAliasIpsRequest, null);
        Type localVarReturnType = new TypeToken<ServerActionsChangeAliasIpsResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call changeAliasIpsAsync(Long id, ServerActionsChangeAliasIpsRequest serverActionsChangeAliasIpsRequest, final ApiCallback<ServerActionsChangeAliasIpsResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = changeAliasIpsValidateBeforeCall(id, serverActionsChangeAliasIpsRequest, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsChangeAliasIpsResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class ChangeAliasIpsRequestBuilder {
        private final List<String> aliasIps;
        private final Long network;
        private final Long id;

        private ChangeAliasIpsRequestBuilder(List<String> aliasIps, Long network, Long id) {
            this.aliasIps = aliasIps;
            this.network = network;
            this.id = id;
        }

        /**
         * Build call for changeAliasIps
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            ServerActionsChangeAliasIpsRequest serverActionsChangeAliasIpsRequest = buildBodyParams();
            return changeAliasIpsCall(id, serverActionsChangeAliasIpsRequest, _callback);
        }

        private ServerActionsChangeAliasIpsRequest buildBodyParams() {
            ServerActionsChangeAliasIpsRequest serverActionsChangeAliasIpsRequest = new ServerActionsChangeAliasIpsRequest();
            serverActionsChangeAliasIpsRequest.aliasIps(this.aliasIps);
            serverActionsChangeAliasIpsRequest.network(this.network);
            return serverActionsChangeAliasIpsRequest;
        }

        /**
         * Execute changeAliasIps request
         * @return ServerActionsChangeAliasIpsResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsChangeAliasIpsResponse execute() throws ApiException {
            ServerActionsChangeAliasIpsRequest serverActionsChangeAliasIpsRequest = buildBodyParams();
            ApiResponse<ServerActionsChangeAliasIpsResponse> localVarResp = changeAliasIpsWithHttpInfo(id, serverActionsChangeAliasIpsRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute changeAliasIps request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsChangeAliasIpsResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsChangeAliasIpsResponse> executeWithHttpInfo() throws ApiException {
            ServerActionsChangeAliasIpsRequest serverActionsChangeAliasIpsRequest = buildBodyParams();
            return changeAliasIpsWithHttpInfo(id, serverActionsChangeAliasIpsRequest);
        }

        /**
         * Execute changeAliasIps request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsChangeAliasIpsResponse> _callback) throws ApiException {
            ServerActionsChangeAliasIpsRequest serverActionsChangeAliasIpsRequest = buildBodyParams();
            return changeAliasIpsAsync(id, serverActionsChangeAliasIpsRequest, _callback);
        }
    }

    /**
     * Change alias IPs of a Network
     * Changes the alias IPs of an already attached Network. Note that the existing aliases for the specified Network will be replaced with these provided in the request body. So if you want to add an alias IP, you have to provide the existing ones from the Network plus the new alias IP in the request body.
     * @param id ID of the Server (required)
     * @return ChangeAliasIpsRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public ChangeAliasIpsRequestBuilder changeAliasIps(List<String> aliasIps, Long network, Long id) throws IllegalArgumentException {
        if (aliasIps == null) throw new IllegalArgumentException("\"aliasIps\" is required but got null");
        if (network == null) throw new IllegalArgumentException("\"network\" is required but got null");
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new ChangeAliasIpsRequestBuilder(aliasIps, network, id);
    }
    private okhttp3.Call changeDnsPtrCall(Long id, ServerActionsChangeDnsPtrRequest serverActionsChangeDnsPtrRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = serverActionsChangeDnsPtrRequest;

        // create path and map variables
        String localVarPath = "/servers/{id}/actions/change_dns_ptr"
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
    private okhttp3.Call changeDnsPtrValidateBeforeCall(Long id, ServerActionsChangeDnsPtrRequest serverActionsChangeDnsPtrRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling changeDnsPtr(Async)");
        }

        return changeDnsPtrCall(id, serverActionsChangeDnsPtrRequest, _callback);

    }


    private ApiResponse<ServerActionsChangeDnsPtrResponse> changeDnsPtrWithHttpInfo(Long id, ServerActionsChangeDnsPtrRequest serverActionsChangeDnsPtrRequest) throws ApiException {
        okhttp3.Call localVarCall = changeDnsPtrValidateBeforeCall(id, serverActionsChangeDnsPtrRequest, null);
        Type localVarReturnType = new TypeToken<ServerActionsChangeDnsPtrResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call changeDnsPtrAsync(Long id, ServerActionsChangeDnsPtrRequest serverActionsChangeDnsPtrRequest, final ApiCallback<ServerActionsChangeDnsPtrResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = changeDnsPtrValidateBeforeCall(id, serverActionsChangeDnsPtrRequest, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsChangeDnsPtrResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class ChangeDnsPtrRequestBuilder {
        private final String dnsPtr;
        private final String ip;
        private final Long id;

        private ChangeDnsPtrRequestBuilder(String dnsPtr, String ip, Long id) {
            this.dnsPtr = dnsPtr;
            this.ip = ip;
            this.id = id;
        }

        /**
         * Build call for changeDnsPtr
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            ServerActionsChangeDnsPtrRequest serverActionsChangeDnsPtrRequest = buildBodyParams();
            return changeDnsPtrCall(id, serverActionsChangeDnsPtrRequest, _callback);
        }

        private ServerActionsChangeDnsPtrRequest buildBodyParams() {
            ServerActionsChangeDnsPtrRequest serverActionsChangeDnsPtrRequest = new ServerActionsChangeDnsPtrRequest();
            serverActionsChangeDnsPtrRequest.dnsPtr(this.dnsPtr);
            serverActionsChangeDnsPtrRequest.ip(this.ip);
            return serverActionsChangeDnsPtrRequest;
        }

        /**
         * Execute changeDnsPtr request
         * @return ServerActionsChangeDnsPtrResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsChangeDnsPtrResponse execute() throws ApiException {
            ServerActionsChangeDnsPtrRequest serverActionsChangeDnsPtrRequest = buildBodyParams();
            ApiResponse<ServerActionsChangeDnsPtrResponse> localVarResp = changeDnsPtrWithHttpInfo(id, serverActionsChangeDnsPtrRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute changeDnsPtr request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsChangeDnsPtrResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsChangeDnsPtrResponse> executeWithHttpInfo() throws ApiException {
            ServerActionsChangeDnsPtrRequest serverActionsChangeDnsPtrRequest = buildBodyParams();
            return changeDnsPtrWithHttpInfo(id, serverActionsChangeDnsPtrRequest);
        }

        /**
         * Execute changeDnsPtr request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsChangeDnsPtrResponse> _callback) throws ApiException {
            ServerActionsChangeDnsPtrRequest serverActionsChangeDnsPtrRequest = buildBodyParams();
            return changeDnsPtrAsync(id, serverActionsChangeDnsPtrRequest, _callback);
        }
    }

    /**
     * Change reverse DNS entry for this Server
     * Changes the hostname that will appear when getting the hostname belonging to the primary IPs (IPv4 and IPv6) of this Server.  Floating IPs assigned to the Server are not affected by this. 
     * @param id ID of the Server (required)
     * @return ChangeDnsPtrRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public ChangeDnsPtrRequestBuilder changeDnsPtr(String dnsPtr, String ip, Long id) throws IllegalArgumentException {
        
            

        if (ip == null) throw new IllegalArgumentException("\"ip\" is required but got null");
            

        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new ChangeDnsPtrRequestBuilder(dnsPtr, ip, id);
    }
    private okhttp3.Call changeProtectionCall(Long id, ServerActionsChangeProtectionRequest serverActionsChangeProtectionRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = serverActionsChangeProtectionRequest;

        // create path and map variables
        String localVarPath = "/servers/{id}/actions/change_protection"
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
    private okhttp3.Call changeProtectionValidateBeforeCall(Long id, ServerActionsChangeProtectionRequest serverActionsChangeProtectionRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling changeProtection(Async)");
        }

        return changeProtectionCall(id, serverActionsChangeProtectionRequest, _callback);

    }


    private ApiResponse<ServerActionsChangeProtectionResponse> changeProtectionWithHttpInfo(Long id, ServerActionsChangeProtectionRequest serverActionsChangeProtectionRequest) throws ApiException {
        okhttp3.Call localVarCall = changeProtectionValidateBeforeCall(id, serverActionsChangeProtectionRequest, null);
        Type localVarReturnType = new TypeToken<ServerActionsChangeProtectionResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call changeProtectionAsync(Long id, ServerActionsChangeProtectionRequest serverActionsChangeProtectionRequest, final ApiCallback<ServerActionsChangeProtectionResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = changeProtectionValidateBeforeCall(id, serverActionsChangeProtectionRequest, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsChangeProtectionResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class ChangeProtectionRequestBuilder {
        private final Long id;
        private Boolean delete;
        private Boolean rebuild;

        private ChangeProtectionRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Set delete
         * @param delete If true, prevents the Server from being deleted (currently delete and rebuild attribute needs to have the same value) (optional)
         * @return ChangeProtectionRequestBuilder
         */
        public ChangeProtectionRequestBuilder delete(Boolean delete) {
            this.delete = delete;
            return this;
        }
        
        /**
         * Set rebuild
         * @param rebuild If true, prevents the Server from being rebuilt (currently delete and rebuild attribute needs to have the same value) (optional)
         * @return ChangeProtectionRequestBuilder
         */
        public ChangeProtectionRequestBuilder rebuild(Boolean rebuild) {
            this.rebuild = rebuild;
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
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            ServerActionsChangeProtectionRequest serverActionsChangeProtectionRequest = buildBodyParams();
            return changeProtectionCall(id, serverActionsChangeProtectionRequest, _callback);
        }

        private ServerActionsChangeProtectionRequest buildBodyParams() {
            ServerActionsChangeProtectionRequest serverActionsChangeProtectionRequest = new ServerActionsChangeProtectionRequest();
            serverActionsChangeProtectionRequest.delete(this.delete);
            serverActionsChangeProtectionRequest.rebuild(this.rebuild);
            return serverActionsChangeProtectionRequest;
        }

        /**
         * Execute changeProtection request
         * @return ServerActionsChangeProtectionResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsChangeProtectionResponse execute() throws ApiException {
            ServerActionsChangeProtectionRequest serverActionsChangeProtectionRequest = buildBodyParams();
            ApiResponse<ServerActionsChangeProtectionResponse> localVarResp = changeProtectionWithHttpInfo(id, serverActionsChangeProtectionRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute changeProtection request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsChangeProtectionResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsChangeProtectionResponse> executeWithHttpInfo() throws ApiException {
            ServerActionsChangeProtectionRequest serverActionsChangeProtectionRequest = buildBodyParams();
            return changeProtectionWithHttpInfo(id, serverActionsChangeProtectionRequest);
        }

        /**
         * Execute changeProtection request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsChangeProtectionResponse> _callback) throws ApiException {
            ServerActionsChangeProtectionRequest serverActionsChangeProtectionRequest = buildBodyParams();
            return changeProtectionAsync(id, serverActionsChangeProtectionRequest, _callback);
        }
    }

    /**
     * Change Server Protection
     * Changes the protection configuration of the Server.
     * @param id ID of the Server (required)
     * @return ChangeProtectionRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public ChangeProtectionRequestBuilder changeProtection(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new ChangeProtectionRequestBuilder(id);
    }
    private okhttp3.Call changeServerTypeCall(Long id, ServerActionsChangeServerTypeRequest serverActionsChangeServerTypeRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = serverActionsChangeServerTypeRequest;

        // create path and map variables
        String localVarPath = "/servers/{id}/actions/change_type"
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
    private okhttp3.Call changeServerTypeValidateBeforeCall(Long id, ServerActionsChangeServerTypeRequest serverActionsChangeServerTypeRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling changeServerType(Async)");
        }

        return changeServerTypeCall(id, serverActionsChangeServerTypeRequest, _callback);

    }


    private ApiResponse<ServerActionsChangeServerTypeResponse> changeServerTypeWithHttpInfo(Long id, ServerActionsChangeServerTypeRequest serverActionsChangeServerTypeRequest) throws ApiException {
        okhttp3.Call localVarCall = changeServerTypeValidateBeforeCall(id, serverActionsChangeServerTypeRequest, null);
        Type localVarReturnType = new TypeToken<ServerActionsChangeServerTypeResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call changeServerTypeAsync(Long id, ServerActionsChangeServerTypeRequest serverActionsChangeServerTypeRequest, final ApiCallback<ServerActionsChangeServerTypeResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = changeServerTypeValidateBeforeCall(id, serverActionsChangeServerTypeRequest, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsChangeServerTypeResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class ChangeServerTypeRequestBuilder {
        private final String serverType;
        private final Boolean upgradeDisk;
        private final Long id;

        private ChangeServerTypeRequestBuilder(String serverType, Boolean upgradeDisk, Long id) {
            this.serverType = serverType;
            this.upgradeDisk = upgradeDisk;
            this.id = id;
        }

        /**
         * Build call for changeServerType
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            ServerActionsChangeServerTypeRequest serverActionsChangeServerTypeRequest = buildBodyParams();
            return changeServerTypeCall(id, serverActionsChangeServerTypeRequest, _callback);
        }

        private ServerActionsChangeServerTypeRequest buildBodyParams() {
            ServerActionsChangeServerTypeRequest serverActionsChangeServerTypeRequest = new ServerActionsChangeServerTypeRequest();
            serverActionsChangeServerTypeRequest.serverType(this.serverType);
            serverActionsChangeServerTypeRequest.upgradeDisk(this.upgradeDisk);
            return serverActionsChangeServerTypeRequest;
        }

        /**
         * Execute changeServerType request
         * @return ServerActionsChangeServerTypeResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsChangeServerTypeResponse execute() throws ApiException {
            ServerActionsChangeServerTypeRequest serverActionsChangeServerTypeRequest = buildBodyParams();
            ApiResponse<ServerActionsChangeServerTypeResponse> localVarResp = changeServerTypeWithHttpInfo(id, serverActionsChangeServerTypeRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute changeServerType request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsChangeServerTypeResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsChangeServerTypeResponse> executeWithHttpInfo() throws ApiException {
            ServerActionsChangeServerTypeRequest serverActionsChangeServerTypeRequest = buildBodyParams();
            return changeServerTypeWithHttpInfo(id, serverActionsChangeServerTypeRequest);
        }

        /**
         * Execute changeServerType request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsChangeServerTypeResponse> _callback) throws ApiException {
            ServerActionsChangeServerTypeRequest serverActionsChangeServerTypeRequest = buildBodyParams();
            return changeServerTypeAsync(id, serverActionsChangeServerTypeRequest, _callback);
        }
    }

    /**
     * Change the Type of a Server
     * Changes the type (Cores, RAM and disk sizes) of a Server.  Server must be powered off for this command to succeed.  This copies the content of its disk, and starts it again.  You can only migrate to Server types with the same &#x60;storage_type&#x60; and equal or bigger disks. Shrinking disks is not possible as it might destroy data.  If the disk gets upgraded, the Server type can not be downgraded any more. If you plan to downgrade the Server type, set &#x60;upgrade_disk&#x60; to &#x60;false&#x60;.  #### Call specific error codes  | Code                          | Description                                                          | |-------------------------------|----------------------------------------------------------------------| | &#x60;invalid_server_type&#x60;         | The server type does not fit for the given server or is deprecated   | | &#x60;server_not_stopped&#x60;          | The action requires a stopped server                                 | 
     * @param id ID of the Server (required)
     * @return ChangeServerTypeRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public ChangeServerTypeRequestBuilder changeServerType(String serverType, Boolean upgradeDisk, Long id) throws IllegalArgumentException {
        if (serverType == null) throw new IllegalArgumentException("\"serverType\" is required but got null");
            

        if (upgradeDisk == null) throw new IllegalArgumentException("\"upgradeDisk\" is required but got null");
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new ChangeServerTypeRequestBuilder(serverType, upgradeDisk, id);
    }
    private okhttp3.Call createImageCall(Long id, ServerActionsCreateImageRequest serverActionsCreateImageRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = serverActionsCreateImageRequest;

        // create path and map variables
        String localVarPath = "/servers/{id}/actions/create_image"
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
    private okhttp3.Call createImageValidateBeforeCall(Long id, ServerActionsCreateImageRequest serverActionsCreateImageRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling createImage(Async)");
        }

        return createImageCall(id, serverActionsCreateImageRequest, _callback);

    }


    private ApiResponse<ServerActionsCreateImageResponse> createImageWithHttpInfo(Long id, ServerActionsCreateImageRequest serverActionsCreateImageRequest) throws ApiException {
        okhttp3.Call localVarCall = createImageValidateBeforeCall(id, serverActionsCreateImageRequest, null);
        Type localVarReturnType = new TypeToken<ServerActionsCreateImageResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call createImageAsync(Long id, ServerActionsCreateImageRequest serverActionsCreateImageRequest, final ApiCallback<ServerActionsCreateImageResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = createImageValidateBeforeCall(id, serverActionsCreateImageRequest, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsCreateImageResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class CreateImageRequestBuilder {
        private final Long id;
        private String description;
        private ServerActionsCreateImageRequestLabels labels;
        private String type;

        private CreateImageRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Set description
         * @param description Description of the Image, will be auto-generated if not set (optional)
         * @return CreateImageRequestBuilder
         */
        public CreateImageRequestBuilder description(String description) {
            this.description = description;
            return this;
        }
        
        /**
         * Set labels
         * @param labels  (optional)
         * @return CreateImageRequestBuilder
         */
        public CreateImageRequestBuilder labels(ServerActionsCreateImageRequestLabels labels) {
            this.labels = labels;
            return this;
        }
        
        /**
         * Set type
         * @param type Type of Image to create. (optional, default to snapshot)
         * @return CreateImageRequestBuilder
         */
        public CreateImageRequestBuilder type(String type) {
            this.type = type;
            return this;
        }
        
        /**
         * Build call for createImage
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;image&#x60; key in the reply contains an the created Image, which is an object with this structure  The &#x60;action&#x60; key in the reply contains an Action object with this structure  </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            ServerActionsCreateImageRequest serverActionsCreateImageRequest = buildBodyParams();
            return createImageCall(id, serverActionsCreateImageRequest, _callback);
        }

        private ServerActionsCreateImageRequest buildBodyParams() {
            ServerActionsCreateImageRequest serverActionsCreateImageRequest = new ServerActionsCreateImageRequest();
            serverActionsCreateImageRequest.description(this.description);
            serverActionsCreateImageRequest.labels(this.labels);
            if (this.type != null)
            serverActionsCreateImageRequest.type(ServerActionsCreateImageRequest.TypeEnum.fromValue(this.type));
            return serverActionsCreateImageRequest;
        }

        /**
         * Execute createImage request
         * @return ServerActionsCreateImageResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;image&#x60; key in the reply contains an the created Image, which is an object with this structure  The &#x60;action&#x60; key in the reply contains an Action object with this structure  </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsCreateImageResponse execute() throws ApiException {
            ServerActionsCreateImageRequest serverActionsCreateImageRequest = buildBodyParams();
            ApiResponse<ServerActionsCreateImageResponse> localVarResp = createImageWithHttpInfo(id, serverActionsCreateImageRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute createImage request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsCreateImageResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;image&#x60; key in the reply contains an the created Image, which is an object with this structure  The &#x60;action&#x60; key in the reply contains an Action object with this structure  </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsCreateImageResponse> executeWithHttpInfo() throws ApiException {
            ServerActionsCreateImageRequest serverActionsCreateImageRequest = buildBodyParams();
            return createImageWithHttpInfo(id, serverActionsCreateImageRequest);
        }

        /**
         * Execute createImage request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;image&#x60; key in the reply contains an the created Image, which is an object with this structure  The &#x60;action&#x60; key in the reply contains an Action object with this structure  </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsCreateImageResponse> _callback) throws ApiException {
            ServerActionsCreateImageRequest serverActionsCreateImageRequest = buildBodyParams();
            return createImageAsync(id, serverActionsCreateImageRequest, _callback);
        }
    }

    /**
     * Create Image from a Server
     * Creates an Image (snapshot) from a Server by copying the contents of its disks. This creates a snapshot of the current state of the disk and copies it into an Image. If the Server is currently running you must make sure that its disk content is consistent. Otherwise, the created Image may not be readable.  To make sure disk content is consistent, we recommend to shut down the Server prior to creating an Image.  You can either create a &#x60;backup&#x60; Image that is bound to the Server and therefore will be deleted when the Server is deleted, or you can create a &#x60;snapshot&#x60; Image which is completely independent of the Server it was created from and will survive Server deletion. Backup Images are only available when the backup option is enabled for the Server. Snapshot Images are billed on a per GB basis. 
     * @param id ID of the Server (required)
     * @return CreateImageRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;image&#x60; key in the reply contains an the created Image, which is an object with this structure  The &#x60;action&#x60; key in the reply contains an Action object with this structure  </td><td>  -  </td></tr>
     </table>
     */
    public CreateImageRequestBuilder createImage(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new CreateImageRequestBuilder(id);
    }
    private okhttp3.Call detachFromNetworkCall(Long id, ServerActionsDetachFromNetworkRequest serverActionsDetachFromNetworkRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = serverActionsDetachFromNetworkRequest;

        // create path and map variables
        String localVarPath = "/servers/{id}/actions/detach_from_network"
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
    private okhttp3.Call detachFromNetworkValidateBeforeCall(Long id, ServerActionsDetachFromNetworkRequest serverActionsDetachFromNetworkRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling detachFromNetwork(Async)");
        }

        return detachFromNetworkCall(id, serverActionsDetachFromNetworkRequest, _callback);

    }


    private ApiResponse<ServerActionsDetachFromNetworkResponse> detachFromNetworkWithHttpInfo(Long id, ServerActionsDetachFromNetworkRequest serverActionsDetachFromNetworkRequest) throws ApiException {
        okhttp3.Call localVarCall = detachFromNetworkValidateBeforeCall(id, serverActionsDetachFromNetworkRequest, null);
        Type localVarReturnType = new TypeToken<ServerActionsDetachFromNetworkResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call detachFromNetworkAsync(Long id, ServerActionsDetachFromNetworkRequest serverActionsDetachFromNetworkRequest, final ApiCallback<ServerActionsDetachFromNetworkResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = detachFromNetworkValidateBeforeCall(id, serverActionsDetachFromNetworkRequest, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsDetachFromNetworkResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class DetachFromNetworkRequestBuilder {
        private final Long network;
        private final Long id;

        private DetachFromNetworkRequestBuilder(Long network, Long id) {
            this.network = network;
            this.id = id;
        }

        /**
         * Build call for detachFromNetwork
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            ServerActionsDetachFromNetworkRequest serverActionsDetachFromNetworkRequest = buildBodyParams();
            return detachFromNetworkCall(id, serverActionsDetachFromNetworkRequest, _callback);
        }

        private ServerActionsDetachFromNetworkRequest buildBodyParams() {
            ServerActionsDetachFromNetworkRequest serverActionsDetachFromNetworkRequest = new ServerActionsDetachFromNetworkRequest();
            serverActionsDetachFromNetworkRequest.network(this.network);
            return serverActionsDetachFromNetworkRequest;
        }

        /**
         * Execute detachFromNetwork request
         * @return ServerActionsDetachFromNetworkResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsDetachFromNetworkResponse execute() throws ApiException {
            ServerActionsDetachFromNetworkRequest serverActionsDetachFromNetworkRequest = buildBodyParams();
            ApiResponse<ServerActionsDetachFromNetworkResponse> localVarResp = detachFromNetworkWithHttpInfo(id, serverActionsDetachFromNetworkRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute detachFromNetwork request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsDetachFromNetworkResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsDetachFromNetworkResponse> executeWithHttpInfo() throws ApiException {
            ServerActionsDetachFromNetworkRequest serverActionsDetachFromNetworkRequest = buildBodyParams();
            return detachFromNetworkWithHttpInfo(id, serverActionsDetachFromNetworkRequest);
        }

        /**
         * Execute detachFromNetwork request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsDetachFromNetworkResponse> _callback) throws ApiException {
            ServerActionsDetachFromNetworkRequest serverActionsDetachFromNetworkRequest = buildBodyParams();
            return detachFromNetworkAsync(id, serverActionsDetachFromNetworkRequest, _callback);
        }
    }

    /**
     * Detach a Server from a Network
     * Detaches a Server from a network. The interface for this network will vanish.
     * @param id ID of the Server (required)
     * @return DetachFromNetworkRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public DetachFromNetworkRequestBuilder detachFromNetwork(Long network, Long id) throws IllegalArgumentException {
        if (network == null) throw new IllegalArgumentException("\"network\" is required but got null");
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new DetachFromNetworkRequestBuilder(network, id);
    }
    private okhttp3.Call detachIsoFromServerCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/{id}/actions/detach_iso"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call detachIsoFromServerValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling detachIsoFromServer(Async)");
        }

        return detachIsoFromServerCall(id, _callback);

    }


    private ApiResponse<ServerActionsDetachIsoFromServerResponse> detachIsoFromServerWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = detachIsoFromServerValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServerActionsDetachIsoFromServerResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call detachIsoFromServerAsync(Long id, final ApiCallback<ServerActionsDetachIsoFromServerResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = detachIsoFromServerValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsDetachIsoFromServerResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class DetachIsoFromServerRequestBuilder {
        private final Long id;

        private DetachIsoFromServerRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for detachIsoFromServer
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return detachIsoFromServerCall(id, _callback);
        }


        /**
         * Execute detachIsoFromServer request
         * @return ServerActionsDetachIsoFromServerResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsDetachIsoFromServerResponse execute() throws ApiException {
            ApiResponse<ServerActionsDetachIsoFromServerResponse> localVarResp = detachIsoFromServerWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute detachIsoFromServer request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsDetachIsoFromServerResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsDetachIsoFromServerResponse> executeWithHttpInfo() throws ApiException {
            return detachIsoFromServerWithHttpInfo(id);
        }

        /**
         * Execute detachIsoFromServer request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsDetachIsoFromServerResponse> _callback) throws ApiException {
            return detachIsoFromServerAsync(id, _callback);
        }
    }

    /**
     * Detach an ISO from a Server
     * Detaches an ISO from a Server. In case no ISO Image is attached to the Server, the status of the returned Action is immediately set to &#x60;success&#x60;
     * @param id ID of the Server (required)
     * @return DetachIsoFromServerRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public DetachIsoFromServerRequestBuilder detachIsoFromServer(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new DetachIsoFromServerRequestBuilder(id);
    }
    private okhttp3.Call disableBackupCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/{id}/actions/disable_backup"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call disableBackupValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling disableBackup(Async)");
        }

        return disableBackupCall(id, _callback);

    }


    private ApiResponse<ServerActionsDisableBackupResponse> disableBackupWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = disableBackupValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServerActionsDisableBackupResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call disableBackupAsync(Long id, final ApiCallback<ServerActionsDisableBackupResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = disableBackupValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsDisableBackupResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class DisableBackupRequestBuilder {
        private final Long id;

        private DisableBackupRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for disableBackup
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return disableBackupCall(id, _callback);
        }


        /**
         * Execute disableBackup request
         * @return ServerActionsDisableBackupResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsDisableBackupResponse execute() throws ApiException {
            ApiResponse<ServerActionsDisableBackupResponse> localVarResp = disableBackupWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute disableBackup request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsDisableBackupResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsDisableBackupResponse> executeWithHttpInfo() throws ApiException {
            return disableBackupWithHttpInfo(id);
        }

        /**
         * Execute disableBackup request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsDisableBackupResponse> _callback) throws ApiException {
            return disableBackupAsync(id, _callback);
        }
    }

    /**
     * Disable Backups for a Server
     * Disables the automatic backup option and deletes all existing Backups for a Server. No more additional charges for backups will be made.  Caution: This immediately removes all existing backups for the Server! 
     * @param id ID of the Server (required)
     * @return DisableBackupRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public DisableBackupRequestBuilder disableBackup(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new DisableBackupRequestBuilder(id);
    }
    private okhttp3.Call disableRescueModeCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/{id}/actions/disable_rescue"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call disableRescueModeValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling disableRescueMode(Async)");
        }

        return disableRescueModeCall(id, _callback);

    }


    private ApiResponse<ServerActionsDisableRescueModeResponse> disableRescueModeWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = disableRescueModeValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServerActionsDisableRescueModeResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call disableRescueModeAsync(Long id, final ApiCallback<ServerActionsDisableRescueModeResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = disableRescueModeValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsDisableRescueModeResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class DisableRescueModeRequestBuilder {
        private final Long id;

        private DisableRescueModeRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for disableRescueMode
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return disableRescueModeCall(id, _callback);
        }


        /**
         * Execute disableRescueMode request
         * @return ServerActionsDisableRescueModeResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsDisableRescueModeResponse execute() throws ApiException {
            ApiResponse<ServerActionsDisableRescueModeResponse> localVarResp = disableRescueModeWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute disableRescueMode request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsDisableRescueModeResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsDisableRescueModeResponse> executeWithHttpInfo() throws ApiException {
            return disableRescueModeWithHttpInfo(id);
        }

        /**
         * Execute disableRescueMode request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsDisableRescueModeResponse> _callback) throws ApiException {
            return disableRescueModeAsync(id, _callback);
        }
    }

    /**
     * Disable Rescue Mode for a Server
     * Disables the Hetzner Rescue System for a Server. This makes a Server start from its disks on next reboot.  Rescue Mode is automatically disabled when you first boot into it or if you do not use it for 60 minutes.  Disabling rescue mode will not reboot your Server — you will have to do this yourself. 
     * @param id ID of the Server (required)
     * @return DisableRescueModeRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public DisableRescueModeRequestBuilder disableRescueMode(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new DisableRescueModeRequestBuilder(id);
    }
    private okhttp3.Call enableBackupCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/{id}/actions/enable_backup"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call enableBackupValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling enableBackup(Async)");
        }

        return enableBackupCall(id, _callback);

    }


    private ApiResponse<ServerActionsEnableBackupResponse> enableBackupWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = enableBackupValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServerActionsEnableBackupResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call enableBackupAsync(Long id, final ApiCallback<ServerActionsEnableBackupResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = enableBackupValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsEnableBackupResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class EnableBackupRequestBuilder {
        private final Long id;

        private EnableBackupRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for enableBackup
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return enableBackupCall(id, _callback);
        }


        /**
         * Execute enableBackup request
         * @return ServerActionsEnableBackupResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsEnableBackupResponse execute() throws ApiException {
            ApiResponse<ServerActionsEnableBackupResponse> localVarResp = enableBackupWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute enableBackup request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsEnableBackupResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsEnableBackupResponse> executeWithHttpInfo() throws ApiException {
            return enableBackupWithHttpInfo(id);
        }

        /**
         * Execute enableBackup request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsEnableBackupResponse> _callback) throws ApiException {
            return enableBackupAsync(id, _callback);
        }
    }

    /**
     * Enable and Configure Backups for a Server
     * Enables and configures the automatic daily backup option for the Server. Enabling automatic backups will increase the price of the Server by 20%. In return, you will get seven slots where Images of type backup can be stored.  Backups are automatically created daily. 
     * @param id ID of the Server (required)
     * @return EnableBackupRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public EnableBackupRequestBuilder enableBackup(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new EnableBackupRequestBuilder(id);
    }
    private okhttp3.Call enableRescueModeCall(Long id, ServerActionsEnableRescueModeRequest serverActionsEnableRescueModeRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = serverActionsEnableRescueModeRequest;

        // create path and map variables
        String localVarPath = "/servers/{id}/actions/enable_rescue"
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
    private okhttp3.Call enableRescueModeValidateBeforeCall(Long id, ServerActionsEnableRescueModeRequest serverActionsEnableRescueModeRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling enableRescueMode(Async)");
        }

        return enableRescueModeCall(id, serverActionsEnableRescueModeRequest, _callback);

    }


    private ApiResponse<ServerActionsEnableRescueModeResponse> enableRescueModeWithHttpInfo(Long id, ServerActionsEnableRescueModeRequest serverActionsEnableRescueModeRequest) throws ApiException {
        okhttp3.Call localVarCall = enableRescueModeValidateBeforeCall(id, serverActionsEnableRescueModeRequest, null);
        Type localVarReturnType = new TypeToken<ServerActionsEnableRescueModeResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call enableRescueModeAsync(Long id, ServerActionsEnableRescueModeRequest serverActionsEnableRescueModeRequest, final ApiCallback<ServerActionsEnableRescueModeResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = enableRescueModeValidateBeforeCall(id, serverActionsEnableRescueModeRequest, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsEnableRescueModeResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class EnableRescueModeRequestBuilder {
        private final Long id;
        private List<Long> sshKeys;
        private String type;

        private EnableRescueModeRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Set sshKeys
         * @param sshKeys Array of SSH key IDs which should be injected into the rescue system. (optional)
         * @return EnableRescueModeRequestBuilder
         */
        public EnableRescueModeRequestBuilder sshKeys(List<Long> sshKeys) {
            this.sshKeys = sshKeys;
            return this;
        }
        
        /**
         * Set type
         * @param type Type of rescue system to boot. (optional, default to linux64)
         * @return EnableRescueModeRequestBuilder
         */
        public EnableRescueModeRequestBuilder type(String type) {
            this.type = type;
            return this;
        }
        
        /**
         * Build call for enableRescueMode
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;root_password&#x60; key in the reply contains the root password that can be used to access the booted rescue system.  The &#x60;action&#x60; key in the reply contains an Action object with this structure  </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            ServerActionsEnableRescueModeRequest serverActionsEnableRescueModeRequest = buildBodyParams();
            return enableRescueModeCall(id, serverActionsEnableRescueModeRequest, _callback);
        }

        private ServerActionsEnableRescueModeRequest buildBodyParams() {
            ServerActionsEnableRescueModeRequest serverActionsEnableRescueModeRequest = new ServerActionsEnableRescueModeRequest();
            serverActionsEnableRescueModeRequest.sshKeys(this.sshKeys);
            if (this.type != null)
            serverActionsEnableRescueModeRequest.type(ServerActionsEnableRescueModeRequest.TypeEnum.fromValue(this.type));
            return serverActionsEnableRescueModeRequest;
        }

        /**
         * Execute enableRescueMode request
         * @return ServerActionsEnableRescueModeResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;root_password&#x60; key in the reply contains the root password that can be used to access the booted rescue system.  The &#x60;action&#x60; key in the reply contains an Action object with this structure  </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsEnableRescueModeResponse execute() throws ApiException {
            ServerActionsEnableRescueModeRequest serverActionsEnableRescueModeRequest = buildBodyParams();
            ApiResponse<ServerActionsEnableRescueModeResponse> localVarResp = enableRescueModeWithHttpInfo(id, serverActionsEnableRescueModeRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute enableRescueMode request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsEnableRescueModeResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;root_password&#x60; key in the reply contains the root password that can be used to access the booted rescue system.  The &#x60;action&#x60; key in the reply contains an Action object with this structure  </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsEnableRescueModeResponse> executeWithHttpInfo() throws ApiException {
            ServerActionsEnableRescueModeRequest serverActionsEnableRescueModeRequest = buildBodyParams();
            return enableRescueModeWithHttpInfo(id, serverActionsEnableRescueModeRequest);
        }

        /**
         * Execute enableRescueMode request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;root_password&#x60; key in the reply contains the root password that can be used to access the booted rescue system.  The &#x60;action&#x60; key in the reply contains an Action object with this structure  </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsEnableRescueModeResponse> _callback) throws ApiException {
            ServerActionsEnableRescueModeRequest serverActionsEnableRescueModeRequest = buildBodyParams();
            return enableRescueModeAsync(id, serverActionsEnableRescueModeRequest, _callback);
        }
    }

    /**
     * Enable Rescue Mode for a Server
     * Enable the Hetzner Rescue System for this Server. The next time a Server with enabled rescue mode boots it will start a special minimal Linux distribution designed for repair and reinstall.  In case a Server cannot boot on its own you can use this to access a Server’s disks.  Rescue Mode is automatically disabled when you first boot into it or if you do not use it for 60 minutes.  Enabling rescue mode will not [reboot](https://docs.hetzner.cloud/#server-actions-soft-reboot-a-server) your Server — you will have to do this yourself. 
     * @param id ID of the Server (required)
     * @return EnableRescueModeRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;root_password&#x60; key in the reply contains the root password that can be used to access the booted rescue system.  The &#x60;action&#x60; key in the reply contains an Action object with this structure  </td><td>  -  </td></tr>
     </table>
     */
    public EnableRescueModeRequestBuilder enableRescueMode(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new EnableRescueModeRequestBuilder(id);
    }
    private okhttp3.Call getActionByIdCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/actions/{id}"
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
    private okhttp3.Call getActionByIdValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getActionById(Async)");
        }

        return getActionByIdCall(id, _callback);

    }


    private ApiResponse<ServerActionsGetActionByIdResponse> getActionByIdWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = getActionByIdValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServerActionsGetActionByIdResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getActionByIdAsync(Long id, final ApiCallback<ServerActionsGetActionByIdResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getActionByIdValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsGetActionByIdResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetActionByIdRequestBuilder {
        private final Long id;

        private GetActionByIdRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for getActionById
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
            return getActionByIdCall(id, _callback);
        }


        /**
         * Execute getActionById request
         * @return ServerActionsGetActionByIdResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsGetActionByIdResponse execute() throws ApiException {
            ApiResponse<ServerActionsGetActionByIdResponse> localVarResp = getActionByIdWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getActionById request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsGetActionByIdResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsGetActionByIdResponse> executeWithHttpInfo() throws ApiException {
            return getActionByIdWithHttpInfo(id);
        }

        /**
         * Execute getActionById request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsGetActionByIdResponse> _callback) throws ApiException {
            return getActionByIdAsync(id, _callback);
        }
    }

    /**
     * Get an Action
     * Returns a specific Action object.
     * @param id ID of the Action. (required)
     * @return GetActionByIdRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
     </table>
     */
    public GetActionByIdRequestBuilder getActionById(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new GetActionByIdRequestBuilder(id);
    }
    private okhttp3.Call getActionById_0Call(Long id, Long actionId, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/{id}/actions/{action_id}"
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
    private okhttp3.Call getActionById_0ValidateBeforeCall(Long id, Long actionId, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getActionById_0(Async)");
        }

        // verify the required parameter 'actionId' is set
        if (actionId == null) {
            throw new ApiException("Missing the required parameter 'actionId' when calling getActionById_0(Async)");
        }

        return getActionById_0Call(id, actionId, _callback);

    }


    private ApiResponse<ServerActionsGetActionById200Response> getActionById_0WithHttpInfo(Long id, Long actionId) throws ApiException {
        okhttp3.Call localVarCall = getActionById_0ValidateBeforeCall(id, actionId, null);
        Type localVarReturnType = new TypeToken<ServerActionsGetActionById200Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getActionById_0Async(Long id, Long actionId, final ApiCallback<ServerActionsGetActionById200Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = getActionById_0ValidateBeforeCall(id, actionId, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsGetActionById200Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetActionById0RequestBuilder {
        private final Long id;
        private final Long actionId;

        private GetActionById0RequestBuilder(Long id, Long actionId) {
            this.id = id;
            this.actionId = actionId;
        }

        /**
         * Build call for getActionById_0
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
            return getActionById_0Call(id, actionId, _callback);
        }


        /**
         * Execute getActionById_0 request
         * @return ServerActionsGetActionById200Response
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsGetActionById200Response execute() throws ApiException {
            ApiResponse<ServerActionsGetActionById200Response> localVarResp = getActionById_0WithHttpInfo(id, actionId);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getActionById_0 request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsGetActionById200Response&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsGetActionById200Response> executeWithHttpInfo() throws ApiException {
            return getActionById_0WithHttpInfo(id, actionId);
        }

        /**
         * Execute getActionById_0 request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsGetActionById200Response> _callback) throws ApiException {
            return getActionById_0Async(id, actionId, _callback);
        }
    }

    /**
     * Get an Action for a Server
     * Returns a specific Action object for a Server.
     * @param id ID of the Server (required)
     * @param actionId ID of the Action (required)
     * @return GetActionById0RequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
     </table>
     */
    public GetActionById0RequestBuilder getActionById_0(Long id, Long actionId) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        if (actionId == null) throw new IllegalArgumentException("\"actionId\" is required but got null");
        return new GetActionById0RequestBuilder(id, actionId);
    }
    private okhttp3.Call getAllCall(Long id, String sort, String status, Long page, Long perPage, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/actions";

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
    private okhttp3.Call getAllValidateBeforeCall(Long id, String sort, String status, Long page, Long perPage, final ApiCallback _callback) throws ApiException {
        return getAllCall(id, sort, status, page, perPage, _callback);

    }


    private ApiResponse<ServerActionsGetAllResponse> getAllWithHttpInfo(Long id, String sort, String status, Long page, Long perPage) throws ApiException {
        okhttp3.Call localVarCall = getAllValidateBeforeCall(id, sort, status, page, perPage, null);
        Type localVarReturnType = new TypeToken<ServerActionsGetAllResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getAllAsync(Long id, String sort, String status, Long page, Long perPage, final ApiCallback<ServerActionsGetAllResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAllValidateBeforeCall(id, sort, status, page, perPage, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsGetAllResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetAllRequestBuilder {
        private Long id;
        private String sort;
        private String status;
        private Long page;
        private Long perPage;

        private GetAllRequestBuilder() {
        }

        /**
         * Set id
         * @param id Filter the actions by ID. Can be used multiple times. The response will only contain actions matching the specified IDs.  (optional)
         * @return GetAllRequestBuilder
         */
        public GetAllRequestBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        /**
         * Set sort
         * @param sort Sort actions by field and direction. Can be used multiple times. For more information, see \&quot;[Sorting](https://docs.hetzner.cloud)\&quot;.  (optional)
         * @return GetAllRequestBuilder
         */
        public GetAllRequestBuilder sort(String sort) {
            this.sort = sort;
            return this;
        }
        
        /**
         * Set status
         * @param status Filter the actions by status. Can be used multiple times. The response will only contain actions matching the specified statuses.  (optional)
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
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getAllCall(id, sort, status, page, perPage, _callback);
        }


        /**
         * Execute getAll request
         * @return ServerActionsGetAllResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsGetAllResponse execute() throws ApiException {
            ApiResponse<ServerActionsGetAllResponse> localVarResp = getAllWithHttpInfo(id, sort, status, page, perPage);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getAll request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsGetAllResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsGetAllResponse> executeWithHttpInfo() throws ApiException {
            return getAllWithHttpInfo(id, sort, status, page, perPage);
        }

        /**
         * Execute getAll request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsGetAllResponse> _callback) throws ApiException {
            return getAllAsync(id, sort, status, page, perPage, _callback);
        }
    }

    /**
     * Get all Actions
     * Returns all Action objects. You can &#x60;sort&#x60; the results by using the sort URI parameter, and filter them with the &#x60;status&#x60; and &#x60;id&#x60; parameter.
     * @return GetAllRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
     </table>
     */
    public GetAllRequestBuilder getAll() throws IllegalArgumentException {
        return new GetAllRequestBuilder();
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
        String localVarPath = "/servers/{id}/actions"
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
    private okhttp3.Call getAllActionsValidateBeforeCall(Long id, String sort, String status, Long page, Long perPage, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getAllActions(Async)");
        }

        return getAllActionsCall(id, sort, status, page, perPage, _callback);

    }


    private ApiResponse<ServerActionsGetAllActionsResponse> getAllActionsWithHttpInfo(Long id, String sort, String status, Long page, Long perPage) throws ApiException {
        okhttp3.Call localVarCall = getAllActionsValidateBeforeCall(id, sort, status, page, perPage, null);
        Type localVarReturnType = new TypeToken<ServerActionsGetAllActionsResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getAllActionsAsync(Long id, String sort, String status, Long page, Long perPage, final ApiCallback<ServerActionsGetAllActionsResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAllActionsValidateBeforeCall(id, sort, status, page, perPage, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsGetAllActionsResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetAllActionsRequestBuilder {
        private final Long id;
        private String sort;
        private String status;
        private Long page;
        private Long perPage;

        private GetAllActionsRequestBuilder(Long id) {
            this.id = id;
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
         * @return ServerActionsGetAllActionsResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsGetAllActionsResponse execute() throws ApiException {
            ApiResponse<ServerActionsGetAllActionsResponse> localVarResp = getAllActionsWithHttpInfo(id, sort, status, page, perPage);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getAllActions request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsGetAllActionsResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsGetAllActionsResponse> executeWithHttpInfo() throws ApiException {
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
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsGetAllActionsResponse> _callback) throws ApiException {
            return getAllActionsAsync(id, sort, status, page, perPage, _callback);
        }
    }

    /**
     * Get all Actions for a Server
     * Returns all Action objects for a Server. You can &#x60;sort&#x60; the results by using the sort URI parameter, and filter them with the &#x60;status&#x60; parameter.
     * @param id ID of the Resource. (required)
     * @return GetAllActionsRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
     </table>
     */
    public GetAllActionsRequestBuilder getAllActions(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new GetAllActionsRequestBuilder(id);
    }
    private okhttp3.Call gracefulShutdownCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/{id}/actions/shutdown"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call gracefulShutdownValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling gracefulShutdown(Async)");
        }

        return gracefulShutdownCall(id, _callback);

    }


    private ApiResponse<ServerActionsGracefulShutdownResponse> gracefulShutdownWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = gracefulShutdownValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServerActionsGracefulShutdownResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call gracefulShutdownAsync(Long id, final ApiCallback<ServerActionsGracefulShutdownResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = gracefulShutdownValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsGracefulShutdownResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GracefulShutdownRequestBuilder {
        private final Long id;

        private GracefulShutdownRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for gracefulShutdown
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return gracefulShutdownCall(id, _callback);
        }


        /**
         * Execute gracefulShutdown request
         * @return ServerActionsGracefulShutdownResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsGracefulShutdownResponse execute() throws ApiException {
            ApiResponse<ServerActionsGracefulShutdownResponse> localVarResp = gracefulShutdownWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute gracefulShutdown request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsGracefulShutdownResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsGracefulShutdownResponse> executeWithHttpInfo() throws ApiException {
            return gracefulShutdownWithHttpInfo(id);
        }

        /**
         * Execute gracefulShutdown request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsGracefulShutdownResponse> _callback) throws ApiException {
            return gracefulShutdownAsync(id, _callback);
        }
    }

    /**
     * Shutdown a Server
     * Shuts down a Server gracefully by sending an ACPI shutdown request. The Server operating system must support ACPI and react to the request, otherwise the Server will not shut down. Please note that the &#x60;action&#x60; status in this case only reflects whether the action was sent to the server. It does not mean that the server actually shut down successfully. If you need to ensure that the server is off, use the &#x60;poweroff&#x60; action 
     * @param id ID of the Server (required)
     * @return GracefulShutdownRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public GracefulShutdownRequestBuilder gracefulShutdown(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new GracefulShutdownRequestBuilder(id);
    }
    private okhttp3.Call powerOffServerCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/{id}/actions/poweroff"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call powerOffServerValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling powerOffServer(Async)");
        }

        return powerOffServerCall(id, _callback);

    }


    private ApiResponse<ServerActionsPowerOffServerResponse> powerOffServerWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = powerOffServerValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServerActionsPowerOffServerResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call powerOffServerAsync(Long id, final ApiCallback<ServerActionsPowerOffServerResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = powerOffServerValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsPowerOffServerResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class PowerOffServerRequestBuilder {
        private final Long id;

        private PowerOffServerRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for powerOffServer
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return powerOffServerCall(id, _callback);
        }


        /**
         * Execute powerOffServer request
         * @return ServerActionsPowerOffServerResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsPowerOffServerResponse execute() throws ApiException {
            ApiResponse<ServerActionsPowerOffServerResponse> localVarResp = powerOffServerWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute powerOffServer request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsPowerOffServerResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsPowerOffServerResponse> executeWithHttpInfo() throws ApiException {
            return powerOffServerWithHttpInfo(id);
        }

        /**
         * Execute powerOffServer request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsPowerOffServerResponse> _callback) throws ApiException {
            return powerOffServerAsync(id, _callback);
        }
    }

    /**
     * Power off a Server
     * Cuts power to the Server. This forcefully stops it without giving the Server operating system time to gracefully stop. May lead to data loss, equivalent to pulling the power cord. Power off should only be used when shutdown does not work.
     * @param id ID of the Server (required)
     * @return PowerOffServerRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public PowerOffServerRequestBuilder powerOffServer(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new PowerOffServerRequestBuilder(id);
    }
    private okhttp3.Call powerOnServerCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/{id}/actions/poweron"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call powerOnServerValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling powerOnServer(Async)");
        }

        return powerOnServerCall(id, _callback);

    }


    private ApiResponse<ServerActionsPowerOnServerResponse> powerOnServerWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = powerOnServerValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServerActionsPowerOnServerResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call powerOnServerAsync(Long id, final ApiCallback<ServerActionsPowerOnServerResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = powerOnServerValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsPowerOnServerResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class PowerOnServerRequestBuilder {
        private final Long id;

        private PowerOnServerRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for powerOnServer
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return powerOnServerCall(id, _callback);
        }


        /**
         * Execute powerOnServer request
         * @return ServerActionsPowerOnServerResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsPowerOnServerResponse execute() throws ApiException {
            ApiResponse<ServerActionsPowerOnServerResponse> localVarResp = powerOnServerWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute powerOnServer request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsPowerOnServerResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsPowerOnServerResponse> executeWithHttpInfo() throws ApiException {
            return powerOnServerWithHttpInfo(id);
        }

        /**
         * Execute powerOnServer request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsPowerOnServerResponse> _callback) throws ApiException {
            return powerOnServerAsync(id, _callback);
        }
    }

    /**
     * Power on a Server
     * Starts a Server by turning its power on.
     * @param id ID of the Server (required)
     * @return PowerOnServerRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public PowerOnServerRequestBuilder powerOnServer(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new PowerOnServerRequestBuilder(id);
    }
    private okhttp3.Call rebuildServerFromImageCall(Long id, ServerActionsRebuildServerFromImageRequest serverActionsRebuildServerFromImageRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = serverActionsRebuildServerFromImageRequest;

        // create path and map variables
        String localVarPath = "/servers/{id}/actions/rebuild"
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
    private okhttp3.Call rebuildServerFromImageValidateBeforeCall(Long id, ServerActionsRebuildServerFromImageRequest serverActionsRebuildServerFromImageRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling rebuildServerFromImage(Async)");
        }

        return rebuildServerFromImageCall(id, serverActionsRebuildServerFromImageRequest, _callback);

    }


    private ApiResponse<ServerActionsRebuildServerFromImageResponse> rebuildServerFromImageWithHttpInfo(Long id, ServerActionsRebuildServerFromImageRequest serverActionsRebuildServerFromImageRequest) throws ApiException {
        okhttp3.Call localVarCall = rebuildServerFromImageValidateBeforeCall(id, serverActionsRebuildServerFromImageRequest, null);
        Type localVarReturnType = new TypeToken<ServerActionsRebuildServerFromImageResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call rebuildServerFromImageAsync(Long id, ServerActionsRebuildServerFromImageRequest serverActionsRebuildServerFromImageRequest, final ApiCallback<ServerActionsRebuildServerFromImageResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = rebuildServerFromImageValidateBeforeCall(id, serverActionsRebuildServerFromImageRequest, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsRebuildServerFromImageResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class RebuildServerFromImageRequestBuilder {
        private final String image;
        private final Long id;

        private RebuildServerFromImageRequestBuilder(String image, Long id) {
            this.image = image;
            this.id = id;
        }

        /**
         * Build call for rebuildServerFromImage
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            ServerActionsRebuildServerFromImageRequest serverActionsRebuildServerFromImageRequest = buildBodyParams();
            return rebuildServerFromImageCall(id, serverActionsRebuildServerFromImageRequest, _callback);
        }

        private ServerActionsRebuildServerFromImageRequest buildBodyParams() {
            ServerActionsRebuildServerFromImageRequest serverActionsRebuildServerFromImageRequest = new ServerActionsRebuildServerFromImageRequest();
            serverActionsRebuildServerFromImageRequest.image(this.image);
            return serverActionsRebuildServerFromImageRequest;
        }

        /**
         * Execute rebuildServerFromImage request
         * @return ServerActionsRebuildServerFromImageResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsRebuildServerFromImageResponse execute() throws ApiException {
            ServerActionsRebuildServerFromImageRequest serverActionsRebuildServerFromImageRequest = buildBodyParams();
            ApiResponse<ServerActionsRebuildServerFromImageResponse> localVarResp = rebuildServerFromImageWithHttpInfo(id, serverActionsRebuildServerFromImageRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute rebuildServerFromImage request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsRebuildServerFromImageResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsRebuildServerFromImageResponse> executeWithHttpInfo() throws ApiException {
            ServerActionsRebuildServerFromImageRequest serverActionsRebuildServerFromImageRequest = buildBodyParams();
            return rebuildServerFromImageWithHttpInfo(id, serverActionsRebuildServerFromImageRequest);
        }

        /**
         * Execute rebuildServerFromImage request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsRebuildServerFromImageResponse> _callback) throws ApiException {
            ServerActionsRebuildServerFromImageRequest serverActionsRebuildServerFromImageRequest = buildBodyParams();
            return rebuildServerFromImageAsync(id, serverActionsRebuildServerFromImageRequest, _callback);
        }
    }

    /**
     * Rebuild a Server from an Image
     * Rebuilds a Server overwriting its disk with the content of an Image, thereby **destroying all data** on the target Server  The Image can either be one you have created earlier (&#x60;backup&#x60; or &#x60;snapshot&#x60; Image) or it can be a completely fresh &#x60;system&#x60; Image provided by us. You can get a list of all available Images with &#x60;GET /images&#x60;.  Your Server will automatically be powered off before the rebuild command executes. 
     * @param id ID of the Server (required)
     * @return RebuildServerFromImageRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public RebuildServerFromImageRequestBuilder rebuildServerFromImage(String image, Long id) throws IllegalArgumentException {
        if (image == null) throw new IllegalArgumentException("\"image\" is required but got null");
            

        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new RebuildServerFromImageRequestBuilder(image, id);
    }
    private okhttp3.Call removeFromPlacementGroupCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/{id}/actions/remove_from_placement_group"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call removeFromPlacementGroupValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling removeFromPlacementGroup(Async)");
        }

        return removeFromPlacementGroupCall(id, _callback);

    }


    private ApiResponse<ServerActionsRemoveFromPlacementGroupResponse> removeFromPlacementGroupWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = removeFromPlacementGroupValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServerActionsRemoveFromPlacementGroupResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call removeFromPlacementGroupAsync(Long id, final ApiCallback<ServerActionsRemoveFromPlacementGroupResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = removeFromPlacementGroupValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsRemoveFromPlacementGroupResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class RemoveFromPlacementGroupRequestBuilder {
        private final Long id;

        private RemoveFromPlacementGroupRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for removeFromPlacementGroup
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return removeFromPlacementGroupCall(id, _callback);
        }


        /**
         * Execute removeFromPlacementGroup request
         * @return ServerActionsRemoveFromPlacementGroupResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsRemoveFromPlacementGroupResponse execute() throws ApiException {
            ApiResponse<ServerActionsRemoveFromPlacementGroupResponse> localVarResp = removeFromPlacementGroupWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute removeFromPlacementGroup request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsRemoveFromPlacementGroupResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsRemoveFromPlacementGroupResponse> executeWithHttpInfo() throws ApiException {
            return removeFromPlacementGroupWithHttpInfo(id);
        }

        /**
         * Execute removeFromPlacementGroup request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsRemoveFromPlacementGroupResponse> _callback) throws ApiException {
            return removeFromPlacementGroupAsync(id, _callback);
        }
    }

    /**
     * Remove from Placement Group
     * Removes a Server from a Placement Group. 
     * @param id ID of the Server (required)
     * @return RemoveFromPlacementGroupRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public RemoveFromPlacementGroupRequestBuilder removeFromPlacementGroup(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new RemoveFromPlacementGroupRequestBuilder(id);
    }
    private okhttp3.Call requestConsoleCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/{id}/actions/request_console"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call requestConsoleValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling requestConsole(Async)");
        }

        return requestConsoleCall(id, _callback);

    }


    private ApiResponse<ServerActionsRequestConsoleResponse> requestConsoleWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = requestConsoleValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServerActionsRequestConsoleResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call requestConsoleAsync(Long id, final ApiCallback<ServerActionsRequestConsoleResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = requestConsoleValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsRequestConsoleResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class RequestConsoleRequestBuilder {
        private final Long id;

        private RequestConsoleRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for requestConsole
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return requestConsoleCall(id, _callback);
        }


        /**
         * Execute requestConsole request
         * @return ServerActionsRequestConsoleResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsRequestConsoleResponse execute() throws ApiException {
            ApiResponse<ServerActionsRequestConsoleResponse> localVarResp = requestConsoleWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute requestConsole request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsRequestConsoleResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsRequestConsoleResponse> executeWithHttpInfo() throws ApiException {
            return requestConsoleWithHttpInfo(id);
        }

        /**
         * Execute requestConsole request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsRequestConsoleResponse> _callback) throws ApiException {
            return requestConsoleAsync(id, _callback);
        }
    }

    /**
     * Request Console for a Server
     * Requests credentials for remote access via VNC over websocket to keyboard, monitor, and mouse for a Server. The provided URL is valid for 1 minute, after this period a new url needs to be created to connect to the Server. How long the connection is open after the initial connect is not subject to this timeout.
     * @param id ID of the Server (required)
     * @return RequestConsoleRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public RequestConsoleRequestBuilder requestConsole(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new RequestConsoleRequestBuilder(id);
    }
    private okhttp3.Call resetServerCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/{id}/actions/reset"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call resetServerValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling resetServer(Async)");
        }

        return resetServerCall(id, _callback);

    }


    private ApiResponse<ServerActionsResetServerResponse> resetServerWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = resetServerValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServerActionsResetServerResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call resetServerAsync(Long id, final ApiCallback<ServerActionsResetServerResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = resetServerValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsResetServerResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class ResetServerRequestBuilder {
        private final Long id;

        private ResetServerRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for resetServer
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return resetServerCall(id, _callback);
        }


        /**
         * Execute resetServer request
         * @return ServerActionsResetServerResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsResetServerResponse execute() throws ApiException {
            ApiResponse<ServerActionsResetServerResponse> localVarResp = resetServerWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute resetServer request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsResetServerResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsResetServerResponse> executeWithHttpInfo() throws ApiException {
            return resetServerWithHttpInfo(id);
        }

        /**
         * Execute resetServer request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsResetServerResponse> _callback) throws ApiException {
            return resetServerAsync(id, _callback);
        }
    }

    /**
     * Reset a Server
     * Cuts power to a Server and starts it again. This forcefully stops it without giving the Server operating system time to gracefully stop. This may lead to data loss, it’s equivalent to pulling the power cord and plugging it in again. Reset should only be used when reboot does not work.
     * @param id ID of the Server (required)
     * @return ResetServerRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public ResetServerRequestBuilder resetServer(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new ResetServerRequestBuilder(id);
    }
    private okhttp3.Call resetServerPasswordCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/{id}/actions/reset_password"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call resetServerPasswordValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling resetServerPassword(Async)");
        }

        return resetServerPasswordCall(id, _callback);

    }


    private ApiResponse<ServerActionsResetServerPasswordResponse> resetServerPasswordWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = resetServerPasswordValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServerActionsResetServerPasswordResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call resetServerPasswordAsync(Long id, final ApiCallback<ServerActionsResetServerPasswordResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = resetServerPasswordValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsResetServerPasswordResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class ResetServerPasswordRequestBuilder {
        private final Long id;

        private ResetServerPasswordRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for resetServerPassword
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;root_password&#x60; key in the reply contains the new root password that will be active if the Action succeeds.  The &#x60;action&#x60; key in the reply contains an Action object with this structure:  </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return resetServerPasswordCall(id, _callback);
        }


        /**
         * Execute resetServerPassword request
         * @return ServerActionsResetServerPasswordResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;root_password&#x60; key in the reply contains the new root password that will be active if the Action succeeds.  The &#x60;action&#x60; key in the reply contains an Action object with this structure:  </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsResetServerPasswordResponse execute() throws ApiException {
            ApiResponse<ServerActionsResetServerPasswordResponse> localVarResp = resetServerPasswordWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute resetServerPassword request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsResetServerPasswordResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;root_password&#x60; key in the reply contains the new root password that will be active if the Action succeeds.  The &#x60;action&#x60; key in the reply contains an Action object with this structure:  </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsResetServerPasswordResponse> executeWithHttpInfo() throws ApiException {
            return resetServerPasswordWithHttpInfo(id);
        }

        /**
         * Execute resetServerPassword request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;root_password&#x60; key in the reply contains the new root password that will be active if the Action succeeds.  The &#x60;action&#x60; key in the reply contains an Action object with this structure:  </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsResetServerPasswordResponse> _callback) throws ApiException {
            return resetServerPasswordAsync(id, _callback);
        }
    }

    /**
     * Reset root Password of a Server
     * Resets the root password. Only works for Linux systems that are running the qemu guest agent. Server must be powered on (status &#x60;running&#x60;) in order for this operation to succeed.  This will generate a new password for this Server and return it.  If this does not succeed you can use the rescue system to netboot the Server and manually change your Server password by hand. 
     * @param id ID of the Server (required)
     * @return ResetServerPasswordRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;root_password&#x60; key in the reply contains the new root password that will be active if the Action succeeds.  The &#x60;action&#x60; key in the reply contains an Action object with this structure:  </td><td>  -  </td></tr>
     </table>
     */
    public ResetServerPasswordRequestBuilder resetServerPassword(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new ResetServerPasswordRequestBuilder(id);
    }
    private okhttp3.Call softRebootServerCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/servers/{id}/actions/reboot"
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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call softRebootServerValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling softRebootServer(Async)");
        }

        return softRebootServerCall(id, _callback);

    }


    private ApiResponse<ServerActionsSoftRebootServerResponse> softRebootServerWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = softRebootServerValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<ServerActionsSoftRebootServerResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call softRebootServerAsync(Long id, final ApiCallback<ServerActionsSoftRebootServerResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = softRebootServerValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<ServerActionsSoftRebootServerResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class SoftRebootServerRequestBuilder {
        private final Long id;

        private SoftRebootServerRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for softRebootServer
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return softRebootServerCall(id, _callback);
        }


        /**
         * Execute softRebootServer request
         * @return ServerActionsSoftRebootServerResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ServerActionsSoftRebootServerResponse execute() throws ApiException {
            ApiResponse<ServerActionsSoftRebootServerResponse> localVarResp = softRebootServerWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute softRebootServer request with HTTP info returned
         * @return ApiResponse&lt;ServerActionsSoftRebootServerResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<ServerActionsSoftRebootServerResponse> executeWithHttpInfo() throws ApiException {
            return softRebootServerWithHttpInfo(id);
        }

        /**
         * Execute softRebootServer request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<ServerActionsSoftRebootServerResponse> _callback) throws ApiException {
            return softRebootServerAsync(id, _callback);
        }
    }

    /**
     * Soft-reboot a Server
     * Reboots a Server gracefully by sending an ACPI request. The Server operating system must support ACPI and react to the request, otherwise the Server will not reboot.
     * @param id ID of the Server (required)
     * @return SoftRebootServerRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public SoftRebootServerRequestBuilder softRebootServer(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new SoftRebootServerRequestBuilder(id);
    }
}
