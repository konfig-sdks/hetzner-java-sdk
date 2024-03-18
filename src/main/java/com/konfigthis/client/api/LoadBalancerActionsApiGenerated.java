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


import com.konfigthis.client.model.LoadBalancerActionsAddServiceRequest;
import com.konfigthis.client.model.LoadBalancerActionsAddServiceResponse;
import com.konfigthis.client.model.LoadBalancerActionsAddTargetRequest;
import com.konfigthis.client.model.LoadBalancerActionsAddTargetRequestLabelSelector;
import com.konfigthis.client.model.LoadBalancerActionsAddTargetRequestServer;
import com.konfigthis.client.model.LoadBalancerActionsAddTargetResponse;
import com.konfigthis.client.model.LoadBalancerActionsAttachToNetworkRequest;
import com.konfigthis.client.model.LoadBalancerActionsAttachToNetworkResponse;
import com.konfigthis.client.model.LoadBalancerActionsChangeAlgorithmRequest;
import com.konfigthis.client.model.LoadBalancerActionsChangeAlgorithmResponse;
import com.konfigthis.client.model.LoadBalancerActionsChangeDnsPtrRequest;
import com.konfigthis.client.model.LoadBalancerActionsChangeDnsPtrResponse;
import com.konfigthis.client.model.LoadBalancerActionsChangeProtectionRequest;
import com.konfigthis.client.model.LoadBalancerActionsChangeProtectionResponse;
import com.konfigthis.client.model.LoadBalancerActionsChangeTypeRequest;
import com.konfigthis.client.model.LoadBalancerActionsChangeTypeResponse;
import com.konfigthis.client.model.LoadBalancerActionsDeleteServiceRequest;
import com.konfigthis.client.model.LoadBalancerActionsDeleteServiceResponse;
import com.konfigthis.client.model.LoadBalancerActionsDetachFromNetworkRequest;
import com.konfigthis.client.model.LoadBalancerActionsDetachFromNetworkResponse;
import com.konfigthis.client.model.LoadBalancerActionsDisablePublicInterfaceResponse;
import com.konfigthis.client.model.LoadBalancerActionsEnablePublicInterfaceResponse;
import com.konfigthis.client.model.LoadBalancerActionsGetAllActions200Response;
import com.konfigthis.client.model.LoadBalancerActionsGetAllActionsResponse;
import com.konfigthis.client.model.LoadBalancerActionsGetSpecificAction200Response;
import com.konfigthis.client.model.LoadBalancerActionsGetSpecificActionResponse;
import com.konfigthis.client.model.LoadBalancerActionsRemoveTargetRequest;
import com.konfigthis.client.model.LoadBalancerActionsRemoveTargetRequestLabelSelector;
import com.konfigthis.client.model.LoadBalancerActionsRemoveTargetRequestServer;
import com.konfigthis.client.model.LoadBalancerActionsRemoveTargetResponse;
import com.konfigthis.client.model.LoadBalancerActionsUpdateServiceRequest;
import com.konfigthis.client.model.LoadBalancerActionsUpdateServiceResponse;
import com.konfigthis.client.model.LoadBalancerServiceHTTPProperty1;
import com.konfigthis.client.model.LoadBalancerServiceHTTPProperty2;
import com.konfigthis.client.model.LoadBalancerServiceHealthCheckProperty1;
import com.konfigthis.client.model.LoadBalancerTargetIPProperty1;
import com.konfigthis.client.model.LoadBalancerTargetIPProperty2;
import com.konfigthis.client.model.UpdateLoadBalancerServiceHealthCheckProperty;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

public class LoadBalancerActionsApiGenerated {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public LoadBalancerActionsApiGenerated() throws IllegalArgumentException {
        this(Configuration.getDefaultApiClient());
    }

    public LoadBalancerActionsApiGenerated(ApiClient apiClient) throws IllegalArgumentException {
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

    private okhttp3.Call addServiceCall(Long id, LoadBalancerActionsAddServiceRequest loadBalancerActionsAddServiceRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = loadBalancerActionsAddServiceRequest;

        // create path and map variables
        String localVarPath = "/load_balancers/{id}/actions/add_service"
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
    private okhttp3.Call addServiceValidateBeforeCall(Long id, LoadBalancerActionsAddServiceRequest loadBalancerActionsAddServiceRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling addService(Async)");
        }

        return addServiceCall(id, loadBalancerActionsAddServiceRequest, _callback);

    }


    private ApiResponse<LoadBalancerActionsAddServiceResponse> addServiceWithHttpInfo(Long id, LoadBalancerActionsAddServiceRequest loadBalancerActionsAddServiceRequest) throws ApiException {
        okhttp3.Call localVarCall = addServiceValidateBeforeCall(id, loadBalancerActionsAddServiceRequest, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsAddServiceResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call addServiceAsync(Long id, LoadBalancerActionsAddServiceRequest loadBalancerActionsAddServiceRequest, final ApiCallback<LoadBalancerActionsAddServiceResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = addServiceValidateBeforeCall(id, loadBalancerActionsAddServiceRequest, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsAddServiceResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class AddServiceRequestBuilder {
        private final Integer destinationPort;
        private final LoadBalancerServiceHealthCheckProperty1 healthCheck;
        private final Integer listenPort;
        private final String protocol;
        private final Boolean proxyprotocol;
        private final Long id;
        private LoadBalancerServiceHTTPProperty1 http;

        private AddServiceRequestBuilder(Integer destinationPort, LoadBalancerServiceHealthCheckProperty1 healthCheck, Integer listenPort, String protocol, Boolean proxyprotocol, Long id) {
            this.destinationPort = destinationPort;
            this.healthCheck = healthCheck;
            this.listenPort = listenPort;
            this.protocol = protocol;
            this.proxyprotocol = proxyprotocol;
            this.id = id;
        }

        /**
         * Set http
         * @param http  (optional)
         * @return AddServiceRequestBuilder
         */
        public AddServiceRequestBuilder http(LoadBalancerServiceHTTPProperty1 http) {
            this.http = http;
            return this;
        }
        
        /**
         * Build call for addService
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_service&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            LoadBalancerActionsAddServiceRequest loadBalancerActionsAddServiceRequest = buildBodyParams();
            return addServiceCall(id, loadBalancerActionsAddServiceRequest, _callback);
        }

        private LoadBalancerActionsAddServiceRequest buildBodyParams() {
            LoadBalancerActionsAddServiceRequest loadBalancerActionsAddServiceRequest = new LoadBalancerActionsAddServiceRequest();
            loadBalancerActionsAddServiceRequest.destinationPort(this.destinationPort);
            loadBalancerActionsAddServiceRequest.healthCheck(this.healthCheck);
            loadBalancerActionsAddServiceRequest.http(this.http);
            loadBalancerActionsAddServiceRequest.listenPort(this.listenPort);
            if (this.protocol != null)
            loadBalancerActionsAddServiceRequest.protocol(LoadBalancerActionsAddServiceRequest.ProtocolEnum.fromValue(this.protocol));
            loadBalancerActionsAddServiceRequest.proxyprotocol(this.proxyprotocol);
            return loadBalancerActionsAddServiceRequest;
        }

        /**
         * Execute addService request
         * @return LoadBalancerActionsAddServiceResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_service&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsAddServiceResponse execute() throws ApiException {
            LoadBalancerActionsAddServiceRequest loadBalancerActionsAddServiceRequest = buildBodyParams();
            ApiResponse<LoadBalancerActionsAddServiceResponse> localVarResp = addServiceWithHttpInfo(id, loadBalancerActionsAddServiceRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute addService request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsAddServiceResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_service&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsAddServiceResponse> executeWithHttpInfo() throws ApiException {
            LoadBalancerActionsAddServiceRequest loadBalancerActionsAddServiceRequest = buildBodyParams();
            return addServiceWithHttpInfo(id, loadBalancerActionsAddServiceRequest);
        }

        /**
         * Execute addService request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_service&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsAddServiceResponse> _callback) throws ApiException {
            LoadBalancerActionsAddServiceRequest loadBalancerActionsAddServiceRequest = buildBodyParams();
            return addServiceAsync(id, loadBalancerActionsAddServiceRequest, _callback);
        }
    }

    /**
     * Add Service
     * Adds a service to a Load Balancer.  #### Call specific error codes  | Code                       | Description                                             | |----------------------------|---------------------------------------------------------| | &#x60;source_port_already_used&#x60; | The source port you are trying to add is already in use | 
     * @param id ID of the Load Balancer (required)
     * @return AddServiceRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_service&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public AddServiceRequestBuilder addService(Integer destinationPort, LoadBalancerServiceHealthCheckProperty1 healthCheck, Integer listenPort, String protocol, Boolean proxyprotocol, Long id) throws IllegalArgumentException {
        if (destinationPort == null) throw new IllegalArgumentException("\"destinationPort\" is required but got null");
        if (healthCheck == null) throw new IllegalArgumentException("\"healthCheck\" is required but got null");
        if (listenPort == null) throw new IllegalArgumentException("\"listenPort\" is required but got null");
        if (protocol == null) throw new IllegalArgumentException("\"protocol\" is required but got null");
            

        if (proxyprotocol == null) throw new IllegalArgumentException("\"proxyprotocol\" is required but got null");
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new AddServiceRequestBuilder(destinationPort, healthCheck, listenPort, protocol, proxyprotocol, id);
    }
    private okhttp3.Call addTargetCall(Long id, LoadBalancerActionsAddTargetRequest loadBalancerActionsAddTargetRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = loadBalancerActionsAddTargetRequest;

        // create path and map variables
        String localVarPath = "/load_balancers/{id}/actions/add_target"
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
    private okhttp3.Call addTargetValidateBeforeCall(Long id, LoadBalancerActionsAddTargetRequest loadBalancerActionsAddTargetRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling addTarget(Async)");
        }

        return addTargetCall(id, loadBalancerActionsAddTargetRequest, _callback);

    }


    private ApiResponse<LoadBalancerActionsAddTargetResponse> addTargetWithHttpInfo(Long id, LoadBalancerActionsAddTargetRequest loadBalancerActionsAddTargetRequest) throws ApiException {
        okhttp3.Call localVarCall = addTargetValidateBeforeCall(id, loadBalancerActionsAddTargetRequest, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsAddTargetResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call addTargetAsync(Long id, LoadBalancerActionsAddTargetRequest loadBalancerActionsAddTargetRequest, final ApiCallback<LoadBalancerActionsAddTargetResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = addTargetValidateBeforeCall(id, loadBalancerActionsAddTargetRequest, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsAddTargetResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class AddTargetRequestBuilder {
        private final String type;
        private final Long id;
        private LoadBalancerTargetIPProperty1 ip;
        private LoadBalancerActionsAddTargetRequestLabelSelector labelSelector;
        private LoadBalancerActionsAddTargetRequestServer server;
        private Boolean usePrivateIp;

        private AddTargetRequestBuilder(String type, Long id) {
            this.type = type;
            this.id = id;
        }

        /**
         * Set ip
         * @param ip  (optional)
         * @return AddTargetRequestBuilder
         */
        public AddTargetRequestBuilder ip(LoadBalancerTargetIPProperty1 ip) {
            this.ip = ip;
            return this;
        }
        
        /**
         * Set labelSelector
         * @param labelSelector  (optional)
         * @return AddTargetRequestBuilder
         */
        public AddTargetRequestBuilder labelSelector(LoadBalancerActionsAddTargetRequestLabelSelector labelSelector) {
            this.labelSelector = labelSelector;
            return this;
        }
        
        /**
         * Set server
         * @param server  (optional)
         * @return AddTargetRequestBuilder
         */
        public AddTargetRequestBuilder server(LoadBalancerActionsAddTargetRequestServer server) {
            this.server = server;
            return this;
        }
        
        /**
         * Set usePrivateIp
         * @param usePrivateIp Use the private network IP instead of the public IP of the Server, requires the Server and Load Balancer to be in the same network. (optional, default to false)
         * @return AddTargetRequestBuilder
         */
        public AddTargetRequestBuilder usePrivateIp(Boolean usePrivateIp) {
            this.usePrivateIp = usePrivateIp;
            return this;
        }
        
        /**
         * Build call for addTarget
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_target&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            LoadBalancerActionsAddTargetRequest loadBalancerActionsAddTargetRequest = buildBodyParams();
            return addTargetCall(id, loadBalancerActionsAddTargetRequest, _callback);
        }

        private LoadBalancerActionsAddTargetRequest buildBodyParams() {
            LoadBalancerActionsAddTargetRequest loadBalancerActionsAddTargetRequest = new LoadBalancerActionsAddTargetRequest();
            loadBalancerActionsAddTargetRequest.ip(this.ip);
            loadBalancerActionsAddTargetRequest.labelSelector(this.labelSelector);
            loadBalancerActionsAddTargetRequest.server(this.server);
            if (this.type != null)
            loadBalancerActionsAddTargetRequest.type(LoadBalancerActionsAddTargetRequest.TypeEnum.fromValue(this.type));
            loadBalancerActionsAddTargetRequest.usePrivateIp(this.usePrivateIp);
            return loadBalancerActionsAddTargetRequest;
        }

        /**
         * Execute addTarget request
         * @return LoadBalancerActionsAddTargetResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_target&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsAddTargetResponse execute() throws ApiException {
            LoadBalancerActionsAddTargetRequest loadBalancerActionsAddTargetRequest = buildBodyParams();
            ApiResponse<LoadBalancerActionsAddTargetResponse> localVarResp = addTargetWithHttpInfo(id, loadBalancerActionsAddTargetRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute addTarget request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsAddTargetResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_target&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsAddTargetResponse> executeWithHttpInfo() throws ApiException {
            LoadBalancerActionsAddTargetRequest loadBalancerActionsAddTargetRequest = buildBodyParams();
            return addTargetWithHttpInfo(id, loadBalancerActionsAddTargetRequest);
        }

        /**
         * Execute addTarget request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_target&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsAddTargetResponse> _callback) throws ApiException {
            LoadBalancerActionsAddTargetRequest loadBalancerActionsAddTargetRequest = buildBodyParams();
            return addTargetAsync(id, loadBalancerActionsAddTargetRequest, _callback);
        }
    }

    /**
     * Add Target
     * Adds a target to a Load Balancer.  #### Call specific error codes  | Code                                    | Description                                                                                           | |-----------------------------------------|-------------------------------------------------------------------------------------------------------| | &#x60;cloud_resource_ip_not_allowed&#x60;         | The IP you are trying to add as a target belongs to a Hetzner Cloud resource                          | | &#x60;ip_not_owned&#x60;                          | The IP you are trying to add as a target is not owned by the Project owner                            | | &#x60;load_balancer_not_attached_to_network&#x60; | The Load Balancer is not attached to a network                                                        | | &#x60;robot_unavailable&#x60;                     | Robot was not available. The caller may retry the operation after a short delay.                      | | &#x60;server_not_attached_to_network&#x60;        | The server you are trying to add as a target is not attached to the same network as the Load Balancer | | &#x60;target_already_defined&#x60;                | The Load Balancer target you are trying to define is already defined                                  | 
     * @param id ID of the Load Balancer (required)
     * @return AddTargetRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;add_target&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public AddTargetRequestBuilder addTarget(String type, Long id) throws IllegalArgumentException {
        if (type == null) throw new IllegalArgumentException("\"type\" is required but got null");
            

        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new AddTargetRequestBuilder(type, id);
    }
    private okhttp3.Call attachToNetworkCall(Long id, LoadBalancerActionsAttachToNetworkRequest loadBalancerActionsAttachToNetworkRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = loadBalancerActionsAttachToNetworkRequest;

        // create path and map variables
        String localVarPath = "/load_balancers/{id}/actions/attach_to_network"
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
    private okhttp3.Call attachToNetworkValidateBeforeCall(Long id, LoadBalancerActionsAttachToNetworkRequest loadBalancerActionsAttachToNetworkRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling attachToNetwork(Async)");
        }

        return attachToNetworkCall(id, loadBalancerActionsAttachToNetworkRequest, _callback);

    }


    private ApiResponse<LoadBalancerActionsAttachToNetworkResponse> attachToNetworkWithHttpInfo(Long id, LoadBalancerActionsAttachToNetworkRequest loadBalancerActionsAttachToNetworkRequest) throws ApiException {
        okhttp3.Call localVarCall = attachToNetworkValidateBeforeCall(id, loadBalancerActionsAttachToNetworkRequest, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsAttachToNetworkResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call attachToNetworkAsync(Long id, LoadBalancerActionsAttachToNetworkRequest loadBalancerActionsAttachToNetworkRequest, final ApiCallback<LoadBalancerActionsAttachToNetworkResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = attachToNetworkValidateBeforeCall(id, loadBalancerActionsAttachToNetworkRequest, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsAttachToNetworkResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class AttachToNetworkRequestBuilder {
        private final Long network;
        private final Long id;
        private String ip;

        private AttachToNetworkRequestBuilder(Long network, Long id) {
            this.network = network;
            this.id = id;
        }

        /**
         * Set ip
         * @param ip IP to request to be assigned to this Load Balancer; if you do not provide this then you will be auto assigned an IP address (optional)
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
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;attach_to_network&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            LoadBalancerActionsAttachToNetworkRequest loadBalancerActionsAttachToNetworkRequest = buildBodyParams();
            return attachToNetworkCall(id, loadBalancerActionsAttachToNetworkRequest, _callback);
        }

        private LoadBalancerActionsAttachToNetworkRequest buildBodyParams() {
            LoadBalancerActionsAttachToNetworkRequest loadBalancerActionsAttachToNetworkRequest = new LoadBalancerActionsAttachToNetworkRequest();
            loadBalancerActionsAttachToNetworkRequest.ip(this.ip);
            loadBalancerActionsAttachToNetworkRequest.network(this.network);
            return loadBalancerActionsAttachToNetworkRequest;
        }

        /**
         * Execute attachToNetwork request
         * @return LoadBalancerActionsAttachToNetworkResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;attach_to_network&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsAttachToNetworkResponse execute() throws ApiException {
            LoadBalancerActionsAttachToNetworkRequest loadBalancerActionsAttachToNetworkRequest = buildBodyParams();
            ApiResponse<LoadBalancerActionsAttachToNetworkResponse> localVarResp = attachToNetworkWithHttpInfo(id, loadBalancerActionsAttachToNetworkRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute attachToNetwork request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsAttachToNetworkResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;attach_to_network&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsAttachToNetworkResponse> executeWithHttpInfo() throws ApiException {
            LoadBalancerActionsAttachToNetworkRequest loadBalancerActionsAttachToNetworkRequest = buildBodyParams();
            return attachToNetworkWithHttpInfo(id, loadBalancerActionsAttachToNetworkRequest);
        }

        /**
         * Execute attachToNetwork request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;attach_to_network&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsAttachToNetworkResponse> _callback) throws ApiException {
            LoadBalancerActionsAttachToNetworkRequest loadBalancerActionsAttachToNetworkRequest = buildBodyParams();
            return attachToNetworkAsync(id, loadBalancerActionsAttachToNetworkRequest, _callback);
        }
    }

    /**
     * Attach a Load Balancer to a Network
     * Attach a Load Balancer to a Network.  **Call specific error codes**  | Code                             | Description                                                           | |----------------------------------|-----------------------------------------------------------------------| | &#x60;load_balancer_already_attached&#x60; | The Load Balancer is already attached to a network                    | | &#x60;ip_not_available&#x60;               | The provided Network IP is not available                              | | &#x60;no_subnet_available&#x60;            | No Subnet or IP is available for the Load Balancer within the network | 
     * @param id ID of the Load Balancer (required)
     * @return AttachToNetworkRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;attach_to_network&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public AttachToNetworkRequestBuilder attachToNetwork(Long network, Long id) throws IllegalArgumentException {
        if (network == null) throw new IllegalArgumentException("\"network\" is required but got null");
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new AttachToNetworkRequestBuilder(network, id);
    }
    private okhttp3.Call changeAlgorithmCall(Long id, LoadBalancerActionsChangeAlgorithmRequest loadBalancerActionsChangeAlgorithmRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = loadBalancerActionsChangeAlgorithmRequest;

        // create path and map variables
        String localVarPath = "/load_balancers/{id}/actions/change_algorithm"
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
    private okhttp3.Call changeAlgorithmValidateBeforeCall(Long id, LoadBalancerActionsChangeAlgorithmRequest loadBalancerActionsChangeAlgorithmRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling changeAlgorithm(Async)");
        }

        return changeAlgorithmCall(id, loadBalancerActionsChangeAlgorithmRequest, _callback);

    }


    private ApiResponse<LoadBalancerActionsChangeAlgorithmResponse> changeAlgorithmWithHttpInfo(Long id, LoadBalancerActionsChangeAlgorithmRequest loadBalancerActionsChangeAlgorithmRequest) throws ApiException {
        okhttp3.Call localVarCall = changeAlgorithmValidateBeforeCall(id, loadBalancerActionsChangeAlgorithmRequest, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsChangeAlgorithmResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call changeAlgorithmAsync(Long id, LoadBalancerActionsChangeAlgorithmRequest loadBalancerActionsChangeAlgorithmRequest, final ApiCallback<LoadBalancerActionsChangeAlgorithmResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = changeAlgorithmValidateBeforeCall(id, loadBalancerActionsChangeAlgorithmRequest, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsChangeAlgorithmResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class ChangeAlgorithmRequestBuilder {
        private final String type;
        private final Long id;

        private ChangeAlgorithmRequestBuilder(String type, Long id) {
            this.type = type;
            this.id = id;
        }

        /**
         * Build call for changeAlgorithm
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_algorithm&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            LoadBalancerActionsChangeAlgorithmRequest loadBalancerActionsChangeAlgorithmRequest = buildBodyParams();
            return changeAlgorithmCall(id, loadBalancerActionsChangeAlgorithmRequest, _callback);
        }

        private LoadBalancerActionsChangeAlgorithmRequest buildBodyParams() {
            LoadBalancerActionsChangeAlgorithmRequest loadBalancerActionsChangeAlgorithmRequest = new LoadBalancerActionsChangeAlgorithmRequest();
            if (this.type != null)
            loadBalancerActionsChangeAlgorithmRequest.type(LoadBalancerActionsChangeAlgorithmRequest.TypeEnum.fromValue(this.type));
            return loadBalancerActionsChangeAlgorithmRequest;
        }

        /**
         * Execute changeAlgorithm request
         * @return LoadBalancerActionsChangeAlgorithmResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_algorithm&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsChangeAlgorithmResponse execute() throws ApiException {
            LoadBalancerActionsChangeAlgorithmRequest loadBalancerActionsChangeAlgorithmRequest = buildBodyParams();
            ApiResponse<LoadBalancerActionsChangeAlgorithmResponse> localVarResp = changeAlgorithmWithHttpInfo(id, loadBalancerActionsChangeAlgorithmRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute changeAlgorithm request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsChangeAlgorithmResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_algorithm&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsChangeAlgorithmResponse> executeWithHttpInfo() throws ApiException {
            LoadBalancerActionsChangeAlgorithmRequest loadBalancerActionsChangeAlgorithmRequest = buildBodyParams();
            return changeAlgorithmWithHttpInfo(id, loadBalancerActionsChangeAlgorithmRequest);
        }

        /**
         * Execute changeAlgorithm request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_algorithm&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsChangeAlgorithmResponse> _callback) throws ApiException {
            LoadBalancerActionsChangeAlgorithmRequest loadBalancerActionsChangeAlgorithmRequest = buildBodyParams();
            return changeAlgorithmAsync(id, loadBalancerActionsChangeAlgorithmRequest, _callback);
        }
    }

    /**
     * Change Algorithm
     * Change the algorithm that determines to which target new requests are sent.
     * @param id ID of the Load Balancer (required)
     * @return ChangeAlgorithmRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_algorithm&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public ChangeAlgorithmRequestBuilder changeAlgorithm(String type, Long id) throws IllegalArgumentException {
        if (type == null) throw new IllegalArgumentException("\"type\" is required but got null");
            

        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new ChangeAlgorithmRequestBuilder(type, id);
    }
    private okhttp3.Call changeDnsPtrCall(Long id, LoadBalancerActionsChangeDnsPtrRequest loadBalancerActionsChangeDnsPtrRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = loadBalancerActionsChangeDnsPtrRequest;

        // create path and map variables
        String localVarPath = "/load_balancers/{id}/actions/change_dns_ptr"
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
    private okhttp3.Call changeDnsPtrValidateBeforeCall(Long id, LoadBalancerActionsChangeDnsPtrRequest loadBalancerActionsChangeDnsPtrRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling changeDnsPtr(Async)");
        }

        return changeDnsPtrCall(id, loadBalancerActionsChangeDnsPtrRequest, _callback);

    }


    private ApiResponse<LoadBalancerActionsChangeDnsPtrResponse> changeDnsPtrWithHttpInfo(Long id, LoadBalancerActionsChangeDnsPtrRequest loadBalancerActionsChangeDnsPtrRequest) throws ApiException {
        okhttp3.Call localVarCall = changeDnsPtrValidateBeforeCall(id, loadBalancerActionsChangeDnsPtrRequest, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsChangeDnsPtrResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call changeDnsPtrAsync(Long id, LoadBalancerActionsChangeDnsPtrRequest loadBalancerActionsChangeDnsPtrRequest, final ApiCallback<LoadBalancerActionsChangeDnsPtrResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = changeDnsPtrValidateBeforeCall(id, loadBalancerActionsChangeDnsPtrRequest, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsChangeDnsPtrResponse>(){}.getType();
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
            LoadBalancerActionsChangeDnsPtrRequest loadBalancerActionsChangeDnsPtrRequest = buildBodyParams();
            return changeDnsPtrCall(id, loadBalancerActionsChangeDnsPtrRequest, _callback);
        }

        private LoadBalancerActionsChangeDnsPtrRequest buildBodyParams() {
            LoadBalancerActionsChangeDnsPtrRequest loadBalancerActionsChangeDnsPtrRequest = new LoadBalancerActionsChangeDnsPtrRequest();
            loadBalancerActionsChangeDnsPtrRequest.dnsPtr(this.dnsPtr);
            loadBalancerActionsChangeDnsPtrRequest.ip(this.ip);
            return loadBalancerActionsChangeDnsPtrRequest;
        }

        /**
         * Execute changeDnsPtr request
         * @return LoadBalancerActionsChangeDnsPtrResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsChangeDnsPtrResponse execute() throws ApiException {
            LoadBalancerActionsChangeDnsPtrRequest loadBalancerActionsChangeDnsPtrRequest = buildBodyParams();
            ApiResponse<LoadBalancerActionsChangeDnsPtrResponse> localVarResp = changeDnsPtrWithHttpInfo(id, loadBalancerActionsChangeDnsPtrRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute changeDnsPtr request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsChangeDnsPtrResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key in the reply contains an Action object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsChangeDnsPtrResponse> executeWithHttpInfo() throws ApiException {
            LoadBalancerActionsChangeDnsPtrRequest loadBalancerActionsChangeDnsPtrRequest = buildBodyParams();
            return changeDnsPtrWithHttpInfo(id, loadBalancerActionsChangeDnsPtrRequest);
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
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsChangeDnsPtrResponse> _callback) throws ApiException {
            LoadBalancerActionsChangeDnsPtrRequest loadBalancerActionsChangeDnsPtrRequest = buildBodyParams();
            return changeDnsPtrAsync(id, loadBalancerActionsChangeDnsPtrRequest, _callback);
        }
    }

    /**
     * Change reverse DNS entry for this Load Balancer
     * Changes the hostname that will appear when getting the hostname belonging to the public IPs (IPv4 and IPv6) of this Load Balancer.  Floating IPs assigned to the Server are not affected by this. 
     * @param id ID of the Load Balancer (required)
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
    private okhttp3.Call changeProtectionCall(Long id, LoadBalancerActionsChangeProtectionRequest loadBalancerActionsChangeProtectionRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = loadBalancerActionsChangeProtectionRequest;

        // create path and map variables
        String localVarPath = "/load_balancers/{id}/actions/change_protection"
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
    private okhttp3.Call changeProtectionValidateBeforeCall(Long id, LoadBalancerActionsChangeProtectionRequest loadBalancerActionsChangeProtectionRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling changeProtection(Async)");
        }

        return changeProtectionCall(id, loadBalancerActionsChangeProtectionRequest, _callback);

    }


    private ApiResponse<LoadBalancerActionsChangeProtectionResponse> changeProtectionWithHttpInfo(Long id, LoadBalancerActionsChangeProtectionRequest loadBalancerActionsChangeProtectionRequest) throws ApiException {
        okhttp3.Call localVarCall = changeProtectionValidateBeforeCall(id, loadBalancerActionsChangeProtectionRequest, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsChangeProtectionResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call changeProtectionAsync(Long id, LoadBalancerActionsChangeProtectionRequest loadBalancerActionsChangeProtectionRequest, final ApiCallback<LoadBalancerActionsChangeProtectionResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = changeProtectionValidateBeforeCall(id, loadBalancerActionsChangeProtectionRequest, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsChangeProtectionResponse>(){}.getType();
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
         * @param delete If true, prevents the Load Balancer from being deleted (optional)
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
            LoadBalancerActionsChangeProtectionRequest loadBalancerActionsChangeProtectionRequest = buildBodyParams();
            return changeProtectionCall(id, loadBalancerActionsChangeProtectionRequest, _callback);
        }

        private LoadBalancerActionsChangeProtectionRequest buildBodyParams() {
            LoadBalancerActionsChangeProtectionRequest loadBalancerActionsChangeProtectionRequest = new LoadBalancerActionsChangeProtectionRequest();
            loadBalancerActionsChangeProtectionRequest.delete(this.delete);
            return loadBalancerActionsChangeProtectionRequest;
        }

        /**
         * Execute changeProtection request
         * @return LoadBalancerActionsChangeProtectionResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_protection&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsChangeProtectionResponse execute() throws ApiException {
            LoadBalancerActionsChangeProtectionRequest loadBalancerActionsChangeProtectionRequest = buildBodyParams();
            ApiResponse<LoadBalancerActionsChangeProtectionResponse> localVarResp = changeProtectionWithHttpInfo(id, loadBalancerActionsChangeProtectionRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute changeProtection request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsChangeProtectionResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_protection&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsChangeProtectionResponse> executeWithHttpInfo() throws ApiException {
            LoadBalancerActionsChangeProtectionRequest loadBalancerActionsChangeProtectionRequest = buildBodyParams();
            return changeProtectionWithHttpInfo(id, loadBalancerActionsChangeProtectionRequest);
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
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsChangeProtectionResponse> _callback) throws ApiException {
            LoadBalancerActionsChangeProtectionRequest loadBalancerActionsChangeProtectionRequest = buildBodyParams();
            return changeProtectionAsync(id, loadBalancerActionsChangeProtectionRequest, _callback);
        }
    }

    /**
     * Change Load Balancer Protection
     * Changes the protection configuration of a Load Balancer.
     * @param id ID of the Load Balancer (required)
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
    private okhttp3.Call changeTypeCall(Long id, LoadBalancerActionsChangeTypeRequest loadBalancerActionsChangeTypeRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = loadBalancerActionsChangeTypeRequest;

        // create path and map variables
        String localVarPath = "/load_balancers/{id}/actions/change_type"
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
    private okhttp3.Call changeTypeValidateBeforeCall(Long id, LoadBalancerActionsChangeTypeRequest loadBalancerActionsChangeTypeRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling changeType(Async)");
        }

        return changeTypeCall(id, loadBalancerActionsChangeTypeRequest, _callback);

    }


    private ApiResponse<LoadBalancerActionsChangeTypeResponse> changeTypeWithHttpInfo(Long id, LoadBalancerActionsChangeTypeRequest loadBalancerActionsChangeTypeRequest) throws ApiException {
        okhttp3.Call localVarCall = changeTypeValidateBeforeCall(id, loadBalancerActionsChangeTypeRequest, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsChangeTypeResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call changeTypeAsync(Long id, LoadBalancerActionsChangeTypeRequest loadBalancerActionsChangeTypeRequest, final ApiCallback<LoadBalancerActionsChangeTypeResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = changeTypeValidateBeforeCall(id, loadBalancerActionsChangeTypeRequest, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsChangeTypeResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class ChangeTypeRequestBuilder {
        private final String loadBalancerType;
        private final Long id;

        private ChangeTypeRequestBuilder(String loadBalancerType, Long id) {
            this.loadBalancerType = loadBalancerType;
            this.id = id;
        }

        /**
         * Build call for changeType
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_load_balancer_type&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            LoadBalancerActionsChangeTypeRequest loadBalancerActionsChangeTypeRequest = buildBodyParams();
            return changeTypeCall(id, loadBalancerActionsChangeTypeRequest, _callback);
        }

        private LoadBalancerActionsChangeTypeRequest buildBodyParams() {
            LoadBalancerActionsChangeTypeRequest loadBalancerActionsChangeTypeRequest = new LoadBalancerActionsChangeTypeRequest();
            loadBalancerActionsChangeTypeRequest.loadBalancerType(this.loadBalancerType);
            return loadBalancerActionsChangeTypeRequest;
        }

        /**
         * Execute changeType request
         * @return LoadBalancerActionsChangeTypeResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_load_balancer_type&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsChangeTypeResponse execute() throws ApiException {
            LoadBalancerActionsChangeTypeRequest loadBalancerActionsChangeTypeRequest = buildBodyParams();
            ApiResponse<LoadBalancerActionsChangeTypeResponse> localVarResp = changeTypeWithHttpInfo(id, loadBalancerActionsChangeTypeRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute changeType request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsChangeTypeResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_load_balancer_type&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsChangeTypeResponse> executeWithHttpInfo() throws ApiException {
            LoadBalancerActionsChangeTypeRequest loadBalancerActionsChangeTypeRequest = buildBodyParams();
            return changeTypeWithHttpInfo(id, loadBalancerActionsChangeTypeRequest);
        }

        /**
         * Execute changeType request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_load_balancer_type&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsChangeTypeResponse> _callback) throws ApiException {
            LoadBalancerActionsChangeTypeRequest loadBalancerActionsChangeTypeRequest = buildBodyParams();
            return changeTypeAsync(id, loadBalancerActionsChangeTypeRequest, _callback);
        }
    }

    /**
     * Change the Type of a Load Balancer
     * Changes the type (Max Services, Max Targets and Max Connections) of a Load Balancer.  **Call specific error codes**  | Code                         | Description                                                     | |------------------------------|-----------------------------------------------------------------| | &#x60;invalid_load_balancer_type&#x60; | The Load Balancer type does not fit for the given Load Balancer | 
     * @param id ID of the Load Balancer (required)
     * @return ChangeTypeRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;change_load_balancer_type&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public ChangeTypeRequestBuilder changeType(String loadBalancerType, Long id) throws IllegalArgumentException {
        if (loadBalancerType == null) throw new IllegalArgumentException("\"loadBalancerType\" is required but got null");
            

        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new ChangeTypeRequestBuilder(loadBalancerType, id);
    }
    private okhttp3.Call deleteServiceCall(Long id, LoadBalancerActionsDeleteServiceRequest loadBalancerActionsDeleteServiceRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = loadBalancerActionsDeleteServiceRequest;

        // create path and map variables
        String localVarPath = "/load_balancers/{id}/actions/delete_service"
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
    private okhttp3.Call deleteServiceValidateBeforeCall(Long id, LoadBalancerActionsDeleteServiceRequest loadBalancerActionsDeleteServiceRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling deleteService(Async)");
        }

        return deleteServiceCall(id, loadBalancerActionsDeleteServiceRequest, _callback);

    }


    private ApiResponse<LoadBalancerActionsDeleteServiceResponse> deleteServiceWithHttpInfo(Long id, LoadBalancerActionsDeleteServiceRequest loadBalancerActionsDeleteServiceRequest) throws ApiException {
        okhttp3.Call localVarCall = deleteServiceValidateBeforeCall(id, loadBalancerActionsDeleteServiceRequest, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsDeleteServiceResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call deleteServiceAsync(Long id, LoadBalancerActionsDeleteServiceRequest loadBalancerActionsDeleteServiceRequest, final ApiCallback<LoadBalancerActionsDeleteServiceResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteServiceValidateBeforeCall(id, loadBalancerActionsDeleteServiceRequest, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsDeleteServiceResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class DeleteServiceRequestBuilder {
        private final Integer listenPort;
        private final Long id;

        private DeleteServiceRequestBuilder(Integer listenPort, Long id) {
            this.listenPort = listenPort;
            this.id = id;
        }

        /**
         * Build call for deleteService
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_service&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            LoadBalancerActionsDeleteServiceRequest loadBalancerActionsDeleteServiceRequest = buildBodyParams();
            return deleteServiceCall(id, loadBalancerActionsDeleteServiceRequest, _callback);
        }

        private LoadBalancerActionsDeleteServiceRequest buildBodyParams() {
            LoadBalancerActionsDeleteServiceRequest loadBalancerActionsDeleteServiceRequest = new LoadBalancerActionsDeleteServiceRequest();
            loadBalancerActionsDeleteServiceRequest.listenPort(this.listenPort);
            return loadBalancerActionsDeleteServiceRequest;
        }

        /**
         * Execute deleteService request
         * @return LoadBalancerActionsDeleteServiceResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_service&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsDeleteServiceResponse execute() throws ApiException {
            LoadBalancerActionsDeleteServiceRequest loadBalancerActionsDeleteServiceRequest = buildBodyParams();
            ApiResponse<LoadBalancerActionsDeleteServiceResponse> localVarResp = deleteServiceWithHttpInfo(id, loadBalancerActionsDeleteServiceRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute deleteService request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsDeleteServiceResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_service&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsDeleteServiceResponse> executeWithHttpInfo() throws ApiException {
            LoadBalancerActionsDeleteServiceRequest loadBalancerActionsDeleteServiceRequest = buildBodyParams();
            return deleteServiceWithHttpInfo(id, loadBalancerActionsDeleteServiceRequest);
        }

        /**
         * Execute deleteService request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_service&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsDeleteServiceResponse> _callback) throws ApiException {
            LoadBalancerActionsDeleteServiceRequest loadBalancerActionsDeleteServiceRequest = buildBodyParams();
            return deleteServiceAsync(id, loadBalancerActionsDeleteServiceRequest, _callback);
        }
    }

    /**
     * Delete Service
     * Delete a service of a Load Balancer.
     * @param id ID of the Load Balancer (required)
     * @return DeleteServiceRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;delete_service&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public DeleteServiceRequestBuilder deleteService(Integer listenPort, Long id) throws IllegalArgumentException {
        if (listenPort == null) throw new IllegalArgumentException("\"listenPort\" is required but got null");
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new DeleteServiceRequestBuilder(listenPort, id);
    }
    private okhttp3.Call detachFromNetworkCall(Long id, LoadBalancerActionsDetachFromNetworkRequest loadBalancerActionsDetachFromNetworkRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = loadBalancerActionsDetachFromNetworkRequest;

        // create path and map variables
        String localVarPath = "/load_balancers/{id}/actions/detach_from_network"
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
    private okhttp3.Call detachFromNetworkValidateBeforeCall(Long id, LoadBalancerActionsDetachFromNetworkRequest loadBalancerActionsDetachFromNetworkRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling detachFromNetwork(Async)");
        }

        return detachFromNetworkCall(id, loadBalancerActionsDetachFromNetworkRequest, _callback);

    }


    private ApiResponse<LoadBalancerActionsDetachFromNetworkResponse> detachFromNetworkWithHttpInfo(Long id, LoadBalancerActionsDetachFromNetworkRequest loadBalancerActionsDetachFromNetworkRequest) throws ApiException {
        okhttp3.Call localVarCall = detachFromNetworkValidateBeforeCall(id, loadBalancerActionsDetachFromNetworkRequest, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsDetachFromNetworkResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call detachFromNetworkAsync(Long id, LoadBalancerActionsDetachFromNetworkRequest loadBalancerActionsDetachFromNetworkRequest, final ApiCallback<LoadBalancerActionsDetachFromNetworkResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = detachFromNetworkValidateBeforeCall(id, loadBalancerActionsDetachFromNetworkRequest, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsDetachFromNetworkResponse>(){}.getType();
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
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;detach_from_network&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            LoadBalancerActionsDetachFromNetworkRequest loadBalancerActionsDetachFromNetworkRequest = buildBodyParams();
            return detachFromNetworkCall(id, loadBalancerActionsDetachFromNetworkRequest, _callback);
        }

        private LoadBalancerActionsDetachFromNetworkRequest buildBodyParams() {
            LoadBalancerActionsDetachFromNetworkRequest loadBalancerActionsDetachFromNetworkRequest = new LoadBalancerActionsDetachFromNetworkRequest();
            loadBalancerActionsDetachFromNetworkRequest.network(this.network);
            return loadBalancerActionsDetachFromNetworkRequest;
        }

        /**
         * Execute detachFromNetwork request
         * @return LoadBalancerActionsDetachFromNetworkResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;detach_from_network&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsDetachFromNetworkResponse execute() throws ApiException {
            LoadBalancerActionsDetachFromNetworkRequest loadBalancerActionsDetachFromNetworkRequest = buildBodyParams();
            ApiResponse<LoadBalancerActionsDetachFromNetworkResponse> localVarResp = detachFromNetworkWithHttpInfo(id, loadBalancerActionsDetachFromNetworkRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute detachFromNetwork request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsDetachFromNetworkResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;detach_from_network&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsDetachFromNetworkResponse> executeWithHttpInfo() throws ApiException {
            LoadBalancerActionsDetachFromNetworkRequest loadBalancerActionsDetachFromNetworkRequest = buildBodyParams();
            return detachFromNetworkWithHttpInfo(id, loadBalancerActionsDetachFromNetworkRequest);
        }

        /**
         * Execute detachFromNetwork request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;detach_from_network&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsDetachFromNetworkResponse> _callback) throws ApiException {
            LoadBalancerActionsDetachFromNetworkRequest loadBalancerActionsDetachFromNetworkRequest = buildBodyParams();
            return detachFromNetworkAsync(id, loadBalancerActionsDetachFromNetworkRequest, _callback);
        }
    }

    /**
     * Detach a Load Balancer from a Network
     * Detaches a Load Balancer from a network.
     * @param id ID of the Load Balancer (required)
     * @return DetachFromNetworkRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;detach_from_network&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public DetachFromNetworkRequestBuilder detachFromNetwork(Long network, Long id) throws IllegalArgumentException {
        if (network == null) throw new IllegalArgumentException("\"network\" is required but got null");
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new DetachFromNetworkRequestBuilder(network, id);
    }
    private okhttp3.Call disablePublicInterfaceCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/load_balancers/{id}/actions/disable_public_interface"
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
    private okhttp3.Call disablePublicInterfaceValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling disablePublicInterface(Async)");
        }

        return disablePublicInterfaceCall(id, _callback);

    }


    private ApiResponse<LoadBalancerActionsDisablePublicInterfaceResponse> disablePublicInterfaceWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = disablePublicInterfaceValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsDisablePublicInterfaceResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call disablePublicInterfaceAsync(Long id, final ApiCallback<LoadBalancerActionsDisablePublicInterfaceResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = disablePublicInterfaceValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsDisablePublicInterfaceResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class DisablePublicInterfaceRequestBuilder {
        private final Long id;

        private DisablePublicInterfaceRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for disablePublicInterface
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;disable_public_interface&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return disablePublicInterfaceCall(id, _callback);
        }


        /**
         * Execute disablePublicInterface request
         * @return LoadBalancerActionsDisablePublicInterfaceResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;disable_public_interface&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsDisablePublicInterfaceResponse execute() throws ApiException {
            ApiResponse<LoadBalancerActionsDisablePublicInterfaceResponse> localVarResp = disablePublicInterfaceWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute disablePublicInterface request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsDisablePublicInterfaceResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;disable_public_interface&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsDisablePublicInterfaceResponse> executeWithHttpInfo() throws ApiException {
            return disablePublicInterfaceWithHttpInfo(id);
        }

        /**
         * Execute disablePublicInterface request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;disable_public_interface&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsDisablePublicInterfaceResponse> _callback) throws ApiException {
            return disablePublicInterfaceAsync(id, _callback);
        }
    }

    /**
     * Disable the public interface of a Load Balancer
     * Disable the public interface of a Load Balancer. The Load Balancer will be not accessible from the internet via its public IPs.  #### Call specific error codes  | Code                                      | Description                                                                    | |-------------------------------------------|--------------------------------------------------------------------------------| | &#x60;load_balancer_not_attached_to_network&#x60;   |  The Load Balancer is not attached to a network                                | | &#x60;targets_without_use_private_ip&#x60;          | The Load Balancer has targets that use the public IP instead of the private IP | 
     * @param id ID of the Load Balancer (required)
     * @return DisablePublicInterfaceRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;disable_public_interface&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public DisablePublicInterfaceRequestBuilder disablePublicInterface(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new DisablePublicInterfaceRequestBuilder(id);
    }
    private okhttp3.Call enablePublicInterfaceCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/load_balancers/{id}/actions/enable_public_interface"
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
    private okhttp3.Call enablePublicInterfaceValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling enablePublicInterface(Async)");
        }

        return enablePublicInterfaceCall(id, _callback);

    }


    private ApiResponse<LoadBalancerActionsEnablePublicInterfaceResponse> enablePublicInterfaceWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = enablePublicInterfaceValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsEnablePublicInterfaceResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call enablePublicInterfaceAsync(Long id, final ApiCallback<LoadBalancerActionsEnablePublicInterfaceResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = enablePublicInterfaceValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsEnablePublicInterfaceResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class EnablePublicInterfaceRequestBuilder {
        private final Long id;

        private EnablePublicInterfaceRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for enablePublicInterface
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;enable_public_interface&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return enablePublicInterfaceCall(id, _callback);
        }


        /**
         * Execute enablePublicInterface request
         * @return LoadBalancerActionsEnablePublicInterfaceResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;enable_public_interface&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsEnablePublicInterfaceResponse execute() throws ApiException {
            ApiResponse<LoadBalancerActionsEnablePublicInterfaceResponse> localVarResp = enablePublicInterfaceWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute enablePublicInterface request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsEnablePublicInterfaceResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;enable_public_interface&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsEnablePublicInterfaceResponse> executeWithHttpInfo() throws ApiException {
            return enablePublicInterfaceWithHttpInfo(id);
        }

        /**
         * Execute enablePublicInterface request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;enable_public_interface&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsEnablePublicInterfaceResponse> _callback) throws ApiException {
            return enablePublicInterfaceAsync(id, _callback);
        }
    }

    /**
     * Enable the public interface of a Load Balancer
     * Enable the public interface of a Load Balancer. The Load Balancer will be accessible from the internet via its public IPs.
     * @param id ID of the Load Balancer (required)
     * @return EnablePublicInterfaceRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;enable_public_interface&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public EnablePublicInterfaceRequestBuilder enablePublicInterface(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new EnablePublicInterfaceRequestBuilder(id);
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
        String localVarPath = "/load_balancers/actions";

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


    private ApiResponse<LoadBalancerActionsGetAllActionsResponse> getAllActionsWithHttpInfo(Long id, String sort, String status, Long page, Long perPage) throws ApiException {
        okhttp3.Call localVarCall = getAllActionsValidateBeforeCall(id, sort, status, page, perPage, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsGetAllActionsResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getAllActionsAsync(Long id, String sort, String status, Long page, Long perPage, final ApiCallback<LoadBalancerActionsGetAllActionsResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAllActionsValidateBeforeCall(id, sort, status, page, perPage, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsGetAllActionsResponse>(){}.getType();
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
         * @return LoadBalancerActionsGetAllActionsResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsGetAllActionsResponse execute() throws ApiException {
            ApiResponse<LoadBalancerActionsGetAllActionsResponse> localVarResp = getAllActionsWithHttpInfo(id, sort, status, page, perPage);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getAllActions request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsGetAllActionsResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsGetAllActionsResponse> executeWithHttpInfo() throws ApiException {
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
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsGetAllActionsResponse> _callback) throws ApiException {
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
        String localVarPath = "/load_balancers/{id}/actions"
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


    private ApiResponse<LoadBalancerActionsGetAllActions200Response> getAllActions_0WithHttpInfo(Long id, String sort, String status, Long page, Long perPage) throws ApiException {
        okhttp3.Call localVarCall = getAllActions_0ValidateBeforeCall(id, sort, status, page, perPage, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsGetAllActions200Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getAllActions_0Async(Long id, String sort, String status, Long page, Long perPage, final ApiCallback<LoadBalancerActionsGetAllActions200Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAllActions_0ValidateBeforeCall(id, sort, status, page, perPage, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsGetAllActions200Response>(){}.getType();
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
         * @return LoadBalancerActionsGetAllActions200Response
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsGetAllActions200Response execute() throws ApiException {
            ApiResponse<LoadBalancerActionsGetAllActions200Response> localVarResp = getAllActions_0WithHttpInfo(id, sort, status, page, perPage);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getAllActions_0 request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsGetAllActions200Response&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;actions&#x60; key contains a list of Actions </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsGetAllActions200Response> executeWithHttpInfo() throws ApiException {
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
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsGetAllActions200Response> _callback) throws ApiException {
            return getAllActions_0Async(id, sort, status, page, perPage, _callback);
        }
    }

    /**
     * Get all Actions for a Load Balancer
     * Returns all Action objects for a Load Balancer. You can sort the results by using the &#x60;sort&#x60; URI parameter, and filter them with the &#x60;status&#x60; parameter.
     * @param id ID of the Load Balancer (required)
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
    private okhttp3.Call getSpecificActionCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/load_balancers/actions/{id}"
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
    private okhttp3.Call getSpecificActionValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getSpecificAction(Async)");
        }

        return getSpecificActionCall(id, _callback);

    }


    private ApiResponse<LoadBalancerActionsGetSpecificActionResponse> getSpecificActionWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = getSpecificActionValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsGetSpecificActionResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getSpecificActionAsync(Long id, final ApiCallback<LoadBalancerActionsGetSpecificActionResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getSpecificActionValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsGetSpecificActionResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetSpecificActionRequestBuilder {
        private final Long id;

        private GetSpecificActionRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for getSpecificAction
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
            return getSpecificActionCall(id, _callback);
        }


        /**
         * Execute getSpecificAction request
         * @return LoadBalancerActionsGetSpecificActionResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsGetSpecificActionResponse execute() throws ApiException {
            ApiResponse<LoadBalancerActionsGetSpecificActionResponse> localVarResp = getSpecificActionWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getSpecificAction request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsGetSpecificActionResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsGetSpecificActionResponse> executeWithHttpInfo() throws ApiException {
            return getSpecificActionWithHttpInfo(id);
        }

        /**
         * Execute getSpecificAction request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsGetSpecificActionResponse> _callback) throws ApiException {
            return getSpecificActionAsync(id, _callback);
        }
    }

    /**
     * Get an Action
     * Returns a specific Action object.
     * @param id ID of the Action. (required)
     * @return GetSpecificActionRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;action&#x60; key in the reply has this structure </td><td>  -  </td></tr>
     </table>
     */
    public GetSpecificActionRequestBuilder getSpecificAction(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new GetSpecificActionRequestBuilder(id);
    }
    private okhttp3.Call getSpecificAction_0Call(Long id, Long actionId, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/load_balancers/{id}/actions/{action_id}"
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
    private okhttp3.Call getSpecificAction_0ValidateBeforeCall(Long id, Long actionId, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getSpecificAction_0(Async)");
        }

        // verify the required parameter 'actionId' is set
        if (actionId == null) {
            throw new ApiException("Missing the required parameter 'actionId' when calling getSpecificAction_0(Async)");
        }

        return getSpecificAction_0Call(id, actionId, _callback);

    }


    private ApiResponse<LoadBalancerActionsGetSpecificAction200Response> getSpecificAction_0WithHttpInfo(Long id, Long actionId) throws ApiException {
        okhttp3.Call localVarCall = getSpecificAction_0ValidateBeforeCall(id, actionId, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsGetSpecificAction200Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getSpecificAction_0Async(Long id, Long actionId, final ApiCallback<LoadBalancerActionsGetSpecificAction200Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = getSpecificAction_0ValidateBeforeCall(id, actionId, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsGetSpecificAction200Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetSpecificAction0RequestBuilder {
        private final Long id;
        private final Long actionId;

        private GetSpecificAction0RequestBuilder(Long id, Long actionId) {
            this.id = id;
            this.actionId = actionId;
        }

        /**
         * Build call for getSpecificAction_0
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key contains the Load Balancer Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getSpecificAction_0Call(id, actionId, _callback);
        }


        /**
         * Execute getSpecificAction_0 request
         * @return LoadBalancerActionsGetSpecificAction200Response
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key contains the Load Balancer Action </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsGetSpecificAction200Response execute() throws ApiException {
            ApiResponse<LoadBalancerActionsGetSpecificAction200Response> localVarResp = getSpecificAction_0WithHttpInfo(id, actionId);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getSpecificAction_0 request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsGetSpecificAction200Response&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key contains the Load Balancer Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsGetSpecificAction200Response> executeWithHttpInfo() throws ApiException {
            return getSpecificAction_0WithHttpInfo(id, actionId);
        }

        /**
         * Execute getSpecificAction_0 request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;action&#x60; key contains the Load Balancer Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsGetSpecificAction200Response> _callback) throws ApiException {
            return getSpecificAction_0Async(id, actionId, _callback);
        }
    }

    /**
     * Get an Action for a Load Balancer
     * Returns a specific Action for a Load Balancer.
     * @param id ID of the Load Balancer (required)
     * @param actionId ID of the Action (required)
     * @return GetSpecificAction0RequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;action&#x60; key contains the Load Balancer Action </td><td>  -  </td></tr>
     </table>
     */
    public GetSpecificAction0RequestBuilder getSpecificAction_0(Long id, Long actionId) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        if (actionId == null) throw new IllegalArgumentException("\"actionId\" is required but got null");
        return new GetSpecificAction0RequestBuilder(id, actionId);
    }
    private okhttp3.Call removeTargetCall(Long id, LoadBalancerActionsRemoveTargetRequest loadBalancerActionsRemoveTargetRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = loadBalancerActionsRemoveTargetRequest;

        // create path and map variables
        String localVarPath = "/load_balancers/{id}/actions/remove_target"
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
    private okhttp3.Call removeTargetValidateBeforeCall(Long id, LoadBalancerActionsRemoveTargetRequest loadBalancerActionsRemoveTargetRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling removeTarget(Async)");
        }

        return removeTargetCall(id, loadBalancerActionsRemoveTargetRequest, _callback);

    }


    private ApiResponse<LoadBalancerActionsRemoveTargetResponse> removeTargetWithHttpInfo(Long id, LoadBalancerActionsRemoveTargetRequest loadBalancerActionsRemoveTargetRequest) throws ApiException {
        okhttp3.Call localVarCall = removeTargetValidateBeforeCall(id, loadBalancerActionsRemoveTargetRequest, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsRemoveTargetResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call removeTargetAsync(Long id, LoadBalancerActionsRemoveTargetRequest loadBalancerActionsRemoveTargetRequest, final ApiCallback<LoadBalancerActionsRemoveTargetResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = removeTargetValidateBeforeCall(id, loadBalancerActionsRemoveTargetRequest, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsRemoveTargetResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class RemoveTargetRequestBuilder {
        private final String type;
        private final Long id;
        private LoadBalancerTargetIPProperty2 ip;
        private LoadBalancerActionsRemoveTargetRequestLabelSelector labelSelector;
        private LoadBalancerActionsRemoveTargetRequestServer server;

        private RemoveTargetRequestBuilder(String type, Long id) {
            this.type = type;
            this.id = id;
        }

        /**
         * Set ip
         * @param ip  (optional)
         * @return RemoveTargetRequestBuilder
         */
        public RemoveTargetRequestBuilder ip(LoadBalancerTargetIPProperty2 ip) {
            this.ip = ip;
            return this;
        }
        
        /**
         * Set labelSelector
         * @param labelSelector  (optional)
         * @return RemoveTargetRequestBuilder
         */
        public RemoveTargetRequestBuilder labelSelector(LoadBalancerActionsRemoveTargetRequestLabelSelector labelSelector) {
            this.labelSelector = labelSelector;
            return this;
        }
        
        /**
         * Set server
         * @param server  (optional)
         * @return RemoveTargetRequestBuilder
         */
        public RemoveTargetRequestBuilder server(LoadBalancerActionsRemoveTargetRequestServer server) {
            this.server = server;
            return this;
        }
        
        /**
         * Build call for removeTarget
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;remove_target&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            LoadBalancerActionsRemoveTargetRequest loadBalancerActionsRemoveTargetRequest = buildBodyParams();
            return removeTargetCall(id, loadBalancerActionsRemoveTargetRequest, _callback);
        }

        private LoadBalancerActionsRemoveTargetRequest buildBodyParams() {
            LoadBalancerActionsRemoveTargetRequest loadBalancerActionsRemoveTargetRequest = new LoadBalancerActionsRemoveTargetRequest();
            loadBalancerActionsRemoveTargetRequest.ip(this.ip);
            loadBalancerActionsRemoveTargetRequest.labelSelector(this.labelSelector);
            loadBalancerActionsRemoveTargetRequest.server(this.server);
            if (this.type != null)
            loadBalancerActionsRemoveTargetRequest.type(LoadBalancerActionsRemoveTargetRequest.TypeEnum.fromValue(this.type));
            return loadBalancerActionsRemoveTargetRequest;
        }

        /**
         * Execute removeTarget request
         * @return LoadBalancerActionsRemoveTargetResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;remove_target&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsRemoveTargetResponse execute() throws ApiException {
            LoadBalancerActionsRemoveTargetRequest loadBalancerActionsRemoveTargetRequest = buildBodyParams();
            ApiResponse<LoadBalancerActionsRemoveTargetResponse> localVarResp = removeTargetWithHttpInfo(id, loadBalancerActionsRemoveTargetRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute removeTarget request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsRemoveTargetResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;remove_target&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsRemoveTargetResponse> executeWithHttpInfo() throws ApiException {
            LoadBalancerActionsRemoveTargetRequest loadBalancerActionsRemoveTargetRequest = buildBodyParams();
            return removeTargetWithHttpInfo(id, loadBalancerActionsRemoveTargetRequest);
        }

        /**
         * Execute removeTarget request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;remove_target&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsRemoveTargetResponse> _callback) throws ApiException {
            LoadBalancerActionsRemoveTargetRequest loadBalancerActionsRemoveTargetRequest = buildBodyParams();
            return removeTargetAsync(id, loadBalancerActionsRemoveTargetRequest, _callback);
        }
    }

    /**
     * Remove Target
     * Removes a target from a Load Balancer.
     * @param id ID of the Load Balancer (required)
     * @return RemoveTargetRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;remove_target&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public RemoveTargetRequestBuilder removeTarget(String type, Long id) throws IllegalArgumentException {
        if (type == null) throw new IllegalArgumentException("\"type\" is required but got null");
            

        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new RemoveTargetRequestBuilder(type, id);
    }
    private okhttp3.Call updateServiceCall(Long id, LoadBalancerActionsUpdateServiceRequest loadBalancerActionsUpdateServiceRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = loadBalancerActionsUpdateServiceRequest;

        // create path and map variables
        String localVarPath = "/load_balancers/{id}/actions/update_service"
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
    private okhttp3.Call updateServiceValidateBeforeCall(Long id, LoadBalancerActionsUpdateServiceRequest loadBalancerActionsUpdateServiceRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling updateService(Async)");
        }

        return updateServiceCall(id, loadBalancerActionsUpdateServiceRequest, _callback);

    }


    private ApiResponse<LoadBalancerActionsUpdateServiceResponse> updateServiceWithHttpInfo(Long id, LoadBalancerActionsUpdateServiceRequest loadBalancerActionsUpdateServiceRequest) throws ApiException {
        okhttp3.Call localVarCall = updateServiceValidateBeforeCall(id, loadBalancerActionsUpdateServiceRequest, null);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsUpdateServiceResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call updateServiceAsync(Long id, LoadBalancerActionsUpdateServiceRequest loadBalancerActionsUpdateServiceRequest, final ApiCallback<LoadBalancerActionsUpdateServiceResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = updateServiceValidateBeforeCall(id, loadBalancerActionsUpdateServiceRequest, _callback);
        Type localVarReturnType = new TypeToken<LoadBalancerActionsUpdateServiceResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class UpdateServiceRequestBuilder {
        private final Integer listenPort;
        private final Long id;
        private Integer destinationPort;
        private UpdateLoadBalancerServiceHealthCheckProperty healthCheck;
        private LoadBalancerServiceHTTPProperty2 http;
        private String protocol;
        private Boolean proxyprotocol;

        private UpdateServiceRequestBuilder(Integer listenPort, Long id) {
            this.listenPort = listenPort;
            this.id = id;
        }

        /**
         * Set destinationPort
         * @param destinationPort Port the Load Balancer will balance to (optional)
         * @return UpdateServiceRequestBuilder
         */
        public UpdateServiceRequestBuilder destinationPort(Integer destinationPort) {
            this.destinationPort = destinationPort;
            return this;
        }
        
        /**
         * Set healthCheck
         * @param healthCheck  (optional)
         * @return UpdateServiceRequestBuilder
         */
        public UpdateServiceRequestBuilder healthCheck(UpdateLoadBalancerServiceHealthCheckProperty healthCheck) {
            this.healthCheck = healthCheck;
            return this;
        }
        
        /**
         * Set http
         * @param http  (optional)
         * @return UpdateServiceRequestBuilder
         */
        public UpdateServiceRequestBuilder http(LoadBalancerServiceHTTPProperty2 http) {
            this.http = http;
            return this;
        }
        
        /**
         * Set protocol
         * @param protocol Protocol of the Load Balancer (optional)
         * @return UpdateServiceRequestBuilder
         */
        public UpdateServiceRequestBuilder protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }
        
        /**
         * Set proxyprotocol
         * @param proxyprotocol Is Proxyprotocol enabled or not (optional)
         * @return UpdateServiceRequestBuilder
         */
        public UpdateServiceRequestBuilder proxyprotocol(Boolean proxyprotocol) {
            this.proxyprotocol = proxyprotocol;
            return this;
        }
        
        /**
         * Build call for updateService
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;update_service&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            LoadBalancerActionsUpdateServiceRequest loadBalancerActionsUpdateServiceRequest = buildBodyParams();
            return updateServiceCall(id, loadBalancerActionsUpdateServiceRequest, _callback);
        }

        private LoadBalancerActionsUpdateServiceRequest buildBodyParams() {
            LoadBalancerActionsUpdateServiceRequest loadBalancerActionsUpdateServiceRequest = new LoadBalancerActionsUpdateServiceRequest();
            loadBalancerActionsUpdateServiceRequest.destinationPort(this.destinationPort);
            loadBalancerActionsUpdateServiceRequest.healthCheck(this.healthCheck);
            loadBalancerActionsUpdateServiceRequest.http(this.http);
            loadBalancerActionsUpdateServiceRequest.listenPort(this.listenPort);
            if (this.protocol != null)
            loadBalancerActionsUpdateServiceRequest.protocol(LoadBalancerActionsUpdateServiceRequest.ProtocolEnum.fromValue(this.protocol));
            loadBalancerActionsUpdateServiceRequest.proxyprotocol(this.proxyprotocol);
            return loadBalancerActionsUpdateServiceRequest;
        }

        /**
         * Execute updateService request
         * @return LoadBalancerActionsUpdateServiceResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;update_service&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public LoadBalancerActionsUpdateServiceResponse execute() throws ApiException {
            LoadBalancerActionsUpdateServiceRequest loadBalancerActionsUpdateServiceRequest = buildBodyParams();
            ApiResponse<LoadBalancerActionsUpdateServiceResponse> localVarResp = updateServiceWithHttpInfo(id, loadBalancerActionsUpdateServiceRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute updateService request with HTTP info returned
         * @return ApiResponse&lt;LoadBalancerActionsUpdateServiceResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;update_service&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<LoadBalancerActionsUpdateServiceResponse> executeWithHttpInfo() throws ApiException {
            LoadBalancerActionsUpdateServiceRequest loadBalancerActionsUpdateServiceRequest = buildBodyParams();
            return updateServiceWithHttpInfo(id, loadBalancerActionsUpdateServiceRequest);
        }

        /**
         * Execute updateService request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;update_service&#x60; Action </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<LoadBalancerActionsUpdateServiceResponse> _callback) throws ApiException {
            LoadBalancerActionsUpdateServiceRequest loadBalancerActionsUpdateServiceRequest = buildBodyParams();
            return updateServiceAsync(id, loadBalancerActionsUpdateServiceRequest, _callback);
        }
    }

    /**
     * Update Service
     * Updates a Load Balancer Service.  #### Call specific error codes  | Code                       | Description                                             | |----------------------------|---------------------------------------------------------| | &#x60;source_port_already_used&#x60; | The source port you are trying to add is already in use | 
     * @param id ID of the Load Balancer (required)
     * @return UpdateServiceRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;action&#x60; key contains the &#x60;update_service&#x60; Action </td><td>  -  </td></tr>
     </table>
     */
    public UpdateServiceRequestBuilder updateService(Integer listenPort, Long id) throws IllegalArgumentException {
        if (listenPort == null) throw new IllegalArgumentException("\"listenPort\" is required but got null");
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new UpdateServiceRequestBuilder(listenPort, id);
    }
}
