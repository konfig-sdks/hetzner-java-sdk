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


import com.konfigthis.client.model.SshKeysCreateKeyRequest;
import com.konfigthis.client.model.SshKeysCreateKeyResponse;
import com.konfigthis.client.model.SshKeysGetAllSshKeysResponse;
import com.konfigthis.client.model.SshKeysGetByIdResponse;
import com.konfigthis.client.model.SshKeysUpdateKeyRequest;
import com.konfigthis.client.model.SshKeysUpdateKeyResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

public class SshKeysApiGenerated {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public SshKeysApiGenerated() throws IllegalArgumentException {
        this(Configuration.getDefaultApiClient());
    }

    public SshKeysApiGenerated(ApiClient apiClient) throws IllegalArgumentException {
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

    private okhttp3.Call createKeyCall(SshKeysCreateKeyRequest sshKeysCreateKeyRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = sshKeysCreateKeyRequest;

        // create path and map variables
        String localVarPath = "/ssh_keys";

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
    private okhttp3.Call createKeyValidateBeforeCall(SshKeysCreateKeyRequest sshKeysCreateKeyRequest, final ApiCallback _callback) throws ApiException {
        return createKeyCall(sshKeysCreateKeyRequest, _callback);

    }


    private ApiResponse<SshKeysCreateKeyResponse> createKeyWithHttpInfo(SshKeysCreateKeyRequest sshKeysCreateKeyRequest) throws ApiException {
        okhttp3.Call localVarCall = createKeyValidateBeforeCall(sshKeysCreateKeyRequest, null);
        Type localVarReturnType = new TypeToken<SshKeysCreateKeyResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call createKeyAsync(SshKeysCreateKeyRequest sshKeysCreateKeyRequest, final ApiCallback<SshKeysCreateKeyResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = createKeyValidateBeforeCall(sshKeysCreateKeyRequest, _callback);
        Type localVarReturnType = new TypeToken<SshKeysCreateKeyResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class CreateKeyRequestBuilder {
        private final String name;
        private final String publicKey;
        private Object labels;

        private CreateKeyRequestBuilder(String name, String publicKey) {
            this.name = name;
            this.publicKey = publicKey;
        }

        /**
         * Set labels
         * @param labels User-defined labels (key-value pairs) (optional)
         * @return CreateKeyRequestBuilder
         */
        public CreateKeyRequestBuilder labels(Object labels) {
            this.labels = labels;
            return this;
        }
        
        /**
         * Build call for createKey
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;ssh_key&#x60; key in the reply contains the object that was just created </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            SshKeysCreateKeyRequest sshKeysCreateKeyRequest = buildBodyParams();
            return createKeyCall(sshKeysCreateKeyRequest, _callback);
        }

        private SshKeysCreateKeyRequest buildBodyParams() {
            SshKeysCreateKeyRequest sshKeysCreateKeyRequest = new SshKeysCreateKeyRequest();
            sshKeysCreateKeyRequest.labels(this.labels);
            sshKeysCreateKeyRequest.name(this.name);
            sshKeysCreateKeyRequest.publicKey(this.publicKey);
            return sshKeysCreateKeyRequest;
        }

        /**
         * Execute createKey request
         * @return SshKeysCreateKeyResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;ssh_key&#x60; key in the reply contains the object that was just created </td><td>  -  </td></tr>
         </table>
         */
        public SshKeysCreateKeyResponse execute() throws ApiException {
            SshKeysCreateKeyRequest sshKeysCreateKeyRequest = buildBodyParams();
            ApiResponse<SshKeysCreateKeyResponse> localVarResp = createKeyWithHttpInfo(sshKeysCreateKeyRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute createKey request with HTTP info returned
         * @return ApiResponse&lt;SshKeysCreateKeyResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;ssh_key&#x60; key in the reply contains the object that was just created </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<SshKeysCreateKeyResponse> executeWithHttpInfo() throws ApiException {
            SshKeysCreateKeyRequest sshKeysCreateKeyRequest = buildBodyParams();
            return createKeyWithHttpInfo(sshKeysCreateKeyRequest);
        }

        /**
         * Execute createKey request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 201 </td><td> The &#x60;ssh_key&#x60; key in the reply contains the object that was just created </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<SshKeysCreateKeyResponse> _callback) throws ApiException {
            SshKeysCreateKeyRequest sshKeysCreateKeyRequest = buildBodyParams();
            return createKeyAsync(sshKeysCreateKeyRequest, _callback);
        }
    }

    /**
     * Create an SSH key
     * Creates a new SSH key with the given &#x60;name&#x60; and &#x60;public_key&#x60;. Once an SSH key is created, it can be used in other calls such as creating Servers.
     * @return CreateKeyRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> The &#x60;ssh_key&#x60; key in the reply contains the object that was just created </td><td>  -  </td></tr>
     </table>
     */
    public CreateKeyRequestBuilder createKey(String name, String publicKey) throws IllegalArgumentException {
        if (name == null) throw new IllegalArgumentException("\"name\" is required but got null");
            

        if (publicKey == null) throw new IllegalArgumentException("\"publicKey\" is required but got null");
            

        return new CreateKeyRequestBuilder(name, publicKey);
    }
    private okhttp3.Call deleteKeyCall(Long id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/ssh_keys/{id}"
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
    private okhttp3.Call deleteKeyValidateBeforeCall(Long id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling deleteKey(Async)");
        }

        return deleteKeyCall(id, _callback);

    }


    private ApiResponse<Void> deleteKeyWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = deleteKeyValidateBeforeCall(id, null);
        return localVarApiClient.execute(localVarCall);
    }

    private okhttp3.Call deleteKeyAsync(Long id, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteKeyValidateBeforeCall(id, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }

    public class DeleteKeyRequestBuilder {
        private final Long id;

        private DeleteKeyRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Build call for deleteKey
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 204 </td><td> SSH key deleted </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return deleteKeyCall(id, _callback);
        }


        /**
         * Execute deleteKey request
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 204 </td><td> SSH key deleted </td><td>  -  </td></tr>
         </table>
         */
        public void execute() throws ApiException {
            deleteKeyWithHttpInfo(id);
        }

        /**
         * Execute deleteKey request with HTTP info returned
         * @return ApiResponse&lt;Void&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 204 </td><td> SSH key deleted </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<Void> executeWithHttpInfo() throws ApiException {
            return deleteKeyWithHttpInfo(id);
        }

        /**
         * Execute deleteKey request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 204 </td><td> SSH key deleted </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<Void> _callback) throws ApiException {
            return deleteKeyAsync(id, _callback);
        }
    }

    /**
     * Delete an SSH key
     * Deletes an SSH key. It cannot be used anymore.
     * @param id ID of the SSH key (required)
     * @return DeleteKeyRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> SSH key deleted </td><td>  -  </td></tr>
     </table>
     */
    public DeleteKeyRequestBuilder deleteKey(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new DeleteKeyRequestBuilder(id);
    }
    private okhttp3.Call getAllSshKeysCall(String sort, String name, String fingerprint, String labelSelector, Long page, Long perPage, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/ssh_keys";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (sort != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("sort", sort));
        }

        if (name != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("name", name));
        }

        if (fingerprint != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("fingerprint", fingerprint));
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
    private okhttp3.Call getAllSshKeysValidateBeforeCall(String sort, String name, String fingerprint, String labelSelector, Long page, Long perPage, final ApiCallback _callback) throws ApiException {
        return getAllSshKeysCall(sort, name, fingerprint, labelSelector, page, perPage, _callback);

    }


    private ApiResponse<SshKeysGetAllSshKeysResponse> getAllSshKeysWithHttpInfo(String sort, String name, String fingerprint, String labelSelector, Long page, Long perPage) throws ApiException {
        okhttp3.Call localVarCall = getAllSshKeysValidateBeforeCall(sort, name, fingerprint, labelSelector, page, perPage, null);
        Type localVarReturnType = new TypeToken<SshKeysGetAllSshKeysResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getAllSshKeysAsync(String sort, String name, String fingerprint, String labelSelector, Long page, Long perPage, final ApiCallback<SshKeysGetAllSshKeysResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAllSshKeysValidateBeforeCall(sort, name, fingerprint, labelSelector, page, perPage, _callback);
        Type localVarReturnType = new TypeToken<SshKeysGetAllSshKeysResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class GetAllSshKeysRequestBuilder {
        private String sort;
        private String name;
        private String fingerprint;
        private String labelSelector;
        private Long page;
        private Long perPage;

        private GetAllSshKeysRequestBuilder() {
        }

        /**
         * Set sort
         * @param sort Can be used multiple times. (optional)
         * @return GetAllSshKeysRequestBuilder
         */
        public GetAllSshKeysRequestBuilder sort(String sort) {
            this.sort = sort;
            return this;
        }
        
        /**
         * Set name
         * @param name Filter resources by their name. The response will only contain the resources matching the specified name.  (optional)
         * @return GetAllSshKeysRequestBuilder
         */
        public GetAllSshKeysRequestBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        /**
         * Set fingerprint
         * @param fingerprint Can be used to filter SSH keys by their fingerprint. The response will only contain the SSH key matching the specified fingerprint. (optional)
         * @return GetAllSshKeysRequestBuilder
         */
        public GetAllSshKeysRequestBuilder fingerprint(String fingerprint) {
            this.fingerprint = fingerprint;
            return this;
        }
        
        /**
         * Set labelSelector
         * @param labelSelector Filter resources by labels. The response will only contain resources matching the label selector. For more information, see \&quot;[Label Selector](https://docs.hetzner.cloud)\&quot;.  (optional)
         * @return GetAllSshKeysRequestBuilder
         */
        public GetAllSshKeysRequestBuilder labelSelector(String labelSelector) {
            this.labelSelector = labelSelector;
            return this;
        }
        
        /**
         * Set page
         * @param page Page number to return. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. (optional, default to 1)
         * @return GetAllSshKeysRequestBuilder
         */
        public GetAllSshKeysRequestBuilder page(Long page) {
            this.page = page;
            return this;
        }
        
        /**
         * Set perPage
         * @param perPage Maximum number of entries returned per page. For more information, see \&quot;[Pagination](https://docs.hetzner.cloud)\&quot;. (optional, default to 25)
         * @return GetAllSshKeysRequestBuilder
         */
        public GetAllSshKeysRequestBuilder perPage(Long perPage) {
            this.perPage = perPage;
            return this;
        }
        
        /**
         * Build call for getAllSshKeys
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;ssh_keys&#x60; key in the reply contains an array of SSH key objects with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getAllSshKeysCall(sort, name, fingerprint, labelSelector, page, perPage, _callback);
        }


        /**
         * Execute getAllSshKeys request
         * @return SshKeysGetAllSshKeysResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;ssh_keys&#x60; key in the reply contains an array of SSH key objects with this structure </td><td>  -  </td></tr>
         </table>
         */
        public SshKeysGetAllSshKeysResponse execute() throws ApiException {
            ApiResponse<SshKeysGetAllSshKeysResponse> localVarResp = getAllSshKeysWithHttpInfo(sort, name, fingerprint, labelSelector, page, perPage);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getAllSshKeys request with HTTP info returned
         * @return ApiResponse&lt;SshKeysGetAllSshKeysResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;ssh_keys&#x60; key in the reply contains an array of SSH key objects with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<SshKeysGetAllSshKeysResponse> executeWithHttpInfo() throws ApiException {
            return getAllSshKeysWithHttpInfo(sort, name, fingerprint, labelSelector, page, perPage);
        }

        /**
         * Execute getAllSshKeys request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;ssh_keys&#x60; key in the reply contains an array of SSH key objects with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<SshKeysGetAllSshKeysResponse> _callback) throws ApiException {
            return getAllSshKeysAsync(sort, name, fingerprint, labelSelector, page, perPage, _callback);
        }
    }

    /**
     * Get all SSH keys
     * Returns all SSH key objects.
     * @return GetAllSshKeysRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;ssh_keys&#x60; key in the reply contains an array of SSH key objects with this structure </td><td>  -  </td></tr>
     </table>
     */
    public GetAllSshKeysRequestBuilder getAllSshKeys() throws IllegalArgumentException {
        return new GetAllSshKeysRequestBuilder();
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
        String localVarPath = "/ssh_keys/{id}"
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


    private ApiResponse<SshKeysGetByIdResponse> getByIdWithHttpInfo(Long id) throws ApiException {
        okhttp3.Call localVarCall = getByIdValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<SshKeysGetByIdResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call getByIdAsync(Long id, final ApiCallback<SshKeysGetByIdResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = getByIdValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<SshKeysGetByIdResponse>(){}.getType();
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
            <tr><td> 200 </td><td> The &#x60;ssh_key&#x60; key in the reply contains an SSH key object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            return getByIdCall(id, _callback);
        }


        /**
         * Execute getById request
         * @return SshKeysGetByIdResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;ssh_key&#x60; key in the reply contains an SSH key object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public SshKeysGetByIdResponse execute() throws ApiException {
            ApiResponse<SshKeysGetByIdResponse> localVarResp = getByIdWithHttpInfo(id);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute getById request with HTTP info returned
         * @return ApiResponse&lt;SshKeysGetByIdResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;ssh_key&#x60; key in the reply contains an SSH key object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<SshKeysGetByIdResponse> executeWithHttpInfo() throws ApiException {
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
            <tr><td> 200 </td><td> The &#x60;ssh_key&#x60; key in the reply contains an SSH key object with this structure </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<SshKeysGetByIdResponse> _callback) throws ApiException {
            return getByIdAsync(id, _callback);
        }
    }

    /**
     * Get a SSH key
     * Returns a specific SSH key object.
     * @param id ID of the SSH key (required)
     * @return GetByIdRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;ssh_key&#x60; key in the reply contains an SSH key object with this structure </td><td>  -  </td></tr>
     </table>
     */
    public GetByIdRequestBuilder getById(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new GetByIdRequestBuilder(id);
    }
    private okhttp3.Call updateKeyCall(Long id, SshKeysUpdateKeyRequest sshKeysUpdateKeyRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = sshKeysUpdateKeyRequest;

        // create path and map variables
        String localVarPath = "/ssh_keys/{id}"
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
    private okhttp3.Call updateKeyValidateBeforeCall(Long id, SshKeysUpdateKeyRequest sshKeysUpdateKeyRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling updateKey(Async)");
        }

        return updateKeyCall(id, sshKeysUpdateKeyRequest, _callback);

    }


    private ApiResponse<SshKeysUpdateKeyResponse> updateKeyWithHttpInfo(Long id, SshKeysUpdateKeyRequest sshKeysUpdateKeyRequest) throws ApiException {
        okhttp3.Call localVarCall = updateKeyValidateBeforeCall(id, sshKeysUpdateKeyRequest, null);
        Type localVarReturnType = new TypeToken<SshKeysUpdateKeyResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    private okhttp3.Call updateKeyAsync(Long id, SshKeysUpdateKeyRequest sshKeysUpdateKeyRequest, final ApiCallback<SshKeysUpdateKeyResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = updateKeyValidateBeforeCall(id, sshKeysUpdateKeyRequest, _callback);
        Type localVarReturnType = new TypeToken<SshKeysUpdateKeyResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    public class UpdateKeyRequestBuilder {
        private final Long id;
        private Object labels;
        private String name;

        private UpdateKeyRequestBuilder(Long id) {
            this.id = id;
        }

        /**
         * Set labels
         * @param labels User-defined labels (key-value pairs) (optional)
         * @return UpdateKeyRequestBuilder
         */
        public UpdateKeyRequestBuilder labels(Object labels) {
            this.labels = labels;
            return this;
        }
        
        /**
         * Set name
         * @param name New name Name to set (optional)
         * @return UpdateKeyRequestBuilder
         */
        public UpdateKeyRequestBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        /**
         * Build call for updateKey
         * @param _callback ApiCallback API callback
         * @return Call to execute
         * @throws ApiException If fail to serialize the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;ssh_key&#x60; key in the reply contains the modified SSH key object with the new description </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call buildCall(final ApiCallback _callback) throws ApiException {
            SshKeysUpdateKeyRequest sshKeysUpdateKeyRequest = buildBodyParams();
            return updateKeyCall(id, sshKeysUpdateKeyRequest, _callback);
        }

        private SshKeysUpdateKeyRequest buildBodyParams() {
            SshKeysUpdateKeyRequest sshKeysUpdateKeyRequest = new SshKeysUpdateKeyRequest();
            sshKeysUpdateKeyRequest.labels(this.labels);
            sshKeysUpdateKeyRequest.name(this.name);
            return sshKeysUpdateKeyRequest;
        }

        /**
         * Execute updateKey request
         * @return SshKeysUpdateKeyResponse
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;ssh_key&#x60; key in the reply contains the modified SSH key object with the new description </td><td>  -  </td></tr>
         </table>
         */
        public SshKeysUpdateKeyResponse execute() throws ApiException {
            SshKeysUpdateKeyRequest sshKeysUpdateKeyRequest = buildBodyParams();
            ApiResponse<SshKeysUpdateKeyResponse> localVarResp = updateKeyWithHttpInfo(id, sshKeysUpdateKeyRequest);
            return localVarResp.getResponseBody();
        }

        /**
         * Execute updateKey request with HTTP info returned
         * @return ApiResponse&lt;SshKeysUpdateKeyResponse&gt;
         * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;ssh_key&#x60; key in the reply contains the modified SSH key object with the new description </td><td>  -  </td></tr>
         </table>
         */
        public ApiResponse<SshKeysUpdateKeyResponse> executeWithHttpInfo() throws ApiException {
            SshKeysUpdateKeyRequest sshKeysUpdateKeyRequest = buildBodyParams();
            return updateKeyWithHttpInfo(id, sshKeysUpdateKeyRequest);
        }

        /**
         * Execute updateKey request (asynchronously)
         * @param _callback The callback to be executed when the API call finishes
         * @return The request call
         * @throws ApiException If fail to process the API call, e.g. serializing the request body object
         * @http.response.details
         <table summary="Response Details" border="1">
            <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
            <tr><td> 200 </td><td> The &#x60;ssh_key&#x60; key in the reply contains the modified SSH key object with the new description </td><td>  -  </td></tr>
         </table>
         */
        public okhttp3.Call executeAsync(final ApiCallback<SshKeysUpdateKeyResponse> _callback) throws ApiException {
            SshKeysUpdateKeyRequest sshKeysUpdateKeyRequest = buildBodyParams();
            return updateKeyAsync(id, sshKeysUpdateKeyRequest, _callback);
        }
    }

    /**
     * Update an SSH key
     * Updates an SSH key. You can update an SSH key name and an SSH key labels.  Please note that when updating labels, the SSH key current set of labels will be replaced with the labels provided in the request body. So, for example, if you want to add a new label, you have to provide all existing labels plus the new label in the request body. 
     * @param id ID of the SSH key (required)
     * @return UpdateKeyRequestBuilder
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The &#x60;ssh_key&#x60; key in the reply contains the modified SSH key object with the new description </td><td>  -  </td></tr>
     </table>
     */
    public UpdateKeyRequestBuilder updateKey(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("\"id\" is required but got null");
        return new UpdateKeyRequestBuilder(id);
    }
}
