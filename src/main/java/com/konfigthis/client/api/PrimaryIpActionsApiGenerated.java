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


import com.konfigthis.client.model.PrimaryIpActionsAssignPrimaryIpToResourceRequest;
import com.konfigthis.client.model.PrimaryIpActionsAssignPrimaryIpToResourceResponse;
import com.konfigthis.client.model.PrimaryIpActionsChangeDnsPtrRequest;
import com.konfigthis.client.model.PrimaryIpActionsChangeDnsPtrResponse;
import com.konfigthis.client.model.PrimaryIpActionsChangeProtectionPrimaryIpRequest;
import com.konfigthis.client.model.PrimaryIpActionsChangeProtectionPrimaryIpResponse;
import com.konfigthis.client.model.PrimaryIpActionsGetActionByIdResponse;
import com.konfigthis.client.model.PrimaryIpActionsGetAllActionsResponse;
import com.konfigthis.client.model.PrimaryIpActionsUnassignPrimaryIpResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

public class PrimaryIpActionsApiGenerated {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public PrimaryIpActionsApiGenerated() throws IllegalArgumentException {
        this(Configuration.getDefaultApiClient());
    }

    public PrimaryIpActionsApiGenerated(ApiClient apiClient) throws IllegalArgumentException {
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

    private okhttp3.Call assignPrimaryIpToResourceCall(Long id, PrimaryIpActionsAssignPrimaryIpToResourceRequest primaryIpActionsAssignPrimaryIpToResourceRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = primaryIpActionsAssignPrimaryIpToResourceRequest;

        // create path and map variables
        String localVarPath = "/primary_ips/{id}/actions/assign"
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
    private okhttp3.Call assignPrimaryIpToResourceValidateBeforeCall(Long id, PrimaryIpActionsAssignPrimaryIpToResourceRequest primaryIpActionsAssignPrimaryIpToResourceRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling assignPrimaryIpToResource(Async)");
        }

        return assignPrimaryIpToResourceCall(id, primaryIpActionsAssignPrimaryIpToResourceRequest, _callback);

    }


    private ApiResponse<PrimaryIpActionsAssignPrimaryIpToResourceResponse> assignPrimaryIpToResourceWithHttpInfo(Long id, PrimaryIpActionsAssignPrimaryIpToResourceRequest primaryIpActionsAssignPrimaryIpToResourceRequest) throws ApiException {
        okhttp3.Call localVarCall = assignPrimaryIpToResourceValidateBeforeCall(id, primaryIpActionsAssignPrimaryIpToResourceRequest, null);
        Type localVarReturnType = new TypeToken<PrimaryIpActionsAssignPrimaryIpToResourceResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call assignPrimaryIpToResourceAsync(Long id, PrimaryIpActionsAssignPrimaryIpToResourceRequest primaryIpActionsAssignPrimaryIpToResourceRequest, final ApiCallback<PrimaryIpActionsAssignPrimaryIpToResourceResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = assignPrimaryIpToResourceValidateBeforeCall(id, primaryIpActionsAssignPrimaryIpToResourceRequest, _callback);
        Type localVarReturnType = new TypeToken<PrimaryIpActionsAssignPrimaryIpToResourceResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class AssignPrimaryIpToResourceRequestBuilder {
        private final Long assigneeId;
        private final String assigneeType;
        private final Long id;

        private AssignPrimaryIpToResourceRequestBuilder(Long assigneeId, String assigneeType, Long id) {
            this.assigneeId = assigneeId;
            this.assigneeType = assigneeType;
            this.id = id;
        }

        /**
         * Build call for assignPrimaryIpToResource
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
            PrimaryIpActionsAssignPrimaryIpToResourceRequest primaryIpActionsAssignPrimaryIpToResourceRequest = buildBodyParams();
            return assignPrimaryIpToResourceCall(id, primaryIpActionsAssignPrimaryIpToResourceRequest, _callback);
        }

        private PrimaryIpActionsAssignPrimaryIpToResourceRequest buildBodyParams() {
            PrimaryIpActionsAssignPrimaryIpToResourceRequest primaryIpActionsAssignPrimaryIpToResourceRequest = new PrimaryIpActionsAssignPrimaryIpToResourceRequest();
            primaryIpActionsAssignPrimaryIpToResourceRequest.assigneeId(this.assigneeId);
            if (this.assigneeType != null)
            primaryIpActionsAssignPrimaryIpToResourceRequest.assigneeType(PrimaryIpActionsAssignPrimaryIpToResourceRequest.AssigneeTypeEnum.fromValue(this.assigneeType));
            return primaryIpActionsAssignPrimaryIpToResourceRequest;
        }

        /**
         * Execute assignPrimaryIpToResource request
         * @return PrimaryIpActionsAssignPrimaryIpToResourceResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public PrimaryIpActionsAssignPrimaryIpToResourceResponse execute() throws ApiException {
            PrimaryIpActionsAssignPrimaryIpToResourceRequest primaryIpActionsAssignPrimaryIpToResourceRequest = buildBodyParams();
            ApiResponse<PrimaryIpActionsAssignPrimaryIpToResourceResponse> localVarResp = assignPrimaryIpToResourceWithHttpInfo(id, primaryIpActionsAssignPrimaryIpToResourceRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute assignPrimaryIpToResource request with HTTP info returned
         * @return ApiResponse&lt;PrimaryIpActionsAssignPrimaryIpToResourceResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<PrimaryIpActionsAssignPrimaryIpToResourceResponse> executeWithHttpInfo() throws ApiException {
            PrimaryIpActionsAssignPrimaryIpToResourceRequest primaryIpActionsAssignPrimaryIpToResourceRequest = buildBodyParams();
            return assignPrimaryIpToResourceWithHttpInfo(id, primaryIpActionsAssignPrimaryIpToResourceRequest);
        }

        /**
         * Execute assignPrimaryIpToResource request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<PrimaryIpActionsAssignPrimaryIpToResourceResponse> _callback) throws ApiException {
            PrimaryIpActionsAssignPrimaryIpToResourceRequest primaryIpActionsAssignPrimaryIpToResourceRequest = buildBodyParams();
            return assignPrimaryIpToResourceAsync(id, primaryIpActionsAssignPrimaryIpToResourceRequest, _callback);
        }
    }

    /**
     * Assign a Primary IP to a resource
     * Assigns a Primary IP to a Server.  A Server can only have one Primary IP of type &#x60;ipv4&#x60; and one of type &#x60;ipv6&#x60; assigned. If you need more IPs use Floating IPs.  The Server must be powered off (status &#x60;off&#x60;) in order for this operation to succeed.  #### Call specific error codes  | Code                          | Description                                                   | |------------------------------ |-------------------------------------------------------------- | | &#x60;server_not_stopped&#x60;          | The server is running, but needs to be powered off            | | &#x60;primary_ip_already_assigned&#x60; | Primary ip is already assigned to a different server          | | &#x60;server_has_ipv4&#x60;             | The server already has an ipv4 address                        | | &#x60;server_has_ipv6&#x60;             | The server already has an ipv6 address                        | 
     * @param id ID of the Primary IP (required)
     * @return AssignPrimaryIpToResourceRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public AssignPrimaryIpToResourceRequestBuilder assignPrimaryIpToResource(Long assigneeId, String assigneeType, Long id) throws IllegalArgumentException {
        if (assigneeId == null) throw new IllegalArgumentException("\"assigneeId\" is required but got null");
        if (assigneeType == null) throw new IllegalArgumentException("\"assigneeType\" is required but got null");
            

        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new AssignPrimaryIpToResourceRequestBuilder(assigneeId, assigneeType, id);
    }
    private okhttp3.Call changeDnsPtrCall(Long id, PrimaryIpActionsChangeDnsPtrRequest primaryIpActionsChangeDnsPtrRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = primaryIpActionsChangeDnsPtrRequest;

        // create path and map variables
        String localVarPath = "/primary_ips/{id}/actions/change_dns_ptr"
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
    private okhttp3.Call changeDnsPtrValidateBeforeCall(Long id, PrimaryIpActionsChangeDnsPtrRequest primaryIpActionsChangeDnsPtrRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling changeDnsPtr(Async)");
        }

        return changeDnsPtrCall(id, primaryIpActionsChangeDnsPtrRequest, _callback);

    }


    private ApiResponse<PrimaryIpActionsChangeDnsPtrResponse> changeDnsPtrWithHttpInfo(Long id, PrimaryIpActionsChangeDnsPtrRequest primaryIpActionsChangeDnsPtrRequest) throws ApiException {
        okhttp3.Call localVarCall = changeDnsPtrValidateBeforeCall(id, primaryIpActionsChangeDnsPtrRequest, null);
        Type localVarReturnType = new TypeToken<PrimaryIpActionsChangeDnsPtrResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call changeDnsPtrAsync(Long id, PrimaryIpActionsChangeDnsPtrRequest primaryIpActionsChangeDnsPtrRequest, final ApiCallback<PrimaryIpActionsChangeDnsPtrResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = changeDnsPtrValidateBeforeCall(id, primaryIpActionsChangeDnsPtrRequest, _callback);
        Type localVarReturnType = new TypeToken<PrimaryIpActionsChangeDnsPtrResponse>(){}.getType();
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
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_dns_ptr&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            PrimaryIpActionsChangeDnsPtrRequest primaryIpActionsChangeDnsPtrRequest = buildBodyParams();
            return changeDnsPtrCall(id, primaryIpActionsChangeDnsPtrRequest, _callback);
        }

        private PrimaryIpActionsChangeDnsPtrRequest buildBodyParams() {
            PrimaryIpActionsChangeDnsPtrRequest primaryIpActionsChangeDnsPtrRequest = new PrimaryIpActionsChangeDnsPtrRequest();
            primaryIpActionsChangeDnsPtrRequest.dnsPtr(this.dnsPtr);
            primaryIpActionsChangeDnsPtrRequest.ip(this.ip);
            return primaryIpActionsChangeDnsPtrRequest;
        }

        /**
         * Execute changeDnsPtr request
         * @return PrimaryIpActionsChangeDnsPtrResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_dns_ptr&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public PrimaryIpActionsChangeDnsPtrResponse execute() throws ApiException {
            PrimaryIpActionsChangeDnsPtrRequest primaryIpActionsChangeDnsPtrRequest = buildBodyParams();
            ApiResponse<PrimaryIpActionsChangeDnsPtrResponse> localVarResp = changeDnsPtrWithHttpInfo(id, primaryIpActionsChangeDnsPtrRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute changeDnsPtr request with HTTP info returned
         * @return ApiResponse&lt;PrimaryIpActionsChangeDnsPtrResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_dns_ptr&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<PrimaryIpActionsChangeDnsPtrResponse> executeWithHttpInfo() throws ApiException {
            PrimaryIpActionsChangeDnsPtrRequest primaryIpActionsChangeDnsPtrRequest = buildBodyParams();
            return changeDnsPtrWithHttpInfo(id, primaryIpActionsChangeDnsPtrRequest);
        }

        /**
         * Execute changeDnsPtr request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_dns_ptr&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<PrimaryIpActionsChangeDnsPtrResponse> _callback) throws ApiException {
            PrimaryIpActionsChangeDnsPtrRequest primaryIpActionsChangeDnsPtrRequest = buildBodyParams();
            return changeDnsPtrAsync(id, primaryIpActionsChangeDnsPtrRequest, _callback);
        }
    }

    /**
     * Change reverse DNS entry for a Primary IP
     * Changes the hostname that will appear when getting the hostname belonging to this Primary IP.
     * @param id ID of the Primary IP (required)
     * @return ChangeDnsPtrRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_dns_ptr&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public ChangeDnsPtrRequestBuilder changeDnsPtr(String dnsPtr, String ip, Long id) throws IllegalArgumentException {
        
            

        if (ip == null) throw new IllegalArgumentException("\"ip\" is required but got null");
            

        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new ChangeDnsPtrRequestBuilder(dnsPtr, ip, id);
    }
    private okhttp3.Call changeProtectionPrimaryIpCall(Long id, PrimaryIpActionsChangeProtectionPrimaryIpRequest primaryIpActionsChangeProtectionPrimaryIpRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = primaryIpActionsChangeProtectionPrimaryIpRequest;

        // create path and map variables
        String localVarPath = "/primary_ips/{id}/actions/change_protection"
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
    private okhttp3.Call changeProtectionPrimaryIpValidateBeforeCall(Long id, PrimaryIpActionsChangeProtectionPrimaryIpRequest primaryIpActionsChangeProtectionPrimaryIpRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling changeProtectionPrimaryIp(Async)");
        }

        return changeProtectionPrimaryIpCall(id, primaryIpActionsChangeProtectionPrimaryIpRequest, _callback);

    }


    private ApiResponse<PrimaryIpActionsChangeProtectionPrimaryIpResponse> changeProtectionPrimaryIpWithHttpInfo(Long id, PrimaryIpActionsChangeProtectionPrimaryIpRequest primaryIpActionsChangeProtectionPrimaryIpRequest) throws ApiException {
        okhttp3.Call localVarCall = changeProtectionPrimaryIpValidateBeforeCall(id, primaryIpActionsChangeProtectionPrimaryIpRequest, null);
        Type localVarReturnType = new TypeToken<PrimaryIpActionsChangeProtectionPrimaryIpResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call changeProtectionPrimaryIpAsync(Long id, PrimaryIpActionsChangeProtectionPrimaryIpRequest primaryIpActionsChangeProtectionPrimaryIpRequest, final ApiCallback<PrimaryIpActionsChangeProtectionPrimaryIpResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = changeProtectionPrimaryIpValidateBeforeCall(id, primaryIpActionsChangeProtectionPrimaryIpRequest, _callback);
        Type localVarReturnType = new TypeToken<PrimaryIpActionsChangeProtectionPrimaryIpResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class ChangeProtectionPrimaryIpRequestBuilder {
        private final Long id;
        private Boolean delete;

        private ChangeProtectionPrimaryIpRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Set delete
         * @param delete If true, prevents the Primary IP from being deleted (optional)
         * @return ChangeProtectionPrimaryIpRequestBuilder
         */
        public ChangeProtectionPrimaryIpRequestBuilder delete(Boolean delete) {
            this.delete = delete;
            return this;
        }
        
        /**
         * Build call for changeProtectionPrimaryIp
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
            PrimaryIpActionsChangeProtectionPrimaryIpRequest primaryIpActionsChangeProtectionPrimaryIpRequest = buildBodyParams();
            return changeProtectionPrimaryIpCall(id, primaryIpActionsChangeProtectionPrimaryIpRequest, _callback);
        }

        private PrimaryIpActionsChangeProtectionPrimaryIpRequest buildBodyParams() {
            PrimaryIpActionsChangeProtectionPrimaryIpRequest primaryIpActionsChangeProtectionPrimaryIpRequest = new PrimaryIpActionsChangeProtectionPrimaryIpRequest();
            primaryIpActionsChangeProtectionPrimaryIpRequest.delete(this.delete);
            return primaryIpActionsChangeProtectionPrimaryIpRequest;
        }

        /**
         * Execute changeProtectionPrimaryIp request
         * @return PrimaryIpActionsChangeProtectionPrimaryIpResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_protection&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public PrimaryIpActionsChangeProtectionPrimaryIpResponse execute() throws ApiException {
            PrimaryIpActionsChangeProtectionPrimaryIpRequest primaryIpActionsChangeProtectionPrimaryIpRequest = buildBodyParams();
            ApiResponse<PrimaryIpActionsChangeProtectionPrimaryIpResponse> localVarResp = changeProtectionPrimaryIpWithHttpInfo(id, primaryIpActionsChangeProtectionPrimaryIpRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute changeProtectionPrimaryIp request with HTTP info returned
         * @return ApiResponse&lt;PrimaryIpActionsChangeProtectionPrimaryIpResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_protection&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<PrimaryIpActionsChangeProtectionPrimaryIpResponse> executeWithHttpInfo() throws ApiException {
            PrimaryIpActionsChangeProtectionPrimaryIpRequest primaryIpActionsChangeProtectionPrimaryIpRequest = buildBodyParams();
            return changeProtectionPrimaryIpWithHttpInfo(id, primaryIpActionsChangeProtectionPrimaryIpRequest);
        }

        /**
         * Execute changeProtectionPrimaryIp request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_protection&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<PrimaryIpActionsChangeProtectionPrimaryIpResponse> _callback) throws ApiException {
            PrimaryIpActionsChangeProtectionPrimaryIpRequest primaryIpActionsChangeProtectionPrimaryIpRequest = buildBodyParams();
            return changeProtectionPrimaryIpAsync(id, primaryIpActionsChangeProtectionPrimaryIpRequest, _callback);
        }
    }

    /**
     * Change Primary IP Protection
     * Changes the protection configuration of a Primary IP.  A Primary IP can only be delete protected if its &#x60;auto_delete&#x60; property is set to &#x60;false&#x60;. 
     * @param id ID of the Primary IP (required)
     * @return ChangeProtectionPrimaryIpRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_protection&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public ChangeProtectionPrimaryIpRequestBuilder changeProtectionPrimaryIp(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new ChangeProtectionPrimaryIpRequestBuilder(id);
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
        String localVarPath = "/primary_ips/actions/{id}"
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


    private ApiResponse<PrimaryIpActionsGetActionByIdResponse> getActionByIdWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = getActionByIdValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<PrimaryIpActionsGetActionByIdResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getActionByIdAsync(Long id, final ApiCallback<PrimaryIpActionsGetActionByIdResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getActionByIdValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<PrimaryIpActionsGetActionByIdResponse>(){}.getType();
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
         * @return PrimaryIpActionsGetActionByIdResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public PrimaryIpActionsGetActionByIdResponse execute() throws ApiException {
            ApiResponse<PrimaryIpActionsGetActionByIdResponse> localVarResp = getActionByIdWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getActionById request with HTTP info returned
         * @return ApiResponse&lt;PrimaryIpActionsGetActionByIdResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<PrimaryIpActionsGetActionByIdResponse> executeWithHttpInfo() throws ApiException {
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
        public okhttp3.Call executeAsync(final ApiCallback<PrimaryIpActionsGetActionByIdResponse> _callback) throws ApiException {
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
        String localVarPath = "/primary_ips/actions";

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


    private ApiResponse<PrimaryIpActionsGetAllActionsResponse> getAllActionsWithHttpInfo(Long id, String sort, String status, Long page, Long perPage) throws ApiException {
        okhttp3.Call localVarCall = getAllActionsValidateBeforeCall(id, sort, status, page, perPage, null);
        Type localVarReturnType = new TypeToken<PrimaryIpActionsGetAllActionsResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getAllActionsAsync(Long id, String sort, String status, Long page, Long perPage, final ApiCallback<PrimaryIpActionsGetAllActionsResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAllActionsValidateBeforeCall(id, sort, status, page, perPage, _callback);
        Type localVarReturnType = new TypeToken<PrimaryIpActionsGetAllActionsResponse>(){}.getType();
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
         * @return PrimaryIpActionsGetAllActionsResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public PrimaryIpActionsGetAllActionsResponse execute() throws ApiException {
            ApiResponse<PrimaryIpActionsGetAllActionsResponse> localVarResp = getAllActionsWithHttpInfo(id, sort, status, page, perPage);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getAllActions request with HTTP info returned
         * @return ApiResponse&lt;PrimaryIpActionsGetAllActionsResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<PrimaryIpActionsGetAllActionsResponse> executeWithHttpInfo() throws ApiException {
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
        public okhttp3.Call executeAsync(final ApiCallback<PrimaryIpActionsGetAllActionsResponse> _callback) throws ApiException {
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
    private okhttp3.Call unassignPrimaryIpCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/primary_ips/{id}/actions/unassign"
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
    private okhttp3.Call unassignPrimaryIpValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling unassignPrimaryIp(Async)");
        }

        return unassignPrimaryIpCall(id, _callback);

    }


    private ApiResponse<PrimaryIpActionsUnassignPrimaryIpResponse> unassignPrimaryIpWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = unassignPrimaryIpValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<PrimaryIpActionsUnassignPrimaryIpResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call unassignPrimaryIpAsync(Long id, final ApiCallback<PrimaryIpActionsUnassignPrimaryIpResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = unassignPrimaryIpValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<PrimaryIpActionsUnassignPrimaryIpResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class UnassignPrimaryIpRequestBuilder {
        private final Long id;

        private UnassignPrimaryIpRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for unassignPrimaryIp
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
            return unassignPrimaryIpCall(id, _callback);
        }


        /**
         * Execute unassignPrimaryIp request
         * @return PrimaryIpActionsUnassignPrimaryIpResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public PrimaryIpActionsUnassignPrimaryIpResponse execute() throws ApiException {
            ApiResponse<PrimaryIpActionsUnassignPrimaryIpResponse> localVarResp = unassignPrimaryIpWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute unassignPrimaryIp request with HTTP info returned
         * @return ApiResponse&lt;PrimaryIpActionsUnassignPrimaryIpResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<PrimaryIpActionsUnassignPrimaryIpResponse> executeWithHttpInfo() throws ApiException {
            return unassignPrimaryIpWithHttpInfo(id);
        }

        /**
         * Execute unassignPrimaryIp request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<PrimaryIpActionsUnassignPrimaryIpResponse> _callback) throws ApiException {
            return unassignPrimaryIpAsync(id, _callback);
        }
    }

    /**
     * Unassign a Primary IP from a resource
     * Unassigns a Primary IP from a Server.  The Server must be powered off (status &#x60;off&#x60;) in order for this operation to succeed.  Note that only Servers that have at least one network interface (public or private) attached can be powered on.  #### Call specific error codes  | Code                              | Description                                                   | |---------------------------------- |-------------------------------------------------------------- | | &#x60;server_not_stopped&#x60;              | The server is running, but needs to be powered off            | | &#x60;server_is_load_balancer_target&#x60;  | The server ipv4 address is a loadbalancer target              | 
     * @param id ID of the Primary IP (required)
     * @return UnassignPrimaryIpRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public UnassignPrimaryIpRequestBuilder unassignPrimaryIp(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new UnassignPrimaryIpRequestBuilder(id);
    }
}
