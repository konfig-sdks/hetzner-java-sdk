

# ActionProperty26


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**command** | **String** | Command executed in the Action |  |
|**error** | [**ActionProperty26Error**](ActionProperty26Error.md) |  |  |
|**finished** | **String** | Point in time when the Action was finished (in ISO-8601 format). Only set if the Action is finished otherwise null. |  |
|**id** | **Long** | ID of the Action. Limited to 52 bits to ensure compatibility with JSON double precision floats.  |  |
|**progress** | **Integer** | Progress of Action in percent |  |
|**resources** | [**List&lt;ActionProperty26ResourcesInner&gt;**](ActionProperty26ResourcesInner.md) | Resources the Action relates to |  |
|**started** | **String** | Point in time when the Action was started (in ISO-8601 format) |  |
|**status** | [**StatusEnum**](#StatusEnum) | Status of the Action |  |



## Enum: StatusEnum

| Name | Value |
|---- | -----|
| SUCCESS | &quot;success&quot; |
| RUNNING | &quot;running&quot; |
| ERROR | &quot;error&quot; |



