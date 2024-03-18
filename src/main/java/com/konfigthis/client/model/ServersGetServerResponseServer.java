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


package com.konfigthis.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.konfigthis.client.model.PlacementGroupNullableProperty2;
import com.konfigthis.client.model.ServersGetServerResponseServerDatacenter;
import com.konfigthis.client.model.ServersGetServerResponseServerImage;
import com.konfigthis.client.model.ServersGetServerResponseServerIso;
import com.konfigthis.client.model.ServersGetServerResponseServerPrivateNetInner;
import com.konfigthis.client.model.ServersGetServerResponseServerProtection;
import com.konfigthis.client.model.ServersGetServerResponseServerPublicNet;
import com.konfigthis.client.model.ServersGetServerResponseServerServerType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.konfigthis.client.JSON;

/**
 * ServersGetServerResponseServer
 */@javax.annotation.Generated(value = "Generated by https://konfigthis.com")
public class ServersGetServerResponseServer {
  public static final String SERIALIZED_NAME_BACKUP_WINDOW = "backup_window";
  @SerializedName(SERIALIZED_NAME_BACKUP_WINDOW)
  private String backupWindow;

  public static final String SERIALIZED_NAME_CREATED = "created";
  @SerializedName(SERIALIZED_NAME_CREATED)
  private String created;

  public static final String SERIALIZED_NAME_DATACENTER = "datacenter";
  @SerializedName(SERIALIZED_NAME_DATACENTER)
  private ServersGetServerResponseServerDatacenter datacenter;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_IMAGE = "image";
  @SerializedName(SERIALIZED_NAME_IMAGE)
  private ServersGetServerResponseServerImage image;

  public static final String SERIALIZED_NAME_INCLUDED_TRAFFIC = "included_traffic";
  @SerializedName(SERIALIZED_NAME_INCLUDED_TRAFFIC)
  private Long includedTraffic;

  public static final String SERIALIZED_NAME_INGOING_TRAFFIC = "ingoing_traffic";
  @SerializedName(SERIALIZED_NAME_INGOING_TRAFFIC)
  private Long ingoingTraffic;

  public static final String SERIALIZED_NAME_ISO = "iso";
  @SerializedName(SERIALIZED_NAME_ISO)
  private ServersGetServerResponseServerIso iso;

  public static final String SERIALIZED_NAME_LABELS = "labels";
  @SerializedName(SERIALIZED_NAME_LABELS)
  private Map<String, String> labels = new HashMap<>();

  public static final String SERIALIZED_NAME_LOAD_BALANCERS = "load_balancers";
  @SerializedName(SERIALIZED_NAME_LOAD_BALANCERS)
  private List<Long> loadBalancers = null;

  public static final String SERIALIZED_NAME_LOCKED = "locked";
  @SerializedName(SERIALIZED_NAME_LOCKED)
  private Boolean locked;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_OUTGOING_TRAFFIC = "outgoing_traffic";
  @SerializedName(SERIALIZED_NAME_OUTGOING_TRAFFIC)
  private Long outgoingTraffic;

  public static final String SERIALIZED_NAME_PLACEMENT_GROUP = "placement_group";
  @SerializedName(SERIALIZED_NAME_PLACEMENT_GROUP)
  private PlacementGroupNullableProperty2 placementGroup;

  public static final String SERIALIZED_NAME_PRIMARY_DISK_SIZE = "primary_disk_size";
  @SerializedName(SERIALIZED_NAME_PRIMARY_DISK_SIZE)
  private Double primaryDiskSize;

  public static final String SERIALIZED_NAME_PRIVATE_NET = "private_net";
  @SerializedName(SERIALIZED_NAME_PRIVATE_NET)
  private List<ServersGetServerResponseServerPrivateNetInner> privateNet = new ArrayList<>();

  public static final String SERIALIZED_NAME_PROTECTION = "protection";
  @SerializedName(SERIALIZED_NAME_PROTECTION)
  private ServersGetServerResponseServerProtection protection;

  public static final String SERIALIZED_NAME_PUBLIC_NET = "public_net";
  @SerializedName(SERIALIZED_NAME_PUBLIC_NET)
  private ServersGetServerResponseServerPublicNet publicNet;

  public static final String SERIALIZED_NAME_RESCUE_ENABLED = "rescue_enabled";
  @SerializedName(SERIALIZED_NAME_RESCUE_ENABLED)
  private Boolean rescueEnabled;

  public static final String SERIALIZED_NAME_SERVER_TYPE = "server_type";
  @SerializedName(SERIALIZED_NAME_SERVER_TYPE)
  private ServersGetServerResponseServerServerType serverType;

  /**
   * Status of the Server
   */
  @JsonAdapter(StatusEnum.Adapter.class)
 public enum StatusEnum {
    RUNNING("running"),
    
    INITIALIZING("initializing"),
    
    STARTING("starting"),
    
    STOPPING("stopping"),
    
    FALSE("false"),
    
    DELETING("deleting"),
    
    MIGRATING("migrating"),
    
    REBUILDING("rebuilding"),
    
    UNKNOWN("unknown");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<StatusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StatusEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return StatusEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private StatusEnum status;

  public static final String SERIALIZED_NAME_VOLUMES = "volumes";
  @SerializedName(SERIALIZED_NAME_VOLUMES)
  private List<Long> volumes = null;

  public ServersGetServerResponseServer() {
  }

  public ServersGetServerResponseServer backupWindow(String backupWindow) {
    
    
    
    
    this.backupWindow = backupWindow;
    return this;
  }

   /**
   * Time window (UTC) in which the backup will run, or null if the backups are not enabled
   * @return backupWindow
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "22-02", required = true, value = "Time window (UTC) in which the backup will run, or null if the backups are not enabled")

  public String getBackupWindow() {
    return backupWindow;
  }


  public void setBackupWindow(String backupWindow) {
    
    
    
    this.backupWindow = backupWindow;
  }


  public ServersGetServerResponseServer created(String created) {
    
    
    
    
    this.created = created;
    return this;
  }

   /**
   * Point in time when the Resource was created (in ISO-8601 format).
   * @return created
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "2016-01-30T23:55:00+00:00", required = true, value = "Point in time when the Resource was created (in ISO-8601 format).")

  public String getCreated() {
    return created;
  }


  public void setCreated(String created) {
    
    
    
    this.created = created;
  }


  public ServersGetServerResponseServer datacenter(ServersGetServerResponseServerDatacenter datacenter) {
    
    
    
    
    this.datacenter = datacenter;
    return this;
  }

   /**
   * Get datacenter
   * @return datacenter
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public ServersGetServerResponseServerDatacenter getDatacenter() {
    return datacenter;
  }


  public void setDatacenter(ServersGetServerResponseServerDatacenter datacenter) {
    
    
    
    this.datacenter = datacenter;
  }


  public ServersGetServerResponseServer id(Long id) {
    
    if (id != null && id > 9007199254740991) {
      throw new IllegalArgumentException("Invalid value for id. Must be less than or equal to 9007199254740991.");
    }
    
    
    this.id = id;
    return this;
  }

   /**
   * ID of the Resource. Limited to 52 bits to ensure compatibility with JSON double precision floats. 
   * maximum: 9007199254740991
   * @return id
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "42", required = true, value = "ID of the Resource. Limited to 52 bits to ensure compatibility with JSON double precision floats. ")

  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    
    if (id != null && id > 9007199254740991) {
      throw new IllegalArgumentException("Invalid value for id. Must be less than or equal to 9007199254740991.");
    }
    
    this.id = id;
  }


  public ServersGetServerResponseServer image(ServersGetServerResponseServerImage image) {
    
    
    
    
    this.image = image;
    return this;
  }

   /**
   * Get image
   * @return image
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(required = true, value = "")

  public ServersGetServerResponseServerImage getImage() {
    return image;
  }


  public void setImage(ServersGetServerResponseServerImage image) {
    
    
    
    this.image = image;
  }


  public ServersGetServerResponseServer includedTraffic(Long includedTraffic) {
    
    
    
    
    this.includedTraffic = includedTraffic;
    return this;
  }

   /**
   * Free Traffic for the current billing period in bytes
   * @return includedTraffic
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "654321", required = true, value = "Free Traffic for the current billing period in bytes")

  public Long getIncludedTraffic() {
    return includedTraffic;
  }


  public void setIncludedTraffic(Long includedTraffic) {
    
    
    
    this.includedTraffic = includedTraffic;
  }


  public ServersGetServerResponseServer ingoingTraffic(Long ingoingTraffic) {
    
    
    
    
    this.ingoingTraffic = ingoingTraffic;
    return this;
  }

   /**
   * Inbound Traffic for the current billing period in bytes
   * @return ingoingTraffic
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "123456", required = true, value = "Inbound Traffic for the current billing period in bytes")

  public Long getIngoingTraffic() {
    return ingoingTraffic;
  }


  public void setIngoingTraffic(Long ingoingTraffic) {
    
    
    
    this.ingoingTraffic = ingoingTraffic;
  }


  public ServersGetServerResponseServer iso(ServersGetServerResponseServerIso iso) {
    
    
    
    
    this.iso = iso;
    return this;
  }

   /**
   * Get iso
   * @return iso
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(required = true, value = "")

  public ServersGetServerResponseServerIso getIso() {
    return iso;
  }


  public void setIso(ServersGetServerResponseServerIso iso) {
    
    
    
    this.iso = iso;
  }


  public ServersGetServerResponseServer labels(Map<String, String> labels) {
    
    
    
    
    this.labels = labels;
    return this;
  }

  public ServersGetServerResponseServer putLabelsItem(String key, String labelsItem) {
    this.labels.put(key, labelsItem);
    return this;
  }

   /**
   * User-defined labels (&#x60;key/value&#x60; pairs) for the Resource. For more information, see \&quot;[Labels](https://docs.hetzner.cloud)\&quot;. 
   * @return labels
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "{\"environment\":\"prod\",\"example.com/my\":\"label\",\"just-a-key\":\"\"}", required = true, value = "User-defined labels (`key/value` pairs) for the Resource. For more information, see \"[Labels](https://docs.hetzner.cloud)\". ")

  public Map<String, String> getLabels() {
    return labels;
  }


  public void setLabels(Map<String, String> labels) {
    
    
    
    this.labels = labels;
  }


  public ServersGetServerResponseServer loadBalancers(List<Long> loadBalancers) {
    
    
    
    
    this.loadBalancers = loadBalancers;
    return this;
  }

  public ServersGetServerResponseServer addLoadBalancersItem(Long loadBalancersItem) {
    if (this.loadBalancers == null) {
      this.loadBalancers = new ArrayList<>();
    }
    this.loadBalancers.add(loadBalancersItem);
    return this;
  }

   /**
   * Load Balancer IDs assigned to the server.
   * @return loadBalancers
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Load Balancer IDs assigned to the server.")

  public List<Long> getLoadBalancers() {
    return loadBalancers;
  }


  public void setLoadBalancers(List<Long> loadBalancers) {
    
    
    
    this.loadBalancers = loadBalancers;
  }


  public ServersGetServerResponseServer locked(Boolean locked) {
    
    
    
    
    this.locked = locked;
    return this;
  }

   /**
   * True if Server has been locked and is not available to user
   * @return locked
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "false", required = true, value = "True if Server has been locked and is not available to user")

  public Boolean getLocked() {
    return locked;
  }


  public void setLocked(Boolean locked) {
    
    
    
    this.locked = locked;
  }


  public ServersGetServerResponseServer name(String name) {
    
    
    
    
    this.name = name;
    return this;
  }

   /**
   * Name of the Server (must be unique per Project and a valid hostname as per RFC 1123)
   * @return name
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "my-resource", required = true, value = "Name of the Server (must be unique per Project and a valid hostname as per RFC 1123)")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    
    
    
    this.name = name;
  }


  public ServersGetServerResponseServer outgoingTraffic(Long outgoingTraffic) {
    
    
    
    
    this.outgoingTraffic = outgoingTraffic;
    return this;
  }

   /**
   * Outbound Traffic for the current billing period in bytes
   * @return outgoingTraffic
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "123456", required = true, value = "Outbound Traffic for the current billing period in bytes")

  public Long getOutgoingTraffic() {
    return outgoingTraffic;
  }


  public void setOutgoingTraffic(Long outgoingTraffic) {
    
    
    
    this.outgoingTraffic = outgoingTraffic;
  }


  public ServersGetServerResponseServer placementGroup(PlacementGroupNullableProperty2 placementGroup) {
    
    
    
    
    this.placementGroup = placementGroup;
    return this;
  }

   /**
   * Get placementGroup
   * @return placementGroup
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public PlacementGroupNullableProperty2 getPlacementGroup() {
    return placementGroup;
  }


  public void setPlacementGroup(PlacementGroupNullableProperty2 placementGroup) {
    
    
    
    this.placementGroup = placementGroup;
  }


  public ServersGetServerResponseServer primaryDiskSize(Double primaryDiskSize) {
    
    
    
    
    this.primaryDiskSize = primaryDiskSize;
    return this;
  }

  public ServersGetServerResponseServer primaryDiskSize(Integer primaryDiskSize) {
    
    
    
    
    this.primaryDiskSize = primaryDiskSize.doubleValue();
    return this;
  }

   /**
   * Size of the primary Disk
   * @return primaryDiskSize
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "50", required = true, value = "Size of the primary Disk")

  public Double getPrimaryDiskSize() {
    return primaryDiskSize;
  }


  public void setPrimaryDiskSize(Double primaryDiskSize) {
    
    
    
    this.primaryDiskSize = primaryDiskSize;
  }


  public ServersGetServerResponseServer privateNet(List<ServersGetServerResponseServerPrivateNetInner> privateNet) {
    
    
    
    
    this.privateNet = privateNet;
    return this;
  }

  public ServersGetServerResponseServer addPrivateNetItem(ServersGetServerResponseServerPrivateNetInner privateNetItem) {
    this.privateNet.add(privateNetItem);
    return this;
  }

   /**
   * Private networks information
   * @return privateNet
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Private networks information")

  public List<ServersGetServerResponseServerPrivateNetInner> getPrivateNet() {
    return privateNet;
  }


  public void setPrivateNet(List<ServersGetServerResponseServerPrivateNetInner> privateNet) {
    
    
    
    this.privateNet = privateNet;
  }


  public ServersGetServerResponseServer protection(ServersGetServerResponseServerProtection protection) {
    
    
    
    
    this.protection = protection;
    return this;
  }

   /**
   * Get protection
   * @return protection
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public ServersGetServerResponseServerProtection getProtection() {
    return protection;
  }


  public void setProtection(ServersGetServerResponseServerProtection protection) {
    
    
    
    this.protection = protection;
  }


  public ServersGetServerResponseServer publicNet(ServersGetServerResponseServerPublicNet publicNet) {
    
    
    
    
    this.publicNet = publicNet;
    return this;
  }

   /**
   * Get publicNet
   * @return publicNet
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public ServersGetServerResponseServerPublicNet getPublicNet() {
    return publicNet;
  }


  public void setPublicNet(ServersGetServerResponseServerPublicNet publicNet) {
    
    
    
    this.publicNet = publicNet;
  }


  public ServersGetServerResponseServer rescueEnabled(Boolean rescueEnabled) {
    
    
    
    
    this.rescueEnabled = rescueEnabled;
    return this;
  }

   /**
   * True if rescue mode is enabled. Server will then boot into rescue system on next reboot
   * @return rescueEnabled
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "false", required = true, value = "True if rescue mode is enabled. Server will then boot into rescue system on next reboot")

  public Boolean getRescueEnabled() {
    return rescueEnabled;
  }


  public void setRescueEnabled(Boolean rescueEnabled) {
    
    
    
    this.rescueEnabled = rescueEnabled;
  }


  public ServersGetServerResponseServer serverType(ServersGetServerResponseServerServerType serverType) {
    
    
    
    
    this.serverType = serverType;
    return this;
  }

   /**
   * Get serverType
   * @return serverType
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public ServersGetServerResponseServerServerType getServerType() {
    return serverType;
  }


  public void setServerType(ServersGetServerResponseServerServerType serverType) {
    
    
    
    this.serverType = serverType;
  }


  public ServersGetServerResponseServer status(StatusEnum status) {
    
    
    
    
    this.status = status;
    return this;
  }

   /**
   * Status of the Server
   * @return status
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Status of the Server")

  public StatusEnum getStatus() {
    return status;
  }


  public void setStatus(StatusEnum status) {
    
    
    
    this.status = status;
  }


  public ServersGetServerResponseServer volumes(List<Long> volumes) {
    
    
    
    
    this.volumes = volumes;
    return this;
  }

  public ServersGetServerResponseServer addVolumesItem(Long volumesItem) {
    if (this.volumes == null) {
      this.volumes = new ArrayList<>();
    }
    this.volumes.add(volumesItem);
    return this;
  }

   /**
   * IDs of Volumes assigned to this Server
   * @return volumes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "IDs of Volumes assigned to this Server")

  public List<Long> getVolumes() {
    return volumes;
  }


  public void setVolumes(List<Long> volumes) {
    
    
    
    this.volumes = volumes;
  }

  /**
   * A container for additional, undeclared properties.
   * This is a holder for any undeclared properties as specified with
   * the 'additionalProperties' keyword in the OAS document.
   */
  private Map<String, Object> additionalProperties;

  /**
   * Set the additional (undeclared) property with the specified name and value.
   * If the property does not already exist, create it otherwise replace it.
   *
   * @param key name of the property
   * @param value value of the property
   * @return the ServersGetServerResponseServer instance itself
   */
  public ServersGetServerResponseServer putAdditionalProperty(String key, Object value) {
    if (this.additionalProperties == null) {
        this.additionalProperties = new HashMap<String, Object>();
    }
    this.additionalProperties.put(key, value);
    return this;
  }

  /**
   * Return the additional (undeclared) property.
   *
   * @return a map of objects
   */
  public Map<String, Object> getAdditionalProperties() {
    return additionalProperties;
  }

  /**
   * Return the additional (undeclared) property with the specified name.
   *
   * @param key name of the property
   * @return an object
   */
  public Object getAdditionalProperty(String key) {
    if (this.additionalProperties == null) {
        return null;
    }
    return this.additionalProperties.get(key);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServersGetServerResponseServer serversGetServerResponseServer = (ServersGetServerResponseServer) o;
    return Objects.equals(this.backupWindow, serversGetServerResponseServer.backupWindow) &&
        Objects.equals(this.created, serversGetServerResponseServer.created) &&
        Objects.equals(this.datacenter, serversGetServerResponseServer.datacenter) &&
        Objects.equals(this.id, serversGetServerResponseServer.id) &&
        Objects.equals(this.image, serversGetServerResponseServer.image) &&
        Objects.equals(this.includedTraffic, serversGetServerResponseServer.includedTraffic) &&
        Objects.equals(this.ingoingTraffic, serversGetServerResponseServer.ingoingTraffic) &&
        Objects.equals(this.iso, serversGetServerResponseServer.iso) &&
        Objects.equals(this.labels, serversGetServerResponseServer.labels) &&
        Objects.equals(this.loadBalancers, serversGetServerResponseServer.loadBalancers) &&
        Objects.equals(this.locked, serversGetServerResponseServer.locked) &&
        Objects.equals(this.name, serversGetServerResponseServer.name) &&
        Objects.equals(this.outgoingTraffic, serversGetServerResponseServer.outgoingTraffic) &&
        Objects.equals(this.placementGroup, serversGetServerResponseServer.placementGroup) &&
        Objects.equals(this.primaryDiskSize, serversGetServerResponseServer.primaryDiskSize) &&
        Objects.equals(this.privateNet, serversGetServerResponseServer.privateNet) &&
        Objects.equals(this.protection, serversGetServerResponseServer.protection) &&
        Objects.equals(this.publicNet, serversGetServerResponseServer.publicNet) &&
        Objects.equals(this.rescueEnabled, serversGetServerResponseServer.rescueEnabled) &&
        Objects.equals(this.serverType, serversGetServerResponseServer.serverType) &&
        Objects.equals(this.status, serversGetServerResponseServer.status) &&
        Objects.equals(this.volumes, serversGetServerResponseServer.volumes)&&
        Objects.equals(this.additionalProperties, serversGetServerResponseServer.additionalProperties);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(backupWindow, created, datacenter, id, image, includedTraffic, ingoingTraffic, iso, labels, loadBalancers, locked, name, outgoingTraffic, placementGroup, primaryDiskSize, privateNet, protection, publicNet, rescueEnabled, serverType, status, volumes, additionalProperties);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServersGetServerResponseServer {\n");
    sb.append("    backupWindow: ").append(toIndentedString(backupWindow)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    datacenter: ").append(toIndentedString(datacenter)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    includedTraffic: ").append(toIndentedString(includedTraffic)).append("\n");
    sb.append("    ingoingTraffic: ").append(toIndentedString(ingoingTraffic)).append("\n");
    sb.append("    iso: ").append(toIndentedString(iso)).append("\n");
    sb.append("    labels: ").append(toIndentedString(labels)).append("\n");
    sb.append("    loadBalancers: ").append(toIndentedString(loadBalancers)).append("\n");
    sb.append("    locked: ").append(toIndentedString(locked)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    outgoingTraffic: ").append(toIndentedString(outgoingTraffic)).append("\n");
    sb.append("    placementGroup: ").append(toIndentedString(placementGroup)).append("\n");
    sb.append("    primaryDiskSize: ").append(toIndentedString(primaryDiskSize)).append("\n");
    sb.append("    privateNet: ").append(toIndentedString(privateNet)).append("\n");
    sb.append("    protection: ").append(toIndentedString(protection)).append("\n");
    sb.append("    publicNet: ").append(toIndentedString(publicNet)).append("\n");
    sb.append("    rescueEnabled: ").append(toIndentedString(rescueEnabled)).append("\n");
    sb.append("    serverType: ").append(toIndentedString(serverType)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    volumes: ").append(toIndentedString(volumes)).append("\n");
    sb.append("    additionalProperties: ").append(toIndentedString(additionalProperties)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("backup_window");
    openapiFields.add("created");
    openapiFields.add("datacenter");
    openapiFields.add("id");
    openapiFields.add("image");
    openapiFields.add("included_traffic");
    openapiFields.add("ingoing_traffic");
    openapiFields.add("iso");
    openapiFields.add("labels");
    openapiFields.add("load_balancers");
    openapiFields.add("locked");
    openapiFields.add("name");
    openapiFields.add("outgoing_traffic");
    openapiFields.add("placement_group");
    openapiFields.add("primary_disk_size");
    openapiFields.add("private_net");
    openapiFields.add("protection");
    openapiFields.add("public_net");
    openapiFields.add("rescue_enabled");
    openapiFields.add("server_type");
    openapiFields.add("status");
    openapiFields.add("volumes");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("backup_window");
    openapiRequiredFields.add("created");
    openapiRequiredFields.add("datacenter");
    openapiRequiredFields.add("id");
    openapiRequiredFields.add("image");
    openapiRequiredFields.add("included_traffic");
    openapiRequiredFields.add("ingoing_traffic");
    openapiRequiredFields.add("iso");
    openapiRequiredFields.add("labels");
    openapiRequiredFields.add("locked");
    openapiRequiredFields.add("name");
    openapiRequiredFields.add("outgoing_traffic");
    openapiRequiredFields.add("primary_disk_size");
    openapiRequiredFields.add("private_net");
    openapiRequiredFields.add("protection");
    openapiRequiredFields.add("public_net");
    openapiRequiredFields.add("rescue_enabled");
    openapiRequiredFields.add("server_type");
    openapiRequiredFields.add("status");
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to ServersGetServerResponseServer
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (!ServersGetServerResponseServer.openapiRequiredFields.isEmpty()) { // has required fields but JSON object is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ServersGetServerResponseServer is not found in the empty JSON string", ServersGetServerResponseServer.openapiRequiredFields.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : ServersGetServerResponseServer.openapiRequiredFields) {
        if (jsonObj.get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonObj.toString()));
        }
      }
      if (!jsonObj.get("backup_window").isJsonNull() && !jsonObj.get("backup_window").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `backup_window` to be a primitive type in the JSON string but got `%s`", jsonObj.get("backup_window").toString()));
      }
      if (!jsonObj.get("created").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `created` to be a primitive type in the JSON string but got `%s`", jsonObj.get("created").toString()));
      }
      // validate the required field `datacenter`
      ServersGetServerResponseServerDatacenter.validateJsonObject(jsonObj.getAsJsonObject("datacenter"));
      // validate the required field `image`
      ServersGetServerResponseServerImage.validateJsonObject(jsonObj.getAsJsonObject("image"));
      // validate the required field `iso`
      ServersGetServerResponseServerIso.validateJsonObject(jsonObj.getAsJsonObject("iso"));
      // ensure the optional json data is an array if present
      if (jsonObj.get("load_balancers") != null && !jsonObj.get("load_balancers").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `load_balancers` to be an array in the JSON string but got `%s`", jsonObj.get("load_balancers").toString()));
      }
      if (!jsonObj.get("name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("name").toString()));
      }
      // validate the optional field `placement_group`
      if (jsonObj.get("placement_group") != null && !jsonObj.get("placement_group").isJsonNull()) {
        PlacementGroupNullableProperty2.validateJsonObject(jsonObj.getAsJsonObject("placement_group"));
      }
      // ensure the json data is an array
      if (!jsonObj.get("private_net").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `private_net` to be an array in the JSON string but got `%s`", jsonObj.get("private_net").toString()));
      }

      JsonArray jsonArrayprivateNet = jsonObj.getAsJsonArray("private_net");
      // validate the required field `private_net` (array)
      for (int i = 0; i < jsonArrayprivateNet.size(); i++) {
        ServersGetServerResponseServerPrivateNetInner.validateJsonObject(jsonArrayprivateNet.get(i).getAsJsonObject());
      };
      // validate the required field `protection`
      ServersGetServerResponseServerProtection.validateJsonObject(jsonObj.getAsJsonObject("protection"));
      // validate the required field `public_net`
      ServersGetServerResponseServerPublicNet.validateJsonObject(jsonObj.getAsJsonObject("public_net"));
      // validate the required field `server_type`
      ServersGetServerResponseServerServerType.validateJsonObject(jsonObj.getAsJsonObject("server_type"));
      if (!jsonObj.get("status").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `status` to be a primitive type in the JSON string but got `%s`", jsonObj.get("status").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("volumes") != null && !jsonObj.get("volumes").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `volumes` to be an array in the JSON string but got `%s`", jsonObj.get("volumes").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ServersGetServerResponseServer.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ServersGetServerResponseServer' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ServersGetServerResponseServer> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ServersGetServerResponseServer.class));

       return (TypeAdapter<T>) new TypeAdapter<ServersGetServerResponseServer>() {
           @Override
           public void write(JsonWriter out, ServersGetServerResponseServer value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             obj.remove("additionalProperties");
             // serialize additonal properties
             if (value.getAdditionalProperties() != null) {
               for (Map.Entry<String, Object> entry : value.getAdditionalProperties().entrySet()) {
                 if (entry.getValue() instanceof String)
                   obj.addProperty(entry.getKey(), (String) entry.getValue());
                 else if (entry.getValue() instanceof Number)
                   obj.addProperty(entry.getKey(), (Number) entry.getValue());
                 else if (entry.getValue() instanceof Boolean)
                   obj.addProperty(entry.getKey(), (Boolean) entry.getValue());
                 else if (entry.getValue() instanceof Character)
                   obj.addProperty(entry.getKey(), (Character) entry.getValue());
                 else {
                   obj.add(entry.getKey(), gson.toJsonTree(entry.getValue()).getAsJsonObject());
                 }
               }
             }
             elementAdapter.write(out, obj);
           }

           @Override
           public ServersGetServerResponseServer read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             // store additional fields in the deserialized instance
             ServersGetServerResponseServer instance = thisAdapter.fromJsonTree(jsonObj);
             for (Map.Entry<String, JsonElement> entry : jsonObj.entrySet()) {
               if (!openapiFields.contains(entry.getKey())) {
                 if (entry.getValue().isJsonPrimitive()) { // primitive type
                   if (entry.getValue().getAsJsonPrimitive().isString())
                     instance.putAdditionalProperty(entry.getKey(), entry.getValue().getAsString());
                   else if (entry.getValue().getAsJsonPrimitive().isNumber())
                     instance.putAdditionalProperty(entry.getKey(), entry.getValue().getAsNumber());
                   else if (entry.getValue().getAsJsonPrimitive().isBoolean())
                     instance.putAdditionalProperty(entry.getKey(), entry.getValue().getAsBoolean());
                   else
                     throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                 } else if (entry.getValue().isJsonArray()) {
                     instance.putAdditionalProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                 } else { // JSON object
                     instance.putAdditionalProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                 }
               }
             }
             return instance;
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ServersGetServerResponseServer given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ServersGetServerResponseServer
  * @throws IOException if the JSON string is invalid with respect to ServersGetServerResponseServer
  */
  public static ServersGetServerResponseServer fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ServersGetServerResponseServer.class);
  }

 /**
  * Convert an instance of ServersGetServerResponseServer to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

