<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./commons.ftlh" as c>

<@c.page>
    <#if userAc??>
        <div class="user">
            <div class="info">
                <ul>
                    <li>
                        id: ${userAc.id}
                    </li>
                    <li>
                        login: ${userAc.login}
                    </li>
                    <li>
                        creationTime: ${userAc.creationTime}
                    </li>
                </ul>
            </div>
        </div>
    <#else>
        No such user
    </#if>
</@c.page>
