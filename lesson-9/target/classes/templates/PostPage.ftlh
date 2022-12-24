<#import "commons.ftlh" as c>

<@c.page>
    <div class="postComment">
    <#if postA??>
        <@c.post post=postA/>
        <#if user??>
        <div class="comment form">
            <div class="header">New Comment</div>
            <div class="body">
                <form method="post" action="">
                    <div class="field">
                        <div class="name">
                            <label for="text">Comment</label>
                        </div>
                        <div class="value">
                            <textarea id="text" name="text">${comment.text!}</textarea>
                        </div>
                        <@c.error "comment.text"/>
                    </div>
                    <div class="button-field">
                        <input type="submit" value="Add">
                    </div>
                </form>
            </div>
        </div>
        <ul>
        </#if>
            <#list postA.comments as com>
                <@c.comment comment=com/>
            </#list>
        </ul>
        <#else>
        No such post
    </#if>
    </div>
</@c.page>