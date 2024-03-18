

# Rule


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**description** | **String** | Description of the Rule |  [optional] |
|**destinationIps** | **List&lt;String&gt;** | List of permitted IPv4/IPv6 addresses in CIDR notation. Use &#x60;0.0.0.0/0&#x60; to allow all IPv4 addresses and &#x60;::/0&#x60; to allow all IPv6 addresses. You can specify 100 CIDRs at most. |  [optional] |
|**direction** | [**DirectionEnum**](#DirectionEnum) | Select traffic direction on which rule should be applied. Use &#x60;source_ips&#x60; for direction &#x60;in&#x60; and &#x60;destination_ips&#x60; for direction &#x60;out&#x60;. |  |
|**port** | **String** | Port or port range to which traffic will be allowed, only applicable for protocols TCP and UDP. A port range can be specified by separating two ports with a dash, e.g &#x60;1024-5000&#x60;. |  [optional] |
|**protocol** | [**ProtocolEnum**](#ProtocolEnum) | Type of traffic to allow |  |
|**sourceIps** | **List&lt;String&gt;** | List of permitted IPv4/IPv6 addresses in CIDR notation. Use &#x60;0.0.0.0/0&#x60; to allow all IPv4 addresses and &#x60;::/0&#x60; to allow all IPv6 addresses. You can specify 100 CIDRs at most. |  [optional] |



## Enum: DirectionEnum

| Name | Value |
|---- | -----|
| IN | &quot;in&quot; |
| OUT | &quot;out&quot; |



## Enum: ProtocolEnum

| Name | Value |
|---- | -----|
| TCP | &quot;tcp&quot; |
| UDP | &quot;udp&quot; |
| ICMP | &quot;icmp&quot; |
| ESP | &quot;esp&quot; |
| GRE | &quot;gre&quot; |



