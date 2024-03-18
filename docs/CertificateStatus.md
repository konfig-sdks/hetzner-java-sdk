

# CertificateStatus

Current status of a type `managed` Certificate, always *null* for type `uploaded` Certificates

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**error** | [**CertificateStatusError**](CertificateStatusError.md) |  |  [optional] |
|**issuance** | [**IssuanceEnum**](#IssuanceEnum) | Status of the issuance process of the Certificate |  [optional] |
|**renewal** | [**RenewalEnum**](#RenewalEnum) | Status of the renewal process of the Certificate. |  [optional] |



## Enum: IssuanceEnum

| Name | Value |
|---- | -----|
| PENDING | &quot;pending&quot; |
| COMPLETED | &quot;completed&quot; |
| FAILED | &quot;failed&quot; |



## Enum: RenewalEnum

| Name | Value |
|---- | -----|
| SCHEDULED | &quot;scheduled&quot; |
| PENDING | &quot;pending&quot; |
| FAILED | &quot;failed&quot; |
| UNAVAILABLE | &quot;unavailable&quot; |



