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

import com.konfigthis.client.ApiException;
import com.konfigthis.client.ApiClient;
import com.konfigthis.client.ApiException;
import com.konfigthis.client.Configuration;
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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ServerActionsApi
 */
@Disabled
public class ServerActionsApiTest {

    private static ServerActionsApi api;

    
    @BeforeAll
    public static void beforeClass() {
        ApiClient apiClient = Configuration.getDefaultApiClient();
        api = new ServerActionsApi(apiClient);
    }

    /**
     * Add a Server to a Placement Group
     *
     * Adds a Server to a Placement Group.  Server must be powered off for this command to succeed.  #### Call specific error codes  | Code                          | Description                                                          | |-------------------------------|----------------------------------------------------------------------| | &#x60;server_not_stopped&#x60;          | The action requires a stopped server                                 | 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void addToPlacementGroupTest() throws ApiException {
        Long placementGroup = null;
        Long id = null;
        ServerActionsAddToPlacementGroupResponse response = api.addToPlacementGroup(placementGroup, id)
                .execute();
        // TODO: test validations
    }

    /**
     * Attach an ISO to a Server
     *
     * Attaches an ISO to a Server. The Server will immediately see it as a new disk. An already attached ISO will automatically be detached before the new ISO is attached.  Servers with attached ISOs have a modified boot order: They will try to boot from the ISO first before falling back to hard disk. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void attachIsoToServerTest() throws ApiException {
        String iso = null;
        Long id = null;
        ServerActionsAttachIsoToServerResponse response = api.attachIsoToServer(iso, id)
                .execute();
        // TODO: test validations
    }

    /**
     * Attach a Server to a Network
     *
     * Attaches a Server to a network. This will complement the fixed public Server interface by adding an additional ethernet interface to the Server which is connected to the specified network.  The Server will get an IP auto assigned from a subnet of type &#x60;server&#x60; in the same &#x60;network_zone&#x60;.  Using the &#x60;alias_ips&#x60; attribute you can also define one or more additional IPs to the Servers. Please note that you will have to configure these IPs by hand on your Server since only the primary IP will be given out by DHCP.  **Call specific error codes**  | Code                             | Description                                                           | |----------------------------------|-----------------------------------------------------------------------| | &#x60;server_already_attached&#x60;        | The server is already attached to the network                         | | &#x60;ip_not_available&#x60;               | The provided Network IP is not available                              | | &#x60;no_subnet_available&#x60;            | No Subnet or IP is available for the Server within the network        | | &#x60;networks_overlap&#x60;               | The network IP range overlaps with one of the server networks         | 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void attachToNetworkTest() throws ApiException {
        Long network = null;
        Long id = null;
        List<String> aliasIps = null;
        String ip = null;
        ServerActionsAttachToNetworkResponse response = api.attachToNetwork(network, id)
                .aliasIps(aliasIps)
                .ip(ip)
                .execute();
        // TODO: test validations
    }

    /**
     * Change alias IPs of a Network
     *
     * Changes the alias IPs of an already attached Network. Note that the existing aliases for the specified Network will be replaced with these provided in the request body. So if you want to add an alias IP, you have to provide the existing ones from the Network plus the new alias IP in the request body.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void changeAliasIpsTest() throws ApiException {
        List<String> aliasIps = null;
        Long network = null;
        Long id = null;
        ServerActionsChangeAliasIpsResponse response = api.changeAliasIps(aliasIps, network, id)
                .execute();
        // TODO: test validations
    }

    /**
     * Change reverse DNS entry for this Server
     *
     * Changes the hostname that will appear when getting the hostname belonging to the primary IPs (IPv4 and IPv6) of this Server.  Floating IPs assigned to the Server are not affected by this. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void changeDnsPtrTest() throws ApiException {
        String dnsPtr = null;
        String ip = null;
        Long id = null;
        ServerActionsChangeDnsPtrResponse response = api.changeDnsPtr(dnsPtr, ip, id)
                .execute();
        // TODO: test validations
    }

    /**
     * Change Server Protection
     *
     * Changes the protection configuration of the Server.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void changeProtectionTest() throws ApiException {
        Long id = null;
        Boolean delete = null;
        Boolean rebuild = null;
        ServerActionsChangeProtectionResponse response = api.changeProtection(id)
                .delete(delete)
                .rebuild(rebuild)
                .execute();
        // TODO: test validations
    }

    /**
     * Change the Type of a Server
     *
     * Changes the type (Cores, RAM and disk sizes) of a Server.  Server must be powered off for this command to succeed.  This copies the content of its disk, and starts it again.  You can only migrate to Server types with the same &#x60;storage_type&#x60; and equal or bigger disks. Shrinking disks is not possible as it might destroy data.  If the disk gets upgraded, the Server type can not be downgraded any more. If you plan to downgrade the Server type, set &#x60;upgrade_disk&#x60; to &#x60;false&#x60;.  #### Call specific error codes  | Code                          | Description                                                          | |-------------------------------|----------------------------------------------------------------------| | &#x60;invalid_server_type&#x60;         | The server type does not fit for the given server or is deprecated   | | &#x60;server_not_stopped&#x60;          | The action requires a stopped server                                 | 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void changeServerTypeTest() throws ApiException {
        String serverType = null;
        Boolean upgradeDisk = null;
        Long id = null;
        ServerActionsChangeServerTypeResponse response = api.changeServerType(serverType, upgradeDisk, id)
                .execute();
        // TODO: test validations
    }

    /**
     * Create Image from a Server
     *
     * Creates an Image (snapshot) from a Server by copying the contents of its disks. This creates a snapshot of the current state of the disk and copies it into an Image. If the Server is currently running you must make sure that its disk content is consistent. Otherwise, the created Image may not be readable.  To make sure disk content is consistent, we recommend to shut down the Server prior to creating an Image.  You can either create a &#x60;backup&#x60; Image that is bound to the Server and therefore will be deleted when the Server is deleted, or you can create a &#x60;snapshot&#x60; Image which is completely independent of the Server it was created from and will survive Server deletion. Backup Images are only available when the backup option is enabled for the Server. Snapshot Images are billed on a per GB basis. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void createImageTest() throws ApiException {
        Long id = null;
        String description = null;
        ServerActionsCreateImageRequestLabels labels = null;
        String type = null;
        ServerActionsCreateImageResponse response = api.createImage(id)
                .description(description)
                .labels(labels)
                .type(type)
                .execute();
        // TODO: test validations
    }

    /**
     * Detach a Server from a Network
     *
     * Detaches a Server from a network. The interface for this network will vanish.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void detachFromNetworkTest() throws ApiException {
        Long network = null;
        Long id = null;
        ServerActionsDetachFromNetworkResponse response = api.detachFromNetwork(network, id)
                .execute();
        // TODO: test validations
    }

    /**
     * Detach an ISO from a Server
     *
     * Detaches an ISO from a Server. In case no ISO Image is attached to the Server, the status of the returned Action is immediately set to &#x60;success&#x60;
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void detachIsoFromServerTest() throws ApiException {
        Long id = null;
        ServerActionsDetachIsoFromServerResponse response = api.detachIsoFromServer(id)
                .execute();
        // TODO: test validations
    }

    /**
     * Disable Backups for a Server
     *
     * Disables the automatic backup option and deletes all existing Backups for a Server. No more additional charges for backups will be made.  Caution: This immediately removes all existing backups for the Server! 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void disableBackupTest() throws ApiException {
        Long id = null;
        ServerActionsDisableBackupResponse response = api.disableBackup(id)
                .execute();
        // TODO: test validations
    }

    /**
     * Disable Rescue Mode for a Server
     *
     * Disables the Hetzner Rescue System for a Server. This makes a Server start from its disks on next reboot.  Rescue Mode is automatically disabled when you first boot into it or if you do not use it for 60 minutes.  Disabling rescue mode will not reboot your Server — you will have to do this yourself. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void disableRescueModeTest() throws ApiException {
        Long id = null;
        ServerActionsDisableRescueModeResponse response = api.disableRescueMode(id)
                .execute();
        // TODO: test validations
    }

    /**
     * Enable and Configure Backups for a Server
     *
     * Enables and configures the automatic daily backup option for the Server. Enabling automatic backups will increase the price of the Server by 20%. In return, you will get seven slots where Images of type backup can be stored.  Backups are automatically created daily. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void enableBackupTest() throws ApiException {
        Long id = null;
        ServerActionsEnableBackupResponse response = api.enableBackup(id)
                .execute();
        // TODO: test validations
    }

    /**
     * Enable Rescue Mode for a Server
     *
     * Enable the Hetzner Rescue System for this Server. The next time a Server with enabled rescue mode boots it will start a special minimal Linux distribution designed for repair and reinstall.  In case a Server cannot boot on its own you can use this to access a Server’s disks.  Rescue Mode is automatically disabled when you first boot into it or if you do not use it for 60 minutes.  Enabling rescue mode will not [reboot](https://docs.hetzner.cloud/#server-actions-soft-reboot-a-server) your Server — you will have to do this yourself. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void enableRescueModeTest() throws ApiException {
        Long id = null;
        List<Long> sshKeys = null;
        String type = null;
        ServerActionsEnableRescueModeResponse response = api.enableRescueMode(id)
                .sshKeys(sshKeys)
                .type(type)
                .execute();
        // TODO: test validations
    }

    /**
     * Get an Action
     *
     * Returns a specific Action object.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getActionByIdTest() throws ApiException {
        Long id = null;
        ServerActionsGetActionByIdResponse response = api.getActionById(id)
                .execute();
        // TODO: test validations
    }

    /**
     * Get an Action for a Server
     *
     * Returns a specific Action object for a Server.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getActionById_0Test() throws ApiException {
        Long id = null;
        Long actionId = null;
        ServerActionsGetActionById200Response response = api.getActionById_0(id, actionId)
                .execute();
        // TODO: test validations
    }

    /**
     * Get all Actions
     *
     * Returns all Action objects. You can &#x60;sort&#x60; the results by using the sort URI parameter, and filter them with the &#x60;status&#x60; and &#x60;id&#x60; parameter.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getAllTest() throws ApiException {
        Long id = null;
        String sort = null;
        String status = null;
        Long page = null;
        Long perPage = null;
        ServerActionsGetAllResponse response = api.getAll()
                .id(id)
                .sort(sort)
                .status(status)
                .page(page)
                .perPage(perPage)
                .execute();
        // TODO: test validations
    }

    /**
     * Get all Actions for a Server
     *
     * Returns all Action objects for a Server. You can &#x60;sort&#x60; the results by using the sort URI parameter, and filter them with the &#x60;status&#x60; parameter.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getAllActionsTest() throws ApiException {
        Long id = null;
        String sort = null;
        String status = null;
        Long page = null;
        Long perPage = null;
        ServerActionsGetAllActionsResponse response = api.getAllActions(id)
                .sort(sort)
                .status(status)
                .page(page)
                .perPage(perPage)
                .execute();
        // TODO: test validations
    }

    /**
     * Shutdown a Server
     *
     * Shuts down a Server gracefully by sending an ACPI shutdown request. The Server operating system must support ACPI and react to the request, otherwise the Server will not shut down. Please note that the &#x60;action&#x60; status in this case only reflects whether the action was sent to the server. It does not mean that the server actually shut down successfully. If you need to ensure that the server is off, use the &#x60;poweroff&#x60; action 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void gracefulShutdownTest() throws ApiException {
        Long id = null;
        ServerActionsGracefulShutdownResponse response = api.gracefulShutdown(id)
                .execute();
        // TODO: test validations
    }

    /**
     * Power off a Server
     *
     * Cuts power to the Server. This forcefully stops it without giving the Server operating system time to gracefully stop. May lead to data loss, equivalent to pulling the power cord. Power off should only be used when shutdown does not work.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void powerOffServerTest() throws ApiException {
        Long id = null;
        ServerActionsPowerOffServerResponse response = api.powerOffServer(id)
                .execute();
        // TODO: test validations
    }

    /**
     * Power on a Server
     *
     * Starts a Server by turning its power on.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void powerOnServerTest() throws ApiException {
        Long id = null;
        ServerActionsPowerOnServerResponse response = api.powerOnServer(id)
                .execute();
        // TODO: test validations
    }

    /**
     * Rebuild a Server from an Image
     *
     * Rebuilds a Server overwriting its disk with the content of an Image, thereby **destroying all data** on the target Server  The Image can either be one you have created earlier (&#x60;backup&#x60; or &#x60;snapshot&#x60; Image) or it can be a completely fresh &#x60;system&#x60; Image provided by us. You can get a list of all available Images with &#x60;GET /images&#x60;.  Your Server will automatically be powered off before the rebuild command executes. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void rebuildServerFromImageTest() throws ApiException {
        String image = null;
        Long id = null;
        ServerActionsRebuildServerFromImageResponse response = api.rebuildServerFromImage(image, id)
                .execute();
        // TODO: test validations
    }

    /**
     * Remove from Placement Group
     *
     * Removes a Server from a Placement Group. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void removeFromPlacementGroupTest() throws ApiException {
        Long id = null;
        ServerActionsRemoveFromPlacementGroupResponse response = api.removeFromPlacementGroup(id)
                .execute();
        // TODO: test validations
    }

    /**
     * Request Console for a Server
     *
     * Requests credentials for remote access via VNC over websocket to keyboard, monitor, and mouse for a Server. The provided URL is valid for 1 minute, after this period a new url needs to be created to connect to the Server. How long the connection is open after the initial connect is not subject to this timeout.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void requestConsoleTest() throws ApiException {
        Long id = null;
        ServerActionsRequestConsoleResponse response = api.requestConsole(id)
                .execute();
        // TODO: test validations
    }

    /**
     * Reset a Server
     *
     * Cuts power to a Server and starts it again. This forcefully stops it without giving the Server operating system time to gracefully stop. This may lead to data loss, it’s equivalent to pulling the power cord and plugging it in again. Reset should only be used when reboot does not work.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void resetServerTest() throws ApiException {
        Long id = null;
        ServerActionsResetServerResponse response = api.resetServer(id)
                .execute();
        // TODO: test validations
    }

    /**
     * Reset root Password of a Server
     *
     * Resets the root password. Only works for Linux systems that are running the qemu guest agent. Server must be powered on (status &#x60;running&#x60;) in order for this operation to succeed.  This will generate a new password for this Server and return it.  If this does not succeed you can use the rescue system to netboot the Server and manually change your Server password by hand. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void resetServerPasswordTest() throws ApiException {
        Long id = null;
        ServerActionsResetServerPasswordResponse response = api.resetServerPassword(id)
                .execute();
        // TODO: test validations
    }

    /**
     * Soft-reboot a Server
     *
     * Reboots a Server gracefully by sending an ACPI request. The Server operating system must support ACPI and react to the request, otherwise the Server will not reboot.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void softRebootServerTest() throws ApiException {
        Long id = null;
        ServerActionsSoftRebootServerResponse response = api.softRebootServer(id)
                .execute();
        // TODO: test validations
    }

}
