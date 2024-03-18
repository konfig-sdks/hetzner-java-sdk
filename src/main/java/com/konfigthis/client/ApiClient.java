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


package com.konfigthis.client;

import okhttp3.*;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.konfigthis.client.auth.Authentication;
import com.konfigthis.client.auth.HttpBasicAuth;
import com.konfigthis.client.auth.HttpBearerAuth;
import com.konfigthis.client.auth.ApiKeyAuth;

/**
 * <p>ApiClient class.</p>
 */
public class ApiClient extends ApiClientCustom {

    private String basePath = "https://api.hetzner.cloud/v1";
    private boolean debugging = false;
    private Map<String, String> defaultHeaderMap = new HashMap<String, String>();
    private Map<String, String> defaultCookieMap = new HashMap<String, String>();
    private String tempFolderPath = null;

    private Map<String, Authentication> authentications;

    private DateFormat dateFormat;
    private DateFormat datetimeFormat;
    private boolean lenientDatetimeFormat;
    private int dateLength;

    private InputStream sslCaCert;
    private boolean verifyingSsl;
    private KeyManager[] keyManagers;

    private OkHttpClient httpClient;
    private JSON json;

    private HttpLoggingInterceptor loggingInterceptor;


    /**
     * Basic constructor for ApiClient
     */
    public ApiClient() {
        this((OkHttpClient) null, null);
    }

    /**
     * Basic constructor with custom OkHttpClient
     *
     * @param client a {@link okhttp3.OkHttpClient} object
     */
    public ApiClient(OkHttpClient client) {
        this(client, null);
    }

    public ApiClient(OkHttpClient client, Configuration configuration) {
        init();
        if (configuration != null) {
            // Setting up authentications, if applicable, may rely on the following, so we initialize them first
            setBasePath(configuration.host);
        }
        // Setup authentications (key: authentication name, value: authentication).
        authentications.put("APIToken", new HttpBearerAuth("bearer"));
        // Prevent the authentications from being modified.
        authentications = Collections.unmodifiableMap(authentications);

        if (client == null) {
            initHttpClient();
        } else {
            this.httpClient = client;
        }

        if (configuration != null) {
            if (configuration.token != null) {
                this.setBearerToken(configuration.token);
            }
            setVerifyingSsl(configuration.verifyingSsl);
        }
    }

    private void initHttpClient() {
        initHttpClient(Collections.<Interceptor>emptyList());
    }

    private void initHttpClient(List<Interceptor> interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(getProgressInterceptor());
        for (Interceptor interceptor: interceptors) {
            builder.addInterceptor(interceptor);
        }
        builder.readTimeout(0, TimeUnit.MILLISECONDS);

        httpClient = builder.build();
    }

    private void init() {
        verifyingSsl = true;

        json = new JSON();

        // Set default User-Agent.
        setUserAgent("Konfig/1.0.0/java");

        authentications = new HashMap<String, Authentication>();
    }

    /**
     * Get base path
     *
     * @return Base path
     */
    public String getBasePath() {
        return basePath;
    }

    /**
     * Set base path
     *
     * @param basePath Base path of the URL (e.g https://api.hetzner.cloud/v1)
     * @return An instance of OkHttpClient
     */
    public ApiClient setBasePath(String basePath) {
        // strip trailing slash from basePath
        if (basePath != null && basePath.endsWith("/")) {
            basePath = basePath.substring(0, basePath.length() - 1);
        }
        this.basePath = basePath;
        return this;
    }


    /**
     * Get HTTP client
     *
     * @return An instance of OkHttpClient
     */
    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * Set HTTP client, which must never be null.
     *
     * @param newHttpClient An instance of OkHttpClient
     * @return Api Client
     * @throws java.lang.NullPointerException when newHttpClient is null
     */
    public ApiClient setHttpClient(OkHttpClient newHttpClient) {
        this.httpClient = Objects.requireNonNull(newHttpClient, "HttpClient must not be null!");
        return this;
    }

    /**
     * Get JSON
     *
     * @return JSON object
     */
    public JSON getJSON() {
        return json;
    }

    /**
     * Set JSON
     *
     * @param json JSON object
     * @return Api client
     */
    public ApiClient setJSON(JSON json) {
        this.json = json;
        return this;
    }

    /**
     * True if isVerifyingSsl flag is on
     *
     * @return True if isVerifySsl flag is on
     */
    public boolean isVerifyingSsl() {
        return verifyingSsl;
    }

    /**
     * Configure whether to verify certificate and hostname when making https requests.
     * Default to true.
     * NOTE: Do NOT set to false in production code, otherwise you would face multiple types of cryptographic attacks.
     *
     * @param verifyingSsl True to verify TLS/SSL connection
     * @return ApiClient
     */
    public ApiClient setVerifyingSsl(boolean verifyingSsl) {
        this.verifyingSsl = verifyingSsl;
        applySslSettings();
        return this;
    }

    /**
     * Get SSL CA cert.
     *
     * @return Input stream to the SSL CA cert
     */
    public InputStream getSslCaCert() {
        return sslCaCert;
    }

    /**
     * Configure the CA certificate to be trusted when making https requests.
     * Use null to reset to default.
     *
     * @param sslCaCert input stream for SSL CA cert
     * @return ApiClient
     */
    public ApiClient setSslCaCert(InputStream sslCaCert) {
        this.sslCaCert = sslCaCert;
        applySslSettings();
        return this;
    }

    /**
     * <p>Getter for the field <code>keyManagers</code>.</p>
     *
     * @return an array of {@link javax.net.ssl.KeyManager} objects
     */
    public KeyManager[] getKeyManagers() {
        return keyManagers;
    }

    /**
     * Configure client keys to use for authorization in an SSL session.
     * Use null to reset to default.
     *
     * @param managers The KeyManagers to use
     * @return ApiClient
     */
    public ApiClient setKeyManagers(KeyManager[] managers) {
        this.keyManagers = managers;
        applySslSettings();
        return this;
    }

    /**
     * <p>Getter for the field <code>dateFormat</code>.</p>
     *
     * @return a {@link java.text.DateFormat} object
     */
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * <p>Setter for the field <code>dateFormat</code>.</p>
     *
     * @param dateFormat a {@link java.text.DateFormat} object
     * @return a {@link com.konfigthis.client.ApiClient} object
     */
    public ApiClient setDateFormat(DateFormat dateFormat) {
        JSON.setDateFormat(dateFormat);
        return this;
    }

    /**
     * <p>Set SqlDateFormat.</p>
     *
     * @param dateFormat a {@link java.text.DateFormat} object
     * @return a {@link com.konfigthis.client.ApiClient} object
     */
    public ApiClient setSqlDateFormat(DateFormat dateFormat) {
        JSON.setSqlDateFormat(dateFormat);
        return this;
    }

    /**
     * <p>Set OffsetDateTimeFormat.</p>
     *
     * @param dateFormat a {@link java.time.format.DateTimeFormatter} object
     * @return a {@link com.konfigthis.client.ApiClient} object
     */
    public ApiClient setOffsetDateTimeFormat(DateTimeFormatter dateFormat) {
        JSON.setOffsetDateTimeFormat(dateFormat);
        return this;
    }

    /**
     * <p>Set LocalDateFormat.</p>
     *
     * @param dateFormat a {@link java.time.format.DateTimeFormatter} object
     * @return a {@link com.konfigthis.client.ApiClient} object
     */
    public ApiClient setLocalDateFormat(DateTimeFormatter dateFormat) {
        JSON.setLocalDateFormat(dateFormat);
        return this;
    }

    /**
     * <p>Set LenientOnJson.</p>
     *
     * @param lenientOnJson a boolean
     * @return a {@link com.konfigthis.client.ApiClient} object
     */
    public ApiClient setLenientOnJson(boolean lenientOnJson) {
        JSON.setLenientOnJson(lenientOnJson);
        return this;
    }

    /**
     * Get authentications (key: authentication name, value: authentication).
     *
     * @return Map of authentication objects
     */
    public Map<String, Authentication> getAuthentications() {
        return authentications;
    }

    /**
     * Get authentication for the given name.
     *
     * @param authName The authentication name
     * @return The authentication, null if not found
     */
    public Authentication getAuthentication(String authName) {
        return authentications.get(authName);
    }

        /**
        * Helper method to set access token for the first Bearer authentication.
        * @param bearerToken Bearer token
        */
    public void setBearerToken(String bearerToken) {
        for (Authentication auth : authentications.values()) {
            if (auth instanceof HttpBearerAuth) {
                ((HttpBearerAuth) auth).setBearerToken(bearerToken);
                return;
            }
        }
        throw new RuntimeException("No Bearer authentication configured!");
    }

    /**
     * Helper method to set username for the first HTTP basic authentication.
     *
     * @param username Username
     */
    public void setUsername(String username) {
        for (Authentication auth : authentications.values()) {
            if (auth instanceof HttpBasicAuth) {
                ((HttpBasicAuth) auth).setUsername(username);
                return;
            }
        }
        throw new RuntimeException("No HTTP basic authentication configured!");
    }

    /**
     * Helper method to set password for the first HTTP basic authentication.
     *
     * @param password Password
     */
    public void setPassword(String password) {
        for (Authentication auth : authentications.values()) {
            if (auth instanceof HttpBasicAuth) {
                ((HttpBasicAuth) auth).setPassword(password);
                return;
            }
        }
        throw new RuntimeException("No HTTP basic authentication configured!");
    }

    /**
     * Helper method to set access token for the first OAuth2 authentication.
     *
     * @param accessToken Access token
     */
    public void setAccessToken(String accessToken) {
        throw new RuntimeException("No OAuth2 authentication configured!");
    }

    /**
     * Set the User-Agent header's value (by adding to the default header map).
     *
     * @param userAgent HTTP request's user agent
     * @return ApiClient
     */
    public ApiClient setUserAgent(String userAgent) {
        addDefaultHeader("User-Agent", userAgent);
        return this;
    }

    /**
     * Add a default header.
     *
     * @param key The header's key
     * @param value The header's value
     * @return ApiClient
     */
    public ApiClient addDefaultHeader(String key, String value) {
        defaultHeaderMap.put(key, value);
        return this;
    }

    /**
     * Add a default cookie.
     *
     * @param key The cookie's key
     * @param value The cookie's value
     * @return ApiClient
     */
    public ApiClient addDefaultCookie(String key, String value) {
        defaultCookieMap.put(key, value);
        return this;
    }

    /**
     * Check that whether debugging is enabled for this API client.
     *
     * @return True if debugging is enabled, false otherwise.
     */
    public boolean isDebugging() {
        return debugging;
    }

    /**
     * Enable/disable debugging for this API client.
     *
     * @param debugging To enable (true) or disable (false) debugging
     * @return ApiClient
     */
    public ApiClient setDebugging(boolean debugging) {
        if (debugging != this.debugging) {
            if (debugging) {
                loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(Level.BODY);
                httpClient = httpClient.newBuilder().addInterceptor(loggingInterceptor).build();
            } else {
                final OkHttpClient.Builder builder = httpClient.newBuilder();
                builder.interceptors().remove(loggingInterceptor);
                httpClient = builder.build();
                loggingInterceptor = null;
            }
        }
        this.debugging = debugging;
        return this;
    }

    /**
     * The path of temporary folder used to store downloaded files from endpoints
     * with file response. The default value is <code>null</code>, i.e. using
     * the system's default temporary folder.
     *
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html#createTempFile(java.lang.String,%20java.lang.String,%20java.nio.file.attribute.FileAttribute...)">createTempFile</a>
     * @return Temporary folder path
     */
    public String getTempFolderPath() {
        return tempFolderPath;
    }

    /**
     * Set the temporary folder path (for downloading files)
     *
     * @param tempFolderPath Temporary folder path
     * @return ApiClient
     */
    public ApiClient setTempFolderPath(String tempFolderPath) {
        this.tempFolderPath = tempFolderPath;
        return this;
    }

    /**
     * Get connection timeout (in milliseconds).
     *
     * @return Timeout in milliseconds
     */
    public int getConnectTimeout() {
        return httpClient.connectTimeoutMillis();
    }

    /**
     * Sets the connect timeout (in milliseconds).
     * A value of 0 means no timeout, otherwise values must be between 1 and
     * {@link java.lang.Integer#MAX_VALUE}.
     *
     * @param connectionTimeout connection timeout in milliseconds
     * @return Api client
     */
    public ApiClient setConnectTimeout(int connectionTimeout) {
        httpClient = httpClient.newBuilder().connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS).build();
        return this;
    }

    /**
     * Get read timeout (in milliseconds).
     *
     * @return Timeout in milliseconds
     */
    public int getReadTimeout() {
        return httpClient.readTimeoutMillis();
    }

    /**
     * Sets the read timeout (in milliseconds).
     * A value of 0 means no timeout, otherwise values must be between 1 and
     * {@link java.lang.Integer#MAX_VALUE}.
     *
     * @param readTimeout read timeout in milliseconds
     * @return Api client
     */
    public ApiClient setReadTimeout(int readTimeout) {
        httpClient = httpClient.newBuilder().readTimeout(readTimeout, TimeUnit.MILLISECONDS).build();
        return this;
    }

    /**
     * Get write timeout (in milliseconds).
     *
     * @return Timeout in milliseconds
     */
    public int getWriteTimeout() {
        return httpClient.writeTimeoutMillis();
    }

    /**
     * Sets the write timeout (in milliseconds).
     * A value of 0 means no timeout, otherwise values must be between 1 and
     * {@link java.lang.Integer#MAX_VALUE}.
     *
     * @param writeTimeout connection timeout in milliseconds
     * @return Api client
     */
    public ApiClient setWriteTimeout(int writeTimeout) {
        httpClient = httpClient.newBuilder().writeTimeout(writeTimeout, TimeUnit.MILLISECONDS).build();
        return this;
    }


    /**
     * Format the given parameter object into string.
     *
     * @param param Parameter
     * @return String representation of the parameter
     */
    public String parameterToString(Object param) {
        if (param == null) {
            return "";
        } else if (param instanceof Date || param instanceof OffsetDateTime || param instanceof LocalDate) {
            //Serialize to json string and remove the " enclosing characters
            String jsonStr = JSON.serialize(param);
            return jsonStr.substring(1, jsonStr.length() - 1);
        } else if (param instanceof Collection) {
            StringBuilder b = new StringBuilder();
            for (Object o : (Collection) param) {
                if (b.length() > 0) {
                    b.append(",");
                }
                b.append(o);
            }
            return b.toString();
        } else {
            return String.valueOf(param);
        }
    }

    /**
     * Formats the specified query parameter to a list containing a single {@code Pair} object.
     *
     * Note that {@code value} must not be a collection.
     *
     * @param name The name of the parameter.
     * @param value The value of the parameter.
     * @return A list containing a single {@code Pair} object.
     */
    public List<Pair> parameterToPair(String name, Object value) {
        List<Pair> params = new ArrayList<Pair>();

        // preconditions
        if (name == null || name.isEmpty() || value == null || value instanceof Collection) {
            return params;
        }

        params.add(new Pair(name, parameterToString(value)));
        return params;
    }

    /**
     * Formats the specified collection query parameters to a list of {@code Pair} objects.
     *
     * Note that the values of each of the returned Pair objects are percent-encoded.
     *
     * @param collectionFormat The collection format of the parameter.
     * @param name The name of the parameter.
     * @param value The value of the parameter.
     * @return A list of {@code Pair} objects.
     */
    public List<Pair> parameterToPairs(String collectionFormat, String name, Collection value) {
        List<Pair> params = new ArrayList<Pair>();

        // preconditions
        if (name == null || name.isEmpty() || value == null || value.isEmpty()) {
            return params;
        }

        // create the params based on the collection format
        if ("multi".equals(collectionFormat)) {
            for (Object item : value) {
                params.add(new Pair(name, escapeString(parameterToString(item))));
            }
            return params;
        }

        // collectionFormat is assumed to be "csv" by default
        String delimiter = ",";

        // escape all delimiters except commas, which are URI reserved
        // characters
        if ("ssv".equals(collectionFormat)) {
            delimiter = escapeString(" ");
        } else if ("tsv".equals(collectionFormat)) {
            delimiter = escapeString("\t");
        } else if ("pipes".equals(collectionFormat)) {
            delimiter = escapeString("|");
        }

        StringBuilder sb = new StringBuilder();
        for (Object item : value) {
            sb.append(delimiter);
            sb.append(escapeString(parameterToString(item)));
        }

        params.add(new Pair(name, sb.substring(delimiter.length())));

        return params;
    }

    /**
     * Formats the specified collection path parameter to a string value.
     *
     * @param collectionFormat The collection format of the parameter.
     * @param value The value of the parameter.
     * @return String representation of the parameter
     */
    public String collectionPathParameterToString(String collectionFormat, Collection value) {
        // create the value based on the collection format
        if ("multi".equals(collectionFormat)) {
            // not valid for path params
            return parameterToString(value);
        }

        // collectionFormat is assumed to be "csv" by default
        String delimiter = ",";

        if ("ssv".equals(collectionFormat)) {
            delimiter = " ";
        } else if ("tsv".equals(collectionFormat)) {
            delimiter = "\t";
        } else if ("pipes".equals(collectionFormat)) {
            delimiter = "|";
        }

        StringBuilder sb = new StringBuilder() ;
        for (Object item : value) {
            sb.append(delimiter);
            sb.append(parameterToString(item));
        }

        return sb.substring(delimiter.length());
    }

    /**
     * Sanitize filename by removing path.
     * e.g. ../../sun.gif becomes sun.gif
     *
     * @param filename The filename to be sanitized
     * @return The sanitized filename
     */
    public String sanitizeFilename(String filename) {
        return filename.replaceAll(".*[/\\\\]", "");
    }

    /**
     * Check if the given MIME is a JSON MIME.
     * JSON MIME examples:
     *   application/json
     *   application/json; charset=UTF8
     *   APPLICATION/JSON
     *   application/vnd.company+json
     * "* / *" is also default to JSON
     * @param mime MIME (Multipurpose Internet Mail Extensions)
     * @return True if the given MIME is JSON, false otherwise.
     */
    public boolean isJsonMime(String mime) {
        String jsonMime = "(?i)^(application/json|[^;/ \t]+/[^;/ \t]+[+]json)[ \t]*(;.*)?$";
        return mime != null && (mime.matches(jsonMime) || mime.equals("*/*"));
    }

    /**
     * Select the Accept header's value from the given accepts array:
     *   if JSON exists in the given array, use it;
     *   otherwise use all of them (joining into a string)
     *
     * @param accepts The accepts array to select from
     * @return The Accept header to use. If the given array is empty,
     *   null will be returned (not to set the Accept header explicitly).
     */
    public String selectHeaderAccept(String[] accepts) {
        if (accepts.length == 0) {
            return null;
        }
        for (String accept : accepts) {
            if (isJsonMime(accept)) {
                return accept;
            }
        }
        return StringUtil.join(accepts, ",");
    }

    /**
     * Select the Content-Type header's value from the given array:
     *   if JSON exists in the given array, use it;
     *   otherwise use the first one of the array.
     *
     * @param contentTypes The Content-Type array to select from
     * @return The Content-Type header to use. If the given array is empty,
     *   returns null. If it matches "any", JSON will be used.
     */
    public String selectHeaderContentType(String[] contentTypes) {
        if (contentTypes.length == 0) {
            return null;
        }

        if (contentTypes[0].equals("*/*")) {
            return "application/json";
        }

        for (String contentType : contentTypes) {
            if (isJsonMime(contentType)) {
                return contentType;
            }
        }

        return contentTypes[0];
    }

    /**
     * Escape the given string to be used as URL query value.
     *
     * @param str String to be escaped
     * @return Escaped string
     */
    public String escapeString(String str) {
        try {
            return URLEncoder.encode(str, "utf8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * Deserialize response body to Java object, according to the return type and
     * the Content-Type response header.
     *
     * @param <T> Type
     * @param response HTTP response
     * @param returnType The type of the Java object
     * @return The deserialized Java object
     * @throws com.konfigthis.client.ApiException If fail to deserialize response body, i.e. cannot read response body
     *   or the Content-Type of the response is not supported.
     */
    @SuppressWarnings("unchecked")
    public <T> T deserialize(Response response, Type returnType) throws ApiException {
        if (response == null || returnType == null) {
            return null;
        }

        if ("byte[]".equals(returnType.toString())) {
            // Handle binary response (byte array).
            try {
                return (T) response.body().bytes();
            } catch (IOException e) {
                throw new ApiException(e);
            }
        } else if (returnType.equals(File.class)) {
            // Handle file downloading.
            return (T) downloadFileFromResponse(response);
        }

        String respBody;
        try {
            if (response.body() != null)
                respBody = response.body().string();
            else
                respBody = null;
        } catch (IOException e) {
            throw new ApiException(e);
        }

        if (respBody == null || "".equals(respBody)) {
            return null;
        }

        String contentType = response.headers().get("Content-Type");
        if (contentType == null) {
            // ensuring a default content type
            contentType = "application/json";
        }
        if (isJsonMime(contentType)) {
            return JSON.deserialize(respBody, returnType);
        } else if (returnType.equals(String.class)) {
            // Expecting string, return the raw response body.
            return (T) respBody;
        } else {
            throw new ApiException(
                    "Content type \"" + contentType + "\" is not supported for type: " + returnType,
                    response.code(),
                    response.headers().toMultimap(),
                    respBody, response.receivedResponseAtMillis() - response.sentRequestAtMillis());
        }
    }

    /**
     * Serialize the given Java object into request body according to the object's
     * class and the request Content-Type.
     *
     * @param obj The Java object
     * @param contentType The request Content-Type
     * @return The serialized request body
     * @throws com.konfigthis.client.ApiException If fail to serialize the given object
     */
    public RequestBody serialize(Object obj, String contentType) throws ApiException {
        if (obj instanceof byte[]) {
            // Binary (byte array) body parameter support.
            return RequestBody.create((byte[]) obj, MediaType.parse(contentType));
        } else if (obj instanceof File) {
            // File body parameter support.
            return RequestBody.create((File) obj, MediaType.parse(contentType));
        } else if ("text/plain".equals(contentType) && obj instanceof String) {
            return RequestBody.create((String) obj, MediaType.parse(contentType));
        } else if (isJsonMime(contentType)) {
            String content;
            if (obj != null) {
                content = JSON.serialize(obj);
            } else {
                content = null;
            }
            return RequestBody.create(content, MediaType.parse(contentType));
        } else if (obj instanceof String) {
            return RequestBody.create((String) obj, MediaType.parse(contentType));
        } else {
            throw new ApiException("Content type \"" + contentType + "\" is not supported");
        }
    }

    /**
     * Download file from the given response.
     *
     * @param response An instance of the Response object
     * @throws com.konfigthis.client.ApiException If fail to read file content from response and write to disk
     * @return Downloaded file
     */
    public File downloadFileFromResponse(Response response) throws ApiException {
        try {
            File file = prepareDownloadFile(response);
            BufferedSink sink = Okio.buffer(Okio.sink(file));
            sink.writeAll(response.body().source());
            sink.close();
            return file;
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    /**
     * Prepare file for download
     *
     * @param response An instance of the Response object
     * @return Prepared file for the download
     * @throws java.io.IOException If fail to prepare file for download
     */
    public File prepareDownloadFile(Response response) throws IOException {
        String filename = null;
        String contentDisposition = response.header("Content-Disposition");
        if (contentDisposition != null && !"".equals(contentDisposition)) {
            // Get filename from the Content-Disposition header.
            Pattern pattern = Pattern.compile("filename=['\"]?([^'\"\\s]+)['\"]?");
            Matcher matcher = pattern.matcher(contentDisposition);
            if (matcher.find()) {
                filename = sanitizeFilename(matcher.group(1));
            }
        }

        String prefix = null;
        String suffix = null;
        if (filename == null) {
            prefix = "download-";
            suffix = "";
        } else {
            int pos = filename.lastIndexOf(".");
            if (pos == -1) {
                prefix = filename + "-";
            } else {
                prefix = filename.substring(0, pos) + "-";
                suffix = filename.substring(pos);
            }
            // Files.createTempFile requires the prefix to be at least three characters long
            if (prefix.length() < 3)
                prefix = "download-";
        }

        if (tempFolderPath == null)
            return Files.createTempFile(prefix, suffix).toFile();
        else
            return Files.createTempFile(Paths.get(tempFolderPath), prefix, suffix).toFile();
    }

    /**
     * {@link #execute(Call, Type)}
     *
     * @param <T> Type
     * @param call An instance of the Call object
     * @return ApiResponse&lt;T&gt;
     * @throws com.konfigthis.client.ApiException If fail to execute the call
     */
    public <T> ApiResponse<T> execute(Call call) throws ApiException {
        return execute(call, null);
    }

    /**
     * Execute HTTP call and deserialize the HTTP response body into the given return type.
     *
     * @param returnType The return type used to deserialize HTTP response body
     * @param <T> The return type corresponding to (same with) returnType
     * @param call Call
     * @return ApiResponse object containing response status, headers and
     *   data, which is a Java object deserialized from response body and would be null
     *   when returnType is null.
     * @throws com.konfigthis.client.ApiException If fail to execute the call
     */
    public <T> ApiResponse<T> execute(Call call, Type returnType) throws ApiException {
        try {
            Response response = call.execute();
            T data = handleResponse(response, returnType);
            return new ApiResponse<T>(
                    call.request(),
                    response.code(),
                    response.headers().toMultimap(),
                    data,
                    response.receivedResponseAtMillis() -
                            response.sentRequestAtMillis());
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    /**
     * {@link #executeAsync(Call, Type, ApiCallback)}
     *
     * @param <T> Type
     * @param call An instance of the Call object
     * @param callback ApiCallback&lt;T&gt;
     */
    public <T> void executeAsync(Call call, ApiCallback<T> callback) {
        executeAsync(call, null, callback);
    }

    /**
     * Execute HTTP call asynchronously.
     *
     * @param <T> Type
     * @param call The callback to be executed when the API call finishes
     * @param returnType Return type
     * @param callback ApiCallback
     * @see #execute(Call, Type)
     */
    @SuppressWarnings("unchecked")
    public <T> void executeAsync(Call call, final Type returnType, final ApiCallback<T> callback) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(new ApiException(e), 0, null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                T result;
                try {
                    result = (T) handleResponse(response, returnType);
                } catch (ApiException e) {
                    callback.onFailure(e, response.code(), response.headers().toMultimap());
                    return;
                } catch (Exception e) {
                    callback.onFailure(new ApiException(e), response.code(), response.headers().toMultimap());
                    return;
                }
                callback.onSuccess(result, response.code(), response.headers().toMultimap());
            }
        });
    }

    /**
     * Handle the given response, return the deserialized object when the response is successful.
     *
     * @param <T> Type
     * @param response Response
     * @param returnType Return type
     * @return Type
     * @throws com.konfigthis.client.ApiException If the response has an unsuccessful status code or
     *                      fail to deserialize the response body
     */
    public <T> T handleResponse(Response response, Type returnType) throws ApiException {
        if (response.isSuccessful()) {
            if (returnType == null || response.code() == 204) {
                // returning null if the returnType is not defined,
                // or the status code is 204 (No Content)
                if (response.body() != null) {
                    try {
                        response.body().close();
                    } catch (Exception e) {
                        throw new ApiException(response.message(), e, response.code(), response.headers().toMultimap(), response.receivedResponseAtMillis() - response.sentRequestAtMillis());
                    }
                }
                return null;
            } else {
                return deserialize(response, returnType);
            }
        } else {
            String respBody = null;
            if (response.body() != null) {
                try {
                    respBody = response.body().string();
                } catch (IOException e) {
                    throw new ApiException(response.message(), e, response.code(), response.headers().toMultimap(), response.receivedResponseAtMillis() - response.sentRequestAtMillis());
                }
            }
            throw new ApiException(response.message(), response.code(), response.headers().toMultimap(), respBody, response.receivedResponseAtMillis() - response.sentRequestAtMillis());
        }
    }

    /**
     * Build HTTP call with the given options.
     *
     * @param baseUrl The base URL
     * @param path The sub-path of the HTTP URL
     * @param method The request method, one of "GET", "HEAD", "OPTIONS", "POST", "PUT", "PATCH" and "DELETE"
     * @param queryParams The query parameters
     * @param collectionQueryParams The collection query parameters
     * @param body The request body object
     * @param headerParams The header parameters
     * @param cookieParams The cookie parameters
     * @param formParams The form parameters
     * @param authNames The authentications to apply
     * @param callback Callback for upload/download progress
     * @return The HTTP call
     * @throws com.konfigthis.client.ApiException If fail to serialize the request body object
     */
    public Call buildCall(String baseUrl, String path, String method, List<Pair> queryParams, List<Pair> collectionQueryParams, Object body, Map<String, String> headerParams, Map<String, String> cookieParams, Map<String, Object> formParams, String[] authNames, ApiCallback callback) throws ApiException {
        Request request = buildRequest(baseUrl, path, method, queryParams, collectionQueryParams, body, headerParams, cookieParams, formParams, authNames, callback);

        return httpClient.newCall(request);
    }

    /**
     * Build an HTTP request with the given options.
     *
     * @param baseUrl The base URL
     * @param path The sub-path of the HTTP URL
     * @param method The request method, one of "GET", "HEAD", "OPTIONS", "POST", "PUT", "PATCH" and "DELETE"
     * @param queryParams The query parameters
     * @param collectionQueryParams The collection query parameters
     * @param body The request body object
     * @param headerParams The header parameters
     * @param cookieParams The cookie parameters
     * @param formParams The form parameters
     * @param authNames The authentications to apply
     * @param callback Callback for upload/download progress
     * @return The HTTP request
     * @throws com.konfigthis.client.ApiException If fail to serialize the request body object
     */
    public Request buildRequest(String baseUrl, String path, String method, List<Pair> queryParams, List<Pair> collectionQueryParams, Object body, Map<String, String> headerParams, Map<String, String> cookieParams, Map<String, Object> formParams, String[] authNames, ApiCallback callback) throws ApiException {
        requestBeforeHook(baseUrl, path, method, queryParams, collectionQueryParams, body, headerParams, cookieParams, formParams, authNames, this);

        // prepare HTTP request body
        RequestBody reqBody;
        String contentType = headerParams.get("Content-Type");

        if (!HttpMethod.permitsRequestBody(method)) {
            reqBody = null;
        } else if ("application/x-www-form-urlencoded".equals(contentType)) {
            reqBody = buildRequestBodyFormEncoding(formParams);
        } else if ("multipart/form-data".equals(contentType)) {
            reqBody = buildRequestBodyMultipart(formParams);
        } else if (body == null) {
            if ("DELETE".equals(method)) {
                // allow calling DELETE without sending a request body
                reqBody = null;
            } else {
                // use an empty request body (for POST, PUT and PATCH)
                reqBody = RequestBody.create("", contentType == null ? null : MediaType.parse(contentType));
            }
        } else {
            reqBody = serialize(body, contentType);
        }

        String payload = requestBodyToString(reqBody);

        // update parameters with authentication settings
        updateParamsForAuth(authNames, queryParams, headerParams, cookieParams, payload, method);

        final String url = buildUrl(baseUrl, path, queryParams, collectionQueryParams);

        requestAfterHook(url, path, method, queryParams, collectionQueryParams, body, headerParams, cookieParams, formParams, authNames, payload, this);

        final Request.Builder reqBuilder = new Request.Builder().url(url);
        processHeaderParams(headerParams, reqBuilder);
        processCookieParams(cookieParams, reqBuilder);

        // Associate callback with request (if not null) so interceptor can
        // access it when creating ProgressResponseBody
        reqBuilder.tag(callback);

        Request request = null;

        if (callback != null && reqBody != null) {
            ProgressRequestBody progressRequestBody = new ProgressRequestBody(reqBody, callback);
            request = reqBuilder.method(method, progressRequestBody).build();
        } else {
            request = reqBuilder.method(method, reqBody).build();
        }

        return request;
    }

    /**
     * Build full URL by concatenating base path, the given sub path and query parameters.
     *
     * @param baseUrl The base URL
     * @param path The sub path
     * @param queryParams The query parameters
     * @param collectionQueryParams The collection query parameters
     * @return The full URL
     */
    public String buildUrl(String baseUrl, String path, List<Pair> queryParams, List<Pair> collectionQueryParams) {
        final StringBuilder url = new StringBuilder();
        if (baseUrl != null) {
            url.append(baseUrl).append(path);
        } else {
            url.append(basePath).append(path);
        }

        if (queryParams != null && !queryParams.isEmpty()) {
            // support (constant) query string in `path`, e.g. "/posts?draft=1"
            String prefix = path.contains("?") ? "&" : "?";
            for (Pair param : queryParams) {
                if (param.getValue() != null) {
                    if (prefix != null) {
                        url.append(prefix);
                        prefix = null;
                    } else {
                        url.append("&");
                    }
                    String value = parameterToString(param.getValue());
                    url.append(escapeString(param.getName())).append("=").append(escapeString(value));
                }
            }
        }

        if (collectionQueryParams != null && !collectionQueryParams.isEmpty()) {
            String prefix = url.toString().contains("?") ? "&" : "?";
            for (Pair param : collectionQueryParams) {
                if (param.getValue() != null) {
                    if (prefix != null) {
                        url.append(prefix);
                        prefix = null;
                    } else {
                        url.append("&");
                    }
                    String value = parameterToString(param.getValue());
                    // collection query parameter value already escaped as part of parameterToPairs
                    url.append(escapeString(param.getName())).append("=").append(value);
                }
            }
        }

        return url.toString();
    }

    /**
     * Set header parameters to the request builder, including default headers.
     *
     * @param headerParams Header parameters in the form of Map
     * @param reqBuilder Request.Builder
     */
    public void processHeaderParams(Map<String, String> headerParams, Request.Builder reqBuilder) {
        for (Entry<String, String> param : headerParams.entrySet()) {
            reqBuilder.header(param.getKey(), parameterToString(param.getValue()));
        }
        for (Entry<String, String> header : defaultHeaderMap.entrySet()) {
            if (!headerParams.containsKey(header.getKey())) {
                reqBuilder.header(header.getKey(), parameterToString(header.getValue()));
            }
        }
    }

    /**
     * Set cookie parameters to the request builder, including default cookies.
     *
     * @param cookieParams Cookie parameters in the form of Map
     * @param reqBuilder Request.Builder
     */
    public void processCookieParams(Map<String, String> cookieParams, Request.Builder reqBuilder) {
        for (Entry<String, String> param : cookieParams.entrySet()) {
            reqBuilder.addHeader("Cookie", String.format("%s=%s", param.getKey(), param.getValue()));
        }
        for (Entry<String, String> param : defaultCookieMap.entrySet()) {
            if (!cookieParams.containsKey(param.getKey())) {
                reqBuilder.addHeader("Cookie", String.format("%s=%s", param.getKey(), param.getValue()));
            }
        }
    }

    /**
     * Update query and header parameters based on authentication settings.
     *
     * @param authNames The authentications to apply
     * @param queryParams List of query parameters
     * @param headerParams Map of header parameters
     * @param cookieParams Map of cookie parameters
     * @param payload HTTP request body
     * @param method HTTP method
     * @throws com.konfigthis.client.ApiException If fails to update the parameters
     */
    public void updateParamsForAuth(String[] authNames, List<Pair> queryParams, Map<String, String> headerParams,
                                    Map<String, String> cookieParams, String payload, String method) throws ApiException {
        for (String authName : authNames) {
            Authentication auth = authentications.get(authName);
            if (auth == null) {
                throw new RuntimeException("Authentication undefined: " + authName);
            }
            auth.applyToParams(queryParams, headerParams, cookieParams, payload, method);
        }
    }

    /**
     * Build a form-encoding request body with the given form parameters.
     *
     * @param formParams Form parameters in the form of Map
     * @return RequestBody
     */
    public RequestBody buildRequestBodyFormEncoding(Map<String, Object> formParams) {
        okhttp3.FormBody.Builder formBuilder = new okhttp3.FormBody.Builder();
        for (Entry<String, Object> param : formParams.entrySet()) {
            formBuilder.add(param.getKey(), parameterToString(param.getValue()));
        }
        return formBuilder.build();
    }

    /**
     * Build a multipart (file uploading) request body with the given form parameters,
     * which could contain text fields and file fields.
     *
     * @param formParams Form parameters in the form of Map
     * @return RequestBody
     */
    public RequestBody buildRequestBodyMultipart(Map<String, Object> formParams) {
        MultipartBody.Builder mpBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Entry<String, Object> param : formParams.entrySet()) {
            if (param.getValue() instanceof File) {
                File file = (File) param.getValue();
                addPartToMultiPartBuilder(mpBuilder, param.getKey(), file);
            } else if (param.getValue() instanceof List) {
                List list = (List) param.getValue();
                for (Object item: list) {
                    if (item instanceof File) {
                        addPartToMultiPartBuilder(mpBuilder, param.getKey(), (File) item);
                    } else {
                        addPartToMultiPartBuilder(mpBuilder, param.getKey(), param.getValue());
                    }
                }
            } else {
                addPartToMultiPartBuilder(mpBuilder, param.getKey(), param.getValue());
            }
        }
        return mpBuilder.build();
    }

    /**
     * Guess Content-Type header from the given file (defaults to "application/octet-stream").
     *
     * @param file The given file
     * @return The guessed Content-Type
     */
    public String guessContentTypeFromFile(File file) {
        String contentType = URLConnection.guessContentTypeFromName(file.getName());
        if (contentType == null) {
            return "application/octet-stream";
        } else {
            return contentType;
        }
    }

    /**
     * Add a Content-Disposition Header for the given key and file to the MultipartBody Builder.
     *
     * @param mpBuilder MultipartBody.Builder
     * @param key The key of the Header element
     * @param file The file to add to the Header
     */
    private void addPartToMultiPartBuilder(MultipartBody.Builder mpBuilder, String key, File file) {
        Headers partHeaders = Headers.of("Content-Disposition", "form-data; name=\"" + key + "\"; filename=\"" + file.getName() + "\"");
        MediaType mediaType = MediaType.parse(guessContentTypeFromFile(file));
        mpBuilder.addPart(partHeaders, RequestBody.create(file, mediaType));
    }

    /**
     * Add a Content-Disposition Header for the given key and complex object to the MultipartBody Builder.
     *
     * @param mpBuilder MultipartBody.Builder
     * @param key The key of the Header element
     * @param obj The complex object to add to the Header
     */
    private void addPartToMultiPartBuilder(MultipartBody.Builder mpBuilder, String key, Object obj) {
        RequestBody requestBody;
        if (obj instanceof String) {
            requestBody = RequestBody.create((String) obj, MediaType.parse("text/plain"));
        } else {
            String content;
            if (obj != null) {
                content = JSON.serialize(obj);
            } else {
                content = null;
            }
            requestBody = RequestBody.create(content, MediaType.parse("application/json"));
        }

        Headers partHeaders = Headers.of("Content-Disposition", "form-data; name=\"" + key + "\"");
        mpBuilder.addPart(partHeaders, requestBody);
    }

    /**
     * Get network interceptor to add it to the httpClient to track download progress for
     * async requests.
     */
    private Interceptor getProgressInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                final Request request = chain.request();
                final Response originalResponse = chain.proceed(request);
                if (request.tag() instanceof ApiCallback) {
                    final ApiCallback callback = (ApiCallback) request.tag();
                    return originalResponse.newBuilder()
                        .body(new ProgressResponseBody(originalResponse.body(), callback))
                        .build();
                }
                return originalResponse;
            }
        };
    }

    /**
     * Apply SSL related settings to httpClient according to the current values of
     * verifyingSsl and sslCaCert.
     */
    private void applySslSettings() {
        try {
            TrustManager[] trustManagers;
            HostnameVerifier hostnameVerifier;
            if (!verifyingSsl) {
                trustManagers = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };
                hostnameVerifier = new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                };
            } else {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

                if (sslCaCert == null) {
                    trustManagerFactory.init((KeyStore) null);
                } else {
                    char[] password = null; // Any password will work.
                    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                    Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(sslCaCert);
                    if (certificates.isEmpty()) {
                        throw new IllegalArgumentException("expected non-empty set of trusted certificates");
                    }
                    KeyStore caKeyStore = newEmptyKeyStore(password);
                    int index = 0;
                    for (Certificate certificate : certificates) {
                        String certificateAlias = "ca" + (index++);
                        caKeyStore.setCertificateEntry(certificateAlias, certificate);
                    }
                    trustManagerFactory.init(caKeyStore);
                }
                trustManagers = trustManagerFactory.getTrustManagers();
                hostnameVerifier = OkHostnameVerifier.INSTANCE;
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagers, trustManagers, new SecureRandom());
            httpClient = httpClient.newBuilder()
                            .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagers[0])
                            .hostnameVerifier(hostnameVerifier)
                            .build();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Convert the HTTP request body to a string.
     *
     * @param requestBody The HTTP request object
     * @return The string representation of the HTTP request body
     * @throws com.konfigthis.client.ApiException If fail to serialize the request body object into a string
     */
    private String requestBodyToString(RequestBody requestBody) throws ApiException {
        if (requestBody != null) {
            try {
                final Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                return buffer.readUtf8();
            } catch (final IOException e) {
                throw new ApiException(e);
            }
        }

        // empty http request body
        return "";
    }
}
