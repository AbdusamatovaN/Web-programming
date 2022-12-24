<#import "./common.ftlh" as c>

<@c.page>
    <#assign n = 0/>
    <#list posts as p>
        <#if p.userId == user_id>
            <@c.post p false/>
            <#assign n++/>
        </#if>
    </#list>
    <#if n == 0>
        No posts(
    </#if>
</@c.page>