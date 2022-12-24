<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if user_id??>
        <#if !user_id?is_number>
            <#assign user_id = 1/>
        </#if>
        <#assign u = c.findBy(users, "id", user_id)!/>
    <#elseif logged_user_id??>
        <#if !logged_user_id?is_number>
            <#assign logged_user_id = 1/>
        </#if>
        <#assign u = c.findBy(users, "id", logged_user_id)!/>
    <#else>
        <#assign u = c.findBy(users, "handle", handle)!/>
    </#if>
    <#if u?has_content>
        <div class="user">
            <div class="info">
                <h2><@c.userlink u/></h2>
            <ul>
                <li>
                    id: ${u.id}
                </li>
                <li>
                    handle: ${u.handle}
                </li>
                <li>
                    name: ${u.name}
                </li>
                <li>
                    posts:
                        <@c.userPosts u.id/>
                </li>
            </ul>
            </div>
            <img class="per" src="/img/img.png" alt="untitled">
        </div>

        <#assign prev=c.findPrev(u.id)!/>
        <#if prev?has_content>
            <a href="user?handle=${prev.handle}">←</a>
        <#else>
            <span class="arrow-prev">←</span>
        </#if>

        <#assign next=c.findNext(u.id)!/>
        <#if next?has_content>
            <a href="user?handle=${next.handle}">→</a>
        <#else>
            <span class="arrow-next">→</span>
        </#if>
    <#else>
        No such user
    </#if>
</@c.page>
