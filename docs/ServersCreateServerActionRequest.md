

# ServersCreateServerActionRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**automount** | **Boolean** | Auto-mount Volumes after attach |  [optional] |
|**datacenter** | **String** | ID or name of Datacenter to create Server in (must not be used together with location) |  [optional] |
|**firewalls** | [**List&lt;ServersCreateServerActionRequestFirewallsInner&gt;**](ServersCreateServerActionRequestFirewallsInner.md) | Firewalls which should be applied on the Server&#39;s public network interface at creation time |  [optional] |
|**image** | **String** | ID or name of the Image the Server is created from |  |
|**labels** | **Object** | User-defined labels (key-value pairs) |  [optional] |
|**location** | **String** | ID or name of Location to create Server in (must not be used together with datacenter) |  [optional] |
|**name** | **String** | Name of the Server to create (must be unique per Project and a valid hostname as per RFC 1123) |  |
|**networks** | **List&lt;Long&gt;** | Network IDs which should be attached to the Server private network interface at the creation time |  [optional] |
|**placementGroup** | **Long** | ID of the Placement Group the server should be in |  [optional] |
|**publicNet** | [**ServersCreateServerActionRequestPublicNet**](ServersCreateServerActionRequestPublicNet.md) |  |  [optional] |
|**serverType** | **String** | ID or name of the Server type this Server should be created with |  |
|**sshKeys** | **List&lt;String&gt;** | SSH key IDs (&#x60;integer&#x60;) or names (&#x60;string&#x60;) which should be injected into the Server at creation time |  [optional] |
|**startAfterCreate** | **Boolean** | This automatically triggers a [Power on a Server-Server Action](https://docs.hetzner.cloud) after the creation is finished and is returned in the &#x60;next_actions&#x60; response object. |  [optional] |
|**userData** | **String** | Cloud-Init user data to use during Server creation. This field is limited to 32KiB. |  [optional] |
|**volumes** | **List&lt;Long&gt;** | Volume IDs which should be attached to the Server at the creation time. Volumes must be in the same Location. |  [optional] |



