<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="sortPosts" :users="users" :comments="comments"/>
            <Users v-if="page === 'Users'" :users="users"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <PostCom v-if="page === 'PostCom'" :post="myPost" :users="users" :comments="comments"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./page/Index";
import Users from "./page/Users";
import Enter from "./page/Enter";
import Register from "./page/Register";
import WritePost from "./page/WritePost";
import EditPost from "./page/EditPost";
import PostCom from "@/components/page/PostCom";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index"
        }
    },
    components: {
      PostCom,
        WritePost,
        Enter,
        Register,
        Index,
        Users,
        Sidebar,
        EditPost
    },
    props: ["posts", "postId", "users", "comments"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        },
        sortPosts: function () {
          return Object.values(this.posts).sort((a, b) => b.id - a.id);
        },
        myPost: function () {
          return Object.values(this.posts).filter(p => p.id === this.postId)[0];
        },
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page)
    }
}
</script>

<style scoped>

</style>
