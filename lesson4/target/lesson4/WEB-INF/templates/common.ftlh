<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-logo-with-telegram.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@nameOnly user/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/contests", "name": "Contests"},
            {"href": "/users", "name": "Users"}
            ]/>
            <ul>
                <#list items as item>
                    <li>
                       <#if uri==item.href>
                           <a href="${item.href}" class="under">${item.name}</a>
                       <#else>
                           <a href="${item.href}">${item.name}</a>
                       </#if>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts?reverse as post>
            <section>
                <div class="header">Post #${post.id}</div>
                <div class="body"><@postShort post true/></div>
                <div class="footer">
                    <a href="/post?post_id=${post.id}">View all</a>
                </div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro post post flag>
    <article>
        <div class="title"><a href="/post?post_id=${post.id}">Post #${post.id}</a></div>
        <#assign u = findBy(users, "id", post.userId)!/>
        <div class="information">By <@userlink u/></div>
        <div class="body">
            <@postShort post flag/>
        </div>
        <ul class="attachment">
            <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
            <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
        </ul>
        <div class="footer">
            <div class="left">
                <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+173</span>
                <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                2 days ago
                <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#">68</a>
            </div>
        </div>
    </article>
</#macro>

<#macro postShort post flag>
    <#if (post.text?length>250 && flag)>
        ${post.text?substring(0, 250)}...
    <#else>
        ${post.text}
    </#if>
</#macro>

<#macro footer>
    <footer>
        2010-2021 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#macro nameOnly user>
<#-- @ftlvariable name="user" type="ru.itmo.web.lesson4.model.User" -->
    <a href="/user?handle=${user.handle}">${user.name}</a>
</#macro>

<#macro userlink user>
<#-- @ftlvariable name="user" type="ru.itmo.web.lesson4.model.User" -->
    <a href="/user?handle=${user.handle}" class="userlink on-class-${user.color}">${user.name}</a>
</#macro>

<#macro userPosts id>
    <a href="/posts?user_id=${id}"><@count id/></a>
</#macro>

<#macro count id>
    <#assign cnt = 0/>
    <#list posts as p>
        <#if p.userId == id>
            <#assign cnt++/>
        </#if>
    </#list>
    ${cnt}
</#macro>

<#function findBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function findNext value>
    <#list users as user>
        <#if user.id == value>
            <#return users[user?index+1]/>
        </#if>
    </#list>
</#function>

<#function findPrev value>
    <#list users as user>
        <#if user["id"] == value>
            <#return users[user?index-1]/>
        </#if>
    </#list>
</#function>
