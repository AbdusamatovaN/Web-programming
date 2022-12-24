<template>
<div>
  <Post :post="post" :users="users" :comments="myComments"/>
  <ul>
    <Comment v-for="comment in myComments" :comment="comment" :key="comment.id" :users="users"/>
  </ul>
</div>
</template>

<script>
import Post from "./Post";
import Comment from "./Comment";

export default {
  name: "PostCom",
  props: ["users", "post", "comments"],
  components: {
    Post,
    Comment
  },
  computed: {
    myComments: function () {
      return Object.values(this.comments).filter(c => c.postId === this.post.id)
    },
  }
}
</script>

<style scoped>

</style>