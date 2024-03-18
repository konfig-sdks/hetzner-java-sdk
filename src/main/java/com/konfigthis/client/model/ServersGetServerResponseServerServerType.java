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
import com.konfigthis.client.model.DeprecationInfoProperty9;
import com.konfigthis.client.model.ServersGetServerResponseServerServerTypePricesInner;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
 * ServersGetServerResponseServerServerType
 */@javax.annotation.Generated(value = "Generated by https://konfigthis.com")
public class ServersGetServerResponseServerServerType {
  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  /**
   * Type of cpu architecture
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

  public static final String SERIALIZED_NAME_CORES = "cores";
  @SerializedName(SERIALIZED_NAME_CORES)
  private Double cores;

  /**
   * Type of cpu
   */
  @JsonAdapter(CpuTypeEnum.Adapter.class)
 public enum CpuTypeEnum {
    SHARED("shared"),
    
    DEDICATED("dedicated");

    private String value;

    CpuTypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static CpuTypeEnum fromValue(String value) {
      for (CpuTypeEnum b : CpuTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<CpuTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final CpuTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public CpuTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return CpuTypeEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_CPU_TYPE = "cpu_type";
  @SerializedName(SERIALIZED_NAME_CPU_TYPE)
  private CpuTypeEnum cpuType;

  public static final String SERIALIZED_NAME_DEPRECATED = "deprecated";
  @SerializedName(SERIALIZED_NAME_DEPRECATED)
  private Boolean deprecated;

  public static final String SERIALIZED_NAME_DEPRECATION = "deprecation";
  @SerializedName(SERIALIZED_NAME_DEPRECATION)
  private DeprecationInfoProperty9 deprecation;

  public static final String SERIALIZED_NAME_DISK = "disk";
  @SerializedName(SERIALIZED_NAME_DISK)
  private Double disk;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_INCLUDED_TRAFFIC = "included_traffic";
  @SerializedName(SERIALIZED_NAME_INCLUDED_TRAFFIC)
  private Long includedTraffic;

  public static final String SERIALIZED_NAME_MEMORY = "memory";
  @SerializedName(SERIALIZED_NAME_MEMORY)
  private Double memory;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_PRICES = "prices";
  @SerializedName(SERIALIZED_NAME_PRICES)
  private List<ServersGetServerResponseServerServerTypePricesInner> prices = new ArrayList<>();

  /**
   * Type of Server boot drive. Local has higher speed. Network has better availability.
   */
  @JsonAdapter(StorageTypeEnum.Adapter.class)
 public enum StorageTypeEnum {
    LOCAL("local"),
    
    NETWORK("network");

    private String value;

    StorageTypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StorageTypeEnum fromValue(String value) {
      for (StorageTypeEnum b : StorageTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<StorageTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StorageTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StorageTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return StorageTypeEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_STORAGE_TYPE = "storage_type";
  @SerializedName(SERIALIZED_NAME_STORAGE_TYPE)
  private StorageTypeEnum storageType;

  public ServersGetServerResponseServerServerType() {
  }

  public ServersGetServerResponseServerServerType description(String description) {
    
    
    
    
    this.description = description;
    return this;
  }

   /**
   * Description of the Server type
   * @return description
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "CX11", required = true, value = "Description of the Server type")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    
    
    
    this.description = description;
  }


  public ServersGetServerResponseServerServerType architecture(ArchitectureEnum architecture) {
    
    
    
    
    this.architecture = architecture;
    return this;
  }

   /**
   * Type of cpu architecture
   * @return architecture
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "X86", required = true, value = "Type of cpu architecture")

  public ArchitectureEnum getArchitecture() {
    return architecture;
  }


  public void setArchitecture(ArchitectureEnum architecture) {
    
    
    
    this.architecture = architecture;
  }


  public ServersGetServerResponseServerServerType cores(Double cores) {
    
    
    
    
    this.cores = cores;
    return this;
  }

  public ServersGetServerResponseServerServerType cores(Integer cores) {
    
    
    
    
    this.cores = cores.doubleValue();
    return this;
  }

   /**
   * Number of cpu cores a Server of this type will have
   * @return cores
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "1", required = true, value = "Number of cpu cores a Server of this type will have")

  public Double getCores() {
    return cores;
  }


  public void setCores(Double cores) {
    
    
    
    this.cores = cores;
  }


  public ServersGetServerResponseServerServerType cpuType(CpuTypeEnum cpuType) {
    
    
    
    
    this.cpuType = cpuType;
    return this;
  }

   /**
   * Type of cpu
   * @return cpuType
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Type of cpu")

  public CpuTypeEnum getCpuType() {
    return cpuType;
  }


  public void setCpuType(CpuTypeEnum cpuType) {
    
    
    
    this.cpuType = cpuType;
  }


  public ServersGetServerResponseServerServerType deprecated(Boolean deprecated) {
    
    
    
    
    this.deprecated = deprecated;
    return this;
  }

   /**
   * This field is deprecated. Use the deprecation object instead
   * @return deprecated
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "false", required = true, value = "This field is deprecated. Use the deprecation object instead")

  public Boolean getDeprecated() {
    return deprecated;
  }


  public void setDeprecated(Boolean deprecated) {
    
    
    
    this.deprecated = deprecated;
  }


  public ServersGetServerResponseServerServerType deprecation(DeprecationInfoProperty9 deprecation) {
    
    
    
    
    this.deprecation = deprecation;
    return this;
  }

   /**
   * Get deprecation
   * @return deprecation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public DeprecationInfoProperty9 getDeprecation() {
    return deprecation;
  }


  public void setDeprecation(DeprecationInfoProperty9 deprecation) {
    
    
    
    this.deprecation = deprecation;
  }


  public ServersGetServerResponseServerServerType disk(Double disk) {
    
    
    
    
    this.disk = disk;
    return this;
  }

  public ServersGetServerResponseServerServerType disk(Integer disk) {
    
    
    
    
    this.disk = disk.doubleValue();
    return this;
  }

   /**
   * Disk size a Server of this type will have in GB
   * @return disk
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "24", required = true, value = "Disk size a Server of this type will have in GB")

  public Double getDisk() {
    return disk;
  }


  public void setDisk(Double disk) {
    
    
    
    this.disk = disk;
  }


  public ServersGetServerResponseServerServerType id(Long id) {
    
    
    
    
    this.id = id;
    return this;
  }

   /**
   * ID of the Server type
   * @return id
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "1", required = true, value = "ID of the Server type")

  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    
    
    
    this.id = id;
  }


  public ServersGetServerResponseServerServerType includedTraffic(Long includedTraffic) {
    
    
    
    
    this.includedTraffic = includedTraffic;
    return this;
  }

   /**
   * Free traffic per month in bytes
   * @return includedTraffic
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "654321", required = true, value = "Free traffic per month in bytes")

  public Long getIncludedTraffic() {
    return includedTraffic;
  }


  public void setIncludedTraffic(Long includedTraffic) {
    
    
    
    this.includedTraffic = includedTraffic;
  }


  public ServersGetServerResponseServerServerType memory(Double memory) {
    
    
    
    
    this.memory = memory;
    return this;
  }

  public ServersGetServerResponseServerServerType memory(Integer memory) {
    
    
    
    
    this.memory = memory.doubleValue();
    return this;
  }

   /**
   * Memory a Server of this type will have in GB
   * @return memory
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "1", required = true, value = "Memory a Server of this type will have in GB")

  public Double getMemory() {
    return memory;
  }


  public void setMemory(Double memory) {
    
    
    
    this.memory = memory;
  }


  public ServersGetServerResponseServerServerType name(String name) {
    
    
    
    
    this.name = name;
    return this;
  }

   /**
   * Unique identifier of the Server type
   * @return name
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "cx11", required = true, value = "Unique identifier of the Server type")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    
    
    
    this.name = name;
  }


  public ServersGetServerResponseServerServerType prices(List<ServersGetServerResponseServerServerTypePricesInner> prices) {
    
    
    
    
    this.prices = prices;
    return this;
  }

  public ServersGetServerResponseServerServerType addPricesItem(ServersGetServerResponseServerServerTypePricesInner pricesItem) {
    this.prices.add(pricesItem);
    return this;
  }

   /**
   * Prices in different Locations
   * @return prices
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Prices in different Locations")

  public List<ServersGetServerResponseServerServerTypePricesInner> getPrices() {
    return prices;
  }


  public void setPrices(List<ServersGetServerResponseServerServerTypePricesInner> prices) {
    
    
    
    this.prices = prices;
  }


  public ServersGetServerResponseServerServerType storageType(StorageTypeEnum storageType) {
    
    
    
    
    this.storageType = storageType;
    return this;
  }

   /**
   * Type of Server boot drive. Local has higher speed. Network has better availability.
   * @return storageType
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Type of Server boot drive. Local has higher speed. Network has better availability.")

  public StorageTypeEnum getStorageType() {
    return storageType;
  }


  public void setStorageType(StorageTypeEnum storageType) {
    
    
    
    this.storageType = storageType;
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
   * @return the ServersGetServerResponseServerServerType instance itself
   */
  public ServersGetServerResponseServerServerType putAdditionalProperty(String key, Object value) {
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
    ServersGetServerResponseServerServerType serversGetServerResponseServerServerType = (ServersGetServerResponseServerServerType) o;
    return Objects.equals(this.description, serversGetServerResponseServerServerType.description) &&
        Objects.equals(this.architecture, serversGetServerResponseServerServerType.architecture) &&
        Objects.equals(this.cores, serversGetServerResponseServerServerType.cores) &&
        Objects.equals(this.cpuType, serversGetServerResponseServerServerType.cpuType) &&
        Objects.equals(this.deprecated, serversGetServerResponseServerServerType.deprecated) &&
        Objects.equals(this.deprecation, serversGetServerResponseServerServerType.deprecation) &&
        Objects.equals(this.disk, serversGetServerResponseServerServerType.disk) &&
        Objects.equals(this.id, serversGetServerResponseServerServerType.id) &&
        Objects.equals(this.includedTraffic, serversGetServerResponseServerServerType.includedTraffic) &&
        Objects.equals(this.memory, serversGetServerResponseServerServerType.memory) &&
        Objects.equals(this.name, serversGetServerResponseServerServerType.name) &&
        Objects.equals(this.prices, serversGetServerResponseServerServerType.prices) &&
        Objects.equals(this.storageType, serversGetServerResponseServerServerType.storageType)&&
        Objects.equals(this.additionalProperties, serversGetServerResponseServerServerType.additionalProperties);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, architecture, cores, cpuType, deprecated, deprecation, disk, id, includedTraffic, memory, name, prices, storageType, additionalProperties);
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
    sb.append("class ServersGetServerResponseServerServerType {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    architecture: ").append(toIndentedString(architecture)).append("\n");
    sb.append("    cores: ").append(toIndentedString(cores)).append("\n");
    sb.append("    cpuType: ").append(toIndentedString(cpuType)).append("\n");
    sb.append("    deprecated: ").append(toIndentedString(deprecated)).append("\n");
    sb.append("    deprecation: ").append(toIndentedString(deprecation)).append("\n");
    sb.append("    disk: ").append(toIndentedString(disk)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    includedTraffic: ").append(toIndentedString(includedTraffic)).append("\n");
    sb.append("    memory: ").append(toIndentedString(memory)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    prices: ").append(toIndentedString(prices)).append("\n");
    sb.append("    storageType: ").append(toIndentedString(storageType)).append("\n");
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
    openapiFields.add("cores");
    openapiFields.add("cpu_type");
    openapiFields.add("deprecated");
    openapiFields.add("deprecation");
    openapiFields.add("disk");
    openapiFields.add("id");
    openapiFields.add("included_traffic");
    openapiFields.add("memory");
    openapiFields.add("name");
    openapiFields.add("prices");
    openapiFields.add("storage_type");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("description");
    openapiRequiredFields.add("architecture");
    openapiRequiredFields.add("cores");
    openapiRequiredFields.add("cpu_type");
    openapiRequiredFields.add("deprecated");
    openapiRequiredFields.add("disk");
    openapiRequiredFields.add("id");
    openapiRequiredFields.add("included_traffic");
    openapiRequiredFields.add("memory");
    openapiRequiredFields.add("name");
    openapiRequiredFields.add("prices");
    openapiRequiredFields.add("storage_type");
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to ServersGetServerResponseServerServerType
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (!ServersGetServerResponseServerServerType.openapiRequiredFields.isEmpty()) { // has required fields but JSON object is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ServersGetServerResponseServerServerType is not found in the empty JSON string", ServersGetServerResponseServerServerType.openapiRequiredFields.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : ServersGetServerResponseServerServerType.openapiRequiredFields) {
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
      if (!jsonObj.get("cpu_type").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `cpu_type` to be a primitive type in the JSON string but got `%s`", jsonObj.get("cpu_type").toString()));
      }
      // validate the optional field `deprecation`
      if (jsonObj.get("deprecation") != null && !jsonObj.get("deprecation").isJsonNull()) {
        DeprecationInfoProperty9.validateJsonObject(jsonObj.getAsJsonObject("deprecation"));
      }
      if (!jsonObj.get("name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("name").toString()));
      }
      // ensure the json data is an array
      if (!jsonObj.get("prices").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `prices` to be an array in the JSON string but got `%s`", jsonObj.get("prices").toString()));
      }

      JsonArray jsonArrayprices = jsonObj.getAsJsonArray("prices");
      // validate the required field `prices` (array)
      for (int i = 0; i < jsonArrayprices.size(); i++) {
        ServersGetServerResponseServerServerTypePricesInner.validateJsonObject(jsonArrayprices.get(i).getAsJsonObject());
      };
      if (!jsonObj.get("storage_type").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `storage_type` to be a primitive type in the JSON string but got `%s`", jsonObj.get("storage_type").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ServersGetServerResponseServerServerType.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ServersGetServerResponseServerServerType' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ServersGetServerResponseServerServerType> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ServersGetServerResponseServerServerType.class));

       return (TypeAdapter<T>) new TypeAdapter<ServersGetServerResponseServerServerType>() {
           @Override
           public void write(JsonWriter out, ServersGetServerResponseServerServerType value) throws IOException {
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
           public ServersGetServerResponseServerServerType read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             // store additional fields in the deserialized instance
             ServersGetServerResponseServerServerType instance = thisAdapter.fromJsonTree(jsonObj);
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
  * Create an instance of ServersGetServerResponseServerServerType given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ServersGetServerResponseServerServerType
  * @throws IOException if the JSON string is invalid with respect to ServersGetServerResponseServerServerType
  */
  public static ServersGetServerResponseServerServerType fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ServersGetServerResponseServerServerType.class);
  }

 /**
  * Convert an instance of ServersGetServerResponseServerServerType to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

