

# CertificatesCreateNewCertificateRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**certificate** | **String** | Certificate and chain in PEM format, in order so that each record directly certifies the one preceding. Required for type &#x60;uploaded&#x60; Certificates. |  [optional] |
|**domainNames** | **List&lt;String&gt;** | Domains and subdomains that should be contained in the Certificate issued by *Let&#39;s Encrypt*. Required for type &#x60;managed&#x60; Certificates. |  [optional] |
|**labels** | **Object** | User-defined labels (key-value pairs) |  [optional] |
|**name** | **String** | Name of the Certificate |  |
|**privateKey** | **String** | Certificate key in PEM format. Required for type &#x60;uploaded&#x60; Certificates. |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Choose between uploading a Certificate in PEM format or requesting a managed *Let&#39;s Encrypt* Certificate. |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| UPLOADED | &quot;uploaded&quot; |
| MANAGED | &quot;managed&quot; |



