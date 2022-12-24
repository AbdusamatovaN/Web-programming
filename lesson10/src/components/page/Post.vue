<template>
  <article>
    <a href="#" @click.prevent="changePage('PostCom')" class="title">{{ post.title }}</a>
    <div class="information">{{ user.name }}</div>
    <div class="body">{{ post.text }}</div>
    <ul class="attachment">
      <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
      <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
    </ul>
    <div class="footer">
      <div class="left">
        <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
        <span class="positive-score">+173</span>
        <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
      </div>
      <div class="right">
        <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
        2 days ago
        <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
        <a href="#">{{ cntComments }}</a>
      </div>
    </div>
  </article>
</template>

<script>
export default {
  name: "Post",
  props: ["post", "users", "comments"],
  computed: {
    user: function (){
      return Object.values(this.users).filter(u => u.id === this.post.userId)[0];
    },

    cntComments: function () {
      return Object.values(this.comments).filter(c => c.postId === this.post.id).length
    }
  },
  methods: {
    changePage: function (page) {
      this.$root.$emit("onChangePage", page);
      this.$root.$emit("onChangePostId", this.post.id);
    },
  }
}
</script>

<style scoped>

</style>