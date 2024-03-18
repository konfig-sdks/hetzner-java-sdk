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


import com.konfigthis.client.model.PrimaryIPsCreateOrUpdateRequest;
import com.konfigthis.client.model.PrimaryIPsCreateOrUpdateResponse;
import com.konfigthis.client.model.PrimaryIPsGetAllResponse;
import com.konfigthis.client.model.PrimaryIPsGetByIdResponse;
import com.konfigthis.client.model.PrimaryIPsUpdateIpLabelsRequest;
import com.konfigthis.client.model.PrimaryIPsUpdateIpLabelsResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

public class PrimaryIpsApiGenerated {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public PrimaryIpsApiGenerated() throws IllegalArgumentException {
        this(Configuration.getDefaultApiClient());
    }

    public PrimaryIpsApiGenerated(ApiClient apiClient) throws IllegalArgumentException {
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

    private okhttp3.Call createOrUpdateCall(PrimaryIPsCreateOrUpdateRequest primaryIPsCreateOrUpdateRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = primaryIPsCreateOrUpdateRequest;

        // create path and map variables
        String localVarPath = "/primary_ips";

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
    private okhttp3.Call createOrUpdateValidateBeforeCall(PrimaryIPsCreateOrUpdateRequest primaryIPsCreateOrUpdateRequest, final ApiCallback _callback) throws ApiException {
        return createOrUpdateCall(primaryIPsCreateOrUpdateRequest, _callback);

    }


    private ApiResponse<PrimaryIPsCreateOrUpdateResponse> createOrUpdateWithHttpInfo(PrimaryIPsCreateOrUpdateRequest primaryIPsCreateOrUpdateRequest) throws ApiException {
        okhttp3.Call localVarCall = createOrUpdateValidateBeforeCall(primaryIPsCreateOrUpdateRequest, null);
        Type localVarReturnType = new TypeToken<PrimaryIPsCreateOrUpdateResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call createOrUpdateAsync(PrimaryIPsCreateOrUpdateRequest primaryIPsCreateOrUpdateRequest, final ApiCallback<PrimaryIPsCreateOrUpdateResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = createOrUpdateValidateBeforeCall(primaryIPsCreateOrUpdateRequest, _callback);
        Type localVarReturnType = new TypeToken<PrimaryIPsCreateOrUpdateResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class CreateOrUpdateRequestBuilder {
        private final String assigneeType;
        private final String name;
        private final String type;
        private Long assigneeId;
        private Boolean autoDelete;
        private String datacenter;
        private Object labels;

        private CreateOrUpdateRequestBuilder(String assigneeType, String name, String type) {
            this.assigneeType = assigneeType;
            this.name = name;
            this.type = type;
        }

        /**
         * Set assigneeId
         * @param assigneeId ID of the resource the Primary IP should be assigned to. Omitted if it should not be assigned. (optional)
         * @return CreateOrUpdateRequestBuilder
         */
        public CreateOrUpdateRequestBuilder assigneeId(Long assigneeId) {
            this.assigneeId = assigneeId;
            return this;
        }
        
        /**
         * Set autoDelete
         * @param autoDelete Delete the Primary IP when the Server it is assigned to is deleted. (optional, default to false)
         * @return CreateOrUpdateRequestBuilder
         */
        public CreateOrUpdateRequestBuilder autoDelete(Boolean autoDelete) {
            this.autoDelete = autoDelete;
            return this;
        }
        
        /**
         * Set datacenter
         * @param datacenter ID or name of Datacenter the Primary IP will be bound to. Needs to be omitted if &#x60;assignee_id&#x60; is passed. (optional)
         * @return CreateOrUpdateRequestBuilder
         */
        public CreateOrUpdateRequestBuilder datacenter(String datacenter) {
            this.datacenter = datacenter;
            return this;
        }
        
        /**
         * Set labels
         * @param labels User-defined labels (key-value pairs) (optional)
         * @return CreateOrUpdateRequestBuilder
         */
        public CreateOrUpdateRequestBuilder labels(Object labels) {
            this.labels = labels;
            return this;
        }
        
        /**
         * Build call for createOrUpdate
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;primary_ip&#x60; key contains the Primary IP that was just created </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            PrimaryIPsCreateOrUpdateRequest primaryIPsCreateOrUpdateRequest = buildBodyParams();
            return createOrUpdateCall(primaryIPsCreateOrUpdateRequest, _callback);
        }

        private PrimaryIPsCreateOrUpdateRequest buildBodyParams() {
            PrimaryIPsCreateOrUpdateRequest primaryIPsCreateOrUpdateRequest = new PrimaryIPsCreateOrUpdateRequest();
            primaryIPsCreateOrUpdateRequest.assigneeId(this.assigneeId);
            if (this.assigneeType != null)
            primaryIPsCreateOrUpdateRequest.assigneeType(PrimaryIPsCreateOrUpdateRequest.AssigneeTypeEnum.fromValue(this.assigneeType));
            primaryIPsCreateOrUpdateRequest.autoDelete(this.autoDelete);
            primaryIPsCreateOrUpdateRequest.datacenter(this.datacenter);
            primaryIPsCreateOrUpdateRequest.labels(this.labels);
            primaryIPsCreateOrUpdateRequest.name(this.name);
            if (this.type != null)
            primaryIPsCreateOrUpdateRequest.type(PrimaryIPsCreateOrUpdateRequest.TypeEnum.fromValue(this.type));
            return primaryIPsCreateOrUpdateRequest;
        }

        /**
         * Execute createOrUpdate request
         * @return PrimaryIPsCreateOrUpdateResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;primary_ip&#x60; key contains the Primary IP that was just created </td><td>  -  </td></tr>
         </table>
         */
        public PrimaryIPsCreateOrUpdateResponse execute() throws ApiException {
            PrimaryIPsCreateOrUpdateRequest primaryIPsCreateOrUpdateRequest = buildBodyParams();
            ApiResponse<PrimaryIPsCreateOrUpdateResponse> localVarResp = createOrUpdateWithHttpInfo(primaryIPsCreateOrUpdateRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute createOrUpdate request with HTTP info returned
         * @return ApiResponse&lt;PrimaryIPsCreateOrUpdateResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;primary_ip&#x60; key contains the Primary IP that was just created </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<PrimaryIPsCreateOrUpdateResponse> executeWithHttpInfo() throws ApiException {
            PrimaryIPsCreateOrUpdateRequest primaryIPsCreateOrUpdateRequest = buildBodyParams();
            return createOrUpdateWithHttpInfo(primaryIPsCreateOrUpdateRequest);
        }

        /**
         * Execute createOrUpdate request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;primary_ip&#x60; key contains the Primary IP that was just created </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<PrimaryIPsCreateOrUpdateResponse> _callback) throws ApiException {
            PrimaryIPsCreateOrUpdateRequest primaryIPsCreateOrUpdateRequest = buildBodyParams();
            return createOrUpdateAsync(primaryIPsCreateOrUpdateRequest, _callback);
        }
    }

    /**
     * Create a Primary IP
     * Creates a new Primary IP, optionally assigned to a Server.  If you want to create a Primary IP that is not assigned to a Server, you need to provide the &#x60;datacenter&#x60; key instead of &#x60;assignee_id&#x60;. This can be either the ID or the name of the Datacenter this Primary IP shall be created in.  Note that a Primary IP can only be assigned to a Server in the same Datacenter later on.  #### Call specific error codes  | Code                          | Description                                                   | |------------------------------ |-------------------------------------------------------------- | | &#x60;server_not_stopped&#x60;          | The specified server is running, but needs to be powered off  | | &#x60;server_has_ipv4&#x60;             | The server already has an ipv4 address                        | | &#x60;server_has_ipv6&#x60;             | The server already has an ipv6 address                        | 
     * @return CreateOrUpdateRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;primary_ip&#x60; key contains the Primary IP that was just created </td><td>  -  </td></tr>
     </table>
     */
    public CreateOrUpdateRequestBuilder createOrUpdate(String assigneeType, String name, String type) throws IllegalArgumentException {
        if (assigneeType == null) throw new IllegalArgumentException("\"assigneeType\" is required but got null");
            

        if (name == null) throw new IllegalArgumentException("\"name\" is required but got null");
            

        if (type == null) throw new IllegalArgumentException("\"type\" is required but got null");
            

        return new CreateOrUpdateRequestBuilder(assigneeType, name, type);
    }
    private okhttp3.Call deletePrimaryIpCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/primary_ips/{id}"
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
    private okhttp3.Call deletePrimaryIpValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling deletePrimaryIp(Async)");
        }

        return deletePrimaryIpCall(id, _callback);

    }


    private ApiResponse<Void> deletePrimaryIpWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = deletePrimaryIpValidateBeforeCall(id, null);
        return localVarApiClient.execute(localVarCall);
    }

    private okhttp3.Call deletePrimaryIpAsync(Long id, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deletePrimaryIpValidateBeforeCall(id, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }

    public class DeletePrimaryIpRequestBuilder {
        private final Long id;

        private DeletePrimaryIpRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for deletePrimaryIp
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 204 </td><td> Primary IP deleted </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return deletePrimaryIpCall(id, _callback);
        }


        /**
         * Execute deletePrimaryIp request
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 204 </td><td> Primary IP deleted </td><td>  -  </td></tr>
         </table>
         */
        public void execute() throws ApiException {
            deletePrimaryIpWithHttpInfo(id);
        }

        /**
         * Execute deletePrimaryIp request with HTTP info returned
         * @return ApiResponse&lt;Void&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 204 </td><td> Primary IP deleted </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<Void> executeWithHttpInfo() throws ApiException {
            return deletePrimaryIpWithHttpInfo(id);
        }

        /**
         * Execute deletePrimaryIp request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 204 </td><td> Primary IP deleted </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<Void> _callback) throws ApiException {
            return deletePrimaryIpAsync(id, _callback);
        }
    }

    /**
     * Delete a Primary IP
     * Deletes a Primary IP.  The Primary IP may be assigned to a Server. In this case it is unassigned automatically. The Server must be powered off (status &#x60;off&#x60;) in order for this operation to succeed. 
     * @param id ID of the Resource. (required)
     * @return DeletePrimaryIpRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Primary IP deleted </td><td>  -  </td></tr>
     </table>
     */
    public DeletePrimaryIpRequestBuilder deletePrimaryIp(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new DeletePrimaryIpRequestBuilder(id);
    }
    private okhttp3.Call getAllCall(String name, String labelSelector, String ip, Long page, Long perPage, String sort, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/primary_ips";

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

        if (ip != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("ip", ip));
        }

        if (page != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("page", page));
        }

        if (perPage != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("per_page", perPage));
        }

        if (sort != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("sort", sort));
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
    private okhttp3.Call getAllValidateBeforeCall(String name, String labelSelector, String ip, Long page, Long perPage, String sort, final ApiCallback _callback) throws ApiException {
        return getAllCall(name, labelSelector, ip, page, perPage, sort, _callback);

    }


    private ApiResponse<PrimaryIPsGetAllResponse> getAllWithHttpInfo(String name, String labelSelector, String ip, Long page, Long perPage, String sort) throws ApiException {
        okhttp3.Call localVarCall = getAllValidateBeforeCall(name, labelSelector, ip, page, perPage, sort, null);
        Type localVarReturnType = new TypeToken<PrimaryIPsGetAllResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getAllAsync(String name, String labelSelector, String ip, Long page, Long perPage, String sort, final ApiCallback<PrimaryIPsGetAllResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAllValidateBeforeCall(name, labelSelector, ip, page, perPage, sort, _callback);
        Type localVarReturnType = new TypeToken<PrimaryIPsGetAllResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetAllRequestBuilder {
        private String name;
        private String labelSelector;
        private String ip;
        private Long page;
        private Long perPage;
        private String sort;

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
         * Set ip
         * @param ip Can be used to filter resources by their ip. The response will only contain the resources matching the specified ip. (optional)
         * @return GetAllRequestBuilder
         */
        public GetAllRequestBuilder ip(String ip) {
            this.ip = ip;
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
         * Set sort
         * @param sort Can be used multiple times. Choices id id:asc id:desc created created:asc created:desc (optional)
         * @return GetAllRequestBuilder
         */
        public GetAllRequestBuilder sort(String sort) {
            this.sort = sort;
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
            <tr><td> 200 </td><td> The &#x60;primary_ips&#x60; key contains an array of Primary IP objects </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getAllCall(name, labelSelector, ip, page, perPage, sort, _callback);
        }


        /**
         * Execute getAll request
         * @return PrimaryIPsGetAllResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;primary_ips&#x60; key contains an array of Primary IP objects </td><td>  -  </td></tr>
         </table>
         */
        public PrimaryIPsGetAllResponse execute() throws ApiException {
            ApiResponse<PrimaryIPsGetAllResponse> localVarResp = getAllWithHttpInfo(name, labelSelector, ip, page, perPage, sort);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getAll request with HTTP info returned
         * @return ApiResponse&lt;PrimaryIPsGetAllResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;primary_ips&#x60; key contains an array of Primary IP objects </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<PrimaryIPsGetAllResponse> executeWithHttpInfo() throws ApiException {
            return getAllWithHttpInfo(name, labelSelector, ip, page, perPage, sort);
        }

        /**
         * Execute getAll request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;primary_ips&#x60; key contains an array of Primary IP objects </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<PrimaryIPsGetAllResponse> _callback) throws ApiException {
            return getAllAsync(name, labelSelector, ip, page, perPage, sort, _callback);
        }
    }

    /**
     * Get all Primary IPs
     * Returns all Primary IP objects.
     * @return GetAllRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;primary_ips&#x60; key contains an array of Primary IP objects </td><td>  -  </td></tr>
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
        String localVarPath = "/primary_ips/{id}"
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


    private ApiResponse<PrimaryIPsGetByIdResponse> getByIdWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = getByIdValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<PrimaryIPsGetByIdResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getByIdAsync(Long id, final ApiCallback<PrimaryIPsGetByIdResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getByIdValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<PrimaryIPsGetByIdResponse>(){}.getType();
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
            <tr><td> 200 </td><td> The &#x60;primary_ip&#x60; key contains a Primary IP object </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getByIdCall(id, _callback);
        }


        /**
         * Execute getById request
         * @return PrimaryIPsGetByIdResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;primary_ip&#x60; key contains a Primary IP object </td><td>  -  </td></tr>
         </table>
         */
        public PrimaryIPsGetByIdResponse execute() throws ApiException {
            ApiResponse<PrimaryIPsGetByIdResponse> localVarResp = getByIdWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getById request with HTTP info returned
         * @return ApiResponse&lt;PrimaryIPsGetByIdResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;primary_ip&#x60; key contains a Primary IP object </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<PrimaryIPsGetByIdResponse> executeWithHttpInfo() throws ApiException {
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
            <tr><td> 200 </td><td> The &#x60;primary_ip&#x60; key contains a Primary IP object </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<PrimaryIPsGetByIdResponse> _callback) throws ApiException {
            return getByIdAsync(id, _callback);
        }
    }

    /**
     * Get a Primary IP
     * Returns a specific Primary IP object.
     * @param id ID of the Resource. (required)
     * @return GetByIdRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;primary_ip&#x60; key contains a Primary IP object </td><td>  -  </td></tr>
     </table>
     */
    public GetByIdRequestBuilder getById(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new GetByIdRequestBuilder(id);
    }
    private okhttp3.Call updateIpLabelsCall(Long id, PrimaryIPsUpdateIpLabelsRequest primaryIPsUpdateIpLabelsRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = primaryIPsUpdateIpLabelsRequest;

        // create path and map variables
        String localVarPath = "/primary_ips/{id}"
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
    private okhttp3.Call updateIpLabelsValidateBeforeCall(Long id, PrimaryIPsUpdateIpLabelsRequest primaryIPsUpdateIpLabelsRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling updateIpLabels(Async)");
        }

        return updateIpLabelsCall(id, primaryIPsUpdateIpLabelsRequest, _callback);

    }


    private ApiResponse<PrimaryIPsUpdateIpLabelsResponse> updateIpLabelsWithHttpInfo(Long id, PrimaryIPsUpdateIpLabelsRequest primaryIPsUpdateIpLabelsRequest) throws ApiException {
        okhttp3.Call localVarCall = updateIpLabelsValidateBeforeCall(id, primaryIPsUpdateIpLabelsRequest, null);
        Type localVarReturnType = new TypeToken<PrimaryIPsUpdateIpLabelsResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call updateIpLabelsAsync(Long id, PrimaryIPsUpdateIpLabelsRequest primaryIPsUpdateIpLabelsRequest, final ApiCallback<PrimaryIPsUpdateIpLabelsResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = updateIpLabelsValidateBeforeCall(id, primaryIPsUpdateIpLabelsRequest, _callback);
        Type localVarReturnType = new TypeToken<PrimaryIPsUpdateIpLabelsResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class UpdateIpLabelsRequestBuilder {
        private final Long id;
        private Boolean autoDelete;
        private Object labels;
        private String name;

        private UpdateIpLabelsRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Set autoDelete
         * @param autoDelete Delete this Primary IP when the resource it is assigned to is deleted (optional)
         * @return UpdateIpLabelsRequestBuilder
         */
        public UpdateIpLabelsRequestBuilder autoDelete(Boolean autoDelete) {
            this.autoDelete = autoDelete;
            return this;
        }
        
        /**
         * Set labels
         * @param labels User-defined labels (key-value pairs) (optional)
         * @return UpdateIpLabelsRequestBuilder
         */
        public UpdateIpLabelsRequestBuilder labels(Object labels) {
            this.labels = labels;
            return this;
        }
        
        /**
         * Set name
         * @param name New unique name to set (optional)
         * @return UpdateIpLabelsRequestBuilder
         */
        public UpdateIpLabelsRequestBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        /**
         * Build call for updateIpLabels
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;primary_ip&#x60; key contains the Primary IP that was just updated </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            PrimaryIPsUpdateIpLabelsRequest primaryIPsUpdateIpLabelsRequest = buildBodyParams();
            return updateIpLabelsCall(id, primaryIPsUpdateIpLabelsRequest, _callback);
        }

        private PrimaryIPsUpdateIpLabelsRequest buildBodyParams() {
            PrimaryIPsUpdateIpLabelsRequest primaryIPsUpdateIpLabelsRequest = new PrimaryIPsUpdateIpLabelsRequest();
            primaryIPsUpdateIpLabelsRequest.autoDelete(this.autoDelete);
            primaryIPsUpdateIpLabelsRequest.labels(this.labels);
            primaryIPsUpdateIpLabelsRequest.name(this.name);
            return primaryIPsUpdateIpLabelsRequest;
        }

        /**
         * Execute updateIpLabels request
         * @return PrimaryIPsUpdateIpLabelsResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;primary_ip&#x60; key contains the Primary IP that was just updated </td><td>  -  </td></tr>
         </table>
         */
        public PrimaryIPsUpdateIpLabelsResponse execute() throws ApiException {
            PrimaryIPsUpdateIpLabelsRequest primaryIPsUpdateIpLabelsRequest = buildBodyParams();
            ApiResponse<PrimaryIPsUpdateIpLabelsResponse> localVarResp = updateIpLabelsWithHttpInfo(id, primaryIPsUpdateIpLabelsRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute updateIpLabels request with HTTP info returned
         * @return ApiResponse&lt;PrimaryIPsUpdateIpLabelsResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;primary_ip&#x60; key contains the Primary IP that was just updated </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<PrimaryIPsUpdateIpLabelsResponse> executeWithHttpInfo() throws ApiException {
            PrimaryIPsUpdateIpLabelsRequest primaryIPsUpdateIpLabelsRequest = buildBodyParams();
            return updateIpLabelsWithHttpInfo(id, primaryIPsUpdateIpLabelsRequest);
        }

        /**
         * Execute updateIpLabels request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;primary_ip&#x60; key contains the Primary IP that was just updated </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<PrimaryIPsUpdateIpLabelsResponse> _callback) throws ApiException {
            PrimaryIPsUpdateIpLabelsRequest primaryIPsUpdateIpLabelsRequest = buildBodyParams();
            return updateIpLabelsAsync(id, primaryIPsUpdateIpLabelsRequest, _callback);
        }
    }

    /**
     * Update a Primary IP
     * Updates the Primary IP.  Note that when updating labels, the Primary IP&#39;s current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body.  If the Primary IP object changes during the request, the response will be a “conflict” error. 
     * @param id ID of the Resource. (required)
     * @return UpdateIpLabelsRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;primary_ip&#x60; key contains the Primary IP that was just updated </td><td>  -  </td></tr>
     </table>
     */
    public UpdateIpLabelsRequestBuilder updateIpLabels(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new UpdateIpLabelsRequestBuilder(id);
    }
}
