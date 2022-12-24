<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="posts"/>
            <Users v-if="page === 'Users'" :users="viewUsers"/>
            <WritePost v-if="page === 'WritePost'"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./main/Index";
import Users from "./main/Users";
import Enter from "./main/Enter";
import Register from "./main/Register";
import WritePost from "@/components/main/WritePost";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index"
        }
    },
    components: {
      WritePost,
        Register,
        Enter,
        Index,
        Users,
        Sidebar
    },
    props: ["posts", "users"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        },
      viewUsers: function () {
        return Object.values(this.users).sort((a, b) => a.id - b.id);
      }
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page)
    }
}
</script>

<style scoped>

</style>
