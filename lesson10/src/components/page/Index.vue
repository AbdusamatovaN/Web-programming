<template>
    <div>
      <Post v-for="post in posts" :post="post" :key="post.id" :users="users" :comments="comments"/>
    </div>
</template>

<script>
import Post from "./Post";

export default {
    name: "Index",
    components: {
      Post
    },
    props: ["posts", "users", "comments"],
}
</script>

<style scoped>

</style>
