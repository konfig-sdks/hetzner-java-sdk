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
import com.konfigthis.client.model.ServersUpdateServerResponseServerImageCreatedFrom;
import com.konfigthis.client.model.ServersUpdateServerResponseServerImageProtection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
 * Image the server is based on.
 */
@ApiModel(description = "Image the server is based on.")@javax.annotation.Generated(value = "Generated by https://konfigthis.com")
public class ServersUpdateServerResponseServerImage {
  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  /**
   * Type of cpu architecture this image is compatible with.
   */
  @JsonAdapter(ArchitectureEnum.Adapter.class)
 public enum ArchitectureEnum {
    X86("x86"),
    
    ARM("arm");

    private String value;

    ArchitectureEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static ArchitectureEnum fromValue(String value) {
      for (ArchitectureEnum b : ArchitectureEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<ArchitectureEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final ArchitectureEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public ArchitectureEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return ArchitectureEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_ARCHITECTURE = "architecture";
  @SerializedName(SERIALIZED_NAME_ARCHITECTURE)
  private ArchitectureEnum architecture;

  public static final String SERIALIZED_NAME_BOUND_TO = "bound_to";
  @SerializedName(SERIALIZED_NAME_BOUND_TO)
  private Long boundTo;

  public static final String SERIALIZED_NAME_CREATED = "created";
  @SerializedName(SERIALIZED_NAME_CREATED)
  private String created;

  public static final String SERIALIZED_NAME_CREATED_FROM = "created_from";
  @SerializedName(SERIALIZED_NAME_CREATED_FROM)
  private ServersUpdateServerResponseServerImageCreatedFrom createdFrom;

  public static final String SERIALIZED_NAME_DELETED = "deleted";
  @SerializedName(SERIALIZED_NAME_DELETED)
  private String deleted;

  public static final String SERIALIZED_NAME_DEPRECATED = "deprecated";
  @SerializedName(SERIALIZED_NAME_DEPRECATED)
  private String deprecated;

  public static final String SERIALIZED_NAME_DISK_SIZE = "disk_size";
  @SerializedName(SERIALIZED_NAME_DISK_SIZE)
  private Double diskSize;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_IMAGE_SIZE = "image_size";
  @SerializedName(SERIALIZED_NAME_IMAGE_SIZE)
  private Double imageSize;

  public static final String SERIALIZED_NAME_LABELS = "labels";
  @SerializedName(SERIALIZED_NAME_LABELS)
  private Map<String, String> labels = new HashMap<>();

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  /**
   * Flavor of operating system contained in the Image
   */
  @JsonAdapter(OsFlavorEnum.Adapter.class)
 public enum OsFlavorEnum {
    UBUNTU("ubuntu"),
    
    CENTOS("centos"),
    
    DEBIAN("debian"),
    
    FEDORA("fedora"),
    
    ROCKY("rocky"),
    
    ALMA("alma"),
    
    UNKNOWN("unknown");

    private String value;

    OsFlavorEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static OsFlavorEnum fromValue(String value) {
      for (OsFlavorEnum b : OsFlavorEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<OsFlavorEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final OsFlavorEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public OsFlavorEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return OsFlavorEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_OS_FLAVOR = "os_flavor";
  @SerializedName(SERIALIZED_NAME_OS_FLAVOR)
  private OsFlavorEnum osFlavor;

  public static final String SERIALIZED_NAME_OS_VERSION = "os_version";
  @SerializedName(SERIALIZED_NAME_OS_VERSION)
  private String osVersion;

  public static final String SERIALIZED_NAME_PROTECTION = "protection";
  @SerializedName(SERIALIZED_NAME_PROTECTION)
  private ServersUpdateServerResponseServerImageProtection protection;

  public static final String SERIALIZED_NAME_RAPID_DEPLOY = "rapid_deploy";
  @SerializedName(SERIALIZED_NAME_RAPID_DEPLOY)
  private Boolean rapidDeploy;

  /**
   * Whether the Image can be used or if it&#39;s still being created or unavailable
   */
  @JsonAdapter(StatusEnum.Adapter.class)
 public enum StatusEnum {
    AVAILABLE("available"),
    
    CREATING("creating"),
    
    UNAVAILABLE("unavailable");

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

  /**
   * Type of the Image
   */
  @JsonAdapter(TypeEnum.Adapter.class)
 public enum TypeEnum {
    SYSTEM("system"),
    
    APP("app"),
    
    SNAPSHOT("snapshot"),
    
    BACKUP("backup"),
    
    TEMPORARY("temporary");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<TypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TypeEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return TypeEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private TypeEnum type;

  public ServersUpdateServerResponseServerImage() {
  }

  public ServersUpdateServerResponseServerImage description(String description) {
    
    
    
    
    this.description = description;
    return this;
  }

   /**
   * Description of the Image
   * @return description
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "Ubuntu 20.04 Standard 64 bit", required = true, value = "Description of the Image")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    
    
    
    this.description = description;
  }


  public ServersUpdateServerResponseServerImage architecture(ArchitectureEnum architecture) {
    
    
    
    
    this.architecture = architecture;
    return this;
  }

   /**
   * Type of cpu architecture this image is compatible with.
   * @return architecture
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "X86", required = true, value = "Type of cpu architecture this image is compatible with.")

  public ArchitectureEnum getArchitecture() {
    return architecture;
  }


  public void setArchitecture(ArchitectureEnum architecture) {
    
    
    
    this.architecture = architecture;
  }


  public ServersUpdateServerResponseServerImage boundTo(Long boundTo) {
    
    
    
    
    this.boundTo = boundTo;
    return this;
  }

   /**
   * ID of Server the Image is bound to. Only set for Images of type &#x60;backup&#x60;.
   * @return boundTo
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(required = true, value = "ID of Server the Image is bound to. Only set for Images of type `backup`.")

  public Long getBoundTo() {
    return boundTo;
  }


  public void setBoundTo(Long boundTo) {
    
    
    
    this.boundTo = boundTo;
  }


  public ServersUpdateServerResponseServerImage created(String created) {
    
    
    
    
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


  public ServersUpdateServerResponseServerImage createdFrom(ServersUpdateServerResponseServerImageCreatedFrom createdFrom) {
    
    
    
    
    this.createdFrom = createdFrom;
    return this;
  }

   /**
   * Get createdFrom
   * @return createdFrom
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(required = true, value = "")

  public ServersUpdateServerResponseServerImageCreatedFrom getCreatedFrom() {
    return createdFrom;
  }


  public void setCreatedFrom(ServersUpdateServerResponseServerImageCreatedFrom createdFrom) {
    
    
    
    this.createdFrom = createdFrom;
  }


  public ServersUpdateServerResponseServerImage deleted(String deleted) {
    
    
    
    
    this.deleted = deleted;
    return this;
  }

   /**
   * Point in time where the Image was deleted (in ISO-8601 format)
   * @return deleted
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(required = true, value = "Point in time where the Image was deleted (in ISO-8601 format)")

  public String getDeleted() {
    return deleted;
  }


  public void setDeleted(String deleted) {
    
    
    
    this.deleted = deleted;
  }


  public ServersUpdateServerResponseServerImage deprecated(String deprecated) {
    
    
    
    
    this.deprecated = deprecated;
    return this;
  }

   /**
   * Point in time when the Image is considered to be deprecated (in ISO-8601 format)
   * @return deprecated
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "2018-02-28T00:00:00+00:00", required = true, value = "Point in time when the Image is considered to be deprecated (in ISO-8601 format)")

  public String getDeprecated() {
    return deprecated;
  }


  public void setDeprecated(String deprecated) {
    
    
    
    this.deprecated = deprecated;
  }


  public ServersUpdateServerResponseServerImage diskSize(Double diskSize) {
    
    
    
    
    this.diskSize = diskSize;
    return this;
  }

  public ServersUpdateServerResponseServerImage diskSize(Integer diskSize) {
    
    
    
    
    this.diskSize = diskSize.doubleValue();
    return this;
  }

   /**
   * Size of the disk contained in the Image in GB
   * @return diskSize
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "10", required = true, value = "Size of the disk contained in the Image in GB")

  public Double getDiskSize() {
    return diskSize;
  }


  public void setDiskSize(Double diskSize) {
    
    
    
    this.diskSize = diskSize;
  }


  public ServersUpdateServerResponseServerImage id(Long id) {
    
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


  public ServersUpdateServerResponseServerImage imageSize(Double imageSize) {
    
    
    
    
    this.imageSize = imageSize;
    return this;
  }

  public ServersUpdateServerResponseServerImage imageSize(Integer imageSize) {
    
    
    
    
    this.imageSize = imageSize.doubleValue();
    return this;
  }

   /**
   * Size of the Image file in our storage in GB. For snapshot Images this is the value relevant for calculating costs for the Image.
   * @return imageSize
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "2.3", required = true, value = "Size of the Image file in our storage in GB. For snapshot Images this is the value relevant for calculating costs for the Image.")

  public Double getImageSize() {
    return imageSize;
  }


  public void setImageSize(Double imageSize) {
    
    
    
    this.imageSize = imageSize;
  }


  public ServersUpdateServerResponseServerImage labels(Map<String, String> labels) {
    
    
    
    
    this.labels = labels;
    return this;
  }

  public ServersUpdateServerResponseServerImage putLabelsItem(String key, String labelsItem) {
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


  public ServersUpdateServerResponseServerImage name(String name) {
    
    
    
    
    this.name = name;
    return this;
  }

   /**
   * Unique identifier of the Image. This value is only set for system Images.
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "ubuntu-20.04", required = true, value = "Unique identifier of the Image. This value is only set for system Images.")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    
    
    
    this.name = name;
  }


  public ServersUpdateServerResponseServerImage osFlavor(OsFlavorEnum osFlavor) {
    
    
    
    
    this.osFlavor = osFlavor;
    return this;
  }

   /**
   * Flavor of operating system contained in the Image
   * @return osFlavor
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "UBUNTU", required = true, value = "Flavor of operating system contained in the Image")

  public OsFlavorEnum getOsFlavor() {
    return osFlavor;
  }


  public void setOsFlavor(OsFlavorEnum osFlavor) {
    
    
    
    this.osFlavor = osFlavor;
  }


  public ServersUpdateServerResponseServerImage osVersion(String osVersion) {
    
    
    
    
    this.osVersion = osVersion;
    return this;
  }

   /**
   * Operating system version
   * @return osVersion
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "20.04", required = true, value = "Operating system version")

  public String getOsVersion() {
    return osVersion;
  }


  public void setOsVersion(String osVersion) {
    
    
    
    this.osVersion = osVersion;
  }


  public ServersUpdateServerResponseServerImage protection(ServersUpdateServerResponseServerImageProtection protection) {
    
    
    
    
    this.protection = protection;
    return this;
  }

   /**
   * Get protection
   * @return protection
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public ServersUpdateServerResponseServerImageProtection getProtection() {
    return protection;
  }


  public void setProtection(ServersUpdateServerResponseServerImageProtection protection) {
    
    
    
    this.protection = protection;
  }


  public ServersUpdateServerResponseServerImage rapidDeploy(Boolean rapidDeploy) {
    
    
    
    
    this.rapidDeploy = rapidDeploy;
    return this;
  }

   /**
   * Indicates that rapid deploy of the Image is available
   * @return rapidDeploy
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "false", value = "Indicates that rapid deploy of the Image is available")

  public Boolean getRapidDeploy() {
    return rapidDeploy;
  }


  public void setRapidDeploy(Boolean rapidDeploy) {
    
    
    
    this.rapidDeploy = rapidDeploy;
  }


  public ServersUpdateServerResponseServerImage status(StatusEnum status) {
    
    
    
    
    this.status = status;
    return this;
  }

   /**
   * Whether the Image can be used or if it&#39;s still being created or unavailable
   * @return status
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Whether the Image can be used or if it's still being created or unavailable")

  public StatusEnum getStatus() {
    return status;
  }


  public void setStatus(StatusEnum status) {
    
    
    
    this.status = status;
  }


  public ServersUpdateServerResponseServerImage type(TypeEnum type) {
    
    
    
    
    this.type = type;
    return this;
  }

   /**
   * Type of the Image
   * @return type
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "SNAPSHOT", required = true, value = "Type of the Image")

  public TypeEnum getType() {
    return type;
  }


  public void setType(TypeEnum type) {
    
    
    
    this.type = type;
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
   * @return the ServersUpdateServerResponseServerImage instance itself
   */
  public ServersUpdateServerResponseServerImage putAdditionalProperty(String key, Object value) {
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
    ServersUpdateServerResponseServerImage serversUpdateServerResponseServerImage = (ServersUpdateServerResponseServerImage) o;
    return Objects.equals(this.description, serversUpdateServerResponseServerImage.description) &&
        Objects.equals(this.architecture, serversUpdateServerResponseServerImage.architecture) &&
        Objects.equals(this.boundTo, serversUpdateServerResponseServerImage.boundTo) &&
        Objects.equals(this.created, serversUpdateServerResponseServerImage.created) &&
        Objects.equals(this.createdFrom, serversUpdateServerResponseServerImage.createdFrom) &&
        Objects.equals(this.deleted, serversUpdateServerResponseServerImage.deleted) &&
        Objects.equals(this.deprecated, serversUpdateServerResponseServerImage.deprecated) &&
        Objects.equals(this.diskSize, serversUpdateServerResponseServerImage.diskSize) &&
        Objects.equals(this.id, serversUpdateServerResponseServerImage.id) &&
        Objects.equals(this.imageSize, serversUpdateServerResponseServerImage.imageSize) &&
        Objects.equals(this.labels, serversUpdateServerResponseServerImage.labels) &&
        Objects.equals(this.name, serversUpdateServerResponseServerImage.name) &&
        Objects.equals(this.osFlavor, serversUpdateServerResponseServerImage.osFlavor) &&
        Objects.equals(this.osVersion, serversUpdateServerResponseServerImage.osVersion) &&
        Objects.equals(this.protection, serversUpdateServerResponseServerImage.protection) &&
        Objects.equals(this.rapidDeploy, serversUpdateServerResponseServerImage.rapidDeploy) &&
        Objects.equals(this.status, serversUpdateServerResponseServerImage.status) &&
        Objects.equals(this.type, serversUpdateServerResponseServerImage.type)&&
        Objects.equals(this.additionalProperties, serversUpdateServerResponseServerImage.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, architecture, boundTo, created, createdFrom, deleted, deprecated, diskSize, id, imageSize, labels, name, osFlavor, osVersion, protection, rapidDeploy, status, type, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServersUpdateServerResponseServerImage {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    architecture: ").append(toIndentedString(architecture)).append("\n");
    sb.append("    boundTo: ").append(toIndentedString(boundTo)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    createdFrom: ").append(toIndentedString(createdFrom)).append("\n");
    sb.append("    deleted: ").append(toIndentedString(deleted)).append("\n");
    sb.append("    deprecated: ").append(toIndentedString(deprecated)).append("\n");
    sb.append("    diskSize: ").append(toIndentedString(diskSize)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    imageSize: ").append(toIndentedString(imageSize)).append("\n");
    sb.append("    labels: ").append(toIndentedString(labels)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    osFlavor: ").append(toIndentedString(osFlavor)).append("\n");
    sb.append("    osVersion: ").append(toIndentedString(osVersion)).append("\n");
    sb.append("    protection: ").append(toIndentedString(protection)).append("\n");
    sb.append("    rapidDeploy: ").append(toIndentedString(rapidDeploy)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
    openapiFields.add("description");
    openapiFields.add("architecture");
    openapiFields.add("bound_to");
    openapiFields.add("created");
    openapiFields.add("created_from");
    openapiFields.add("deleted");
    openapiFields.add("deprecated");
    openapiFields.add("disk_size");
    openapiFields.add("id");
    openapiFields.add("image_size");
    openapiFields.add("labels");
    openapiFields.add("name");
    openapiFields.add("os_flavor");
    openapiFields.add("os_version");
    openapiFields.add("protection");
    openapiFields.add("rapid_deploy");
    openapiFields.add("status");
    openapiFields.add("type");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("description");
    openapiRequiredFields.add("architecture");
    openapiRequiredFields.add("bound_to");
    openapiRequiredFields.add("created");
    openapiRequiredFields.add("created_from");
    openapiRequiredFields.add("deleted");
    openapiRequiredFields.add("deprecated");
    openapiRequiredFields.add("disk_size");
    openapiRequiredFields.add("id");
    openapiRequiredFields.add("image_size");
    openapiRequiredFields.add("labels");
    openapiRequiredFields.add("name");
    openapiRequiredFields.add("os_flavor");
    openapiRequiredFields.add("os_version");
    openapiRequiredFields.add("protection");
    openapiRequiredFields.add("status");
    openapiRequiredFields.add("type");
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to ServersUpdateServerResponseServerImage
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (!ServersUpdateServerResponseServerImage.openapiRequiredFields.isEmpty()) { // has required fields but JSON object is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ServersUpdateServerResponseServerImage is not found in the empty JSON string", ServersUpdateServerResponseServerImage.openapiRequiredFields.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : ServersUpdateServerResponseServerImage.openapiRequiredFields) {
        if (jsonObj.get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonObj.toString()));
        }
      }
      if (!jsonObj.get("description").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `description` to be a primitive type in the JSON string but got `%s`", jsonObj.get("description").toString()));
      }
      if (!jsonObj.get("architecture").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `architecture` to be a primitive type in the JSON string but got `%s`", jsonObj.get("architecture").toString()));
      }
      if (!jsonObj.get("created").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `created` to be a primitive type in the JSON string but got `%s`", jsonObj.get("created").toString()));
      }
      // validate the required field `created_from`
      ServersUpdateServerResponseServerImageCreatedFrom.validateJsonObject(jsonObj.getAsJsonObject("created_from"));
      if (!jsonObj.get("deleted").isJsonNull() && !jsonObj.get("deleted").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `deleted` to be a primitive type in the JSON string but got `%s`", jsonObj.get("deleted").toString()));
      }
      if (!jsonObj.get("deprecated").isJsonNull() && !jsonObj.get("deprecated").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `deprecated` to be a primitive type in the JSON string but got `%s`", jsonObj.get("deprecated").toString()));
      }
      if (!jsonObj.get("name").isJsonNull() && !jsonObj.get("name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("name").toString()));
      }
      if (!jsonObj.get("os_flavor").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `os_flavor` to be a primitive type in the JSON string but got `%s`", jsonObj.get("os_flavor").toString()));
      }
      if (!jsonObj.get("os_version").isJsonNull() && !jsonObj.get("os_version").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `os_version` to be a primitive type in the JSON string but got `%s`", jsonObj.get("os_version").toString()));
      }
      // validate the required field `protection`
      ServersUpdateServerResponseServerImageProtection.validateJsonObject(jsonObj.getAsJsonObject("protection"));
      if (!jsonObj.get("status").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `status` to be a primitive type in the JSON string but got `%s`", jsonObj.get("status").toString()));
      }
      if (!jsonObj.get("type").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `type` to be a primitive type in the JSON string but got `%s`", jsonObj.get("type").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ServersUpdateServerResponseServerImage.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ServersUpdateServerResponseServerImage' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ServersUpdateServerResponseServerImage> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ServersUpdateServerResponseServerImage.class));

       return (TypeAdapter<T>) new TypeAdapter<ServersUpdateServerResponseServerImage>() {
           @Override
           public void write(JsonWriter out, ServersUpdateServerResponseServerImage value) throws IOException {
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
           public ServersUpdateServerResponseServerImage read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             // store additional fields in the deserialized instance
             ServersUpdateServerResponseServerImage instance = thisAdapter.fromJsonTree(jsonObj);
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
  * Create an instance of ServersUpdateServerResponseServerImage given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ServersUpdateServerResponseServerImage
  * @throws IOException if the JSON string is invalid with respect to ServersUpdateServerResponseServerImage
  */
  public static ServersUpdateServerResponseServerImage fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ServersUpdateServerResponseServerImage.class);
  }

 /**
  * Convert an instance of ServersUpdateServerResponseServerImage to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

