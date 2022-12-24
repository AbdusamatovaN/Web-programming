<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <div class="no-data">
            No article are been posted yet
        </div>
        <template>
            <article>
                <div class="title"></div>
                <button class="hide-button"
                        data-article-id="-1">Hide</button>
                <div class="information"></div>
                <div class="body"></div>
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
        </template>
    </div>

    <script>
        $(function () {
            window.ajax({
                    action: "findAll"
                },
                function (response) {
                    if (response["articles"]){
                        $(".articles .no-data").hide()
                    }
                    response["articles"].forEach(function (article) {
                        if (article["hidden"]){
                            return
                        }
                        const $a = $($(".articles template").prop("content")).clone();
                        $a.find(".title").text(article["title"]);
                        const $button = $a.find(".hide-button");
                        $button.text("hide");
                        $button.attr("data-article-id", article["id"]);
                        $button.click(function () {
                            window.ajax({
                                action: "hide",
                                articleId: $(this).attr("data-article-id")
                            }, function (response) {
                                alert(response["error"])
                            })
                            /*
                            function (arg) {}
                            eq: (arg) => {}

                            (ignored) => {}
                            eq: _ => {}
                             */
                            if ($(this).text() === "hide") {
                                $(this).text("show")
                            } else {
                                $(this).text("hide")
                            }
                        })
                        $a.find(".information").text("By " +
                            (response["users"].find(function (user) {
                                return user.id === article["userId"];
                            }))["login"]);
                        $a.find(".body").text(article["text"]);
                        $(".articles").append($a);
                    });
                })
        })
    </script>
</@c.page>
