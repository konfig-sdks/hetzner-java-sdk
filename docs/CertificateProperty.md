

# CertificateProperty


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**certificate** | **String** | Certificate and chain in PEM format, in order so that each record directly certifies the one preceding |  |
|**created** | **String** | Point in time when the Resource was created (in ISO-8601 format). |  |
|**domainNames** | **List&lt;String&gt;** | Domains and subdomains covered by the Certificate |  |
|**fingerprint** | **String** | SHA256 fingerprint of the Certificate |  |
|**id** | **Long** | ID of the Resource. Limited to 52 bits to ensure compatibility with JSON double precision floats.  |  |
|**labels** | **Map&lt;String, String&gt;** | User-defined labels (&#x60;key/value&#x60; pairs) for the Resource. For more information, see \&quot;[Labels](https://docs.hetzner.cloud)\&quot;.  |  |
|**name** | **String** | Name of the Resource. Must be unique per Project. |  |
|**notValidAfter** | **String** | Point in time when the Certificate stops being valid (in ISO-8601 format) |  |
|**notValidBefore** | **String** | Point in time when the Certificate becomes valid (in ISO-8601 format) |  |
|**status** | [**CertificatePropertyStatus**](CertificatePropertyStatus.md) |  |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Type of the Certificate |  [optional] |
|**usedBy** | [**List&lt;CertificatePropertyUsedByInner&gt;**](CertificatePropertyUsedByInner.md) | Resources currently using the Certificate |  |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| UPLOADED | &quot;uploaded&quot; |
| MANAGED | &quot;managed&quot; |



