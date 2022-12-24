<#import "./common.ftlh" as c>

<@c.page>
    <#assign p = c.findBy(posts, "id", post_id)>
    <@c.post p false/>
</@c.page>